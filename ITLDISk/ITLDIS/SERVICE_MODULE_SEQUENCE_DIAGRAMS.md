# Service Module Sequence Diagrams

This document contains sequence diagrams for the ITLDIS Service Module, showing the interaction flow between different components.

## 1. View All Job Cards Flow

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as Struts Framework
    participant ServiceAction as serviceAction
    participant MethodUtility as MethodUtility
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as SQL Server DB

    User->>Browser: Click "View All Job Card" Tile
    Browser->>StrutsFramework: GET /serviceProcess.do?option=init_viewallJobcarddetails
    StrutsFramework->>ServiceAction: init_viewallJobcarddetails()
    
    ServiceAction->>ServiceAction: Extract request parameters<br/>(vinid, flag, range, status, dates)
    ServiceAction->>ServiceAction: Get user_id from session
    ServiceAction->>ServiceAction: Get userFunctionalities from session
    
    ServiceAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query UmUserDealerMatrix
    Database-->>MethodUtility: Return dealer list
    MethodUtility-->>ServiceAction: List<Dealer> dealerList
    
    alt User has functionality 101
        ServiceAction->>ServiceAction: Set dealerCode from session
    else User has other functionalities
        ServiceAction->>Browser: Set dealerList attribute
    end
    
    ServiceAction->>ServiceDAO: getJobCardDetails(sf, nameSrch, ColumnName, user_id, userFunctionalities)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>ServiceDAO: Build HQL query with filters<br/>(date range, status, search)
    ServiceDAO->>Database: Execute HQL Query<br/>SELECT from Jobcarddetails, Dealervslocationcode
    Database-->>ServiceDAO: ResultSet
    
    ServiceDAO->>ServiceDAO: Map results to serviceForm objects
    ServiceDAO->>HibernateUtil: session.close()
    ServiceDAO-->>ServiceAction: ArrayList<serviceForm> jobcardDetails
    
    ServiceAction->>Browser: Set request attributes<br/>(jobcardDetails, dates, status, etc.)
    ServiceAction->>StrutsFramework: Forward to "view_alljobcardDetails"
    StrutsFramework->>Browser: Render v_viewalljobcard.jsp
    Browser-->>User: Display Job Card List
```

## 2. Generate Invoice Flow

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as Struts Framework
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant MethodUtility as MethodUtility
    participant HibernateUtil as HibernateUtil
    participant Database as SQL Server DB
    participant SP as Stored Procedures

    User->>Browser: Click "Generate Invoice" on Job Card
    Browser->>StrutsFramework: POST /serviceProcess.do?option=generateInvoice
    StrutsFramework->>ServiceAction: generateInvoice()
    
    ServiceAction->>ServiceAction: Extract form data<br/>(jobCardNo, vinNo, parts, prices, etc.)
    ServiceAction->>ServiceAction: Get user_id, dealerCode from session
    
    ServiceAction->>ServiceDAO: generateInvoice(sf, user_id)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    ServiceDAO->>ServiceDAO: session.beginTransaction()
    
    ServiceDAO->>SP: SP_getVehicleCustomerID(vinNo, dealerCode)
    SP->>Database: Query Vehicledetails
    Database-->>SP: Customer ID
    SP-->>ServiceDAO: List customerId
    
    alt Customer ID not found
        ServiceDAO-->>ServiceAction: "FAILURECUST@@vehicleInfoFailure4locCode"
        ServiceAction->>Browser: Display error message
    else Customer ID found
        ServiceDAO->>MethodUtility: check_in_Db_HQL(jobCardNo, "SpInventSaleMaster")
        MethodUtility->>Database: Check if invoice exists
        Database-->>MethodUtility: exists/notexists
        MethodUtility-->>ServiceDAO: check result
        
        alt Invoice already exists
            ServiceDAO-->>ServiceAction: "EXIST@@Invoice EXIST"
            ServiceAction->>Browser: Display "Invoice already exists"
        else Invoice does not exist
            ServiceDAO->>MethodUtility: getNumber(session, "SpInventSaleMaster", dealerCode, "I")
            MethodUtility->>Database: Generate invoice number
            Database-->>MethodUtility: invoiceNo
            MethodUtility-->>ServiceDAO: invoiceNo
            
            ServiceDAO->>Database: Load Jobcarddetails by jobCardNo
            Database-->>ServiceDAO: Jobcarddetails object
            ServiceDAO->>ServiceDAO: Update Jobcarddetails status
            
            loop For each part in invoice
                ServiceDAO->>MethodUtility: checkInDb(partNo, "CatPart")
                MethodUtility->>Database: Verify part exists
                Database-->>MethodUtility: exists/notexists
                
                alt Part not found
                    ServiceDAO->>ServiceDAO: session.rollback()
                    ServiceDAO-->>ServiceAction: "HSNCODE@@partNo"
                else Part found
                    ServiceDAO->>Database: Insert SpInventSaleDetails
                    ServiceDAO->>MethodUtility: inventoryLedgerEntry()
                    MethodUtility->>Database: Update inventory ledger
                end
            end
            
            ServiceDAO->>Database: Insert SpInventSaleMaster
            ServiceDAO->>Database: Update SWVehicleServiceSchedule
            
            ServiceDAO->>ServiceDAO: session.commit()
            
            ServiceDAO->>SP: SP_updateTaxInvoice(invoiceNo)
            SP->>Database: Update tax invoice details
            SP-->>ServiceDAO: Success
            
            alt Warranty required and JobType=35
                ServiceDAO->>SP: SP_BajajExtWtyTaxInvoiceUpdate(invoiceNo)
                SP->>Database: Update extended warranty invoice
                SP-->>ServiceDAO: Success
            end
            
            ServiceDAO->>Database: Update Jobcarddetails status to "BILLED"
            ServiceDAO->>HibernateUtil: session.close()
            ServiceDAO-->>ServiceAction: "SUCCESS@@Invoice Generated@@invoiceNo"
        end
    end
    
    ServiceAction->>ServiceAction: Parse result string
    alt Success
        ServiceAction->>Browser: Set success message and invoice number
        ServiceAction->>Browser: Set print invoice link
    else Failure
        ServiceAction->>Browser: Set error message
    end
    
    ServiceAction->>StrutsFramework: Forward to "message" or "initCreateInvoiceList"
    StrutsFramework->>Browser: Render success/error page
    Browser-->>User: Display result
```

