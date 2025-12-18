## Spares Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.spares` module:

- **Sales Order Management** (Create, Search, View, Update Sales Orders)
- **Quotation Management** (Create, Search, View Quotations)
- **Invoice Management** (Create, Search, View, Cancel Invoices)
- **Part Requisition/Issue/Return** (Requisition creation, Issue processing, Return handling)
- **Pick List Management** (Pick list creation and management)
- **Purchase Order Management** (PO creation, approval, GRN processing)
- **Inventory Management** (Stock adjustments, Branch transfers, Bin transfers, Stock reports)
- **Reports** (Various inventory and sales reports)

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the Spares module.

---

## 1. Sales Order Creation Flow

This flow shows how **Spare Parts Sales Orders** are created with part details, customer information, and pricing.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as SpareSalesOrderController
    participant Service as SpareSalesOrderService
    participant UserAuth as UserAuthentication
    participant SalesOrderRepo as SpareSalesOrderRepository
    participant StockRepo as StockRepository
    participant DB as Database

    %% Sales Order Creation Flow
    Note over Client,DB: Spare Parts Sales Order Creation
    
    Client->>Controller: POST /api/spares/salesorder/create<br/>(SpareSalesOrder with partDetails)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>Controller: salesOrder.setDealerId(dealerId)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: salesOrder.setCreatedBy(loginId)
    Controller->>Controller: salesOrder.setCreatedDate(new Date())
    
    Controller->>Service: createSalesOrder(salesOrder)
    
    loop For each Part Detail
        Service->>StockRepo: Check stock availability(partNumber, quantity)
        StockRepo-->>Service: stockAvailable
        alt Stock Available
            Service->>Service: Validate pricing and discounts
            Service->>Service: Calculate line totals
        else Stock Not Available
            Service-->>Controller: Error: Insufficient stock
        end
    end
    
    Service->>Service: Calculate order total
    Service->>Service: Generate sales order number
    Service->>SalesOrderRepo: save(salesOrder)
    SalesOrderRepo->>DB: INSERT INTO spare_sales_order
    DB-->>SalesOrderRepo: Order saved
    
    Service-->>Controller: SalesOrder created successfully
    Controller-->>Client: ResponseEntity<SuccessResponse>
```

---

## 2. Quotation Creation Flow

This flow shows how **Spare Parts Quotations** are created for customers with part details and pricing.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as SpareQuotationController
    participant Service as SpareQuotationService
    participant UserAuth as UserAuthentication
    participant QuotationRepo as SpareQuotationRepository
    participant StockRepo as StockRepository
    participant DB as Database

    %% Quotation Creation Flow
    Note over Client,DB: Spare Parts Quotation Creation
    
    Client->>Controller: POST /api/spares/quotation/create<br/>(SpareQuotation with partDetails)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>Controller: quotation.setDealerId(dealerId)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: quotation.setCreatedBy(loginId)
    Controller->>Controller: quotation.setCreatedDate(new Date())
    Controller->>Controller: quotation.setValidUntil(expiryDate)
    
    Controller->>Service: createQuotation(quotation)
    
    loop For each Part Detail
        Service->>StockRepo: Check part availability(partNumber)
        StockRepo-->>Service: partInfo
        Service->>Service: Set quotation price and discount
        Service->>Service: Calculate line totals
    end
    
    Service->>Service: Calculate quotation total
    Service->>Service: Generate quotation number
    Service->>QuotationRepo: save(quotation)
    QuotationRepo->>DB: INSERT INTO spare_quotation
    DB-->>QuotationRepo: Quotation saved
    
    Service-->>Controller: Quotation created successfully
    Controller-->>Client: ResponseEntity<SuccessResponse>
```

---

## 3. Invoice Creation Flow

