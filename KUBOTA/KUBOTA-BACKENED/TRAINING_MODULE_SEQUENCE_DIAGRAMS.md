## Training Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.training` module:

- **Training Program Calendar Management** (Create, Update, Search, Approve Nominees).
- **Training Nomination Management** (Create, Update, Search Nominations).
- **Attendance Sheet Management** (Create, Update, Search Attendance, Generate Certificates).
- **Training Program Report** (Search and Export Training Reports).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the Training module.

---

## 1. Training Program Calendar - Save Flow

This flow shows how **Training Program Calendar** is created with header, dealer details, and holiday details.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingProgramCalendarController
    participant UserAuth as UserAuthentication
    participant TpcRepo as TrainingProgramCalendarRepo
    participant DB as Database

    %% Training Program Calendar Save Flow
    Note over Client,DB: Training Program Calendar Save
    
    Client->>Controller: POST /api/training/training-program-calender/saveTrainingProgramCalendar<br/>(TrainingProgramCalendarHeader)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: header.setCreatedBy(loginId)
    Controller->>Controller: header.setCreatedDate(new Date())
    Controller->>Controller: header.setActive('Y')
    
    alt Dealer Details Exist
        Controller->>Controller: header.getTpcDealerDetails().forEach(dealerDtl)
        Controller->>Controller: dealerDtl.setCreatedBy(loginId)
        Controller->>Controller: dealerDtl.setCreatedDate(new Date())
        Controller->>Controller: dealerDtl.setActive('Y')
    end
    
    alt Holiday Details Exist
        Controller->>Controller: header.getTpcHolidayDetails().forEach(holiday)
        Controller->>Controller: holiday.setActive('Y')
    end
    
    Controller->>TpcRepo: save(header)
    TpcRepo->>DB: INSERT INTO TR_PROG_HDR<br/>INSERT INTO TR_PROG_DLR_DTL<br/>INSERT INTO TR_PROG_HOLIDAY_DTL
    DB-->>TpcRepo: Training Program Calendar saved with ID
    TpcRepo-->>Controller: Saved TrainingProgramCalendarHeader
    
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setMessage("Training Program Calendar Save Successfully")
    Controller-->>Client: ResponseEntity<ApiResponse> (Success)
    
    alt Exception Occurs
        Controller->>Controller: response.setStatus(HttpStatus.BAD_REQUEST)
        Controller->>Controller: response.setMessage("Training Program Claender can't Save")
        Controller-->>Client: ResponseEntity<ApiResponse> (Error)
    end
```

---

## 2. Training Program Calendar - Update Flow

This flow shows how **Training Program Calendar** is updated with new dealer and holiday details.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingProgramCalendarController
    participant UserAuth as UserAuthentication
    participant TpcRepo as TrainingProgramCalendarRepo
    participant DB as Database

    %% Training Program Calendar Update Flow
    Note over Client,DB: Training Program Calendar Update
    
    Client->>Controller: POST /api/training/training-program-calender/updateTrainingProgramCalendar<br/>(TrainingProgramCalendarHeader)
    
    Controller->>TpcRepo: deleteDealerDetails(update.getProgramId())
    TpcRepo->>DB: DELETE FROM TR_PROG_DLR_DTL WHERE program_id = programId
    DB-->>TpcRepo: Dealer details deleted
    
    Controller->>TpcRepo: deleteHolidayDates(update.getProgramId())
    TpcRepo->>DB: DELETE FROM TR_PROG_HOLIDAY_DTL WHERE program_id = programId
    DB-->>TpcRepo: Holiday dates deleted
    
    Controller->>TpcRepo: getOne(update.getProgramId())
    TpcRepo->>DB: SELECT * FROM TR_PROG_HDR WHERE program_id = programId
    DB-->>TpcRepo: TrainingProgramCalendarHeader
    TpcRepo-->>Controller: tpcUpdate (existing entity)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    Controller->>Controller: tpcUpdate.setModifiedBy(loginId)
    Controller->>Controller: tpcUpdate.setModifiedDate(new Date())
    Controller->>Controller: Update all header fields<br/>(trainingModuleId, location, dates, etc.)
    
    Controller->>Controller: tpcUpdate.setTpcDealerDetails(update.getTpcDealerDetails())
    Controller->>Controller: tpcUpdate.setTpcHolidayDetails(update.getTpcHolidayDetails())
    
    Controller->>Controller: tpcUpdate.getTpcDealerDetails().forEach(dealerDtl)
    alt New Dealer Detail (programDlrDtlId == null)
        Controller->>Controller: dealerDtl.setCreatedBy(loginId)
        Controller->>Controller: dealerDtl.setCreatedDate(new Date())
        Controller->>Controller: dealerDtl.setActive('Y')
    else Existing Dealer Detail
        Controller->>Controller: dealerDtl.setActive('Y')
    end
    
    Controller->>Controller: tpcUpdate.getTpcHolidayDetails().forEach(holiday)
    Controller->>Controller: holiday.setActive('Y')
    
    Controller->>TpcRepo: save(tpcUpdate)
    TpcRepo->>DB: UPDATE TR_PROG_HDR<br/>INSERT/UPDATE TR_PROG_DLR_DTL<br/>INSERT/UPDATE TR_PROG_HOLIDAY_DTL
    DB-->>TpcRepo: Training Program Calendar updated
    TpcRepo-->>Controller: Updated TrainingProgramCalendarHeader
    
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setMessage("Training Program Calendar Update Successfully")
    Controller-->>Client: ResponseEntity<ApiResponse> (Success)
```

