## Warranty Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.warranty` module:

- **PCR (Product Concern Report) Creation and Approval Flow** - PCR submission with approval hierarchy
- **WCR (Warranty Claim Request) Creation Flow** - Warranty claim request from PCR/Goodwill
- **Goodwill Request Flow** - Goodwill request creation and approval
- **Logsheet Creation Flow** - Warranty logsheet creation and management
- **Delivery Challan Creation Flow** - Warranty parts delivery challan creation
- **Hotline Report Submission Flow** - Hotline report creation with attachments
- **KAI Inspection Sheet Flow** - KAI inspection sheet creation
- **Retrofitment Campaign Flow** - Retrofitment campaign management

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the Warranty module.

---

## 1. PCR (Product Concern Report) Creation and Submission Flow

This flow shows how **PCR (Product Concern Report)** is created from a Service Job Card, with photo/video attachments, and approval hierarchy setup.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyPcrController
    participant Service as WarrantyPcrService
    participant UserAuth as UserAuthentication
    participant PcrRepo as WarrantyPcrRepo
    participant ApprovalRepo as WarrantyPcrApprovalRepository
    participant DesignationRepo as DesignationHierarchyRepository
    participant SparePartRepo as SparePartRequisitionItemRepo
    participant StorageService as StorageService
    participant PhotosRepo as WarrantyPcrPhotosRepo
    participant DB as Database

    Note over Client,DB: PCR Creation and Submission Flow
    
    Client->>Controller: POST /api/warranty/pcr/saveWarrantyPcr<br/>(WarrantyPcr, List<MultipartFile>)
    
    Controller->>Service: saveWarrantyPcr(warrantyPcr, multipartFileList)
    
    Service->>UserAuth: getBranchId()
    UserAuth-->>Service: branchId
    Service->>Service: warrantyPcr.setBranchId(branchId)
    
    Service->>UserAuth: getLoginId()
    UserAuth-->>Service: loginId
    Service->>Service: warrantyPcr.setCreatedBy(loginId)
    Service->>Service: warrantyPcr.setCreatedOn(new Date())
    
    Service->>Service: Check draftFlag
    alt Draft Flag = true
        Service->>Service: warrantyPcr.setStatus("Draft")
    else Draft Flag = false
        Service->>Service: warrantyPcr.setStatus("Open")
    end
    
    Service->>PcrRepo: save(warrantyPcr)
    PcrRepo->>DB: INSERT INTO warranty_pcr
    DB-->>PcrRepo: PCR saved with ID
    PcrRepo-->>Service: Saved WarrantyPcr entity
    
    alt Status = "Open"
        Service->>ApprovalRepo: getWarrantyPcrApprovalHierarchyLevel(dealerId)
        ApprovalRepo->>DesignationRepo: Query approval hierarchy
        DesignationRepo->>DB: SELECT approver_level_seq, designation_level_id,<br/>grp_seq_no, isFinalApprovalStatus, approvalStatus<br/>FROM designation_hierarchy WHERE dealer_id = dealerId
        DB-->>DesignationRepo: Approval hierarchy data
        DesignationRepo-->>ApprovalRepo: List of hierarchy levels
        ApprovalRepo-->>Service: List of designationHierarchy maps
        
        loop For each hierarchy level
            Service->>Service: Create WarrantyPcrApproval object
            Service->>Service: approval.setWarrantyPcrId(pcrId)
            Service->>Service: approval.setApproverLevelSeq(levelSeq)
            Service->>Service: approval.setDesignationLevelId(designationLevelId)
            Service->>Service: approval.setGrpSeqNo(grpSeqNo)
            Service->>Service: approval.setIsfinalapprovalstatus(isFinal)
            Service->>Service: approval.setApprovalStatus("Pending")
            Service->>Service: approval.setRejectedFlag('N')
        end
        
        Service->>ApprovalRepo: saveAll(warrantyPcrApprovals)
        ApprovalRepo->>DB: INSERT INTO warranty_pcr_approval (multiple rows)
        DB-->>ApprovalRepo: Approval records saved
        ApprovalRepo-->>Service: List of saved approvals
    end
    
    alt multipartFileList != null and size > 0
        loop For each file (max 5 files)
            Service->>Service: Generate photoName = "PCR" + timestamp + "_" + filename
            Service->>StorageService: store(file, photoName)
            StorageService->>StorageService: Save file to storage
            StorageService-->>Service: File stored successfully
            
            Service->>Service: Create WarrantyPcrPhotos object
            Service->>Service: photos.setFileName(photoName)
            Service->>Service: photos.setWarrantyPcr(warrantyPcr)
            Service->>PhotosRepo: save(photos)
            PhotosRepo->>DB: INSERT INTO warranty_pcr_photos
            DB-->>PhotosRepo: Photo record saved
            PhotosRepo-->>Service: Saved WarrantyPcrPhotos
        end
    end
    
    Service->>Service: Update spare part requisition items with PCR quantities
    loop For each SparePartRequisitionItem
        Service->>SparePartRepo: updatePartPcrQty(pcrQuantity, pcrId, failureCodeId, itemId)
        SparePartRepo->>DB: UPDATE spare_part_requisition_item<br/>SET pcr_quantity = pcrQuantity<br/>WHERE id = itemId AND warranty_pcr_id = pcrId
        DB-->>SparePartRepo: Updated
        SparePartRepo-->>Service: Update successful
    end
    
    Service-->>Controller: ApiResponse with success message
    Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"PCR Submitted Successfully"
