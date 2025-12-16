# Extended Warranty Registration Module Sequence Diagrams

This document contains detailed sequence diagrams for all flows within the Extended Warranty Registration (EXT WARRANTY REG) Module of the ITLDIS system.

## Table of Contents

1. [Initialize Add Extended Warranty Flow](#1-initialize-add-extended-warranty-flow)
2. [Add Extended Warranty Flow](#2-add-extended-warranty-flow)
3. [View Extended Warranty Flow](#3-view-extended-warranty-flow)
4. [Edit Extended Warranty Flow](#4-edit-extended-warranty-flow)
5. [Update Extended Warranty Flow](#5-update-extended-warranty-flow)
6. [Get Premium Amount Flow (AJAX)](#6-get-premium-amount-flow-ajax)
7. [Check Vehicle Existence Flow (AJAX)](#7-check-vehicle-existence-flow-ajax)
8. [Check Chassis Existence Flow (AJAX)](#8-check-chassis-existence-flow-ajax)
9. [Get Extended Warranty Popup View Flow](#9-get-extended-warranty-popup-view-flow)
10. [Export Extended Warranty Flow](#10-export-extended-warranty-flow)

---

## 1. Initialize Add Extended Warranty Flow

This diagram shows the initialization process when adding a new Extended Warranty registration.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant MethodUtility as MethodUtility
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Add Extended Warranty Tile
    Browser->>StrutsFramework: GET /serviceProcess.do?option=initAddExtWarranty&srch=viewDiv&vinNo=CHASSIS123
    StrutsFramework->>ServiceAction: initAddExtWarranty(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("dealerCode")
    HTTPSession-->>ServiceAction: String dealerCode
    
    ServiceAction->>ServiceAction: Extract vinNo from request parameter
    ServiceAction->>ServiceAction: Extract srch parameter
    
    alt srch equals viewDiv
        ServiceAction->>HibernateUtil: getSessionFactory().openSession()
        HibernateUtil-->>ServiceAction: Session hsession
        
        ServiceAction->>MethodUtility: getNumberEW(hsession, "EWMLoaderDetail", dealerCode, "EW")
        MethodUtility->>Database: Query EWMLoaderDetail to get next sequence number
        Database-->>MethodUtility: Next EW reference number
        MethodUtility-->>ServiceAction: String seqNo (EW reference number)
        
        ServiceAction->>ServiceAction: Set flagCheck = "viewDiv"
        ServiceAction->>ServiceAction: Set ewLoaderId = seqNo
        
        ServiceAction->>ServiceDAO: getPolicyType(sf)
        ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
        HibernateUtil-->>ServiceDAO: Session hrbsession
        ServiceDAO->>Database: SELECT DISTINCT POLICY_TERM_ID, POLICY_TERM_DESC FROM EWM_POLICY_TERM_MST WHERE isActive='Y'
        Database-->>ServiceDAO: ResultSet policy types
        ServiceDAO->>ServiceDAO: Build LinkedHashSet LabelValueBean list
        ServiceDAO-->>ServiceAction: serviceForm with policyTypeList
        
        ServiceAction->>ServiceDAO: getMechanicCode(dealerCode)
        ServiceDAO->>Database: Query mechanic details for dealer
        Database-->>ServiceDAO: Mechanic list
        ServiceDAO-->>ServiceAction: Mechanic list
        
        ServiceAction->>ServiceDAO: getVehicleDetails(vinNo, sf)
        ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
        HibernateUtil-->>ServiceDAO: Session hrbsession
        ServiceDAO->>Database: SELECT TOP(1) CustomerName, Tehsil, District, State, Pincode, MobilePh, LandlineNo, emailId, ModelFamilyDesc, ModelCodeDesc, EngineNo, vinNo, DeliveryDate FROM SW_vehicledetails WHERE vinNo=? AND DeliveryDate IS NOT NULL AND vin_details_type='DB' ORDER BY DeliveryDate DESC
        Database-->>ServiceDAO: Vehicle details ResultSet
        ServiceDAO->>ServiceDAO: Map results to serviceForm (customerName, location, district, state, pincode, mobile, contact, email, model, engine, chassis, deliveryDate)
        ServiceDAO-->>ServiceAction: serviceForm with vehicle details
        
        ServiceAction->>Browser: Set request attributes (flagCheck, policyTypeList, mechanicList, vinNo)
        ServiceAction->>StrutsFramework: Forward to initAddExtWarranty
        StrutsFramework->>Browser: Render addExtWarranty.jsp
        Browser-->>User: Display Add Extended Warranty form with pre-filled vehicle details
    else srch is null
        ServiceAction->>Browser: Set request attribute vinNo
        ServiceAction->>StrutsFramework: Forward to initAddExtWarranty
        StrutsFramework->>Browser: Render addExtWarranty.jsp
        Browser-->>User: Display Add Extended Warranty form (empty)
    end
```

---

## 2. Add Extended Warranty Flow

This diagram shows the complete process of saving a new Extended Warranty registration.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant PageTemplate as PageTemplate
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant EWMLoaderDetail as EWMLoaderDetail
    participant MSWDmechanicmaster as MSWDmechanicmaster
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Fill Extended Warranty form (customer details, vehicle info, policy type, premium amount)
    User->>Browser: Click Submit button
    Browser->>StrutsFramework: POST /serviceProcess.do?option=addExtWarranty
    StrutsFramework->>ServiceAction: addExtWarranty(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getValue("server_name"), session.getValue("ecatPATH"), session.getValue("mainURL")
    HTTPSession-->>ServiceAction: Server configuration details
    
    ServiceAction->>PageTemplate: new PageTemplate(server_name, ecatPath, mainURL)
    PageTemplate-->>ServiceAction: PageTemplate object with warranty booklet number, IMD code, float type, PP ID, type name, delivery days difference
    
    ServiceAction->>ServiceAction: Extract form data to serviceForm
    ServiceAction->>ServiceAction: Set extWarrantyBookletNo, imdCode, floatType, ppId, typeName from PageTemplate
    
    ServiceAction->>HTTPSession: session.getAttribute("user_id"), session.getAttribute("dealerCode")
    HTTPSession-->>ServiceAction: String userId, String dealerCode
    
    ServiceAction->>ServiceAction: Calculate days between delivery date and today
    ServiceAction->>ServiceAction: Get deliveryDaysDiff from PageTemplate
    
    alt Premium Amount is zero or null
        ServiceAction->>Browser: Set request attribute message (Premium Amount is Zero)
        ServiceAction->>Browser: Set request attribute vinNo
        ServiceAction->>StrutsFramework: Forward to premiumAmount
        StrutsFramework->>Browser: Render initAddExtWarranty.jsp with error
        Browser-->>User: Display error message
    else Premium Amount is valid
        alt Days between delivery date and today < deliveryDaysDiff
            ServiceAction->>ServiceDAO: saveExtWarranty(sf, userId)
            
            ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
            HibernateUtil-->>ServiceDAO: Session session
            
            ServiceDAO->>ServiceDAO: session.beginTransaction()
            ServiceDAO->>EWMLoaderDetail: new EWMLoaderDetail()
            ServiceDAO->>EWMLoaderDetail: Set all fields from serviceForm (ewLoaderId, customerTitle, customerName, address, city, state, pincode, mobile, telNo, email, extWarrantyBookletNo, dateOfSaleOfCertificate, typeName, makeName, modelName, fuelType, engineNo, chassisNo, vehicleRegNo, saleDate, deliveryDate, imdCode, dealerCode, sumInsured, floatType, ppId, hmr, policyTermId, policyTypeId, premiumAmount, status="Pending", createdBy, createdOn)
            
            alt Mechanic name is provided
                ServiceDAO->>Database: Load MSWDmechanicmaster WHERE mechanicId=?
                Database-->>ServiceDAO: MSWDmechanicmaster object
                ServiceDAO->>EWMLoaderDetail: Set mechanicDealerKey
            end
            
            ServiceDAO->>Database: session.save(ewm)
            Database-->>ServiceDAO: Save result
            ServiceDAO->>ServiceDAO: session.getTransaction().commit()
            ServiceDAO-->>ServiceAction: "success"
            
            alt Result is SUCCESS
                ServiceAction->>Browser: Set request attributes (result="SUCCESS", show_message=ewLoaderId, addMoreLink="YES", addMoreLinkURL)
                ServiceAction->>Browser: Add success message
                ServiceAction->>StrutsFramework: Forward to message
                StrutsFramework->>Browser: Render success message page
                Browser-->>User: Display "Extended Warranty Detail has been Successfully Added for Ref No: [EW_REF_NO]"
            else Result is FAILURE
                ServiceAction->>Browser: Set request attributes (result="FAILURE", show_message="", optionLink="YES", optionLinkURL)
                ServiceAction->>Browser: Add failure message
                ServiceAction->>StrutsFramework: Forward to message
                StrutsFramework->>Browser: Render error message page
                Browser-->>User: Display "Unable to Added Extended Warranty Details, Please Contact System Administrator"
            end
        else Days between delivery date and today >= deliveryDaysDiff
            ServiceAction->>Browser: Set error message (Delivery date exceeds allowed days)
            ServiceAction->>StrutsFramework: Forward to message
            Browser-->>User: Display error message
        end
    end
```

---

## 3. View Extended Warranty Flow

This diagram shows the process of viewing Extended Warranty registrations with search and filter capabilities.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant MethodUtility as MethodUtility
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click View Extended Warranty Tile or submit search criteria
    Browser->>StrutsFramework: GET /serviceProcess.do?option=viewExtendedWarranty (with optional parameters: chassisNo, fromdate, todate, range, status, dealerCode, etype)
    StrutsFramework->>ServiceAction: viewExtendedWarranty(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>ServiceAction: Vector userFunctionalities, String user_id
    
    ServiceAction->>ServiceAction: Extract search parameters (chassisNo, etype, flag, range, fromdate, todate, status)
    
    alt flag is null (initial load)
        ServiceAction->>ServiceAction: Set todate = today
        ServiceAction->>ServiceAction: Set fromdate = yesterday
        ServiceAction->>ServiceAction: Set range = "1"
        ServiceAction->>ServiceAction: Set flag = ""
    end
    
    ServiceAction->>ServiceAction: Set default values for range, fromdate, todate, status, chassisNo if null
    
    ServiceAction->>ServiceDAO: getExtWarrStatus()
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session hrbsession
    ServiceDAO->>Database: SELECT DISTINCT STATUS FROM EWM_LOADER_DETAIL
    Database-->>ServiceDAO: List of status values
    ServiceDAO-->>ServiceAction: List extWarrStatus
    
    ServiceAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query dealer details based on user permissions
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>ServiceAction: List dealerList
    
    alt User has functionality 101
        ServiceAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>ServiceAction: String dealerCode
        ServiceAction->>ServiceAction: Set dealercode = dealerCode from session
    else User has other functionalities
        ServiceAction->>Browser: Set request attribute dealerList
        ServiceAction->>ServiceAction: Set dealercode = "ALL"
    end
    
    ServiceAction->>ServiceAction: Set dealercode = "ALL" if null
    
    ServiceAction->>Browser: Set request attributes (fromdate, todate, range, extWarrStatus)
    
    alt etype equals Export
        ServiceAction->>ServiceDAO: getExpWarrDetailsExport(sf, user_id, userFunctionalities)
        ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
        HibernateUtil-->>ServiceDAO: Session hsession
        ServiceDAO->>Database: EXEC PROC_GetExtWarrantyView :chassisNo, :dealerCode, :userId, :fromDate, :toDate, :status, :ewRefNo (with all columns for export)
        Database-->>ServiceDAO: Extended warranty details ResultSet
        ServiceDAO->>ServiceDAO: Map all fields to serviceForm objects
        ServiceDAO-->>ServiceAction: ArrayList viewExpWarrList (with all details)
        ServiceAction->>Browser: Set request attribute expWarrListExport
        ServiceAction->>StrutsFramework: Forward to exportExtWarranty
        StrutsFramework->>Browser: Render exportExtWarranty.jsp
        Browser-->>User: Display export page with full details
    else etype is null or not Export
        ServiceAction->>ServiceDAO: getViewExpWarrDetails(sf, user_id, userFunctionalities)
        ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
        HibernateUtil-->>ServiceDAO: Session hsession
        ServiceDAO->>Database: EXEC PROC_GetExtWarrantyView :chassisNo, :dealerCode, :userId, :fromDate, :toDate, :status, :ewRefNo (with summary columns)
        Database-->>ServiceDAO: Extended warranty summary ResultSet
        ServiceDAO->>ServiceDAO: Map summary fields to serviceForm objects
        ServiceDAO-->>ServiceAction: ArrayList viewExpWarrList (summary view)
        ServiceAction->>Browser: Set request attribute viewExpWarrList
        ServiceAction->>StrutsFramework: Forward to viewExtWarranty
        StrutsFramework->>Browser: Render viewExtentedWarranty.jsp
        Browser-->>User: Display Extended Warranty list with search filters
    end
```

---

## 4. Edit Extended Warranty Flow

This diagram shows the process of loading Extended Warranty details for editing.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Edit link on Extended Warranty record
    Browser->>StrutsFramework: GET /serviceProcess.do?option=editExtWarranty&ewRefNo=EW123&chassisNo=CHASSIS123&dealerCode=DLR001
    StrutsFramework->>ServiceAction: editExtWarranty(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ServiceAction: String user_id
    
    ServiceAction->>ServiceAction: Extract ewRefNo, dealerCode, chassisNo from request parameters
    
    ServiceAction->>ServiceDAO: getPolicyType(sf)
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session hrbsession
    ServiceDAO->>Database: SELECT DISTINCT POLICY_TERM_ID, POLICY_TERM_DESC FROM EWM_POLICY_TERM_MST WHERE isActive='Y'
    Database-->>ServiceDAO: ResultSet policy types
    ServiceDAO->>ServiceDAO: Build LinkedHashSet LabelValueBean list
    ServiceDAO-->>ServiceAction: serviceForm with policyTypeList
    
    ServiceAction->>ServiceDAO: getMechanicCode(userCode)
    ServiceDAO->>Database: Query mechanic details
    Database-->>ServiceDAO: Mechanic list
    ServiceDAO-->>ServiceAction: Mechanic list
    
    ServiceAction->>ServiceDAO: getExtWarrPopupView(sf, ewRefNo, chassisNo, user_id)
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session hsession
    ServiceDAO->>Database: EXEC PROC_GetExtWarrantyView :chassisNo, :dealerCode, :userId, :fromDate, :toDate, :status, :ewRefNo (with all detail columns)
    Database-->>ServiceDAO: Extended warranty detail ResultSet
    ServiceDAO->>ServiceDAO: Map all fields to serviceForm (dateOfSaleOfCertificate, chassisNo, engineNumber, fuelType, makeName, modelCodeDesc, vehicleRegNo, saleDate, deliveryDate, hmrNo, title, customerName, address, city, state, pincode, mobileNo, contactno, emailId, sumInsured, policyType, creditAmount, ewLoaderId, dealerCode, dealerName, bajajPolicyNo, status, policyTermId, policyTypeId, bajajPolicyDate, mechanicName, mechanicDealerKey, createdOn)
    ServiceDAO-->>ServiceAction: serviceForm with complete details
    
    ServiceAction->>Browser: Set request attributes (policyTypeList, mechanicList)
    ServiceAction->>StrutsFramework: Forward to initEditExtWarranty
    StrutsFramework->>Browser: Render updateExtWarranty.jsp
    Browser-->>User: Display Edit Extended Warranty form with pre-filled data
```

---

## 5. Update Extended Warranty Flow

This diagram shows the process of updating an existing Extended Warranty registration.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant EWMLoaderDetail as EWMLoaderDetail
    participant MSWDmechanicmaster as MSWDmechanicmaster
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Modify Extended Warranty details in edit form
    User->>Browser: Click Update button
    Browser->>StrutsFramework: POST /serviceProcess.do?option=updateExtWarranty
    StrutsFramework->>ServiceAction: updateExtWarranty(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ServiceAction: String user_id
    
    ServiceAction->>ServiceAction: Extract form data to serviceForm
    
    ServiceAction->>ServiceDAO: updateExtWarranty(sf, user_id)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session hsession
    
    ServiceDAO->>ServiceDAO: hsession.beginTransaction()
    ServiceDAO->>Database: Load EWMLoaderDetail WHERE ewLoaderId=?
    Database-->>ServiceDAO: EWMLoaderDetail ewm object
    
    ServiceDAO->>EWMLoaderDetail: Update all fields from serviceForm (customerTitle, customerName, address, city, state, pincode, mobile, telNo, email, makeName, modelName, fuelType, vehicleRegNo, sumInsured, hmr, policyTermId, premiumAmount, status="Registered", modifiedBy, modifiedOn, bajajPolicyNo, bajajPolicyDate)
    
    alt Mechanic name is provided
        ServiceDAO->>Database: Load MSWDmechanicmaster WHERE mechanicId=?
        Database-->>ServiceDAO: MSWDmechanicmaster object
        ServiceDAO->>EWMLoaderDetail: Set mechanicDealerKey
    end
    
    ServiceDAO->>Database: hsession.update(ewm)
    Database-->>ServiceDAO: Update result
    ServiceDAO->>ServiceDAO: hsession.getTransaction().commit()
    ServiceDAO-->>ServiceAction: "success"
    
    alt Result is success
        ServiceAction->>Browser: Set request attributes (message="Extended Warranty Detail has been Successfully updated for Ref No. ", result="success", refNo=ewLoaderId)
        ServiceAction->>StrutsFramework: Forward to successExtWarr
        StrutsFramework->>Browser: Render success page
        Browser-->>User: Display "Extended Warranty Detail has been Successfully updated for Ref No: [EW_REF_NO]"
    else Result is failure
        ServiceAction->>Browser: Set request attributes (message="Unable to updated Extended Warranty Details, Please Contact System Administrator.", result="failure", refNo=ewLoaderId)
        ServiceAction->>StrutsFramework: Forward to successExtWarr
        StrutsFramework->>Browser: Render error page
        Browser-->>User: Display error message
    end
```

---

## 6. Get Premium Amount Flow (AJAX)

This diagram shows the AJAX flow for calculating premium amount based on policy type and delivery date.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Select Policy Type or change Delivery Date in form
    Browser->>Browser: Trigger AJAX call
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getPremiumAmt&policyType=1&delDate=01-Jan-2024&dealerCode=DLR001
    StrutsFramework->>ServiceAction: getPremiumAmt(serviceForm, request, response)
    
    ServiceAction->>ServiceAction: Extract policyType, delDate, dealerCode from request parameters
    
    alt dealerCode from session is available
        ServiceAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>ServiceAction: String dealerCode
    else dealerCode from request parameter
        ServiceAction->>ServiceAction: Use dealerCode from request parameter
    end
    
    ServiceAction->>ServiceDAO: getPremiumAmt(policyType, delDate, dealerCode)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: EXEC PROC_PolicyTermsCalculate :policyType, :delDate, :dealerCode
    Database-->>ServiceDAO: ResultSet (premiumAmount, policyId)
    ServiceDAO->>ServiceDAO: Extract premiumAmount and policyId
    ServiceDAO-->>ServiceAction: String result = "premiumAmount@@policyId"
    
    ServiceAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result and update Premium Amount field
    Browser-->>User: Display updated Premium Amount in form
```

---

## 7. Check Vehicle Existence Flow (AJAX)

This diagram shows the AJAX flow for checking if a vehicle exists in the system.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant PageTemplate as PageTemplate
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Enter VIN/Chassis Number in form
    Browser->>Browser: Trigger AJAX call on blur/change
    Browser->>StrutsFramework: GET /serviceProcess.do?option=isVehicleExist&vinNo=CHASSIS123
    StrutsFramework->>ServiceAction: isVehicleExist(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getValue("server_name"), session.getValue("ecatPATH"), session.getValue("mainURL")
    HTTPSession-->>ServiceAction: Server configuration details
    
    ServiceAction->>PageTemplate: new PageTemplate(server_name, ecatPath, mainURL)
    PageTemplate-->>ServiceAction: PageTemplate object with deliveryDaysDiff
    
    ServiceAction->>ServiceAction: Extract vinNo from request parameter
    ServiceAction->>ServiceAction: Get deliveryDaysDiff from PageTemplate
    
    ServiceAction->>ServiceDAO: isVehicleExist(vinNo, delDaysCount)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: SELECT vinNo FROM Vehicledetails WHERE vinNo=? AND vin_details_type='DB'
    Database-->>ServiceDAO: ResultSet vehicle check
    
    alt Vehicle exists
        ServiceDAO->>ServiceDAO: Set vinFlag = "true"
    else Vehicle does not exist
        ServiceDAO->>ServiceDAO: Set vinFlag = "false"
    end
    
    ServiceDAO->>Database: SELECT vinNo FROM Vehicledetails WHERE (DATEDIFF(dd, DeliveryDate, getdate()) + 1) <= ? AND vinNo=? AND vin_details_type='DB'
    Database-->>ServiceDAO: ResultSet delivery date check
    
    alt Delivery date within allowed days
        ServiceDAO->>ServiceDAO: Append "@@true" to vinFlag
    else Delivery date exceeds allowed days
        ServiceDAO->>ServiceDAO: Append "@@false" to vinFlag
    end
    
    ServiceDAO-->>ServiceAction: String vinFlag (e.g., "true@@true" or "false@@false")
    
    ServiceAction->>Browser: Write vinFlag to response PrintWriter
    Browser->>Browser: Parse result and validate form
    alt Vehicle exists and delivery date valid
        Browser-->>User: Enable form submission or show success message
    else Vehicle does not exist or delivery date invalid
        Browser-->>User: Show error message or disable form submission
    end
```

---

## 8. Check Chassis Existence Flow (AJAX)

This diagram shows the AJAX flow for checking if a chassis number already exists in Extended Warranty records.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"

    User->>Browser: Enter Chassis Number in form
    Browser->>Browser: Trigger AJAX call on blur/change
    Browser->>StrutsFramework: GET /serviceProcess.do?option=isChassisExist&vinNo=CHASSIS123
    StrutsFramework->>ServiceAction: isChassisExist(serviceForm, request, response)
    
    ServiceAction->>ServiceAction: Extract vinNo from request parameter
    
    ServiceAction->>ServiceDAO: isChassisAlreadyExist(vinNo)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: SELECT chassisNo FROM ITLEWMLoaderDetail WHERE chassisNo=?
    Database-->>ServiceDAO: ResultSet chassis check
    
    alt Chassis exists
        ServiceDAO->>ServiceDAO: Set vinFlag = "true"
    else Chassis does not exist
        ServiceDAO->>ServiceDAO: Set vinFlag = "false"
    end
    
    ServiceDAO-->>ServiceAction: String vinFlag ("true" or "false")
    
    ServiceAction->>Browser: Write vinFlag to response PrintWriter
    Browser->>Browser: Parse result
    alt Chassis already exists
        Browser-->>User: Show warning message "Chassis number already registered"
    else Chassis does not exist
        Browser-->>User: Allow form submission
    end
```

---

## 9. Get Extended Warranty Popup View Flow

This diagram shows the flow for displaying Extended Warranty details in a popup view.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click View Details link on Extended Warranty record
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getExtWarrPopupView&ewRefNo=EW123&chassisNo=CHASSIS123
    StrutsFramework->>ServiceAction: getExtWarrPopupView(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ServiceAction: String user_id
    
    ServiceAction->>ServiceAction: Extract ewRefNo, chassisNo from request parameters
    
    ServiceAction->>ServiceDAO: getExtWarrPopupView(sf, ewRefNo, chassisNo, user_id)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session hsession
    
    ServiceDAO->>Database: EXEC PROC_GetExtWarrantyView :chassisNo, :dealerCode, :userId, :fromDate, :toDate, :status, :ewRefNo (with all detail columns)
    Database-->>ServiceDAO: Extended warranty detail ResultSet
    
    ServiceDAO->>ServiceDAO: Map all fields to serviceForm (dateOfSaleOfCertificate, chassisNo, engineNumber, fuelType, makeName, modelCodeDesc, vehicleRegNo, saleDate, deliveryDate, hmrNo, title, customerName, address, city, state, pincode, mobileNo, contactno, emailId, sumInsured, policyType, creditAmount, ewLoaderId, dealerCode, dealerName, bajajPolicyNo, status, policyTermId, policyTypeId, bajajPolicyDate, mechanicName, mechanicDealerKey, createdOn)
    ServiceDAO-->>ServiceAction: serviceForm with complete details
    
    ServiceAction->>Browser: Set serviceForm in request
    ServiceAction->>StrutsFramework: Forward to extWarrPopup
    StrutsFramework->>Browser: Render popup JSP page
    Browser-->>User: Display Extended Warranty details in popup/modal window
```

---

## 10. Export Extended Warranty Flow

This diagram shows the flow for exporting Extended Warranty data to Excel format.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant JasperReports as JasperReports
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Export button on View Extended Warranty page
    Browser->>StrutsFramework: GET /serviceProcess.do?option=viewExtendedWarranty&etype=Export&chassisNo=&fromdate=01/01/2024&todate=31/12/2024&status=All&dealerCode=ALL
    StrutsFramework->>ServiceAction: viewExtendedWarranty(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>ServiceAction: Vector userFunctionalities, String user_id
    
    ServiceAction->>ServiceAction: Extract search parameters and set etype="Export"
    
    ServiceAction->>ServiceDAO: getExpWarrDetailsExport(sf, user_id, userFunctionalities)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session hsession
    
    ServiceDAO->>Database: EXEC PROC_GetExtWarrantyView :chassisNo, :dealerCode, :userId, :fromDate, :toDate, :status, :ewRefNo (with all export columns: EW_REF_NO, DATE_OF_SALE_OF_CERTIFICATE, CHASSIS_NO, MODEL_NAME, CUSTOMER_NAME, POLICY_TERM_DESC, DEALER_CODE, DealerName, POLICY_TYPE, ENGINE_NO, FUEL_TYPE, MAKE_NAME, VEHICLE_REG_NO, SALE_DATE, DELIVERY_DATE, HMR, CUSTOMER_TITLE, ADDRESS, CITY, STATE, PINCODE, MOBILE, TEL_NO, EMAIL, SUM_INSURED, PREMIUM_AMOUNT, BAJAJ_POLICY_NO, STATUS, EXT_WARRANTY_BOOKLET_NO, TYPE_NAME, IMD_CODE, FLOAT_TYPE, PPID, AMOUNT_TO_BAJAJ, BAJAJ_POLICY_DATE, MechanicName, MechanicDealerKey)
    Database-->>ServiceDAO: Extended warranty details ResultSet with all columns
    
    ServiceDAO->>ServiceDAO: Map all fields to serviceForm objects (complete details)
    ServiceDAO-->>ServiceAction: ArrayList viewExpWarrList (with all export fields)
    
    ServiceAction->>Browser: Set request attribute expWarrListExport
    ServiceAction->>StrutsFramework: Forward to exportExtWarranty
    StrutsFramework->>Browser: Render exportExtWarranty.jsp
    
    Browser->>JasperReports: Generate Excel report using JasperReports
    JasperReports->>Browser: Excel file stream
    Browser-->>User: Download Extended Warranty Excel file with all details
```

---

## Summary

The Extended Warranty Registration Module handles:

1. **Registration Management**: Adding new Extended Warranty registrations with vehicle and customer details
2. **View and Search**: Viewing Extended Warranty records with search filters (chassis number, date range, status, dealer)
3. **Edit and Update**: Modifying existing Extended Warranty registrations
4. **Validation**: AJAX-based validation for vehicle existence, chassis uniqueness, and premium calculation
5. **Export**: Exporting Extended Warranty data to Excel format with complete details
6. **Policy Management**: Integration with policy types and premium calculation based on delivery date
7. **Mechanic Assignment**: Associating mechanics with Extended Warranty registrations

All flows integrate with the SQL Server database through Hibernate ORM and use stored procedures for complex queries. The module supports both regular Extended Warranty and ITL Extended Warranty registrations.

