# 🔐 Auth Demo - Spring Boot JWT Authentication

A RESTful authentication service built with **Spring Boot**, **Spring Security**, **JWT**, **Spring Data JPA**, and **MySQL**. This project demonstrates secure user registration and login using JSON Web Tokens (JWT) and follows a layered architecture commonly used in production applications.

---

## 🚀 Features

* User Registration
* User Login
* BCrypt Password Hashing
* JWT Token Generation
* JWT Validation
* Role-based User Model
* Spring Security Configuration
* MySQL Database Integration
* DTO-based Request & Response Models
* Layered Architecture (Controller → Service → Repository)

---

## 🛠 Tech Stack

| Technology      | Purpose                        |
| --------------- | ------------------------------ |
| Java 21         | Programming Language           |
| Spring Boot     | Backend Framework              |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | ORM                            |
| Hibernate       | JPA Implementation             |
| MySQL           | Database                       |
| JWT (JJWT)      | Token-based Authentication     |
| Maven           | Dependency Management          |

---

## 📁 Project Structure

```
src/main/java
│
├── Configuration
│   └── SecurityConfig.java
│
├── Controller
│   └── UserController.java
│
├── DTO
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   └── UserResponse.java
│
├── Model
│   ├── User.java
│   └── Role.java
│
├── Repository
│   └── UserRepository.java
│
├── Service
│   ├── UserService.java
│   └── JWTService.java
│
└── AuthDemoApplication.java
```

---

## ⚙️ Configuration

Create an `application.properties` file inside:

```
src/main/resources/
```

Example:

```properties
spring.application.name=Auth-demo

# JWT Configuration
jwt.secret=REPLACE_WITH_YOUR_SECRET
jwt.expiration=3600000

# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/auth_demo
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🗄 Database

Create a MySQL database before running the application.

```sql
CREATE DATABASE auth_demo;
```

Hibernate will automatically create the required tables.

---

## ▶️ Running the Project

Clone the repository:

```bash
git clone <repository-url>
```

Navigate to the project:

```bash
cd Auth-demo
```

Run the application:

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

## 📌 API Endpoints

### Register User

**POST**

```
/api/users/signup
```

Request

```json
{
  "username": "apple",
  "email": "apple@gmail.com",
  "password": "password123"
}
```

---

### Login

**POST**

```
/api/users/login
```

Request

```json
{
  "email": "apple@gmail.com",
  "password": "password123"
}
```

Response

```json
{
  "userId": 1,
  "username": "apple",
  "email": "apple@gmail.com",
  "token": "JWT_TOKEN"
}
```

---

## 🔒 Authentication Flow

```
Client
   │
   ▼
Login Request
   │
   ▼
UserService
   │
   ▼
Verify Password (BCrypt)
   │
   ▼
Generate JWT
   │
   ▼
Return Token
   │
   ▼
Client stores JWT
   │
   ▼
Future requests include:

Authorization: Bearer <JWT>
```

---

## 🧩 JWT Payload

The generated JWT contains:

* Subject (User Email)
* User Role
* Issued At Time
* Expiration Time

---

## 📚 Concepts Practiced

* Spring Boot REST APIs
* Dependency Injection
* Spring Security Basics
* Password Encryption using BCrypt
* JWT Authentication
* DTO Pattern
* Repository Pattern
* Layered Architecture
* Exception Handling
* JPA & Hibernate
* MySQL Integration

---

## 🔮 Future Improvements

* JWT Authentication Filter
* UserDetailsService Integration
* Role-Based Authorization
* Refresh Tokens
* Global Exception Handling
* Request Validation
* Docker Support
* Unit & Integration Tests
* Swagger/OpenAPI Documentation
* Environment Variable Configuration

---

## 👨‍💻 Author

Built as part of my Spring Boot backend learning journey to understand secure authentication using JWT and Spring Security.
