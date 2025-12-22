# ITLDIS Warranty Module - UML Class Diagram

```mermaid
classDiagram
    %% ============================================
    %% CONTROLLER LAYER (9 Controllers)
    %% ============================================
    
    class WarrantyWcrController {
        +pcrWarrantyClaim()
        +goodwillWarrantyClaim()
        +saveWcr()
        +wcrSearch()
        +viewWcr()
        +wcrApproval()
        +wcrExcelSummary()
        +wcrExcelDetails()
        +printWcr()
    }
    
    class WarrantyPcrController {
        +dropDownFieldCondition()
        +dropDownCropCondition()
        +dropDownImplCategory()
        +savePcr()
        +pcrSearch()
        +viewPcr()
        +pcrApproval()
        +pcrExcelSummary()
        +pcrExcelDetails()
        +printPcr()
    }
    
    class WarrantyLogsheetController {
        +saveWarrantyLogsheet()
        +dropDownLogsheetType()
        +logsheetSearch()
        +viewLogsheet()
        +logsheetExcelDetails()
        +printLogsheet()
    }
    
    class WarrantyRetrofitmentCampaignController {
        +uploadExcel()
        +saveRetrofitmentCampaign()
        +searchRetrofitmentCampaign()
        +viewRetrofitmentCampaign()
        +printRetrofitmentCampaign()
    }
    
    class WarrantyGoodWillController {
        +saveGoodwill()
        +dropDownPriceType()
        +goodwillSearch()
        +viewGoodwill()
        +goodwillApproval()
        +printGoodwill()
    }
    
    class WarrantyDeliveryChallanController {
        +getClaimPartInDc()
        +saveDeliveryChallan()
        +deliveryChallanSearch()
        +viewDeliveryChallan()
        +printDeliveryChallan()
    }
    
    class WarrantyHotlineReportController {
        +submitHotlineReport()
        +viewHotlineReport()
        +hotlineReportSearch()
        +printHotlineReport()
    }
    
    class KaiInspectionSheetController {
        +WcrDcForKaiInspectionSheet()
        +saveKaiInspectionSheet()
        +kaiInspectionSheetSearch()
        +viewKaiInspectionSheet()
        +printKaiInspectionSheet()
    }
    
    class WarrantyReportController {
        +printPCR()
        +printGoodwill()
        +printWCR()
        +printLogsheet()
        +printDeliveryChallan()
    }
    
    %% ============================================
    %% SERVICE LAYER (6 Services)
    %% ============================================
    
    class WarrantyPcrService {
        +savePcr()
        +searchPcr()
        +viewPcr()
        +approvePcr()
    }
    
    class WarrantyPcrImpl {
        +savePcr()
        +searchPcr()
        +viewPcr()
        +approvePcr()
    }
    
    class GoodwillService {
        +saveGoodwill()
        +searchGoodwill()
        +viewGoodwill()
        +approveGoodwill()
    }
    
    class GoodwillServiceImplement {
        +saveGoodwill()
        +searchGoodwill()
        +viewGoodwill()
        +approveGoodwill()
    }
    
    class WarrantyLogsheetService {
        +saveWarrantyLogsheet()
        +searchLogsheet()
        +viewLogsheet()
    }
    
    class WarrantyLogsheetImpl {
        +saveWarrantyLogsheet()
        +searchLogsheet()
        +viewLogsheet()
    }
    
    class WarrantyRetrofitmentCampaignService {
        +saveRetrofitmentCampaign()
        +searchRetrofitmentCampaign()
        +viewRetrofitmentCampaign()
        +saveExcelChassisDetail()
    }
    
    class WarrantyRetrofitmentCampaignImpl {
        +saveRetrofitmentCampaign()
        +searchRetrofitmentCampaign()
        +viewRetrofitmentCampaign()
        +saveExcelChassisDetail()
    }
    
    class WarrantyHotlineReportService {
        +submitHotlineReport()
        +findHotlineByHtlrNo()
        +searchHotlineReport()
    }
    
    class WarrantyHotlineReportServiceImpl {
        +submitHotlineReport()
        +findHotlineByHtlrNo()
        +searchHotlineReport()
    }
    
    class KaiInspectionSheetService {
        +saveKaiInspectionSheet()
        +searchKaiInspectionSheet()
        +viewKaiInspectionSheet()
    }
    
    class KaiInspectionSheetImpl {
        +saveKaiInspectionSheet()
        +searchKaiInspectionSheet()
        +viewKaiInspectionSheet()
    }
    
    %% ============================================
    %% REPOSITORY LAYER
    %% ============================================
    
    class WarrantyWcrRepo {
        +findByWcrNo()
        +searchWcr()
        +getJobCardPartWarrantyInfo()
        +getLabourCharge()
        +getOutSideLabourCharge()
    }
    
    class WarrantyWcrApprovalRepo {
        +findByWcrId()
        +save()
    }
    
    class WarrantyPcrRepo {
        +findByPcrNo()
        +searchPcr()
        +dropDownFieldCondition()
        +dropDownCropCondition()
        +getDealerDetails()
    }
    
    class WarrantyPcrPhotosRepo {
        +findByWarrantyPcrId()
        +save()
    }
    
    class WarrantyPcrApprovalRepository {
        +findByPcrId()
        +save()
    }
    
    class WarrantyGoodwillRepo {
        +findByGoodwillNo()
        +searchGoodwill()
        +save()
    }
    
    class WarrantyGoodwillApprovalRepo {
        +findByGoodwillId()
        +save()
    }
    
    class WarrantyLogsheetRepo {
        +findByLogsheetNo()
        +searchLogsheet()
        +save()
    }
    
    class WarrantyLogsheetPhotoRepo {
        +findByLogsheetId()
        +save()
    }
    
    class WarrantyRetrofitmentCampaignRepo {
        +searchWarrantyRetrofitmentCampaign()
        +findByRetrofitmentNo()
        +save()
    }
    
    class WarrantyRetrofitmentChassisInfoRepo {
        +findByChassisNo()
        +save()
    }
    
    class WarrantyRetrofitmentCampaignPhotoRepo {
        +findByCampaignId()
        +save()
    }
    
    class WarrantyDeliveryChallanRepo {
        +findByDcNo()
        +deliveryChallanSearch()
        +getDeliveryChallanCount()
        +getClaimPartInDc()
        +save()
    }
    
    class WarrantyHotlineReportRepo {
        +findByHtlrNo()
        +searchHotlineReport()
        +save()
    }
    
    class WarrantyHotlineReportAttachmentRepo {
        +findByHotlineReportId()
        +save()
    }
    
    class KaiInspectionSheetRepo {
        +findByInspectionNo()
        +searchKaiInspectionSheet()
        +getWcrDcDetails()
        +getJobCardPartWarrantyInfo()
        +getLabourChargeInfo()
        +getOutsideChargeInfo()
        +save()
    }
    
    class KaiInspectionSheetPhotoRepo {
        +findByInspectionSheetId()
        +save()
    }
    
    %% ============================================
    %% DOMAIN ENTITIES (Main Entities)
    %% ============================================
    
    class WarrantyWcr {
        -Long id
        -String wcrNo
        -Date wcrDate
        -String wcrStatus
        -String wcrType
        -Boolean draftFlag
        -String kaiRemarks
        -String inspectionRemarks
        -Long createdBy
        -Date createdOn
        -Long modifiedBy
        -Date modifiedOn
        -Long branchId
        -Date inspectionDate
    }
    
    class WarrantyWcrApproval {
        -Long id
        -String approvalStatus
        -String remarks
        -Long approvedBy
        -Date approvedOn
        -Integer approvalLevel
    }
    
    class WarrantyPcr {
        -Long id
        -String pcrNo
        -Date pcrDate
        -String status
        -String crop
        -String cropCondition
        -String failureType
        -String soilType
        -String delayReason
        -String fieldCondition
        -Boolean repeatFailureFlag
        -Integer failureCount
        -String dealerObservation
        -String actionTaken
        -String dealerRemarks
        -String kaiRemarks
        -String specialApvRemarks
        -Boolean draftFlag
        -String soldToDealer
        -String serviceDealer
        -Boolean allowVideoUpload
        -String serviceDealerAddress
        -Boolean goodwillFlag
    }
    
    class WarrantyPcrImplements {
        -Long id
        -String implementCode
        -String implementDesc
    }
    
    class WarrantyPcrPhotos {
        -Long id
        -String photoPath
        -String photoType
    }
    
    class WarrantyPcrApproval {
        -Long id
        -String approvalStatus
        -String remarks
        -Long approvedBy
        -Date approvedOn
        -Integer approvalLevel
    }
    
    class WarrantyGoodwill {
        -Long id
        -String goodwillNo
        -Date goodwillDate
        -Boolean draftFlag
        -String status
        -String goodwillType
        -String goodwillReason
        -Double budgetConsumed
        -String dealerRemark
        -String kaiRemark
    }
    
    class WarrantyGoodwillApproval {
        -Long id
        -String approvalStatus
        -String remarks
        -Long approvedBy
        -Date approvedOn
    }
    
    class WarrantyGoodwillPhoto {
        -Long id
        -String photoPath
        -String photoType
    }
    
    class WarrantyLogsheet {
        -Long id
        -String logsheetNo
        -Date logsheetDate
        -Boolean draftFlag
        -String status
        -String logsheetType
        -Date dateOfFailure
        -Double hours
        -String crop
        -String cropCondition
        -String failureType
        -String soilType
        -String fieldCondition
        -String probableCause
        -String resultOfConfirmation
        -Boolean repeatFailure
        -Integer noOfTimes
        -String tentativeAction
        -String remarks
    }
    
    class LogsheetImplement {
        -Long id
        -String implementCode
        -String implementDesc
    }
    
    class LogsheetFailurePartInfo {
        -Long id
        -String partNumber
        -String partDescription
        -Integer quantity
    }
    
    class WarrantyLogsheetPhotos {
        -Long id
        -String photoPath
        -String photoType
    }
    
    class WarrantyRetrofitmentCampaign {
        -Long id
        -String retrofitmentNo
        -Date retrofitmentDate
        -String status
        -String campaignName
        -Date startDate
        -Date endDate
        -String dataFilePath
    }
    
    class WarrantyRetrofitmentCampaignItem {
        -Long id
        -String partNumber
        -String partDescription
        -Integer quantity
    }
    
    class WarrantyRetrofitmentChassisInfo {
        -Long id
        -String chassisNo
        -String vinNo
    }
    
    class WarrantyRetrofitmentJcLabourChargeInfo {
        -Long id
        -String workCode
        -String workDescription
        -Double amount
    }
    
    class WarrantyRetrofitmentCampaignPhoto {
        -Long id
        -String photoPath
        -String photoType
    }
    
    class WarrantyDeliveryChallan {
        -Long id
        -String dcNo
        -Date dcDate
        -String status
        -Boolean draftFlag
        -String transporterName
        -String docketNumber
        -Integer numberOfBox
        -Date dispatchDate
    }
    
    class WarrantyHotlineReport {
        -Long id
        -String htlrNo
        -Date htlrDate
        -Date failureDate
        -String status
        -String vendorResponse
        -String complaint
        -String remarks
    }
    
    class WarrantyHotlineReportPartsDetail {
        -Long id
        -String partNumber
        -String partDescription
        -Integer quantity
    }
    
    class WarrantyHotlineReportMachineDetails {
        -Long id
        -String machineModel
        -String chassisNo
        -String engineNo
    }
    
    class WarrantyHotlineReportAttachment {
        -Long id
        -String attachmentPath
        -String attachmentType
    }
    
    class WarrantyHotlineReportPlantMaster {
        -Long id
        -String plantCode
        -String plantName
    }
    
    class WarrantyHotlineReportFailureMaster {
        -Long id
        -String failureCode
        -String failureDescription
    }
    
    class WarrantyHotlineConditionFailureMaster {
        -Long id
        -String conditionCode
        -String conditionDescription
    }
    
    class KaiInspectionSheet {
        -Long id
        -String inspectionNo
        -Date inspectionDate
        -String symptom
        -String defect
        -String remedy
        -String claimFinalRemark
        -Long inspectionBy
    }
    
    class KaiInspectionSheetPhoto {
        -Long id
        -String photoPath
        -String photoType
    }
    
    %% ============================================
    %% DTO LAYER (Grouped by Sub-module)
    %% ============================================
    
    class WcrWarrantyDto {
        +PcrWarrantyClaimDto pcrWarrantyClaimDto
        +List warrantyPart
        +List labourCharge
        +List outSideLabourCharge
    }
    
    class WarrantyWcrView {
        +Long id
        +String wcrNo
        +Date wcrDate
        +String wcrStatus
        +String pcrNo
        +String goodwillNo
    }
    
    class WarrantyWcrSearch {
        +String wcrNo
        +Date fromDate
        +Date toDate
        +String status
        +String machineModel
    }
    
    class WarrantyWcrResponse {
        +List content
        +Long totalElements
        +Integer totalPages
    }
    
    class WcrApprovalDto {
        +Long wcrId
        +String approvalStatus
        +String remarks
        +Integer approvalLevel
    }
    
    class WarrantyPcrViewDto {
        +Long id
        +String pcrNo
        +Date pcrDate
        +String status
        +String crop
        +String failureType
    }
    
    class WarrantyPcrSearchDto {
        +String pcrNo
        +Date fromDate
        +Date toDate
        +String status
        +String machineModel
    }
    
    class WarrantyPcrResponseDto {
        +List content
        +Long totalElements
        +Integer totalPages
    }
    
    class PcrApprovalDto {
        +Long pcrId
        +String approvalStatus
        +String remarks
        +Integer approvalLevel
    }
    
    class GoodwillViewDto {
        +Long id
        +String goodwillNo
        +Date goodwillDate
        +String status
        +String goodwillType
        +String goodwillReason
    }
    
    class GoodwillSearch {
        +String goodwillNo
        +Date fromDate
        +Date toDate
        +String status
    }
    
    class GoodwillSearchResponse {
        +List content
        +Long totalElements
    }
    
    class GoodwillApprovalDto {
        +Long goodwillId
        +String approvalStatus
        +String remarks
    }
    
    class WarrantyLogsheetViewDto {
        +Long id
        +String logsheetNo
        +Date logsheetDate
        +String status
        +String logsheetType
    }
    
    class LogsheetSearchDto {
        +String logsheetNo
        +Date fromDate
        +Date toDate
        +String status
    }
    
    class LogsheetResponseDto {
        +List content
        +Long totalElements
    }
    
    class WarrantyRetrofitmentCampaignResponseDto {
        +Long id
        +String retrofitmentNo
        +Date retrofitmentDate
        +String status
        +String campaignName
    }
    
    class WarrantyRetrofitmentCampaignSearchDto {
        +String rfcNo
        +String status
        +Date fromDate
        +Date toDate
    }
    
    class WarrantyDcResponseDto {
        +Long id
        +String dcNo
        +Date dcDate
        +String status
    }
    
    class WarrantyDcSearchDcSearchDto {
        +String dcNo
        +String wcrNo
        +Date dcFromDate
        +Date dcToDate
    }
    
    class WarrantyHotlineSearchResponceDto {
        +List content
        +Long totalElements
    }
    
    class WarrantyHotlineReportSearchDto {
        +String htlrNo
        +Date fromDate
        +Date toDate
        +String status
    }
    
    class KaiInspectionSheetResponseDto {
        +List content
        +Long totalElements
    }
    
    class KaiInspectionSheetSearchDto {
        +String inspectionNo
        +String wcrNo
        +Date fromDate
        +Date toDate
    }
    
    %% ============================================
    %% EXTERNAL DEPENDENCIES
    %% ============================================
    
    class ServiceJobCard {
        -Long id
        -String jobCardNo
        -Date jobCardDate
    }
    
    class SparePartRequisitionItem {
        -Long id
        -String partNumber
        -Double claimApprovedAmount
        -Integer claimApprovedQuantity
    }
    
    class MachineVinMaster {
        -Long id
        -String vinNo
        -String chassisNo
    }
    
    class CustomerMaster {
        -Long id
        -String customerName
        -String mobileNumber
    }
    
    class DealerMaster {
        -Long id
        -String dealerCode
        -String dealerName
    }
    
    class KubotaEmployeeMaster {
        -Long id
        -String employeeName
        -String designation
    }
    
    class StorageService {
        +uploadFile()
        +downloadFile()
        +deleteFile()
    }
    
    class UserAuthentication {
        +getBranchId()
        +getLoginId()
        +getDealerId()
        +getKubotaEmployeeId()
    }
    
    class ApiResponse {
        +Object result
        +String message
        +Integer status
    }
    
    class JasperPrintService {
        +generateReport()
        +printReport()
    }
    
    %% ============================================
    %% RELATIONSHIPS - Controllers to Services
    %% ============================================
    
    WarrantyWcrController --> WarrantyWcrRepo : uses
    WarrantyWcrController --> WarrantyWcrApprovalRepo : uses
    WarrantyWcrController --> WarrantyPcrRepo : uses
    WarrantyWcrController --> WarrantyGoodwillRepo : uses
    WarrantyWcrController --> StorageService : uses
    WarrantyWcrController --> UserAuthentication : uses
    WarrantyWcrController --> SparePartRequisitionItem : uses
    
    WarrantyPcrController --> WarrantyPcrService : uses
    WarrantyPcrController --> WarrantyPcrRepo : uses
    WarrantyPcrController --> WarrantyPcrPhotosRepo : uses
    WarrantyPcrController --> WarrantyPcrApprovalRepository : uses
    WarrantyPcrController --> StorageService : uses
    WarrantyPcrController --> UserAuthentication : uses
    
    WarrantyLogsheetController --> WarrantyLogsheetService : uses
    WarrantyLogsheetController --> WarrantyLogsheetRepo : uses
    WarrantyLogsheetController --> WarrantyLogsheetPhotoRepo : uses
    WarrantyLogsheetController --> UserAuthentication : uses
    
    WarrantyRetrofitmentCampaignController --> WarrantyRetrofitmentCampaignService : uses
    WarrantyRetrofitmentCampaignController --> WarrantyRetrofitmentCampaignRepo : uses
    WarrantyRetrofitmentCampaignController --> JasperPrintService : uses
    
    WarrantyGoodWillController --> GoodwillService : uses
    WarrantyGoodWillController --> WarrantyGoodwillRepo : uses
    WarrantyGoodWillController --> WarrantyGoodwillApprovalRepo : uses
    WarrantyGoodWillController --> StorageService : uses
    WarrantyGoodWillController --> UserAuthentication : uses
    
    WarrantyDeliveryChallanController --> WarrantyDeliveryChallanRepo : uses
    WarrantyDeliveryChallanController --> WarrantyWcrRepo : uses
    WarrantyDeliveryChallanController --> UserAuthentication : uses
    
    WarrantyHotlineReportController --> WarrantyHotlineReportService : uses
    WarrantyHotlineReportController --> WarrantyHotlineReportRepo : uses
    WarrantyHotlineReportController --> StorageService : uses
    
    KaiInspectionSheetController --> KaiInspectionSheetService : uses
    KaiInspectionSheetController --> KaiInspectionSheetRepo : uses
    KaiInspectionSheetController --> WarrantyWcrRepo : uses
    KaiInspectionSheetController --> StorageService : uses
    KaiInspectionSheetController --> UserAuthentication : uses
    
    WarrantyReportController --> WarrantyPcrRepo : uses
    WarrantyReportController --> JasperPrintService : uses
    
    %% ============================================
    %% RELATIONSHIPS - Services to Repositories
    %% ============================================
    
    WarrantyPcrImpl ..|> WarrantyPcrService : implements
    WarrantyPcrImpl --> WarrantyPcrRepo : uses
    WarrantyPcrImpl --> WarrantyPcrPhotosRepo : uses
    WarrantyPcrImpl --> StorageService : uses
    
    GoodwillServiceImplement ..|> GoodwillService : implements
    GoodwillServiceImplement --> WarrantyGoodwillRepo : uses
    GoodwillServiceImplement --> WarrantyGoodwillApprovalRepo : uses
    GoodwillServiceImplement --> StorageService : uses
    
    WarrantyLogsheetImpl ..|> WarrantyLogsheetService : implements
    WarrantyLogsheetImpl --> WarrantyLogsheetRepo : uses
    WarrantyLogsheetImpl --> WarrantyLogsheetPhotoRepo : uses
    WarrantyLogsheetImpl --> StorageService : uses
    
    WarrantyRetrofitmentCampaignImpl ..|> WarrantyRetrofitmentCampaignService : implements
    WarrantyRetrofitmentCampaignImpl --> WarrantyRetrofitmentCampaignRepo : uses
    WarrantyRetrofitmentCampaignImpl --> WarrantyRetrofitmentChassisInfoRepo : uses
    WarrantyRetrofitmentCampaignImpl --> StorageService : uses
    
    WarrantyHotlineReportServiceImpl ..|> WarrantyHotlineReportService : implements
    WarrantyHotlineReportServiceImpl --> WarrantyHotlineReportRepo : uses
    WarrantyHotlineReportServiceImpl --> WarrantyHotlineReportAttachmentRepo : uses
    WarrantyHotlineReportServiceImpl --> StorageService : uses
    
    KaiInspectionSheetImpl ..|> KaiInspectionSheetService : implements
    KaiInspectionSheetImpl --> KaiInspectionSheetRepo : uses
    KaiInspectionSheetImpl --> KaiInspectionSheetPhotoRepo : uses
    KaiInspectionSheetImpl --> StorageService : uses
    
    %% ============================================
    %% RELATIONSHIPS - Domain Entity Relationships
    %% ============================================
    
    WarrantyWcr --> WarrantyPcr : ManyToOne
    WarrantyWcr --> WarrantyGoodwill : ManyToOne
    WarrantyWcr --> WarrantyWcrApproval : OneToMany
    WarrantyWcr --> WarrantyDeliveryChallan : ManyToMany
    WarrantyWcr --> KubotaEmployeeMaster : ManyToOne inspectionBy
    
    WarrantyPcr --> ServiceJobCard : ManyToOne
    WarrantyPcr --> WarrantyPcrImplements : OneToMany
    WarrantyPcr --> WarrantyPcrPhotos : OneToMany
    WarrantyPcr --> WarrantyPcrApproval : OneToMany
    
    WarrantyGoodwill --> WarrantyPcr : ManyToOne
    WarrantyGoodwill --> WarrantyGoodwillApproval : OneToMany
    WarrantyGoodwill --> WarrantyGoodwillPhoto : OneToMany
    
    WarrantyLogsheet --> MachineVinMaster : ManyToOne
    WarrantyLogsheet --> CustomerMaster : ManyToOne
    WarrantyLogsheet --> ServiceJobCard : ManyToOne
    WarrantyLogsheet --> LogsheetImplement : OneToMany
    WarrantyLogsheet --> LogsheetFailurePartInfo : OneToMany
    WarrantyLogsheet --> WarrantyLogsheetPhotos : OneToMany
    
    WarrantyRetrofitmentCampaign --> WarrantyRetrofitmentCampaignItem : OneToMany
    WarrantyRetrofitmentCampaign --> WarrantyRetrofitmentChassisInfo : OneToMany
    WarrantyRetrofitmentCampaign --> WarrantyRetrofitmentJcLabourChargeInfo : OneToMany
    WarrantyRetrofitmentCampaign --> WarrantyRetrofitmentCampaignPhoto : OneToMany
    
    WarrantyHotlineReport --> WarrantyHotlineReportPlantMaster : ManyToOne
    WarrantyHotlineReport --> WarrantyHotlineReportFailureMaster : ManyToOne
    WarrantyHotlineReport --> WarrantyHotlineConditionFailureMaster : ManyToOne
    WarrantyHotlineReport --> WarrantyHotlineReportPartsDetail : OneToMany
    WarrantyHotlineReport --> WarrantyHotlineReportMachineDetails : OneToMany
    WarrantyHotlineReport --> WarrantyHotlineReportAttachment : OneToMany
    WarrantyHotlineReport --> KubotaEmployeeMaster : ManyToOne
    
    KaiInspectionSheet --> WarrantyWcr : ManyToOne
    KaiInspectionSheet --> KaiInspectionSheetPhoto : OneToMany
    
    WarrantyDeliveryChallan --> WarrantyWcr : ManyToMany
    
    %% ============================================
    %% REPOSITORY TO ENTITY RELATIONSHIPS
    %% ============================================
    
    WarrantyWcrRepo --> WarrantyWcr : manages
    WarrantyWcrApprovalRepo --> WarrantyWcrApproval : manages
    WarrantyPcrRepo --> WarrantyPcr : manages
    WarrantyPcrPhotosRepo --> WarrantyPcrPhotos : manages
    WarrantyPcrApprovalRepository --> WarrantyPcrApproval : manages
    WarrantyGoodwillRepo --> WarrantyGoodwill : manages
    WarrantyGoodwillApprovalRepo --> WarrantyGoodwillApproval : manages
    WarrantyLogsheetRepo --> WarrantyLogsheet : manages
    WarrantyLogsheetPhotoRepo --> WarrantyLogsheetPhotos : manages
    WarrantyRetrofitmentCampaignRepo --> WarrantyRetrofitmentCampaign : manages
    WarrantyRetrofitmentChassisInfoRepo --> WarrantyRetrofitmentChassisInfo : manages
    WarrantyRetrofitmentCampaignPhotoRepo --> WarrantyRetrofitmentCampaignPhoto : manages
    WarrantyDeliveryChallanRepo --> WarrantyDeliveryChallan : manages
    WarrantyHotlineReportRepo --> WarrantyHotlineReport : manages
    WarrantyHotlineReportAttachmentRepo --> WarrantyHotlineReportAttachment : manages
    KaiInspectionSheetRepo --> KaiInspectionSheet : manages
    KaiInspectionSheetPhotoRepo --> KaiInspectionSheetPhoto : manages
```

