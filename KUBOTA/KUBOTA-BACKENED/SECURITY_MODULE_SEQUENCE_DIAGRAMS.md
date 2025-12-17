# Security Module - Detailed Sequence Diagrams

This document contains comprehensive sequence diagrams for the KUBOTA Security Module, covering authentication, authorization, token management, and error handling flows.

## Table of Contents
1. [Token Generation and Login Flow](#1-token-generation-and-login-flow)
2. [Request Authentication Filter Flow](#2-request-authentication-filter-flow)
3. [User Logout Flow](#3-user-logout-flow)
4. [Get Logged In User Flow](#4-get-logged-in-user-flow)
5. [Check User Login Status Flow](#5-check-user-login-status-flow)
6. [Unauthorized Access Handling Flow](#6-unauthorized-access-handling-flow)
7. [Access Denied Handling Flow](#7-access-denied-handling-flow)

---

## 1. Token Generation and Login Flow

This flow shows how a JWT token is generated, encrypted, and stored when a user successfully logs in.

```mermaid
sequenceDiagram
    participant Client
    participant LoginController as Login Controller<br/>(External)
    participant JwtProvider
    participant UserDetailsService as UserDetailsServiceImpl
    participant KubotaUserRepo as KubotaUserRepository
    participant LoginUserService as LoginUserService
    participant UserFactory
    participant UserTokenService as UserTokenServiceImpl
    participant SysUserTokenRepo as SysUserTokenRepo
    participant Database as Database<br/>(SYS_USER_TOKEN)

    Client->>LoginController: POST /api/kubotauser/login<br/>(username, password)
    
    Note over LoginController: Validate credentials
    
    LoginController->>UserDetailsService: loadUserByUsername(username)
    UserDetailsService->>KubotaUserRepo: findByUserName(username)
    KubotaUserRepo-->>UserDetailsService: Optional<LoginUser>
    
    alt User Not Found
        UserDetailsService-->>LoginController: UsernameNotFoundException
        LoginController-->>Client: 401 Unauthorized
    else User Found
        UserDetailsService->>LoginUserService: getAuthenticatedUser(username)
        LoginUserService-->>UserDetailsService: AuthenticatedUser
        UserDetailsService->>UserFactory: create(AuthenticatedUser)
        UserFactory-->>UserDetailsService: SecurityUser
        UserDetailsService-->>LoginController: UserDetails (SecurityUser)
        
        Note over LoginController: Password validation successful
        
        LoginController->>JwtProvider: generateEncryptedToken(AuthenticatedUser)
        
        Note over JwtProvider: Initialize RSA Keys<br/>(@PostConstruct)
        JwtProvider->>JwtProvider: initKeys()<br/>(Load from KeyStore)
        
        JwtProvider->>JwtProvider: generateToken(AuthenticatedUser)
        Note over JwtProvider: Create JWT Claims:<br/>- sub: username<br/>- status: loginIdStatus<br/>- dealerEmployeeId<br/>- kubotaEmployeeId<br/>- dealerId<br/>- id<br/>- branchId<br/>- created: currentDate
        JwtProvider->>JwtProvider: generateToken(claims)<br/>(Sign with HS512)
        JwtProvider-->>JwtProvider: JWT Token (signed)
        
        JwtProvider->>JwtProvider: encryptJWT(token)
        Note over JwtProvider: Create JWE Object<br/>(RSA-OAEP-256, A256GCM)
        JwtProvider->>JwtProvider: encrypt with RSAEncrypter(publicKey)
        JwtProvider-->>LoginController: Encrypted JWT Token
        
        LoginController->>UserTokenService: recordToken(username, encryptedToken)
        
        UserTokenService->>SysUserTokenRepo: findByUserName(username)
        SysUserTokenRepo->>Database: SELECT * FROM SYS_USER_TOKEN<br/>WHERE userName = ?
        Database-->>SysUserTokenRepo: SysUserToken (if exists)
        SysUserTokenRepo-->>UserTokenService: SysUserToken or null
        
        alt Token Already Exists
            UserTokenService-->>LoginController: IllegalStateException<br/>("Someone is already logged in")
            LoginController-->>Client: 400 Bad Request
        else New Token
            UserTokenService->>UserTokenService: Create new SysUserToken<br/>- userName<br/>- token: encryptedToken<br/>- isLoggedIn: true<br/>- createdDate: now
            UserTokenService->>SysUserTokenRepo: save(sysUserToken)
            SysUserTokenRepo->>Database: INSERT INTO SYS_USER_TOKEN
            Database-->>SysUserTokenRepo: Saved Entity
            SysUserTokenRepo-->>UserTokenService: SysUserToken
            UserTokenService-->>LoginController: Token Recorded
            
            LoginController-->>Client: 200 OK<br/>{token: encryptedJWT}
        end
    end
```

---

## 2. Request Authentication Filter Flow

This flow shows how every incoming request is authenticated through the filter chain.

```mermaid
sequenceDiagram
    participant Client
    participant FilterChain as Spring Filter Chain
    participant AuthTokenFilter as AuthenticationTokenFilter
    participant JwtProvider
    participant UserDetailsService as UserDetailsServiceImpl
    participant KubotaUserRepo as KubotaUserRepository
    participant LoginUserService as LoginUserService
    participant UserFactory
    participant UserTokenService as UserTokenServiceImpl
    participant SysUserTokenRepo as SysUserTokenRepo
    participant Database as Database<br/>(SYS_USER_TOKEN)
    participant SecurityContext as SecurityContextHolder
    participant ResourceController as Protected Resource Controller

    Client->>FilterChain: HTTP Request<br/>(Header: javatab.token.header)
    
    FilterChain->>AuthTokenFilter: doFilter(request, response, chain)
    
    AuthTokenFilter->>AuthTokenFilter: Extract encryptedToken<br/>from request header
    
    alt No Token Provided
        AuthTokenFilter->>FilterChain: chain.doFilter(request, response)
        FilterChain->>ResourceController: Forward Request
        ResourceController->>SecurityContext: Check Authentication
        SecurityContext-->>ResourceController: null (No Auth)
        ResourceController-->>FilterChain: 401 Unauthorized
        FilterChain-->>Client: 401 Unauthorized<br/>(via SecurityUnauthorisedHandler)
    else Token Provided
        AuthTokenFilter->>JwtProvider: decryptToken(encryptedToken)
        
        alt Decryption Fails
            JwtProvider-->>AuthTokenFilter: Exception
            Note over AuthTokenFilter: Log error, continue filter chain
            AuthTokenFilter->>FilterChain: chain.doFilter(request, response)
            FilterChain->>ResourceController: Forward Request
            ResourceController-->>FilterChain: 401 Unauthorized
            FilterChain-->>Client: 401 Unauthorized
        else Decryption Success
            JwtProvider-->>AuthTokenFilter: decryptedToken (JWT)
            
            AuthTokenFilter->>JwtProvider: getUsernameFromToken(decryptedToken)
            JwtProvider->>JwtProvider: getClaimsFromToken(token)<br/>(Parse JWT with secret)
            JwtProvider-->>AuthTokenFilter: username
            
            alt Username is null OR SecurityContext already has auth
                AuthTokenFilter->>FilterChain: chain.doFilter(request, response)
                FilterChain->>ResourceController: Forward Request
            else Username exists AND no existing auth
                AuthTokenFilter->>UserDetailsService: loadUserByUsername(username)
                UserDetailsService->>KubotaUserRepo: findByUserName(username)
                KubotaUserRepo-->>UserDetailsService: Optional<LoginUser>
                
                alt User Not Found
                    UserDetailsService-->>AuthTokenFilter: UsernameNotFoundException
                    AuthTokenFilter->>FilterChain: chain.doFilter(request, response)
                    FilterChain-->>Client: 401 Unauthorized
                else User Found
                    UserDetailsService->>LoginUserService: getAuthenticatedUser(username)
                    LoginUserService-->>UserDetailsService: AuthenticatedUser
                    UserDetailsService->>UserFactory: create(AuthenticatedUser)
                    UserFactory-->>UserDetailsService: SecurityUser
                    UserDetailsService-->>AuthTokenFilter: UserDetails (SecurityUser)
                    
                    AuthTokenFilter->>JwtProvider: validateToken(decryptedToken, userDetails)
                    JwtProvider->>JwtProvider: getUsernameFromToken(token)
                    JwtProvider->>JwtProvider: isTokenExpired(token)
                    JwtProvider->>JwtProvider: Check username matches<br/>AND token not expired
                    JwtProvider-->>AuthTokenFilter: validationResult (boolean)
                    
                    AuthTokenFilter->>UserTokenService: isExpiredToken(username, encryptedToken)
                    UserTokenService->>SysUserTokenRepo: findByUserNameAndToken(username, encryptedToken)
                    SysUserTokenRepo->>Database: SELECT * FROM SYS_USER_TOKEN<br/>WHERE userName = ? AND token = ?
                    Database-->>SysUserTokenRepo: SysUserToken or null
                    SysUserTokenRepo-->>UserTokenService: SysUserToken
                    
                    alt Token Not Found in DB
                        UserTokenService-->>AuthTokenFilter: true (expired)
                    else Token Found
                        UserTokenService->>UserTokenService: Check isLoggedIn flag
                        UserTokenService-->>AuthTokenFilter: !isLoggedIn (expired if false)
                    end
                    
                    alt Token Valid AND Not Expired in DB
                        AuthTokenFilter->>AuthTokenFilter: Create UsernamePasswordAuthenticationToken<br/>(userDetails, null, authorities)
                        AuthTokenFilter->>AuthTokenFilter: Set WebAuthenticationDetails<br/>(from request)
                        AuthTokenFilter->>SecurityContext: setAuthentication(authentication)
                        SecurityContext-->>AuthTokenFilter: Authentication Set
                        
                        Note over AuthTokenFilter: Log request URL and username
                        
                        AuthTokenFilter->>FilterChain: chain.doFilter(request, response)
                        FilterChain->>ResourceController: Forward Request
                        ResourceController->>SecurityContext: getAuthentication()
                        SecurityContext-->>ResourceController: Authentication (with user details)
                        ResourceController-->>FilterChain: 200 OK (with data)
                        FilterChain-->>Client: 200 OK
                    else Token Invalid OR Expired
                        AuthTokenFilter->>FilterChain: chain.doFilter(request, response)
                        FilterChain->>ResourceController: Forward Request
                        ResourceController->>SecurityContext: Check Authentication
                        SecurityContext-->>ResourceController: null (No Auth)
                        ResourceController-->>FilterChain: 401 Unauthorized
                        FilterChain-->>Client: 401 Unauthorized<br/>(via SecurityUnauthorisedHandler)
                    end
                end
            end
        end
    end
```

---

## 3. User Logout Flow

This flow shows how a user logs out and their token is invalidated.

```mermaid
sequenceDiagram
    participant Client
    participant UserTokenController as UserTokenController
    participant UserTokenService as UserTokenServiceImpl
    participant JwtProvider
    participant SysUserTokenRepo as SysUserTokenRepo
    participant Database as Database<br/>(SYS_USER_TOKEN)

    Client->>UserTokenController: GET /api/security/userToken/logoutUser<br/>(Header: javatab.token.header)
    
    UserTokenController->>UserTokenService: logoutUser(request)
    
    UserTokenService->>UserTokenService: Extract encryptedToken<br/>from request header
    
    UserTokenService->>JwtProvider: decryptToken(encryptedToken)
    
    alt Decryption Fails
        JwtProvider-->>UserTokenService: Exception
        UserTokenService-->>UserTokenController: RuntimeException
        UserTokenController-->>Client: 400 Bad Request<br/>{message: "Error occurred"}
    else Decryption Success
        JwtProvider-->>UserTokenService: decryptedToken
        
        UserTokenService->>JwtProvider: getUsernameFromToken(decryptedToken)
        JwtProvider-->>UserTokenService: username
        
        UserTokenService->>SysUserTokenRepo: findByUserName(username)
        SysUserTokenRepo->>Database: SELECT * FROM SYS_USER_TOKEN<br/>WHERE userName = ?
        Database-->>SysUserTokenRepo: SysUserToken or null
        SysUserTokenRepo-->>UserTokenService: SysUserToken
        
        alt Token Not Found
            UserTokenService-->>UserTokenController: "User Token Does Not Exist."
            UserTokenController->>UserTokenController: Create ApiResponse<br/>(result: "User Token Does Not Exist.")
            UserTokenController-->>Client: 200 OK<br/>{result: "User Token Does Not Exist.",<br/>message: "Logout User Successful!",<br/>status: 200}
        else Token Found
            UserTokenService->>UserTokenService: Set isLoggedIn = false<br/>Set lastModifiedDate = now
            UserTokenService->>SysUserTokenRepo: save(sysUserToken)
            SysUserTokenRepo->>Database: UPDATE SYS_USER_TOKEN<br/>SET isLoggedIn = false,<br/>lastModifiedDate = ?<br/>WHERE id = ?
            Database-->>SysUserTokenRepo: Updated Entity
            SysUserTokenRepo-->>UserTokenService: SysUserToken
            UserTokenService-->>UserTokenController: "Success"
            
            UserTokenController->>UserTokenController: Create ApiResponse<br/>(result: "Success")
            UserTokenController-->>Client: 200 OK<br/>{result: "Success",<br/>message: "Logout User Successful!",<br/>status: 200}
        end
    end
```

---

## 4. Get Logged In User Flow

This flow shows how the current logged-in user details are retrieved.

```mermaid
sequenceDiagram
    participant Client
    participant UserTokenController as UserTokenController
    participant UserTokenService as UserTokenServiceImpl
    participant UserAuthentication as UserAuthenticationImpl
    participant SecurityContext as SecurityContextHolder
    participant SecurityUser

    Client->>UserTokenController: GET /api/security/userToken/getLoggedInUser<br/>(Header: javatab.token.header)
    
    Note over UserTokenController: Request must be authenticated<br/>(via AuthenticationTokenFilter)
    
    UserTokenController->>UserTokenService: getLoggedInUser()
    
    UserTokenService->>UserAuthentication: getLoginId()
    UserAuthentication->>SecurityContext: getContext().getAuthentication().getPrincipal()
    SecurityContext-->>UserAuthentication: SecurityUser
    UserAuthentication->>SecurityUser: getId()
    SecurityUser-->>UserAuthentication: loginId
    UserAuthentication-->>UserTokenService: loginId
    
    UserTokenService->>UserAuthentication: getBranchId()
    UserAuthentication->>SecurityContext: getSecurityUser()
    SecurityContext-->>UserAuthentication: SecurityUser
    UserAuthentication->>SecurityUser: getBranchId()
    SecurityUser-->>UserAuthentication: branchId
    UserAuthentication-->>UserTokenService: branchId
    
    UserTokenService->>UserAuthentication: getDealerId()
    UserAuthentication->>SecurityUser: getDealerId()
    SecurityUser-->>UserAuthentication: dealerId
    UserAuthentication-->>UserTokenService: dealerId
    
    UserTokenService->>UserAuthentication: getUsername()
    UserAuthentication->>SecurityUser: getUsername()
    SecurityUser-->>UserAuthentication: username
    UserAuthentication-->>UserTokenService: username
    
    UserTokenService->>UserAuthentication: getKubotaEmployeeId()
    UserAuthentication->>SecurityUser: getKubotaEmployeeId()
    SecurityUser-->>UserAuthentication: kubotaEmployeeId
    UserAuthentication-->>UserTokenService: kubotaEmployeeId
    
    UserTokenService->>UserAuthentication: getDealerEmployeeId()
    UserAuthentication->>SecurityUser: getDealerEmployeeId()
    SecurityUser-->>UserAuthentication: dealerEmployeeId
    UserAuthentication-->>UserTokenService: dealerEmployeeId
    
    UserTokenService->>UserTokenService: Create LoggedInUserDetailsResDto<br/>with all user details
    UserTokenService-->>UserTokenController: LoggedInUserDetailsResDto
    
    UserTokenController->>UserTokenController: Create ApiResponse<br/>(result: LoggedInUserDetailsResDto)
    UserTokenController-->>Client: 200 OK<br/>{result: {loginId, branchId, dealerId,<br/>username, kubotaEmployeeId, dealerEmployeeId},<br/>message: "Logged-in User Get Successful!",<br/>status: 200}
```

---

## 5. Check User Login Status Flow

This flow shows how the system checks if a user is currently logged in.

```mermaid
sequenceDiagram
    participant Client
    participant UserTokenController as UserTokenController
    participant UserTokenService as UserTokenServiceImpl
    participant JwtProvider
    participant SysUserTokenRepo as SysUserTokenRepo
    participant Database as Database<br/>(SYS_USER_TOKEN)

    Client->>UserTokenController: GET /api/security/userToken/isUserLoggedIn<br/>(Header: javatab.token.header)
    
    UserTokenController->>UserTokenService: isUserLoggedIn(request)
    
    UserTokenService->>UserTokenService: Extract encryptedToken<br/>from request header
    
    UserTokenService->>JwtProvider: decryptToken(encryptedToken)
    
    alt Decryption Fails
        JwtProvider-->>UserTokenService: Exception
        UserTokenService-->>UserTokenController: RuntimeException
        UserTokenController-->>Client: 400 Bad Request<br/>{message: "Error occurred"}
    else Decryption Success
        JwtProvider-->>UserTokenService: decryptedToken
        
        UserTokenService->>JwtProvider: getUsernameFromToken(decryptedToken)
        JwtProvider-->>UserTokenService: username
        
        alt Username is null
            UserTokenService-->>UserTokenController: false
        else Username exists
            UserTokenService->>UserTokenService: isExpiredToken(username, encryptedToken)
            UserTokenService->>SysUserTokenRepo: findByUserNameAndToken(username, encryptedToken)
            SysUserTokenRepo->>Database: SELECT * FROM SYS_USER_TOKEN<br/>WHERE userName = ? AND token = ?
            Database-->>SysUserTokenRepo: SysUserToken or null
            SysUserTokenRepo-->>UserTokenService: SysUserToken
            
            alt Token Not Found in DB
                UserTokenService-->>UserTokenService: return true (expired)
            else Token Found
                UserTokenService->>UserTokenService: Check isLoggedIn flag
                UserTokenService-->>UserTokenService: return !isLoggedIn
            end
            
            UserTokenService->>UserTokenService: Evaluate: username != null<br/>AND !isExpiredToken
            UserTokenService-->>UserTokenController: boolean result
            
            alt User is Logged In
                UserTokenController->>UserTokenController: Create ApiResponse<br/>(result: true)
                UserTokenController-->>Client: 200 OK<br/>{result: true,<br/>message: "User Logged-In!",<br/>status: 200}
            else User Not Logged In
                UserTokenController->>UserTokenController: Create ApiResponse<br/>(result: false)
                UserTokenController-->>Client: 200 OK<br/>{result: false,<br/>message: "User Not Logged-In!",<br/>status: 200}
            end
        end
    end
```

---

## 6. Unauthorized Access Handling Flow

This flow shows how unauthorized access (401) is handled.

```mermaid
sequenceDiagram
    participant Client
    participant FilterChain as Spring Filter Chain
    participant AuthTokenFilter as AuthenticationTokenFilter
    participant SecurityConfig as WebSecurityConfiguration
    participant UnauthorisedHandler as SecurityUnauthorisedHandler
    participant ObjectMapper

    Client->>FilterChain: HTTP Request<br/>(Invalid/Missing Token)
    
    FilterChain->>AuthTokenFilter: doFilter(request, response, chain)
    
    alt No Token OR Invalid Token OR Expired Token
        AuthTokenFilter->>FilterChain: chain.doFilter(request, response)
        FilterChain->>FilterChain: Authentication Check Fails
        
        Note over SecurityConfig: Exception Handling Configuration:<br/>.exceptionHandling()<br/>.authenticationEntryPoint(unauthorizedHandler)
        
        FilterChain->>UnauthorisedHandler: commence(request, response,<br/>AuthenticationException)
        
        UnauthorisedHandler->>UnauthorisedHandler: Create ApiResponse<br/>(message: "Unauthorized. Token is expired")
        UnauthorisedHandler->>UnauthorisedHandler: Set Response Status: 401
        UnauthorisedHandler->>UnauthorisedHandler: Set Content-Type: APPLICATION_JSON
        
        UnauthorisedHandler->>ObjectMapper: writeValue(outputStream, apiResponse)
        ObjectMapper-->>UnauthorisedHandler: JSON Serialized
        
        UnauthorisedHandler->>UnauthorisedHandler: Flush OutputStream
        
        UnauthorisedHandler-->>FilterChain: Response Sent
        FilterChain-->>Client: 401 Unauthorized<br/>{message: "Unauthorized. Token is expired"}
    end
```

---

## 7. Access Denied Handling Flow

This flow shows how access denied (403) is handled when a user lacks required permissions.

```mermaid
sequenceDiagram
    participant Client
    participant FilterChain as Spring Filter Chain
    participant ResourceController as Protected Resource Controller
    participant SecurityConfig as WebSecurityConfiguration
    participant DeniedHandler as SecurityDeniedHandler
    participant ObjectMapper
    participant ResponseEntity

    Client->>FilterChain: HTTP Request<br/>(Valid Token, Insufficient Permissions)
    
    Note over FilterChain: Request authenticated successfully<br/>via AuthenticationTokenFilter
    
    FilterChain->>ResourceController: Forward Request
    
    ResourceController->>ResourceController: Check Authorization<br/>(@PreAuthorize, @Secured, etc.)
    
    alt User Lacks Required Permissions
        ResourceController-->>FilterChain: AccessDeniedException
        
        Note over SecurityConfig: Exception Handling Configuration:<br/>.exceptionHandling()<br/>.accessDeniedHandler(securityDeniedHandler)
        
        FilterChain->>DeniedHandler: handle(request, response,<br/>AccessDeniedException)
        
        DeniedHandler->>DeniedHandler: Create ApiResponse<br/>(status: 403, message: "Access Denied")
        DeniedHandler->>DeniedHandler: Create ResponseEntity<br/>(apiResponse, HttpStatus.FORBIDDEN)
        
        DeniedHandler->>ObjectMapper: writeValue(outputStream, ResponseEntity)
        ObjectMapper-->>DeniedHandler: JSON Serialized
        
        DeniedHandler->>DeniedHandler: Flush OutputStream
        
        DeniedHandler-->>FilterChain: Response Sent
        FilterChain-->>Client: 403 Forbidden<br/>{status: 403, message: "Access Denied"}
    else User Has Required Permissions
        ResourceController-->>FilterChain: 200 OK (with data)
        FilterChain-->>Client: 200 OK
    end
```

---

## Component Overview

### Key Components

1. **WebSecurityConfiguration**: Configures Spring Security, sets up authentication filter, and defines security rules
2. **AuthenticationTokenFilter**: Intercepts all requests, validates JWT tokens, and sets security context
3. **JwtProvider**: Generates, validates, encrypts, and decrypts JWT tokens
4. **UserDetailsServiceImpl**: Loads user details from database for authentication
5. **UserTokenService**: Manages token lifecycle (record, validate, expire) in database
6. **UserTokenController**: REST endpoints for token management (logout, get user, check status)
7. **SecurityUnauthorisedHandler**: Handles 401 Unauthorized responses
8. **SecurityDeniedHandler**: Handles 403 Forbidden responses

### Security Features

- **JWT Token Authentication**: Stateless authentication using JWT tokens
- **Token Encryption**: JWT tokens are encrypted using RSA-OAEP-256 and AES-GCM
- **Token Expiration Tracking**: Database-backed token expiration to prevent reuse after logout
- **Session Management**: Stateless sessions (no server-side session storage)
- **Role-Based Access Control**: Spring Security method-level security with @PreAuthorize
- **CORS Support**: Cross-origin resource sharing enabled
- **CSRF Protection**: Disabled for stateless API (can be enabled if needed)

### Database Schema

**SYS_USER_TOKEN Table:**
- `id`: Primary key
- `userName`: Username (varchar(30), not null)
- `token`: Encrypted JWT token (varchar(max), not null)
- `isLoggedIn`: Boolean flag indicating if token is active (not null)
- `createdDate`: Token creation timestamp
- `lastModifiedDate`: Last modification timestamp

---

## Notes

- All tokens are encrypted before being sent to clients
- Tokens are validated both cryptographically (JWT signature) and against database (logout status)
- The system prevents multiple simultaneous logins for the same user
- Token expiration is checked both in JWT claims and database status
- Security context is set per request and cleared after request processing (stateless)