---

## 3. Training Program Calendar - Search Flow

This flow shows how **Training Program Calendar** records are searched with various filters.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingProgramCalendarController
    participant UserAuth as UserAuthentication
    participant TpcRepo as TrainingProgramCalendarRepo
    participant DB as Database

    %% Training Program Calendar Search Flow
    Note over Client,DB: Training Program Calendar Search
    
    Client->>Controller: POST /api/training/training-program-calender/tpcSearch<br/>(TrainingProgCalendarSearchDto)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>TpcRepo: tpcSearch(username, departmentName, programNo,<br/>trainingLocationId, trainingModuleId,<br/>fromDate, toDate, page, size)
    TpcRepo->>DB: CALL sp_Search_Training_Prog_HDR<br/>(userCode, departmentName, programNo,<br/>trainingLocationId, trainingModuleId,<br/>fromDate, toDate, page, size)
    DB-->>TpcRepo: List<TrainingProgCalendarSearchResponse>
    TpcRepo-->>Controller: searchResult
    
    alt Search Result Not Empty
        Controller->>Controller: count = searchResult.get(0).getRecordCount()
    else Search Result Empty
        Controller->>Controller: count = 0
    end
    
    Controller->>Controller: response.setMessage("Training Prog Search get successfully")
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setResult(searchResult)
    Controller->>Controller: response.setCount(count)
    Controller-->>Client: ResponseEntity<ApiResponse> with search results
```

---

## 4. Training Program Calendar - Approve Nominees Flow

This flow shows how **Nominees** are approved or rejected for training programs.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingProgramCalendarController
    participant TpcRepo as TrainingProgramCalendarRepo
    participant DB as Database

    %% Nominees Approval Flow
    Note over Client,DB: Approve Nominees
    
    Client->>Controller: POST /api/training/training-program-calender/getNomineesApproval<br/>(TrainingProgCalendarApprovalDto)
    
    Controller->>Controller: Check nomineesApproval.getNomineesApproval() != null<br/>AND size > 0
    
    Controller->>Controller: nomineesApproval.getNomineesApproval().forEach(data)
    Controller->>TpcRepo: aproveNominies(data.getNominationStatus(),<br/>data.getAttendedStatus(),<br/>data.getProgramNominationDtlId())
    TpcRepo->>DB: UPDATE TR_PROG_NOMIN_DTL<br/>SET Nomination_Status = :nominationStatus,<br/>Attended_status = :attended<br/>WHERE Program_Nomination_DTL_id = :programNominationDtlId
    DB-->>TpcRepo: Nominee status updated
    TpcRepo-->>Controller: Update completed
    
    Controller->>Controller: response.setMessage("Nominees Approval Successfully")
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller-->>Client: ResponseEntity<ApiResponse> (Success)
```

---

## 5. Training Nomination - Save Flow

