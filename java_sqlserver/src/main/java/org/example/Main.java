package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        if (URL == null || USER == null || PASSWORD == null) {
            System.err.println("❌ Database credentials are not set. Please configure environment variables.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("✅ Connected to the database!");

            int id;
            String name;
            int studentId, courseId, grade;

            // CREATE operation: Insert a new student
            System.out.print("Enter student ID: ");
            id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter student name: ");
            name = scanner.nextLine();
            String insertQuery = "INSERT INTO students (id, name) VALUES (?, ?);";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.executeUpdate();
                System.out.println("🎉 Student '" + name + "' has been added successfully!");
            }

            // READ operation: Retrieve a student by ID
            System.out.print("Enter student ID to retrieve: ");
            id = scanner.nextInt();
            String selectQuery = "SELECT * FROM students WHERE id = ?;";
            try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println("📖 Student ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
                }
            }

            // UPDATE operation: Change grade in enrolled table for a student
            System.out.print("Enter student ID to update grade: ");
            studentId = scanner.nextInt();
            System.out.print("Enter course ID: ");
            courseId = scanner.nextInt();
            System.out.print("Enter new grade: ");
            grade = scanner.nextInt();
            String updateQuery = "UPDATE enrolled SET grade = ? WHERE student_id = ? AND course_id = ?;";
            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                pstmt.setInt(1, grade);
                pstmt.setInt(2, studentId);
                pstmt.setInt(3, courseId);
                pstmt.executeUpdate();
                System.out.println("🔄 Student's grade has been updated!");
            }

            // DELETE operation: Remove a student
            System.out.print("Enter student ID to delete: ");
            id = scanner.nextInt();
            String deleteQuery = "DELETE FROM students WHERE id = ?;";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("🗑️ Student with ID " + id + " has been removed from the database!");
            }

        } catch (SQLException e) {
            System.err.println("❌ Database error: " + e.getMessage());
        }
    }
}