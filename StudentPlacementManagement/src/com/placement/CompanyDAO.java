
package com.placement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDAO {

    // Add a company
    public void addCompany(Company company) {

        String sql = "INSERT INTO companies (company_name, location) "
                   + "VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, company.getCompanyName());
            ps.setString(2, company.getLocation());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Company added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error adding company: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // View all companies
    public void displayCompanies() {

        String sql = "SELECT * FROM companies";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== COMPANY LIST =====");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("Company ID: "
                        + rs.getInt("company_id"));
                System.out.println("Company Name: "
                        + rs.getString("company_name"));
                System.out.println("Location: "
                        + rs.getString("location"));
                System.out.println("-------------------------");
            }

            if (!found) {
                System.out.println("No companies found.");
            }

        } catch (SQLException e) {
            System.out.println("Error displaying companies: "
                    + e.getMessage());
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