```

---

## 2. PCR Approval Flow

This flow shows how **PCR approvals** are processed through the approval hierarchy.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyPcrController
    participant Service as WarrantyPcrService
    participant UserAuth as UserAuthentication
    participant ApprovalRepo as WarrantyPcrApprovalRepository
    participant PcrRepo as WarrantyPcrRepo
    participant DB as Database

    Note over Client,DB: PCR Approval Flow
    
    Client->>Controller: POST /api/warranty/pcr/approveWarrantyPcr<br/>(PcrApprovalDto: id, remarks, approvalStatus)
    
    Controller->>Service: approvedPcr(pcrApprovalDto)
    
    Service->>UserAuth: getKubotaEmployeeId()
    UserAuth-->>Service: kubotaEmployeeId
    
    Service->>UserAuth: getUsername()
    UserAuth-->>Service: username
    
    Service->>ApprovalRepo: Find current approval record by pcrId and username
    ApprovalRepo->>DB: SELECT * FROM warranty_pcr_approval<br/>WHERE warranty_pcr_id = pcrId<br/>AND approver_username = username<br/>AND approved_date IS NULL
    DB-->>ApprovalRepo: Current approval record
    ApprovalRepo-->>Service: WarrantyPcrApproval
    
    alt approvalStatus = "Approved"
        Service->>Service: approval.setApprovedDate(new Date())
        Service->>Service: approval.setApprovedBy(kubotaEmployeeId)
        Service->>Service: approval.setRemark(remarks)
        Service->>Service: approval.setApprovalStatus("Approved")
        
        Service->>ApprovalRepo: save(approval)
        ApprovalRepo->>DB: UPDATE warranty_pcr_approval<br/>SET approved_date = NOW(),<br/>approved_by = kubotaEmployeeId,<br/>remark = remarks,<br/>approval_status = 'Approved'<br/>WHERE id = approvalId
        DB-->>ApprovalRepo: Updated
        ApprovalRepo-->>Service: Saved approval
        
        Service->>ApprovalRepo: Check if this is final approval
        ApprovalRepo->>DB: SELECT isfinalapprovalstatus FROM warranty_pcr_approval<br/>WHERE id = approvalId
        DB-->>ApprovalRepo: isFinalApprovalStatus = 'Y' or 'N'
        ApprovalRepo-->>Service: isFinal flag
        
        alt isFinalApprovalStatus = 'Y'
            Service->>PcrRepo: updatePcrStatus(loginId, pcrId, "Approved")
            PcrRepo->>DB: UPDATE warranty_pcr<br/>SET status = 'Approved',<br/>last_modified_by = loginId,<br/>last_modified_date = NOW()<br/>WHERE id = pcrId
            DB-->>PcrRepo: Updated
            PcrRepo-->>Service: Status updated
        else Not final approval
            Service->>ApprovalRepo: Get next approval level
            ApprovalRepo->>DB: SELECT * FROM warranty_pcr_approval<br/>WHERE warranty_pcr_id = pcrId<br/>AND approver_level_seq = currentLevel + 1<br/>AND approved_date IS NULL
            DB-->>ApprovalRepo: Next approval record
            ApprovalRepo-->>Service: Next approval (if exists)
        end
        
    else approvalStatus = "Rejected"
        Service->>Service: approval.setApprovedDate(new Date())
        Service->>Service: approval.setApprovedBy(kubotaEmployeeId)
        Service->>Service: approval.setRemark(remarks)
        Service->>Service: approval.setApprovalStatus("Rejected")
        Service->>Service: approval.setRejectedFlag('Y')
        
        Service->>ApprovalRepo: save(approval)
        ApprovalRepo->>DB: UPDATE warranty_pcr_approval<br/>SET approved_date = NOW(),<br/>approved_by = kubotaEmployeeId,<br/>remark = remarks,<br/>approval_status = 'Rejected',<br/>rejected_flag = 'Y'<br/>WHERE id = approvalId
        DB-->>ApprovalRepo: Updated
        
        Service->>PcrRepo: updatePcrStatus(loginId, pcrId, "Rejected")
        PcrRepo->>DB: UPDATE warranty_pcr<br/>SET status = 'Rejected',<br/>last_modified_by = loginId,<br/>last_modified_date = NOW()<br/>WHERE id = pcrId
        DB-->>PcrRepo: Updated
        PcrRepo-->>Service: Status updated
        
        Service->>ApprovalRepo: Mark all pending approvals as auto-closed
        ApprovalRepo->>DB: UPDATE warranty_pcr_approval<br/>SET approved_date = NOW(),<br/>remark = 'Auto Close',<br/>approval_status = 'Auto Closed'<br/>WHERE warranty_pcr_id = pcrId<br/>AND approved_date IS NULL
        DB-->>ApprovalRepo: Updated
    end
    
    Service-->>Controller: Success message
    Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"PCR Approved/Rejected Successfully"
```

---

## 3. WCR (Warranty Claim Request) Creation Flow

