## CRM Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.crm` module:

- **Customer Care Executive Call Creation** (Sales Enquiry, Service Booking, Post Service Feedback, Post Sales Feedback).
- **Direct Survey Submission** (Survey form submission with recordings and call attempts).
- **Toll-Free Call Management** (Toll-free call creation with complaint recordings).
- **Complaint Resolution** (Complaint search and resolution update).
- **Survey Summary Report Generation** (Survey reports and analytics).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the CRM module.

---

## 1. Customer Care Executive Call Creation Flow

This flow shows how **Customer Care Executive (CCE) calls** are created with different call types: Sales Enquiry (Type 1), Service Booking (Type 2), Post Service Feedback (Type 3), and Post Sales Feedback (Type 4).

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as CustomerCareExeCallController
    participant UserAuth as UserAuthentication
    participant CceCallRepo as CustomerCareExeCallRepo
    participant EnquiryRepo as EnquiryRepo
    participant ServiceBookingRepo as ServiceBookingRepo
    participant CallFeedbackRepo as QaCceCallFeedbackRepo
    participant DB as Database

    %% CCE Call Creation Flow
    Note over Client,DB: Customer Care Executive Call Creation
    
    Client->>Controller: POST /api/crm/crmmodule/customerCareExeCall/create<br/>(CustomerCareExeCallHdr with callType)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    Controller->>Controller: cceCallHdr.setDealerId(dealerId)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: cceCallHdr.setCreatedBy(loginId)
    Controller->>Controller: cceCallHdr.setCreatedDate(new Date())
    Controller->>Controller: cceCallHdr.setEndTime(HH:mm format)
    
    Controller->>Controller: Check callType.getId()
    
    alt Call Type = 1 (Sales Enquiry)
        Controller->>Controller: Check enquiryDetailDto != null
        Controller->>EnquiryRepo: Create new Enquiry entity
        Controller->>Controller: Set enquiry fields from enquiryDetailDto
        Controller->>Controller: Set mandatory fields (address1, purposeOfPurchase, etc.)
        Controller->>EnquiryRepo: save(enquiry)
        EnquiryRepo->>DB: INSERT INTO enquiry
        DB-->>EnquiryRepo: Enquiry saved with ID
        EnquiryRepo-->>Controller: Saved Enquiry entity
        Controller->>Controller: cceCallHdr.setSalesEnquiryId(enquiry.getId())
        
    else Call Type = 2 (Service Booking)
        Controller->>Controller: Check serviceBooking != null
        Controller->>UserAuth: getBranchId()
        UserAuth-->>Controller: branchId
        Controller->>Controller: serviceBooking.setBranchId(branchId)
        Controller->>Controller: serviceBooking.setBookingDate(new Date())
        Controller->>Controller: serviceBooking.setStatus("Open")
        Controller->>Controller: serviceBooking.setCreatedBy(loginId)
        Controller->>ServiceBookingRepo: save(serviceBooking)
        ServiceBookingRepo->>DB: INSERT INTO service_booking
        DB-->>ServiceBookingRepo: Service booking saved with ID
        ServiceBookingRepo-->>Controller: Saved ServiceBooking entity
        Controller->>Controller: cceCallHdr.setServiceBookingId(serviceBooking.getId())
        Controller->>Controller: cceCallHdr.setCustomerMasterId(serviceBooking.getCustomerMaster().getId())
        
    else Call Type = 3 (Post Service Feedback)
        Controller->>Controller: Check callFeedback != null and size > 0
        Controller->>CallFeedbackRepo: saveAll(callFeedback)
        CallFeedbackRepo->>DB: INSERT INTO qa_crm_cce_call_feedback
        DB-->>CallFeedbackRepo: Feedback records saved
        CallFeedbackRepo-->>Controller: List of saved QaCrmCceCallFeedback
        Controller->>Controller: cceCallHdr.setJcId(callFeedback.get(0).getJcId())
        Controller->>CceCallRepo: getJcCustomerId(jcId)
        CceCallRepo->>DB: SELECT customer_id FROM job_card WHERE id = jcId
        DB-->>CceCallRepo: customerId
        CceCallRepo-->>Controller: customerId
        Controller->>Controller: cceCallHdr.setCustomerMasterId(customerId)
        
    else Call Type = 4 (Post Sales Feedback)
        Controller->>Controller: Check callFeedback != null and size > 0
        Controller->>CallFeedbackRepo: saveAll(callFeedback)
        CallFeedbackRepo->>DB: INSERT INTO qa_crm_cce_call_feedback
        DB-->>CallFeedbackRepo: Feedback records saved
        CallFeedbackRepo-->>Controller: List of saved QaCrmCceCallFeedback
        Controller->>Controller: cceCallHdr.setDcId(callFeedback.get(0).getDcId())
        Controller->>CceCallRepo: getDcCustomerId(dcId)
        CceCallRepo->>DB: SELECT customer_id FROM delivery_challan WHERE id = dcId
        DB-->>CceCallRepo: customerId
        CceCallRepo-->>Controller: customerId
        Controller->>Controller: cceCallHdr.setCustomerMasterId(customerId)
    end
    
    Controller->>CceCallRepo: save(cceCallHdr)
    CceCallRepo->>DB: INSERT INTO customer_care_exe_call_hdr
    DB-->>CceCallRepo: Call header saved with ID
    CceCallRepo-->>Controller: Saved CustomerCareExeCallHdr
    
    alt Call Type = 3 or 4 (with callFeedback)
        loop For each feedback item
            Controller->>CceCallRepo: setCallIdToQuestionair(feedbackId, callHdrId)
            CceCallRepo->>DB: UPDATE qa_crm_cce_call_feedback<br/>SET call_id = callHdrId WHERE id = feedbackId
            DB-->>CceCallRepo: Updated
        end
    end
    
    Controller->>Controller: Create ApiResponse with success message
    Controller-->>Client: HTTP 200 OK (Call record saved successfully)