This flow shows how **Spare Parts Invoices** are created from sales orders with part details, labour charges, and outside charges.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as SparesInvoiceController
    participant UserAuth as UserAuthentication
    participant InvoiceRepo as SparesInvoiceRepo
    participant SalesOrderRepo as SpareSalesOrderRepository
    participant StockRepo as StockRepository
    participant DB as Database

    %% Invoice Creation Flow
    Note over Client,DB: Spare Parts Invoice Creation
    
    Client->>Controller: POST /api/spares/invoice/create<br/>(SparesInvoice with partDetails, labourDetails, outsideChargeDetails)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>Controller: invoice.setDealerId(dealerId)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: invoice.setCreatedBy(loginId)
    Controller->>Controller: invoice.setCreatedDate(new Date())
    Controller->>Controller: invoice.setInvoiceDate(new Date())
    
    alt Invoice from Sales Order
        Controller->>SalesOrderRepo: findById(salesOrderId)
        SalesOrderRepo-->>Controller: salesOrder
        Controller->>Controller: Copy part details from sales order
        Controller->>Controller: Update sales order status to "Invoiced"
    end
    
    loop For each Part Detail
        Controller->>StockRepo: Update stock quantity(partNumber, -quantity)
        StockRepo->>DB: UPDATE stock SET quantity = quantity - ?
        DB-->>StockRepo: Stock updated
    end
    
    Controller->>Controller: Calculate part total
    Controller->>Controller: Calculate labour total
    Controller->>Controller: Calculate outside charges total
    Controller->>Controller: Calculate invoice grand total
    Controller->>Controller: Generate invoice number
    
    Controller->>InvoiceRepo: save(invoice)
    InvoiceRepo->>DB: INSERT INTO spares_invoice
    DB-->>InvoiceRepo: Invoice saved
    
    Controller-->>Client: ResponseEntity<SuccessResponse>
```

---

## 4. Part Requisition Flow

This flow shows how **Spare Part Requisitions** are created, issued, and returned.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant RequisitionController as SparePartRequisitionController
    participant IssueController as SparePartIssueController
    participant ReturnController as SparePartReturnController
    participant UserAuth as UserAuthentication
    participant RequisitionRepo as SparePartRequisitionRepo
    participant IssueRepo as SparePartIssueRepository
    participant ReturnRepo as SparePartReturnRepository
    participant StockRepo as StockRepository
    participant DB as Database

    %% Part Requisition Flow
    Note over Client,DB: Part Requisition Creation
    
    Client->>RequisitionController: POST /api/spares/requisition/create<br/>(SparePartRequisition with items)
    
    RequisitionController->>UserAuth: getDealerId()
    UserAuth-->>RequisitionController: dealerId
    RequisitionController->>UserAuth: getLoginId()
    UserAuth-->>RequisitionController: loginId
    
    RequisitionController->>RequisitionController: requisition.setDealerId(dealerId)
    RequisitionController->>RequisitionController: requisition.setCreatedBy(loginId)
    RequisitionController->>RequisitionController: requisition.setCreatedDate(new Date())
    RequisitionController->>RequisitionController: requisition.setStatus("Pending")
    
    RequisitionController->>RequisitionRepo: save(requisition)
    RequisitionRepo->>DB: INSERT INTO spare_part_requisition
    DB-->>RequisitionRepo: Requisition saved
    
    RequisitionController-->>Client: Requisition created successfully
    
    Note over Client,DB: Part Issue Flow
    
    Client->>IssueController: POST /api/spares/issue/create<br/>(SparePartIssue with requisitionId)
    
    IssueController->>RequisitionRepo: findById(requisitionId)
    RequisitionRepo-->>IssueController: requisition
    
    loop For each Issue Item
        IssueController->>StockRepo: Check stock availability(partNumber, quantity)
        StockRepo-->>IssueController: stockAvailable
        alt Stock Available
            IssueController->>StockRepo: Update stock quantity(partNumber, -quantity)
            StockRepo->>DB: UPDATE stock SET quantity = quantity - ?
            IssueController->>IssueRepo: save(issueItem)
        else Stock Not Available
            IssueController-->>Client: Error: Insufficient stock
        end
    end
    
    IssueController->>RequisitionRepo: Update requisition status to "Issued"
    RequisitionRepo->>DB: UPDATE spare_part_requisition SET status = 'Issued'
    
    IssueController-->>Client: Issue completed successfully
    
    Note over Client,DB: Part Return Flow
    
    Client->>ReturnController: POST /api/spares/return/create<br/>(SparePartReturn with issueId)
    
    ReturnController->>IssueRepo: findById(issueId)
    IssueRepo-->>ReturnController: issue
    
    loop For each Return Item
        ReturnController->>StockRepo: Update stock quantity(partNumber, +quantity)
        StockRepo->>DB: UPDATE stock SET quantity = quantity + ?
        ReturnController->>ReturnRepo: save(returnItem)
    end
    
    ReturnController->>ReturnRepo: save(return)
    ReturnRepo->>DB: INSERT INTO spare_part_return
    
    ReturnController-->>Client: Return completed successfully
```

