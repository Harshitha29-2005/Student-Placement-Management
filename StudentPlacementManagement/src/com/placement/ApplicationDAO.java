
package com.placement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDAO {

    // 1. Apply for a job
    public void applyForJob(int studentId, int jobId) {

        String sql = "INSERT INTO applications "
                   + "(student_id, job_id, application_date, status) "
                   + "VALUES (?, ?, CURRENT_DATE, 'Applied')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, jobId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                    "Job application submitted successfully!");
            } else {
                System.out.println(
                    "Application could not be submitted.");
            }

        } catch (SQLException e) {
            System.out.println(
                "Error submitting application: " + e.getMessage());
        }
    }

    // 2. View all applications
    public void displayApplications() {

        String sql =
            "SELECT a.application_id, s.student_name, s.email, "
          + "c.company_name, j.job_title, j.salary_lpa, "
          + "a.application_date, a.status "
          + "FROM applications a "
          + "JOIN students s ON a.student_id = s.student_id "
          + "JOIN job_openings j ON a.job_id = j.job_id "
          + "JOIN companies c ON j.company_id = c.company_id "
          + "ORDER BY a.application_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("\n================================");
                System.out.println("Application ID: "
                        + rs.getInt("application_id"));
                System.out.println("Student Name: "
                        + rs.getString("student_name"));
                System.out.println("Student Email: "
                        + rs.getString("email"));
                System.out.println("Company Name: "
                        + rs.getString("company_name"));
                System.out.println("Job Title: "
                        + rs.getString("job_title"));
                System.out.println("Salary (LPA): "
                        + rs.getDouble("salary_lpa"));
                System.out.println("Application Date: "
                        + rs.getDate("application_date"));
                System.out.println("Status: "
                        + rs.getString("status"));
                System.out.println("================================");
            }

            if (!found) {
                System.out.println("No applications found.");
            }

        } catch (SQLException e) {
            System.out.println(
                "Error displaying applications: " + e.getMessage());
        }
    }

    // 3. Update application status
    public void updateApplicationStatus(
            int applicationId, String status) {

        String sql =
            "UPDATE applications SET status = ? "
          + "WHERE application_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, applicationId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                    "Application status updated successfully!");
                System.out.println("New Status: " + status);
            } else {
                System.out.println(
                    "Application ID not found.");
            }

        } catch (SQLException e) {
            System.out.println(
                "Error updating application status: "
                + e.getMessage());
        }
    }
}