```

---

## 2. Direct Survey Submission Flow

This flow shows how **direct surveys** are submitted with survey questions, answers, complaint recordings, and call attempt recordings.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as DirectSurveyController
    participant UserAuth as UserAuthentication
    participant DirectSurveyRepo as DirectSurveyRepo
    participant StorageService as StorageService
    participant SurveyRecordingRepo as SurveyRecordingRepo
    participant CallAttemptRepo as CallAttemptRepo
    participant DB as Database

    %% Direct Survey Submission Flow
    Note over Client,DB: Direct Survey Form Submission
    
    Client->>Controller: POST submitSurveyForm (SurveyHeader, complaintRecording files)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: surveyDto.setCreatedBy(loginId)
    
    alt Implement details exist
        Controller->>Controller: Filter implementDetails (hours and implementName not null)
        loop For each implement detail
            Controller->>Controller: implement.setDeleteFlag(true)
        end
    end
    
    alt Crops exist
        loop For each crop
            Controller->>Controller: crop.setDeleteFlag(false)
        end
    end
    
    alt Other purchase details exist
        Controller->>Controller: Filter otherPurchaseDetails (brand and model not null)
        loop For each other purchase detail
            Controller->>Controller: other.setDeleteFlag(false)
        end
    end
    
    alt Complaints exist
        Controller->>Controller: Filter complaints (department not null)
    end
    
    Controller->>DirectSurveyRepo: save(surveyDto)
    DirectSurveyRepo->>DB: INSERT INTO survey_header
    DB-->>DirectSurveyRepo: Survey header saved with surveyHdrId
    DirectSurveyRepo-->>Controller: Saved SurveyHeader
    
    alt Survey header saved successfully
        Controller->>DirectSurveyRepo: updateSurveyStatus(surveyRemdId)
        DirectSurveyRepo->>DB: UPDATE survey_reminder SET status = completed WHERE id = surveyRemdId
        DB-->>DirectSurveyRepo: Updated
        
        Controller->>DirectSurveyRepo: updateSurveySatisfactionLevel(surveyHdrId)
        DirectSurveyRepo->>DB: UPDATE survey_header SET satisfaction_level WHERE id = surveyHdrId
        DB-->>DirectSurveyRepo: Updated
    end
    
    alt Complaint recordings exist (max 5 files)
        loop For each complaint recording file
            Controller->>Controller: Create SurveyRecordings object
            Controller->>Controller: Build recordingName = CT timestamp filename
            Controller->>StorageService: store(file, recordingName)
            StorageService->>StorageService: Save file to storage location
            StorageService-->>Controller: File stored
            Controller->>Controller: recording.setSurveyHdrId(surveyHdrId)
            Controller->>Controller: recording.setRecording(recordingName)
            Controller->>SurveyRecordingRepo: save(recording)
            SurveyRecordingRepo->>DB: INSERT INTO survey_recordings
            DB-->>SurveyRecordingRepo: Recording saved
        end
    end
    
    Controller->>Controller: Create ApiResponse with success message
    Controller-->>Client: HTTP 200 OK (Survey details saved successfully)

    %% Call Attempt Submission Flow
    Note over Client,DB: Call Attempt Submission
    
    Client->>Controller: POST submitCallAttempt (CallAttempt with callRecording files)
    
    alt Call recordings exist (max 5 files)
        loop For each call recording file
            Controller->>Controller: Build recordingName = CA timestamp filename
            Controller->>StorageService: store(file, recordingName)
            StorageService->>StorageService: Save file to storage location
            StorageService-->>Controller: File stored
            Controller->>Controller: callDetails.setRecordingFileName(recordingName)
        end
    end
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: callDetails.setCreatedBy(loginId)
    
    Controller->>CallAttemptRepo: save(callDetails)
    CallAttemptRepo->>DB: INSERT INTO call_attempt
    DB-->>CallAttemptRepo: Call attempt saved
    CallAttemptRepo-->>Controller: Saved CallAttempt
    
    Controller->>Controller: Create ApiResponse with success message
    Controller-->>Client: HTTP 200 OK (Call attempt details saved successfully)
```

