# ITLDIS Workflow Diagrams

## 1. User Authentication & Login Workflow

```mermaid
flowchart TD
    Start([User Accesses Application]) --> LoginPage[Display Login Page]
    LoginPage --> EnterCredentials[User Enters Credentials]
    EnterCredentials --> ValidateInput{Validate Input}
    ValidateInput -->|Invalid| ShowError[Display Error Message]
    ShowError --> LoginPage
    ValidateInput -->|Valid| EncryptPassword[Encrypt Password]
    EncryptPassword --> CheckUser[LoginDAO.checkUser]
    CheckUser --> UserExists{User Exists?}
    UserExists -->|No| InvalidUser[Show Invalid User Error]
    InvalidUser --> LoginPage
    UserExists -->|Yes| CheckActive{User Active?}
    CheckActive -->|No| InactiveUser[Show Inactive User Error]
    InactiveUser --> LoginPage
    CheckActive -->|Yes| SetSession[Create Session]
    SetSession --> LoadUserData[Load User Permissions & Data]
    LoadUserData --> CheckUserType{User Type?}
    CheckUserType -->|ECAT User| RedirectECAT[Redirect to E-Catalog]
    CheckUserType -->|Regular User| RedirectHome[Redirect to Home Page]
    RedirectECAT --> End([User Logged In])
    RedirectHome --> End
```

## 2. Service Management Workflow

```mermaid
flowchart TD
    Start([Service Request Initiated]) --> SelectService[Select Service Type]
    SelectService --> CreateJobCard[Create Job Card]
    CreateJobCard --> EnterCustomerInfo[Enter Customer Information]
    EnterCustomerInfo --> EnterVehicleInfo[Enter Vehicle Information]
    EnterVehicleInfo --> EnterComplaint[Enter Complaint Details]
    EnterComplaint --> CreateEstimate[Create Estimate]
    CreateEstimate --> AddParts[Add Parts/Components]
    AddParts --> CalculatePrice[Calculate Price via Price Master]
    CalculatePrice --> CustomerApproval{Customer Approves?}
    CustomerApproval -->|No| ModifyEstimate[Modify Estimate]
    ModifyEstimate --> CreateEstimate
    CustomerApproval -->|Yes| SaveJobCard[Save Job Card]
    SaveJobCard --> PerformService[Perform Service]
    PerformService --> UpdateStatus[Update Service Status]
    UpdateStatus --> GenerateInvoice[Generate Invoice]
    GenerateInvoice --> UpdateInventory[Update Inventory]
    UpdateInventory --> End([Service Completed])
```

## 3. Inventory Management Workflow

```mermaid
flowchart TD
    Start([Inventory Operation]) --> SelectOperation{Select Operation}
    SelectOperation -->|Stock Entry| StockEntry[Stock Entry Process]
    SelectOperation -->|Stock Issue| StockIssue[Stock Issue Process]
    SelectOperation -->|Stock Transfer| StockTransfer[Stock Transfer Process]
    SelectOperation -->|GRN| GRNProcess[GRN Process]
    SelectOperation -->|Export| ExportProcess[Export Inventory Process]
    
    StockEntry --> ValidateEntry[Validate Entry Data]
    ValidateEntry --> CheckStock[Check Current Stock]
    CheckStock --> UpdateStock[Update Stock Levels]
    UpdateStock --> UpdateLedger[Update Inventory Ledger]
    UpdateLedger --> End1([Stock Entry Complete])
    
    StockIssue --> ValidateIssue[Validate Issue Request]
    ValidateIssue --> CheckAvailability{Stock Available?}
    CheckAvailability -->|No| ShowError[Show Insufficient Stock]
    ShowError --> StockIssue
    CheckAvailability -->|Yes| DeductStock[Deduct from Stock]
    DeductStock --> UpdateLedger
    UpdateLedger --> End2([Stock Issue Complete])
    
    StockTransfer --> SelectSource[Select Source Branch]
    SelectSource --> SelectDestination[Select Destination Branch]
    SelectDestination --> TransferItems[Transfer Items]
    TransferItems --> UpdateBothBranches[Update Both Branch Stocks]
    UpdateBothBranches --> End3([Transfer Complete])
    
    GRNProcess --> ReceiveGoods[Receive Goods]
    ReceiveGoods --> VerifyPO{Verify Purchase Order}
    VerifyPO -->|Invalid| RejectGRN[Reject GRN]
    VerifyPO -->|Valid| CreateGRN[Create GRN]
    CreateGRN --> UpdateStock
    UpdateStock --> End4([GRN Complete])
    
    ExportProcess --> SelectItems[Select Items for Export]
    SelectItems --> CreateExportOrder[Create Export Order]
    CreateExportOrder --> GenerateDocuments[Generate Export Documents]
    GenerateDocuments --> UpdateExportStock[Update Export Stock]
    UpdateExportStock --> End5([Export Complete])
```