## 3. VIN Number Details Lookup (AJAX)

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as Struts Framework
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as SQL Server DB

    User->>Browser: Enter VIN number in form field
    Browser->>Browser: Trigger AJAX call on input change
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getvinNumberAjax&vinNo=XXX
    
    StrutsFramework->>ServiceAction: getvinNumberAjax()
    ServiceAction->>ServiceAction: Extract vinNo, jctype from request
    ServiceAction->>ServiceAction: Get user_id, dealerCode from session
    
    ServiceAction->>ServiceDAO: getVinNoList(vinNo, jctype, user_id, dealerCode)
    
    ServiceDAO->>Database: Get connection
    Database-->>ServiceDAO: Connection conn
    
    ServiceDAO->>Database: Query Vehicledetails<br/>WHERE vinNo LIKE '%XXX%'
    Database-->>ServiceDAO: ResultSet with matching VINs
    
    ServiceDAO->>ServiceDAO: Format results as JSON/CSV string
    ServiceDAO->>Database: Close connection
    ServiceDAO-->>ServiceAction: String result (JSON/CSV)
    
    ServiceAction->>Browser: Write result to PrintWriter
    ServiceAction->>Browser: Close PrintWriter
    Browser->>Browser: Parse JSON/CSV and populate dropdown
    Browser-->>User: Display matching VIN numbers
```

## 4. Get VIN Details Flow

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as Struts Framework
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as SQL Server DB

    User->>Browser: Select VIN from dropdown
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getVimNumberDetails&vinNo=XXX
    
    StrutsFramework->>ServiceAction: getVimNumberDetails()
    ServiceAction->>ServiceAction: Extract vinNo from request
    ServiceAction->>ServiceAction: Get dealerCode from session
    
    ServiceAction->>ServiceDAO: getVinDetails(vinNo, dealerCode)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: HQL Query<br/>FROM Vehicledetails<br/>WHERE vinNo=? AND dealerCode=?
    Database-->>ServiceDAO: Vehicledetails object
    
    ServiceDAO->>ServiceDAO: Extract vehicle details<br/>(EngineNo, DeliveryDate, RegNo, etc.)
    ServiceDAO->>ServiceDAO: Format as delimited string<br/>"EngineNo@@DeliveryDate@@RegNo@@..."
    
    ServiceDAO->>HibernateUtil: session.close()
    ServiceDAO-->>ServiceAction: String vindetails
    
    ServiceAction->>Browser: Write vindetails to PrintWriter
    ServiceAction->>Browser: Close PrintWriter
    Browser->>Browser: Parse delimited string
    Browser->>Browser: Populate form fields with vehicle data
    Browser-->>User: Display vehicle information
```