---

## 3. Toll-Free Call Creation Flow

This flow shows how **toll-free calls** are created with complaint details and complaint recording files.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TollFreeController
    participant UserAuth as UserAuthentication
    participant TollFreeRepo as TollFreeRepo
    participant StorageService as StorageService
    participant DB as Database

    %% Toll-Free Call Creation Flow
    Note over Client,DB: Toll-Free Call Creation
    
    Client->>Controller: POST /api/crm/crmmodule/tollFreeCall/create<br/>(TollFreeHdr, complaintRecording files)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: callData.setCreatedBy(loginId)
    Controller->>Controller: callData.setCreatedDate(new Date())
    
    alt Complaint detail list exists
        Controller->>Controller: Filter complaintDtlList (complaintType != null)
        loop For each complaint detail
            Controller->>Controller: comp.setComplaintNumber("COMP/" + timestamp)
        end
    end
    
    Controller->>Controller: Initialize uploads list
    
    alt Complaint recordings exist
        loop For each complaint recording file
            Controller->>Controller: Create TollFreeFileUplaod object
            Controller->>Controller: Get original filename
            Controller->>Controller: Build recordingName = "toll_free_call_" + timestamp + "_" + filename
            Controller->>StorageService: store(recording, recordingName)
            StorageService->>StorageService: Save file to storage location
            StorageService-->>Controller: File stored
            
            Controller->>Controller: fileUpload.setContentType(recording.getContentType())
            Controller->>Controller: fileUpload.setTollFreeCallHdr(callData)
            Controller->>Controller: fileUpload.setFileName(recordingName)
            Controller->>Controller: fileUpload.setOriginalName(fileName)
            Controller->>Controller: fileUpload.setCreatedDate(new Date())
            Controller->>Controller: uploads.add(fileUpload)
        end
    end
    
    Controller->>Controller: callData.setFileUploadList(uploads)
    
    Controller->>TollFreeRepo: save(callData)
    TollFreeRepo->>DB: INSERT INTO toll_free_hdr<br/>INSERT INTO toll_free_dtl<br/>INSERT INTO toll_free_file_upload
    DB-->>TollFreeRepo: Toll-free call saved with ID
    TollFreeRepo-->>Controller: Saved TollFreeHdr
    
    Controller->>Controller: Create ApiResponse with success message
    Controller-->>Client: HTTP 200 OK (Toll-free record saved successfully)
