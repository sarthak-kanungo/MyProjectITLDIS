# ITLDIS Spares Module - UML Class Diagram

```mermaid
classDiagram
    %% ============================================
    %% CONTROLLER LAYER (25 Controllers)
    %% ============================================
    
    class SparesInvoiceController {
        +getReferenceDocument()
        +documentAutoComplete()
        +saveInvoice()
        +searchSpareInvoice()
        +getSpareInvoiceById()
        +cancelInvoice()
        +getSparesInvoiceNumberAutocomplete()
        +getJobCardDetails()
        +getWcrDetails()
        +downloadPartSalesReport()
    }
    
    class SpareSalesOrderController {
        +saveSpareSalesOrder()
        +getSalesOrderNumberAutocomplete()
        +getQuotationNumberAutocomplete()
        +getCustomerNameAutocomplete()
        +getItemDetailsByItemNumber()
        +getAvailableStockByItemNumber()
        +search()
        +getSalesOrderById()
        +getRetailerOrMechanicAutocomplete()
        +getRetailerOrMechanicDetails()
        +getDealerDetails()
        +getDealerCodeAutocomplete()
        +customerUploadExcel()
        +downloadCustomerOrderSearchExcel()
    }
    
    class SpareQuotationController {
        +saveQuotation()
        +getQuotationNumberAutocomplete()
        +getQuotationSearch()
        +getQuotationById()
        +getQuotationByIdForSalesOrder()
        +getCustomerNameAutocomplete()
        +uploadExcel()
    }
    
    class PickListController {
        +viewPicklist()
        +savePicklist()
        +getCustomerOrderNoAutocomplete()
        +getCustomerDetails()
        +getSalesOrderAutocomplete()
        +searchPickList()
    }
    
    class SparePartRequisitionController {
        +savePartRequisition()
        +getRequisitionPurpose()
        +searchPartRequisitionNo()
        +searchJobCardNo()
        +searchPartRequisition()
        +getPartRequisitionById()
    }
    
    class SparePartIssueController {
        +savePartIssue()
        +searchPartIssue()
        +getPartIssueById()
        +getAvailableStockForPartIssue()
    }
    
    class SparePartReturnController {
        +savePartReturn()
        +searchPartReturn()
        +getPartReturnById()
    }
    
    class SparePurchaseOrderController {
        +downloadTemplate()
        +getItemNumberAutoComplete()
        +savePurchaseOrder()
        +searchPurchaseOrder()
        +getPurchaseOrderById()
        +poApproval()
        +getOPSItemsDetail()
        +getDealerOutstanding()
        +downloadPurchaseOrderSearchExcel()
        +uploadExcel()
    }
    
    class SparePartGrnController {
        +saveGrn()
        +searchGrn()
        +getGrnById()
        +getPurchaseOrderDetails()
        +getPurchaseOrderAutocomplete()
    }
    
    class SPOrderPlanningSheetController {
        +saveOrderPlanningSheet()
        +searchOrderPlanningSheet()
        +getOrderPlanningSheetById()
        +getOrderPlanningSheetItems()
    }
    
    class SPBackOrderCancellationController {
        +saveBackOrderCancellation()
        +searchBackOrderCancellation()
        +getBackOrderCancellationById()
        +backOrderCancellationApproval()
    }
    
    class SpPartDiscrepancyClaimController {
        +saveDiscrepancyClaim()
        +searchDiscrepancyClaim()
        +getDiscrepancyClaimById()
        +discrepancyClaimApproval()
        +uploadAttachment()
    }
    
    class BlockPartsForSaleController {
        +blockPartsForSale()
        +unblockPartsForSale()
        +searchBlockedParts()
    }
    
    class BinningSlipController {
        +generateBinningSlip()
        +getBinningSlipDetails()
    }
    
    class TransitReportController {
        +getTransitReport()
        +downloadTransitReport()
    }
    
    class StockAdjustmentController {
        +saveStockAdjustment()
        +searchStockAdjustment()
        +getStockAdjustmentById()
        +stockAdjustmentApproval()
    }
    
    class SPBranchTransferIndentController {
        +saveBranchTransferIndent()
        +searchBranchTransferIndent()
        +getBranchTransferIndentById()
    }
    
    class SPBranchTransferIssueController {
        +saveBranchTransferIssue()
        +searchBranchTransferIssue()
        +getBranchTransferIssueById()
    }
    
    class SPBranchTransferReceiptController {
        +saveBranchTransferReceipt()
        +searchBranchTransferReceipt()
        +getBranchTransferReceiptById()
    }
    
    class BinToBinTransferController {
        +saveBinToBinTransfer()
        +searchBinToBinTransfer()
        +getBinToBinTransferById()
    }
    
    class CurrentStockController {
        +getCurrentStock()
        +searchCurrentStock()
        +downloadCurrentStockReport()
    }
    
    class MachineStockController {
        +getMachineStock()
        +searchMachineStock()
    }
    
    class NonMovInventoryController {
        +getNonMovingInventory()
        +searchNonMovingInventory()
        +downloadNonMovingInventoryReport()
    }
    
    class SpareReportsController {
        +getBackOrderPartsReport()
        +getClosingStockReport()
        +getInventoryMovementReport()
        +getItemMovementReport()
        +getNonMovingPartsStockReport()
    }
    
    class ServiceReportsController {
        +getServiceReports()
    }
    
    %% ============================================
    %% SERVICE LAYER (10 Services)
    %% ============================================
    
    class SpareSalesOrderService {
        +saveSpareSaleOrder()
        +customerUploadExcel()
    }
    
    class SpareSalesOrdeImpl {
        +saveSpareSaleOrder()
        +customerUploadExcel()
    }
    
    class SpareQuotationService {
        +saveSpareQuotation()
        +uploadExcel()
    }
    
    class SpareQuotationImpl {
        +saveSpareQuotation()
        +uploadExcel()
    }
    
    class SparesPurchaseOrderService {
        +savePurchaseOrder()
        +uploadExcel()
    }
    
    class SparePurchaseOrderImpl {
        +savePurchaseOrder()
        +uploadExcel()
    }
    
    class SPOrderPlanningSheetService {
        +saveOrderPlanningSheet()
        +getOrderPlanningSheetItems()
    }
    
    class SPOrderPlanningSheetServiceImpl {
        +saveOrderPlanningSheet()
        +getOrderPlanningSheetItems()
    }
    
    class SPBackOrderCancellationService {
        +saveBackOrderCancellation()
        +backOrderCancellationApproval()
    }
    
    class SPBackOrderCancellationServiceImpl {
        +saveBackOrderCancellation()
        +backOrderCancellationApproval()
    }
    
    class SpPartDiscrepancyClaimService {
        +saveDiscrepancyClaim()
        +discrepancyClaimApproval()
    }
    
    class SpPartDiscrepancyClaimServiceImpl {
        +saveDiscrepancyClaim()
        +discrepancyClaimApproval()
    }
    
    class SPBranchTransferIndentService {
        +saveBranchTransferIndent()
    }
    
    class SPBranchTransferIndentServiceImpl {
        +saveBranchTransferIndent()
    }
    
    class SPBranchTransferIssueService {
        +saveBranchTransferIssue()
    }
    
    class SPBranchTransferIssueServiceImpl {
        +saveBranchTransferIssue()
    }
    
    class SPBranchTransferReceiptService {
        +saveBranchTransferReceipt()
    }
    
    class SPBranchTransferReceiptServiceImpl {
        +saveBranchTransferReceipt()
    }
    
    class NonMovInventoryService {
        +getNonMovingInventory()
        +searchNonMovingInventory()
    }
    
    class NonMovInventoryServiceImpl {
        +getNonMovingInventory()
        +searchNonMovingInventory()
    }
    
    %% ============================================
    %% REPOSITORY LAYER (30+ Repositories)
    %% ============================================
    
    class SparesInvoiceRepo {
        +getSaleOrderInfo()
        +getSaleOrderAutoComplete()
        +save()
        +searchSaleInvoice()
        +getSparesInvoiceViewHeaderData()
        +getSparesInvoiceViewPartDetails()
        +getSparesInvoiceViewLabourDetails()
        +getSparesInvoiceViewOutsideChargeDetails()
        +cancelInvoice()
        +getSpareInvoiceNumberAutocomplete()
        +getSpareInvoiceJobCardDetails()
        +getSpareInvoiceWcrDetails()
    }
    
    class SpareSalesOrderRepository {
        +save()
        +getSpareSalesOrderAutocomplete()
        +getQuotationNumberAutocomplete()
        +getSpareSalesOrderCustomerNameAutocomplete()
        +getItemDetailsByItemNumber()
        +spareSalesOrderSearch()
        +getSpareSalesOrderHeaderDetails()
        +getSpareSalesOrderPartDetails()
        +getRetailerAndMechanicAutocomplete()
        +getRetailerAndMechanicDetails()
        +getDealerDetails()
        +getDealerCodeAutocomplete()
    }
    
    class SpareQuotationRepository {
        +save()
        +getQuotationNumberAutocomplete()
        +getQuotationSearch()
        +getQuotationViewHeaderDetails()
        +getQuotationViewPartDetails()
        +getQuotationViewPartDetailsForSalesOrder()
        +getQuotationCustomerNameAutocomplete()
    }
    
    class PickListRepository {
        +save()
        +getPickHeaderDetails()
        +getPickLineItemDetails()
        +updateStockDetails()
        +updateSaleOrderStatus()
        +getPickListCustomerOrderNoAuto()
        +getSalesOrderCustomerDetails()
        +getSalesOrderItemDetails()
        +getSalesOrderAutocomplete()
        +searchPickList()
    }
    
    class PicklistReturnRepository {
        +save()
        +findByPicklistDtlId()
    }
    
    class SparePartRequisitionRepo {
        +save()
        +getRequisitionPurpose()
        +searchPartRequisitionNo()
        +searchJobCardNo()
        +searchPartRequisition()
        +getPartRequisitionById()
        +getCategoryIdByName()
    }
    
    class SparePartRequisitionItemRepo {
        +save()
        +findBySparePartRequisitionId()
    }
    
    class SparePartIssueRepository {
        +save()
        +searchPartIssue()
        +getPartIssueById()
        +getAvailableStockForPartIssue()
    }
    
    class SparePartReturnRepository {
        +save()
        +searchPartReturn()
        +getPartReturnById()
    }
    
    class SparePurchaseOrderRepository {
        +save()
        +getItemNumberAutoComplete()
        +searchPurchaseOrder()
        +getPurchaseOrderById()
        +getPurchaseOrderViewHeaderDetails()
        +getPurchaseOrderViewPartDetails()
        +getOPSItemsDetail()
        +getDealerOutstanding()
    }
    
    class SparePurchaseOrderApprovalRepository {
        +save()
        +findByPurchaseOrderId()
    }
    
    class SparePartGrnRepository {
        +save()
        +searchGrn()
        +getGrnById()
        +getPurchaseOrderDetails()
        +getPurchaseOrderAutocomplete()
    }
    
    class SPOrderPlanningSheetRepo {
        +save()
        +searchOrderPlanningSheet()
        +getOrderPlanningSheetById()
        +getOrderPlanningSheetItems()
    }
    
    class SPBackOrderCancellationRepo {
        +save()
        +searchBackOrderCancellation()
        +getBackOrderCancellationById()
    }
    
    class SPBackOrderCancellationApprovalRepo {
        +save()
        +findByBackOrderCancellationId()
    }
    
    class SpPartDiscrepancyClaimHdrRepo {
        +save()
        +searchDiscrepancyClaim()
        +getDiscrepancyClaimById()
    }
    
    class SpPartDiscrepancyClaimAttachmentRepo {
        +save()
        +findByDiscrepancyClaimId()
    }
    
    class SpPartDiscrepancyClaimApprovalRepo {
        +save()
        +findByDiscrepancyClaimId()
    }
    
    class SpDiscrepancyKaiAdditionalRemarksRepo {
        +save()
        +findByDiscrepancyClaimId()
    }
    
    class BlockPartsForSaleRepo {
        +findByItemNo()
        +save()
    }
    
    class StockAdjustmentRepo {
        +save()
        +searchStockAdjustment()
        +getStockAdjustmentById()
    }
    
    class StockAdjustmentApprovalRepo {
        +save()
        +findByStockAdjustmentId()
    }
    
    class SPBranchTransferIndentRepo {
        +save()
        +searchBranchTransferIndent()
        +getBranchTransferIndentById()
    }
    
    class SPBranchTransferIssueRepo {
        +save()
        +searchBranchTransferIssue()
        +getBranchTransferIssueById()
    }
    
    class SPBranchTransferReceiptRepo {
        +save()
        +searchBranchTransferReceipt()
        +getBranchTransferReceiptById()
    }
    
    class BinToBinTransferRepository {
        +save()
        +searchBinToBinTransfer()
        +getBinToBinTransferById()
    }
    
    class CurrentStockRepo {
        +getCurrentStock()
        +searchCurrentStock()
    }
    
    class MachineStockRepository {
        +getMachineStock()
        +searchMachineStock()
    }
    
    class SpareStockCurrentRepo {
        +getNonMovingInventory()
        +searchNonMovingInventory()
    }
    
    class SpareStockStoreBinDetailRepository {
        +save()
        +findByItemNoAndStoreId()
        +getAvailableStock()
    }
    
    %% ============================================
    %% DOMAIN ENTITIES (Main Entities)
    %% ============================================
    
    class SparesInvoice {
        -Long id
        -String invoiceNumber
        -Date invoiceDate
        -Date createdDate
        -String transportMode
        -String transporter
        -String lrNo
        -Date lrDate
        -String paymentType
        -Double totalBaseAmount
        -Double tcsPercent
        -Double tcsAmount
        -Double totalTaxAmount
        -Double totalInvoiceAmount
        -Long sparesSalesOrderId
        -String salesType
        -Long serviceJobcardId
        -Long warrantyWcrId
        -Long createdBy
        -Long lastModifiedBy
        -Date lastModifiedDate
        -Long branchId
        -String customerName
        -String customerAddress1
        -String customerAddress2
        -Integer pinCode
        -String postOffice
        -String village
        -String tehsil
        -String district
        -String state
        -String country
        -String contactNumber
        -Boolean draftFlag
    }
    
    class SparesInvoicePartDetail {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer quantity
        -Double unitPrice
        -Double baseAmount
        -Double taxAmount
        -Double totalAmount
    }
    
    class SparesInvoiceLabourDetail {
        -Long id
        -String workCode
        -String workDescription
        -Double amount
    }
    
    class SparesInvoiceOutsideChargeDetail {
        -Long id
        -String chargeType
        -String chargeDescription
        -Double amount
    }
    
    class SpareSalesOrder {
        -Long id
        -String salesOrderNumber
        -Date salesOrderDate
        -String customerType
        -String customerName
        -String customerAddress1
        -String customerAddress2
        -Integer pinCode
        -Long pinId
        -String postOffice
        -String village
        -String tehsil
        -String district
        -String state
        -String country
        -String contactNumber
        -String discountType
        -Double discountRate
        -Double totalDiscountValue
        -Double totalBasicValue
        -Double totalTaxValue
        -Double totalSalesOrderAmount
        -Boolean draftFlag
        -Long createdBy
        -Date createdDate
        -Long lastModifiedBy
        -Date lastModifiedDate
        -Long branchId
        -String status
    }
    
    class SpareSalesOrderPartDetail {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer quantity
        -Integer backQuantity
        -Double unitPrice
        -Double baseAmount
        -Double taxAmount
        -Double totalAmount
    }
    
    class SpareQuotation {
        -Long id
        -String quotationNumber
        -Date quotationDate
        -String customerType
        -String customerName
        -String customerAddress1
        -String customerAddress2
        -Integer pinCode
        -String postOffice
        -String village
        -String tehsil
        -String district
        -String state
        -String country
        -String contactNumber
        -String discountType
        -Double discountRate
        -Double totalDiscountValue
        -Double totalBasicValue
        -Double totalTaxValue
        -Double totalQuotationAmount
        -Boolean draftFlag
        -String quotationStatus
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SpareQuotationPartDetail {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer quantity
        -Double unitPrice
        -Double baseAmount
        -Double taxAmount
        -Double totalAmount
    }
    
    class PickList {
        -Long id
        -String picklistNumber
        -Date picklistDate
        -Long salesOrderId
        -String status
        -Long createdBy
        -Date createdDate
        -Long lastModifiedBy
        -Date lastModifiedDate
        -Long branchId
    }
    
    class PickListItemDtl {
        -Long id
        -String pickedItemNo
        -String itemDescription
        -Integer issueQty
        -Integer returnQty
        -Double unitPrice
        -Long storeId
        -Long binLocationId
    }
    
    class PickListReturn {
        -Long id
        -Long picklistDtlId
        -Integer returnQty
        -Long createdBy
        -Date createdDate
    }
    
    class SparePartRequisition {
        -Long id
        -String requisitionNumber
        -Date requisitionDate
        -String type
        -String requisitionPurpose
        -String status
        -Long jobCardId
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SparePartRequisitionItem {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer reqQuantity
        -Double priceUnit
        -Double amount
    }
    
    class SparePartIssue {
        -Long id
        -String issueNumber
        -Date issueDate
        -String status
        -Long requisitionId
        -Long jobCardId
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SparePartIssueItem {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer issueQuantity
        -Double unitPrice
        -Double amount
    }
    
    class SparePartReturn {
        -Long id
        -String returnNumber
        -Date returnDate
        -String status
        -Long issueId
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SparePartReturnItem {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer returnQuantity
        -Double unitPrice
        -Double amount
    }
    
    class SparePurchaseOrder {
        -Long id
        -String purchaseOrderNumber
        -Date purchaseOrderDate
        -String supplierType
        -String freightBorneBy
        -String transportMode
        -String transporter
        -Double creditLimit
        -Double currentOutStanding
        -Double overduesOutStanding
        -Double paymentUnderProcess
        -Double ordersUnderProcess
        -Double availableLimit
        -Double netAmountPayable
        -String remarks
        -Boolean draftFlag
        -Long createdBy
        -Date createdDate
        -Long lastModifiedBy
        -Date lastModifiedDate
        -Double totalBaseAmount
        -Double totalPoAmount
        -Long vendorPartyId
        -String purchaseOrderStatus
        -String priceType
        -Boolean grnDoneFlag
        -Double transportationCharges
    }
    
    class SparePurchaseOrderItem {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer quantity
        -Integer receivedQuantity
        -Integer pendingQuantity
        -Double unitPrice
        -Double baseAmount
        -Double taxAmount
        -Double totalAmount
    }
    
    class SparePOApproval {
        -Long id
        -String approvalStatus
        -String remarks
        -Long approvedBy
        -Date approvedOn
        -Integer approvalLevel
    }
    
    class SparePartGrn {
        -Long id
        -String grnNumber
        -Date grnDate
        -String status
        -Long purchaseOrderId
        -String transporter
        -String lrNo
        -Date lrDate
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SparePartGrnItem {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer orderedQuantity
        -Integer receivedQuantity
        -Integer acceptedQuantity
        -Integer rejectedQuantity
        -Double unitPrice
        -Double baseAmount
        -Double taxAmount
        -Double totalAmount
    }
    
    class SPOrderPlanningSheet {
        -Long id
        -String orderPlanningSheetNumber
        -Date orderPlanningSheetDate
        -String status
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SPOrderPlanningSheetDetail {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer currentStock
        -Integer minStock
        -Integer maxStock
        -Integer suggestedOrderQty
        -Double unitPrice
    }
    
    class SPOrderPlanningSuggestedOrderQty {
        -Long id
        -String itemNumber
        -Integer suggestedOrderQty
    }
    
    class SPOrderPlanningSheetLogic {
        -Long id
        -String logicName
        -String logicDescription
    }
    
    class SpareOrderPlanningOrderType {
        -Long id
        -String orderTypeName
        -String orderTypeDescription
    }
    
    class SPBackOrderCancellation {
        -Long id
        -String backOrderCancellationNumber
        -Date backOrderCancellationDate
        -String status
        -Long purchaseOrderId
        -String cancellationReason
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SPBackOrderCancellationDtl {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer cancelledQuantity
        -String cancellationReason
    }
    
    class SPBackOrderCancellationApproval {
        -Long id
        -String approvalStatus
        -String remarks
        -Long approvedBy
        -Date approvedOn
    }
    
    class SpPartDiscrepancyClaimHdr {
        -Long id
        -String discrepancyClaimNumber
        -Date discrepancyClaimDate
        -String status
        -Long grnId
        -String claimType
        -String claimReason
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SpPartDiscrepancyClaimDtl {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer claimedQuantity
        -String discrepancyType
        -String discrepancyReason
    }
    
    class SpPartDiscrepancyClaimAttachment {
        -Long id
        -String attachmentPath
        -String attachmentType
    }
    
    class SpPartDiscrepancyClaimApproval {
        -Long id
        -String approvalStatus
        -String remarks
        -Long approvedBy
        -Date approvedOn
        -Integer approvalLevel
    }
    
    class SpDiscrepancyKaiAdditionalRemarks {
        -Long id
        -String remarks
        -Long addedBy
        -Date addedOn
    }
    
    class StockAdjustmentHdr {
        -Long id
        -String adjustmentNumber
        -Date adjustmentDate
        -String status
        -String adjustmentType
        -String adjustmentReason
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class StockAdjustmentDtl {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer currentQuantity
        -Integer adjustedQuantity
        -Integer differenceQuantity
        -String adjustmentReason
    }
    
    class StockAdjustmentApprovalEntity {
        -Long id
        -String approvalStatus
        -String remarks
        -Long approvedBy
        -Date approvedOn
    }
    
    class SPBranchTransferIndent {
        -Long id
        -String indentNumber
        -Date indentDate
        -String status
        -Long fromBranchId
        -Long toBranchId
        -String transferType
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SPBranchTransferIndentItem {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer requestedQuantity
        -Double unitPrice
    }
    
    class SPBranchTransferIssue {
        -Long id
        -String issueNumber
        -Date issueDate
        -String status
        -Long indentId
        -Long fromBranchId
        -Long toBranchId
        -String transporter
        -String lrNo
        -Date lrDate
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SPBranchTransferIssueItem {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer issuedQuantity
        -Double unitPrice
    }
    
    class SPBranchTransferReceipt {
        -Long id
        -String receiptNumber
        -Date receiptDate
        -String status
        -Long issueId
        -Long fromBranchId
        -Long toBranchId
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SPBranchTransferReceiptItem {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer receivedQuantity
        -Integer acceptedQuantity
        -Integer rejectedQuantity
        -Double unitPrice
    }
    
    class SparePartBinTransferHDR {
        -Long id
        -String transferNumber
        -Date transferDate
        -String status
        -Long fromStoreId
        -Long toStoreId
        -Long fromBinLocationId
        -Long toBinLocationId
        -Long createdBy
        -Date createdDate
        -Long branchId
    }
    
    class SparePartBinTransferDTL {
        -Long id
        -String itemNumber
        -String itemDescription
        -Integer transferQuantity
        -Double unitPrice
    }
    
    class SpareStockStoreBinDetail {
        -Long id
        -String itemNo
        -Long storeId
        -Long binLocationId
        -Integer inQty
        -Integer outQty
        -Integer usedQty
        -Integer avlQty
        -Double unitPrice
        -Double spmrp
        -Double spegst
        -Double spmgst
        -String transactionType
        -Long refTransHdrId
        -Date transactionDate
        -Long branchId
        -Long createdby
        -Date createddate
        -Long modifiedby
        -Date modifieddate
    }
    
    class SpareStockCurrent {
        -Long id
        -String itemNo
        -String itemDescription
        -Integer currentStock
        -Integer minStock
        -Integer maxStock
        -Date lastMovementDate
        -Long branchId
    }
    
    %% ============================================
    %% DTO LAYER (Grouped by Sub-module)
    %% ============================================
    
    class SpareInvoiceViewDto {
        +Map invoiceDetails
        +List itemDetails
        +List labourDetails
        +List outsideChargeDetails
    }
    
    class SpareInvoiceSearch {
        +String salesInvoiceId
        +String customerCode
        +String customerName
        +String referenceDocument
        +String customerType
        +String salesType
        +String modeOfTransport
        +String transporter
        +Date fromDate
        +Date toDate
        +Integer page
        +Integer size
        +String wcrNo
        +String jobCardNumber
    }
    
    class ResponseSearchSparesInvoice {
        +Long id
        +String invoiceNumber
        +Date invoiceDate
        +String customerName
        +String salesType
        +Double totalInvoiceAmount
        +Long recordCount
    }
    
    class SalesOrderDto {
        +Map invoiceDetails
        +List itemDetails
    }
    
    class InvoiceCancellationDto {
        +Long id
        +String referenceType
        +String remark
    }
    
    class SpareSaleOrderResponseDto {
        +Long id
        +String salesOrderNumber
        +Date salesOrderDate
        +String customerName
        +String customerType
        +String status
        +Double totalSalesOrderAmount
        +Long recordCount
    }
    
    class SpareSearchSalesOrderDto {
        +String salesOrderId
        +String customerName
        +String customerType
        +String orderStatus
        +Date orderFromDate
        +Date orderToDate
        +Integer page
        +Integer size
    }
    
    class SpareQuotationViewDto {
        +Map headerResponse
        +List partDetails
        +List partDetailsForSalesOrder
    }
    
    class SpareQuotationSearchDto {
        +String quotationId
        +String customerName
        +String customerType
        +Date quotationFromDate
        +Date quotationToDate
        +Integer page
        +Integer size
    }
    
    class SpareQuotationSearchResponse {
        +Long id
        +String quotationNumber
        +Date quotationDate
        +String customerName
        +String customerType
        +String quotationStatus
        +Double totalQuotationAmount
        +Long recordCount
    }
    
    class PickListSearchDto {
        +String picklistNumber
        +String orderStatus
        +Date orderFromDate
        +Date orderToDate
        +Integer page
        +Integer size
    }
    
    class PickListSearchItem {
        +Long id
        +String picklistNumber
        +Date picklistDate
        +String salesOrderNumber
        +String status
        +Long recordCount
    }
    
    class SalesOrderDetailsDto {
        +Map headerResponse
        +List partDetails
    }
    
    class PartRequisitionDetailDto {
        +Long id
        +String requisitionNumber
        +Date requisitionDate
        +String type
        +String status
        +List itemDetails
    }
    
    class RequisitionSearchObject {
        +String requisitionNumber
        +String jobCardNumber
        +Date fromDate
        +Date toDate
        +String status
        +Integer page
        +Integer size
    }
    
    class RequisitionSearchResponse {
        +Long id
        +String requisitionNumber
        +Date requisitionDate
        +String status
        +Long recordCount
    }
    
    class PartIssueSearchObject {
        +String issueNumber
        +String requisitionNumber
        +Date fromDate
        +Date toDate
        +String status
        +Integer page
        +Integer size
    }
    
    class IssueSearchResponse {
        +Long id
        +String issueNumber
        +Date issueDate
        +String status
        +Long recordCount
    }
    
    class PartReturnSearchObject {
        +String returnNumber
        +String issueNumber
        +Date fromDate
        +Date toDate
        +String status
        +Integer page
        +Integer size
    }
    
    class ReturnSearchResponse {
        +Long id
        +String returnNumber
        +Date returnDate
        +String status
        +Long recordCount
    }
    
    class PartIssueRequisitionDetail {
        +Map headerDetails
        +List itemDetails
    }
    
    class PartReturnDetail {
        +Map headerDetails
        +List itemDetails
    }
    
    class ResponseSearchPurchaseOrder {
        +Long id
        +String purchaseOrderNumber
        +Date purchaseOrderDate
        +String supplierType
        +String purchaseOrderStatus
        +Double totalPoAmount
        +Long recordCount
    }
    
    class SearchSparePurchaseOrder {
        +String purchaseOrderId
        +String supplierType
        +String orderType
        +Date fromDate
        +Date toDate
        +String status
        +Integer page
        +Integer size
    }
    
    class PoViewDto {
        +Map headerDetails
        +List partDetails
    }
    
    class OPSItemsDetailResponseDto {
        +String itemNumber
        +String itemDescription
        +Integer currentStock
        +Integer minStock
        +Integer maxStock
        +Integer suggestedOrderQty
        +Double unitPrice
    }
    
    class DealerOutstandingResponse {
        +Double creditLimit
        +Double currentOutStanding
        +Double overduesOutStanding
        +Double paymentUnderProcess
        +Double ordersUnderProcess
        +Double availableLimit
    }
    
    class BackOrderPartsReportsDto {
        +String itemNumber
        +String itemDescription
        +Integer pendingQuantity
        +Date orderDate
    }
    
    class ClosingStockReportDto {
        +String itemNumber
        +String itemDescription
        +Integer closingStock
        +Double unitPrice
        +Double totalValue
    }
    
    class InventoryMovementDto {
        +String itemNumber
        +String itemDescription
        +Integer openingStock
        +Integer inQuantity
        +Integer outQuantity
        +Integer closingStock
    }
    
    class ItemMovementDto {
        +Date transactionDate
        +String transactionType
        +String documentNumber
        +Integer quantity
        +String movementType
    }
    
    class NonMovingPartsStockReportDto {
        +String itemNumber
        +String itemDescription
        +Integer currentStock
        +Date lastMovementDate
    }
    
    class ReportSearchDto {
        +Date fromDate
        +Date toDate
        +String itemNumber
        +Long branchId
        +Long storeId
    }
    
    %% ============================================
    %% EXTERNAL DEPENDENCIES
    %% ============================================
    
    class ServiceJobCard {
        -Long id
        -String jobCardNo
        -Date jobCardDate
    }
    
    class WarrantyWcr {
        -Long id
        -String wcrNo
        -Date wcrDate
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
    
    class DealerEmployeeMaster {
        -Long id
        -String employeeName
        -String designation
    }
    
    class SparesMtSupplier {
        -Long id
        -String supplierCode
        -String supplierName
    }
    
    class SparesMtPurchaseOrderOrderType {
        -Long id
        -String orderTypeName
    }
    
    class SpareMtPartCategory {
        -Long id
        -String categoryName
    }
    
    class PartyMaster {
        -Long id
        -String partyName
        -String partyType
    }
    
    class SparePartMaster {
        -Long id
        -String itemNumber
        -String itemDescription
    }
    
    class UserAuthentication {
        +getBranchId()
        +getLoginId()
        +getDealerId()
        +getDealerEmployeeId()
        +getKubotaEmployeeId()
        +getManagementAccess()
        +getUsername()
    }
    
    class ApiResponse {
        +Object result
        +String message
        +Integer status
        +Long count
    }
    
    class ExcelCellGenerator {
        +sparePartSalesReport()
        +customerSalseOrderExcelReport()
    }
    
    class StorageService {
        +uploadFile()
        +downloadFile()
        +deleteFile()
    }
    
    %% ============================================
    %% RELATIONSHIPS - Controllers to Services
    %% ============================================
    
    SparesInvoiceController --> SparesInvoiceRepo : uses
    SparesInvoiceController --> UserAuthentication : uses
    SparesInvoiceController --> ExcelCellGenerator : uses
    
    SpareSalesOrderController --> SpareSalesOrderService : uses
    SpareSalesOrderController --> SpareSalesOrderRepository : uses
    SpareSalesOrderController --> UserAuthentication : uses
    SpareSalesOrderController --> ExcelCellGenerator : uses
    
    SpareQuotationController --> SpareQuotationService : uses
    SpareQuotationController --> SpareQuotationRepository : uses
    SpareQuotationController --> UserAuthentication : uses
    
    PickListController --> PickListRepository : uses
    PickListController --> PicklistReturnRepository : uses
    PickListController --> SpareStockStoreBinDetailRepository : uses
    PickListController --> UserAuthentication : uses
    
    SparePartRequisitionController --> SparePartRequisitionRepo : uses
    SparePartRequisitionController --> UserAuthentication : uses
    
    SparePartIssueController --> SparePartIssueRepository : uses
    SparePartIssueController --> UserAuthentication : uses
    
    SparePartReturnController --> SparePartReturnRepository : uses
    SparePartReturnController --> UserAuthentication : uses
    
    SparePurchaseOrderController --> SparesPurchaseOrderService : uses
    SparePurchaseOrderController --> SparePurchaseOrderRepository : uses
    SparePurchaseOrderController --> SparePurchaseOrderApprovalRepository : uses
    SparePurchaseOrderController --> UserAuthentication : uses
    SparePurchaseOrderController --> ExcelCellGenerator : uses
    
    SparePartGrnController --> SparePartGrnRepository : uses
    SparePartGrnController --> UserAuthentication : uses
    
    SPOrderPlanningSheetController --> SPOrderPlanningSheetService : uses
    SPOrderPlanningSheetController --> SPOrderPlanningSheetRepo : uses
    SPOrderPlanningSheetController --> UserAuthentication : uses
    
    SPBackOrderCancellationController --> SPBackOrderCancellationService : uses
    SPBackOrderCancellationController --> SPBackOrderCancellationRepo : uses
    SPBackOrderCancellationController --> SPBackOrderCancellationApprovalRepo : uses
    SPBackOrderCancellationController --> UserAuthentication : uses
    
    SpPartDiscrepancyClaimController --> SpPartDiscrepancyClaimService : uses
    SpPartDiscrepancyClaimController --> SpPartDiscrepancyClaimHdrRepo : uses
    SpPartDiscrepancyClaimController --> SpPartDiscrepancyClaimAttachmentRepo : uses
    SpPartDiscrepancyClaimController --> StorageService : uses
    SpPartDiscrepancyClaimController --> UserAuthentication : uses
    
    StockAdjustmentController --> StockAdjustmentRepo : uses
    StockAdjustmentController --> StockAdjustmentApprovalRepo : uses
    StockAdjustmentController --> UserAuthentication : uses
    
    SPBranchTransferIndentController --> SPBranchTransferIndentService : uses
    SPBranchTransferIndentController --> SPBranchTransferIndentRepo : uses
    SPBranchTransferIndentController --> UserAuthentication : uses
    
    SPBranchTransferIssueController --> SPBranchTransferIssueService : uses
    SPBranchTransferIssueController --> SPBranchTransferIssueRepo : uses
    SPBranchTransferIssueController --> UserAuthentication : uses
    
    SPBranchTransferReceiptController --> SPBranchTransferReceiptService : uses
    SPBranchTransferReceiptController --> SPBranchTransferReceiptRepo : uses
    SPBranchTransferReceiptController --> UserAuthentication : uses
    
    BinToBinTransferController --> BinToBinTransferRepository : uses
    BinToBinTransferController --> UserAuthentication : uses
    
    CurrentStockController --> CurrentStockRepo : uses
    CurrentStockController --> UserAuthentication : uses
    
    MachineStockController --> MachineStockRepository : uses
    MachineStockController --> UserAuthentication : uses
    
    NonMovInventoryController --> NonMovInventoryService : uses
    NonMovInventoryController --> SpareStockCurrentRepo : uses
    NonMovInventoryController --> UserAuthentication : uses
    
    SpareReportsController --> SpareSalesOrderRepository : uses
    SpareReportsController --> SparePartGrnRepository : uses
    SpareReportsController --> SpareStockStoreBinDetailRepository : uses
    SpareReportsController --> UserAuthentication : uses
    
    %% ============================================
    %% RELATIONSHIPS - Services to Repositories
    %% ============================================
    
    SpareSalesOrdeImpl ..|> SpareSalesOrderService : implements
    SpareSalesOrdeImpl --> SpareSalesOrderRepository : uses
    
    SpareQuotationImpl ..|> SpareQuotationService : implements
    SpareQuotationImpl --> SpareQuotationRepository : uses
    
    SparePurchaseOrderImpl ..|> SparesPurchaseOrderService : implements
    SparePurchaseOrderImpl --> SparePurchaseOrderRepository : uses
    
    SPOrderPlanningSheetServiceImpl ..|> SPOrderPlanningSheetService : implements
    SPOrderPlanningSheetServiceImpl --> SPOrderPlanningSheetRepo : uses
    
    SPBackOrderCancellationServiceImpl ..|> SPBackOrderCancellationService : implements
    SPBackOrderCancellationServiceImpl --> SPBackOrderCancellationRepo : uses
    SPBackOrderCancellationServiceImpl --> SPBackOrderCancellationApprovalRepo : uses
    
    SpPartDiscrepancyClaimServiceImpl ..|> SpPartDiscrepancyClaimService : implements
    SpPartDiscrepancyClaimServiceImpl --> SpPartDiscrepancyClaimHdrRepo : uses
    SpPartDiscrepancyClaimServiceImpl --> SpPartDiscrepancyClaimAttachmentRepo : uses
    SpPartDiscrepancyClaimServiceImpl --> StorageService : uses
    
    SPBranchTransferIndentServiceImpl ..|> SPBranchTransferIndentService : implements
    SPBranchTransferIndentServiceImpl --> SPBranchTransferIndentRepo : uses
    
    SPBranchTransferIssueServiceImpl ..|> SPBranchTransferIssueService : implements
    SPBranchTransferIssueServiceImpl --> SPBranchTransferIssueRepo : uses
    
    SPBranchTransferReceiptServiceImpl ..|> SPBranchTransferReceiptService : implements
    SPBranchTransferReceiptServiceImpl --> SPBranchTransferReceiptRepo : uses
    
    NonMovInventoryServiceImpl ..|> NonMovInventoryService : implements
    NonMovInventoryServiceImpl --> SpareStockCurrentRepo : uses
    
    %% ============================================
    %% RELATIONSHIPS - Domain Entity Relationships
    %% ============================================
    
    SparesInvoice --> SpareSalesOrder : ManyToOne sparesSalesOrderId
    SparesInvoice --> ServiceJobCard : ManyToOne serviceJobcardId
    SparesInvoice --> WarrantyWcr : ManyToOne warrantyWcrId
    SparesInvoice --> SparesInvoicePartDetail : OneToMany
    SparesInvoice --> SparesInvoiceLabourDetail : OneToMany
    SparesInvoice --> SparesInvoiceOutsideChargeDetail : OneToMany
    
    SpareSalesOrder --> CustomerMaster : ManyToOne
    SpareSalesOrder --> SpareQuotation : ManyToOne
    SpareSalesOrder --> PartyMaster : ManyToOne
    SpareSalesOrder --> DealerMaster : ManyToOne coDealer
    SpareSalesOrder --> SpareSalesOrderPartDetail : OneToMany
    
    SpareQuotation --> SpareQuotationPartDetail : OneToMany
    
    PickList --> SpareSalesOrder : ManyToOne salesOrderId
    PickList --> PickListItemDtl : OneToMany
    PickListReturn --> PickListItemDtl : ManyToOne picklistDtlId
    
    SparePartRequisition --> ServiceJobCard : ManyToOne jobCardId
    SparePartRequisition --> SparePartRequisitionItem : OneToMany
    
    SparePartIssue --> SparePartRequisition : ManyToOne requisitionId
    SparePartIssue --> ServiceJobCard : ManyToOne jobCardId
    SparePartIssue --> SparePartIssueItem : OneToMany
    
    SparePartReturn --> SparePartIssue : ManyToOne issueId
    SparePartReturn --> SparePartReturnItem : OneToMany
    
    SparePurchaseOrder --> SparesMtPurchaseOrderOrderType : ManyToOne orderType
    SparePurchaseOrder --> SparesMtSupplier : ManyToOne sparesMtSupplier
    SparePurchaseOrder --> DealerMaster : ManyToOne dealerMaster
    SparePurchaseOrder --> DealerMaster : ManyToOne coDealerMaster
    SparePurchaseOrder --> ServiceJobCard : ManyToOne serviceJobCard
    SparePurchaseOrder --> SparePurchaseOrderItem : OneToMany
    SparePurchaseOrder --> SparePOApproval : OneToMany
    
    SparePartGrn --> SparePurchaseOrder : ManyToOne purchaseOrderId
    SparePartGrn --> SparePartGrnItem : OneToMany
    
    SPOrderPlanningSheet --> SPOrderPlanningSheetDetail : OneToMany
    SPOrderPlanningSheet --> SPOrderPlanningSuggestedOrderQty : OneToMany
    SPOrderPlanningSheet --> SpareOrderPlanningOrderType : ManyToOne
    
    SPBackOrderCancellation --> SparePurchaseOrder : ManyToOne purchaseOrderId
    SPBackOrderCancellation --> SPBackOrderCancellationDtl : OneToMany
    SPBackOrderCancellation --> SPBackOrderCancellationApproval : OneToMany
    
    SpPartDiscrepancyClaimHdr --> SparePartGrn : ManyToOne grnId
    SpPartDiscrepancyClaimHdr --> SpPartDiscrepancyClaimDtl : OneToMany
    SpPartDiscrepancyClaimHdr --> SpPartDiscrepancyClaimAttachment : OneToMany
    SpPartDiscrepancyClaimHdr --> SpPartDiscrepancyClaimApproval : OneToMany
    SpPartDiscrepancyClaimHdr --> SpDiscrepancyKaiAdditionalRemarks : OneToMany
    
    StockAdjustmentHdr --> StockAdjustmentDtl : OneToMany
    StockAdjustmentHdr --> StockAdjustmentApprovalEntity : OneToMany
    
    SPBranchTransferIndent --> SPBranchTransferIndentItem : OneToMany
    SPBranchTransferIndent --> DealerMaster : ManyToOne fromBranchId
    SPBranchTransferIndent --> DealerMaster : ManyToOne toBranchId
    
    SPBranchTransferIssue --> SPBranchTransferIndent : ManyToOne indentId
    SPBranchTransferIssue --> SPBranchTransferIssueItem : OneToMany
    SPBranchTransferIssue --> DealerMaster : ManyToOne fromBranchId
    SPBranchTransferIssue --> DealerMaster : ManyToOne toBranchId
    
    SPBranchTransferReceipt --> SPBranchTransferIssue : ManyToOne issueId
    SPBranchTransferReceipt --> SPBranchTransferReceiptItem : OneToMany
    SPBranchTransferReceipt --> DealerMaster : ManyToOne fromBranchId
    SPBranchTransferReceipt --> DealerMaster : ManyToOne toBranchId
    
    SparePartBinTransferHDR --> SparePartBinTransferDTL : OneToMany
    
    SpareStockStoreBinDetail --> SparePartMaster : ManyToOne itemNo
    SpareStockStoreBinDetail --> DealerMaster : ManyToOne branchId
    
    SpareStockCurrent --> SparePartMaster : ManyToOne itemNo
    SpareStockCurrent --> DealerMaster : ManyToOne branchId
    
    %% ============================================
    %% REPOSITORY TO ENTITY RELATIONSHIPS
    %% ============================================
    
    SparesInvoiceRepo --> SparesInvoice : manages
    SpareSalesOrderRepository --> SpareSalesOrder : manages
    SpareQuotationRepository --> SpareQuotation : manages
    PickListRepository --> PickList : manages
    PicklistReturnRepository --> PickListReturn : manages
    SparePartRequisitionRepo --> SparePartRequisition : manages
    SparePartRequisitionItemRepo --> SparePartRequisitionItem : manages
    SparePartIssueRepository --> SparePartIssue : manages
    SparePartReturnRepository --> SparePartReturn : manages
    SparePurchaseOrderRepository --> SparePurchaseOrder : manages
    SparePurchaseOrderApprovalRepository --> SparePOApproval : manages
    SparePartGrnRepository --> SparePartGrn : manages
    SPOrderPlanningSheetRepo --> SPOrderPlanningSheet : manages
    SPBackOrderCancellationRepo --> SPBackOrderCancellation : manages
    SPBackOrderCancellationApprovalRepo --> SPBackOrderCancellationApproval : manages
    SpPartDiscrepancyClaimHdrRepo --> SpPartDiscrepancyClaimHdr : manages
    SpPartDiscrepancyClaimAttachmentRepo --> SpPartDiscrepancyClaimAttachment : manages
    SpPartDiscrepancyClaimApprovalRepo --> SpPartDiscrepancyClaimApproval : manages
    SpDiscrepancyKaiAdditionalRemarksRepo --> SpDiscrepancyKaiAdditionalRemarks : manages
    StockAdjustmentRepo --> StockAdjustmentHdr : manages
    StockAdjustmentApprovalRepo --> StockAdjustmentApprovalEntity : manages
    SPBranchTransferIndentRepo --> SPBranchTransferIndent : manages
    SPBranchTransferIssueRepo --> SPBranchTransferIssue : manages
    SPBranchTransferReceiptRepo --> SPBranchTransferReceipt : manages
    BinToBinTransferRepository --> SparePartBinTransferHDR : manages
    CurrentStockRepo --> SpareStockStoreBinDetail : manages
    MachineStockRepository --> SpareStockStoreBinDetail : manages
    SpareStockCurrentRepo --> SpareStockCurrent : manages
    SpareStockStoreBinDetailRepository --> SpareStockStoreBinDetail : manages
```

