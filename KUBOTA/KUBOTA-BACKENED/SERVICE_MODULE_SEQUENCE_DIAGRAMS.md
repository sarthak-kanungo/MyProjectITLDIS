## Service Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.service` module:

- **Service Booking** (Create, search, cancel service bookings).
- **Job Card Management** (Create, update, search, close, reopen job cards with photos and spare parts).
- **Service Claim** (Create, search, approve service claims with approval hierarchy).
- **Activity Proposal** (Create, search, approve service activity proposals).
- **Activity Claim** (Create, search, approve service activity claims with photos and vendor details).
- **Activity Report** (Create, search service activity reports with photos and PDF generation).
- **PSC/PDI/PDC** (Post-Sales Check, Pre-Delivery Inspection, Pre-Delivery Check).
- **MRC** (Machine Reconditioning with photos and invoice integration).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the Service module.

---

## 1. Service Booking Creation Flow

This flow shows how **service bookings** are created with machine details, customer information, and service requirements.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ServiceBookingController
    participant UserAuth as UserAuthentication
    participant BookingRepo as ServiceBookingRepo
    participant MachineInvRepo as MachineInventoryRepository
    participant DB as Database

    %% Service Booking Creation Flow
    Note over Client,DB: Service Booking Creation
    
    Client->>Controller: POST /api/service/servicebooking/saveServiceBooking<br/>(ServiceBooking)
    
    Controller->>UserAuth: getBranchId()
    UserAuth-->>Controller: branchId
    Controller->>Controller: serviceBooking.setBranchId(branchId)
    
    Controller->>Controller: serviceBooking.setBookingDate(new Date())
    
    alt Draft Flag = true
        Controller->>Controller: serviceBooking.setStatus("Draft")
    else Draft Flag = false
        Controller->>Controller: serviceBooking.setStatus("Open")
    end
    
    alt Update Existing Booking (id != null)
        alt Cancel Booking Flag = true
            Controller->>Controller: serviceBooking.setStatus("Cancelled")
            Controller->>Controller: serviceBooking.setReasonForCancellation(reason)
            Controller->>Controller: serviceBooking.setCancelBookingFlag(true)
        end
        Controller->>UserAuth: getLoginId()
        UserAuth-->>Controller: loginId
        Controller->>Controller: serviceBooking.setLastModifiedBy(loginId)
        Controller->>Controller: serviceBooking.setLastModifiedOn(new Date())
    end
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: serviceBooking.setCreatedBy(loginId)
    Controller->>Controller: serviceBooking.setCreatedOn(new Date())
    
    Controller->>BookingRepo: save(serviceBooking)
    BookingRepo->>DB: INSERT INTO service_booking<br/>(booking_no, chassis_no, branch_id,<br/>booking_date, status, created_by, ...)
    DB-->>BookingRepo: Service booking saved with ID
    BookingRepo-->>Controller: Saved ServiceBooking entity
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setResult(savedBooking)
    Controller->>Controller: apiResponse.setMessage("Service booking submitted successfully")
    
    Controller-->>Client: HTTP 200 OK (Service booking saved)
```

---

## 2. Service Booking Search Flow

This flow shows how **service bookings** are searched with various filters including booking number, chassis number, status, date range, and service details.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ServiceBookingController
    participant UserAuth as UserAuthentication
    participant BookingRepo as ServiceBookingRepo
    participant DB as Database

    %% Service Booking Search Flow
    Note over Client,DB: Service Booking Search
    
    Client->>Controller: POST /api/service/servicebooking/serviceBookingSearch<br/>(ServiceBookingSearchDto)
    
    Controller->>UserAuth: getManagementAccess()
    UserAuth-->>Controller: managementAccess
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    
    Controller->>UserAuth: getKubotaEmployeeId()
    UserAuth-->>Controller: kubotaEmployeeId
    
    Controller->>UserAuth: getDealerEmployeeId()
    UserAuth-->>Controller: dealerEmployeeId
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>BookingRepo: serviceBookingSearch(<br/>managementAccess, dealerId, kubotaEmployeeId,<br/>dealerEmployeeId, bookingNo, status,<br/>chassisNo, bookingFromDate, bookingToDate,<br/>mechanicName, engineNo, machineSubModel,<br/>sourceOfBooking, serviceCategory, serviceType,<br/>placeOfService, activityType, activityNo,<br/>appointmentFromDate, appointmentToDate,<br/>page, size, username, 'N', 0L)
    
    BookingRepo->>DB: SELECT booking details with filters<br/>FROM service_booking sb<br/>JOIN machine_inventory mi ON sb.machine_inventory_id = mi.id<br/>JOIN customer_master cm ON sb.customer_master_id = cm.id<br/>WHERE sb.dealer_id = dealerId<br/>AND sb.booking_date BETWEEN bookingFromDate AND bookingToDate<br/>AND (bookingNo IS NULL OR sb.booking_no LIKE bookingNo)<br/>AND (status IS NULL OR sb.status = status)<br/>AND (chassisNo IS NULL OR mi.chassis_no LIKE chassisNo)<br/>... (other filters)<br/>ORDER BY ... LIMIT page, size
    DB-->>BookingRepo: List of ServiceBookingResponseDto with totalCount
    BookingRepo-->>Controller: List of service booking records
    
    Controller->>Controller: Extract totalCount from first record (if exists)
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setResult(bookingList)
    Controller->>Controller: apiResponse.setCount(totalCount)
    Controller->>Controller: apiResponse.setMessage("service booking search")
    
    Controller-->>Client: HTTP 200 OK (Service booking search results with pagination)
```

