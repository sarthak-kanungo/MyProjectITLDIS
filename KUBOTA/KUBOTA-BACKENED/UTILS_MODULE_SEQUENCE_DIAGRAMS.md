## Utils Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.utils` module:

- **System Date Retrieval** (REST endpoints for getting system dates).
- **Encryption/Decryption** (AES encryption and decryption utilities).
- **Number Generation** (Code generation for various entities).
- **Excel Generation** (Excel file generation for reports).
- **Excel Import** (Excel file validation and import).
- **JWT Key Management** (Loading RSA keys from keystore).
- **API Response Wrapper** (Standardized API response structure).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the Utils module.

---

## 1. System Date Retrieval Flow

This flow shows how **system dates** are retrieved via REST endpoints. The `SystemDateController` provides two endpoints for getting the current system date.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as SystemDateController
    participant ApiResponse as ApiResponse
    participant System as System (Date)
    
    %% Get Date Flow
    Note over Client,System: System Date Retrieval - GET /api/getDate
    
    Client->>Controller: GET /api/getDate
    Controller->>System: new Date()
    System-->>Controller: Current Date Object
    Controller->>Controller: Create new ApiResponse()
    Controller->>ApiResponse: setMessage("get date")
    Controller->>ApiResponse: setStatus(HttpStatus.OK.value())
    Controller->>ApiResponse: setResult(date)
    Controller-->>Client: ResponseEntity.ok(apiResponse)
    
    %% Get System Generated Date Flow
    Note over Client,System: System Generated Date - GET /api/getSystemGeneratedDate
    
    Client->>Controller: GET /api/getSystemGeneratedDate
    Controller->>System: new Date()
    System-->>Controller: Current Date Object
    Controller->>Controller: Create new ApiResponse()
    Controller->>ApiResponse: setMessage("get date")
    Controller->>ApiResponse: setStatus(HttpStatus.OK.value())
    Controller->>ApiResponse: setResult(date)
    Controller-->>Client: ResponseEntity.ok(apiResponse)
