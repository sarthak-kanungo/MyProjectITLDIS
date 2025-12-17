## Masters Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.masters` module:

- **User Authentication and Login** (Kubota user login with JWT token generation).
- **User Management** (Kubota user creation, role assignment, functionality mapping).
- **Customer Master Management** (Customer creation, search, change request with approval workflow).
- **Product Master Management** (Item/Machine master search, product hierarchy navigation).
- **Area Master Management** (Geographic hierarchy management: State, District, Tehsil, City, PinCode).
- **Dealer Master Management** (Dealer creation, search, territory management).
- **Employee Management** (Kubota employee and dealer employee management with organizational hierarchy).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the Masters module.

---

## 1. User Authentication and Login Flow

This flow shows how **Kubota users** authenticate and receive JWT tokens for accessing the system.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as KubotaUserController
    participant EncryptUtil as EncryptionDecryptionUtil
    participant UserRepo as KubotaUserRepository
    participant LoginService as LoginUserService
    participant JwtProvider as JwtProvider
    participant TokenService as UserTokenService
    participant DB as Database

    %% User Login Flow
    Note over Client,DB: User Authentication and Login
    
    Client->>Controller: POST /api/kubotauser/login<br/>(LoginDto with encrypted username/password)
    
    Controller->>EncryptUtil: decrypt(username)
    EncryptUtil-->>Controller: decryptedUsername
    
    Controller->>EncryptUtil: decrypt(password)
    EncryptUtil-->>Controller: decryptedPassword
    
    alt App Login (appLogin = true)
        Controller->>UserRepo: userAppLogin(username, password)
    else Web Login (appLogin = false)
        Controller->>UserRepo: userLogin(username, password)
    end
    
    UserRepo->>DB: SELECT user details with validation<br/>WHERE username = username<br/>AND password = password<br/>AND active_status = 'Y'
    DB-->>UserRepo: User details (id, name, departmentId, userType, etc.)
    UserRepo-->>Controller: Optional<Map<String, Object>> loginUser
    
    alt Login Successful (msg = "Done")
        Controller->>LoginService: getAuthenticatedUser(username)
        LoginService->>UserRepo: Find user by username
        UserRepo->>DB: SELECT * FROM kubota_user WHERE username = username
        DB-->>UserRepo: KubotaUser entity
        UserRepo-->>LoginService: User entity
        LoginService-->>Controller: AuthenticatedUser
        
        Controller->>JwtProvider: generateEncryptedToken(authenticatedUser)
        JwtProvider->>JwtProvider: Generate JWT token with user claims
        JwtProvider->>EncryptUtil: encrypt(token)
        EncryptUtil-->>JwtProvider: encryptedToken
        JwtProvider-->>Controller: encryptedToken
        
        Controller->>TokenService: recordToken(username, encryptedToken)
        TokenService->>DB: INSERT/UPDATE user_token<br/>SET token = encryptedToken<br/>WHERE username = username
        DB-->>TokenService: Token recorded
        
        Controller->>Controller: Build response DTO<br/>(departmentId, name, userType, dealerCode, etc.)
        Controller->>EncryptUtil: encrypt(sensitive fields)
        EncryptUtil-->>Controller: Encrypted response data
        
        Controller->>Controller: Create ApiResponse<br/>setToken(encryptedToken)<br/>setResult(responseDto)<br/>setMessage("User login successfully")
        Controller-->>Client: HTTP 200 OK (Login successful with token)
        
    else Login Failed
        Controller->>Controller: Create ApiResponse<br/>setMessage(error message)
        Controller-->>Client: HTTP 400 BAD REQUEST (Invalid credentials)
    end
```

---

## 2. User Creation and Functionality Assignment Flow

This flow shows how **new Kubota users** are created and assigned roles and functionalities.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as KubotaUserController
    participant UserAuth as UserAuthentication
    participant LoginService as LoginUserService
    participant UserRepo as KubotaUserRepository
    participant FuncMappingRepo as UserFunctionalityMappingRepository
    participant DB as Database

    %% User Creation Flow
    Note over Client,DB: Kubota User Creation with Functionality Assignment
    
    Client->>Controller: POST /api/kubotauser<br/>(AddLoginUserDto)
    
    Controller->>LoginService: saveUserFunctions(loginUser)
    
    LoginService->>UserAuth: getLoginId()
    UserAuth-->>LoginService: loginId
    
    LoginService->>LoginService: Set user fields<br/>setCreatedBy(loginId)<br/>setCreatedDate(new Date())
    
    LoginService->>UserRepo: save(kubotaUser)
    UserRepo->>DB: INSERT INTO kubota_user<br/>(username, password, employee_id, etc.)
    DB-->>UserRepo: User saved with ID
    UserRepo-->>LoginService: Saved KubotaUser entity
    
    alt Functionalities assigned
        loop For each functionality ID
            LoginService->>FuncMappingRepo: Check if mapping exists
            FuncMappingRepo->>DB: SELECT * FROM user_functionality_mapping<br/>WHERE user_id = userId<br/>AND functionality_id = funcId
            DB-->>FuncMappingRepo: Existing mapping (if any)
            
            alt Mapping doesn't exist
                LoginService->>LoginService: Create UserFunctionalityMapping<br/>setUser(user)<br/>setFunctionality(functionality)
                LoginService->>FuncMappingRepo: save(mapping)
                FuncMappingRepo->>DB: INSERT INTO user_functionality_mapping
                DB-->>FuncMappingRepo: Mapping saved
            end
        end
    end
    
    LoginService-->>Controller: User created successfully
    
    Controller->>Controller: Create ApiResponse<br/>setMessage("kubota User Added")
    Controller-->>Client: HTTP 200 OK (User created successfully)
```

