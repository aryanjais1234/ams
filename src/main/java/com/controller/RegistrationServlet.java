package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


import com.dao.AmsDao;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("contactNumber");

        // Validate form data
        if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            request.setAttribute("errorMessage", "Please fill all fields");
            request.getRequestDispatcher("Registration.jsp").forward(request, response);
            return;
        }

        // Generate userId and password
        int userId = (int) (Math.random() * 100000);
        String password = firstName.substring(0, 4) + "@123";
        String hashedPassword = "";
        try {
			hashedPassword = hashPassword(password);
			System.out.println(hashedPassword);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // Store data in the database
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
        	conn = AmsDao.getConnection();


            // Check if email or phone already exists
            String checkQuery = "SELECT * FROM USERS WHERE EMAILID = ? OR PHONE = ?";
            stmt = conn.prepareStatement(checkQuery);
            stmt.setString(1, email);
            stmt.setString(2, phone);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Email or phone already exists
                request.setAttribute("errorMessage", "User already exists with the same email or contact number.");
                request.getRequestDispatcher("Registration.jsp").forward(request, response);
                return;
            }

            // Email or phone doesn't exist, proceed with registration
            String sql = "INSERT INTO USERS (USERID, PASSWORD, FIRSTNAME, LASTNAME, PHONE, EMAILID, ADDRESS1, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, phone);
            stmt.setString(6, email);
            stmt.setString(7, address);
            stmt.setDate(8, java.sql.Date.valueOf(dob));

            // Execute the insert statement
            stmt.executeUpdate();

            // Set success message
         // Set success message
            String successMessage = "Passenger Registration is successful!";
            request.setAttribute("successMessage", successMessage);
            request.setAttribute("userId", userId);
            request.setAttribute("password", password);

            // Redirect to Confirmation.jsp
            request.getRequestDispatcher("RegistrationSuccess.jsp").forward(request, response);


        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("Registration.jsp").forward(request, response);

        } finally {
            try {
            	if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
