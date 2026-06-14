# Task Manager API

A backend REST API built with Spring Boot for managing tasks and categories. This project focuses on implementing core backend concepts including secure authentication, data validation, and clean architectural patterns.

## Features

* **JWT Authentication:** Secure API endpoints requiring valid JSON Web Tokens for access.
* **Layered Architecture:** Clear separation of concerns utilizing Controllers, Services, and Repositories via dependency injection.
* **Data Transfer Objects (DTOs):** Structured data payloads to separate database entities from client-facing data.
* **Global Exception Handling:** Centralized error handling to ensure consistent, readable JSON error responses for the client.
* **Category Management:** Endpoints to group and organize tasks efficiently.

## Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Security:** Spring Security, JWT
* **Build Tool:** Maven
* **Version Control:** Git

## Getting Started

### Prerequisites
* Java Development Kit (JDK) 17 or higher
* Maven installed
* Your preferred IDE (IntelliJ IDEA recommended)

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/BadrinathRudroju/task-manager.git] (https://github.com/BadrinathRudroju/task-manager.git)
   cd task-manager