## 4. Installation Management Workflow

```mermaid
flowchart TD
    Start([New Installation Request]) --> CreateInstallation[Create Installation Record]
    CreateInstallation --> EnterChassisInfo[Enter Chassis Information]
    EnterChassisInfo --> ValidateChassis{Validate Chassis}
    ValidateChassis -->|Invalid| ShowError[Show Error]
    ShowError --> EnterChassisInfo
    ValidateChassis -->|Valid| EnterCustomerInfo[Enter Customer Information]
    EnterCustomerInfo --> SelectModel[Select Machine Model]
    SelectModel --> EnterInstallationDetails[Enter Installation Details]
    EnterInstallationDetails --> ScheduleInstallation[Schedule Installation]
    ScheduleInstallation --> AssignTechnician[Assign Technician]
    AssignTechnician --> PerformPDI[Perform Pre-Delivery Inspection]
    PerformPDI --> PDIResult{PDI Passed?}
    PDIResult -->|No| RectifyIssues[Rectify Issues]
    RectifyIssues --> PerformPDI
    PDIResult -->|Yes| PerformInstallation[Perform Installation]
    PerformInstallation --> UpdateCheckpoints[Update Installation Checkpoints]
    UpdateCheckpoints --> CompleteInstallation[Mark Installation Complete]
    CompleteInstallation --> GenerateDocuments[Generate Installation Documents]
    GenerateDocuments --> UpdateWarranty[Update Warranty Records]
    UpdateWarranty --> End([Installation Complete])
```

## 5. Warranty Claim Workflow

```mermaid
flowchart TD
    Start([Warranty Claim Request]) --> CreateClaim[Create Warranty Claim]
    CreateClaim --> EnterClaimDetails[Enter Claim Details]
    EnterClaimDetails --> AttachDocuments[Attach Supporting Documents]
    AttachDocuments --> ValidateClaim{Validate Claim}
    ValidateClaim -->|Invalid| ShowError[Show Validation Error]
    ShowError --> EnterClaimDetails
    ValidateClaim -->|Valid| SubmitClaim[Submit Claim]
    SubmitClaim --> AssignApprover[Assign to Approver]
    AssignApprover --> ReviewClaim[Review Claim]
    ReviewClaim --> ApprovalDecision{Approval Decision}
    ApprovalDecision -->|Reject| RejectClaim[Reject Claim]
    RejectClaim --> NotifyCustomer[Notify Customer]
    NotifyCustomer --> End1([Claim Rejected])
    ApprovalDecision -->|Approve| ApproveClaim[Approve Claim]
    ApproveClaim --> CheckParts{Parts Required?}
    CheckParts -->|Yes| ReserveParts[Reserve Parts from Inventory]
    CheckParts -->|No| PrepareDispatch
    ReserveParts --> CheckAvailability{Parts Available?}
    CheckAvailability -->|No| BackOrder[Create Back Order]
    BackOrder --> WaitForParts[Wait for Parts]
    WaitForParts --> ReserveParts
    CheckAvailability -->|Yes| PrepareDispatch[Prepare Dispatch]
    PrepareDispatch --> CreatePackingList[Create Packing List]
    CreatePackingList --> GenerateConsignment[Generate Consignment Note]
    GenerateConsignment --> DispatchGoods[Dispatch Goods]
    DispatchGoods --> UpdateSAP[Update SAP System]
    UpdateSAP --> GenerateCreditNote[Generate Credit Note]
    GenerateCreditNote --> End2([Warranty Claim Processed])
```

## 6. Purchase Order Workflow

```mermaid
flowchart TD
    Start([Purchase Order Request]) --> CreatePO[Create Purchase Order]
    CreatePO --> SelectItems[Select Items/Spare Parts]
    SelectItems --> CheckPriceList[Check Price List]
    CheckPriceList --> CalculateTotal[Calculate Total Amount]
    CalculateTotal --> AddTaxes[Add Taxes & Charges]
    AddTaxes --> SubmitPO[Submit PO for Approval]
    SubmitPO --> ApprovalWorkflow{Approval Required?}
    ApprovalWorkflow -->|Yes| AssignApprover[Assign Approver]
    AssignApprover --> ReviewPO[Review Purchase Order]
    ReviewPO --> ApproveDecision{Approve?}
    ApproveDecision -->|Reject| RejectPO[Reject PO]
    RejectPO --> NotifyRequester[Notify Requester]
    NotifyRequester --> End1([PO Rejected])
    ApproveDecision -->|Approve| ApprovePO[Approve PO]
    ApprovalWorkflow -->|No| ApprovePO
    ApprovePO --> SendToVendor[Send PO to Vendor]
    SendToVendor --> WaitForDelivery[Wait for Delivery]
    WaitForDelivery --> ReceiveGoods[Receive Goods]
    ReceiveGoods --> CreateGRN[Create GRN]
    CreateGRN --> VerifyItems{Items Match PO?}
    VerifyItems -->|No| RejectGRN[Reject GRN]
    RejectGRN --> NotifyVendor[Notify Vendor]
    NotifyVendor --> WaitForDelivery
    VerifyItems -->|Yes| AcceptGRN[Accept GRN]
    AcceptGRN --> UpdateInventory[Update Inventory]
    UpdateInventory --> GenerateInvoice[Generate Invoice]
    GenerateInvoice --> ProcessPayment[Process Payment]
    ProcessPayment --> End2([PO Complete])
```

