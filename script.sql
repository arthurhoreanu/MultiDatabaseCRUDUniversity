-- Create the database
CREATE DATABASE university;

-- Create the students table
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,  -- Auto-increment for portability
    name VARCHAR(100) NOT NULL
);

-- Create the courses table
CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,  -- Auto-increment for portability
    name VARCHAR(100) NOT NULL
);

-- Create the enrolled table (many-to-many relationship)
CREATE TABLE enrolled (
    student_id INT,
    course_id INT,
    grade INT CHECK (grade BETWEEN 1 AND 10),
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);