## Module Overview

The **Spares Module** is a comprehensive module in the ITLDIS application that handles spare parts inventory management, sales, purchase, requisition, and reporting. It is one of the largest modules with 239 Java files.

### Core Sub-Modules:

#### 1. **Invoice Management**
   - Create invoices from sales orders, job cards, or warranty claims
   - Invoice cancellation functionality
   - Support for part details, labour charges, and outside charges
   - Invoice search and view
   - Excel export for part sales reports

#### 2. **Sales Order Management**
   - Create sales orders from quotations
   - Support for multiple customer types (Dealer, Retailer, Mechanic, etc.)
   - Sales order search and view
   - Excel upload for bulk item entry
   - Excel export for sales order reports
   - Track back quantities for partial fulfillment

#### 3. **Quotation Management**
   - Create quotations for customers
   - Convert quotations to sales orders
   - Quotation search and view
   - Excel upload for bulk item entry
   - Support for discount types and rates

#### 4. **Picklist & Counter Sales**
   - Create picklists from sales orders
   - Picklist return functionality
   - Stock update on picklist creation
   - Picklist search and view
   - Integration with stock management

#### 5. **Part Requisition Management**
   - Create part requisitions (APR - Auto Part Requisition)
   - Link requisitions to job cards
   - Part issue from requisitions
   - Part return functionality
   - Requisition, issue, and return search