```

---

## 2. Encryption/Decryption Flow

This flow shows how **data encryption and decryption** works using AES/CBC/PKCS5Padding algorithm with SHA-512 key derivation.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Service as Service Layer
    participant EncUtil as EncryptionDecryptionUtil
    participant MessageDigest as MessageDigest (SHA-512)
    participant Cipher as Cipher (AES/CBC/PKCS5Padding)
    participant Base64 as Base64 Encoder/Decoder
    participant Random as Random (IV Generator)
    
    %% Encryption Flow
    Note over Client,Random: Data Encryption Flow
    
    Client->>Service: encrypt(plainText)
    Service->>EncUtil: encrypt(value)
    
    EncUtil->>EncUtil: Get key = "thrsl@@@"
    EncUtil->>MessageDigest: getInstance("SHA-512")
    MessageDigest-->>EncUtil: digest instance
    EncUtil->>MessageDigest: digest(key.getBytes("UTF-8"))
    MessageDigest-->>EncUtil: SHA-512 hash bytes
    
    EncUtil->>EncUtil: Convert hash bytes to hex string
    EncUtil->>EncUtil: Extract first 16 characters as shaKey
    
    EncUtil->>Random: getIV()
    Random->>Random: Generate 16-character random IV from "1234567890"
    Random-->>EncUtil: IV string
    
    EncUtil->>EncUtil: fixKey(shaKey) - Ensure 16-byte length
    EncUtil->>Cipher: getInstance("AES/CBC/PKCS5PADDING")
    Cipher-->>EncUtil: cipher instance
    EncUtil->>Cipher: init(ENCRYPT_MODE, secretKey, ivSpec)
    EncUtil->>Cipher: doFinal(value.getBytes())
    Cipher-->>EncUtil: Encrypted bytes
    
    EncUtil->>Base64: encode(encryptedData)
    Base64-->>EncUtil: encryptedDataInBase64
    EncUtil->>Base64: encode(iv.getBytes("UTF-8"))
    Base64-->>EncUtil: ivInBase64
    
    EncUtil->>EncUtil: Return encryptedDataInBase64 + ":" + ivInBase64
    EncUtil-->>Service: Encrypted string (data:iv)
    Service-->>Client: Encrypted data
    
    %% Decryption Flow
    Note over Client,Random: Data Decryption Flow
    
    Client->>Service: decrypt(encryptedData)
    Service->>EncUtil: decrypt(data)
    
    EncUtil->>EncUtil: Split data by ":" to get parts[0] and parts[1]
    EncUtil->>EncUtil: Get key = "thrsl@@@"
    EncUtil->>MessageDigest: getInstance("SHA-512")
    MessageDigest-->>EncUtil: digest instance
    EncUtil->>MessageDigest: digest(key.getBytes("UTF-8"))
    MessageDigest-->>EncUtil: SHA-512 hash bytes
    
    EncUtil->>EncUtil: Convert hash bytes to hex string
    EncUtil->>EncUtil: Extract first 16 characters as shaKey
    
    EncUtil->>Base64: decode(parts[1]) - Decode IV
    Base64-->>EncUtil: IV bytes
    EncUtil->>EncUtil: Create IvParameterSpec from decoded IV
    
    EncUtil->>Base64: decode(parts[0]) - Decode encrypted data
    Base64-->>EncUtil: Encrypted bytes
    
    EncUtil->>Cipher: getInstance("AES/CBC/PKCS5PADDING")
    Cipher-->>EncUtil: cipher instance
    EncUtil->>Cipher: init(DECRYPT_MODE, secretKey, iv)
    EncUtil->>Cipher: doFinal(decodedEncryptedData)
    Cipher-->>EncUtil: Original plaintext bytes
    
    EncUtil->>EncUtil: new String(original)
    EncUtil-->>Service: Decrypted plaintext
    Service-->>Client: Decrypted data
```

---

## 3. Number Generation Flow

This flow shows how **various entity codes** are generated using the `NumberGenerator` utility class.

```mermaid
sequenceDiagram
    %% Participants
    participant Service as Service Layer
    participant NumberGen as NumberGenerator
    participant System as System.out
    
    %% Employee Code Generation
    Note over Service,System: Kubota Employee Code Generation
    
    Service->>NumberGen: generateKubotaEmployeeCode(id)
    NumberGen->>System: print("id" + id)
    NumberGen->>NumberGen: Return "KEMP-" + id
    NumberGen-->>Service: "KEMP-{id}"
    
    %% Scheme Number Generation
    Note over Service,System: Scheme Number Generation
    
    Service->>NumberGen: generateSchemeNo(id)
    NumberGen->>System: print("id" + id)
    NumberGen->>NumberGen: Return "SCH-" + id
    NumberGen-->>Service: "SCH-{id}"
    
    %% Transport Code Generation
    Note over Service,System: Transport Code Generation
    
    Service->>NumberGen: generateTrasnportCode(id)
    NumberGen->>System: print("id" + id)
    NumberGen->>NumberGen: Return "TR-" + id
    NumberGen-->>Service: "TR-{id}"
    
    %% Party Code Generation
    Note over Service,System: Party Code Generation
    
    Service->>NumberGen: generatePartyCode(id)
    NumberGen->>System: print("id" + id)
    NumberGen->>NumberGen: Return "PM-" + id
    NumberGen-->>Service: "PM-{id}"
    
    %% Branch Code Generation
    Note over Service,System: Branch Code Generation
    
    Service->>NumberGen: generateBranchCode(id)
    NumberGen->>System: print("id" + id)
    NumberGen->>NumberGen: Return "BR-" + id
    NumberGen-->>Service: "BR-{id}"
    
    %% DMS Item Number Generation
    Note over Service,System: DMS Item Number Generation
    
    Service->>NumberGen: generateDmsItemNumber(id)
    NumberGen->>System: print("id" + id)
    NumberGen->>NumberGen: Return "LP-" + id
    NumberGen-->>Service: "LP-{id}"
    
    %% Installation Number Generation
    Note over Service,System: Installation Number Generation
    
    Service->>NumberGen: generateInstallationNumber(id)
    NumberGen->>System: print("id" + id)
    NumberGen->>NumberGen: Return "IN-" + id
    NumberGen-->>Service: "IN-{id}"
```

