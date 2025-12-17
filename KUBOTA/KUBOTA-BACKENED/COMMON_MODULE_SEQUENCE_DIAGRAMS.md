## Common Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.common` module:

- **Mail sending pipeline** (scheduled job + mail service + mail repository).
- **System lookup API**.
- **Jasper report generation and export** (PDF/Excel/CSV/HTML).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the common module.

---

## 1. Mail Sending Pipeline (Scheduled Job → MailJobMain → SendMailService)

This flow shows how **pending emails** in the database are periodically sent via SMTP using `MailJobImpl`, `MailJobMain`, and `SendMailService`.

```mermaid
sequenceDiagram
    %% Participants
    participant Cron as Spring Scheduler (@Scheduled)
    participant MailJob as MailJobImpl
    participant MailJobMain as MailJobMain
    participant MailRepo as MailRepo
    participant MailService as SendMailService
    participant SMTP as SMTP Server
    participant DB as Database

    %% Scheduler triggers job based on cron expression
    Cron->>MailJob: executeInternal()  (cron = ${MAIL_CORN_EXP})

    MailJob->>MailJobMain: sendMail(MailRepo)

    Note over MailJobMain,MailRepo: Load all pending mails
    MailJobMain->>MailRepo: findByMailstatus("Pending")
    MailRepo->>DB: SELECT * FROM mail_entity<br/>WHERE mailstatus = 'Pending'
    DB-->>MailRepo: List of MailEntity (status = 'Pending')
    MailRepo-->>MailJobMain: List of MailEntity (list)

    loop For each MailEntity in list
        MailJobMain->>MailJobMain: mailEntity = next()

        %% Use SendMailService to send one mail
        MailJobMain->>MailService: sendMail(mailEntity, null)

        Note over MailService,SMTP: Configure JavaMail Session
        MailService->>MailService: Load host, port,<br/>username, password, from<br/>(from application.properties)
        MailService->>MailService: Create Properties<br/>mail.smtp.host, mail.smtp.port,<br/>mail.smtp.auth, mail.smtp.starttls.enable=false

        MailService->>MailService: Session.getInstance(props, Authenticator)
        MailService->>MailService: Build MimeMessage(session)
        MailService->>MailService: message.setFrom(from)

        alt TO recipients present
            MailService->>MailService: message.setRecipients(TO, mailEntity.tomailid)
        end
        alt CC recipients present
            MailService->>MailService: message.setRecipients(CC, mailEntity.ccmailid)
        end
        alt BCC recipients present
            MailService->>MailService: message.setRecipients(BCC, mailEntity.bccmailid)
        end

        MailService->>MailService: message.setSubject(mailEntity.mailsubject)

        %% Build multipart body
        MailService->>MailService: Create MimeBodyPart for HTML body
        MailService->>MailService: Set HTML body content as HTML (UTF-8)
        MailService->>MailService: Create MimeMultipart and add body part

        alt attachment != null
            MailService->>MailService: Create attachment MimeBodyPart
            MailService->>MailService: new FileDataSource(attachment)
            MailService->>MailService: Set DataHandler and fileName
            MailService->>MailService: multipart.addBodyPart(attachmentPart)
        end

        MailService->>MailService: message.setContent(multipart)

        alt At least one recipient present (TO or CC or BCC)
            MailService->>SMTP: Transport.send(message)
            SMTP-->>MailService: Success or exception
        end

        alt sendMail returns true
            MailJobMain->>MailEntity: Update status to DONE
            MailJobMain->>MailEntity: setMailsentdate(new Date())
            MailJobMain->>MailRepo: save(mailEntity)
            MailRepo->>DB: UPDATE mail_entity<br/>SET mailstatus='DONE', mailsentdate=NOW()<br/>WHERE id = mailEntity.id
            DB-->>MailRepo: Updated
            MailRepo-->>MailJobMain: MailEntity updated
        else sendMail returns false
            MailJobMain->>MailEntity: Update status to FAILED
            MailJobMain->>MailRepo: save(mailEntity)
            MailRepo->>DB: UPDATE mail_entity<br/>SET mailstatus='FAILED'<br/>WHERE id = mailEntity.id
            DB-->>MailRepo: Updated
            MailRepo-->>MailJobMain: MailEntity updated
        end
    end

    MailJobMain-->>MailJob: Log end-of-utility message
    MailJob-->>Cron: Job execution completed
```