#### 6. **Purchase Order Management**
   - Create purchase orders with multiple order types
   - Support for different supplier types
   - Purchase order approval workflow
   - Integration with Order Planning Sheet (OPS)
   - Dealer outstanding tracking
   - Excel upload for bulk item entry
   - Excel export for purchase order reports

#### 7. **GRN (Goods Receipt Note) Management**
   - Create GRN from purchase orders
   - Track ordered, received, accepted, and rejected quantities
   - GRN search and view
   - Integration with stock management

#### 8. **Order Planning Sheet (OPS)**
   - Generate order planning sheets based on stock levels
   - Suggest order quantities based on min/max stock
   - Support for multiple order types
   - OPS item details and logic management

#### 9. **Back Order Cancellation**
   - Cancel back orders from purchase orders
   - Back order cancellation approval workflow
   - Track cancellation reasons

#### 10. **Discrepancy Claim & MMR Request**
   - Create discrepancy claims for GRN items
   - Support for attachments
   - Discrepancy claim approval workflow
   - KAI additional remarks tracking

#### 11. **Block Parts for Sale**
   - Block/unblock parts for sale
   - Search blocked parts

#### 12. **Binning Slip**
   - Generate binning slips for GRN items
   - Track bin locations for received items

#### 13. **Transit Report**
   - Generate transit reports for items in transit
   - Track items between branches