---

## 3. Role Creation and Functionality Assignment Flow

This flow shows how **roles** are created and assigned functionalities in a hierarchical structure.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as RoleController
    participant UserAuth as UserAuthentication
    participant RoleRepo as RoleRepository
    participant FuncRepo as FunctionalityRepository
    participant MappingRepo as RoleFunctionalityMappingRepository
    participant DB as Database

    %% Role Creation Flow
    Note over Client,DB: Role Creation with Functionality Assignment
    
    Client->>Controller: POST /api/role<br/>(RoleFunctionDto with roleMaster and functionalityIds)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    Controller->>Controller: roleMaster.setRoleCode("Role-" + timestamp)
    Controller->>Controller: roleMaster.setCreatedBy(loginId)
    Controller->>Controller: roleMaster.setCreatedDate(new Date())
    
    Controller->>RoleRepo: save(roleMaster)
    RoleRepo->>DB: INSERT INTO role_master<br/>(role_code, role_name, applicable_to, etc.)
    DB-->>RoleRepo: Role saved with ID
    RoleRepo-->>Controller: Saved RoleMaster entity
    
    Controller->>Controller: Initialize mappings list
    
    loop For each functionality ID
        Controller->>Controller: Create RoleFunctionalityMapping<br/>setRoleMaster(roleMaster)<br/>setFunctionalityMaster(functionalityId)
        Controller->>MappingRepo: save(mapping)
        MappingRepo->>DB: INSERT INTO role_functionality_mapping<br/>(role_id, functionality_id)
        DB-->>MappingRepo: Mapping saved
    end
    
    Controller->>Controller: Create ApiResponse<br/>setMessage("Role assigned to functionality")
    Controller-->>Client: HTTP 200 OK (Role created successfully)
    
    %% Role Search Flow
    Note over Client,DB: Role Search
    
    Client->>Controller: POST /api/role/searchRole<br/>(RoleSearchDto with filters)
    
    Controller->>RoleRepo: searchRole(roleCode, roleName, isActive,<br/>applicableFor, page, size)
    
    RoleRepo->>DB: SELECT roles with filters<br/>WHERE role_code LIKE roleCode<br/>AND role_name LIKE roleName<br/>AND active_status = isActive<br/>AND applicable_to = applicableFor<br/>ORDER BY ... LIMIT page, size
    DB-->>RoleRepo: List of RoleSearchResponse with totalCount
    RoleRepo-->>Controller: Search results
    
    Controller->>Controller: Extract totalCount from first record
    Controller->>Controller: Create ApiResponse<br/>setResult(roleList)<br/>setCount(totalCount)
    Controller-->>Client: HTTP 200 OK (Role search results with pagination)