This flow shows how **Training Nomination** is created with header and nomination details.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingNominationController
    participant UserAuth as UserAuthentication
    participant TnRepo as TrainingNominationRepo
    participant DB as Database

    %% Training Nomination Save Flow
    Note over Client,DB: Training Nomination Save
    
    Client->>Controller: POST /api/training/nomination/saveNomination<br/>(TrainingNominationHeader)
    
    alt Nomination ID Exists (Update)
        Controller->>UserAuth: getLoginId()
        UserAuth-->>Controller: loginId
        Controller->>Controller: nomination.setModifiedBy(loginId)
        Controller->>Controller: nomination.setModifiedDate(new Date())
        Controller->>TnRepo: findByNominationId(nomination.getNominationId())
        TnRepo->>DB: SELECT * FROM TR_PROG_NOMIN_HDR WHERE nomination_id = nominationId
        DB-->>TnRepo: TrainingNominationHeader
        TnRepo-->>Controller: existingNomination
        Controller->>Controller: dls = existingNomination.getNominationDetails()
    else New Nomination (Create)
        Controller->>UserAuth: getLoginId()
        UserAuth-->>Controller: loginId
        Controller->>UserAuth: getDealerId()
        UserAuth-->>Controller: dealerId
        Controller->>Controller: nomination.setCreatedBy(loginId)
        Controller->>Controller: nomination.setCreatedDate(new Date())
        Controller->>Controller: nomination.setDealerId(dealerId)
        Controller->>Controller: dls = null
    end
    
    alt Nomination Details Exist
        Controller->>Controller: dtlids = dls.stream().map(d -> d.getNominationDtlId()).collect()
        Controller->>Controller: nomination.getNominationDetails().forEach(nominee)
        Controller->>Controller: nominee.setCreatedBy(loginId)
        Controller->>Controller: nominee.setCreatedDate(new Date())
        Controller->>Controller: nominee.setActive('Y')
        
        alt Existing Detail (nominationDtlId != null)
            Controller->>Controller: dtlids.remove(nominee.getNominationDtlId())
        end
    end
    
    alt Details to Delete Exist (dtlids.size() > 0)
        Controller->>TnRepo: deleteDtls(dtlids)
        TnRepo->>DB: DELETE FROM TR_PROG_NOMIN_DTL<br/>WHERE Program_Nomination_DTL_id IN (:ids)
        DB-->>TnRepo: Details deleted
    end
    
    Controller->>TnRepo: save(nomination)
    TnRepo->>DB: INSERT/UPDATE TR_PROG_NOMIN_HDR<br/>INSERT/UPDATE TR_PROG_NOMIN_DTL
    DB-->>TnRepo: Nomination saved with ID
    TnRepo-->>Controller: Saved TrainingNominationHeader
    
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setMessage("Training Nomination Save Successfully")
    Controller-->>Client: ResponseEntity<ApiResponse> (Success)
    
    alt Exception Occurs
        Controller->>Controller: response.setStatus(HttpStatus.BAD_REQUEST)
        Controller->>Controller: response.setMessage("Training Nomination can't Save")
        Controller-->>Client: ResponseEntity<ApiResponse> (Error)
    end
```

---

## 6. Training Nomination - Search Flow

This flow shows how **Training Nomination** records are searched with filters.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingNominationController
    participant UserAuth as UserAuthentication
    participant TnRepo as TrainingNominationRepo
    participant DB as Database

    %% Training Nomination Search Flow
    Note over Client,DB: Training Nomination Search
    
    Client->>Controller: POST /api/training/nomination/nomineeSearch<br/>(TrainingNominationSearchDto)
    
    Controller->>UserAuth: getLoginId()
    UserAuth-->>Controller: loginId
    
    Controller->>TnRepo: nomineeSearch(loginId, programId, nomineeId,<br/>fromDate, toDate, page, size)
    TnRepo->>DB: CALL sp_search_training_Nominee_DTL<br/>(loginId, programId, nomineeId,<br/>fromDate, toDate, page, size)
    DB-->>TnRepo: List<TrainingNominationSearchResponse>
    TnRepo-->>Controller: searchResult
    
    alt Search Result Not Empty
        Controller->>Controller: count = searchResult.get(0).getRecordCount()
    else Search Result Empty
        Controller->>Controller: count = 0
    end
    
    Controller->>Controller: response.setMessage("Nomination Search get successfully")
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setResult(searchResult)
    Controller->>Controller: response.setCount(count)
    Controller-->>Client: ResponseEntity<ApiResponse> with search results
```

---

## 7. Training Nomination - Get Employee Details Flow