---

## 4. Excel Generation Flow

This flow shows how **Excel files** are generated using the `ExcelCellGenerator` utility class. This is a comprehensive utility that generates Excel files for various reports across the system.

```mermaid
sequenceDiagram
    %% Participants
    participant Controller as Controller Layer
    participant Service as Service Layer
    participant ExcelGen as ExcelCellGenerator
    participant Workbook as XSSFWorkbook
    participant Sheet as Sheet
    participant Row as Row
    participant Cell as Cell
    participant CellStyle as CellStyle
    participant Font as Font
    participant ByteArrayOutputStream as ByteArrayOutputStream
    participant ByteArrayInputStream as ByteArrayInputStream
    
    %% Excel Generation Flow
    Note over Controller,ByteArrayInputStream: Excel File Generation Flow
    
    Controller->>Service: generateExcelReport(dataList)
    Service->>ExcelGen: generateSalesPreSalesTransactionReport(dataList)
    
    ExcelGen->>ExcelGen: Define column headers array
    ExcelGen->>Workbook: new XSSFWorkbook()
    Workbook-->>ExcelGen: workbook instance
    
    ExcelGen->>Workbook: getCreationHelper()
    Workbook-->>ExcelGen: creationHelper
    
    ExcelGen->>Workbook: createSheet("Transaction Report")
    Workbook-->>ExcelGen: sheet instance
    
    ExcelGen->>Workbook: createFont()
    Workbook-->>ExcelGen: headerFont
    ExcelGen->>Font: setBold(true)
    
    ExcelGen->>Workbook: createCellStyle()
    Workbook-->>ExcelGen: headerCellStyle
    ExcelGen->>CellStyle: setFont(headerFont)
    
    ExcelGen->>Sheet: createRow(0)
    Sheet-->>ExcelGen: headerRow
    
    loop For each column
        ExcelGen->>Row: createCell(col)
        Row-->>ExcelGen: cell instance
        ExcelGen->>Cell: setCellValue(columns[col])
        ExcelGen->>Cell: setCellStyle(headerCellStyle)
    end
    
    loop For each data item
        ExcelGen->>Sheet: createRow(rowIdx++)
        Sheet-->>ExcelGen: dataRow
        
        loop For each field
            ExcelGen->>Row: createCell(fieldIndex)
            Row-->>ExcelGen: cell instance
            ExcelGen->>Cell: setCellValue(data.getField())
        end
    end
    
    ExcelGen->>ExcelGen: new ByteArrayOutputStream()
    ExcelGen->>Workbook: write(out)
    Workbook->>ByteArrayOutputStream: Write workbook bytes
    
    ExcelGen->>ByteArrayOutputStream: toByteArray()
    ByteArrayOutputStream-->>ExcelGen: byte array
    
    ExcelGen->>ByteArrayInputStream: new ByteArrayInputStream(bytes)
    ByteArrayInputStream-->>ExcelGen: inputStream
    
    ExcelGen-->>Service: ByteArrayInputStream
    Service-->>Controller: ByteArrayInputStream (Excel file)
    Controller-->>Client: HTTP Response with Excel file
```

---

## 5. Excel Import Validation Flow

This flow shows how **Excel files are validated** during import using the `ExcelImportManager` utility class.

