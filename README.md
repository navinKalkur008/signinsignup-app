# 🔐 JWT Authentication System (Spring Boot)

A production-style authentication system built using **Spring Boot, Spring Security, Hibernate (JPA), and JWT**.
This project demonstrates secure user registration and login with **token-based authentication**.

---

## 🚀 Features

* ✅ User Signup (Registration)
* ✅ User Login (Authentication)
* 🔐 Password Encryption using **BCrypt**
* 🛡️ JWT Token Generation & Validation
* ⚠️ Global Exception Handling
* ✅ Input Validation using annotations
* 🔒 Secured APIs using Spring Security
* ⚡ Stateless Authentication (No sessions)

---

## 🧠 Tech Stack

* Java 17 / 21
* Spring Boot
* Spring Security
* Hibernate (JPA)
* MySQL
* JWT (JSON Web Token)
* Maven

---

## 🏗️ Project Architecture

```
Controller → Service → Repository → Database
```

* **Controller** → Handles HTTP requests
* **Service** → Business logic
* **Repository** → Database interaction
* **Entity** → Table mapping

---

## 🔄 Authentication Flow

### 🟢 Signup

1. User registers with email & password
2. Password is encrypted using BCrypt
3. User data stored in database

---

### 🔵 Login

1. User provides email & password
2. Password is validated
3. JWT token is generated and returned

---

### 🟣 Access Protected APIs

1. Client sends JWT token in header
2. JWT Filter validates token
3. User is authenticated using SecurityContext
4. Access granted to protected endpoints

---

## 🔐 JWT Flow

```
Login → Generate Token → Client Stores Token
        ↓
Request → JWT Filter → Validate Token
        ↓
SecurityContext → Authorized Access
```

---

## 🌐 API Endpoints

### 🔹 Signup

```
POST /api/auth/signup
```

### 🔹 Login

```
POST /api/auth/login
```

### 🔹 Protected API Example

```
GET /api/test
```

---

## 🧪 Sample Request

### Login Request

```json
{
  "email": "user@example.com",
  "password": "12345"
}
```

### Login Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## 🔒 Security Highlights

* Passwords are **never stored in plain text**
* JWT ensures **stateless authentication**
* Custom filter validates every request
* SecurityContext stores authenticated user

---

## ⚠️ Exception Handling

Handled using:

```
@RestControllerAdvice
```

Custom Exceptions:

* UserAlreadyExistsException
* UserNotFoundException
* InvalidPasswordException

---

## 🛠️ How to Run

1. Clone the repository
2. Configure database in `application.properties`
3. Run the application

```bash
mvn spring-boot:run
```

---

## 📌 Future Enhancements

* 🔐 Role-Based Authorization (Admin/User)
* 🔁 Refresh Token Mechanism
* 🌐 Frontend Integration (React)
* 📦 Docker Deployment

---

## 👨‍💻 Author

**Navin Kalkur**

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
