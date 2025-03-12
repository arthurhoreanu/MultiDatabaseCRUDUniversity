import mysql.connector

# Connection configuration
DB_CONFIG = {
    "host": "host",
    "user": "user",
    "password": "password",
    "database": "database"
}

try:
    # Credential validation
    if None in DB_CONFIG.values():
        raise ValueError("❌ Missing database credentials. Please set environment variables.")

    # MySQL Database Connection
    conn = mysql.connector.connect(**DB_CONFIG)
    cursor = conn.cursor()
    print("✅ Connected to the database!")

    # CREATE operation: Insert a new student
    id = int(input("Enter student ID: "))
    name = input("Enter student name: ")
    insert_query = "INSERT INTO students (id, name) VALUES (%s, %s);"
    cursor.execute(insert_query, (id, name))
    conn.commit()
    print(f"🎉 Student '{name}' has been added successfully!")

    # READ operation: Retrieve a student by ID
    id = int(input("Enter student ID to retrieve: "))
    select_query = "SELECT id, name FROM students WHERE id = %s;"
    cursor.execute(select_query, (id,))
    for student in cursor.fetchall():
        print(f"📖 Student ID: {student[0]}, Name: {student[1]}")

    # UPDATE operation: Update a student's grade
    student_id = int(input("Enter student ID to update grade: "))
    course_id = int(input("Enter course ID: "))
    grade = int(input("Enter new grade: "))
    update_query = "UPDATE enrolled SET grade = %s WHERE student_id = %s AND course_id = %s;"
    cursor.execute(update_query, (grade, student_id, course_id))
    conn.commit()
    print("🔄 Student's grade has been updated!")

    # DELETE operation: Delete a student
    id = int(input("Enter student ID to delete: "))
    delete_query = "DELETE FROM students WHERE id = %s;"
    cursor.execute(delete_query, (id,))
    conn.commit()
    print(f"🗑️ Student with ID {id} has been removed from the database!")

except mysql.connector.Error as e:
    print(f"❌ Database error: {e}")

finally:
    if conn.is_connected():
        cursor.close()
        conn.close()
        print("🔌 Connection closed.")