```mermaid
sequenceDiagram
    %% Participants
    participant Controller as Controller Layer
    participant Service as Service Layer
    participant ExcelManager as ExcelImportManager
    participant Workbook as Workbook (Apache POI)
    participant Sheet as Sheet
    participant Row as Row
    participant Cell as Cell
    participant InvalidColumnException as InvalidColumnException
    
    %% Excel Import Validation Flow
    Note over Controller,InvalidColumnException: Excel Import Validation Flow
    
    Controller->>Service: importExcelFile(multipartFile)
    Service->>ExcelManager: getXLSHeaders(workbook)
    
    ExcelManager->>Workbook: getSheetAt(0)
    Workbook-->>ExcelManager: sheet instance
    
    ExcelManager->>Sheet: getRow(0)
    Sheet-->>ExcelManager: headerRow
    
    ExcelManager->>ExcelManager: Create empty headers list
    
    loop For each cell in header row
        ExcelManager->>Row: cellIterator()
        Row-->>ExcelManager: cell iterator
        ExcelManager->>Cell: getStringCellValue()
        Cell-->>ExcelManager: header string
        ExcelManager->>ExcelManager: headers.add(header)
    end
    
    ExcelManager-->>Service: List<String> headers
    
    Service->>ExcelManager: checkXLSValidity(preDefinedColumns, headers)
    
    ExcelManager->>ExcelManager: IntStream.range(0, preDefinedColumns.length - 1)
    
    loop For each column position
        alt Column name mismatch
            ExcelManager->>ExcelManager: !preDefinedColumns[pos].equals(headers.get(pos))
            ExcelManager->>InvalidColumnException: new InvalidColumnException("Invalid column at position " + pos)
            InvalidColumnException-->>Service: Exception thrown
            Service-->>Controller: Exception response
        end
    end
    
    alt Extra columns
        ExcelManager->>ExcelManager: headers.size() > preDefinedColumns.length
        ExcelManager->>InvalidColumnException: new InvalidColumnException("You have an extra column")
        InvalidColumnException-->>Service: Exception thrown
        Service-->>Controller: Exception response
    else Less columns
        ExcelManager->>ExcelManager: headers.size() < preDefinedColumns.length
        ExcelManager->>InvalidColumnException: new InvalidColumnException("You have less columns than expected")
        InvalidColumnException-->>Service: Exception thrown
        Service-->>Controller: Exception response
    else Validation successful
        ExcelManager-->>Service: Empty string (validation passed)
        Service->>Service: Process Excel data rows
        Service-->>Controller: Success response with imported data
    end
```

---

## 6. JWT Key Loading Flow

This flow shows how **RSA keys are loaded** from a Java KeyStore (JKS) for JWT token generation and validation.