This flow shows how **WCR (Warranty Claim Request)** is created from PCR or Goodwill, with approval hierarchy setup.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyWcrController
    participant UserAuth as UserAuthentication
    participant WcrRepo as WarrantyWcrRepo
    participant ApprovalRepo as WarrantyWcrApprovalRepo
    participant SparePartRepo as SparePartRequisitionItemRepo
    participant PcrRepo as WarrantyPcrRepo
    participant GoodwillRepo as WarrantyGoodwillRepo
    participant DB as Database

    Note over Client,DB: WCR Creation Flow
    
    Client->>Controller: POST /api/warranty/Wcr/saveWcr<br/>(WarrantyWcr)
    
    Controller->>UserAuth: getBranchId()
    UserAuth-->>Controller: branchId
    Controller->>Controller: warrantyWcr.setBranchId(branchId)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: warrantyWcr.setCreatedBy(loginId)
    Controller->>Controller: warrantyWcr.setCreatedOn(new Date())
    Controller->>Controller: warrantyWcr.setWcrDate(new Date())
    
    alt warrantyWcr.getId() != null (Update)
        Controller->>Controller: warrantyWcr.setModifiedBy(loginId)
        Controller->>Controller: warrantyWcr.setModifiedOn(new Date())
    end
    
    Controller->>Controller: warrantyWcr.setWcrStatus("Submitted")
    
    alt warrantyWcr.getWarrantyPcr() == null
        Controller->>Controller: warrantyWcr.setWcrType("GOODWILL")
    else warrantyWcr.getWarrantyPcr() != null
        Controller->>Controller: warrantyWcr.setWcrType("PCR")
    end
    
    Controller->>WcrRepo: save(warrantyWcr)
    WcrRepo->>DB: INSERT INTO warranty_wcr
    DB-->>WcrRepo: WCR saved with ID
    WcrRepo-->>Controller: Saved WarrantyWcr entity
    
    alt WCR Type = "GOODWILL"
        alt SparePartRequisitionItem exists
            loop For each sparePartRequisitionItem
                Controller->>SparePartRepo: updateWarrantyClaimPartGoodwill(wcrId, claimApprovedAmount,<br/>claimApprovedQuantity, itemId)
                SparePartRepo->>DB: UPDATE spare_part_requisition_item<br/>SET warranty_claim_id = wcrId,<br/>claim_approved_amount = amount,<br/>claim_approved_quantity = qty<br/>WHERE id = itemId AND type = 'GOODWILL'
                DB-->>SparePartRepo: Updated
            end
        end
        
        alt LabourCharge exists
            loop For each labourItem
                Controller->>SparePartRepo: updateWarrantyClaimLabourGoodwill(wcrId, claimApprovedAmount, itemId)
                SparePartRepo->>DB: UPDATE labour_charge<br/>SET warranty_claim_id = wcrId,<br/>claim_approved_amount = amount<br/>WHERE id = itemId AND type = 'GOODWILL'
                DB-->>SparePartRepo: Updated
            end
        end
        
        alt OutsideJobCharge exists
            loop For each outsideChargeItem
                Controller->>SparePartRepo: updateWarrantyClaimOutsideChargeGoodwill(wcrId, claimApprovedAmount, itemId)
                SparePartRepo->>DB: UPDATE outside_job_charge<br/>SET warranty_claim_id = wcrId,<br/>claim_approved_amount = amount<br/>WHERE id = itemId AND type = 'GOODWILL'
                DB-->>SparePartRepo: Updated
            end
        end
        
    else WCR Type = "PCR"
        alt SparePartRequisitionItem exists
            loop For each sparePartRequisitionItem
                Controller->>SparePartRepo: updateWarrantyClaimPartPcr(wcrId, claimApprovedAmount,<br/>claimApprovedQuantity, itemId)
                SparePartRepo->>DB: UPDATE spare_part_requisition_item<br/>SET warranty_claim_id = wcrId,<br/>claim_approved_amount = amount,<br/>claim_approved_quantity = qty<br/>WHERE id = itemId AND type = 'PCR'
                DB-->>SparePartRepo: Updated
            end
        end
        
        alt LabourCharge exists
            loop For each labourItem
                Controller->>SparePartRepo: updateWarrantyClaimLabourPcr(wcrId, claimApprovedAmount, itemId)
                SparePartRepo->>DB: UPDATE labour_charge<br/>SET warranty_claim_id = wcrId,<br/>claim_approved_amount = amount<br/>WHERE id = itemId AND type = 'PCR'
                DB-->>SparePartRepo: Updated
            end
        end
        
        alt OutsideJobCharge exists
            loop For each outsideChargeItem
                Controller->>SparePartRepo: updateWarrantyClaimOutsideChargePcr(wcrId, claimApprovedAmount, itemId)
                SparePartRepo->>DB: UPDATE outside_job_charge<br/>SET warranty_claim_id = wcrId,<br/>claim_approved_amount = amount<br/>WHERE id = itemId AND type = 'PCR'
                DB-->>SparePartRepo: Updated
            end
        end
    end
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    
    Controller->>ApprovalRepo: getWarrantyWcrApprovalHierarchyLevel(dealerId)
    ApprovalRepo->>DB: SELECT approver_level_seq, designation_level_id,<br/>grp_seq_no, isFinalApprovalStatus, approvalStatus<br/>FROM designation_hierarchy WHERE dealer_id = dealerId
    DB-->>ApprovalRepo: Approval hierarchy data
    ApprovalRepo-->>Controller: List of hierarchy levels
    
    loop For each hierarchy level
        Controller->>Controller: Create WarrantyWcrApproval object
        Controller->>Controller: approval.setWarrantyWcrId(wcrId)
        Controller->>Controller: approval.setApproverLevelSeq(levelSeq)
        Controller->>Controller: approval.setDesignationLevelId(designationLevelId)
        Controller->>Controller: approval.setGrpSeqNo(grpSeqNo)
        Controller->>Controller: approval.setIsfinalapprovalstatus(isFinal)
        Controller->>Controller: approval.setApprovalStatus("Pending")
        Controller->>Controller: approval.setRejectedFlag('N')
    end
    
    Controller->>ApprovalRepo: saveAll(warrantyWcrApprovals)
    ApprovalRepo->>DB: INSERT INTO warranty_wcr_approval (multiple rows)
    DB-->>ApprovalRepo: Approval records saved
    ApprovalRepo-->>Controller: List of saved approvals
    
    Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"WCR submitted successfully"