## Module Overview

The **Warranty Module** is a comprehensive module in the ITLDIS application that handles warranty claim processing, product concern reports, goodwill requests, logsheets, retrofitment campaigns, delivery challans, hotline reports, and KAI inspection sheets.

### Core Sub-Modules:

#### 1. **Warranty Claim Request (WCR)**
   - Create warranty claims from PCR or Goodwill requests
   - WCR approval workflow with multiple levels
   - WCR search and view functionality
   - Excel export (summary and details)
   - Print WCR reports

#### 2. **Product Concern Report (PCR)**
   - Create PCR from service job cards
   - Capture failure details (crop, crop condition, failure type, soil type, field condition)
   - PCR approval workflow
   - Implement tracking
   - Photo attachments
   - Excel export and print functionality

#### 3. **Goodwill Request**
   - Create goodwill requests linked to PCR
   - Goodwill approval workflow
   - Budget tracking
   - Photo attachments
   - Print goodwill reports

#### 4. **Warranty Logsheet**
   - Create logsheets for warranty failures
   - Track failure details (date, hours, crop, failure type, soil type)
   - Implement and part failure information
   - Photo attachments
   - Excel export and print functionality

#### 5. **Retrofitment Campaign**
   - Create retrofitment campaigns
   - Bulk chassis upload via Excel
   - Campaign item and labour charge tracking
   - Photo attachments
   - Print campaign reports