```

---

## 4. Customer Master Creation and Search Flow

This flow shows how **customers** are created, searched, and change requests are submitted with approval workflow.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as CustomerMasterController
    participant UserAuth as UserAuthentication
    participant CustomerRepo as CustomerMasterRepo
    participant ChangeRequestRepo as CustomerMasterChangeRequestRepo
    participant ApprovalRepo as CustomerMasterChangeRequestApprovalRepo
    participant DB as Database

    %% Customer Creation Flow
    Note over Client,DB: Customer Master Creation
    
    Client->>Controller: POST /api/customerMaster/addCustomer<br/>(CustomerMaster)
    
    Controller->>CustomerRepo: save(customerMaster)
    CustomerRepo->>DB: INSERT INTO customer_master<br/>(customer_code, first_name, mobile_number,<br/>address, pin_code, etc.)
    DB-->>CustomerRepo: Customer saved with ID
    CustomerRepo-->>Controller: Saved CustomerMaster entity
    
    Controller->>Controller: Create ApiResponse<br/>setMessage("Customer Added Successfully")
    Controller-->>Client: HTTP 200 OK (Customer created)
    
    %% Customer Change Request Flow
    Note over Client,DB: Customer Change Request Submission
    
    Client->>Controller: POST /api/customerMaster/addCustomerChangeRequest<br/>(CustomerMasterRequest)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    Controller->>Controller: customerRequest.setCreatedBy(loginId)
    Controller->>Controller: customerRequest.setCreatedDate(new Date())
    Controller->>Controller: customerRequest.setStatus("Approval Pending")
    
    Controller->>ChangeRequestRepo: getByCustomerId(customerId)
    ChangeRequestRepo->>DB: SELECT * FROM customer_master_change_request<br/>WHERE customer_id = customerId
    DB-->>ChangeRequestRepo: Optional<CustomerMasterRequest>
    
    alt New Change Request
        Controller->>ChangeRequestRepo: save(customerRequest)
        ChangeRequestRepo->>DB: INSERT INTO customer_master_change_request
        DB-->>ChangeRequestRepo: Request saved with custReqId
        
        Controller->>ChangeRequestRepo: getCustomerChangeRequestApprovalHierarchyLevel(dealerId)
        ChangeRequestRepo->>DB: SELECT approval hierarchy levels<br/>FROM designation_hierarchy<br/>WHERE dealer_id = dealerId<br/>ORDER BY approver_level_seq
        DB-->>ChangeRequestRepo: List of approval hierarchy levels
        
        Controller->>Controller: Create approval records for each hierarchy level
        
        loop For each hierarchy level
            Controller->>Controller: Create CustomerMasterRequestApproval<br/>setCustReqId(custReqId)<br/>setApproverLevelSeq(levelSeq)<br/>setDesignationLevelId(levelId)<br/>setApprovalStatus("Pending")<br/>setRejectedFlag('N')
            Controller->>ApprovalRepo: save(approval)
            ApprovalRepo->>DB: INSERT INTO customer_master_change_request_approval
            DB-->>ApprovalRepo: Approval record saved
        end
        
    else Existing Change Request (Update)
        Controller->>Controller: Update existing request fields
        Controller->>ChangeRequestRepo: save(updatedRequest)
        ChangeRequestRepo->>DB: UPDATE customer_master_change_request<br/>SET ... WHERE cust_req_id = custReqId
        DB-->>ChangeRequestRepo: Request updated
    end
    
    Controller->>Controller: Create ApiResponse<br/>setMessage("Customer Change Request Saved Successfully")
    Controller-->>Client: HTTP 200 OK (Change request submitted)
    
    %% Customer Search Flow
    Note over Client,DB: Customer Master Search
    
    Client->>Controller: POST /api/customerMaster/customerMasterSearch<br/>(CustomerMasterSearchDto)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>CustomerRepo: getCustomerMasterSearch(customerCode, mobileNo,<br/>page, size, username)
    
    CustomerRepo->>DB: SELECT customer details with filters<br/>WHERE customer_code LIKE customerCode<br/>AND mobile_number LIKE mobileNo<br/>AND dealer_id = dealerId<br/>ORDER BY ... LIMIT page, size
    DB-->>CustomerRepo: List of CustomerMasterSearchResponseDto with totalCount
    CustomerRepo-->>Controller: Search results
    
    Controller->>Controller: Extract totalCount from first record
    Controller->>Controller: Create ApiResponse<br/>setResult(customerList)<br/>setCount(totalCount)
    Controller-->>Client: HTTP 200 OK (Customer search results with pagination)
```

---

## 5. Product Master Search and Hierarchy Navigation Flow