```

---

## 4. WCR from PCR/Goodwill Claim Flow

This flow shows how **WCR claim data** is retrieved from PCR or Goodwill for claim creation.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyWcrController
    participant PcrRepo as WarrantyPcrRepo
    participant GoodwillRepo as WarrantyGoodwillRepo
    participant WcrRepo as WarrantyWcrRepo
    participant DB as Database

    Note over Client,DB: WCR Claim Data Retrieval Flow
    
    alt PCR Warranty Claim
        Client->>Controller: GET /api/warranty/Wcr/pcrWarrantyClaim<br/>?pcrNo=PCR001&id=123
        
        Controller->>PcrRepo: findByPcrNoAndId(pcrNo, id)
        PcrRepo->>DB: SELECT * FROM warranty_pcr_view<br/>WHERE pcr_no = pcrNo AND id = id
        DB-->>PcrRepo: PcrWarrantyClaimDto
        PcrRepo-->>Controller: claimDto
        
        Controller->>PcrRepo: getDealerDetails(branchId)
        PcrRepo->>DB: SELECT email_id, dealer_code, mobile_no<br/>FROM dealer_master WHERE branch_id = branchId
        DB-->>PcrRepo: Dealer details map
        PcrRepo-->>Controller: map with email_id, dealer_code, mobile_no
        
        Controller->>Controller: claimDto.setEmailId(map.get("email_id"))
        Controller->>Controller: claimDto.setDealerCode(map.get("dealer_code"))
        Controller->>Controller: claimDto.setMobileNumber(map.get("mobile_no"))
        
        Controller->>WcrRepo: getJobCardPartWarrantyInfo(pcrNo, "PCR")
        WcrRepo->>DB: SELECT part details, warranty info<br/>FROM spare_part_requisition_item<br/>WHERE pcr_no = pcrNo AND type = 'PCR'
        DB-->>WcrRepo: Warranty part list
        WcrRepo-->>Controller: warrantyPart list
        
        Controller->>WcrRepo: getLabourCharge(pcrNo, "PCR")
        WcrRepo->>DB: SELECT labour charge details<br/>FROM labour_charge<br/>WHERE pcr_no = pcrNo AND type = 'PCR'
        DB-->>WcrRepo: Labour charge list
        WcrRepo-->>Controller: labourCharge list
        
        Controller->>WcrRepo: getOutSideLabourCharge(pcrNo, "PCR")
        WcrRepo->>DB: SELECT outside labour charge details<br/>FROM outside_job_charge<br/>WHERE pcr_no = pcrNo AND type = 'PCR'
        DB-->>WcrRepo: Outside labour charge list
        WcrRepo-->>Controller: outSideLabourCharge list
        
        Controller->>Controller: wcrWarrantyDto.setPcrWarrantyClaimDto(claimDto)
        Controller->>Controller: wcrWarrantyDto.setWarrantyPart(warrantyPart)
        Controller->>Controller: wcrWarrantyDto.setLabourCharge(labourCharge)
        Controller->>Controller: wcrWarrantyDto.setOutSideLabourCharge(outSideLabourCharge)
        
        Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>with WcrWarrantyDto
        
    else Goodwill Warranty Claim
        Client->>Controller: GET /api/warranty/Wcr/goodwillWarrantyClaim<br/>?goodwillNo=GW001&id=456
        
        Controller->>GoodwillRepo: findByGoodwillNo(goodwillNo)
        GoodwillRepo->>DB: SELECT * FROM warranty_goodwill_view<br/>WHERE goodwill_no = goodwillNo
        DB-->>GoodwillRepo: GoodwillViewDto
        GoodwillRepo-->>Controller: claimDto
        
        Controller->>PcrRepo: getDealerDetails(claimDto.getBranchId())
        PcrRepo->>DB: SELECT email_id, dealer_code, mobile_no<br/>FROM dealer_master WHERE branch_id = branchId
        DB-->>PcrRepo: Dealer details map
        PcrRepo-->>Controller: map
        
        Controller->>Controller: claimDto.setEmailId(map.get("email_id"))
        Controller->>Controller: claimDto.setDealerCode(map.get("dealer_code"))
        Controller->>Controller: claimDto.setMobileNumber(map.get("mobile_no"))
        
        Controller->>WcrRepo: getJobCardPartWarrantyInfo(pcrNo, "GOODWILL")
        WcrRepo->>DB: SELECT part details, warranty info<br/>FROM spare_part_requisition_item<br/>WHERE pcr_no = pcrNo AND type = 'GOODWILL'
        DB-->>WcrRepo: Warranty part list
        WcrRepo-->>Controller: warrantyPart list
        
        Controller->>WcrRepo: getLabourCharge(pcrNo, "GOODWILL")
        WcrRepo->>DB: SELECT labour charge details<br/>FROM labour_charge<br/>WHERE pcr_no = pcrNo AND type = 'GOODWILL'
        DB-->>WcrRepo: Labour charge list
        WcrRepo-->>Controller: labourCharge list
        
        Controller->>WcrRepo: getOutSideLabourCharge(pcrNo, "GOODWILL")
        WcrRepo->>DB: SELECT outside labour charge details<br/>FROM outside_job_charge<br/>WHERE pcr_no = pcrNo AND type = 'GOODWILL'
        DB-->>WcrRepo: Outside labour charge list
        WcrRepo-->>Controller: outSideLabourCharge list
        
        Controller->>Controller: wcrWarrantyDto.setGoodwillViewDto(claimDto)
        Controller->>Controller: wcrWarrantyDto.setWarrantyPart(warrantyPart)
        Controller->>Controller: wcrWarrantyDto.setLabourCharge(labourCharge)
        Controller->>Controller: wcrWarrantyDto.setOutSideLabourCharge(outSideLabourCharge)
        
        Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>with WcrWarrantyDto
    end
```