---

## 3. Job Card Creation Flow

This flow shows how **job cards** are created with machine details, service information, photos, spare parts, and retrofitment mappings.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ServiceJobCardController
    participant ServiceImpl as ServiceJobcardImpl
    participant UserAuth as UserAuthentication
    participant JobCardRepo as ServiceJobCardRepo
    participant StorageService as StorageService
    participant SparePartReqRepo as SparePartRequisitionRepo
    participant DB as Database

    %% Job Card Creation Flow
    Note over Client,DB: Job Card Creation
    
    Client->>Controller: POST /api/service/jobcard/saveJobcard<br/>(multipart/form-data: ServiceJobCard,<br/>fsCouponPage1, fsCouponPage2,<br/>hourMeterPhoto, chassisPhoto,<br/>signedJobcard, retroAcknowledgementForm)
    
    Controller->>ServiceImpl: saveJobCard(serviceJobCard, photos...)
    
    ServiceImpl->>JobCardRepo: isJobCardCreated(0L)
    JobCardRepo->>DB: SELECT COUNT(*) FROM service_job_card WHERE ...
    DB-->>JobCardRepo: count
    JobCardRepo-->>ServiceImpl: false (if not created)
    
    ServiceImpl->>UserAuth: getBranchId()
    UserAuth-->>ServiceImpl: branchId
    ServiceImpl->>ServiceImpl: serviceJobCard.setBranchId(branchId)
    
    ServiceImpl->>UserAuth: getLoginId()
    UserAuth-->>ServiceImpl: loginId
    ServiceImpl->>ServiceImpl: serviceJobCard.setCreatedBy(loginId)
    
    alt Job Card Date is null
        ServiceImpl->>ServiceImpl: serviceJobCard.setJobCardDate(new Date())
    end
    
    alt Retrofitment Mappings exist
        loop For each retrofitment mapping
            ServiceImpl->>ServiceImpl: retroMapping.setCreatedBy(loginId)
            ServiceImpl->>ServiceImpl: retroMapping.setMachineInventory(serviceJobCard.getMachineInventory())
        end
        ServiceImpl->>ServiceImpl: serviceJobCard.setJobcardRetroMappings(retroMappings)
    end
    
    alt Photos exist
        alt Free Service Coupon Page 1 exists
            ServiceImpl->>ServiceImpl: Create ServiceJobcardPhotos object
            ServiceImpl->>ServiceImpl: Build photoName = "JC" + timestamp + "_" + filename
            ServiceImpl->>StorageService: store(fsCouponPage1, photoName)
            StorageService-->>ServiceImpl: File stored
            ServiceImpl->>ServiceImpl: photo.setFileType("free service coupon 1")
            ServiceImpl->>ServiceImpl: photoList.add(photo)
        end
        
        alt Free Service Coupon Page 2 exists
            ServiceImpl->>ServiceImpl: Create ServiceJobcardPhotos object
            ServiceImpl->>ServiceImpl: Build photoName = "JC" + timestamp + "_" + filename
            ServiceImpl->>StorageService: store(fsCouponPage2, photoName)
            StorageService-->>ServiceImpl: File stored
            ServiceImpl->>ServiceImpl: photo.setFileType("free service coupon 2")
            ServiceImpl->>ServiceImpl: photoList.add(photo)
        end
        
        alt Hour Meter Photo exists
            ServiceImpl->>ServiceImpl: Create ServiceJobcardPhotos object
            ServiceImpl->>ServiceImpl: Build photoName = "JC" + timestamp + "_" + filename
            ServiceImpl->>StorageService: store(hourMeterPhoto, photoName)
            StorageService-->>ServiceImpl: File stored
            ServiceImpl->>ServiceImpl: photo.setFileType("hour meter")
            ServiceImpl->>ServiceImpl: photoList.add(photo)
        end
        
        alt Chassis Photo exists
            ServiceImpl->>ServiceImpl: Create ServiceJobcardPhotos object
            ServiceImpl->>ServiceImpl: Build photoName = "JC" + timestamp + "_" + filename
            ServiceImpl->>StorageService: store(chassisPhoto, photoName)
            StorageService-->>ServiceImpl: File stored
            ServiceImpl->>ServiceImpl: photo.setFileType("chassis")
            ServiceImpl->>ServiceImpl: photoList.add(photo)
        end
        
        alt Signed Job Card exists
            ServiceImpl->>ServiceImpl: Create ServiceJobcardPhotos object
            ServiceImpl->>ServiceImpl: Build photoName = "JC" + timestamp + "_" + filename
            ServiceImpl->>StorageService: store(signedJobcard, photoName)
            StorageService-->>ServiceImpl: File stored
            ServiceImpl->>ServiceImpl: photo.setFileType("Signed Job Card")
            ServiceImpl->>ServiceImpl: photoList.add(photo)
        end
        
        alt Retrofitment Acknowledgement Form exists
            ServiceImpl->>ServiceImpl: Create ServiceJobcardPhotos object
            ServiceImpl->>ServiceImpl: Build photoName = "JC" + timestamp + "_" + filename
            ServiceImpl->>StorageService: store(retroAcknowledgementForm, photoName)
            StorageService-->>ServiceImpl: File stored
            ServiceImpl->>ServiceImpl: photo.setFileType("Retrofitment Acknowledgement Form")
            ServiceImpl->>ServiceImpl: photoList.add(photo)
        end
        
        ServiceImpl->>ServiceImpl: serviceJobCard.setServiceJobcardPhotos(photoList)
    end
    
    alt Draft Flag = true
        ServiceImpl->>ServiceImpl: serviceJobCard.setStatus("Draft")
        ServiceImpl->>JobCardRepo: save(serviceJobCard)
        JobCardRepo->>DB: INSERT INTO service_job_card<br/>INSERT INTO service_jobcard_photos<br/>INSERT INTO service_jobcard_retro_mapping
        DB-->>JobCardRepo: Job card saved
        JobCardRepo-->>ServiceImpl: Saved ServiceJobCard
        ServiceImpl-->>Controller: ApiResponse("job card save successfully")
    else Draft Flag = false
        ServiceImpl->>ServiceImpl: serviceJobCard.setStatus("Open")
        ServiceImpl->>JobCardRepo: save(serviceJobCard)
        JobCardRepo->>DB: INSERT INTO service_job_card<br/>INSERT INTO service_jobcard_photos<br/>INSERT INTO service_jobcard_retro_mapping
        DB-->>JobCardRepo: Job card saved with ID
        JobCardRepo-->>ServiceImpl: Saved ServiceJobCard
        
        ServiceImpl->>JobCardRepo: createSparePartRequistion(jobCardId)
        JobCardRepo->>SparePartReqRepo: Create SparePartRequisition
        SparePartReqRepo->>DB: INSERT INTO spare_part_requisition<br/>(requisition_purpose, service_job_card_id, ...)
        DB-->>SparePartReqRepo: Requisition saved
        SparePartReqRepo-->>JobCardRepo: Requisition created
        
        ServiceImpl-->>Controller: ApiResponse("Job card submitted successfully")
    end
    
    Controller-->>Client: HTTP 200 OK (Job card saved successfully)
