
package com.placement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Register a new user
    public boolean registerUser(
            String username, String email, String password) {

        String sql = "INSERT INTO users "
                + "(username, email, password, role) "
                + "VALUES (?, ?, ?, 'STUDENT')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Registration successful!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Registration failed: "
                    + e.getMessage());
        }

        return false;
    }

    // Login an existing user
    public boolean loginUser(String email, String password) {

        String sql = "SELECT username, role FROM users "
                + "WHERE email = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println("Login successful!");
                    System.out.println("Welcome, "
                            + rs.getString("username"));
                    System.out.println("Role: "
                            + rs.getString("role"));
                    return true;
                }
            }

            System.out.println("Invalid email or password.");

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return false;
    }
}