---

## 2. System Lookup API (SysLookupController.lookupByCode)

This flow shows how system lookup values are fetched for UI dropdowns or configuration using `SysLookupController` and underlying repository logic.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant UI as Angular Frontend
    participant SysController as SysLookupController
    participant SysRepo as SysLookupRepo
    participant DB as Database

    Client->>UI: Request lookup values<br/>(e.g. status codes, types)

    UI->>SysController: GET /api/common/syslookup/lookupByCode?code=XYZ

    SysController->>SysController: Create ApiResponse<Object> apiResponse

    %% Repository call for lookups
    SysController->>SysRepo: lookupByTypeCode(code)
    SysRepo->>DB: SELECT * FROM system_lookup<br/>WHERE lookuptypecode = :code
    DB-->>SysRepo: List of SystemLookUpEntity
    SysRepo-->>SysController: List of SystemLookUpEntity (lookupDetails)

    SysController->>apiResponse: setResult(lookupDetails)
    SysController->>apiResponse: Set message text to Lookup Details
    SysController->>apiResponse: setStatus(HttpStatus.OK.value())

    SysController-->>UI: ResponseEntity(ApiResponse)<br/>{ status:200, message: Lookup Details, result:[...]}

    UI-->>Client: Render dropdown / list<br/>using lookupDetails
```

---

## 3. Template Download API (SysLookupController.downloadTemplate)

This flow describes downloading static Excel templates from `WEB-INF/template` using `downloadTemplate`.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant UI as Angular Frontend
    participant SysController as SysLookupController
    participant ServletCtx as ServletContext
    participant FileSys as File System

    Client->>UI: Download template request<br/>(for example, StockUpload.xlsx)

    UI->>SysController: GET /api/common/syslookup/downloadTemplate?filename=StockUpload.xlsx

    SysController->>ServletCtx: getRealPath for /WEB-INF/template/ and filename
    ServletCtx-->>SysController: filePath (absolute path)

    SysController->>FileSys: new File(filePath)
    FileSys-->>SysController: File fileOnServer

    SysController->>FileSys: new FileInputStream(fileOnServer)
    FileSys-->>SysController: FileInputStream inputStream

    SysController->>SysController: Set response content type to Excel

    SysController->>SysController: Create HttpHeaders headers
    SysController->>headers: Add Content-Disposition header with attachment filename
    SysController->>headers: add(ACCESS_CONTROL_EXPOSE_HEADERS,<br/>CONTENT_DISPOSITION)

    SysController-->>UI: ResponseEntity(InputStreamResource)<br/>with headers and Excel stream

    UI-->>Client: Browser download dialog<br/>Save/Open template file
```

---

## 4. Jasper Report Generation & Export (JasperPrintService)

This flow illustrates how a Jasper report is generated and exported to **PDF**, **Excel (XLS/XLSX)**, **CSV**, or **HTML** using `JasperPrintService`.