```

---

## 4. Job Card Update Flow

This flow shows how **job cards** are updated with new photos, spare parts, labour charges, and machine out details.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ServiceJobCardController
    participant UserAuth as UserAuthentication
    participant JobCardRepo as ServiceJobCardRepo
    participant StorageService as StorageService
    participant SparePartReqRepo as SparePartRequisitionRepo
    participant SparePartItemRepo as SparePartRequisitionItemRepo
    participant DB as Database

    %% Job Card Update Flow
    Note over Client,DB: Job Card Update
    
    Client->>Controller: POST /api/service/jobcard/updateJobcard<br/>(multipart/form-data: ServiceJobCard, photos...)
    
    Controller->>JobCardRepo: getOne(serviceJobCard.getId())
    JobCardRepo->>DB: SELECT * FROM service_job_card WHERE id = id
    DB-->>JobCardRepo: Existing ServiceJobCard
    JobCardRepo-->>Controller: serviceJobCard1 (existing)
    
    Controller->>Controller: serviceJobCard1.setLastModifiedDate(new Date())
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: serviceJobCard1.setLastModifiedBy(loginId)
    
    Controller->>Controller: Update basic fields<br/>(alternateNumber, closeDelayReason, closeRemark)
    
    alt Out DateTime exists
        Controller->>Controller: Parse outDateTime string to Date
        Controller->>Controller: serviceJobCard1.setMachineOutDateTime(parsedDate)
    end
    
    alt New Photos exist
        loop For each new photo type
            Controller->>Controller: Create ServiceJobcardPhotos object
            Controller->>Controller: Build photoName = "service_jobcard" + timestamp + "_" + filename
            Controller->>StorageService: store(photo, photoName)
            StorageService-->>Controller: File stored
            Controller->>Controller: photo.setFileType(photoType)
            Controller->>JobCardRepo: deletePhoto(jobCardId, photoType)
            JobCardRepo->>DB: DELETE FROM service_jobcard_photos<br/>WHERE job_card_id = id AND file_type = photoType
            DB-->>JobCardRepo: Old photo deleted
            Controller->>Controller: photoList.add(photo)
        end
        Controller->>Controller: serviceJobCard1.setServiceJobcardPhotos(photoList)
    end
    
    alt Spare Part Requisition Items exist
        Controller->>SparePartReqRepo: findByServiceJobCard(serviceJobCard1)
        SparePartReqRepo->>DB: SELECT * FROM spare_part_requisition<br/>WHERE service_job_card_id = id
        DB-->>SparePartReqRepo: Existing SparePartRequisition
        SparePartReqRepo-->>Controller: sparePartRequisition
        
        loop For each spare part item
            alt Item exists (id != null) AND Category = Paid/Warranty
                Controller->>SparePartItemRepo: getOne(item.getId())
                SparePartItemRepo->>DB: SELECT * FROM spare_part_requisition_item WHERE id = id
                DB-->>SparePartItemRepo: Existing item
                SparePartItemRepo-->>Controller: sparePartRequisitionItem1
                
                Controller->>Controller: Update item fields<br/>(priceUnit, gstPercent, category, reqQuantity, amount)
                
                alt Quantity changed
                    Controller->>Controller: item1.setQtyUpdateFlag(true)
                end
                
                Controller->>Controller: updatedItems.add(item1)
            else New Item OR Category = FOC
                Controller->>Controller: item.setSparePartRequisition(sparePartRequisition)
                
                alt New Item (id == null)
                    Controller->>Controller: serviceJobCard1.setPartIssueFlag(false)
                end
                
                alt Category = Paid/Warranty
                    Controller->>Controller: item.setQtyUpdateFlag(true)
                end
                
                Controller->>Controller: updatedItems.add(item)
            end
        end
        
        Controller->>Controller: serviceJobCard1.setSparePartRequisitionItem(updatedItems)
    end
    
    alt Labour Charge exists
        Controller->>Controller: serviceJobCard1.setLabourCharge(labourCharge)
    end
    
    alt Outside Job Charge exists
        Controller->>Controller: serviceJobCard1.setOutsideJobCharge(outsideJobCharge)
    end
    
    Controller->>Controller: serviceJobCard1.setFinalActionTaken(finalActionTaken)
    Controller->>Controller: serviceJobCard1.setSuggestionToCustomer(suggestionToCustomer)
    
    Controller->>JobCardRepo: save(serviceJobCard1)
    JobCardRepo->>DB: UPDATE service_job_card<br/>INSERT/UPDATE service_jobcard_photos<br/>UPDATE spare_part_requisition_item
    DB-->>JobCardRepo: Job card updated
    JobCardRepo-->>Controller: Updated ServiceJobCard
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setMessage("Job card updated successfully")
    
    Controller-->>Client: HTTP 200 OK (Job card updated successfully)
```

