# Multi-Database CRUD University

# Overview
This project demonstrates how to perform CRUD (Create, Read, Update, Delete) operations on a simple university database using different database management systems (DBMS). The project includes implementations in:
- C++ with PostgreSQL
- Java with SQL Server
- Python with MySQL

# Database Structure
The database consists of three tables:
- **students** - Stores student records.
- **courses** - Stores available courses.
- **enrolled** - A join table managing student enrollments with grades.

# Implementations
Each implementation connects to a database and allows users to insert, read, update, and delete records interactively.
- C++ (PostgreSQL) - Uses libpqxx for database connectivity.
- Java (SQL Server) - Uses JDBC for database interaction.
- Python (MySQL) - Uses mysql-connector-python for database access.
