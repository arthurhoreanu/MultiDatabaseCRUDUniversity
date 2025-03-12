#include <iostream>
#include <pqxx/pqxx> // C++ library for PostgreSQL

int main() {
    try {
        // PostgreSQL database connection
        pqxx::connection conn("dbname=dbname user=user password=password host=host port=port");
        if (conn.is_open()) {
            std::cout << "✅ Connected to the database: " << conn.dbname() << std::endl;
        } else {
            std::cerr << "❌ Error connecting to the database!" << std::endl;
            return 1;
        }

        int id;
        std::string name;
        int student_id, course_id, grade;

        // CREATE operation: Insert a new student
        std::cout << "Enter student ID: ";
        std::cin >> id;
        std::cin.ignore();
        std::cout << "Enter student name: ";
        std::getline(std::cin, name);
        pqxx::work txn_create(conn);
        txn_create.exec_params("INSERT INTO students (id, name) VALUES ($1, $2);", id, name);
        txn_create.commit();
        std::cout << "🎉 Student '" << name << "' has been added successfully!" << std::endl;

        // READ operation: Retrieve a student
        std::cout << "Enter student ID to retrieve: ";
        std::cin >> id;
        pqxx::nontransaction txn_read(conn);
        pqxx::result result = txn_read.exec_params("SELECT * FROM students WHERE id = $1;", id);
        for (auto row : result) {
            std::cout << "📖 Student ID: " << row["id"].as<int>()
                      << ", Name: " << row["name"].as<std::string>() << std::endl;
        }

        // UPDATE operation: Change grade in enrolled table for a student
        std::cout << "Enter student ID to update grade: ";
        std::cin >> student_id;
        std::cout << "Enter course ID: ";
        std::cin >> course_id;
        std::cout << "Enter new grade: ";
        std::cin >> grade;
        pqxx::work txn_update(conn);
        txn_update.exec_params("UPDATE enrolled SET grade = $1 WHERE student_id = $2 AND course_id = $3;", grade, student_id, course_id);
        txn_update.commit();
        std::cout << "🔄 Student with ID " << student_id << " has received an updated grade!" << std::endl;

        // DELETE operation: Remove a student
        std::cout << "Enter student ID to delete: ";
        std::cin >> id;
        pqxx::work txn_delete(conn);
        txn_delete.exec_params("DELETE FROM students WHERE id = $1;", id);
        txn_delete.commit();
        std::cout << "🗑️ Student with ID " << id << " has been removed from the database!" << std::endl;

    } catch (const std::exception &e) {
        std::cerr << "❌ Error: " << e.what() << std::endl;
        return 1;
    }

    return 0;
}