---

## 5. Goodwill Request Creation Flow

This flow shows how **Goodwill requests** are created from PCR with approval hierarchy setup.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyGoodWillController
    participant Service as GoodwillService
    participant UserAuth as UserAuthentication
    participant GoodwillRepo as WarrantyGoodwillRepo
    participant ApprovalRepo as WarrantyGoodwillApprovalRepo
    participant StorageService as StorageService
    participant DB as Database

    Note over Client,DB: Goodwill Request Creation Flow
    
    Client->>Controller: POST /api/warranty/goodwill/saveGoodwill<br/>(WarrantyGoodwill, List<MultipartFile>)
    
    Controller->>UserAuth: getBranchId()
    UserAuth-->>Controller: branchId
    Controller->>Controller: warrantyGoodwill.setBranchId(branchId)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: warrantyGoodwill.setCreatedBy(loginId)
    
    alt warrantyGoodwill.getId() != null (Update)
        Controller->>Controller: warrantyGoodwill.setLastModifiedBy(loginId)
        Controller->>Controller: warrantyGoodwill.setLastModifiedDate(new Date())
    end
    
    Controller->>Controller: Check draftFlag
    alt draftFlag = true
        Controller->>Controller: warrantyGoodwill.setStatus("Draft")
    else draftFlag = false
        Controller->>Controller: warrantyGoodwill.setStatus("Open")
    end
    
    Controller->>Service: saveGoodwill(warrantyGoodwill, multipartFileList)
    
    Service->>GoodwillRepo: save(warrantyGoodwill)
    GoodwillRepo->>DB: INSERT INTO warranty_goodwill
    DB-->>GoodwillRepo: Goodwill saved with ID
    GoodwillRepo-->>Service: Saved WarrantyGoodwill entity
    
    alt Status = "Open"
        Service->>ApprovalRepo: Get approval hierarchy for dealer
        ApprovalRepo->>DB: SELECT approver_level_seq, designation_level_id,<br/>grp_seq_no, isFinalApprovalStatus, approvalStatus<br/>FROM designation_hierarchy WHERE dealer_id = dealerId
        DB-->>ApprovalRepo: Approval hierarchy data
        ApprovalRepo-->>Service: List of hierarchy levels
        
        loop For each hierarchy level
            Service->>Service: Create WarrantyGoodwillApproval object
            Service->>Service: approval.setWarrantyGoodwillId(goodwillId)
            Service->>Service: approval.setApproverLevelSeq(levelSeq)
            Service->>Service: approval.setDesignationLevelId(designationLevelId)
            Service->>Service: approval.setGrpSeqNo(grpSeqNo)
            Service->>Service: approval.setIsfinalapprovalstatus(isFinal)
            Service->>Service: approval.setApprovalStatus("Pending")
            Service->>Service: approval.setRejectedFlag('N')
        end
        
        Service->>ApprovalRepo: saveAll(goodwillApprovals)
        ApprovalRepo->>DB: INSERT INTO warranty_goodwill_approval (multiple rows)
        DB-->>ApprovalRepo: Approval records saved
        ApprovalRepo-->>Service: List of saved approvals
    end
    
    alt multipartFileList != null and size > 0
        loop For each file
            Service->>Service: Generate fileName = "Goodwill" + timestamp + "_" + filename
            Service->>StorageService: store(file, fileName)
            StorageService->>StorageService: Save file to storage
            StorageService-->>Service: File stored successfully
            
            Service->>GoodwillRepo: Save file reference
            GoodwillRepo->>DB: INSERT INTO warranty_goodwill_files
            DB-->>GoodwillRepo: File record saved
        end
    end
    
    Service-->>Controller: ApiResponse with success message
    Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"Goodwill Request Saved Successfully"