This flow shows how **product/item masters** are searched and product hierarchies (Series, Model, SubModel, Variant) are navigated.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ItemMasterController
    participant UserAuth as UserAuthentication
    participant MachineRepo as MachineMasterRepository
    participant DB as Database

    %% Product Search Flow
    Note over Client,DB: Product/Item Master Search
    
    Client->>Controller: POST /api/master/product/searchItem<br/>(ItemSearchDto with filters)
    
    Controller->>MachineRepo: searchItem(itemNo, model, product,<br/>series, subModel, variant, page, size)
    
    MachineRepo->>DB: SELECT item details with filters<br/>FROM machine_master m<br/>JOIN product_master p ON m.product_id = p.id<br/>JOIN series s ON m.series_id = s.id<br/>JOIN model_master mo ON m.model_id = mo.id<br/>WHERE item_no LIKE itemNo<br/>AND model LIKE model<br/>AND product LIKE product<br/>ORDER BY ... LIMIT page, size
    DB-->>MachineRepo: List of item search results
    
    Controller->>MachineRepo: searchItemCount(itemNo, model, product,<br/>series, subModel, variant, page, size)
    MachineRepo->>DB: SELECT COUNT(*) FROM machine_master<br/>WHERE ... (same filters)
    DB-->>MachineRepo: totalCount
    MachineRepo-->>Controller: Search results and count
    
    Controller->>Controller: Create ApiResponse<br/>setResult(itemList)<br/>setCount(totalCount)
    Controller-->>Client: HTTP 200 OK (Item search results with pagination)
    
    %% Product Hierarchy Navigation Flow
    Note over Client,DB: Product Hierarchy Navigation
    
    Client->>Controller: GET /api/master/product/seriesDropdown?product=productId
    
    Controller->>MachineRepo: getSeriesByProductId(productId)
    MachineRepo->>DB: SELECT * FROM series<br/>WHERE product_id = productId<br/>AND active_status = 'Y'
    DB-->>MachineRepo: List of Series
    MachineRepo-->>Controller: Series list
    Controller-->>Client: HTTP 200 OK (Series list)
    
    Client->>Controller: GET /api/master/product/modelDropdown?series=seriesId
    
    Controller->>MachineRepo: getModelBySeriesId(seriesId)
    MachineRepo->>DB: SELECT * FROM model_master<br/>WHERE series_id = seriesId<br/>AND active_status = 'Y'
    DB-->>MachineRepo: List of Models
    MachineRepo-->>Controller: Model list
    Controller-->>Client: HTTP 200 OK (Model list)
    
    Client->>Controller: GET /api/master/product/subModelDropdown?model=modelId
    
    Controller->>MachineRepo: getSubModelByModelId(modelId)
    MachineRepo->>DB: SELECT * FROM sub_model_master<br/>WHERE model_id = modelId<br/>AND active_status = 'Y'
    DB-->>MachineRepo: List of SubModels
    MachineRepo-->>Controller: SubModel list
    Controller-->>Client: HTTP 200 OK (SubModel list)
    
    Client->>Controller: GET /api/master/product/variantDropdown?subModel=subModelId
    
    Controller->>MachineRepo: getVariantBySubModel(subModelId)
    MachineRepo->>DB: SELECT * FROM variant_master<br/>WHERE sub_model_id = subModelId<br/>AND active_status = 'Y'
    DB-->>MachineRepo: List of Variants
    MachineRepo-->>Controller: Variant list
    Controller-->>Client: HTTP 200 OK (Variant list)
    
    %% Item Details Retrieval Flow
    Note over Client,DB: Item Details by Item Number
    
    Client->>Controller: GET /api/master/product/getByItemNo?itemNo=itemNo&requestFrom=source&depot=depotId
    
    Controller->>UserAuth: getBranchId()
    UserAuth-->>Controller: branchId
    
    Controller->>MachineRepo: getByItemNo(itemNo, requestFrom, depot, branchId)
    MachineRepo->>DB: SELECT item details with pricing<br/>FROM machine_master m<br/>JOIN product_master p ON m.product_id = p.id<br/>LEFT JOIN pricing_master pr ON m.id = pr.item_id<br/>WHERE m.item_no = itemNo<br/>AND pr.branch_id = branchId
    DB-->>MachineRepo: Map<String, Object> item details
    MachineRepo-->>Controller: Item details with pricing
    Controller-->>Client: HTTP 200 OK (Item details)