## 5. Service Reminder Flow

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as Struts Framework
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant MethodUtility as MethodUtility
    participant HibernateUtil as HibernateUtil
    participant Database as SQL Server DB

    User->>Browser: Click "Service Reminder" Tile
    Browser->>StrutsFramework: GET /serviceProcess.do?option=initsercviceReminder
    
    StrutsFramework->>ServiceAction: initsercviceReminder()
    ServiceAction->>ServiceAction: Extract date range parameters
    ServiceAction->>ServiceAction: Get user_id, dealerCode, userFunctionalities from session
    
    ServiceAction->>ServiceDAO: getCommonLabelValues("Jobtypemaster", ...)
    ServiceDAO->>Database: Query Jobtypemaster table
    Database-->>ServiceDAO: Job type list
    ServiceDAO-->>ServiceAction: LinkedHashSet<LabelValueBean> jobTypeList
    
    ServiceAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query UmUserDealerMatrix
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>ServiceAction: List<Dealer> dealerList
    
    ServiceAction->>ServiceDAO: getsercviceReminder(sf, userFunctionalities)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: Complex Query<br/>SWVehicleServiceSchedule<br/>JOIN Vehicledetails<br/>WHERE dueDate BETWEEN dates<br/>AND status='PENDING'
    Database-->>ServiceDAO: ResultSet with service reminders
    
    ServiceDAO->>ServiceDAO: Map to serviceForm objects
    ServiceDAO->>HibernateUtil: session.close()
    ServiceDAO-->>ServiceAction: ArrayList<serviceForm> sercviceReminderList
    
    ServiceAction->>Browser: Set request attributes<br/>(sercviceReminderList, jobTypeList, dates)
    
    alt Export requested
        ServiceAction->>StrutsFramework: Forward to "exportServiceReminder"
        StrutsFramework->>Browser: Render ExportServiceReminder.jsp
    else Normal view
        ServiceAction->>StrutsFramework: Forward to "initsercviceReminder"
        StrutsFramework->>Browser: Render sercviceReminder.jsp
    end
    
    Browser-->>User: Display Service Reminder List
```

## 6. Close Job Card Flow

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as Struts Framework
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as SQL Server DB
    participant SP as Stored Procedures

    User->>Browser: Click "Close Job Card" Tile
    Browser->>StrutsFramework: GET /serviceProcess.do?option=initCloseJobCards
    
    StrutsFramework->>ServiceAction: initCloseJobCards()
    ServiceAction->>ServiceAction: Get user_id, dealerCode, userFunctionalities from session
    ServiceAction->>ServiceAction: Set jobCardStatus = "open"
    
    ServiceAction->>ServiceDAO: getJobCardDetails(sf, "", "", user_id, userFunctionalities)
    ServiceDAO->>Database: Query Jobcarddetails WHERE status='open'
    Database-->>ServiceDAO: Open job cards list
    ServiceDAO-->>ServiceAction: ArrayList<serviceForm> jobcardDetails
    
    ServiceAction->>Browser: Set jobcardDetails and closeflag=true
    ServiceAction->>StrutsFramework: Forward to "view_alljobcardDetails"
    StrutsFramework->>Browser: Render v_viewalljobcard.jsp
    Browser-->>User: Display open job cards with close option
    
    User->>Browser: Click "Close" on a job card
    Browser->>StrutsFramework: POST /serviceProcess.do?option=setJobCardNoStatus
    
    StrutsFramework->>ServiceAction: setJobCardNoStatus()
    ServiceAction->>ServiceAction: Extract jobCardNo, status, remarks from form
    
    ServiceAction->>ServiceDAO: setJobCardNoStatus(jobCardNo, status, user_id, remarks)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    ServiceDAO->>ServiceDAO: session.beginTransaction()
    
    ServiceDAO->>Database: Load Jobcarddetails by jobCardNo
    Database-->>ServiceDAO: Jobcarddetails object
    
    ServiceDAO->>ServiceDAO: Update status and remarks
    ServiceDAO->>Database: Save Jobcarddetails
    
    ServiceDAO->>SP: SP_UpdateJobCardDailyConsumption(jobCardNo, userId)
    SP->>Database: Update daily consumption records
    SP-->>ServiceDAO: Success
    
    ServiceDAO->>ServiceDAO: session.commit()
    ServiceDAO->>HibernateUtil: session.close()
    ServiceDAO-->>ServiceAction: "success" or "FAILURE"
    
    ServiceAction->>ServiceAction: Create ActionMessages
    ServiceAction->>ServiceAction: initReOpenJobCards() (refresh list)
    ServiceAction->>StrutsFramework: Forward to "closeJC"
    StrutsFramework->>Browser: Render close job card page
    Browser-->>User: Display updated job card list
```

