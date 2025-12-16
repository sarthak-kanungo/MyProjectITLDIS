# Login Module Sequence Diagrams

This document contains detailed sequence diagrams for all flows within the Login Module of the ITLDIS system.

## Table of Contents

1. [Main Login Flow](#1-main-login-flow)
2. [Login Validation Flow](#2-login-validation-flow)
3. [Session Initialization Flow](#3-session-initialization-flow)
4. [Logout Flow](#4-logout-flow)
5. [Home Page Flow](#5-home-page-flow)
6. [Contact US Flow](#6-contact-us-flow)
7. [Change Password Flow](#7-change-password-flow)

---

## 1. Main Login Flow

This diagram shows the complete login process including authentication, session setup, language configuration, user models, and functionality loading.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant LoginAction as LoginAction
    participant EncryptionDecryption as EncryptionDecryption
    participant LoginDAO as LoginDAO
    participant Database as "SQL Server DB"
    participant HibernateUtil as HibernateUtil
    participant GenLanguages as GenLanguages
    participant PageTemplate as PageTemplate
    participant ConnectionHolder as ConnectionHolder
    participant inventoryDAO as inventoryDAO
    participant PriceDetails as PriceDetails

    User->>Browser: Enter User ID, Password, Language, Captcha
    User->>Browser: Click Sign In button
    Browser->>Browser: Validate Captcha
    Browser->>StrutsFramework: POST /login.do?option=login
    StrutsFramework->>LoginAction: login(loginForm, request, response)
    
    LoginAction->>LoginAction: Validate userid and password (not empty)
    
    alt Validation fails
        LoginAction->>LoginAction: Add error message
        LoginAction->>Browser: Return fail forward
        Browser-->>User: Display error message
    else Validation succeeds
        LoginAction->>EncryptionDecryption: encrypt(password)
        EncryptionDecryption-->>LoginAction: Encrypted password
        
        LoginAction->>LoginDAO: checkUser(userId, encryptedPassword)
        
        LoginDAO->>Database: SELECT status, user_type_id, User_Type, Last_Changed, Reg_Date, USER_NAME, password, DealerCode, DealerName FROM UM_user_check, UM_spas101 WHERE User_Id=? AND password=?
        Database-->>LoginDAO: ResultSet user data
        
        alt Connection Problem
            LoginDAO-->>LoginAction: "Connection Problem"
            LoginAction->>Browser: Display connection error
        else User Not Found
            LoginDAO-->>LoginAction: "NotExist"
            LoginAction->>Browser: Display invalid user error
        else User Inactive
            LoginDAO-->>LoginAction: "NotActive"
            LoginAction->>Browser: Display inactive user error
        else User Active
            LoginDAO-->>LoginAction: "Active@@user_type_id@@User_Type@@lastChange@@registerDate@@USER_NAME@@DealerCode@@DealerName"
            
            LoginAction->>LoginDAO: getLanguageDetails(loginForm)
            LoginDAO->>HibernateUtil: getSessionFactory().openSession()
            HibernateUtil-->>LoginDAO: Session hrbsession
            LoginDAO->>GenLanguages: HQL Query WHERE langId=?
            GenLanguages-->>LoginDAO: GenLanguages object
            LoginDAO->>LoginDAO: Set languageName, languageCode, languageCountry
            LoginDAO-->>LoginAction: LoginForm with language details
            
            LoginAction->>LoginAction: Create Locale from languageCode and languageCountry
            LoginAction->>Browser: Set session attribute Globals.LOCALE_KEY
            LoginAction->>Browser: Load ApplicationResource properties file
            
            LoginAction->>LoginAction: Split result string by @@
            LoginAction->>Browser: Set session attributes (user_id, password, user_type, user_type_id, username, dealerCode, dealerName, language)
            
            LoginAction->>PageTemplate: jdbcDriverMAIN()
            PageTemplate-->>LoginAction: JDBC driver name
            LoginAction->>PageTemplate: dsnPATH(), dbUserName(), dbPasswd()
            PageTemplate-->>LoginAction: Connection details
            LoginAction->>ConnectionHolder: Create ConnectionHolder(connection)
            ConnectionHolder-->>LoginAction: Connection holder
            LoginAction->>Browser: Set session servletapp.connection
            
            LoginAction->>Browser: Set session attributes (session_id, screenWidth, screenHeight, server_name, mainURL, ecatPATH, userCode, effectiveDate, date_OR_serial, input_Date, modelNo, input_serialNo, buckleDate)
            
            LoginAction->>LoginDAO: getUserModels(userId)
            LoginDAO->>Database: SELECT DISTINCT ENGINE_SERIES FROM CAT_USER_PRODUCT_FAMILY WHERE USER_ID=?
            Database-->>LoginDAO: ResultSet engine series
            LoginDAO->>LoginDAO: Build productFamilySubQuery
            LoginDAO->>Database: SELECT DISTINCT APPLICATION_TYPE FROM CAT_USER_PRODUCT_TYPE WHERE USER_ID=?
            Database-->>LoginDAO: ResultSet application types
            LoginDAO->>LoginDAO: Build applicationTypSubQuery
            LoginDAO-->>LoginAction: ArrayList (productFamilySubQuery, applicationTypSubQuery)
            LoginAction->>Browser: Set session attributes (productFamilySubQuery, applicationTypSubQuery)
            
            LoginAction->>LoginDAO: getUserFunctionalties(user_type_id)
            LoginDAO->>Database: SELECT Func_Id FROM UM_spas103 WHERE User_Type_Id=?
            Database-->>LoginDAO: ResultSet functionality IDs
            LoginDAO-->>LoginAction: Vector userFun
            
            LoginAction->>inventoryDAO: getDealerCode()
            inventoryDAO-->>LoginAction: Dealer code list
            LoginAction->>Browser: Set session attribute lockedDealerlist
            LoginAction->>Browser: Set session attribute userFun
            
            alt User has functionality 99
                LoginAction->>LoginDAO: getBinaryUrl(userId)
                LoginDAO-->>LoginAction: Binary URL string
            end
            
            LoginAction->>Browser: Set session attribute userSessionList
            
            LoginAction->>PriceDetails: new PriceDetails(conn)
            LoginAction->>LoginAction: Set priceListCode = "MRP_INR"
            LoginAction->>LoginAction: Set sellingPercentage = 0.0
            LoginAction->>Browser: Set session attributes (priceListCode, sellingPercentage)
            
            alt User has functionalities other than ecat
                LoginAction->>Browser: Forward to success
                Browser-->>User: Display home page
            else User has only ecat functionalities (1 or 2)
                LoginAction->>Browser: Forward to successEcat
                Browser-->>User: Redirect to ecat
            end
        end
    end
```

---

## 2. Login Validation Flow

This diagram shows the detailed validation process including field validation, password encryption, and error handling.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant LoginAction as LoginAction
    participant EncryptionDecryption as EncryptionDecryption
    participant LoginDAO as LoginDAO
    participant Database as "SQL Server DB"
    participant ActionMessages as ActionMessages

    User->>Browser: Submit login form
    Browser->>LoginAction: POST /login.do?option=login
    
    LoginAction->>LoginAction: Extract userid, password, language from form
    
    alt userid is null or empty
        LoginAction->>ActionMessages: Add error "error.userid.required"
        LoginAction->>Browser: Save errors to request
        LoginAction->>Browser: Forward to fail
        Browser-->>User: Display "User ID required" error
    else password is null or empty
        LoginAction->>ActionMessages: Add error "error.password.required"
        LoginAction->>Browser: Save errors to request
        LoginAction->>Browser: Forward to fail
        Browser-->>User: Display "Password required" error
    else Fields are valid
        LoginAction->>EncryptionDecryption: encrypt(password)
        EncryptionDecryption-->>LoginAction: Encrypted password
        
        LoginAction->>LoginDAO: checkUser(userId, encryptedPassword)
        LoginDAO->>Database: Query UM_user_check, UM_spas101
        Database-->>LoginDAO: User record or empty
        
        alt No user found
            LoginDAO-->>LoginAction: "NotExist"
            LoginAction->>ActionMessages: Add error "error.login.failed"
            LoginAction->>Browser: Forward to fail
            Browser-->>User: Display "Invalid User" error
        else User found but password mismatch
            LoginDAO-->>LoginAction: "NotExist"
            LoginAction->>ActionMessages: Add error "error.login.failed"
            LoginAction->>Browser: Forward to fail
            Browser-->>User: Display "Invalid User" error
        else User found but status != 'A'
            LoginDAO-->>LoginAction: "NotActive"
            LoginAction->>ActionMessages: Add error "error.login.inactive"
            LoginAction->>Browser: Forward to fail
            Browser-->>User: Display "Inactive User" error
        else Database connection failed
            LoginDAO-->>LoginAction: "Connection Problem"
            LoginAction->>ActionMessages: Add error "error.connection"
            LoginAction->>Browser: Forward to fail
            Browser-->>User: Display "Connection Problem" error
        else User active and password correct
            LoginDAO-->>LoginAction: "Active@@user_type_id@@User_Type@@..."
            LoginAction->>LoginAction: Continue with session initialization
        end
    end
```

---

## 3. Session Initialization Flow

This diagram shows the detailed session initialization process after successful authentication.

```mermaid
sequenceDiagram
    participant LoginAction as LoginAction
    participant LoginDAO as LoginDAO
    participant HibernateUtil as HibernateUtil
    participant GenLanguages as GenLanguages
    participant Database as "SQL Server DB"
    participant PageTemplate as PageTemplate
    participant ConnectionHolder as ConnectionHolder
    participant inventoryDAO as inventoryDAO
    participant PriceDetails as PriceDetails
    participant Browser as "HTTP Session"

    LoginAction->>LoginAction: Split authentication result by @@
    LoginAction->>Browser: Set user_id, password, session_active=true
    
    LoginAction->>Browser: Set user_type, user_type_id, username
    LoginAction->>Browser: Set dealerCode, dealerName, language
    
    LoginAction->>LoginDAO: getLanguageDetails(loginForm)
    LoginDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>LoginDAO: Session hrbsession
    LoginDAO->>GenLanguages: HQL Query WHERE langId=?
    GenLanguages-->>LoginDAO: GenLanguages object
    LoginDAO->>LoginDAO: Extract languageName, languageCode, languageCountry
    LoginDAO-->>LoginAction: LoginForm with language details
    
    LoginAction->>LoginAction: Create Locale(languageCode, languageCountry)
    LoginAction->>Browser: Set Globals.LOCALE_KEY = locale
    LoginAction->>Browser: Load ApplicationResource properties file
    LoginAction->>Browser: Set prop attribute
    
    LoginAction->>PageTemplate: Get JDBC connection details
    PageTemplate-->>LoginAction: Driver, DSN, username, password
    LoginAction->>ConnectionHolder: new ConnectionHolder(connection)
    ConnectionHolder-->>LoginAction: Connection holder
    LoginAction->>Browser: Set servletapp.connection
    
    LoginAction->>Browser: Set session_id, screenWidth, screenHeight
    LoginAction->>Browser: Set server_name, mainURL, ecatPATH, userCode
    LoginAction->>Browser: Set effectiveDate, date_OR_serial, input_Date
    LoginAction->>Browser: Set modelNo, input_serialNo, buckleDate
    
    LoginAction->>LoginDAO: getUserModels(userId)
    LoginDAO->>Database: SELECT DISTINCT ENGINE_SERIES FROM CAT_USER_PRODUCT_FAMILY WHERE USER_ID=?
    Database-->>LoginDAO: Engine series list
    LoginDAO->>LoginDAO: Build productFamilySubQuery WHERE ENGINE_SERIES IN(...)
    LoginDAO->>Database: SELECT DISTINCT APPLICATION_TYPE FROM CAT_USER_PRODUCT_TYPE WHERE USER_ID=?
    Database-->>LoginDAO: Application types list
    LoginDAO->>LoginDAO: Build applicationTypSubQuery AND APPLICATION_TYPE IN(...)
    LoginDAO-->>LoginAction: ArrayList (productFamilySubQuery, applicationTypSubQuery)
    LoginAction->>Browser: Set productFamilySubQuery, applicationTypSubQuery
    
    LoginAction->>LoginDAO: getUserFunctionalties(user_type_id)
    LoginDAO->>Database: SELECT Func_Id FROM UM_spas103 WHERE User_Type_Id=?
    Database-->>LoginDAO: Functionality IDs
    LoginDAO-->>LoginAction: Vector userFun
    
    LoginAction->>inventoryDAO: getDealerCode()
    inventoryDAO-->>LoginAction: Dealer code list
    LoginAction->>Browser: Set lockedDealerlist
    LoginAction->>Browser: Set userFun
    
    alt User has functionality 99
        LoginAction->>LoginDAO: getBinaryUrl(userId)
        LoginDAO-->>LoginAction: Binary URL string
    end
    
    LoginAction->>Browser: Set userSessionList (userId, session)
    
    LoginAction->>PriceDetails: new PriceDetails(conn)
    LoginAction->>LoginAction: Set priceListCode = "MRP_INR"
    LoginAction->>LoginAction: Set sellingPercentage = 0.0
    LoginAction->>Browser: Set priceListCode, sellingPercentage
    
    LoginAction->>LoginAction: Determine forward based on userFun
    alt userFun has non-ecat functionalities
        LoginAction->>Browser: Forward to success
    else userFun contains only 1 or 2
        LoginAction->>Browser: Forward to successEcat
    else
        LoginAction->>Browser: Forward to success
    end
```

---

## 4. Logout Flow

This diagram shows the logout process which invalidates the user session.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant LoginAction as LoginAction
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Logout link/button
    Browser->>StrutsFramework: GET /login.do?option=logout
    StrutsFramework->>LoginAction: logout(loginForm, request, response)
    
    LoginAction->>HTTPSession: request.getSession()
    HTTPSession-->>LoginAction: HttpSession session
    
    LoginAction->>HTTPSession: session.invalidate()
    HTTPSession->>HTTPSession: Remove all session attributes
    HTTPSession->>HTTPSession: Invalidate session
    
    LoginAction->>StrutsFramework: Forward to logout
    StrutsFramework->>Browser: Render Logout.jsp
    Browser-->>User: Display logout confirmation page
    Browser->>Browser: Redirect to login page
```

---

## 5. Home Page Flow

This diagram shows the flow when accessing the home page after login.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant LoginAction as LoginAction

    User->>Browser: Access home page or click home link
    Browser->>StrutsFramework: GET /login.do?option=homePage
    StrutsFramework->>LoginAction: homePage(loginForm, request, response)
    
    LoginAction->>LoginAction: Set forward = "success"
    LoginAction->>StrutsFramework: Forward to success
    StrutsFramework->>Browser: Render home page JSP
    Browser-->>User: Display home page dashboard
```

---

## 6. Contact US Flow

This diagram shows the flow when accessing the Contact US page.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant LoginAction as LoginAction

    User->>Browser: Click Contact US link
    Browser->>StrutsFramework: GET /login.do?option=contactUS
    StrutsFramework->>LoginAction: contactUS(loginForm, request, response)
    
    LoginAction->>LoginAction: Set forward = "contactUS"
    LoginAction->>StrutsFramework: Forward to contactUS
    StrutsFramework->>Browser: Render locateUsPath JSP
    Browser-->>User: Display Contact US page
```

---

## 7. Change Password Flow

This diagram shows the password change flow from the User Management module.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant UserManagementAction as UserManagementAction
    participant UserManagementDAO as UserManagementDAO
    participant EncryptionDecryption as EncryptionDecryption
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Navigate to Change Password page
    Browser->>StrutsFramework: GET /userManagementAction.do?option=initChangePassword
    StrutsFramework->>UserManagementAction: initChangePassword(loginForm, request, response)
    
    UserManagementAction->>HTTPSession: request.getSession()
    HTTPSession-->>UserManagementAction: HttpSession session
    
    UserManagementAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>UserManagementAction: String user_id
    
    UserManagementAction->>StrutsFramework: Forward to changePassword
    StrutsFramework->>Browser: Render changePassword.jsp
    Browser-->>User: Display Change Password form
    
    User->>Browser: Enter current password, new password, confirm password
    User->>Browser: Click Submit button
    Browser->>Browser: Validate new password matches confirm password
    
    alt Passwords do not match
        Browser-->>User: Display "Passwords do not match" error
    else Passwords match
        Browser->>StrutsFramework: POST /userManagementAction.do?option=changePassword
        StrutsFramework->>UserManagementAction: changePassword(loginForm, request, response)
        
        UserManagementAction->>HTTPSession: session.getAttribute("user_id")
        HTTPSession-->>UserManagementAction: String user_id
        
        UserManagementAction->>UserManagementAction: Extract currentPassword, newPassword from form
        
        UserManagementAction->>EncryptionDecryption: encrypt(currentPassword)
        EncryptionDecryption-->>UserManagementAction: Encrypted current password
        
        UserManagementAction->>UserManagementDAO: verifyCurrentPassword(user_id, encryptedCurrentPassword)
        UserManagementDAO->>Database: SELECT password FROM UM_user_check WHERE User_Id=?
        Database-->>UserManagementDAO: Current password hash
        
        alt Current password incorrect
            UserManagementDAO-->>UserManagementAction: false
            UserManagementAction->>Browser: Display "Current password incorrect" error
            Browser-->>User: Show error message
        else Current password correct
            UserManagementDAO-->>UserManagementAction: true
            
            UserManagementAction->>EncryptionDecryption: encrypt(newPassword)
            EncryptionDecryption-->>UserManagementAction: Encrypted new password
            
            UserManagementAction->>UserManagementDAO: changePassword(user_id, encryptedNewPassword)
            UserManagementDAO->>Database: UPDATE UM_user_check SET password=?, UpdatedDate=GETDATE() WHERE User_Id=?
            Database-->>UserManagementDAO: Update result
            UserManagementDAO-->>UserManagementAction: Success message
            
            UserManagementAction->>Browser: Set success message
            UserManagementAction->>StrutsFramework: Forward to changePassword
            StrutsFramework->>Browser: Render changePassword.jsp with success message
            Browser-->>User: Display "Password changed successfully" message
        end
    end
```

---

## Summary

The Login Module handles:

1. **Authentication**: User validation, password encryption, and credential verification
2. **Session Management**: Comprehensive session initialization with user details, language, models, functionalities, and connection setup
3. **Authorization**: Loading user functionalities and permissions based on user type
4. **Localization**: Language selection and resource bundle loading
5. **Logout**: Session invalidation and cleanup
6. **Password Management**: Password change functionality with current password verification

All flows integrate with the SQL Server database through DAO classes and use Hibernate for ORM operations where applicable.

