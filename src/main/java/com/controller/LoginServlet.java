package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String hashedPassword = "";
        try {
			hashedPassword = hashPassword(password);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            Connection conn = AmsDao.getConnection();
            String query = "SELECT * FROM USERS WHERE userId = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userId);
            stmt.setString(2, hashedPassword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Get user's name from the result set
                String firstName = rs.getString("firstName"); // assuming 'firstName' is the column name

                // Store the user's name in the session
                HttpSession session = request.getSession();
                session.setAttribute("username", firstName);
                request.getSession().setAttribute("loggedInUserName", firstName);
                request.getSession().setAttribute("loggedInUserID", userId);

                // Redirect to home page after successful login
                response.sendRedirect("SearchFlightServlet");
            } else {
                // User not found in DB, set error message and redirect to login page
                request.setAttribute("errorMessage", "Invalid credentials, please try again.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred. Please try again later.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