---

## 5. Pick List Creation Flow

This flow shows how **Pick Lists** are created from sales orders for warehouse picking operations.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as PickListController
    participant UserAuth as UserAuthentication
    participant PickListRepo as PickListRepository
    participant SalesOrderRepo as SpareSalesOrderRepository
    participant StockRepo as StockRepository
    participant DB as Database

    %% Pick List Creation Flow
    Note over Client,DB: Pick List Creation from Sales Order
    
    Client->>Controller: POST /api/spares/picklist/create<br/>(PickList with salesOrderId)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    Controller->>SalesOrderRepo: findById(salesOrderId)
    SalesOrderRepo-->>Controller: salesOrder
    
    Controller->>Controller: pickList.setDealerId(dealerId)
    Controller->>Controller: pickList.setCreatedBy(loginId)
    Controller->>Controller: pickList.setCreatedDate(new Date())
    Controller->>Controller: pickList.setStatus("Pending")
    
    loop For each Sales Order Part Detail
        Controller->>StockRepo: Check stock location(partNumber)
        StockRepo-->>Controller: binLocation, quantity
        Controller->>Controller: Create pickListItem with bin location
        Controller->>Controller: pickListItem.setQuantityToPick(quantity)
    end
    
    Controller->>Controller: Generate pick list number
    Controller->>PickListRepo: save(pickList)
    PickListRepo->>DB: INSERT INTO pick_list
    DB-->>PickListRepo: Pick list saved
    
    Controller->>SalesOrderRepo: Update sales order status to "Pick List Created"
    SalesOrderRepo->>DB: UPDATE spare_sales_order SET status = 'Pick List Created'
    
    Controller-->>Client: ResponseEntity<PickList>
```

---

## 6. Purchase Order Creation and Approval Flow

This flow shows how **Spare Parts Purchase Orders** are created, submitted for approval, and processed.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as SparePurchaseOrderController
    participant Service as SparesPurchaseOrderService
    participant UserAuth as UserAuthentication
    participant PORepo as SparePurchaseOrderRepository
    participant ApprovalRepo as SparePurchaseOrderApprovalRepository
    participant StockRepo as StockRepository
    participant DB as Database

    %% Purchase Order Creation Flow
    Note over Client,DB: Purchase Order Creation
    
    Client->>Controller: POST /api/spares/purchaseorder/create<br/>(SparePurchaseOrder with partDetails)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    Controller->>Service: createPurchaseOrder(purchaseOrder)
    
    Service->>Service: po.setDealerId(dealerId)
    Service->>Service: po.setCreatedBy(loginId)
    Service->>Service: po.setCreatedDate(new Date())
    Service->>Service: po.setStatus("Draft")
    
    loop For each Part Detail
        Service->>StockRepo: Check current stock level(partNumber)
        StockRepo-->>Service: currentStock
        Service->>Service: Calculate reorder quantity
        Service->>Service: Set unit price and total
    end
    
    Service->>Service: Calculate PO total
    Service->>Service: Generate PO number
    Service->>PORepo: save(purchaseOrder)
    PORepo->>DB: INSERT INTO spare_purchase_order
    DB-->>PORepo: PO saved
    
    Service-->>Controller: PurchaseOrder created
    Controller-->>Client: ResponseEntity<SuccessResponse>
    
    Note over Client,DB: Purchase Order Approval Flow
    
    Client->>Controller: POST /api/spares/purchaseorder/approve<br/>(poId, approvalStatus)
    
    Controller->>PORepo: findById(poId)
    PORepo-->>Controller: purchaseOrder
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: approverId
    
    Controller->>ApprovalRepo: Create approval record
    ApprovalRepo->>DB: INSERT INTO spare_po_approval
    
    alt Approval Status = Approved
        Controller->>PORepo: Update PO status to "Approved"
        PORepo->>DB: UPDATE spare_purchase_order SET status = 'Approved'
        Controller->>Controller: Send notification to procurement team
    else Approval Status = Rejected
        Controller->>PORepo: Update PO status to "Rejected"
        PORepo->>DB: UPDATE spare_purchase_order SET status = 'Rejected'
        Controller->>Controller: Send notification to creator
    end
    
    Controller-->>Client: Approval processed successfully
```