```

---

## 6. Area Master Management Flow

This flow shows how **geographic hierarchies** (State, District, Tehsil, City, PinCode, PostOffice) are managed and queried.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as AreaController
    participant UserAuth as UserAuthentication
    participant ModelRepo as ModelRepo
    participant StateRepo as StateRepository
    participant DistrictRepo as DistrictRepository
    participant TehsilRepo as TehsilRepository
    participant CityRepo as CityRepository
    participant PinCodeRepo as PinCodeRepository
    participant PostOfficeRepo as PostOfficeRepository
    participant DB as Database

    %% Area Master Addition Flow
    Note over Client,DB: Area Master Bulk Addition
    
    Client->>Controller: POST /api/master/areamaster/addAreaMaster<br/>(List<AreaMaster>)
    
    loop For each AreaMaster
        Controller->>StateRepo: findByState(state)
        StateRepo->>DB: SELECT * FROM state WHERE state = state
        DB-->>StateRepo: State entity (or null)
        
        alt State doesn't exist
            Controller->>Controller: Create new State<br/>setState(state)
            Controller->>StateRepo: save(state)
            StateRepo->>DB: INSERT INTO state
            DB-->>StateRepo: State saved with ID
        end
        
        Controller->>DistrictRepo: findByDistrict(district)
        DistrictRepo->>DB: SELECT * FROM district WHERE district = district
        DB-->>DistrictRepo: District entity (or null)
        
        alt District doesn't exist
            Controller->>Controller: Create new District<br/>setDistrict(district)<br/>setState(state)
            Controller->>DistrictRepo: save(district)
            DistrictRepo->>DB: INSERT INTO district
            DB-->>DistrictRepo: District saved with ID
        end
        
        Controller->>TehsilRepo: findByTehsil(tehsil)
        TehsilRepo->>DB: SELECT * FROM tehsil WHERE tehsil = tehsil
        DB-->>TehsilRepo: Tehsil entity (or null)
        
        alt Tehsil doesn't exist
            Controller->>Controller: Create new Tehsil<br/>setTehsil(tehsil)<br/>setDistrict(district)
            Controller->>TehsilRepo: save(tehsil)
            TehsilRepo->>DB: INSERT INTO tehsil
            DB-->>TehsilRepo: Tehsil saved with ID
        end
        
        Controller->>CityRepo: findByCity(city)
        CityRepo->>DB: SELECT * FROM city WHERE city = city
        DB-->>CityRepo: City entity (or null)
        
        alt City doesn't exist
            Controller->>Controller: Create new City<br/>setCity(city)<br/>setTehsil(tehsil)
            Controller->>CityRepo: save(city)
            CityRepo->>DB: INSERT INTO city
            DB-->>CityRepo: City saved with ID
        end
        
        Controller->>PinCodeRepo: findByPinCode(pincode)
        PinCodeRepo->>DB: SELECT * FROM pin_code WHERE pin_code = pincode
        DB-->>PinCodeRepo: PinCode entity (or null)
        
        alt PinCode doesn't exist
            Controller->>Controller: Create new PinCode<br/>setPinCode(pincode)<br/>setCity(city)
            Controller->>PinCodeRepo: save(pinCode)
            PinCodeRepo->>DB: INSERT INTO pin_code
            DB-->>PinCodeRepo: PinCode saved with ID
        end
        
        Controller->>PostOfficeRepo: findByPostOffice(postOffice)
        PostOfficeRepo->>DB: SELECT * FROM post_offices WHERE post_office = postOffice
        DB-->>PostOfficeRepo: PostOffice entity (or null)
        
        alt PostOffice doesn't exist
            Controller->>Controller: Create new PostOffice<br/>setPostOffice(postOffice)<br/>setPinCode(pinCode)
            Controller->>PostOfficeRepo: save(postOffice)
            PostOfficeRepo->>DB: INSERT INTO post_offices
            DB-->>PostOfficeRepo: PostOffice saved
        end
    end
    
    Controller-->>Client: HTTP 200 OK (Area master added)
    
    %% Area Hierarchy Autocomplete Flow
    Note over Client,DB: Area Hierarchy Autocomplete
    
    Client->>Controller: GET /api/master/areamaster/getStateAutocomplete<br/>?countryId=countryId&stateName=stateName
    
    Controller->>ModelRepo: getStateAutoComplete(countryId, stateName)
    ModelRepo->>DB: SELECT * FROM state<br/>WHERE country_id = countryId<br/>AND state LIKE stateName<br/>LIMIT 10
    DB-->>ModelRepo: List of States
    ModelRepo-->>Controller: State list
    Controller-->>Client: HTTP 200 OK (State autocomplete results)
    
    Client->>Controller: GET /api/master/areamaster/getDistrictAutocomplete<br/>?stateId=stateId&districtName=districtName
    
    Controller->>ModelRepo: getDistrictAutoComplete(stateId, districtName)
    ModelRepo->>DB: SELECT * FROM district<br/>WHERE state_id = stateId<br/>AND district LIKE districtName<br/>LIMIT 10
    DB-->>ModelRepo: List of Districts
    ModelRepo-->>Controller: District list
    Controller-->>Client: HTTP 200 OK (District autocomplete results)
    
    Client->>Controller: GET /api/master/areamaster/getPinCodeAutocomplete<br/>?cityId=cityId&pincode=pincode
    
    Controller->>ModelRepo: getPinCodeAutoComplete(pincode, cityId)
    ModelRepo->>DB: SELECT * FROM pin_code<br/>WHERE city_id = cityId<br/>AND pin_code LIKE pincode<br/>LIMIT 10
    DB-->>ModelRepo: List of PinCodes
    ModelRepo-->>Controller: PinCode list
    Controller-->>Client: HTTP 200 OK (PinCode autocomplete results)
```

---

## 7. Dealer Master Management Flow

