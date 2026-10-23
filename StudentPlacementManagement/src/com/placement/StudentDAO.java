
package com.placement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    // 1. Add student
    public void addStudent(Student student) {

        String sql = "INSERT INTO students "
                + "(student_name, email, department, graduation_year) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getDepartment());
            ps.setInt(4, student.getGraduationYear());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // 2. Display all students
    public void displayStudents() {

        String sql = "SELECT * FROM students ORDER BY student_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("\n-----------------------------");
                System.out.println("Student ID: "
                        + rs.getInt("student_id"));
                System.out.println("Name: "
                        + rs.getString("student_name"));
                System.out.println("Email: "
                        + rs.getString("email"));
                System.out.println("Department: "
                        + rs.getString("department"));
                System.out.println("Graduation Year: "
                        + rs.getInt("graduation_year"));
            }

            if (!found) {
                System.out.println("No students found.");
            }

        } catch (SQLException e) {
            System.out.println("Error displaying students: "
                    + e.getMessage());
        }
    }

    // 3. Search student by ID
    public void searchStudentById(int studentId) {

        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println("\n--- Student Details ---");
                    System.out.println("Student ID: "
                            + rs.getInt("student_id"));
                    System.out.println("Name: "
                            + rs.getString("student_name"));
                    System.out.println("Email: "
                            + rs.getString("email"));
                    System.out.println("Department: "
                            + rs.getString("department"));
                    System.out.println("Graduation Year: "
                            + rs.getInt("graduation_year"));
                } else {
                    System.out.println("Student not found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching student: "
                    + e.getMessage());
        }
    }

    // 4. Update student
    public void updateStudent(int studentId, String name,
            String email, String department, int graduationYear) {

        String sql = "UPDATE students SET student_name = ?, "
                + "email = ?, department = ?, graduation_year = ? "
                + "WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, department);
            ps.setInt(4, graduationYear);
            ps.setInt(5, studentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating student: "
                    + e.getMessage());
        }
    }

    // 5. Delete student
    public void deleteStudent(int studentId) {

        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting student: "
                    + e.getMessage());
            System.out.println(
                    "If the student has applications, deletion may be blocked by a foreign key.");
        }
    }
}