## 7. Report Generation Workflow

```mermaid
flowchart TD
    Start([Report Request]) --> SelectReportType[Select Report Type]
    SelectReportType --> MISReport{MIS Report?}
    SelectReportType --> GSTRReport{GSTR Report?}
    SelectReportType --> InvoiceReport{Invoice Report?}
    SelectReportType --> CustomReport{Custom Report?}
    
    MISReport -->|Yes| EnterMISParams[Enter MIS Parameters]
    EnterMISParams --> CallStoredProc[Call Stored Procedure]
    CallStoredProc --> SP_DMIS_JobTypes[SP_DMIS_JobTypes]
    SP_DMIS_JobTypes --> SP_DMIS_Installations[SP_DMIS_Installations]
    SP_DMIS_Installations --> GenerateMIS[Generate MIS Report]
    
    GSTRReport -->|Yes| SelectGSTRType[Select GSTR-1 or GSTR-2]
    SelectGSTRType --> EnterGSTRParams[Enter GST Parameters]
    EnterGSTRParams --> FetchGSTData[Fetch GST Data]
    FetchGSTData --> GenerateGSTR[Generate GSTR Report]
    
    InvoiceReport -->|Yes| SelectInvoiceType[Select Invoice Type]
    SelectInvoiceType --> EnterInvoiceParams[Enter Invoice Parameters]
    EnterInvoiceParams --> FetchInvoiceData[Fetch Invoice Data]
    FetchInvoiceData --> GenerateInvoice[Generate Invoice Report]
    
    CustomReport -->|Yes| SelectCustomReport[Select Custom Report]
    SelectCustomReport --> EnterCustomParams[Enter Custom Parameters]
    EnterCustomParams --> ExecuteQuery[Execute Custom Query]
    ExecuteQuery --> GenerateCustom[Generate Custom Report]
    
    GenerateMIS --> FormatReport[Format Report]
    GenerateGSTR --> FormatReport
    GenerateInvoice --> FormatReport
    GenerateCustom --> FormatReport
    
    FormatReport --> ExportFormat{Export Format?}
    ExportFormat -->|PDF| GeneratePDF[Generate PDF]
    ExportFormat -->|Excel| GenerateExcel[Generate Excel]
    ExportFormat -->|Print| PrintReport[Print Report]
    
    GeneratePDF --> Download[Download Report]
    GenerateExcel --> Download
    PrintReport --> End([Report Generated])
    Download --> End
```

## 8. SAP Integration Workflow

```mermaid
flowchart TD
    Start([SAP Integration Triggered]) --> SelectIntegrationType{Integration Type}
    SelectIntegrationType -->|APN Sync| APNSync[Alternate Part Number Sync]
    SelectIntegrationType -->|TCP Sync| TCPSync[Travel Card Process Sync]
    SelectIntegrationType -->|Warranty Update| WarrantyUpdate[Warranty Data Update]
    
    APNSync --> CreateDestination[Create SAP Destination]
    CreateDestination --> ConnectSAP[Connect to SAP]
    ConnectSAP --> CallRFC[Call RFC Function Module]
    CallRFC --> FetchAPNData[Fetch APN Data]
    FetchAPNData --> TransformData[Transform Data]
    TransformData --> UpdateLocalDB[Update Local Database]
    UpdateLocalDB --> LogSync[Log Sync Status]
    
    TCPSync --> CreateDestination
    CreateDestination --> ConnectSAP
    ConnectSAP --> CallTCPRFC[Call TCP RFC]
    CallTCPRFC --> FetchTCPData[Fetch TCP Data]
    FetchTCPData --> ProcessTCP[Process TCP Data]
    ProcessTCP --> UpdateChassisData[Update Chassis Data]
    UpdateChassisData --> LogSync
    
    WarrantyUpdate --> PrepareWarrantyData[Prepare Warranty Data]
    PrepareWarrantyData --> CreateDestination
    CreateDestination --> ConnectSAP
    ConnectSAP --> SendWarrantyData[Send Warranty Data to SAP]
    SendWarrantyData --> ReceiveConfirmation[Receive Confirmation]
    ReceiveConfirmation --> UpdateStatus[Update Warranty Status]
    UpdateStatus --> LogSync
    
    LogSync --> End([Integration Complete])
```