```mermaid
sequenceDiagram
    %% Participants
    participant Controller as Any Report Controller
    participant JasperService as JasperPrintService
    participant DB as Database
    participant JasperEngine as JasperReports Engine
    participant Client as Client / Browser

    %% Step 1: Generate JasperPrint
    Controller->>Controller: Build jasperFile path<br/>(e.g. /WEB-INF/reports/ExportPartDumpList.jasper)
    Controller->>Controller: Build jasperParameter (HashMap)<br/>(e.g. date range, dealerId, etc.)

    Controller->>JasperService: getJasperPrint(jasperFile, jasperParameter)

    JasperService->>JasperService: connection = getConnection()<br/>(from ConnectionConfigurationImpl)
    JasperService->>DB: Open JDBC connection
    DB-->>JasperService: Connection

    JasperService->>JasperEngine: JasperFillManager.fillReport(<br/>jasperFile, jasperParameter, connection)
    JasperEngine->>DB: Execute SQL queries / stored procedures
    DB-->>JasperEngine: Result sets
    JasperEngine-->>JasperService: JasperPrint object

    JasperService->>JasperService: Close connection in finally block
    JasperService-->>Controller: JasperPrint

    %% Step 2: Export to desired format (PDF / XLS / XLSX / CSV / HTML)
    alt Export as PDF
        Controller->>JasperService: printPdfReport(jasperPrint, printStatus, outputStream)
        JasperService->>JasperService: new JRPdfExporter()
        JasperService->>JasperService: exporter.setExporterInput(SimpleExporterInput(jasperPrint))
        JasperService->>JasperService: exporter.setExporterOutput(SimpleOutputStreamExporterOutput(outputStream))

        JasperService->>JasperService: SimplePdfExporterConfiguration configuration
        alt printStatus is enabled
            JasperService->>configuration: Set permissions (ALLOW_COPY, ALLOW_PRINTING)
            JasperService->>configuration: Enable auto-print JavaScript in PDF
        end

        JasperService->>JasperService: exporter.setConfiguration(configuration)
        JasperService->>JasperEngine: exporter.exportReport()
        JasperEngine-->>Client: PDF bytes via HTTP response

    else Export as XLS
        Controller->>JasperService: printExcelReport(jasperPrint, fileName, sheetName[], outputStream)
        JasperService->>JasperService: Configure JasperPrint to ignore graphics in XLS export
        JasperService->>JasperService: new JRXlsExporter()
        JasperService->>JasperService: setExporterInput / setExporterOutput(outputStream)
        JasperService->>JasperService: SimpleXlsReportConfiguration configuration<br/>setSheetNames(sheetName)
        JasperService->>JasperEngine: xlsExporter.exportReport()
        JasperEngine-->>Client: XLS bytes via HTTP response

    else Export as XLSX
        Controller->>JasperService: exportToXlsx(jasperPrint, sheetName[], outputStream)
        JasperService->>JasperService: new JRXlsxExporter()
        JasperService->>JasperService: setExporterInput / setExporterOutput(outputStream)
        JasperService->>JasperService: SimpleXlsxReportConfiguration reportConfig<br/>setSheetNames(sheetName)
        JasperService->>JasperEngine: exporter.exportReport()
        JasperEngine-->>Client: XLSX bytes via HTTP response

    else Export as CSV
        Controller->>JasperService: exportToCsv(jasperPrint, fileName, outputStream)
        JasperService->>JasperService: new JRCsvExporter()
        JasperService->>JasperService: setExporterInput(jasperPrint)<br/>setExporterOutput(SimpleWriterExporterOutput(outputStream))
        JasperService->>JasperEngine: exporter.exportReport()
        JasperEngine-->>Client: CSV text via HTTP response

    else Export as HTML
        Controller->>JasperService: exportToHtml(jasperPrint, fileName, outputStream)
        JasperService->>JasperService: new HtmlExporter()
        JasperService->>JasperService: setExporterInput(jasperPrint)<br/>setExporterOutput(SimpleHtmlExporterOutput(outputStream))
        JasperService->>JasperEngine: exporter.exportReport()
        JasperEngine-->>Client: HTML content via HTTP response
    end
```

---

## Summary

The **common** module centralizes **cross-cutting infrastructure** for the KUBOTA DMS:

- **Email Delivery**:
  - `MailJobImpl` (scheduled by Spring) periodically triggers `MailJobMain`.
  - `MailJobMain` loads `Pending` mails from `MailRepo` and delegates sending to `SendMailService`.
  - `SendMailService` uses JavaMail to send HTML emails with optional attachments, updating each `MailEntity` to `DONE` or `FAILED`.

- **System Lookups & Templates**:
  - `SysLookupController` exposes lookup data via `/lookupByCode`.
  - `/downloadTemplate` serves static Excel templates from `/WEB-INF/template` for imports.

- **Reporting**:
  - `JasperPrintService` encapsulates all JasperReports integration:
    - Fills reports using DB connections from `ConnectionConfigurationImpl`.
    - Exports them into **PDF**, **XLS/XLSX**, **CSV**, or **HTML** for controllers to stream to clients.

These shared services simplify other modules (sales, service, warranty, etc.) by providing **reusable mail, lookup, and reporting capabilities** with well-defined APIs.


