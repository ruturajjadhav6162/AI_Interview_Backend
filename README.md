
## 📦 Job Service

This microservice is responsible for handling job-related operations. It is part of a microservice architecture and communicates with the Company Service and Interview Service via JWT authentication.It uses a JWT issued by the **Company Service** containing the `companyId` for authentication.

---

### 🔗 Endpoints

#### 1. Create a Job

- **Method:** `POST`
- **URL:** `/job_service/create`
- **Description:** Create a new job for the company.
- **Request Body:** `JobServiceInput`

```json
{
  "jobTitle": "Senior Software Engineer",
  "department": "Engineering",
  "location": "New York, NY",
  "jobType": "Full-time",
  "experienceRequired": "5+ years",
  "salaryRange": "$100,000 - $150,000",
  "teamSize": "10",
  "tags": ["Java", "Spring Boot", "AWS"],
  "remoteWorkAvailable": true,
  "urgentHiring": false,
  "publishImmediately": true,
  "jobDescription": "Develop and maintain web applications.",
  "responsibilities": "Write clean code, conduct code reviews, collaborate with product team.",
  "requirements": "Proficient in Java, experience with microservices, knowledge of cloud platforms.",
  "benefits": "Health insurance, 401k, flexible hours.",
  "interviewProcess": "Phone screening, technical interview, HR interview.",
  "applicationDeadline": "2025-12-31"
}

```

**Authorization Header:**
```makefile
Authorization: Bearer <JWT_with_companyId>
```

**Response: JobResponse**
```json
{
  "jobTitle": "Senior Software Engineer",
  "jobDescription": "Develop and maintain web applications.",
  "jobResponseMessage": "Job Requirement Posted Successfully"
}
```

---

#### 2. Get Job Count

- **Method:** `GET`
- **URL:** `/job_service/getcount`
- **Description:** Get number of jobs posted by the authenticated company.

**Authorization Header:**
```makefile
Authorization: Bearer <JWT_with_companyId>
```

**Response:**
```json
{
  "topJobs": [
    {
      "jobId": 1,
      "jobTitle": "Senior Software Engineer",
      "department": "Engineering",
      "location": "New York, NY",
      "jobType": "Full-time",
      "experienceRequired": "5+ years",
      "salaryRange": "$100,000 - $150,000",
      "applicationDeadline": "2025-12-31",
      "createdAt": "2025-06-22",
      "status": "ACTIVE",
      "teamSize": "10",
      "tags": [
        "Java",
        "Spring Boot",
        "AWS"
      ],
      "remoteWorkAvailable": true,
      "urgentHiring": false,
      "publishImmediately": true,
      "jobDescription": "Develop and maintain web applications.",
      "responsibilities": "Write clean code, conduct code reviews, collaborate with product team.",
      "requirements": "Proficient in Java, experience with microservices, knowledge of cloud platforms.",
      "benefits": "Health insurance, 401k, flexible hours.",
      "interviewProcess": "Phone screening, technical interview, HR interview.",
      "companyId": 1
    }
  ],
  "jobCount": 1,
  "jobServiceList": [
    {
      "jobId": 1,
      "jobTitle": "Senior Software Engineer",
      "department": "Engineering",
      "location": "New York, NY",
      "jobType": "Full-time",
      "experienceRequired": "5+ years",
      "salaryRange": "$100,000 - $150,000",
      "applicationDeadline": "2025-12-31",
      "createdAt": "2025-06-22",
      "status": "ACTIVE",
      "teamSize": "10",
      "tags": [
        "Java",
        "Spring Boot",
        "AWS"
      ],
      "remoteWorkAvailable": true,
      "urgentHiring": false,
      "publishImmediately": true,
      "jobDescription": "Develop and maintain web applications.",
      "responsibilities": "Write clean code, conduct code reviews, collaborate with product team.",
      "requirements": "Proficient in Java, experience with microservices, knowledge of cloud platforms.",
      "benefits": "Health insurance, 401k, flexible hours.",
      "interviewProcess": "Phone screening, technical interview, HR interview.",
      "companyId": 1
    }
  ]
}
```

---

#### 3. Get All Jobs

- **Method:** `GET`
- **URL:** `/job_service/getJobs`
- **Description:** Returns a list of all job postings.

**Response:**
```json
[
  {
    "jobId": 1,
    "jobTitle": "Senior Software Engineer",
    "department": "Engineering",
    "location": "New York, NY",
    "jobType": "Full-time",
    "experienceRequired": "5+ years",
    "salaryRange": "$100,000 - $150,000",
    "applicationDeadline": "2025-12-31",
    "createdAt": "2025-06-22",
    "status": "ACTIVE",
    "teamSize": "10",
    "tags": [
      "Java",
      "Spring Boot",
      "AWS"
    ],
    "remoteWorkAvailable": true,
    "urgentHiring": false,
    "publishImmediately": true,
    "jobDescription": "Develop and maintain web applications.",
    "responsibilities": "Write clean code, conduct code reviews, collaborate with product team.",
    "requirements": "Proficient in Java, experience with microservices, knowledge of cloud platforms.",
    "benefits": "Health insurance, 401k, flexible hours.",
    "interviewProcess": "Phone screening, technical interview, HR interview.",
    "companyId": 1
  }
]
```

---

#### 4. Get Job By ID

- **Method:** `GET`
- **URL:** `/job_service/getJobById/{id}`
- **Description:** Returns job details by ID.

---

### 🔐 JWT Token Usage (Job Service)

| Endpoint                   | Requires JWT | Token Provided By | Token Contains |
|---------------------------|--------------|--------------------|----------------|
| /job_service/create       | ✅ Yes       | Company Service    | companyId      |
| /job_service/getcount     | ✅ Yes       | Company Service    | companyId      |
| /job_service/getJobs      | ❌ No        | -                  | -              |
| /job_service/getJobById   | ❌ No        | -                  | -              |