#### 14. **Stock Adjustment**
   - Create stock adjustments
   - Stock adjustment approval workflow
   - Track adjustment reasons and quantities

#### 15. **Branch Transfer Management**
   - **Indent**: Create branch transfer indents
   - **Issue**: Issue items from source branch
   - **Receipt**: Receive items at destination branch
   - Track transfer status and quantities

#### 16. **Bin-to-Bin Transfer**
   - Transfer items between bins within a store
   - Track bin locations and quantities

#### 17. **Current Stock Management**
   - View current stock by item, store, and bin
   - Current stock search and reports
   - Excel export for stock reports

#### 18. **Machine Stock Management**
   - Track stock allocated to specific machines
   - Machine stock search and view

#### 19. **Non-Moving Inventory**
   - Identify non-moving inventory items
   - Track last movement date
   - Non-moving inventory reports

#### 20. **Reports**
   - Back order parts reports
   - Closing stock reports
   - Inventory movement reports
   - Item movement reports
   - Non-moving parts stock reports

### Key Components:

- **25 Controllers**: REST controllers handling HTTP requests
- **10 Service Interfaces**: Business logic interfaces
- **10 Service Implementations**: Business logic implementations
- **30+ Repositories**: Data access layer using Spring Data JPA
- **50+ Domain Entities**: JPA entities representing database tables
- **40+ DTOs**: Data transfer objects for API communication