#### 6. **Delivery Challan**
   - Create delivery challans for warranty claims
   - Link multiple WCRs to a single delivery challan
   - Track transporter and dispatch details
   - Print delivery challan

#### 7. **Hotline Report**
   - Submit hotline reports for warranty issues
   - Track machine and part details
   - Vendor response tracking
   - Attachment management
   - Print hotline reports

#### 8. **KAI Inspection Sheet**
   - Create KAI inspection sheets for warranty claims
   - Link to WCR and delivery challan
   - Capture failure mode, unit, and type of use
   - Photo attachments
   - Print inspection sheets

#### 9. **Warranty Reports**
   - Print PCR reports
   - Print Goodwill reports
   - Print WCR reports
   - Print Logsheet reports
   - Print Delivery Challan reports

### Key Components:

- **9 Controllers**: REST controllers handling HTTP requests
- **6 Service Interfaces**: Business logic interfaces
- **6 Service Implementations**: Business logic implementations
- **20+ Repositories**: Data access layer using Spring Data JPA
- **30+ Domain Entities**: JPA entities representing database tables
- **25+ DTOs**: Data transfer objects for API communication

### Technology Stack:

- **Framework**: Spring Boot (REST API)
- **ORM**: JPA/Hibernate
- **Database**: SQL Server
- **File Storage**: StorageService (for photo/attachment management)
- **Reporting**: JasperReports
- **Security**: UserAuthentication service

