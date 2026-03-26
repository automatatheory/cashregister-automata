# Cash Register

## Description
A lightweight desktop Point-of-Sale (POS) application built with Java Swing, providing a high-speed interface for retail transactions. This application features product selection, payment method selection, automated tax and total calculataions, digital receipt generation, and transaction records storage.

## Tech Stack

### Frontend
- RJava Swing

### Backend & Database
- Java SE
- SQL
- JDBC
- MySQL

## Other Tools
- Maven

## Installation

### 1. Prerequisites
Ensure you have JDK 17 or higher installed. Install MySQL and ensure that the service is running. Set Maven for dependency management.

### 2. Clone the repository

### 3. Database Setup
Open your SQL and create a new database and intializing the tables by running the provided query in "cashregister/database/databaseseed.sql".

### 4. Configure Connection
Navigate tp "cashregister/src/DBConnection.java" and update URL, USER, and PASSWORD to match your local SQL credentials.

### 5. Build the project.
