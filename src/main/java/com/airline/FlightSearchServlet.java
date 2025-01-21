package com.airline;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AmsDao;
import com.model.Flight;

/**
 * Servlet implementation class FlightSearchServlet
 */
@WebServlet("/FlightSearchServlet")
public class FlightSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        String travelDate = request.getParameter("travelDate");

        List<Flight> flights = new ArrayList<>();

        // DB connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = AmsDao.getConnection();

            // Query to find flights based on origin, destination, and date
            String sql = "SELECT * FROM Flight WHERE origin = ? AND destination = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, origin);
            pstmt.setString(2, destination);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flightId"));
                flight.setOrigin(rs.getString("origin"));
                flight.setDestination(rs.getString("destination"));
                flight.setAirFare(rs.getDouble("airFare"));
                flight.setEconomyClassSeats(rs.getInt("economyClassSeats"));
                flight.setBusinessClassSeats(rs.getInt("businessClassSeats"));
                flight.setExecutiveClassSeats(rs.getInt("executiveClassSeats"));
                flights.add(flight);
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the flights attribute in the request scope
        request.setAttribute("flights", flights);

        // Forward to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage.jsp");
        dispatcher.forward(request, response);
    }
}