### Key Relationships:

1. **WCR → PCR/Goodwill**: WCR can be created from either PCR or Goodwill request
2. **PCR → Service Job Card**: PCR is linked to a service job card
3. **Goodwill → PCR**: Goodwill request is linked to a PCR
4. **Logsheet → Service Job Card**: Logsheet is linked to a service job card
5. **Delivery Challan → WCR**: Many-to-many relationship (one DC can have multiple WCRs)
6. **KAI Inspection Sheet → WCR**: One-to-one relationship
7. **All entities support photo/attachment management**

### Data Flow:

1. **Request Flow**: HTTP Request → Controller → Service → Repository → Database
2. **Response Flow**: Database → Repository → Service → DTO → Controller → JSON Response
3. **File Upload Flow**: MultipartFile → Controller → StorageService → File Storage
4. **Approval Flow**: Entity → Approval Entity → Repository → Database
5. **Report Generation**: Entity Data → JasperReports → PDF Output

### Key Features:

- **Multi-level Approval Workflows**: WCR, PCR, and Goodwill support approval workflows
- **Photo/Attachment Management**: All major entities support photo attachments
- **Excel Import/Export**: Bulk operations and reporting via Excel
- **Comprehensive Search**: Advanced search capabilities across all sub-modules
- **Print Functionality**: PDF report generation for all major documents
- **Integration**: Seamless integration with Service Module, Spares Module, and Masters Module

