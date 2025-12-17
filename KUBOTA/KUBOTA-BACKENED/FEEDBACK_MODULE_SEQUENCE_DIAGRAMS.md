## Feedback Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.feedback` module:

- **Feedback Form Submission** (Add feedback form with dealer association).
- **Feedback List Retrieval** (Search and retrieve feedback list with pagination).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the Feedback module.

---

## 1. Feedback Form Submission Flow

This flow shows how **feedback forms** are submitted by users. The system automatically associates the feedback with the dealer based on the authenticated user's dealer ID.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as FeedbackFormController
    participant UserAuth as UserAuthentication
    participant DealerRepo as DealerMasterRepo
    participant FeedbackRepo as FeedbackFormRepository
    participant DB as Database

    %% Feedback Form Submission Flow
    Note over Client,DB: Feedback Form Submission
    
    Client->>Controller: POST /api/feedback/addFeedbackForm<br/>(FeedbackForm JSON body)
    
    Note right of Client: FeedbackForm contains:<br/>- moduleName<br/>- transactions<br/>- operation<br/>- fieldName<br/>- comment<br/>- mobileNumber
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId (Long)
    
    Controller->>DealerRepo: findById(dealerId)
    DealerRepo->>DB: SELECT * FROM dealer_master WHERE id = dealerId
    DB-->>DealerRepo: DealerMaster entity
    DealerRepo-->>Controller: DealerMaster entity
    
    Controller->>Controller: feedbackForm.setDealerMaster(dealerMaster)
    
    Controller->>Controller: Create ApiResponse object
    Controller->>Controller: apiResponse.setMessage("form submitted successfully")
    Controller->>Controller: apiResponse.setStatus(HttpStatus.OK.value())
    
    Controller->>FeedbackRepo: save(feedbackForm)
    
    Note right of FeedbackRepo: JPA automatically sets:<br/>- id (auto-generated)<br/>- createdDate (new Date())<br/>- dealer_id (foreign key)
    
    FeedbackRepo->>DB: INSERT INTO feedback_form<br/>(module_name, transactions, operation,<br/>field_name, comment, mobile_number,<br/>created_date, dealer_id)
    DB-->>FeedbackRepo: FeedbackForm saved with generated ID
    FeedbackRepo-->>Controller: Saved FeedbackForm entity
    
    Controller-->>Client: HTTP 200 OK<br/>(ApiResponse with success message)
    
    Note right of Client: Response contains:<br/>- message: "form submitted successfully"<br/>- status: 200
```

---

## 2. Feedback List Retrieval Flow

This flow shows how **feedback lists** are retrieved with pagination support. The system uses stored procedures to search and count feedback records filtered by dealer ID.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as FeedbackFormController
    participant UserAuth as UserAuthentication
    participant FeedbackRepo as FeedbackFormRepository
    participant DB as Database

    %% Feedback List Retrieval Flow
    Note over Client,DB: Feedback List Retrieval with Pagination
    
    Client->>Controller: GET /api/feedback/getFeedbackList<br/>?page={page}&size={size}
    
    Note right of Client: Query Parameters:<br/>- page: Integer (page number)<br/>- size: Integer (page size)
    
    Controller->>UserAuth: getDealerId()
    UserAuth-->>Controller: dealerId (Long)
    
    Controller->>Controller: Create ApiResponse object
    
    %% Search Feedback Flow
    Controller->>FeedbackRepo: searchFeedback(dealerId, page, size)
    
    Note right of FeedbackRepo: Calls stored procedure:<br/>sp_feedback_searchFeedback(:dealerId, :page, :size)
    
    FeedbackRepo->>DB: CALL sp_feedback_searchFeedback(dealerId, page, size)
    
    Note right of DB: Stored procedure executes:<br/>- Filters by dealer_id<br/>- Applies pagination<br/>- Returns List<Map<Object, String>>
    
    DB-->>FeedbackRepo: List<Map<Object, String>> (feedback records)
    FeedbackRepo-->>Controller: List<Map<Object, String>> (feedback list)
    
    %% Count Feedback Flow
    Controller->>FeedbackRepo: searchFeedbackCount(dealerId, page, size)
    
    Note right of FeedbackRepo: Calls stored procedure:<br/>sp_feedback_searchFeedback_count(:dealerId, :page, :size)
    
    FeedbackRepo->>DB: CALL sp_feedback_searchFeedback_count(dealerId, page, size)
    
    Note right of DB: Stored procedure executes:<br/>- Filters by dealer_id<br/>- Counts total matching records<br/>- Returns Long (total count)
    
    DB-->>FeedbackRepo: Long (total count)
    FeedbackRepo-->>Controller: Long (total count)
    
    Controller->>Controller: apiResponse.setMessage("get feedback list")
    Controller->>Controller: apiResponse.setStatus(HttpStatus.OK.value())
    Controller->>Controller: apiResponse.setResult(feedbackList)
    Controller->>Controller: apiResponse.setCount(totalCount)
    
    Controller-->>Client: HTTP 200 OK<br/>(ApiResponse with feedback list and count)
    
    Note right of Client: Response contains:<br/>- message: "get feedback list"<br/>- status: 200<br/>- result: List<Map> (feedback records)<br/>- count: Long (total count)
```

---

## Module Architecture Overview

### Components

1. **FeedbackFormController** (`/api/feedback`)
   - REST controller handling HTTP requests
   - Endpoints:
     - `POST /addFeedbackForm` - Submit feedback form
     - `GET /getFeedbackList` - Retrieve paginated feedback list

2. **FeedbackForm** (Domain Entity)
   - JPA entity representing feedback form
   - Fields:
     - `id` (Long, auto-generated)
     - `moduleName` (String)
     - `transactions` (String)
     - `operation` (String)
     - `fieldName` (String)
     - `comment` (String)
     - `mobileNumber` (String)
     - `createdDate` (Date, auto-set)
     - `dealerMaster` (DealerMaster, ManyToOne relationship)

3. **FeedbackFormRepository**
   - JPA repository extending `JpaRepository<FeedbackForm, Long>`
   - Custom queries using stored procedures:
     - `searchFeedback()` - Calls `sp_feedback_searchFeedback`
     - `searchFeedbackCount()` - Calls `sp_feedback_searchFeedback_count`

### Dependencies

- **UserAuthentication** - Provides authenticated user's dealer ID
- **DealerMasterRepo** - Repository for dealer master data
- **ApiResponse** - Standardized API response wrapper

### Database Operations

- **Direct JPA Operations**: Save feedback form using `save()` method
- **Stored Procedures**: 
  - `sp_feedback_searchFeedback` - Search feedback with pagination
  - `sp_feedback_searchFeedback_count` - Count total feedback records

### Security & Authorization

- All endpoints require authentication (via `UserAuthentication`)
- Feedback is automatically filtered by dealer ID
- Dealer association is enforced at the controller level

