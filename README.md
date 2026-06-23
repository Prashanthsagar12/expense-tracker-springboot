# Expense Tracker Backend

## Overview

Expense Tracker is a Spring Boot REST API application that helps users manage their personal expenses securely. The application provides JWT-based authentication, role-based authorization, expense analytics, budget management, and export functionality.

## Features

### Authentication & Security

* User Registration and Login
* JWT Authentication
* Password Encryption using BCrypt
* Role-Based Access Control (ADMIN and USER)

### Expense Management

* Add Expense
* Update Expense
* Delete Expense
* View Expenses
* User-specific expense access

### Search & Filtering

* Search expenses by title
* Filter expenses by category
* Filter expenses by date
* Filter expenses by amount range

### Analytics Dashboard

* Total Expenses
* Highest Expense
* Lowest Expense
* Average Expense
* Daily Expense Summary
* Monthly Expense Summary

### Budget Management

* Set Monthly Budget
* Update Monthly Budget
* Budget vs Expense Dashboard
* Remaining Budget Calculation
* Percentage Budget Utilization

### Additional Features

* Pagination
* Sorting
* Global Exception Handling
* Excel Export of Expenses
* Admin APIs

## Tech Stack

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT
* MySQL
* Maven
* Apache POI
* Hibernate

## Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── DTO
├── security
├── exception
├── config
```

## API Endpoints

### Authentication

| Method | Endpoint       | Description       |
| ------ | -------------- | ----------------- |
| POST   | /auth/register | Register new user |
| POST   | /auth/login    | Login user        |

### Expenses

| Method | Endpoint       |
| ------ | -------------- |
| POST   | /expenses      |
| GET    | /expenses      |
| PUT    | /expenses/{id} |
| DELETE | /expenses/{id} |

### Dashboard

| Method | Endpoint                  |
| ------ | ------------------------- |
| GET    | /expenses/dashboard       |
| GET    | /expenses/daily-summary   |
| GET    | /expenses/monthly-summary |

### Budget

| Method | Endpoint          |
| ------ | ----------------- |
| POST   | /budget           |
| GET    | /budget/dashboard |

### Admin

| Method | Endpoint     |
| ------ | ------------ |
| GET    | /admin/users |

## Security

* JWT-based authentication secures all protected endpoints.
* Users can access only their own expenses and budgets.
* ADMIN users can access administrative endpoints.

## Setup Instructions

### Clone Repository

```bash
git clone <repository-url>
```

### Configure Database

Update `application.properties`:

```properties
spring.datasource.url=your_database_url
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run Application

```bash
mvn clean install
mvn spring-boot:run
```

Application runs on:

```text
http://localhost:8080
```

## Future Enhancements

* PDF Export
* Email Notifications
* Docker Deployment
* Cloud Deployment
* Category-wise Budgets
* Frontend Integration

Chinthala Prashanth Sagar