```

---

## 6. Logsheet Creation Flow

This flow shows how **Warranty Logsheets** are created with failure part information and attachments.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyLogsheetController
    participant Service as WarrantyLogsheetService
    participant UserAuth as UserAuthentication
    participant LogsheetRepo as WarrantyLogsheetRepo
    participant StorageService as StorageService
    participant DB as Database

    Note over Client,DB: Logsheet Creation Flow
    
    Client->>Controller: POST /api/warranty/logsheet/saveWarrantyLogsheet<br/>(WarrantyLogsheet, List<MultipartFile>)
    
    Controller->>Controller: warrantyLogsheet.setLogsheetDate(new Date())
    
    Controller->>Service: saveWarrantyLogsheet(warrantyLogsheet, multipartFileList)
    
    Service->>UserAuth: getLoginId()
    UserAuth-->>Service: loginId
    Service->>Service: warrantyLogsheet.setCreatedBy(loginId)
    Service->>Service: warrantyLogsheet.setCreatedOn(new Date())
    
    Service->>LogsheetRepo: save(warrantyLogsheet)
    LogsheetRepo->>DB: INSERT INTO warranty_logsheet
    DB-->>LogsheetRepo: Logsheet saved with ID
    LogsheetRepo-->>Service: Saved WarrantyLogsheet entity
    
    alt Failure part information exists
        loop For each failure part
            Service->>Service: Create LogsheetFailurePartInfo object
            Service->>Service: failurePart.setWarrantyLogsheet(logsheet)
            Service->>Service: failurePart.setSparePartMasterId(partId)
            Service->>Service: failurePart.setFailureDescription(description)
            Service->>LogsheetRepo: saveFailurePart(failurePart)
            LogsheetRepo->>DB: INSERT INTO warranty_logsheet_failure_part
            DB-->>LogsheetRepo: Failure part record saved
        end
    end
    
    alt multipartFileList != null and size > 0
        loop For each file
            Service->>Service: Generate fileName = "Logsheet" + timestamp + "_" + filename
            Service->>StorageService: store(file, fileName)
            StorageService->>StorageService: Save file to storage
            StorageService-->>Service: File stored successfully
            
            Service->>LogsheetRepo: Save file reference
            LogsheetRepo->>DB: INSERT INTO warranty_logsheet_files
            DB-->>LogsheetRepo: File record saved
        end
    end
    
    Service-->>Controller: ApiResponse with success message
    Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"warranty log sheet save successfully"
```

---

## 7. Delivery Challan Creation Flow

This flow shows how **Warranty Delivery Challans** are created for WCR claims.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyDeliveryChallanController
    participant UserAuth as UserAuthentication
    participant DcRepo as WarrantyDeliveryChallanRepo
    participant WcrRepo as WarrantyWcrRepo
    participant DB as Database

    Note over Client,DB: Delivery Challan Creation Flow
    
    Client->>Controller: POST /api/warranty/deliverychallan/saveDeliveryChallan<br/>(WarrantyDeliveryChallan with WCR list)
    
    Controller->>DcRepo: Check if DC already exists for WCRs
    loop For each WarrantyWcr in warrantyDeliveryChallan.getWarrantyWcr()
        Controller->>DcRepo: getDeliveryChallanCount(wcrId)
        DcRepo->>DB: SELECT COUNT(*) FROM warranty_delivery_challan_wcr<br/>WHERE wcr_id = wcrId
        DB-->>DcRepo: count
        DcRepo-->>Controller: wcrNo or null
        
        alt wcrNo != null (DC already exists)
            Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"Delivery Challan already created for WCR : " + wcrNo
        end
    end
    
    Controller->>UserAuth: getBranchId()
    UserAuth-->>Controller: branchId
    Controller->>Controller: warrantyDeliveryChallan.setBranchId(branchId)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: warrantyDeliveryChallan.setCreatedBy(loginId)
    Controller->>Controller: warrantyDeliveryChallan.setDcDate(new Date())
    
    Controller->>Controller: Check draftFlag
    alt draftFlag = true
        Controller->>Controller: warrantyDeliveryChallan.setStatus("Draft")
    else draftFlag = false
        Controller->>Controller: warrantyDeliveryChallan.setStatus("Submitted")
    end
    
    Controller->>DcRepo: save(warrantyDeliveryChallan)
    DcRepo->>DB: INSERT INTO warranty_delivery_challan
    DB-->>DcRepo: Delivery challan saved with ID
    DcRepo-->>Controller: Saved WarrantyDeliveryChallan entity
    
    loop For each WarrantyWcr
        Controller->>DcRepo: Link WCR to DC
        DcRepo->>DB: INSERT INTO warranty_delivery_challan_wcr<br/>(dc_id, wcr_id)
        DB-->>DcRepo: Link record saved
    end
    
    Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"Delivery Challan created successfully"