This flow shows how **Employee Details** are retrieved for nomination.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingNominationController
    participant TnRepo as TrainingNominationRepo
    participant DB as Database

    %% Get Employee Details for Nomination Flow
    Note over Client,DB: Get Nominee Employee Details
    
    Client->>Controller: GET /api/training/nomination/getNomineeEmpDetails<br/>?programId={programId}&employeeId={employeeId}
    
    Controller->>TnRepo: getNomineeEmpDetails(programId, employeeId)
    TnRepo->>DB: CALL sp_Get_Employee_Detail_For_Nomination<br/>(programId, employeeId)
    DB-->>TnRepo: Map<String, Object> (employee details)
    TnRepo-->>Controller: nominiDetails
    
    Controller->>Controller: response.setMessage("Nominee details get Successfully")
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setResult(nominiDetails)
    Controller-->>Client: ResponseEntity<ApiResponse> with employee details
```

---

## 8. Attendance Sheet - Save Flow

This flow shows how **Attendance Sheet** is created with attendance details, employee indexes, trainers, and documents.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingAttendanceSheetController
    participant AttendenceService as AttendenceServiceImpl
    participant TasRepo as TrainingAttendanceSheetRepo
    participant DocRepo as TrainingAttendanceSheetDocRepo
    participant StorageService as StorageService
    participant DB as Database

    %% Attendance Sheet Save Flow
    Note over Client,DB: Attendance Sheet Save
    
    Client->>Controller: POST /api/training/attendance-sheet/saveNomineAttendanceSheet<br/>(TrainingAttendanceSheetDto, List<MultipartFile>)
    
    Controller->>AttendenceService: nomineAttendanceSheetSave(tnAttendanceSheet, tnAttendanceSheetDocs)
    
    AttendenceService->>AttendenceService: tnAttendanceSheet.getAttendanceDtl().forEach(attendancDtl)
    AttendenceService->>TasRepo: save(attendancDtl)
    TasRepo->>DB: INSERT INTO TR_PROG_NOMIN_ATTENDANCE_DTL
    DB-->>TasRepo: Attendance detail saved
    TasRepo-->>AttendenceService: Saved NomineAttendanceSheet
    
    AttendenceService->>AttendenceService: avgGrowthIndex = tnAttendanceSheet.getAvgGrowthIndex()
    AttendenceService->>AttendenceService: tnAttendanceSheet.getNominationEmpIndexs().forEach(nominationIndes)
    alt Nomination Detail ID Exists
        AttendenceService->>TasRepo: updateIndexesValuesForEmployees(programId, nominationDtlId,<br/>preTest, postTest, growthIndex, avgGrowthIndex)
        TasRepo->>DB: CALL SP_TR_UPDATE_INDEXES_FOR_EMPLOYEES<br/>(programId, nominationDtlId, preTest,<br/>postTest, growthIndex, avgGrowthIndex)
        DB-->>TasRepo: Indexes updated
    end
    
    alt Trainers Exist (trainer1 != null OR trainer2 != null)
        AttendenceService->>TasRepo: updateTrainerInTRProgHrd(trainer1, trainer2, programId)
        TasRepo->>DB: UPDATE TR_PROG_HDR<br/>SET Trainer_1 = :trainer1, Trainer_2 = :trainer2<br/>WHERE program_id = :programeId
        DB-->>TasRepo: Trainers updated
    end
    
    alt Documents Exist (size <= 5 AND !isEmpty)
        AttendenceService->>AttendenceService: tnAttendanceSheetDocs.forEach(multipartFile)
        AttendenceService->>AttendenceService: fileName = multipartFile.getOriginalFilename()
        AttendenceService->>AttendenceService: tasDocName = "TAS" + System.currentTimeMillis() + "_" + fileName
        AttendenceService->>StorageService: store(multipartFile, tasDocName)
        StorageService-->>AttendenceService: File stored
        
        AttendenceService->>AttendenceService: tasDoc = new TrainingAttendanceSheetDoc()
        AttendenceService->>AttendenceService: tasDoc.setProgramId(programId)
        AttendenceService->>AttendenceService: tasDoc.setDocumentName(tasDocName)
        AttendenceService->>AttendenceService: tasDoc.setDocumentPath(tasDocName)
        AttendenceService->>AttendenceService: tasDoc.setCreatedDate(new Date())
        AttendenceService->>DocRepo: save(tasDoc)
        DocRepo->>DB: INSERT INTO TR_PROG_NOMIN_DOC_DTL
        DB-->>DocRepo: Document saved
    end
    
    AttendenceService-->>Controller: Save completed
    
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setMessage("Attendance Sheet Save Successfully")
    Controller-->>Client: ResponseEntity<ApiResponse> (Success)
    
    alt Exception Occurs
        Controller->>Controller: response.setStatus(HttpStatus.BAD_REQUEST)
        Controller->>Controller: response.setMessage("Attendance Sheet can't Save")
        Controller-->>Client: ResponseEntity<ApiResponse> (Error)
    end
```