```

---

## 4. Complaint Resolution Search and Update Flow

This flow shows how **complaint resolution** searches are performed and how resolution details are updated.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as ComplaintResolutionController
    participant UserAuth as UserAuthentication
    participant TollFreeRepo as TollFreeRepo
    participant DB as Database

    %% Complaint Resolution Search Flow
    Note over Client,DB: Complaint Resolution Search
    
    Client->>Controller: POST /api/crm/crmmodule/complaintOrQueryResolution/getComplaintOrQueryResolution<br/>(ComplaintResolutionSearchRequest)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>TollFreeRepo: getComplaintResoltionSearchSearch(<br/>dealerId, fromDate, toDate, status,<br/>complaintType, department, page, size, username)
    
    TollFreeRepo->>DB: SELECT complaint details with filters<br/>WHERE dealer_id = dealerId<br/>AND date BETWEEN fromDate AND toDate<br/>AND status = status<br/>AND complaint_type = complaintType<br/>AND department = department<br/>ORDER BY ... LIMIT page, size
    DB-->>TollFreeRepo: List of ComplaintResolutionResponseDto
    TollFreeRepo-->>Controller: List of complaint resolution records
    
    Controller->>Controller: Extract totalCount from first record
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setResult(callList)
    Controller->>Controller: apiResponse.setCount(totalCount)
    Controller->>Controller: apiResponse.setMessage("ComplaintResolution Search Get Successfully")
    
    Controller-->>Client: HTTP 200 OK (Complaint resolution search results)

    %% Complaint Resolution Update Flow
    Note over Client,DB: Complaint Resolution Update
    
    Client->>Controller: POST /api/crm/crmmodule/complaintOrQueryResolution/updateResolutionDetails<br/>(ComplaintResolutionRequest)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    Controller->>TollFreeRepo: updateComplaintResolutionDetails(<br/>complaintId, complaintType,<br/>reasonForDelayFrt, reasonForDelayFrt,<br/>actionTaken, isInvalid, loginId)
    
    TollFreeRepo->>DB: UPDATE toll_free_dtl<br/>SET complaint_type = complaintType,<br/>reason_for_delay_frt = reasonForDelayFrt,<br/>action_taken = actionTaken,<br/>is_invalid = isInvalid,<br/>last_modified_by = loginId,<br/>last_modified_date = NOW()<br/>WHERE id = complaintId
    DB-->>TollFreeRepo: Complaint resolution updated
    TollFreeRepo-->>Controller: Update successful
    
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setMessage("ComplaintResolution update Successfully")
    
    Controller-->>Client: HTTP 200 OK (Complaint resolution updated successfully)
```

---

## 5. CCE Call Search Flow

This flow shows how **CCE calls** are searched with various filters including call type, call status, mobile number, customer name, and date range.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as CustomerCareExeCallController
    participant UserAuth as UserAuthentication
    participant CceCallRepo as CustomerCareExeCallRepo
    participant DB as Database

    %% CCE Call Search Flow
    Note over Client,DB: CCE Call Search
    
    Client->>Controller: POST /api/crm/crmmodule/customerCareExeCall/search<br/>(CallSearchRequest)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    
    Controller->>CceCallRepo: getCRMCallSearch(<br/>dealerId, fromDate, toDate,<br/>mobileNo, callType, callStatus,<br/>customerName, page, size)
    
    CceCallRepo->>DB: SELECT call details with filters<br/>FROM customer_care_exe_call_hdr cce<br/>LEFT JOIN enquiry e ON cce.sales_enquiry_id = e.id<br/>LEFT JOIN service_booking sb ON cce.service_booking_id = sb.id<br/>WHERE cce.dealer_id = dealerId<br/>AND cce.call_date BETWEEN fromDate AND toDate<br/>AND (mobileNo IS NULL OR mobile_number LIKE mobileNo)<br/>AND (callType IS NULL OR call_type_id = callType)<br/>AND (callStatus IS NULL OR call_status_id = callStatus)<br/>AND (customerName IS NULL OR customer_name LIKE customerName)<br/>ORDER BY ... LIMIT page, size
    DB-->>CceCallRepo: List of CRMCallSearchResponseDto with totalCount
    CceCallRepo-->>Controller: List of call records
    
    Controller->>Controller: Extract totalCount from first record (if exists)
    Controller->>Controller: Create ApiResponse
    Controller->>Controller: apiResponse.setResult(callList)
    Controller->>Controller: apiResponse.setCount(totalCount)
    Controller->>Controller: apiResponse.setMessage("CRM CCE CALL Search Get Successfully")
    
    Controller-->>Client: HTTP 200 OK (CCE call search results with pagination)
