package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AmsDao;
import com.model.Flight;

@WebServlet("/SearchFlightServlet")
public class SearchFlightServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> origins = new ArrayList<>();
        List<String> destinations = new ArrayList<>();

        try (Connection conn = AmsDao.getConnection()) {
            // Fetch distinct origins
            String originQuery = "SELECT DISTINCT origin FROM Flight";
            PreparedStatement originStmt = conn.prepareStatement(originQuery);
            ResultSet originRs = originStmt.executeQuery();
            
            while (originRs.next()) {
                origins.add(originRs.getString("origin"));
            }
            originRs.close();
            originStmt.close();

            // Fetch distinct destinations
            String destinationQuery = "SELECT DISTINCT destination FROM Flight";
            PreparedStatement destinationStmt = conn.prepareStatement(destinationQuery);
            ResultSet destinationRs = destinationStmt.executeQuery();
            
            while (destinationRs.next()) {
                destinations.add(destinationRs.getString("destination"));
            }
            destinationRs.close();
            destinationStmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        HttpSession session = request.getSession();

        // Set the lists as request attributes
        session.setAttribute("origins", origins);
        session.setAttribute("destinations", destinations);

        // Forward the request to HomePage.jsp
        request.getRequestDispatcher("HomePage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        String travelDate = request.getParameter("travelDate");
        int seats = Integer.parseInt(request.getParameter("seats"));
        System.out.println("seats : "+seats);
        String message = "";
        HttpSession session=request.getSession();
        session.setAttribute("seats", seats);

        List<Flight> flights = new ArrayList<>();

        try (Connection conn = AmsDao.getConnection()) {
            String query = "SELECT * FROM Flight WHERE origin = ? AND destination = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, origin);
            stmt.setString(2, destination);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	String status = rs.getString("status");  // Assuming the status column is named "status"
                
                if ("Cancel".equalsIgnoreCase(status)) {
                    // Skip adding this flight and set a message indicating booking is not allowed
                    message = "Booking is not allowed for flights with canceled status.";
                    continue;
                }
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flightId"));
                flight.setOrigin(rs.getString("origin"));
                flight.setDestination(rs.getString("destination"));
                flight.setEconomyClassSeats(rs.getInt("economyClassSeats"));
                flight.setBusinessClassSeats(rs.getInt("businessClassSeats"));
                flight.setExecutiveClassSeats(rs.getInt("executiveClassSeats"));
                flight.setAirFare(rs.getDouble("airFare"));

                flights.add(flight);
            }

            // Close the ResultSet and Statement
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (flights.isEmpty()) {
        	if(message.length()==0)
        		message = "No flights available for the selected route and date.";
           request.setAttribute("message", message);
        }
        

        // Pass the flight list to the JSP
        request.setAttribute("flights", flights);
        request.getRequestDispatcher("HomePage.jsp").forward(request, response);
    }
}
