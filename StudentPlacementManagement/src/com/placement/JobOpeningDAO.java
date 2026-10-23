
package com.placement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobOpeningDAO {

    // Add a job opening
    public void addJobOpening(JobOpening job) {

        String sql = "INSERT INTO job_openings "
                   + "(company_id, job_title, salary_lpa) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, job.getCompanyId());
            ps.setString(2, job.getJobTitle());
            ps.setDouble(3, job.getSalaryLpa());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Job opening added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error adding job: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // View jobs with company names
    public void displayJobOpenings() {

        String sql = "SELECT j.job_id, c.company_name, "
                   + "j.job_title, j.salary_lpa "
                   + "FROM job_openings j "
                   + "JOIN companies c ON j.company_id = c.company_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== AVAILABLE JOBS =====");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("Job ID: " + rs.getInt("job_id"));
                System.out.println("Company: "
                        + rs.getString("company_name"));
                System.out.println("Job Title: "
                        + rs.getString("job_title"));
                System.out.println("Salary: "
                        + rs.getDouble("salary_lpa") + " LPA");
                System.out.println("---------------------------");
            }

            if (!found) {
                System.out.println("No job openings found.");
            }

        } catch (SQLException e) {
            System.out.println("Error displaying jobs: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
