# Question 5: Task Management API

## Project Description
A RESTful API for task/to-do list management built with Spring Boot.

## Project Structure
```
src/main/java/com/task/question5taskapi/
├── controller/
│   └── task/
│       └── TaskController.java
├── model/
│   └── task/
│       └── Task.java
└── Question5TaskApiApplication.java
```

1. The application will start on `http://localhost:8080`

## API Endpoints

### 1. Get All Tasks
- **URL:** `/api/tasks`
- **Method:** `GET`
- **Status Code:** `200 OK`
- **Description:** Retrieves all tasks

**Sample Response:**
```json
[
  {
    "taskId": 1,
    "title": "Complete Spring Boot Assignment",
    "description": "Finish all 5 REST API questions",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-10"
  },
  {
    "taskId": 2,
    "title": "Review Java Concepts",
    "description": "Study OOP principles and collections",
    "completed": false,
    "priority": "MEDIUM",
    "dueDate": "2026-02-12"
  }
]
```

---

### 2. Get Task by ID
- **URL:** `/api/tasks/{taskId}`
- **Method:** `GET`
- **Status Codes:** 
  - `200 OK` - Task found
  - `404 NOT FOUND` - Task not found

**Sample Request:**
```
GET http://localhost:8080/api/tasks/1
```

**Sample Response (200 OK):**
```json
{
  "taskId": 1,
  "title": "Complete Spring Boot Assignment",
  "description": "Finish all 5 REST API questions",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-10"
}
```

---

### 3. Get Tasks by Completion Status
- **URL:** `/api/tasks/status?completed={true/false}`
- **Method:** `GET`
- **Status Code:** `200 OK`
- **Description:** Filters tasks by completion status

**Sample Request (Completed Tasks):**
```
GET http://localhost:8080/api/tasks/status?completed=true
```

**Sample Response:**
```json
[
  {
    "taskId": 3,
    "title": "Setup Development Environment",
    "description": "Install VS Code, Postman, and Java",
    "completed": true,
    "priority": "HIGH",
    "dueDate": "2026-02-05"
  },
  {
    "taskId": 6,
    "title": "Practice REST API Testing",
    "description": "Test all endpoints using Postman",
    "completed": true,
    "priority": "MEDIUM",
    "dueDate": "2026-02-08"
  }
]
```

**Sample Request (Incomplete Tasks):**
```
GET http://localhost:8080/api/tasks/status?completed=false
```

---

### 4. Get Tasks by Priority
- **URL:** `/api/tasks/priority/{priority}`
- **Method:** `GET`
- **Status Code:** `200 OK`
- **Description:** Retrieves tasks by priority level
- **Valid Priorities:** LOW, MEDIUM, HIGH

**Sample Request:**
```
GET http://localhost:8080/api/tasks/priority/HIGH
```

**Sample Response:**
```json
[
  {
    "taskId": 1,
    "title": "Complete Spring Boot Assignment",
    "description": "Finish all 5 REST API questions",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-10"
  },
  {
    "taskId": 3,
    "title": "Setup Development Environment",
    "description": "Install VS Code, Postman, and Java",
    "completed": true,
    "priority": "HIGH",
    "dueDate": "2026-02-05"
  }
]
```

---

### 5. Create New Task
- **URL:** `/api/tasks`
- **Method:** `POST`
- **Status Code:** `201 CREATED`
- **Content-Type:** `application/json`

**Sample Request:**
```json
POST http://localhost:8080/api/tasks
Content-Type: application/json

{
  "title": "Prepare for Exam",
  "description": "Study chapters 1-5 for final exam",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-18"
}
```

**Sample Response (201 CREATED):**
```json
{
  "taskId": 7,
  "title": "Prepare for Exam",
  "description": "Study chapters 1-5 for final exam",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-18"
}
```

---

### 6. Update Task (Full Update)
- **URL:** `/api/tasks/{taskId}`
- **Method:** `PUT`
- **Status Codes:**
  - `200 OK` - Successfully updated
  - `404 NOT FOUND` - Task not found
- **Content-Type:** `application/json`

**Sample Request:**
```json
PUT http://localhost:8080/api/tasks/1
Content-Type: application/json

{
  "title": "Complete Spring Boot Assignment - UPDATED",
  "description": "Finish all 5 REST API questions and submit",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-09"
}
```

**Sample Response (200 OK):**
```json
{
  "taskId": 1,
  "title": "Complete Spring Boot Assignment - UPDATED",
  "description": "Finish all 5 REST API questions and submit",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-09"
}
```

---

### 7. Mark Task as Completed
- **URL:** `/api/tasks/{taskId}/complete`
- **Method:** `PATCH`
- **Status Codes:**
  - `200 OK` - Successfully marked as completed
  - `404 NOT FOUND` - Task not found
- **Description:** Sets the completed field to true

**Sample Request:**
```
PATCH http://localhost:8080/api/tasks/1/complete
```

**Sample Response (200 OK):**
```json
{
  "taskId": 1,
  "title": "Complete Spring Boot Assignment",
  "description": "Finish all 5 REST API questions",
  "completed": true,
  "priority": "HIGH",
  "dueDate": "2026-02-10"
}
```

