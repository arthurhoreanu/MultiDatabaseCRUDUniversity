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
```sql
-- Creating the database
CREATE DATABASE university;

-- Creating the students table
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

-- Creating the courses table
CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

-- Creating the enrolled table
CREATE TABLE enrolled (
    student_id INT REFERENCES students(id) ON DELETE CASCADE,
    course_id INT REFERENCES courses(id) ON DELETE CASCADE,
    grade INT CHECK (grade BETWEEN 1 AND 10),
    PRIMARY KEY (student_id, course_id)
);
```

# Implementations
Each implementation connects to a database and allows users to insert, read, update, and delete records interactively.
- C++ (PostgreSQL) - Uses libpqxx for database connectivity.
- Java (SQL Server) - Uses JDBC for database interaction.
- Python (MySQL) - Uses mysql-connector-python for database access.
