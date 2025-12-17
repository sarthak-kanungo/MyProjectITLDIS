## Purchase Order AOP - Detailed Sequence Diagrams

This document describes the **AOP-based post-processing flows** around Purchase Orders in the KUBOTA DMS.  
The `PurchaseOrderAop` aspect hooks into controller methods to:

- Initialize KAI approval workflow when a purchase order is saved.
- Generate multi-level approval chains when a purchase order is approved.

Although the methods are currently commented in the source, these diagrams capture the intended behavior.

---

## 1. Save Purchase Order – Initialize KAI Approval Flow

This flow is triggered **after** `PurchaseOrderController.savePurchaseOrder(..)` successfully returns an HTTP 200 response.  
The aspect inspects the `ApiResponse`, loads the `PurchaseOrder`, and sets up the initial KAI approval state.

```mermaid
sequenceDiagram
    %% Participants
    participant User as Dealer User
    participant UI as Angular Frontend
    participant POController as PurchaseOrderController
    participant POService as PurchaseOrderService
    participant PORepo as PurchaseOrderRepo
    participant AOP as PurchaseOrderAop (@AfterReturning)
    participant SalesPoApprovalRepo as SalesPoApprovalRepository
    participant KubotaEmpRepo as KubotaEmployeeRepository
    participant DB as Database

    %% User submits purchase order
    User->>UI: Fill Purchase Order Form<br/>(dealer, items, values, etc.)
    UI->>POController: POST /api/purchaseOrder/savePurchaseOrder<br/>(PurchaseOrder JSON)

    %% Normal controller/service persistence
    POController->>POService: savePurchaseOrder(purchaseOrderDto)
    POService->>PORepo: save(PurchaseOrder)
    PORepo->>DB: INSERT / UPDATE purchase_order
    DB-->>PORepo: Saved PurchaseOrder (with ID)
    PORepo-->>POService: PurchaseOrder entity
    POService-->>POController: ApiResponse (status=200, id=purchaseOrderId)

    POController-->>UI: ResponseEntity(ApiResponse)<br/>{ status:200, id:purchaseOrderId }

    %% AOP AfterReturning advice triggers
    Note over POController,AOP: @AfterReturning on<br/>savePurchaseOrder(..)
    POController-->>AOP: retVal = ResponseEntity(ApiResponse)

    AOP->>AOP: Cast retVal to ResponseEntity(ApiResponse)<br/>entity = (ResponseEntity(ApiResponse)) retVal
    AOP->>AOP: apiResponse = entity.getBody()

    alt apiResponse.status == HttpStatus.OK.value()
        AOP->>PORepo: getOne(apiResponse.id)
        PORepo->>DB: SELECT * FROM purchase_order<br/>WHERE id = apiResponse.id
        DB-->>PORepo: PurchaseOrder
        PORepo-->>AOP: PurchaseOrder entity

        alt purchaseOrder.poStatus == UNDER_KAI_APPROVAL
            Note over AOP: Initialize first KAI approval step

            AOP->>KubotaEmpRepo: findByHierarchy(SALES_ADMIN)
            KubotaEmpRepo->>DB: SELECT * FROM kubota_employee<br/>WHERE hierarchy = SALES_ADMIN
            DB-->>KubotaEmpRepo: KubotaEmployeeMaster (Sales Admin)
            KubotaEmpRepo-->>AOP: KubotaEmployeeMaster

            AOP->>AOP: Create new SalesPoApproval
            AOP->>AOP: salesPoApproval.setPurchaseOrder(purchaseOrder)
            AOP->>AOP: salesPoApproval.setApprovalStatus(PENDING_AT_SALES_ADMIN)
            AOP->>AOP: salesPoApproval.setDesignationHierarchy(<br/>Sales Admin hierarchy)

            AOP->>SalesPoApprovalRepo: save(salesPoApproval)
            SalesPoApprovalRepo->>DB: INSERT INTO sales_po_approval<br/>(po_id, designation_hierarchy_id,<br/>approval_status, ...)
            DB-->>SalesPoApprovalRepo: Saved SalesPoApproval
            SalesPoApprovalRepo-->>AOP: SalesPoApproval entity

            %% Update purchase order state
            AOP->>AOP: purchaseOrder.setPoStatus(UNDER_KAI_APPROVAL)
            AOP->>AOP: purchaseOrder.setDraftMode(false)
        else purchaseOrder.poStatus != UNDER_KAI_APPROVAL
            Note over AOP: No approval record created<br/>Only persist current PurchaseOrder state
        end

        %% Persist any changes to the PurchaseOrder
        AOP->>PORepo: save(purchaseOrder)
        PORepo->>DB: UPDATE purchase_order<br/>SET po_status=?, draft_mode=? ...<br/>WHERE id = apiResponse.id
        DB-->>PORepo: Updated row
        PORepo-->>AOP: PurchaseOrder (updated)
    else apiResponse.status != 200
        Note over AOP: No changes applied<br/>(non-OK response)
    end

    AOP->>AOP: Log success or exception<br/>logger.info(\"savePurchaseOrder Exception: ...\")
```

---

## 2. Approve Purchase Order – Build Multi-level Approval Chain

This flow is triggered **after** `PurchaseOrderController.approvePurchaseOrder(..)` completes successfully.  
The aspect reads the `PoApproval` argument, loads the `PurchaseOrder` and its dealer, then builds a **multi-level KAI approval hierarchy**.

> Note: In the current source, this block is commented and the `dealerEmployeeMasterRepo` field would need to be autowired for a working implementation. The diagram reflects the intended design.