This flow shows how **dealers** are created, searched, and managed with territory and region information.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as DealerMasterController
    participant UserAuth as UserAuthentication
    participant StorageService as StorageService
    participant DealerRepo as DealerMasterRepo
    participant DB as Database

    %% Dealer Creation Flow
    Note over Client,DB: Dealer Master Creation
    
    Client->>Controller: POST /api/dealerMaster/saveDealerMaster<br/>(multipart/form-data with DealerMaster and logo file)
    
    Controller->>Controller: Extract logo file from request
    Controller->>Controller: Build filename = "logo" + timestamp + "_" + originalFilename
    
    Controller->>StorageService: store(logoImage, filename)
    StorageService->>StorageService: Save file to storage location
    StorageService-->>Controller: File stored
    
    Controller->>Controller: dealerMaster.setLogo(filename)
    
    Controller->>DealerRepo: save(dealerMaster)
    DealerRepo->>DB: INSERT INTO dealer_master<br/>(dealer_code, dealer_name, logo,<br/>zone, region, area, territory_level, etc.)
    DB-->>DealerRepo: Dealer saved with ID
    DealerRepo-->>Controller: Saved DealerMaster entity
    
    Controller->>Controller: Create ApiResponse<br/>setMessage("Dealer Master successfully saved")
    Controller-->>Client: HTTP 200 OK (Dealer created successfully)
    
    %% Dealer Search Flow
    Note over Client,DB: Dealer Master Search
    
    Client->>Controller: POST /api/dealerMaster/searchDealer<br/>(DealerMasterSearchDto with filters)
    
    Controller->>DealerRepo: searchDealerMaster(zone, region, area,<br/>territoryLevel, dealerCode, dealerName,<br/>activeStatus, subsidyDealer, page, size)
    
    DealerRepo->>DB: SELECT dealer details with filters<br/>FROM dealer_master d<br/>LEFT JOIN territory_master t ON d.territory_id = t.id<br/>WHERE (zone IS NULL OR d.zone = zone)<br/>AND (region IS NULL OR d.region = region)<br/>AND (dealer_code LIKE dealerCode)<br/>AND (dealer_name LIKE dealerName)<br/>AND (active_status = activeStatus OR activeStatus IS NULL)<br/>ORDER BY ... LIMIT page, size
    DB-->>DealerRepo: List of dealer search results
    
    Controller->>DealerRepo: searchDealerMasterCount(zone, region, area,<br/>territoryLevel, dealerCode, dealerName,<br/>activeStatus, subsidyDealer, page, size)
    DealerRepo->>DB: SELECT COUNT(*) FROM dealer_master<br/>WHERE ... (same filters)
    DB-->>DealerRepo: totalCount
    DealerRepo-->>Controller: Search results and count
    
    Controller->>Controller: Create ApiResponse<br/>setResult(dealerList)<br/>setCount(totalCount)
    Controller-->>Client: HTTP 200 OK (Dealer search results with pagination)
    
    %% Dealer Region Info Flow
    Note over Client,DB: Dealer Region Information
    
    Client->>Controller: GET /api/dealerMaster/getDealerRegionInfo?showAll=Y
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>Controller: allDist = (showAll == "Y") ? true : false
    
    Controller->>DealerRepo: getDealerRegionInfo(username, allDist)
    DealerRepo->>DB: SELECT region information<br/>FROM dealer_master d<br/>JOIN territory_master t ON d.territory_id = t.id<br/>WHERE d.dealer_code IN (accessible dealers)<br/>GROUP BY zone, region, area
    DB-->>DealerRepo: List of region information
    DealerRepo-->>Controller: Region info list
    Controller-->>Client: HTTP 200 OK (Dealer region information)