## 9. User Management Workflow

```mermaid
flowchart TD
    Start([User Management Request]) --> SelectOperation{Select Operation}
    SelectOperation -->|Create User| CreateUser[Create New User]
    SelectOperation -->|Update User| UpdateUser[Update Existing User]
    SelectOperation -->|Deactivate User| DeactivateUser[Deactivate User]
    SelectOperation -->|Assign Roles| AssignRoles[Assign Roles]
    
    CreateUser --> EnterUserDetails[Enter User Details]
    EnterUserDetails --> ValidateDetails{Validate Details}
    ValidateDetails -->|Invalid| ShowError[Show Validation Error]
    ShowError --> EnterUserDetails
    ValidateDetails -->|Valid| EncryptPassword[Encrypt Password]
    EncryptPassword --> AssignDefaultRole[Assign Default Role]
    AssignDefaultRole --> SaveUser[Save User]
    SaveUser --> SendCredentials[Send Credentials via Email]
    SendCredentials --> End1([User Created])
    
    UpdateUser --> SelectUser[Select User]
    SelectUser --> ModifyDetails[Modify User Details]
    ModifyDetails --> ValidateDetails
    ValidateDetails -->|Invalid| ShowError
    ValidateDetails -->|Valid| UpdateUserRecord[Update User Record]
    UpdateUserRecord --> End2([User Updated])
    
    DeactivateUser --> SelectUser
    SelectUser --> ConfirmDeactivation{Confirm Deactivation}
    ConfirmDeactivation -->|Cancel| Cancel[Cancel Operation]
    ConfirmDeactivation -->|Confirm| MarkInactive[Mark User Inactive]
    MarkInactive --> RevokeAccess[Revoke Access]
    RevokeAccess --> End3([User Deactivated])
    
    AssignRoles --> SelectUser
    SelectUser --> SelectRole[Select Role]
    SelectRole --> AssignPermissions[Assign Permissions]
    AssignPermissions --> SaveRoleAssignment[Save Role Assignment]
    SaveRoleAssignment --> End4([Roles Assigned])
```

## 10. EAMG Module Workflow

```mermaid
flowchart TD
    Start([EAMG Operation]) --> SelectEAMGModule{Select EAMG Module}
    SelectEAMGModule -->|Group| GroupManagement[Group Management]
    SelectEAMGModule -->|Part| PartManagement[Part Management]
    SelectEAMGModule -->|Kit| KitManagement[Kit Management]
    SelectEAMGModule -->|Model| ModelManagement[Model Management]
    SelectEAMGModule -->|Tool| ToolManagement[Tool Management]
    
    GroupManagement --> CreateGroup[Create/Modify Group]
    CreateGroup --> DefineBOM[Define BOM Structure]
    DefineBOM --> AssignSequence[Assign F-Code Sequence]
    AssignSequence --> SaveGroup[Save Group]
    SaveGroup --> End1([Group Created])
    
    PartManagement --> CreatePart[Create/Modify Part]
    CreatePart --> DefinePartDetails[Define Part Details]
    DefinePartDetails --> LinkAlternateParts[Link Alternate Parts]
    LinkAlternateParts --> SetPrice[Set Price]
    SetPrice --> SavePart[Save Part]
    SavePart --> End2([Part Created])
    
    KitManagement --> CreateKit[Create/Modify Kit]
    CreateKit --> AddComponents[Add Components to Kit]
    AddComponents --> DefineKitStructure[Define Kit Structure]
    DefineKitStructure --> SaveKit[Save Kit]
    SaveKit --> End3([Kit Created])
    
    ModelManagement --> CreateModel[Create/Modify Model]
    CreateModel --> AddVariants[Add Variants]
    AddVariants --> AttachGroups[Attach Groups]
    AttachGroups --> AssignComponents[Assign Components]
    AssignComponents --> CompleteModel[Complete Model]
    CompleteModel --> End4([Model Created])
    
    ToolManagement --> CreateTool[Create/Modify Tool]
    CreateTool --> DefineToolSpecs[Define Tool Specifications]
    DefineToolSpecs --> LinkToParts[Link to Parts]
    LinkToParts --> SaveTool[Save Tool]
    SaveTool --> End5([Tool Created])
```