---

## 7. GRN (Goods Receipt Note) Processing Flow

This flow shows how **GRN** is processed when goods are received against a purchase order.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as GRNController
    participant UserAuth as UserAuthentication
    participant GRNRepo as GRNRepository
    participant PORepo as SparePurchaseOrderRepository
    participant StockRepo as StockRepository
    participant DB as Database

    %% GRN Processing Flow
    Note over Client,DB: GRN Processing from Purchase Order
    
    Client->>Controller: POST /api/spares/grn/create<br/>(GRN with purchaseOrderId, receivedItems)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    Controller->>PORepo: findById(purchaseOrderId)
    PORepo-->>Controller: purchaseOrder
    
    Controller->>Controller: grn.setDealerId(dealerId)
    Controller->>Controller: grn.setCreatedBy(loginId)
    Controller->>Controller: grn.setCreatedDate(new Date())
    Controller->>Controller: grn.setReceivedDate(new Date())
    Controller->>Controller: grn.setStatus("Received")
    
    loop For each Received Item
        Controller->>Controller: Validate received quantity <= ordered quantity
        alt Quantity Valid
            Controller->>StockRepo: Update stock quantity(partNumber, +receivedQuantity)
            StockRepo->>DB: UPDATE stock SET quantity = quantity + ?
            Controller->>Controller: Create GRN item detail
        else Quantity Invalid
            Controller-->>Client: Error: Received quantity exceeds ordered quantity
        end
    end
    
    Controller->>GRNRepo: save(grn)
    GRNRepo->>DB: INSERT INTO grn
    DB-->>GRNRepo: GRN saved
    
    Controller->>PORepo: Update PO status to "Received"
    PORepo->>DB: UPDATE spare_purchase_order SET status = 'Received'
    
    Controller-->>Client: GRN processed successfully
```

---

## 8. Stock Adjustment Flow

This flow shows how **Stock Adjustments** are made to correct inventory discrepancies.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as StockAdjustmentController
    participant UserAuth as UserAuthentication
    participant AdjustmentRepo as StockAdjustmentRepository
    participant StockRepo as StockRepository
    participant DB as Database

    %% Stock Adjustment Flow
    Note over Client,DB: Stock Adjustment Processing
    
    Client->>Controller: POST /api/spares/stockadjustment/create<br/>(StockAdjustment with adjustmentItems)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    Controller->>Controller: adjustment.setDealerId(dealerId)
    Controller->>Controller: adjustment.setCreatedBy(loginId)
    Controller->>Controller: adjustment.setCreatedDate(new Date())
    Controller->>Controller: adjustment.setAdjustmentDate(new Date())
    Controller->>Controller: adjustment.setReason(reason)
    
    loop For each Adjustment Item
        Controller->>StockRepo: Get current stock(partNumber)
        StockRepo-->>Controller: currentStock
        
        Controller->>Controller: Calculate adjustment difference
        alt Adjustment Type = Increase
            Controller->>StockRepo: Update stock quantity(partNumber, +adjustmentQuantity)
            StockRepo->>DB: UPDATE stock SET quantity = quantity + ?
        else Adjustment Type = Decrease
            Controller->>StockRepo: Update stock quantity(partNumber, -adjustmentQuantity)
            StockRepo->>DB: UPDATE stock SET quantity = quantity - ?
        end
        
        Controller->>Controller: Create adjustment item record
    end
    
    Controller->>AdjustmentRepo: save(adjustment)
    AdjustmentRepo->>DB: INSERT INTO stock_adjustment
    DB-->>AdjustmentRepo: Adjustment saved
    
    Controller-->>Client: Stock adjustment completed successfully
```

---

## 9. Branch Transfer Flow