```mermaid
sequenceDiagram
    %% Participants
    participant JwtProvider as JwtProvider
    participant KeyStoreUtil as JweKeyStoreUtil
    participant FileInputStream as FileInputStream
    participant KeyStore as KeyStore (JKS)
    participant PrivateKeyEntry as PrivateKeyEntry
    participant Certificate as Certificate
    participant RSAPrivateKey as RSAPrivateKey
    participant RSAPublicKey as RSAPublicKey
    
    %% Private Key Loading Flow
    Note over JwtProvider,RSAPrivateKey: Private Key Loading Flow
    
    JwtProvider->>KeyStoreUtil: loadPrivateKeyFromKeyStore(keyStorePath, keyStorePassword, alias, keyPassword)
    
    KeyStoreUtil->>KeyStore: getInstance("JKS")
    KeyStore-->>KeyStoreUtil: keyStore instance
    
    KeyStoreUtil->>FileInputStream: new FileInputStream(keyStorePath)
    FileInputStream-->>KeyStoreUtil: fileInputStream
    
    KeyStoreUtil->>KeyStore: load(fileInputStream, keyStorePassword.toCharArray())
    KeyStore->>KeyStore: Load keystore from file
    KeyStore-->>KeyStoreUtil: Keystore loaded
    
    KeyStoreUtil->>FileInputStream: close()
    
    KeyStoreUtil->>KeyStore: getEntry(alias, PasswordProtection(keyPassword))
    KeyStore-->>KeyStoreUtil: PrivateKeyEntry
    
    KeyStoreUtil->>PrivateKeyEntry: getPrivateKey()
    PrivateKeyEntry-->>KeyStoreUtil: PrivateKey
    
    KeyStoreUtil->>KeyStoreUtil: Cast to RSAPrivateKey
    KeyStoreUtil-->>JwtProvider: RSAPrivateKey
    
    %% Public Key Loading Flow
    Note over JwtProvider,RSAPublicKey: Public Key Loading Flow
    
    JwtProvider->>KeyStoreUtil: loadPublicKeyFromKeyStore(keyStorePath, keyStorePassword, alias)
    
    KeyStoreUtil->>KeyStore: getInstance("JKS")
    KeyStore-->>KeyStoreUtil: keyStore instance
    
    KeyStoreUtil->>FileInputStream: new FileInputStream(keyStorePath)
    FileInputStream-->>KeyStoreUtil: fileInputStream
    
    KeyStoreUtil->>KeyStore: load(fileInputStream, keyStorePassword.toCharArray())
    KeyStore->>KeyStore: Load keystore from file
    KeyStore-->>KeyStoreUtil: Keystore loaded
    
    KeyStoreUtil->>FileInputStream: close()
    
    KeyStoreUtil->>KeyStore: getCertificate(alias)
    KeyStore-->>KeyStoreUtil: Certificate
    
    KeyStoreUtil->>Certificate: getPublicKey()
    Certificate-->>KeyStoreUtil: PublicKey
    
    KeyStoreUtil->>KeyStoreUtil: Cast to RSAPublicKey
    KeyStoreUtil-->>JwtProvider: RSAPublicKey
```

---

## 7. API Response Wrapper Flow

This flow shows how the **standardized API response** structure is used throughout the application.

```mermaid
sequenceDiagram
    %% Participants
    participant Controller as Controller Layer
    participant Service as Service Layer
    participant ApiResponse as ApiResponse<T>
    participant ResponseEntity as ResponseEntity
    
    %% Successful API Response Flow
    Note over Controller,ResponseEntity: Successful API Response Flow
    
    Controller->>Service: processRequest(request)
    Service->>Service: Business logic processing
    Service-->>Controller: result data
    
    Controller->>ApiResponse: new ApiResponse()
    ApiResponse-->>Controller: apiResponse instance
    
    Controller->>ApiResponse: setStatus(HttpStatus.OK.value())
    Controller->>ApiResponse: setMessage("Operation successful")
    Controller->>ApiResponse: setResult(result)
    Controller->>ApiResponse: setCount(totalCount) [Optional]
    Controller->>ApiResponse: setId(entityId) [Optional]
    Controller->>ApiResponse: setToken(jwtToken) [Optional]
    
    Controller->>ResponseEntity: ok(apiResponse)
    ResponseEntity-->>Controller: ResponseEntity<ApiResponse>
    
    Controller-->>Client: HTTP 200 OK with ApiResponse JSON
    
    %% Error API Response Flow
    Note over Controller,ResponseEntity: Error API Response Flow
    
    Controller->>Service: processRequest(request)
    Service->>Service: Business logic processing
    Service-->>Controller: Exception thrown
    
    Controller->>ApiResponse: new ApiResponse()
    ApiResponse-->>Controller: apiResponse instance
    
    Controller->>ApiResponse: setStatus(HttpStatus.BAD_REQUEST.value())
    Controller->>ApiResponse: setMessage("Error message")
    Controller->>ApiResponse: setResult(null)
    
    Controller->>ResponseEntity: badRequest(apiResponse)
    ResponseEntity-->>Controller: ResponseEntity<ApiResponse>
    
    Controller-->>Client: HTTP 400 Bad Request with ApiResponse JSON
```