---

## 9. Attendance Sheet - Update Flow

This flow shows how **Attendance Sheet** is updated with new attendance details and indexes.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingAttendanceSheetController
    participant AttendenceService as AttendenceServiceImpl
    participant TasRepo as TrainingAttendanceSheetRepo
    participant DB as Database

    %% Attendance Sheet Update Flow
    Note over Client,DB: Attendance Sheet Update
    
    Client->>Controller: POST /api/training/attendance-sheet/updateNomineAttendanceSheet<br/>(TrainingAttendanceSheetDto)
    
    Controller->>AttendenceService: updateAttendence(tnAttendanceSheetUpdate)
    
    AttendenceService->>TasRepo: deleteOldAttendance(tnAttendanceSheetUpdate.getProgramId())
    TasRepo->>DB: CALL SP_TR_DELETE_OLD_ATTENDANCE(:programId)
    DB-->>TasRepo: Old attendance deleted
    
    AttendenceService->>AttendenceService: tnAttendanceSheetUpdate.getAttendanceDtl().forEach(attendancDtl)
    alt Training Date and Nomination Detail ID Exist
        AttendenceService->>TasRepo: save(attendancDtl)
        TasRepo->>DB: INSERT INTO TR_PROG_NOMIN_ATTENDANCE_DTL
        DB-->>TasRepo: Attendance detail saved
    end
    
    AttendenceService->>AttendenceService: avgGrowthIndex = tnAttendanceSheetUpdate.getAvgGrowthIndex()
    AttendenceService->>AttendenceService: tnAttendanceSheetUpdate.getNominationEmpIndexs().forEach(nominationIndes)
    alt Nomination Detail ID Exists
        AttendenceService->>TasRepo: updateIndexesValuesForEmployees(programId, nominationDtlId,<br/>preTest, postTest, growthIndex, avgGrowthIndex)
        TasRepo->>DB: CALL SP_TR_UPDATE_INDEXES_FOR_EMPLOYEES<br/>(programId, nominationDtlId, preTest,<br/>postTest, growthIndex, avgGrowthIndex)
        DB-->>TasRepo: Indexes updated
    end
    
    alt Trainers Exist (trainer1 != null OR trainer2 != null)
        AttendenceService->>TasRepo: updateTrainerInTRProgHrd(trainer1, trainer2, programId)
        TasRepo->>DB: UPDATE TR_PROG_HDR<br/>SET Trainer_1 = :trainer1, Trainer_2 = :trainer2<br/>WHERE program_id = :programeId
        DB-->>TasRepo: Trainers updated
    end
    
    AttendenceService-->>Controller: Update completed
    
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setMessage("Attendance Sheet Update Successfully")
    Controller-->>Client: ResponseEntity<ApiResponse> (Success)
    
    alt Exception Occurs
        Controller->>Controller: response.setStatus(HttpStatus.BAD_REQUEST)
        Controller->>Controller: response.setMessage("Attendance Sheet can't Update")
        Controller-->>Client: ResponseEntity<ApiResponse> (Error)
    end