This flow shows how **Branch Transfers** are processed (Indent → Issue → Receipt).

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant IndentController as BranchTransferIndentController
    participant IssueController as BranchTransferIssueController
    participant ReceiptController as BranchTransferReceiptController
    participant UserAuth as UserAuthentication
    participant IndentRepo as BranchTransferIndentRepository
    participant IssueRepo as BranchTransferIssueRepository
    participant ReceiptRepo as BranchTransferReceiptRepository
    participant StockRepo as StockRepository
    participant DB as Database

    %% Branch Transfer Indent Flow
    Note over Client,DB: Branch Transfer Indent Creation
    
    Client->>IndentController: POST /api/spares/branchtransfer/indent/create<br/>(BranchTransferIndent with items)
    
    IndentController->>UserAuth: getDealerId()
    UserAuth-->>IndentController: dealerId
    IndentController->>UserAuth: getLoginId()
    UserAuth-->>IndentController: loginId
    
    IndentController->>IndentController: indent.setFromBranch(fromBranch)
    IndentController->>IndentController: indent.setToBranch(toBranch)
    IndentController->>IndentController: indent.setStatus("Pending")
    
    IndentController->>IndentRepo: save(indent)
    IndentRepo->>DB: INSERT INTO branch_transfer_indent
    DB-->>IndentRepo: Indent saved
    
    IndentController-->>Client: Indent created successfully
    
    Note over Client,DB: Branch Transfer Issue Flow
    
    Client->>IssueController: POST /api/spares/branchtransfer/issue/create<br/>(BranchTransferIssue with indentId)
    
    IssueController->>IndentRepo: findById(indentId)
    IndentRepo-->>IssueController: indent
    
    loop For each Transfer Item
        IssueController->>StockRepo: Check stock at source branch(partNumber, fromBranch)
        StockRepo-->>IssueController: stockAvailable
        alt Stock Available
            IssueController->>StockRepo: Update source branch stock(partNumber, fromBranch, -quantity)
            StockRepo->>DB: UPDATE stock SET quantity = quantity - ? WHERE branch = fromBranch
            IssueController->>IssueRepo: save(issueItem)
        else Stock Not Available
            IssueController-->>Client: Error: Insufficient stock at source branch
        end
    end
    
    IssueController->>IndentRepo: Update indent status to "Issued"
    IndentRepo->>DB: UPDATE branch_transfer_indent SET status = 'Issued'
    
    IssueController-->>Client: Issue completed successfully
    
    Note over Client,DB: Branch Transfer Receipt Flow
    
    Client->>ReceiptController: POST /api/spares/branchtransfer/receipt/create<br/>(BranchTransferReceipt with issueId)
    
    ReceiptController->>IssueRepo: findById(issueId)
    IssueRepo-->>ReceiptController: issue
    
    loop For each Received Item
        ReceiptController->>StockRepo: Update destination branch stock(partNumber, toBranch, +quantity)
        StockRepo->>DB: UPDATE stock SET quantity = quantity + ? WHERE branch = toBranch
        ReceiptController->>ReceiptRepo: save(receiptItem)
    end
    
    ReceiptController->>ReceiptRepo: save(receipt)
    ReceiptRepo->>DB: INSERT INTO branch_transfer_receipt
    
    ReceiptController->>IndentRepo: Update indent status to "Received"
    IndentRepo->>DB: UPDATE branch_transfer_indent SET status = 'Received'
    
    ReceiptController-->>Client: Receipt completed successfully
```

---

## 10. Invoice Cancellation Flow

This flow shows how **Spare Parts Invoices** are cancelled and stock is restored.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as SparesInvoiceController
    participant UserAuth as UserAuthentication
    participant InvoiceRepo as SparesInvoiceRepo
    participant StockRepo as StockRepository
    participant DB as Database

    %% Invoice Cancellation Flow
    Note over Client,DB: Invoice Cancellation and Stock Restoration
    
    Client->>Controller: POST /api/spares/invoice/cancel<br/>(InvoiceCancellationDto with invoiceId, reason)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    Controller->>InvoiceRepo: findById(invoiceId)
    InvoiceRepo-->>Controller: invoice
    
    Controller->>Controller: Validate invoice status (must be active)
    alt Invoice Can Be Cancelled
        Controller->>Controller: invoice.setStatus("Cancelled")
        Controller->>Controller: invoice.setCancelledBy(loginId)
        Controller->>Controller: invoice.setCancelledDate(new Date())
        Controller->>Controller: invoice.setCancellationReason(reason)
        
        loop For each Invoice Part Detail
            Controller->>StockRepo: Restore stock quantity(partNumber, +quantity)
            StockRepo->>DB: UPDATE stock SET quantity = quantity + ?
            DB-->>StockRepo: Stock restored
        end
        
        Controller->>InvoiceRepo: save(invoice)
        InvoiceRepo->>DB: UPDATE spares_invoice SET status = 'Cancelled'
        DB-->>InvoiceRepo: Invoice updated
        
        Controller-->>Client: Invoice cancelled successfully
    else Invoice Cannot Be Cancelled
        Controller-->>Client: Error: Invoice cannot be cancelled (already cancelled or invalid status)
    end
```