---

## 8. Complete Utils Module Integration Flow

This flow shows how **multiple utilities work together** in a typical request processing scenario.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as Controller Layer
    participant Service as Service Layer
    participant SystemDateController as SystemDateController
    participant EncryptionUtil as EncryptionDecryptionUtil
    participant NumberGen as NumberGenerator
    participant ApiResponse as ApiResponse
    participant ExcelGen as ExcelCellGenerator
    participant KeyStoreUtil as JweKeyStoreUtil
    
    %% Complete Integration Flow
    Note over Client,KeyStoreUtil: Complete Utils Module Integration Flow
    
    Client->>Controller: POST /api/entity/create (with encrypted data)
    
    Controller->>EncryptionUtil: decrypt(encryptedData)
    EncryptionUtil-->>Controller: Decrypted plaintext
    
    Controller->>Service: createEntity(decryptedData)
    
    Service->>Service: Validate and process data
    Service->>Service: Save entity to database
    Service-->>Service: entityId
    
    Service->>NumberGen: generateEntityCode(entityId)
    NumberGen-->>Service: "CODE-" + entityId
    
    Service->>Service: Update entity with generated code
    
    Service->>SystemDateController: GET /api/getDate (internal call)
    SystemDateController->>SystemDateController: new Date()
    SystemDateController->>ApiResponse: Create response with date
    SystemDateController-->>Service: Current date
    
    Service->>Service: Set createdDate = currentDate
    
    Service->>KeyStoreUtil: loadPrivateKeyFromKeyStore(...)
    KeyStoreUtil-->>Service: RSAPrivateKey
    
    Service->>Service: Generate JWT token using private key
    
    Service-->>Controller: Entity created with code and date
    
    Controller->>ApiResponse: new ApiResponse()
    Controller->>ApiResponse: setStatus(200)
    Controller->>ApiResponse: setMessage("Entity created successfully")
    Controller->>ApiResponse: setResult(entity)
    Controller->>ApiResponse: setToken(jwtToken)
    
    Controller-->>Client: HTTP 200 OK with ApiResponse
    
    %% Excel Export Flow
    Note over Client,KeyStoreUtil: Excel Export Integration Flow
    
    Client->>Controller: GET /api/entity/export
    
    Controller->>Service: getAllEntities()
    Service-->>Controller: List of entities
    
    Controller->>ExcelGen: generateEntityReport(entityList)
    ExcelGen-->>Controller: ByteArrayInputStream (Excel file)
    
    Controller->>ApiResponse: new ApiResponse()
    Controller->>ApiResponse: setStatus(200)
    Controller->>ApiResponse: setMessage("Excel exported successfully")
    
    Controller-->>Client: HTTP 200 OK with Excel file attachment
```

---

## Summary

The Utils module provides essential cross-cutting utilities for the KUBOTA DMS application:

1. **SystemDateController**: REST endpoints for date retrieval
2. **EncryptionDecryptionUtil**: AES encryption/decryption with SHA-512 key derivation
3. **NumberGenerator**: Code generation for various entities (Employee, Scheme, Transport, Party, Branch, Item, Installation)
4. **ExcelCellGenerator**: Comprehensive Excel file generation for reports
5. **ExcelImportManager**: Excel file validation and header checking
6. **JweKeyStoreUtil**: RSA key loading from Java KeyStore for JWT operations
7. **ApiResponse**: Standardized response wrapper for all API endpoints
8. **Base64Util**: Base64 encoding/decoding utilities (currently commented out)
9. **Exception Classes**: Custom exception handling (ApiException, ApiRequestException, InvalidColumnException)

These utilities are used across all modules of the application, providing consistent functionality for encryption, code generation, Excel operations, and API responses.