## 7. Part Number Lookup (AJAX)

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as Struts Framework
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant Database as SQL Server DB

    User->>Browser: Type part number in form field
    Browser->>Browser: Trigger AJAX call
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getPartNumberAjax&partno=XXX&comptype=YYY
    
    StrutsFramework->>ServiceAction: getPartNumberAjax()
    ServiceAction->>ServiceAction: Extract partno, comptype from request
    
    ServiceAction->>ServiceDAO: getComponentList(partno, comptype, "partNo", "Partmaster", "partType")
    
    ServiceDAO->>Database: Query Partmaster/CatPart<br/>WHERE partType LIKE '%comptype%'<br/>AND partNo LIKE '%partno%'
    Database-->>ServiceDAO: ResultSet with matching parts
    
    ServiceDAO->>ServiceDAO: Format as JSON/CSV string
    ServiceDAO-->>ServiceAction: String result
    
    ServiceAction->>Browser: Write result to PrintWriter
    Browser->>Browser: Parse and populate dropdown
    Browser-->>User: Display matching part numbers
```

## 8. Overall Service Module Architecture

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant Struts as Struts Framework
    participant Action as Action Classes
    participant DAO as DAO Classes
    participant Util as Utility Classes
    participant Hibernate as Hibernate ORM
    participant DB as SQL Server Database
    participant SP as Stored Procedures

    User->>Browser: Interact with Service Module Tiles
    Browser->>Struts: HTTP Request (GET/POST)
    
    Struts->>Action: Route to serviceAction method
    
    Action->>Action: Extract request parameters
    Action->>Action: Get session attributes<br/>(user_id, dealerCode, userFunctionalities)
    
    Action->>Util: MethodUtility.getDealersDetailsUnderUser()
    Util->>DB: Query UmUserDealerMatrix
    DB-->>Util: Dealer list
    Util-->>Action: List<Dealer>
    
    Action->>DAO: Call DAO method<br/>(getJobCardDetails, generateInvoice, etc.)
    
    DAO->>Hibernate: getSessionFactory().openSession()
    Hibernate-->>DAO: Session
    
    alt Direct HQL Query
        DAO->>DB: Execute HQL Query
        DB-->>DAO: ResultSet/Objects
    else Stored Procedure Call
        DAO->>SP: Execute stored procedure
        SP->>DB: Execute SQL
        DB-->>SP: ResultSet
        SP-->>DAO: Results
    end
    
    DAO->>DAO: Map results to Form Beans
    DAO->>Hibernate: session.close()
    DAO-->>Action: Return data (ArrayList, String, etc.)
    
    Action->>Action: Process business logic
    Action->>Browser: Set request attributes
    Action->>Struts: Forward to JSP view
    
    Struts->>Browser: Render JSP page
    Browser-->>User: Display results
```

## Component Interaction Summary

### Key Components:

1. **User Interface Layer**
   - Browser/Client
   - JSP Pages (v_viewalljobcard.jsp, sercviceReminder.jsp, etc.)

2. **Controller Layer**
   - Struts Framework (routing)
   - serviceAction (business logic coordination)

3. **Service Layer**
   - serviceDAO (data access)
   - MethodUtility (common utilities)

4. **Data Access Layer**
   - Hibernate ORM (object-relational mapping)
   - Direct SQL queries
   - Stored Procedures

5. **Database Layer**
   - SQL Server Database
   - Tables: Jobcarddetails, Vehicledetails, SpInventSaleMaster, etc.
   - Stored Procedures: SP_getVehicleCustomerID, SP_updateTaxInvoice, etc.

### Common Patterns:

1. **Session Management**: User context (user_id, dealerCode, userFunctionalities) stored in HTTP session
2. **Transaction Management**: Hibernate transactions for data consistency
3. **Error Handling**: Try-catch blocks with rollback on errors
4. **AJAX Calls**: Asynchronous requests for dynamic data loading
5. **Form Validation**: Request parameter validation before processing
6. **Authorization**: User functionality checks before allowing operations