### Technology Stack:

- **Framework**: Spring Boot (REST API)
- **ORM**: JPA/Hibernate
- **Database**: SQL Server
- **File Storage**: StorageService (for attachment management)
- **Reporting**: Excel export using ExcelCellGenerator
- **Security**: UserAuthentication service

### Key Relationships:

1. **Invoice → Sales Order/Job Card/WCR**: Invoice can be created from sales order, job card, or warranty claim
2. **Sales Order → Quotation**: Sales order can be created from quotation
3. **Picklist → Sales Order**: Picklist is created from sales order
4. **Part Requisition → Job Card**: Part requisition is linked to job card
5. **Part Issue → Part Requisition**: Part issue is created from requisition
6. **Part Return → Part Issue**: Part return is created from part issue
7. **GRN → Purchase Order**: GRN is created from purchase order
8. **Purchase Order → Order Planning Sheet**: Purchase order can be created from OPS
9. **Branch Transfer**: Indent → Issue → Receipt workflow
10. **Stock Management**: All transactions update stock through SpareStockStoreBinDetail

### Data Flow:

1. **Request Flow**: HTTP Request → Controller → Service → Repository → Database
2. **Response Flow**: Database → Repository → Service → DTO → Controller → JSON Response
3. **File Upload Flow**: MultipartFile → Controller → Service → Excel Processing → Data Extraction
4. **Approval Flow**: Entity → Approval Entity → Repository → Database
5. **Stock Update Flow**: Transaction → Stock Update → SpareStockStoreBinDetail → Database
6. **Report Generation**: Entity Data → ExcelCellGenerator → Excel Output

### Key Features:

- **Multi-level Approval Workflows**: Purchase orders, back order cancellations, discrepancy claims, and stock adjustments support approval workflows
- **Excel Import/Export**: Bulk operations and reporting via Excel
- **Comprehensive Search**: Advanced search capabilities across all sub-modules
- **Stock Management**: Real-time stock tracking with bin-level detail
- **Integration**: Seamless integration with Service Module, Warranty Module, and Masters Module
- **Branch Transfer**: Complete workflow for inter-branch transfers
- **Inventory Reports**: Multiple reports for inventory analysis and management

