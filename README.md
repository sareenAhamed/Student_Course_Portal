# Student Course Portal -- Spring Boot Microservices

This project implements a **Student Course Portal** using **Spring Boot
Microservices Architecture**.\
Each feature (Students, Courses, Enrollments, Results, Notifications) is
built as an **independent microservice** with its own database, server
port, and API.

------------------------------------------------------------------------

## 🏗️ Microservices Architecture

The system contains **five** independent microservices:

  ------------------------------------------------------------------------
  Service Name                           Port       Description
  -------------------------------------- ---------- ----------------------
  **student-service**                    8081       Manages student data
                                                    (CRUD)

  **course-service**                     8082       Manages course data
                                                    (CRUD)

  **enrollment-service**                 8083       Enrolls students,
                                                    validates data, sends
                                                    notifications

  **result-service**                     8084       Manages student
                                                    results

  **notification-service**               8085       Prints enrollment
                                                    confirmations
  ------------------------------------------------------------------------

------------------------------------------------------------------------

## 🛠️ Technologies Used

-   Java 21\
-   Spring Boot\
-   Spring Web\
-   Spring Data JPA\
-   MySQL\
-   Maven\
-   RestTemplate

------------------------------------------------------------------------

## 🗂️ MySQL Databases Setup

Create five separate databases:

    CREATE DATABASE student_db;
    CREATE DATABASE course_db;
    CREATE DATABASE enrollment_db;
    CREATE DATABASE result_db;
    CREATE DATABASE notification_db;

------------------------------------------------------------------------

## ⚙️ Project Setup & Run Instructions

You must run **each microservice separately**.

------------------------------------------------------------------------

# 1️⃣ student-service (PORT 8081)

### Configure `application.properties`

    server.port=8081
    spring.datasource.url=jdbc:mysql://localhost:3306/student_db
    spring.datasource.username=root
    spring.datasource.password=StrongPassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

### Run the service

    cd student-service
    mvn spring-boot:run

### Endpoints

  Method   Endpoint         Description
  -------- ---------------- -------------------
  GET      /students        Get all students
  GET      /students/{id}   Get student by ID
  POST     /students        Create a student

------------------------------------------------------------------------

# 2️⃣ course-service (PORT 8082)

### Configure `application.properties`

    server.port=8082
    spring.datasource.url=jdbc:mysql://localhost:3306/course_db
    spring.datasource.username=root
    spring.datasource.password=StrongPassword
    spring.jpa.hibernate.ddl-auto=update

### Run

    cd course-service
    mvn spring-boot:run

### Endpoints

  Method   Endpoint        Description
  -------- --------------- ------------------
  GET      /courses        Get all courses
  GET      /courses/{id}   Get course by ID
  POST     /courses        Create a course

------------------------------------------------------------------------

# 3️⃣ notification-service (PORT 8085)

### Configure properties

    server.port=8085
    spring.datasource.url=jdbc:mysql://localhost:3306/notification_db
    spring.datasource.username=root
    spring.datasource.password=StrongPassword

### Run

    cd notification-service
    mvn spring-boot:run

### Endpoint

  Method   Endpoint             Description
  -------- -------------------- --------------------------
  POST     /notify/enrollment   Print enrollment message

------------------------------------------------------------------------

# 4️⃣ enrollment-service (PORT 8083)

### Configure properties

    server.port=8083
    spring.datasource.url=jdbc:mysql://localhost:3306/enrollment_db
    spring.datasource.username=root
    spring.datasource.password=StrongPassword
    spring.jpa.hibernate.ddl-auto=update

### Run

    cd enrollment-service
    mvn spring-boot:run

### Endpoints

  Method   Endpoint               Description
  -------- ---------------------- --------------------------
  POST     /enroll                Enroll student in course
  GET      /enroll/student/{id}   Get student enrollments

------------------------------------------------------------------------

# 5️⃣ result-service (PORT 8084)

### Configure properties

    server.port=8084
    spring.datasource.url=jdbc:mysql://localhost:3306/result_db
    spring.datasource.username=root
    spring.datasource.password=StrongPassword
    spring.jpa.hibernate.ddl-auto=update

### Run

    cd result-service
    mvn spring-boot:run

### Endpoints

  Method   Endpoint                Description
  -------- ----------------------- -------------------------
  POST     /results                Add a result
  GET      /results/student/{id}   Get results for student

------------------------------------------------------------------------

# 🧪 END-TO-END WORKFLOW

### 1. Create a student

    POST http://localhost:8081/students
    {
      "name": "Sareen",
      "email": "sareen@mail.com"
    }

### 2. Create a course

    POST http://localhost:8082/courses
    {
      "title": "Spring Boot",
      "description": "Microservices intro"
    }

### 3. Enroll the student

    POST http://localhost:8083/enroll
    {
      "studentId": 1,
      "courseId": 1
    }

➡️ **Expected output in notification-service console:**

    Student 1 enrolled into Course 1

### 4. Add result

    POST http://localhost:8084/results
    {
      "studentId": 1,
      "courseId": 1,
      "grade": "A"
    }

### 5. Fetch results

    GET http://localhost:8084/results/student/1

------------------------------------------------------------------------

# ✔️ Summary

This project demonstrates:

-   Microservice architecture\
-   MySQL integration\
-   Inter-service communication using RestTemplate\
-   CRUD operations\
-   Clean service separation

------------------------------------------------------------------------

# 👨‍💻 Author

**Sareen Ahamed**