---

## 5. Service Claim Creation and Approval Flow

This flow shows how **service claims** are created with product/service details, documents, and approval hierarchy setup.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ServiceClaimController
    participant UserAuth as UserAuthentication
    participant ClaimRepo as ServiceClaimRepo
    participant ApprovalRepo as ServiceClaimApprovalRepo
    participant DB as Database

    %% Service Claim Creation Flow
    Note over Client,DB: Service Claim Creation
    
    Client->>Controller: POST /api/service/claim/create<br/>(ServiceClaimHdrEntity)
    
    Controller->>Controller: claim.setClaimStatus("Claim Approval Pending")
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>Controller: claim.setDealerId(dealerId)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: claim.setCreatedBy(loginId)
    
    Controller->>ClaimRepo: save(claim)
    ClaimRepo->>DB: INSERT INTO service_claim_hdr<br/>INSERT INTO service_claim_dtl<br/>INSERT INTO service_claim_document
    DB-->>ClaimRepo: Claim saved with ID
    ClaimRepo-->>Controller: Saved ServiceClaimHdrEntity
    
    Controller->>ApprovalRepo: getClaimApprovalHierarchyLevel(dealerId)
    ApprovalRepo->>DB: SELECT approver_level_seq, designation_level_id,<br/>grp_seq_no, isFinalApprovalStatus, approvalStatus<br/>FROM designation_hierarchy<br/>WHERE dealer_id = dealerId<br/>ORDER BY approver_level_seq
    DB-->>ApprovalRepo: List of hierarchy levels
    ApprovalRepo-->>Controller: List of hierarchy maps
    
    Controller->>Controller: Initialize approvalList
    
    loop For each hierarchy level
        Controller->>Controller: Create ServiceClaimApprovalEntity
        Controller->>Controller: approval.setServiceClaimId(claim.getId())
        Controller->>Controller: approval.setApproverLevelSeq(levelSeq)
        Controller->>Controller: approval.setDesignationLevelId(designationLevelId)
        Controller->>Controller: approval.setGrpSeqNo(grpSeqNo)
        Controller->>Controller: approval.setIsfinalapprovalstatus(isFinalApprovalStatus)
        Controller->>Controller: approval.setApprovalStatus("Pending")
        Controller->>Controller: approval.setRejectedFlag('N')
        Controller->>Controller: approvalList.add(approval)
    end
    
    Controller->>ApprovalRepo: saveAll(approvalList)
    ApprovalRepo->>DB: INSERT INTO service_claim_approval<br/>(service_claim_id, approver_level_seq, ...)
    DB-->>ApprovalRepo: Approval records saved
    ApprovalRepo-->>Controller: List of saved approvals
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setMessage("Claim Generated Successfully")
    
    Controller-->>Client: HTTP 200 OK (Claim created with approval hierarchy)

    %% Service Claim Approval Flow
    Note over Client,DB: Service Claim Approval
    
    Client->>Controller: POST /api/service/claim/approval<br/>(ServiceClaimApprovalRequestModel)
    
    Controller->>UserAuth: getKubotaEmployeeId()
    UserAuth-->>Controller: kubotaEmployeeId
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>ApprovalRepo: claimApproval(<br/>claimId, kubotaEmployeeId, remarks,<br/>username, approvalStatus)
    
    ApprovalRepo->>DB: UPDATE service_claim_approval<br/>SET approval_status = approvalStatus,<br/>approved_by = kubotaEmployeeId,<br/>approved_date = NOW(),<br/>remarks = remarks<br/>WHERE service_claim_id = claimId<br/>AND approver_level_seq = currentLevel
    
    alt Approval Status = "Approved"
        ApprovalRepo->>DB: Check if next level exists
        DB-->>ApprovalRepo: Next level exists
        
        alt Next level exists
            ApprovalRepo->>DB: UPDATE service_claim_approval<br/>SET approval_status = 'Pending'<br/>WHERE service_claim_id = claimId<br/>AND approver_level_seq = nextLevel
        else Final approval
            ApprovalRepo->>DB: UPDATE service_claim_hdr<br/>SET claim_status = 'Approved'<br/>WHERE id = claimId
        end
        
        alt Rejection Data exists
            loop For each rejection detail
                ApprovalRepo->>DB: UPDATE service_claim_dtl<br/>SET rejected = !selection,<br/>rejection_reason = rejectionReason,<br/>remark = remark<br/>WHERE id = detailId
            end
        end
    else Approval Status = "Rejected"
        ApprovalRepo->>DB: UPDATE service_claim_hdr<br/>SET claim_status = 'Rejected'<br/>WHERE id = claimId
        ApprovalRepo->>DB: UPDATE service_claim_approval<br/>SET rejected_flag = 'Y'<br/>WHERE service_claim_id = claimId<br/>AND approver_level_seq >= currentLevel
    end
    
    ApprovalRepo-->>Controller: Approval status message
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setMessage(statusMessage)
    
    Controller-->>Client: HTTP 200 OK (Claim approval processed)