```

---

## 8. Employee Management Flow

This flow shows how **Kubota employees** are created, updated, searched, and organizational hierarchy is managed.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as KubotaEmployeeController
    participant UserAuth as UserAuthentication
    participant EmployeeRepo as KubotaEmployeeRepository
    participant OrgHierRepo as KubotaEmployeeMasterOrgHierRepository
    participant DB as Database

    %% Employee Creation Flow
    Note over Client,DB: Kubota Employee Creation
    
    Client->>Controller: POST /api/kubotaemployee/add<br/>(KubotaEmployeeMaster)
    
    Controller->>EmployeeRepo: save(kubotaEmployeeMaster)
    EmployeeRepo->>DB: INSERT INTO kubota_employee_master<br/>(employee_code, employee_name, contact_no,<br/>email_id, department_id, designation_id,<br/>designation_level_id, reporting_user_id, etc.)
    DB-->>EmployeeRepo: Employee saved with ID
    EmployeeRepo-->>Controller: Saved KubotaEmployeeMaster entity
    
    Controller->>Controller: Create ApiResponse<br/>setMessage("Employee Saved Successfully")
    Controller-->>Client: HTTP 200 OK (Employee created)
    
    %% Employee Search Flow
    Note over Client,DB: Employee Search
    
    Client->>Controller: POST /api/kubotaemployee/searchEmployee<br/>(EmployeeSearchDto)
    
    Controller->>EmployeeRepo: searchEmployee(employeeCode, employeeName,<br/>page, size)
    
    EmployeeRepo->>DB: SELECT employee details with filters<br/>FROM kubota_employee_master e<br/>LEFT JOIN department_master d ON e.department_id = d.id<br/>LEFT JOIN designation_master des ON e.designation_id = des.id<br/>WHERE (employee_code LIKE employeeCode OR employeeCode IS NULL)<br/>AND (employee_name LIKE employeeName OR employeeName IS NULL)<br/>ORDER BY ... LIMIT page, size
    DB-->>EmployeeRepo: List of EmployeeSearchResponse with totalRecords
    EmployeeRepo-->>Controller: Search results
    
    Controller->>Controller: Extract totalRecords from first record
    Controller->>Controller: Create ApiResponse<br/>setResult(employeeList)<br/>setCount(totalRecords)
    Controller-->>Client: HTTP 200 OK (Employee search results with pagination)
    
    %% Employee Update Flow
    Note over Client,DB: Employee Update
    
    Client->>Controller: POST /api/kubotaemployee/update<br/>(KubotaEmployeeMaster)
    
    Controller->>EmployeeRepo: updateEmployee(employeeCode, employeeName,<br/>activeStatus, contactNo, emailId,<br/>departmentId, designationId, designationLevelId,<br/>reportingUserId, managementAccess)
    
    EmployeeRepo->>DB: UPDATE kubota_employee_master<br/>SET employee_name = employeeName,<br/>active_status = activeStatus,<br/>contact_no = contactNo,<br/>email_id = emailId,<br/>department_id = departmentId,<br/>designation_id = designationId,<br/>designation_level_id = designationLevelId,<br/>reporting_user_id = reportingUserId,<br/>management_access = managementAccess<br/>WHERE employee_code = employeeCode
    DB-->>EmployeeRepo: Employee updated
    EmployeeRepo-->>Controller: Update successful
    
    Controller->>Controller: Create ApiResponse<br/>setMessage("Employee Updated Successfully")
    Controller-->>Client: HTTP 200 OK (Employee updated)
    
    %% Organizational Hierarchy Assignment Flow
    Note over Client,DB: Organizational Hierarchy Assignment
    
    Client->>Controller: POST /api/kubotaemployee/assigneDeptInOrgHier<br/>(EmployeeOrgHierDto)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    loop For each organization hierarchy item
        Controller->>OrgHierRepo: checkForHoUserStatus(hoEmployeeId, orgHierarchyId)
        OrgHierRepo->>DB: SELECT id FROM kubota_employee_master_org_hier<br/>WHERE ho_usr_id = hoEmployeeId<br/>AND org_hierarchy_id = orgHierarchyId
        DB-->>OrgHierRepo: Existing hierarchy ID (or null)
        
        alt Hierarchy mapping exists
            Controller->>OrgHierRepo: findById(existingId)
            OrgHierRepo->>DB: SELECT * FROM kubota_employee_master_org_hier<br/>WHERE id = existingId
            DB-->>OrgHierRepo: Existing KubotaEmployeeMasterOrgHier
            
            Controller->>Controller: hier.setLastModifiedBy(loginId)<br/>hier.setLastModifiedDate(new Date())<br/>hier.setIsactive("Y")
            Controller->>OrgHierRepo: save(hier)
            OrgHierRepo->>DB: UPDATE kubota_employee_master_org_hier<br/>SET isactive = 'Y', last_modified_by = loginId<br/>WHERE id = existingId
            DB-->>OrgHierRepo: Hierarchy updated
            
        else New hierarchy mapping
            Controller->>Controller: Create KubotaEmployeeMasterOrgHier<br/>setHoUsrId(hoEmployeeId)<br/>setOrgHierarchyId(orgHierarchyId)<br/>setCreatedBy(loginId)<br/>setCreatedDate(new Date())<br/>setIsactive("Y")
            Controller->>OrgHierRepo: save(orgHier)
            OrgHierRepo->>DB: INSERT INTO kubota_employee_master_org_hier<br/>(ho_usr_id, org_hierarchy_id, created_by, isactive)
            DB-->>OrgHierRepo: Hierarchy mapping saved
        end
    end
    
    Controller->>Controller: Create ApiResponse<br/>setMessage("Saved Organization Hierarchy Successfully")
    Controller-->>Client: HTTP 200 OK (Organizational hierarchy assigned)
```

---

## 9. Dynamic Menu Generation Flow

