# 🛠️ Church Management System Backend — Spring Boot + MySQL  

This is the **backend implementation** for the **Church Management System Android app**, built using **Spring Boot**, **Spring MVC**, and **MySQL**.  
It provides RESTful APIs for managing members, events, and attendance — enabling seamless integration with the Android frontend developed using **Kotlin + Jetpack Compose**.

---

## ⚙️ Tech Stack
- **Language:** Java (compatible with Kotlin)
- **Framework:** Spring Boot (Spring MVC)
- **Database:** MySQL
- **ORM:** JPA (Java Persistence API)
- **Library:** Lombok (to reduce boilerplate)
- **Testing Tool:** Postman
- **Build Tool:** Maven

---

## 🧠 Why Spring Boot
When planning the backend, I initially considered **Python FastAPI**, but ultimately chose **Spring Boot** because:

✅ **Mature and robust ecosystem** — Enterprise-ready, well-documented, and widely adopted.  
✅ **Seamless integration with Java and Kotlin** — Perfect synergy with the Kotlin frontend, all within the JVM ecosystem.  
✅ **Scalability and flexibility** — Ideal for production systems with built-in modules for security, validation, and data access.  
✅ **Reliability and community support** — Proven framework used across large-scale applications worldwide.  

This makes Spring Boot a **natural fit** alongside the Android app built in **Kotlin + Jetpack Compose**.



## ⚡ Features
- 👥 Member registration & profile management  
- 🗓️ Event creation and tracking  
- 📊 Attendance management  
- 🔐 Secure REST API endpoints  
- 🧩 Database interaction via JPA + MySQL  

---

## 🧩 API Endpoints (Examples)

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `api/v1/users/create` | Add a new church member |
| `GET` | `api/v1/users/all` | Get all members |
| `GET` | `api/v1/users/{email}` | Get member by email |
| `PUT` | `api/v1/users/update/{email}` | Update member details |
| `POST` | `api/v1/daily/create` | add daily prayer |
| `GET` | `api/v1/daily/all` | get all daily prayers |
| `PUT` | `api/v1/daily/{prayerId}` | update prayer by ID |
| `GET` | `api/v1/daily/{prayerId}` | get specific prayer by ID |
| `DELETE` | `api/v1/daily/{prayerId}` | delete prayer by ID |

> 💡 *All routes return JSON responses and can be tested using Postman.*

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the repository
```bash
git clone https://github.com/Shimmita/SpringBootBackendGospelMinistry.git
cd SpringBootBackendGospelMinistry
```

## ⚙️ Setup & Installation
spring.datasource.url=jdbc:mysql://localhost:3306/{db_name}?useSSL=true&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username={username}
spring.datasource.password={password}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.error.include-message:always

### Build and Run the Project
If using **Maven CLI**:
```bash
mvn spring-boot:run
```
If using **Maven Wrapper**:
```bash
./mvnw spring-boot:run