```

---

## 6. Activity Proposal Creation and Approval Flow

This flow shows how **service activity proposals** are created with activity details, heads, sub-activities, and approval hierarchy setup.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ServiceActivityProposalController
    participant UserAuth as UserAuthentication
    participant ProposalRepo as ServiceActivityProposalRepo
    participant ApprovalRepo as ServiceActivityProposalApprovalRepository
    participant DealerRepo as DealerMasterRepo
    participant DB as Database

    %% Activity Proposal Creation Flow
    Note over Client,DB: Activity Proposal Creation
    
    Client->>Controller: POST /api/service/activityProposal/saveServiceActivityProposal<br/>(ServiceActivityProposal)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: serviceActivityProposal.setCreatedBy(loginId)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    
    Controller->>DealerRepo: getOne(dealerId)
    DealerRepo->>DB: SELECT * FROM dealer_master WHERE id = dealerId
    DB-->>DealerRepo: DealerMaster
    DealerRepo-->>Controller: dealerMaster
    
    Controller->>Controller: serviceActivityProposal.setDealerMaster(dealerMaster)
    
    Controller->>ProposalRepo: save(serviceActivityProposal)
    ProposalRepo->>DB: INSERT INTO service_activity_proposal<br/>INSERT INTO service_activity_proposal_heads<br/>INSERT INTO service_activity_proposal_sub_activity
    DB-->>ProposalRepo: Proposal saved with ID
    ProposalRepo-->>Controller: Saved ServiceActivityProposal
    
    alt Draft Flag = false
        Controller->>ApprovalRepo: getServiceActivityProposalApprovalHierarchyLevel(dealerId)
        ApprovalRepo->>DB: SELECT approver_level_seq, designation_level_id,<br/>grp_seq_no, isFinalApprovalStatus, approvalStatus<br/>FROM designation_hierarchy<br/>WHERE dealer_id = dealerId<br/>ORDER BY approver_level_seq
        DB-->>ApprovalRepo: List of hierarchy levels
        ApprovalRepo-->>Controller: List of hierarchy maps
        
        Controller->>Controller: Initialize approvalList
        
        loop For each hierarchy level
            Controller->>Controller: Create ServiceActivityProposalApproval
            Controller->>Controller: approval.setServiceActivityProposalId(proposal.getId())
            Controller->>Controller: approval.setApproverLevelSeq(levelSeq)
            Controller->>Controller: approval.setDesignationLevelId(designationLevelId)
            Controller->>Controller: approval.setGrpSeqNo(grpSeqNo)
            Controller->>Controller: approval.setIsfinalapprovalstatus(isFinalApprovalStatus)
            Controller->>Controller: approval.setApprovalStatus("Pending")
            Controller->>Controller: approval.setRejectedFlag('N')
            Controller->>Controller: approvalList.add(approval)
        end
        
        Controller->>ApprovalRepo: saveAll(approvalList)
        ApprovalRepo->>DB: INSERT INTO service_activity_proposal_approval
        DB-->>ApprovalRepo: Approval records saved
        ApprovalRepo-->>Controller: List of saved approvals
    end
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setMessage("Service activity save successfully.")
    
    Controller-->>Client: HTTP 200 OK (Activity proposal created)

    %% Activity Proposal Approval Flow
    Note over Client,DB: Activity Proposal Approval
    
    Client->>Controller: POST /api/service/activityProposal/approveServiceActivityProposal<br/>(ServiceProposalApprovalDto)
    
    Controller->>UserAuth: getKubotaEmployeeId()
    UserAuth-->>Controller: kubotaEmployeeId
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>ApprovalRepo: approveServiceActivityProposal(<br/>proposalId, kubotaEmployeeId, remark,<br/>username, approvalStatus, approvedAmount)
    
    ApprovalRepo->>DB: UPDATE service_activity_proposal_approval<br/>SET approval_status = approvalStatus,<br/>approved_by = kubotaEmployeeId,<br/>approved_date = NOW(),<br/>remarks = remark,<br/>approved_amount = approvedAmount<br/>WHERE service_activity_proposal_id = proposalId<br/>AND approver_level_seq = currentLevel
    
    alt Approval Status = "Approved"
        ApprovalRepo->>DB: Check if next level exists
        DB-->>ApprovalRepo: Next level exists
        
        alt Next level exists
            ApprovalRepo->>DB: UPDATE service_activity_proposal_approval<br/>SET approval_status = 'Pending'<br/>WHERE service_activity_proposal_id = proposalId<br/>AND approver_level_seq = nextLevel
        else Final approval
            ApprovalRepo->>DB: UPDATE service_activity_proposal<br/>SET activity_status = 'Approved'<br/>WHERE id = proposalId
        end
    else Approval Status = "Rejected"
        ApprovalRepo->>DB: UPDATE service_activity_proposal<br/>SET activity_status = 'Rejected'<br/>WHERE id = proposalId
        ApprovalRepo->>DB: UPDATE service_activity_proposal_approval<br/>SET rejected_flag = 'Y'<br/>WHERE service_activity_proposal_id = proposalId<br/>AND approver_level_seq >= currentLevel
    end
    
    ApprovalRepo-->>Controller: Approval status message
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setMessage(statusMessage)
    
    Controller-->>Client: HTTP 200 OK (Activity proposal approval processed)
```

