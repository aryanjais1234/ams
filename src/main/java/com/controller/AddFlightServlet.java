package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CarrierDAO;
import com.dao.FlightDao;
import com.model.Carrier;
import com.model.Flight;

@WebServlet("/AddFlightServlet")
public class AddFlightServlet extends HttpServlet {

    private CarrierDAO carrierDAO;
    private FlightDao flightDAO;

    @Override
    public void init() throws ServletException {
        carrierDAO = new CarrierDAO();
        flightDAO = new FlightDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch carrier list and send to JSP
    	List<Carrier> carrierList = carrierDAO.getAllCarriersNames();
    	
        
        // Set the list as a request attribute to be accessible in JSP
    	request.setAttribute("carriers", carrierList);
        
        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("addFlight.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get flight details from the form
    	   
        String carrierName = request.getParameter("carrierName");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        double airFare = Double.parseDouble(request.getParameter("airFare"));
        String status = request.getParameter("status");        
        int businessClassSeats = Integer.parseInt(request.getParameter("seatCapacityBusinessClass"));
        int economyClassSeats = Integer.parseInt(request.getParameter("seatCapacityEconomyClass"));
        int executiveClassSeats = Integer.parseInt(request.getParameter("seatCapacityExecutiveClass"));

        // Retrieve carrierId by carrierName from the database
        int carrierId = carrierDAO.getCarrierIdByName(carrierName);

        if (carrierId > 0) {
            // Create a Flight object and set its values
            Flight flight = new Flight();
            flight.setCarrierId(carrierId);
            flight.setOrigin(origin);
            flight.setDestination(destination);
            flight.setAirFare(airFare);
            flight.setStatus(status);
            flight.setBusinessClassSeats(businessClassSeats);
            flight.setEconomyClassSeats(economyClassSeats);
            flight.setExecutiveClassSeats(executiveClassSeats);

            // Save the flight details in the database
            flightDAO.addFlight(flight);

            // Redirect back with a success message
            request.setAttribute("flightSuccessMessage", "Flight added successfully!");
        } else {
            request.setAttribute("flightErrorMessage", "Failed to add flight. Invalid carrier.");
        }

        // Forward request back to JSP with updated carrier list
        doGet(request, response);
    }
    
    

}
