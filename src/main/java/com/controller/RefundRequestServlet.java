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

import com.dao.AmsDao;

@WebServlet("/RefundRequestServlet")
public class RefundRequestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        String action = request.getParameter("action");
        double refundAmount = 0.0;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = AmsDao.getConnection();

            // Fetch the booking amount based on bookingId
            String query = "SELECT bookingAmount FROM FlightBooking WHERE bookingId = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, bookingId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                double bookingAmount = rs.getDouble("bookingAmount");
                
                // Example refund logic: refund 80% of the booking amount
                refundAmount = bookingAmount;

                // Update the booking status to "Refunded"
                String updateQuery = "";
                if(action.equals("flightCancel")) {
                	updateQuery = "UPDATE FlightBooking SET bookingStatus = 'Refunded' WHERE bookingId = ?";
                }
                else if(action.equals("userCancel")) {
                	updateQuery = "UPDATE FlightBooking SET bookingStatus = 'UserRefund' WHERE bookingId = ?";
                }
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, bookingId);
                updateStmt.executeUpdate();

                // Set refundAmount in request scope for display in JSP
                request.setAttribute("refundAmount", refundAmount);

                // Forward to confirmation.jsp to display refund amount
                request.getRequestDispatcher("Refund.jsp").forward(request, response);

            } else {
                request.setAttribute("errorMessage", "Booking ID not found.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing the refund request.");
            request.getRequestDispatcher("error.jsp").forward(request, response);

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