---

## 11. Sales Order Search Flow

This flow shows how **Spare Parts Sales Orders** are searched with various filters.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as SpareSalesOrderController
    participant Service as SpareSalesOrderService
    participant UserAuth as UserAuthentication
    participant SalesOrderRepo as SpareSalesOrderRepository
    participant DB as Database

    Client->>Controller: POST /api/spares/salesorder/search SpareSearchSalesOrderDto with filters
    
    Controller->>UserAuth: getDealerId
    UserAuth-->>Controller: dealerId
    
    Controller->>Service: searchSalesOrders searchDto dealerId
    
    Service->>Service: Build query with filters orderNumber customerName dateRange status partNumber
    
    Service->>SalesOrderRepo: findByFilters searchDto dealerId
    SalesOrderRepo->>DB: SELECT FROM spare_sales_order WHERE dealer_id AND filters
    DB-->>SalesOrderRepo: List SpareSalesOrder
    
    Service->>Service: Map entities to DTOs
    Service->>Service: Calculate totals and status
    
    Service-->>Controller: List SpareSaleOrderResponseDto
    Controller-->>Client: ResponseEntity List SpareSaleOrderResponseDto
```

---

## 12. Reports Generation Flow

This flow shows how various **Spare Parts Reports** are generated (Inventory Movement, Closing Stock, Non-Moving Parts, etc.).

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as SpareReportsController
    participant UserAuth as UserAuthentication
    participant StockRepo as StockRepository
    participant MovementRepo as InventoryMovementRepository
    participant DB as Database

    Client->>Controller: POST /api/spares/reports/inventorymovement ReportSearchDto with dateRange filters
    
    Controller->>UserAuth: getDealerId
    UserAuth-->>Controller: dealerId
    
    Controller->>Controller: Validate date range
    
    alt Report Type Inventory Movement
        Controller->>MovementRepo: findInventoryMovements dateRange dealerId
        MovementRepo->>DB: SELECT FROM inventory_movement WHERE dealer_id AND date BETWEEN
        DB-->>MovementRepo: List InventoryMovement
        Controller->>Controller: Map to InventoryMovementDto
        Controller->>Controller: Calculate movement totals
        
    else Report Type Closing Stock
        Controller->>StockRepo: findClosingStock dateRange dealerId
        StockRepo->>DB: SELECT part_number SUM quantity as closing_stock FROM stock WHERE dealer_id AND date GROUP BY part_number
        DB-->>StockRepo: List ClosingStock
        Controller->>Controller: Map to ClosingStockReportDto
        
    else Report Type Non-Moving Parts
        Controller->>StockRepo: findNonMovingParts thresholdDays dealerId
        StockRepo->>DB: SELECT FROM stock WHERE dealer_id AND last_movement_date DATE_SUB NOW INTERVAL DAY
        DB-->>StockRepo: List NonMovingStock
        Controller->>Controller: Map to NonMovingPartsStockReportDto
        Controller->>Controller: Calculate stock value
    end
    
    Controller-->>Client: ResponseEntity List ReportDto
```

---

## Summary

The Spares module handles the complete lifecycle of spare parts management:

1. **Sales Process**: Quotation → Sales Order → Pick List → Invoice
2. **Procurement Process**: Purchase Order → Approval → GRN → Stock Update
3. **Inventory Management**: Stock Adjustments, Branch Transfers, Bin Transfers
4. **Part Management**: Requisition → Issue → Return
5. **Reporting**: Various inventory and sales reports

All flows follow a consistent pattern:
- Authentication and authorization (dealerId, loginId)
- Validation of business rules
- Database persistence
- Stock updates where applicable
- Status tracking throughout the lifecycle