```

---

## 10. Attendance Sheet - Search Flow

This flow shows how **Attendance Sheet** records are searched with various filters.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingAttendanceSheetController
    participant UserAuth as UserAuthentication
    participant TasRepo as TrainingAttendanceSheetRepo
    participant DB as Database

    %% Attendance Sheet Search Flow
    Note over Client,DB: Attendance Sheet Search
    
    Client->>Controller: POST /api/training/attendance-sheet/attendaceSheetSearch<br/>(TrainingAttendanceSheetSearchDto)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>TasRepo: attendaceSheetSearch(username, departmentName, programNumber,<br/>trainingLocationId, trainingModuleId,<br/>fromDate, toDate, page, size)
    TasRepo->>DB: CALL sp_Search_Training_PROG_Nominee_Attendance<br/>(userCode, departmentName, programNo,<br/>trainingLocationId, trainingModuleId,<br/>fromDate, toDate, page, size)
    DB-->>TasRepo: List<TrainingAttendanceSheetSearchResponse>
    TasRepo-->>Controller: search
    
    alt Search Result Not Empty
        Controller->>Controller: count = search.get(0).getRecordCount()
    else Search Result Empty
        Controller->>Controller: count = 0
    end
    
    Controller->>Controller: response.setMessage("Nomination Search get successfully")
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setResult(search)
    Controller->>Controller: response.setCount(count)
    Controller-->>Client: ResponseEntity<ApiResponse> with search results
```

---

## 11. Attendance Sheet - View/Edit Data Flow

This flow shows how **Attendance Sheet** view/edit data is retrieved including header, trainers, attendance details, employee indexes, and documents.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingAttendanceSheetController
    participant TpcRepo as TrainingProgramCalendarRepo
    participant TasRepo as TrainingAttendanceSheetRepo
    participant DB as Database

    %% Attendance Sheet View/Edit Flow
    Note over Client,DB: Get Attendance Sheet View/Edit Data
    
    Client->>Controller: GET /api/training/attendance-sheet/getViewEditData<br/>?programId={programId}
    
    Controller->>TpcRepo: getHeaderDetailsByProgramId(programId, "Header")
    TpcRepo->>DB: CALL sp_get_Training_Prog_DTL(:programeId, 'Header')
    DB-->>TpcRepo: Map<String, Object> (header details)
    TpcRepo-->>Controller: tcpHeader
    
    Controller->>TasRepo: viewTrainers(programId)
    TasRepo->>DB: SELECT Trainer_1 as trainer1, Trainer_2 as trainer2<br/>FROM TR_PROG_HDR WHERE program_id = :programeId
    DB-->>TasRepo: Map<String, Object> (trainers)
    TasRepo-->>Controller: trainers
    
    Controller->>TasRepo: getProgramAndNomineeDtlView(programId)
    TasRepo->>DB: CALL sp_Get_PROG_Nominee_Person_DTL_For_View(:programId)
    DB-->>TasRepo: List<Map<String, Object>> (attendance details)
    TasRepo-->>Controller: attendanceDtl
    
    Controller->>TasRepo: viewNominationEmpIndex(programId)
    TasRepo->>DB: CALL SP_TR_GET_NOMINATION_EMP_INDEX(:programeId)
    DB-->>TasRepo: List<Map<String, Object>> (employee indexes)
    TasRepo-->>Controller: nominEmpIndex
    
    Controller->>TasRepo: viewAttendanceDoc(programId)
    TasRepo->>DB: SELECT id, Document_Name, Document_Path<br/>FROM TR_PROG_NOMIN_DOC_DTL WHERE Program_id = :programeId
    DB-->>TasRepo: List<Map<String, Object>> (documents)
    TasRepo-->>Controller: docsDtl
    
    Controller->>Controller: map.put("TcpHeaderDtl", tcpHeader)
    Controller->>Controller: map.put("Trainers", trainers)
    Controller->>Controller: map.put("AttendanceDtl", attendanceDtl)
    Controller->>Controller: map.put("nominEmpIndex", nominEmpIndex)
    Controller->>Controller: map.put("docsDtl", docsDtl)
    
    Controller->>Controller: response.setMessage("Get TN view Data")
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setResult(map)
    Controller-->>Client: ResponseEntity<ApiResponse> with view/edit data