---

## 7. Activity Claim Creation Flow

This flow shows how **service activity claims** are created with activity proposal details, heads, sub-activities, photos, and vendor information.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ServiceActivityClaimController
    participant UserAuth as UserAuthentication
    participant ClaimRepo as ServiceActivityClaimRepo
    participant ProposalRepo as ServiceActivityProposalRepo
    participant ProposalHeadRepo as ServiceActivityProposalHeadRepo
    participant ProposalSubActivityRepo as ServiceActivityProposalSubActivityRepo
    participant ApprovalRepo as ServiceActivityClaimApprovalRepository
    participant StorageService as StorageService
    participant DB as Database

    %% Activity Claim Creation Flow
    Note over Client,DB: Activity Claim Creation
    
    Client->>Controller: POST /api/service/activityClaim/saveServiceActivityClaim<br/>(multipart/form-data: ServiceActivityClaim,<br/>claimHeads with images, subActivities with images)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: serviceActivityClaim.setCreatedBy(loginId)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    
    Controller->>ProposalRepo: findById(serviceActivityClaim.getServiceActivityProposalId())
    ProposalRepo->>DB: SELECT * FROM service_activity_proposal WHERE id = proposalId
    DB-->>ProposalRepo: ServiceActivityProposal
    ProposalRepo-->>Controller: serviceActivityProposal
    
    Controller->>Controller: Extract claimHeads and subActivities from request
    
    loop For each claim head
        Controller->>ProposalHeadRepo: findById(head.getId())
        ProposalHeadRepo->>DB: SELECT * FROM service_activity_proposal_heads WHERE id = headId
        DB-->>ProposalHeadRepo: ServiceActivityProposalHeads
        ProposalHeadRepo-->>Controller: serviceActivityProposalHeads
        
        Controller->>Controller: serviceActivityProposalHeads.setActualClaimAmount(head.getActualClaimAmount())
        
        alt Head Image exists
            Controller->>Controller: Extract MultipartFile headImage
            Controller->>Controller: Build headImageName = "activity_claim_head_" + timestamp + "_" + filename
            Controller->>StorageService: store(headImage, headImageName)
            StorageService-->>Controller: File stored
            Controller->>Controller: serviceActivityProposalHeads.setHeadImage(headImageName)
        end
        
        Controller->>Controller: serviceActivityProposalHeads.setVendorName(head.getVendorName())
        Controller->>Controller: serviceActivityProposalHeads.setBillNo(head.getBillNo())
        
        alt Bill Date exists
            Controller->>Controller: Parse billDate string to Date
            Controller->>Controller: serviceActivityProposalHeads.setBillDate(parsedDate)
        end
        
        Controller->>Controller: serviceActivityProposalHeads.setServiceActivityProposal(serviceActivityProposal)
    end
    
    alt Activity Type = 4 (with sub-activities)
        loop For each sub-activity
            Controller->>ProposalSubActivityRepo: findById(subActivity.getId())
            ProposalSubActivityRepo->>DB: SELECT * FROM service_activity_proposal_sub_activity WHERE id = subActivityId
            DB-->>ProposalSubActivityRepo: ServiceActivityProposalSubActivity
            ProposalSubActivityRepo-->>Controller: serviceActivityProposalSubActivity
            
            Controller->>Controller: serviceActivityProposalSubActivity.setActualClaimAmount(subActivity.getActualClaimAmount())
            
            alt Sub-Activity Image exists
                Controller->>Controller: Extract MultipartFile subActivityImage
                Controller->>Controller: Build subActivityImageName = "activity_claim_sub_activity_" + timestamp + "_" + filename
                Controller->>StorageService: store(subActivityImage, subActivityImageName)
                StorageService-->>Controller: File stored
                Controller->>Controller: serviceActivityProposalSubActivity.setSubActivityImage(subActivityImageName)
            end
            
            Controller->>Controller: serviceActivityProposalSubActivity.setVendorName(subActivity.getVendorName())
            Controller->>Controller: serviceActivityProposalSubActivity.setBillNo(subActivity.getBillNo())
            
            alt Bill Date exists
                Controller->>Controller: Parse billDate string to Date
                Controller->>Controller: serviceActivityProposalSubActivity.setBillDate(parsedDate)
            end
            
            Controller->>Controller: serviceActivityProposalSubActivity.setServiceActivityProposal(serviceActivityProposal)
        end
    end
    
    Controller->>Controller: serviceActivityClaim.setServiceActivityProposal(serviceActivityProposal)
    
    Controller->>ClaimRepo: save(serviceActivityClaim)
    ClaimRepo->>DB: INSERT INTO service_activity_claim<br/>UPDATE service_activity_proposal_heads<br/>UPDATE service_activity_proposal_sub_activity
    DB-->>ClaimRepo: Claim saved with ID
    ClaimRepo-->>Controller: Saved ServiceActivityClaim
    
    Controller->>ApprovalRepo: getServiceActivityClaimApprovalHierarchyLevel(dealerId)
    ApprovalRepo->>DB: SELECT approver_level_seq, designation_level_id,<br/>grp_seq_no, isFinalApprovalStatus, approvalStatus<br/>FROM designation_hierarchy<br/>WHERE dealer_id = dealerId<br/>ORDER BY approver_level_seq
    DB-->>ApprovalRepo: List of hierarchy levels
    ApprovalRepo-->>Controller: List of hierarchy maps
    
    Controller->>Controller: Initialize approvals list
    
    loop For each hierarchy level
        Controller->>Controller: Create ServiceActivityClaimApproval
        Controller->>Controller: approval.setServiceActivityClaimId(claim.getId())
        Controller->>Controller: approval.setApproverLevelSeq(levelSeq)
        Controller->>Controller: approval.setDesignationLevelId(designationLevelId)
        Controller->>Controller: approval.setGrpSeqNo(grpSeqNo)
        Controller->>Controller: approval.setIsfinalapprovalstatus(isFinalApprovalStatus)
        Controller->>Controller: approval.setApprovalStatus("Pending")
        Controller->>Controller: approval.setRejectedFlag('N')
        Controller->>Controller: approvals.add(approval)
    end
    
    Controller->>ApprovalRepo: saveAll(approvals)
    ApprovalRepo->>DB: INSERT INTO service_activity_claim_approval
    DB-->>ApprovalRepo: Approval records saved
    ApprovalRepo-->>Controller: List of saved approvals
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setMessage("Service activity Claim save successfully.")
    
    Controller-->>Client: HTTP 200 OK (Activity claim created with approval hierarchy)