```

---

## 8. Hotline Report Submission Flow

This flow shows how **Warranty Hotline Reports** are submitted with attachments.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyHotlineReportController
    participant Service as WarrantyHotlineReportService
    participant UserAuth as UserAuthentication
    participant HotlineRepo as WarrantyHotlineReportRepo
    participant StorageService as StorageService
    participant DB as Database

    Note over Client,DB: Hotline Report Submission Flow
    
    Client->>Controller: POST /api/warranty/hotlineReport/submitHotlineReport<br/>(WarrantyHotlineReport, List<MultipartFile>)
    
    Controller->>Service: submitHotlineReport(hotlineReport, multipartFileList)
    
    Service->>UserAuth: getLoginId()
    UserAuth-->>Service: loginId
    Service->>Service: hotlineReport.setCreatedBy(loginId)
    Service->>Service: hotlineReport.setCreatedOn(new Date())
    
    Service->>Service: Generate hotline number
    Service->>Service: hotlineReport.setHotlineNo(generatedNumber)
    Service->>Service: hotlineReport.setStatus("Submitted")
    
    Service->>HotlineRepo: save(hotlineReport)
    HotlineRepo->>DB: INSERT INTO warranty_hotline_report
    DB-->>HotlineRepo: Hotline report saved with ID
    HotlineRepo-->>Service: Saved WarrantyHotlineReport entity
    
    alt multipartFileList != null and size > 0
        loop For each file
            Service->>Service: Generate fileName = "Hotline" + timestamp + "_" + filename
            Service->>StorageService: store(file, fileName)
            StorageService->>StorageService: Save file to storage
            StorageService-->>Service: File stored successfully
            
            Service->>HotlineRepo: Save file reference
            HotlineRepo->>DB: INSERT INTO warranty_hotline_report_files
            DB-->>HotlineRepo: File record saved
        end
    end
    
    Service-->>Controller: ApiResponse with success message
    Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"Warranty Hotline Report has been saved Successfully"
```

---

## 9. KAI Inspection Sheet Creation Flow

This flow shows how **KAI Inspection Sheets** are created for WCR and DC inspection.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as KaiInspectionSheetController
    participant Service as KaiInspectionSheetService
    participant UserAuth as UserAuthentication
    participant InspectionRepo as KaiInspectionSheetRepo
    participant WcrRepo as WarrantyWcrRepo
    participant DcRepo as WarrantyDeliveryChallanRepo
    participant SparePartRepo as SparePartRequisitionItemRepo
    participant StorageService as StorageService
    participant DB as Database

    Note over Client,DB: KAI Inspection Sheet Creation Flow
    
    Client->>Controller: POST /api/warranty/kaiinspectionsheet/saveKaiInspectionSheet<br/>(KaiInspectionSheet, List<MultipartFile>)
    
    Controller->>Service: saveKaiInspectionSheet(kaiInspectionSheet, multipartFileList)
    
    Service->>UserAuth: getLoginId()
    UserAuth-->>Service: loginId
    Service->>Service: kaiInspectionSheet.setCreatedBy(loginId)
    Service->>Service: kaiInspectionSheet.setCreatedOn(new Date())
    
    Service->>InspectionRepo: save(kaiInspectionSheet)
    InspectionRepo->>DB: INSERT INTO kai_inspection_sheet
    DB-->>InspectionRepo: Inspection sheet saved with ID
    InspectionRepo-->>Service: Saved KaiInspectionSheet entity
    
    alt SparePartRequisitionItem exists
        loop For each sparePartRequisitionItem
            Service->>Service: Update part inspection details
            Service->>SparePartRepo: updateKaiInspectionDetails(itemId, keyPartNumber, claimable, inspectionSheetId)
            SparePartRepo->>DB: UPDATE spare_part_requisition_item<br/>SET kai_inspection_sheet_id = inspectionSheetId,<br/>key_part_number = keyPartNumber,<br/>claimable = claimable<br/>WHERE id = itemId
            DB-->>SparePartRepo: Updated
            SparePartRepo-->>Service: Update successful
        end
    end
    
    alt multipartFileList != null and size > 0
        loop For each file
            Service->>Service: Generate fileName = "KAIInspection" + timestamp + "_" + filename
            Service->>StorageService: store(file, fileName)
            StorageService->>StorageService: Save file to storage
            StorageService-->>Service: File stored successfully
            
            Service->>InspectionRepo: Save file reference
            InspectionRepo->>DB: INSERT INTO kai_inspection_sheet_files
            DB-->>InspectionRepo: File record saved
        end
    end
    
    Service-->>Controller: ApiResponse with success message
    Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"KAI Inspection Sheet Saved Successfully"
```

---

## 10. Retrofitment Campaign Creation Flow

This flow shows how **Retrofitment Campaigns** are created with Excel upload for chassis details.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyRetrofitmentCampaignController
    participant Service as WarrantyRetrofitmentCampaignService
    participant UserAuth as UserAuthentication
    participant CampaignRepo as WarrantyRetrofitmentCampaignRepo
    participant StorageService as StorageService
    participant DB as Database

    Note over Client,DB: Retrofitment Campaign Creation Flow
    
    Client->>Controller: POST /api/warranty/retrofitmentcampaign/saveRetrofitmentCampaign<br/>(WarrantyRetrofitmentCampaign, MultipartFile, List<MultipartFile>)
    
    Controller->>Service: saveRetrofitmentCampaign(campaign, multipartFile, multipartFileList)
    
    Service->>UserAuth: getLoginId()
    UserAuth-->>Service: loginId
    Service->>Service: campaign.setCreatedBy(loginId)
    Service->>Service: campaign.setCreatedOn(new Date())
    
    Service->>Service: Generate retrofitment campaign number
    Service->>Service: campaign.setRetrofitmentNo(generatedNumber)
    Service->>Service: campaign.setStatus("Open")
    
    Service->>CampaignRepo: save(campaign)
    CampaignRepo->>DB: INSERT INTO warranty_retrofitment_campaign
    DB-->>CampaignRepo: Campaign saved with ID
    CampaignRepo-->>Service: Saved WarrantyRetrofitmentCampaign entity
    
    alt multipartFile != null (Excel file with chassis details)
        Service->>Service: Parse Excel file
        Service->>Service: Extract chassis details from Excel
        loop For each chassis detail row
            Service->>Service: Create ChassisDetails object
            Service->>Service: chassisDetail.setCampaignId(campaignId)
            Service->>Service: chassisDetail.setChassisNo(chassisNo)
            Service->>Service: chassisDetail.setOtherFields(...)
            Service->>CampaignRepo: saveChassisDetail(chassisDetail)
            CampaignRepo->>DB: INSERT INTO warranty_retrofitment_campaign_chassis
            DB-->>CampaignRepo: Chassis detail saved
        end
    end
    
    alt multipartFileList != null and size > 0 (Supporting documents)
        loop For each file
            Service->>Service: Generate fileName = "Retrofitment" + timestamp + "_" + filename
            Service->>StorageService: store(file, fileName)
            StorageService->>StorageService: Save file to storage
            StorageService-->>Service: File stored successfully
            
            Service->>CampaignRepo: Save file reference
            CampaignRepo->>DB: INSERT INTO warranty_retrofitment_campaign_files
            DB-->>CampaignRepo: File record saved
        end
    end
    
    Service-->>Controller: ApiResponse with success message
    Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"Retrofitment Campaign has been Saved Successfully"
```