```

---

## 12. Attendance Sheet - Generate Training Certificate Flow

This flow shows how **Training Certificates** are generated as PDF using Jasper Reports.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingAttendanceSheetController
    participant JasperPrintService as JasperPrintService
    participant DB as Database

    %% Generate Training Certificate Flow
    Note over Client,DB: Generate Training Certificate
    
    Client->>Controller: POST /api/training/attendance-sheet/generateTrainingCertificate<br/>(TrainingCertificateDto)
    
    Controller->>Controller: filePath = request.getServletContext().getRealPath("/WEB-INF/reports/")
    Controller->>Controller: outputStream = response.getOutputStream()
    Controller->>Controller: response.setContentType("application/pdf")
    Controller->>Controller: response.setHeader("Content-Disposition", "inline; filename=Training_Certificate-{random}-{timestamp}.pdf")
    
    Controller->>Controller: jasperfile = filePath + "printingtrainingprogramCertificate.jasper"
    Controller->>Controller: jasperParameter.put("programId", certificateDto.getProgramId())
    Controller->>Controller: jasperParameter.put("employeeId", certificateDto.getEmployeeId())
    Controller->>Controller: jasperParameter.put("dealerId", certificateDto.getDealerId())
    
    Controller->>JasperPrintService: getJasperPrint(jasperfile, jasperParameter)
    JasperPrintService->>DB: Query data using jasperParameter
    DB-->>JasperPrintService: Certificate data
    JasperPrintService->>JasperPrintService: Compile and fill Jasper report
    JasperPrintService-->>Controller: JasperPrint object
    
    Controller->>JasperPrintService: printPdfReport(jasperPrint, printStatus, outputStream)
    JasperPrintService->>JasperPrintService: Export JasperPrint to PDF
    JasperPrintService-->>Controller: PDF generated
    
    Controller->>Controller: outputStream.flush()
    Controller->>Controller: outputStream.close()
    Controller-->>Client: PDF certificate stream
    
    alt Exception Occurs
        Controller->>Controller: Handle IOException/Exception
        Controller-->>Client: Error response
    end
```

---

## 13. Training Program Report - Search Flow

This flow shows how **Training Program Reports** are searched with various filters.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingProgramReportController
    participant UserAuth as UserAuthentication
    participant TprRepo as TrainingProgramReportRepo
    participant DB as Database

    %% Training Program Report Search Flow
    Note over Client,DB: Training Program Report Search
    
    Client->>Controller: POST /api/training/training-program-report/trainingReportSearch<br/>(TrainingProgramReportExcelDto)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>TprRepo: downloadTrainingReportExcel(username, state, tsmName, dealerName,<br/>delearEmpDesignation, employeeStatus, typeofTraining,<br/>trainingModule, trainingStartDate, trainingEndDate)
    TprRepo->>DB: CALL sp_training_PROG_Attendance_Report<br/>(userCode, stateId, tsmNameID, dealerNameId,<br/>dealerDesigId, empStatus, trainingTypeId,<br/>trainingModuleId, fromDate, toDate)
    DB-->>TprRepo: List<TrainingProgramReportSearchResponse>
    TprRepo-->>Controller: search
    
    alt Search Result Not Empty
        Controller->>Controller: count = search.get(0).getRecordCount()
    else Search Result Empty
        Controller->>Controller: count = 0
    end
    
    Controller->>Controller: response.setMessage("Training Report Search get successfully")
    Controller->>Controller: response.setStatus(HttpStatus.OK)
    Controller->>Controller: response.setResult(search)
    Controller->>Controller: response.setCount(count)
    Controller-->>Client: ResponseEntity<ApiResponse> with search results
```

---

## 14. Training Program Report - Download Excel Flow

This flow shows how **Training Program Reports** are exported to Excel format.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingProgramReportController
    participant UserAuth as UserAuthentication
    participant TprRepo as TrainingProgramReportRepo
    participant ExcelGenerator as ExcelCellGenerator
    participant DB as Database

    %% Training Program Report Excel Download Flow
    Note over Client,DB: Download Training Program Report Excel
    
    Client->>Controller: POST /api/training/training-program-report/downloadTrainingReportExcel<br/>(TrainingProgramReportExcelDto)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>Controller: size = Integer.MAX_VALUE - 1
    
    Controller->>TprRepo: downloadTrainingReportExcel(username, state, tsmName, dealerName,<br/>delearEmpDesignation, employeeStatus, typeofTraining,<br/>trainingModule, trainingStartDate, trainingEndDate)
    TprRepo->>DB: CALL sp_training_PROG_Attendance_Report<br/>(userCode, stateId, tsmNameID, dealerNameId,<br/>dealerDesigId, empStatus, trainingTypeId,<br/>trainingModuleId, fromDate, toDate)
    DB-->>TprRepo: List<TrainingProgramReportSearchResponse>
    TprRepo-->>Controller: list
    
    Controller->>ExcelGenerator: trainingProgramExcelReport(list)
    ExcelGenerator->>ExcelGenerator: Create Excel workbook
    ExcelGenerator->>ExcelGenerator: Add headers and data rows
    ExcelGenerator->>ExcelGenerator: Format cells and styles
    ExcelGenerator-->>Controller: ByteArrayInputStream (Excel file)
    
    Controller->>Controller: response.setContentType("application/vnd.ms-excel")
    Controller->>Controller: filename = "TrainingProgramReport_" + timestamp + ".xlsx"
    Controller->>Controller: headers.add("Content-Disposition", "attachment; filename=" + filename)
    Controller->>Controller: headers.add("Access-Control-Expose-Headers", "Content-Disposition")
    
    Controller-->>Client: ResponseEntity<InputStreamResource> with Excel file stream
```

