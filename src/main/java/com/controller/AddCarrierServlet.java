package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AmsDao;

/**
 * Servlet implementation class AddCarrierServlet
 */
@WebServlet("/AddCarrierServlet")
public class AddCarrierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String carrierName = request.getParameter("carrierName");
        System.out.println(carrierName);
        String _30DaysAdvanceBooking = request.getParameter("30DaysAdvanceBooking");
        String _60DaysAdvanceBooking = request.getParameter("60DaysAdvanceBooking");
        String _90DaysAdvanceBooking = request.getParameter("90DaysAdvanceBooking");
        String bulkBooking = request.getParameter("bulkBooking");
        String silverUser = request.getParameter("silverUser");
        String goldUser = request.getParameter("goldUser");
        String platinumUser = request.getParameter("platinumUser");
        String _2DaysBeforeTravelDate = request.getParameter("2DaysBeforeTravelDate");
        String _10DaysBeforeTravelDate = request.getParameter("10DaysBeforeTravelDate");
        String _20DaysOrMoreBeforeTravelDate = request.getParameter("20DaysOrMoreBeforeTravelDate");

        try {
            // Connect to the database
            Connection conn = AmsDao.getConnection();
            // Create SQL query
            String query = "INSERT INTO Carrier (carrierName, Days30, Days60, Days90, " +
                            "bulkBooking, silverUser, goldUser, platinumUser, DaysBeforeTravelDate2, DaysBeforeTravelDate10, DaysOrMoreBeforeTravelDate20) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Create a PreparedStatement
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, carrierName);
            ps.setString(2, _30DaysAdvanceBooking);
            ps.setString(3, _60DaysAdvanceBooking);
            ps.setString(4, _90DaysAdvanceBooking);
            ps.setString(5, bulkBooking);
            ps.setString(6, silverUser);
            ps.setString(7, goldUser);
            ps.setString(8, platinumUser);
            ps.setString(9, _2DaysBeforeTravelDate);
            ps.setString(10, _10DaysBeforeTravelDate);
            ps.setString(11, _20DaysOrMoreBeforeTravelDate);

            // Execute update
            int row = ps.executeUpdate();

            // Redirect with a success message
            if (row > 0) {
                request.setAttribute("message", "Carrier added successfully.");
            } else {
                request.setAttribute("message", "Error in adding carrier.");
            }
            request.getRequestDispatcher("addCarrier.jsp").forward(request, response);

            // Close resources
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}