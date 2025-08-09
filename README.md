### README.md

# 📚 Quiz App Using Spring Boot

A **web-based quiz application** built with **Spring Boot**. It provides REST APIs to manage questions, create quizzes, and calculate scores.
The application uses **MySQL** for data persistence and **Spring Data JPA** for ORM.

---

## 📂 Project Structure

```
com.saqib.quizeapp
│
├── QuizAppApplication.java          # Main Spring Boot application class
│
├── controller/                      # Handles HTTP requests
│   ├── QuestionController.java      # APIs for fetching & adding questions
│   └── QuizController.java          # APIs for creating quizzes & scoring
│
├── dao/                             # Data access layer (JPA Repositories)
│   ├── QuestionDao.java             # Queries for Question entity
│   └── QuizDao.java                 # Queries for Quiz entity
│
├── model/                           # Data models (Entities & DTOs)
│   ├── Question.java                # Question entity
│   ├── QuestionWrapper.java         # DTO without correct answer
│   ├── Quiz.java                    # Quiz entity
│   └── Response.java                # User's answer model
│
└── service/                         # Business logic layer
    ├── QuestionService.java         # Logic for question handling
    └── QuizService.java             # Logic for quiz creation & scoring
```

---

## 🛠 Technology Stack

* **Framework**: Spring Boot
* **Language**: Java 21
* **Database**: MySQL
* **ORM**: Spring Data JPA
* **Build Tool**: Apache Maven

### 📦 Dependencies

* `spring-boot-starter-web` – Build REST APIs
* `spring-boot-starter-data-jpa` – JPA repository support
* `mysql-connector-j` – MySQL database driver
* `lombok` – Reduce boilerplate code

---

## 🚀 Getting Started

### 1️⃣ Database Setup

* Create a MySQL database (e.g., `quizdb`).
* Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 2️⃣ Build the Project

```sh
./mvnw clean install
```

### 3️⃣ Run the Application

```sh
java -jar target/quizapp-0.0.1-SNAPSHOT.jar
```

The app runs on **[http://localhost:8080](http://localhost:8080)** by default.

---

## 📌 API Endpoints Overview

| Method | Endpoint                                           | Description                |
| ------ | -------------------------------------------------- | -------------------------- |
| GET    | `/questions/all`                                   | Get all questions          |
| GET    | `/questions/category/{category}`                   | Get questions by category  |
| POST   | `/questions/add`                                   | Add a new question         |
| POST   | `/quiz/create?category=Java&numQ=5&title=JavaQuiz` | Create a quiz              |
| GET    | `/quiz/get/{quizId}`                               | Get quiz questions         |
| POST   | `/quiz/submit/{quizId}`                            | Submit answers & get score |

---


## 🤝 Contribution

Feel free to fork the repo, open issues, or submit PRs.
