# Service Module Sequence Diagrams - Quick Reference Guide

## Overview

This guide explains the sequence diagrams created for the ITLDIS Service Module. The diagrams illustrate the flow of interactions between different components when users interact with the 13 service module tiles.

## File Location

**Main File:** `SERVICE_MODULE_SEQUENCE_DIAGRAMS.md`

This file contains 8 comprehensive sequence diagrams covering major workflows in the service module.

## Diagrams Included

### 1. View All Job Cards Flow
**Purpose:** Shows how users view a list of job cards with filtering options.

**Key Components:**
- User clicks tile → Browser → Struts Framework → serviceAction
- serviceAction calls MethodUtility to get dealer list
- serviceDAO queries database using Hibernate
- Results mapped to serviceForm objects and displayed

**Use Case:** When user clicks "View All Job Card" tile (Tile #6)

---

### 2. Generate Invoice Flow
**Purpose:** Illustrates the complex invoice generation process.

**Key Components:**
- Validates customer ID using stored procedure `SP_getVehicleCustomerID`
- Checks if invoice already exists
- Generates invoice number
- Inserts invoice details (parts, labor, other charges)
- Updates job card status
- Calls tax invoice stored procedures

**Use Case:** When user generates an invoice from a job card (Tile #10)

**Stored Procedures Used:**
- `SP_getVehicleCustomerID`
- `SP_updateTaxInvoice`
- `SP_BajajExtWtyTaxInvoiceUpdate`

---

### 3. VIN Number Details Lookup (AJAX)
**Purpose:** Shows asynchronous VIN number search functionality.

**Key Components:**
- AJAX call triggered on user input
- Real-time search in Vehicledetails table
- Results returned as JSON/CSV
- Dropdown populated dynamically

**Use Case:** When user types VIN number in any form field

---

### 4. Get VIN Details Flow
**Purpose:** Retrieves complete vehicle information for a selected VIN.

**Key Components:**
- Queries Vehicledetails table using Hibernate
- Returns formatted string with all vehicle details
- Populates form fields automatically

**Use Case:** When user selects a VIN from dropdown

---

### 5. Service Reminder Flow
**Purpose:** Displays service reminders based on due dates.

**Key Components:**
- Queries SWVehicleServiceSchedule table
- Joins with Vehicledetails
- Filters by date range and status
- Supports export functionality

**Use Case:** When user clicks "Service Reminder" tile (Tile #12)

---

### 6. Close Job Card Flow
**Purpose:** Shows the process of closing a job card.

**Key Components:**
- Lists open job cards
- Updates job card status
- Calls `SP_UpdateJobCardDailyConsumption` stored procedure
- Refreshes job card list

**Use Case:** When user clicks "Close Job Card" tile (Tile #7)

**Stored Procedures Used:**
- `SP_UpdateJobCardDailyConsumption`

---

### 7. Part Number Lookup (AJAX)
**Purpose:** Real-time part number search functionality.

**Key Components:**
- AJAX call on part number input
- Searches Partmaster/CatPart tables
- Returns matching parts as JSON/CSV

**Use Case:** When user types part number in invoice/job card forms

---

### 8. Overall Service Module Architecture
**Purpose:** High-level overview of component interactions.

**Key Components:**
- Shows complete flow from user to database
- Illustrates all layers (UI, Controller, Service, DAO, Database)
- Demonstrates common patterns used throughout

**Use Case:** Understanding the overall architecture

---

## Component Roles

### User Interface Layer
- **Browser/Client**: Renders JSP pages and handles user interactions
- **JSP Pages**: View templates (v_viewalljobcard.jsp, sercviceReminder.jsp, etc.)

### Controller Layer
- **Struts Framework**: Routes HTTP requests to appropriate action methods
- **serviceAction**: Coordinates business logic and delegates to DAO layer

### Service Layer
- **serviceDAO**: Handles all database operations
- **MethodUtility**: Provides common utility methods (dealer lookup, number generation, etc.)

### Data Access Layer
- **Hibernate ORM**: Object-relational mapping framework
- **Direct SQL Queries**: Some operations use native SQL
- **Stored Procedures**: Complex business logic executed in database

### Database Layer
- **SQL Server Database**: Stores all application data
- **Tables**: Jobcarddetails, Vehicledetails, SpInventSaleMaster, etc.
- **Stored Procedures**: SP_getVehicleCustomerID, SP_updateTaxInvoice, etc.

## Common Patterns

### 1. Session Management
- User context stored in HTTP session:
  - `user_id`: Current logged-in user
  - `dealerCode`: User's dealer code
  - `userFunctionalities`: Vector of user permissions

### 2. Transaction Management
- Hibernate transactions ensure data consistency
- `session.beginTransaction()` → operations → `session.commit()`
- `session.rollback()` on errors

### 3. Error Handling
- Try-catch blocks around database operations
- Transaction rollback on exceptions
- User-friendly error messages

### 4. AJAX Calls
- Asynchronous requests for dynamic data
- Real-time updates without page refresh
- JSON/CSV formatted responses

### 5. Form Validation
- Request parameter validation before processing
- Business rule validation (e.g., invoice existence check)

### 6. Authorization
- User functionality checks before operations
- Dealer code filtering based on user permissions

## How to Read the Diagrams

1. **Participants**: Vertical lines representing different components
2. **Messages**: Horizontal arrows showing method calls/data flow
   - `->>`: Synchronous call
   - `-->>`: Return/response
3. **Alt Blocks**: Conditional flows (if/else scenarios)
4. **Loops**: Repeated operations
5. **Notes**: Additional context using `<br/>` for line breaks

## Viewing the Diagrams

### Option 1: GitHub/GitLab
- The Mermaid diagrams will render automatically in markdown viewers that support Mermaid

### Option 2: VS Code
- Install "Markdown Preview Mermaid Support" extension
- Open the `.md` file and preview

### Option 3: Online Mermaid Editor
- Copy diagram code from ````mermaid` blocks
- Paste at https://mermaid.live/
- View and export as PNG/SVG

### Option 4: Mermaid CLI
```bash
npm install -g @mermaid-js/mermaid-cli
mmdc -i SERVICE_MODULE_SEQUENCE_DIAGRAMS.md -o diagrams.pdf
```

## Related Files

- **Service_Module_Analysis.xlsx**: Comprehensive Excel analysis with tiles, APIs, classes, methods, and database tables
- **analyze_service_module.py**: Python script used to generate the Excel analysis

## Key Database Tables Referenced

1. **Jobcarddetails**: Main job card information
2. **Vehicledetails**: Vehicle master data
3. **SpInventSaleMaster**: Invoice header information
4. **SpInventSaleDetails**: Invoice line items
5. **SWVehicleServiceSchedule**: Service schedule/reminders
6. **UmUserDealerMatrix**: User-dealer mapping for authorization
7. **Dealervslocationcode**: Dealer master data
8. **CatPart**: Part master data
9. **SpPriceMaster**: Part pricing information

## Key Stored Procedures Referenced

1. **SP_getVehicleCustomerID**: Gets customer ID for a vehicle
2. **SP_updateTaxInvoice**: Updates tax invoice details
3. **SP_BajajExtWtyTaxInvoiceUpdate**: Updates extended warranty tax invoice
4. **SP_UpdateJobCardDailyConsumption**: Updates daily consumption records
5. **SP_showTaxInvoice**: Retrieves tax invoice details
6. **SP_showTaxValuesInInvoice**: Gets tax values for invoice
7. **SP_JobCardComplaintMandatoryCheck**: Validates mandatory complaints
8. **SP_CheckEstimateMandatory**: Checks mandatory estimate fields

## Best Practices Demonstrated

1. **Separation of Concerns**: Clear separation between Action, DAO, and Database layers
2. **Transaction Management**: Proper use of transactions for data consistency
3. **Error Handling**: Comprehensive error handling with rollback
4. **Session Management**: Secure session-based user context
5. **Authorization**: Functionality-based access control
6. **Code Reusability**: Utility classes for common operations

## Troubleshooting

### If diagrams don't render:
1. Check Mermaid syntax is correct
2. Ensure markdown viewer supports Mermaid
3. Use online Mermaid editor to validate syntax

### If you need to modify diagrams:
1. Edit the `.md` file directly
2. Maintain proper Mermaid syntax
3. Test in Mermaid Live Editor before committing

## Next Steps

1. Review each diagram to understand the flow
2. Cross-reference with actual code in `serviceAction.java` and `serviceDAO.java`
3. Use diagrams for:
   - Code reviews
   - Onboarding new developers
   - System documentation
   - Troubleshooting issues
   - Planning enhancements

