
# 🏢 Company Service

This service manages company and company professional information, and issues JWT tokens to other microservices (like Job Service) for secure communication.

---

## 🔐 JWT Token Endpoints

### 1. Get Token from Company Service

**URL:** `/jwt/token`  
**Method:** `GET`  
**Description:** Accepts a user JWT, extracts the username, finds associated companyId, and returns a new token with `companyId` claim.

**Authorization Header:**
```
Authorization: Bearer <User_JWT>
```

**Response:**
```
<JWT Token with companyId>
```

### 2. View Token Details

**URL:** `/jwt/tokenDetails`  
**Method:** `GET`  
**Authorization Header:**
```
Authorization: Bearer <JWT>
```

**Response:**  
A string containing token source, username, companyId, and role.

---

## 🧑‍💼 CompanyProfessional Controller

### 1. Create Professional

**URL:** `/companyProfessional/createProfessional`  
**Method:** `POST`  
**Body:** `CompanyProfessionalDetailsInput`  
**Response:** `CompanyProfessionalCreationResponse`

### 2. Get Professional Details

**URL:** `/companyProfessional/details/{username}`  
**Method:** `GET`  
**Response:** `CompanyProfessionalAndCompany`

### 3. Get All Professionals

**URL:** `/companyProfessional/getAllProfessional`  
**Method:** `GET`  
**Response:** List of `CompanyProfessionalAndCompany`

---

## 🏢 Company Controller

### 1. Create Company

**URL:** `/company/createCompany`  
**Method:** `POST`  
**Body:** `CompanyServiceInput`  
**Response:** `CompanyCreationResponse`

### 2. Get Company Details

**URL:** `/company/companyDetails/{id}`  
**Method:** `GET`  
**Response:** `CompanyDetailsGet`

### 3. Get All Company Names

**URL:** `/company/allCompanyNames`  
**Method:** `GET`  
**Response:** List of Strings (Company Names)

---

## 🔁 JWT Token Flow Summary

1. User logs in and receives a user-level token (Token A).
2. The frontend sends Token A to `/jwt/token`.
3. Company Service extracts the username, maps to a company, and issues Token B containing the `companyId`.
4. Token B is used to authorize requests in Job Service and other services.