```mermaid
sequenceDiagram
    %% Participants
    participant User as Approver (Dealer/Kubota)
    participant UI as Angular Frontend
    participant POController as PurchaseOrderController
    participant AOP as PurchaseOrderAop (@AfterReturning)
    participant PORepo as PurchaseOrderRepo
    participant DealerEmpRepo as DealerEmployeeMasterRepo
    participant SalesPoApprovalRepo as SalesPoApprovalRepository
    participant KubotaEmpRepo as KubotaEmployeeRepository
    participant DB as Database

    %% Approver approves the purchase order
    User->>UI: Approve Purchase Order<br/>(PoApproval payload)
    UI->>POController: POST /api/purchaseOrder/approvePurchaseOrder<br/>(PoApproval)

    %% Normal controller logic (simplified)
    POController->>POController: Validate PoApproval<br/>(purchaseOrderId, comments, action)
    POController->>PORepo: findById(purchaseOrderId)
    PORepo->>DB: SELECT * FROM purchase_order WHERE id = ?
    DB-->>PORepo: PurchaseOrder
    PORepo-->>POController: PurchaseOrder entity

    POController->>POController: Apply approval decision<br/>(status, timestamps, etc.)
    POController-->>UI: ResponseEntity(ApiResponse)<br/>{ status:200, message:\"Approved\" }

    %% AOP AfterReturning advice triggers
    Note over POController,AOP: @AfterReturning on<br/>approvePurchaseOrder(..) && args(poApproval,..)
    POController-->>AOP: retVal = ResponseEntity(ApiResponse),<br/>poApproval (method arg)

    AOP->>AOP: Cast retVal to ResponseEntity(ApiResponse)<br/>entity = (ResponseEntity(ApiResponse)) retVal
    AOP->>AOP: apiResponse = entity.getBody()
    AOP->>AOP: Log poApproval.toString()

    %% Load PurchaseOrder and Dealer Employee
    AOP->>PORepo: getOne(poApproval.purchaseOrderId)
    PORepo->>DB: SELECT * FROM purchase_order WHERE id = poApproval.purchaseOrderId
    DB-->>PORepo: PurchaseOrder
    PORepo-->>AOP: PurchaseOrder entity

    AOP->>DealerEmpRepo: getOne(purchaseOrder.dealerEmployeeMaster.id)
    DealerEmpRepo->>DB: SELECT * FROM dealer_employee_master<br/>WHERE id = dealerEmployeeId
    DB-->>DealerEmpRepo: DealerEmployeeMaster
    DealerEmpRepo-->>AOP: DealerEmployeeMaster entity

    AOP->>AOP: Extract dealerId = dealerEmployeeMaster.dealerMaster.id

    %% Fetch approval hierarchy for this dealer
    AOP->>SalesPoApprovalRepo: getApprovalHierarchyLevel(dealerId)
    SalesPoApprovalRepo->>DB: CALL sp_get_approval_hierarchy_level(dealerId)<br/>OR SELECT hierarchy_ids
    DB-->>SalesPoApprovalRepo: List of Long kubotaEmployeeIds
    SalesPoApprovalRepo-->>AOP: List of Long kubotaEmployeeIds

    AOP->>AOP: Initialize approvalList = new ArrayList<>()

    %% For each hierarchy level, create SalesPoApproval
    loop For each kubotaEmployeeId in kubotaEmployeeIds
        AOP->>KubotaEmpRepo: getOne(kubotaEmployeeId)
        KubotaEmpRepo->>DB: SELECT * FROM kubota_employee_master<br/>WHERE id = kubotaEmployeeId
        DB-->>KubotaEmpRepo: KubotaEmployeeMaster
        KubotaEmpRepo-->>AOP: KubotaEmployeeMaster entity

        AOP->>AOP: Create new SalesPoApproval approval
        AOP->>AOP: approval.setKubotaEmployeeMaster(kubotaEmployeeMaster)
        AOP->>AOP: approval.setPurchaseOrder(purchaseOrder)
        AOP->>AOP: approval.setApprovalStatus(<br/>PENDING_AT + kubotaEmployeeMaster.designationHierarchy.hierarchy)

        AOP->>AOP: approvalList.add(approval)
        AOP->>AOP: logger.info(\"Added\")
    end

    %% Persist all approval records in one batch
    AOP->>SalesPoApprovalRepo: saveAll(approvalList)
    SalesPoApprovalRepo->>DB: INSERT INTO sales_po_approval<br/>(po_id, kubota_employee_id,<br/>approval_status, ...) * N
    DB-->>SalesPoApprovalRepo: Saved N approval rows
    SalesPoApprovalRepo-->>AOP: List of SalesPoApproval (saved)

    AOP->>AOP: Log completion or exception<br/>logger.info(\"savePurchaseOrder Exception: ...\")
```

---

## Summary

- The **Save Purchase Order** advice initializes the **first KAI approval step** when a PO enters `UNDER_KAI_APPROVAL`, ensuring:
  - A `SalesPoApproval` is created for **Sales Admin**.
  - The `PurchaseOrder`’s status and `draftMode` flags are updated.

- The **Approve Purchase Order** advice (conceptual design) builds a **multi-level KAI approval chain** based on:
  - Dealer-specific approval hierarchy from `SalesPoApprovalRepository`.
  - Corresponding `KubotaEmployeeMaster` records for each hierarchy level.
  - A batch `saveAll` of `SalesPoApproval` entities representing each approval step.

These AOP flows decouple approval initialization logic from the controllers/services and ensure that **approval metadata is consistently created and maintained** whenever purchase orders are saved or approved.


