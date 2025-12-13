# DDL Errors Fixed

## Issues Found and Fixed

### 1. **Reserved Keyword Issue: `LIMIT`**
- **Entity:** `ChannelFinanceIndent.java`
- **Problem:** `LIMIT` is a reserved keyword in H2 database
- **Fix:** Changed `@Column(name = "\"limit\"", length = 15)` to escape the reserved keyword

### 2. **Invalid columnDefinition in SPBackOrderCancellation**
- **File:** `SPBackOrderCancellation.java`
- **Problems:**
  - `@Column(columnDefinition = "Default draftFlag value as 'false'")` - Invalid SQL syntax
  - `@Column(columnDefinition = "It accept 'Draft' and 'Submitted' as values")` - Invalid SQL syntax (looks like a comment)
- **Fix:**
  - Changed to `@Column(columnDefinition = "BOOLEAN DEFAULT false")`
  - Removed invalid columnDefinition from status field, added proper length

### 3. **Invalid columnDefinition in SPBranchTransferIndent**
- **File:** `SPBranchTransferIndent.java`
- **Problem:** `@Column(columnDefinition = "Setted draftFlag default false")` - Invalid SQL syntax
- **Fix:** Changed to `@Column(columnDefinition = "BOOLEAN DEFAULT false")`

### 4. **Invalid columnDefinition in SPBranchTransferIndentItem**
- **File:** `SPBranchTransferIndentItem.java`
- **Problems:**
  - `@Column(columnDefinition = "Requesting Branch Available Stock Quantity")` - Invalid SQL (looks like a comment)
  - `@Column(columnDefinition = "Supplying Branch Available Stock Quantity")` - Invalid SQL (looks like a comment)
- **Fix:** Removed invalid columnDefinition annotations (Hibernate will infer the type from Java type)

### 5. **Invalid columnDefinition in SPBranchTransferIssue**
- **File:** `SPBranchTransferIssue.java`
- **Problems:**
  - `@Column(columnDefinition = "Setted draftFlag default false")` - Invalid SQL syntax
  - `@Column(columnDefinition = "It accept Draft and Submitted as values")` - Invalid SQL syntax
- **Fix:**
  - Changed to `@Column(columnDefinition = "BOOLEAN DEFAULT false")`
  - Removed invalid columnDefinition from status field, added proper length

### 6. **Invalid columnDefinition in SPBranchTransferReceipt**
- **File:** `SPBranchTransferReceipt.java`
- **Problems:**
  - `@Column(columnDefinition = "Default draftFlag value as 'false'")` - Invalid SQL syntax
  - `@Column(columnDefinition = "It accept 'Draft' and 'Submitted' as values")` - Invalid SQL syntax
- **Fix:**
  - Changed to `@Column(columnDefinition = "BOOLEAN DEFAULT false")`
  - Removed invalid columnDefinition from status field, added proper length

### 7. **Invalid columnDefinition in SPOrderPlanningSheet**
- **File:** `SPOrderPlanningSheet.java`
- **Problem:** `@Column(columnDefinition = "Set draftFlag default false")` - Invalid SQL syntax
- **Fix:** Changed to `@Column(columnDefinition = "BOOLEAN DEFAULT false")`

## Summary

All DDL errors have been fixed by:
1. Escaping reserved keywords with double quotes
2. Replacing invalid columnDefinition values with proper SQL syntax
3. Removing comment-like columnDefinition values that were incorrectly used
4. Using proper H2-compatible SQL syntax for default values

## Status

✅ **All DDL errors fixed**
✅ **Application should now start without DDL-related errors**

The application may still take 3-5 minutes to fully initialize due to the large number of entities (1600+ source files), but no DDL syntax errors should occur.