This flow shows how **dynamic menus** are generated based on user functionality mappings and role assignments.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as KubotaUserController
    participant UserAuth as UserAuthentication
    participant FuncMappingRepo as UserFunctionalityMappingRepository
    participant DB as Database

    %% Dynamic Menu Generation Flow
    Note over Client,DB: Dynamic Menu Generation Based on User Functionalities
    
    Client->>Controller: GET /api/kubotauser/getFunctionalityMappedToUser/{userId}
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>FuncMappingRepo: getFirstLevelFunctionality(userId)
    FuncMappingRepo->>DB: SELECT functionality details<br/>FROM user_functionality_mapping ufm<br/>JOIN functionality_master fm ON ufm.functionality_id = fm.id<br/>WHERE ufm.user_id = userId<br/>AND fm.parent_id IS NULL<br/>ORDER BY fm.sequence_no
    DB-->>FuncMappingRepo: List of first-level functionalities
    FuncMappingRepo-->>Controller: First-level functionalities
    
    Controller->>Controller: Initialize responses list
    
    loop For each first-level functionality
        Controller->>Controller: Create DynamicMenuResponse<br/>setFunctionality(functionality)<br/>setId(id)<br/>setParentId(parentId)<br/>setRouterLink(routerLink)<br/>setSequenceNo(sequenceNo)
        
        Controller->>FuncMappingRepo: getFunctionalityByParentIdAndUserId(parentId, userId)
        FuncMappingRepo->>DB: SELECT functionality details<br/>FROM user_functionality_mapping ufm<br/>JOIN functionality_master fm ON ufm.functionality_id = fm.id<br/>WHERE ufm.user_id = userId<br/>AND fm.parent_id = parentId<br/>ORDER BY fm.sequence_no
        DB-->>FuncMappingRepo: List of second-level functionalities
        
        loop For each second-level functionality
            Controller->>Controller: Create DynamicMenuResponse (level 2)<br/>setFunctionality(functionality)<br/>setId(id)<br/>setParentId(parentId)<br/>setRouterLink(routerLink)
            
            Controller->>FuncMappingRepo: getFunctionalityByParentIdAndUserId(level2Id, userId)
            FuncMappingRepo->>DB: SELECT functionality details<br/>FROM user_functionality_mapping ufm<br/>JOIN functionality_master fm ON ufm.functionality_id = fm.id<br/>WHERE ufm.user_id = userId<br/>AND fm.parent_id = level2Id<br/>ORDER BY fm.sequence_no
            DB-->>FuncMappingRepo: List of third-level functionalities
            
            loop For each third-level functionality
                Controller->>Controller: Create DynamicMenuResponse (level 3)<br/>setFunctionality(functionality)<br/>setId(id)<br/>setParentId(parentId)<br/>setRouterLink(routerLink)
                Controller->>Controller: Add to level 2 children list
            end
            
            Controller->>Controller: Add level 2 to level 1 children list
        end
        
        Controller->>Controller: Add level 1 to responses list
    end
    
    Controller->>Controller: Create ApiResponse<br/>setResult(responses)<br/>setMessage("get functionality mapped to user")
    Controller-->>Client: HTTP 200 OK (Hierarchical menu structure)
    
    %% Router Access Check Flow
    Note over Client,DB: Router Access Check
    
    Client->>Controller: GET /api/kubotauser/checkRouterAccessibility<br/>?userId=userId&routerLink=routerLink
    
    Controller->>FuncMappingRepo: checkUserRouterAccessibility(userId, routerLink)
    FuncMappingRepo->>DB: SELECT COUNT(*) FROM user_functionality_mapping ufm<br/>JOIN functionality_master fm ON ufm.functionality_id = fm.id<br/>WHERE ufm.user_id = userId<br/>AND fm.router_link = routerLink
    DB-->>FuncMappingRepo: Access count (0 or 1)
    FuncMappingRepo-->>Controller: Access result
    
    alt User has access (result > 0)
        Controller->>Controller: Create ApiResponse<br/>setResult(true)<br/>setMessage("Good to Go!")
    else User doesn't have access (result == 0)
        Controller->>Controller: Create ApiResponse<br/>setResult(false)<br/>setMessage("User Access Denied!")
    end
    
    Controller-->>Client: HTTP 200 OK (Access check result)
```

---

## Summary

The **Masters module** provides comprehensive **master data management** functionality for the KUBOTA DMS:

- **User Management**:
  - User authentication with JWT token generation and encryption
  - User creation with role and functionality assignment
  - Dynamic menu generation based on user permissions
  - Router access control and validation
  - Password reset and forgot password functionality

- **Role and Functionality Management**:
  - Role creation with hierarchical functionality assignment
  - Role search with filters (role code, name, active status, applicable to)
  - Functionality tree generation for role assignment
  - Role update with functionality remapping

- **Customer Master**:
  - Customer creation and search with multiple filters
  - Customer change request workflow with multi-level approval hierarchy
  - Customer validation (mobile number, customer code)
  - Vehicle details and chassis number tracking
  - Customer autocomplete functionality

- **Product Master**:
  - Product/item search with filters (item number, model, product, series, submodel, variant)
  - Product hierarchy navigation (Product → Series → Model → SubModel → Variant)
  - Item details retrieval with pricing information
  - Product autocomplete and dropdown population

- **Area Master**:
  - Geographic hierarchy management (Country → State → District → Tehsil → City → PinCode → PostOffice)
  - Bulk area master addition with automatic parent entity creation
  - Area hierarchy autocomplete for all levels
  - PinCode and locality lookup

- **Dealer Master**:
  - Dealer creation with logo upload
  - Dealer search with multiple filters (zone, region, area, territory, dealer code/name, status)
  - Dealer region information retrieval
  - Territory and area level management

- **Employee Management**:
  - Kubota employee creation, update, and search
  - Organizational hierarchy assignment
  - Employee status management (active/inactive)
  - Reporting hierarchy management
  - Department and designation management

- **Spares Master**:
  - Spare part master search and autocomplete
  - Spare part details retrieval for MRC and quotation
  - Item number validation and lookup

The Masters module serves as the foundation for all other modules in the KUBOTA DMS, providing essential master data that is referenced throughout the system for sales, service, warranty, CRM, and other business processes. All master data operations include proper authentication, authorization checks, and audit trail maintenance (created by, created date, last modified by, last modified date).