```

---

## 8. Activity Report Creation Flow

This flow shows how **service activity reports** are created with activity proposal details, job card details, machine details, service details, and photos.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ServiceActivityReportController
    participant UserAuth as UserAuthentication
    participant ReportRepo as ServiceActivityReportRepo
    participant PhotoRepo as ServiceActivityReportPhotoRepo
    participant StorageService as StorageService
    participant DB as Database

    %% Activity Report Creation Flow
    Note over Client,DB: Activity Report Creation
    
    Client->>Controller: POST /api/service/activityReport/saveServiceActivityReport<br/>(multipart/form-data: ServiceActivityReport,<br/>multipartFileList)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: serviceActivityReport.setCreatedBy(loginId)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    
    Controller->>ReportRepo: save(serviceActivityReport)
    ReportRepo->>DB: INSERT INTO service_activity_report<br/>INSERT INTO service_activity_report_job_card_details<br/>INSERT INTO service_activity_report_machine_details<br/>INSERT INTO service_activity_report_service_details
    DB-->>ReportRepo: Report saved with ID
    ReportRepo-->>Controller: Saved ServiceActivityReport
    
    alt Photos exist (multipartFileList not empty)
        loop For each photo file
            Controller->>Controller: Create ServiceActivityReportPhotos object
            Controller->>Controller: Build photoName = "service_activity_report_photos" + timestamp + "_" + filename
            Controller->>StorageService: store(photo, photoName)
            StorageService-->>Controller: File stored
            Controller->>Controller: photo.setFileName(photoName)
            Controller->>Controller: photo.setServiceActivityReport(savedReport)
            Controller->>PhotoRepo: save(photo)
            PhotoRepo->>DB: INSERT INTO service_activity_report_photos<br/>(file_name, service_activity_report_id)
            DB-->>PhotoRepo: Photo saved
            PhotoRepo-->>Controller: Saved ServiceActivityReportPhotos
        end
    end
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setMessage("Service activity Report save successfully.")
    
    Controller-->>Client: HTTP 200 OK (Activity report created with photos)
```

---

## 9. PSC (Post-Sales Check) Creation Flow

This flow shows how **Post-Sales Check (PSC)** records are created with chassis details and checkpoint information.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as PscController
    participant ServiceImpl as ServicePscImpl
    participant UserAuth as UserAuthentication
    participant PscRepo as PscRepository
    participant DB as Database

    %% PSC Creation Flow
    Note over Client,DB: Post-Sales Check Creation
    
    Client->>Controller: POST /api/service/psc/savePsc<br/>(ServicePsc)
    
    Controller->>ServiceImpl: savePsc(servicePsc)
    
    ServiceImpl->>UserAuth: getLoginId()
    UserAuth-->>ServiceImpl: loginId
    ServiceImpl->>ServiceImpl: servicePsc.setCreatedBy(loginId)
    
    ServiceImpl->>UserAuth: getDealerId()
    UserAuth-->>ServiceImpl: dealerId
    ServiceImpl->>ServiceImpl: servicePsc.setDealerId(dealerId)
    
    ServiceImpl->>UserAuth: getBranchId()
    UserAuth-->>ServiceImpl: branchId
    ServiceImpl->>ServiceImpl: servicePsc.setBranchId(branchId)
    
    ServiceImpl->>ServiceImpl: servicePsc.setPscDate(new Date())
    
    ServiceImpl->>PscRepo: save(servicePsc)
    PscRepo->>DB: INSERT INTO service_psc<br/>INSERT INTO service_psc_checkpoint<br/>(psc_no, chassis_no, psc_date,<br/>checkpoint_id, checkpoint_status, ...)
    DB-->>PscRepo: PSC saved with ID
    PscRepo-->>ServiceImpl: Saved ServicePsc
    
    ServiceImpl-->>Controller: ApiResponse("PSC save successfully")
    
    Controller-->>Client: HTTP 200 OK (PSC created successfully)