**Note:** Only the `completed` field changes to `true`, all other fields remain the same

---

### 8. Delete Task
- **URL:** `/api/tasks/{taskId}`
- **Method:** `DELETE`
- **Status Codes:**
  - `204 NO CONTENT` - Successfully deleted
  - `404 NOT FOUND` - Task not found

**Sample Request:**
```
DELETE http://localhost:8080/api/tasks/5
```

**Sample Response (204 NO CONTENT):**
```
Empty body
```

---

## Sample Task Data

The application initializes with 6 sample tasks:

1. **Complete Spring Boot Assignment** - HIGH priority - Due: 2026-02-10 - Incomplete
2. **Review Java Concepts** - MEDIUM priority - Due: 2026-02-12 - Incomplete
3. **Setup Development Environment** - HIGH priority - Due: 2026-02-05 - ✅ Completed
4. **Learn Git Commands** - MEDIUM priority - Due: 2026-02-15 - Incomplete
5. **Read Spring Documentation** - LOW priority - Due: 2026-02-20 - Incomplete
6. **Practice REST API Testing** - MEDIUM priority - Due: 2026-02-08 - ✅ Completed

---

## Key Features

### Priority Levels
- **HIGH** - Urgent tasks that need immediate attention
- **MEDIUM** - Important but not urgent tasks
- **LOW** - Tasks that can be done later

### Completion Status
- `true` - Task is completed ✅
- `false` - Task is still pending ⏳

### Date Format
- All dates use the format: **YYYY-MM-DD**
- Example: `"2026-02-10"` for February 10, 2026

### PATCH vs PUT
- **PUT** `/api/tasks/{id}` - Full update (all fields required)
- **PATCH** `/api/tasks/{id}/complete` - Partial update (only marks as completed)

---

## HTTP Status Codes Used

| Code | Meaning | Used For |
|------|---------|----------|
| **200 OK** | Success | GET, PUT, and PATCH requests |
| **201 CREATED** | Resource created | POST (create task) |
| **204 NO CONTENT** | Success, no body | DELETE (remove task) |
| **404 NOT FOUND** | Resource not found | GET/PUT/PATCH/DELETE when task doesn't exist |

---

## Testing Guide

### Test Filtering
```
GET /api/tasks/status?completed=true     // Completed tasks only
GET /api/tasks/status?completed=false    // Incomplete tasks only
GET /api/tasks/priority/HIGH             // High priority tasks
GET /api/tasks/priority/MEDIUM           // Medium priority tasks
GET /api/tasks/priority/LOW              // Low priority tasks
```

### Test CRUD Operations
```
POST /api/tasks                          // Create
GET /api/tasks/1                         // Read
PUT /api/tasks/1                         // Update (full)
PATCH /api/tasks/1/complete              // Update (mark complete)
DELETE /api/tasks/1                      // Delete
```

---

## Common Use Cases

### View All Pending Tasks
```
GET /api/tasks/status?completed=false
```

### View All High Priority Tasks
```
GET /api/tasks/priority/HIGH
```

### Mark Task as Done
```
PATCH /api/tasks/1/complete
```

### Create Urgent Task
```json
POST /api/tasks
{
  "title": "Fix Critical Bug",
  "description": "Application crashes on startup",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-08"
}
```
SCREENSHOTS FOR THE OUTPUTS 

<img width="1793" height="686" alt="Screenshot 2026-02-09 103944" src="https://github.com/user-attachments/assets/1c9790b4-be1d-4843-a3cb-de73763a64e2" />

---
<img width="1793" height="573" alt="Screenshot 2026-02-09 104017" src="https://github.com/user-attachments/assets/e096c235-3c4f-4076-8460-e0aa92bdd9be" />

---
<img width="1802" height="684" alt="Screenshot 2026-02-09 104045" src="https://github.com/user-attachments/assets/b90850ec-7789-42c3-b0c7-9bb24e11ec86" />

---
<img width="1797" height="581" alt="Screenshot 2026-02-09 104439" src="https://github.com/user-attachments/assets/88e80676-761a-489c-84d7-1697c0dc01b2" />

---
<img width="1802" height="600" alt="Screenshot 2026-02-09 105004" src="https://github.com/user-attachments/assets/c872c1a7-dea0-4aaa-b5f0-610da7013348" />

---
<img width="1800" height="725" alt="Screenshot 2026-02-09 105029" src="https://github.com/user-attachments/assets/f842f991-37fd-4313-ac97-1e9181926a33" />

---
<img width="1810" height="699" alt="Screenshot 2026-02-09 105649" src="https://github.com/user-attachments/assets/62e2b411-110e-42ac-be5c-ef173ab48605" />

---
<img width="1797" height="649" alt="Screenshot 2026-02-09 110549" src="https://github.com/user-attachments/assets/07b0f54c-28f2-47cf-ac71-c9710cac604e" />

---
<img width="1794" height="541" alt="Screenshot 2026-02-09 131336" src="https://github.com/user-attachments/assets/3b8dbe69-1433-486b-8961-9ce553ee0b10" />

---

## Author
SHEDRICK BUCAGU ELISA  
26939

## Date
February 7, 2026