---

## 11. WCR Invoice Upload and Verification Flow

This flow shows how **WCR invoices** are uploaded and verified.

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Controller as WarrantyWcrController
    participant UserAuth as UserAuthentication
    participant WcrRepo as WarrantyWcrRepo
    participant StorageService as StorageService
    participant DB as Database

    Note over Client,DB: WCR Invoice Upload and Verification Flow
    
    alt Invoice Upload
        Client->>Controller: POST /api/warranty/Wcr/invoiceUpload<br/>(invoiceData Map, MultipartFile)
        
        Controller->>Controller: Extract wcrId, invoiceNo, invoiceDate from invoiceData
        
        alt invoiceNo == null OR invoiceDate == null
            Controller-->>Client: ResponseEntity.badRequest()<br/>"invoiceNo and invoiceDate must not be null or empty"
        else Valid data
            Controller->>Controller: Parse invoiceDate from "dd-MM-yyyy" format
            
            Controller->>Service: Generate photoName = "WCRInvoice_" + timestamp + "_" + filename
            
            Controller->>WcrRepo: uploadInvoice(wcrId, invoiceNo, invoiceDate, loginId, "Invoice Uploaded", photoName)
            WcrRepo->>DB: UPDATE warranty_wcr<br/>SET invoice_no = invoiceNo,<br/>invoice_date = invoiceDate,<br/>invoice_file_name = photoName,<br/>invoice_status = 'Uploaded',<br/>last_modified_by = loginId,<br/>last_modified_date = NOW()<br/>WHERE id = wcrId
            DB-->>WcrRepo: Updated
            WcrRepo-->>Controller: Update successful
            
            Controller->>StorageService: store(attachedFile, photoName)
            StorageService->>StorageService: Save file to storage
            StorageService-->>Controller: File stored successfully
            
            Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"Invoice Copy uploaded Succesfully"
        end
        
    else Invoice Verification
        Client->>Controller: POST /api/warranty/Wcr/invoiceVerify<br/>(invoiceData Map)
        
        Controller->>Controller: Extract wcrId, invoiceNo, invoiceDate from invoiceData
        
        alt invoiceNo == null OR invoiceDate == null
            Controller-->>Client: ResponseEntity.badRequest()<br/>"invoiceNo and invoiceDate must not be null or empty"
        else Valid data
            Controller->>Controller: Parse invoiceDate from "dd-MM-yyyy" format
            
            Controller->>UserAuth: getLoginId()
            UserAuth-->>Controller: loginId
            
            Controller->>WcrRepo: markAsInvoiceVerified(wcrId, invoiceNo, invoiceDate, loginId, "Invoice Verified", "")
            WcrRepo->>DB: UPDATE warranty_wcr<br/>SET invoice_status = 'Verified',<br/>invoice_verified_by = loginId,<br/>invoice_verified_date = NOW(),<br/>last_modified_by = loginId,<br/>last_modified_date = NOW()<br/>WHERE id = wcrId
            DB-->>WcrRepo: Updated
            WcrRepo-->>Controller: Update successful
            
            Controller-->>Client: ResponseEntity.ok(apiResponse)<br/>"Invoice Verified Successfuly"
        end
    end
```

---

## Summary

The Warranty module provides comprehensive warranty management functionality including:

1. **PCR Management**: Product Concern Report creation, approval workflow, and tracking
2. **WCR Management**: Warranty Claim Request creation from PCR/Goodwill with approval hierarchy
3. **Goodwill Management**: Goodwill request creation and approval workflow
4. **Logsheet Management**: Warranty logsheet creation with failure part tracking
5. **Delivery Challan**: Warranty parts delivery challan creation and management
6. **Hotline Reports**: Hotline report submission with attachments
7. **KAI Inspection**: KAI inspection sheet creation for warranty parts inspection
8. **Retrofitment Campaigns**: Retrofitment campaign management with Excel upload
9. **Invoice Management**: WCR invoice upload and verification
10. **Reports**: Various warranty reports generation (PCR, WCR, Goodwill, Logsheet, etc.)

All flows include proper authentication, authorization, approval hierarchies, file storage, and database persistence.