```

---

## 10. MRC (Machine Reconditioning) Creation Flow

This flow shows how **Machine Reconditioning (MRC)** records are created with chassis details, invoice information, and photos.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ServiceMrcController
    participant ServiceImpl as ServiceMrcService
    participant UserAuth as UserAuthentication
    participant MrcRepo as MrcRepository
    participant StorageService as StorageService
    participant DB as Database

    %% MRC Creation Flow
    Note over Client,DB: Machine Reconditioning Creation
    
    Client->>Controller: POST /api/service/mrc/saveMrc<br/>(multipart/form-data: ServiceMrc, multipartFileList)
    
    Controller->>Controller: serviceMrc.setMrcDate(new Date())
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: serviceMrc.setCreatedBy(loginId)
    
    alt Update Existing MRC (id != null)
        Controller->>Controller: serviceMrc.setModifiedBy(loginId)
        Controller->>Controller: serviceMrc.setModifiedDate(new Date())
    end
    
    Controller->>ServiceImpl: saveServiceMrc(serviceMrc, multipartFileList)
    
    ServiceImpl->>UserAuth: getDealerId()
    UserAuth-->>ServiceImpl: dealerId
    ServiceImpl->>ServiceImpl: serviceMrc.setDealerId(dealerId)
    
    ServiceImpl->>UserAuth: getBranchId()
    UserAuth-->>ServiceImpl: branchId
    ServiceImpl->>ServiceImpl: serviceMrc.setBranchId(branchId)
    
    alt Photos exist (multipartFileList not empty)
        loop For each photo file
            ServiceImpl->>ServiceImpl: Create ServiceMrcPhotos object
            ServiceImpl->>ServiceImpl: Build photoName = "MRC" + timestamp + "_" + filename
            ServiceImpl->>StorageService: store(photo, photoName)
            StorageService-->>ServiceImpl: File stored
            ServiceImpl->>ServiceImpl: photo.setFileName(photoName)
            ServiceImpl->>ServiceImpl: photo.setServiceMrc(serviceMrc)
            ServiceImpl->>ServiceImpl: photoList.add(photo)
        end
        ServiceImpl->>ServiceImpl: serviceMrc.setServiceMrcPhotos(photoList)
    end
    
    ServiceImpl->>MrcRepo: save(serviceMrc)
    MrcRepo->>DB: INSERT INTO service_mrc<br/>INSERT INTO service_mrc_photos<br/>(mrc_no, chassis_no, accpac_invoice_id,<br/>mrc_date, file_name, ...)
    DB-->>MrcRepo: MRC saved with ID
    MrcRepo-->>ServiceImpl: Saved ServiceMrc
    
    ServiceImpl-->>Controller: MRC saved successfully
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setMessage("Service mrc save successfully.")
    
    Controller-->>Client: HTTP 200 OK (MRC created with photos)
```

---

## Summary

The **Service module** provides comprehensive **Service Management** functionality for the KUBOTA DMS:

- **Service Booking**:
  - Create service bookings with machine and customer details
  - Search bookings with multiple filters (booking number, chassis number, status, date range, service details)
  - Cancel service bookings with reason
  - Auto-complete chassis numbers and booking numbers
  - Support for draft and open status

- **Job Card Management**:
  - Create job cards with machine details, service information, and photos
  - Upload multiple photo types (free service coupons, hour meter, chassis, signed job card, retrofitment acknowledgement)
  - Manage spare part requisitions with categories (FOC, Paid, Warranty)
  - Update job cards with labour charges, outside job charges, and machine out details
  - Close and reopen job cards with approval workflow
  - Search job cards with extensive filters
  - Machine service history tracking
  - Excel report generation for job cards
  - Retrofitment campaign integration

- **Service Claim**:
  - Create service claims with product/service details and documents
  - Multi-level approval hierarchy setup
  - Claim search with filters (claim number, status, date range)
  - Claim approval workflow with rejection handling
  - Excel report generation (summary and detail)
  - Document approval amount tracking

- **Activity Proposal**:
  - Create service activity proposals with activity type, budget, and targets
  - Activity heads and sub-activities management
  - Budget calculation for activity types
  - Multi-level approval hierarchy
  - Activity proposal search with filters
  - Group approval support
  - Activity status tracking

- **Activity Claim**:
  - Create activity claims linked to approved proposals
  - Upload photos for heads and sub-activities
  - Vendor details and bill information (vendor name, bill number, bill date)
  - Actual claim amount tracking
  - Multi-level approval workflow
  - Head and sub-activity level approval
  - Claim search and view functionality
  - Edit capability for non-approved claims

- **Activity Report**:
  - Create activity reports with job card, machine, and service details
  - Upload multiple photos
  - Report search functionality
  - PDF report generation using Jasper Reports
  - View activity report details with all associated data

- **PSC/PDI/PDC**:
  - Post-Sales Check (PSC) with checkpoint management
  - Pre-Delivery Inspection (PDI) with inspection details
  - Pre-Delivery Check (PDC) with check details
  - Chassis number auto-complete
  - Search functionality for all check types

- **MRC (Machine Reconditioning)**:
  - Create MRC records with chassis and invoice details
  - Photo upload support
  - Integration with ACCPAC invoices
  - MRC search with filters
  - View MRC details with photos

- **Machine Installation/Reinstallation**:
  - Machine installation tracking
  - Machine reinstallation tracking
  - Installation details and status management

The Service module integrates with multiple other modules (Sales, Spares, Warranty, Masters) to provide a unified service management system, enabling dealers to efficiently manage service bookings, job cards, claims, activities, and machine maintenance operations.