```

---

## 6. Survey Summary Report Generation Flow

This flow shows how **survey summary reports** are generated with various filters and exported to Excel format.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as SurveySummaryReportController
    participant UserAuth as UserAuthentication
    participant SurveyReportRepo as SurveySummaryReportRepo
    participant ExcelGenerator as ExcelCellGenerator
    participant DB as Database

    %% Survey Summary Report Flow
    Note over Client,DB: Survey Summary Report Generation
    
    Client->>Controller: POST /api/crm/crmmodule/surveysummaryreport/getSurveySummaryReport<br/>(filters: surveyName, fromDate, toDate, satisfactionLevel)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId
    
    Controller->>SurveyReportRepo: getSurveySummaryReport(<br/>dealerId, surveyName, fromDate, toDate, satisfactionLevel)
    
    SurveyReportRepo->>DB: SELECT survey summary data FROM survey_header<br/>JOIN survey_questions WHERE dealer_id = dealerId<br/>AND survey_date BETWEEN fromDate AND toDate<br/>GROUP BY survey_hdr_id, question_id
    DB-->>SurveyReportRepo: List of SurveySummaryReportDto
    SurveyReportRepo-->>Controller: Survey summary data
    
    Controller->>Controller: Process and aggregate survey data
    Controller->>Controller: Create SurveySummaryReportResponse
    Controller->>Controller: apiResponse.setResult(surveySummaryData)
    Controller->>Controller: apiResponse.setMessage("Survey Summary Report Get Successfully")
    
    Controller-->>Client: HTTP 200 OK (Survey summary report data)

    %% Survey Report Excel Export Flow
    Note over Client,DB: Survey Report Excel Export
    
    Client->>Controller: GET /api/crm/crmmodule/surveysummaryreport/exportSurveyDetailsExcel<br/>(filters: dealerId, surveyName, fromDate, toDate)
    
    Controller->>SurveyReportRepo: getSurveyDetailsExcelReport(<br/>dealerId, surveyName, fromDate, toDate)
    
    SurveyReportRepo->>DB: SELECT detailed survey data FROM survey_header<br/>JOIN survey_questions JOIN survey_answers<br/>WHERE dealer_id = dealerId AND survey_date BETWEEN fromDate AND toDate<br/>ORDER BY survey_date, question_order
    DB-->>SurveyReportRepo: List of SurveyDetailsExcelReportResponse
    SurveyReportRepo-->>Controller: Detailed survey data
    
    Controller->>ExcelGenerator: Generate Excel file with survey data
    ExcelGenerator->>ExcelGenerator: Create workbook and sheets
    ExcelGenerator->>ExcelGenerator: Add headers and data rows
    ExcelGenerator->>ExcelGenerator: Format cells and apply styles
    ExcelGenerator-->>Controller: Excel file bytes (ByteArrayInputStream)
    
    Controller->>Controller: Set HTTP response headers<br/>Content-Type: Excel, Content-Disposition: attachment filename=survey_report.xlsx
    
    Controller-->>Client: HTTP Response with Excel file stream
    Client->>Client: Download Excel file
```

---

## Summary

The **CRM module** provides comprehensive **Customer Relationship Management** functionality for the KUBOTA DMS:

- **Customer Care Executive Calls**:
  - Supports 4 call types: Sales Enquiry, Service Booking, Post Service Feedback, Post Sales Feedback
  - Automatically creates related entities (Enquiry, ServiceBooking) based on call type
  - Links call feedback questionnaires to job cards or delivery challans
  - Provides search functionality with multiple filters (date range, call type, status, customer details)

- **Direct Surveys**:
  - Survey form submission with questions, answers, and sub-answers
  - Complaint recording file uploads (up to 5 files)
  - Call attempt tracking with recording file uploads
  - Survey status and satisfaction level management
  - Machine details, crops grown, and implement tracking

- **Toll-Free Calls**:
  - Toll-free call creation with complaint details
  - Complaint recording file uploads
  - Complaint number auto-generation
  - Integration with dealer and TSM (Territory Sales Manager) details
  - Call history tracking by customer and VIN

- **Complaint Resolution**:
  - Complaint search with filters (date range, status, complaint type, department)
  - Resolution details update (action taken, reason for delay, invalid flag)
  - Pagination support for large result sets

- **Reports and Analytics**:
  - Survey summary reports with satisfaction level filtering
  - Survey details Excel export
  - Customer satisfaction score reports
  - Monthly failure code summary reports
  - Toll-free call reports

The CRM module integrates with multiple other modules (Sales, Service, Warranty) to provide a unified customer interaction and feedback management system, enabling dealers and headquarters to track customer satisfaction, complaints, and service quality effectively.