---

## 15. Training Program Calendar - Get Master Data Flows

This flow shows how various **Master Data** is retrieved for Training Program Calendar (locations, types, modules, dealers).

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as TrainingProgramCalendarController
    participant TpcRepo as TrainingProgramCalendarRepo
    participant DB as Database

    %% Get Master Data Flows
    Note over Client,DB: Get Master Data for Training Program Calendar
    
    rect rgb(240, 248, 255)
        Note over Client,DB: Get Program Location
        Client->>Controller: GET /api/training/training-program-calender/getProgramLocation?type={type}
        Controller->>TpcRepo: getProgramLocation(type)
        TpcRepo->>DB: CALL sp_Get_Training_Mst_Location(:type)
        DB-->>TpcRepo: List<Map<String, Object>> (locations)
        TpcRepo-->>Controller: location
        Controller-->>Client: ResponseEntity<ApiResponse> with locations
    end
    
    rect rgb(240, 248, 255)
        Note over Client,DB: Get Training Type
        Client->>Controller: GET /api/training/training-program-calender/getTrainingType<br/>?departmentName={dept}&type={type}
        Controller->>TpcRepo: getTrainingType(departmentName, type)
        TpcRepo->>DB: CALL sp_Get_Training_Mst_Type(:departmentName, :type)
        DB-->>TpcRepo: List<Map<String, Object>> (training types)
        TpcRepo-->>Controller: result
        Controller-->>Client: ResponseEntity<ApiResponse> with training types
    end
    
    rect rgb(240, 248, 255)
        Note over Client,DB: Get Training Module
        Client->>Controller: GET /api/training/training-program-calender/getTrainingModule<br/>?trainingTypeId={id}&type={type}
        Controller->>TpcRepo: getTrainingModule(trainingTypeId, type)
        TpcRepo->>DB: CALL sp_Get_Training_Mst_Module(:trainingTypeId, :type)
        DB-->>TpcRepo: List<Map<String, Object>> (training modules)
        TpcRepo-->>Controller: result
        Controller-->>Client: ResponseEntity<ApiResponse> with training modules
    end
    
    rect rgb(240, 248, 255)
        Note over Client,DB: Get Dealer Name
        Client->>Controller: GET /api/training/training-program-calender/getDealerName<br/>?allStateName={states}
        Controller->>TpcRepo: getDealerName(allStateName)
        TpcRepo->>DB: CALL sp_Get_Dealers_By_States(:allStateName)
        DB-->>TpcRepo: List<Map<String, Object>> (dealers)
        TpcRepo-->>Controller: result
        Controller-->>Client: ResponseEntity<ApiResponse> with dealers
    end
```

---

## Summary

The Training Module consists of four main sub-modules:

1. **Training Program Calendar** (`trainingProgrammeCalendar`): Manages training program schedules, dealer assignments, holidays, and nominee approvals.

2. **Training Nomination** (`trainingNomination`): Handles employee nominations for training programs with search and CRUD operations.

3. **Attendance Sheet** (`attendanceSheet`): Manages attendance tracking, employee performance indexes (pre-test, post-test, growth index), trainer assignments, document uploads, and certificate generation.

4. **Training Program Report** (`trainingProgramReport`): Provides search and export functionality for training program reports with various filters.

All flows follow a consistent pattern:
- Controllers handle HTTP requests/responses
- UserAuthentication service provides user context (loginId, dealerId, username)
- Repositories interact with database via stored procedures and native queries
- Services handle complex business logic and transactions
- Database operations use stored procedures for complex queries

The module uses Spring Boot with JPA repositories and follows RESTful API design principles.

