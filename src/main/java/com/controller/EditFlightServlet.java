package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CarrierDAO;
import com.dao.FlightDao;
import com.model.Flight;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.model.Carrier;
import com.model.Flight;

@WebServlet("/EditFlightServlet")
public class EditFlightServlet extends HttpServlet {
	
	private CarrierDAO carrierDAO;
	private FlightDao flightDAO;
    @Override
    public void init() throws ServletException {
        // Get connection from ServletContext
    	carrierDAO = new CarrierDAO();
    	flightDAO = new FlightDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Carrier> carriers = carrierDAO.getAllCarriersNames();
        List<Flight>flightList = flightDAO.getAllFlight();
		request.setAttribute("flights", flightList);
		

        // Set carrier names as a request attribute
        request.setAttribute("carriers", carriers);

        // Forward the request to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("editFlight.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get flight details from the form
    	  String action = request.getParameter("action");

          try {
              switch (action) {
                  case "editFlight":
                      updateFlight(request, response);
                      break;
                  case "deleteFlight":
                      deleteFlight(request, response);
                      break;
                  case "searchFlight":
                      searchFlight(request, response);
                      break;
                  default:
                      response.sendRedirect("AdminHome.jsp");
                      break;
              }
          } catch (Exception e) {
              throw new ServletException(e);
          }
//        String carrierName = request.getParameter("carrierName");
//        String origin = request.getParameter("origin");
//        String destination = request.getParameter("destination");
//        double airFare = Double.parseDouble(request.getParameter("airFare"));
//        int businessClassSeats = Integer.parseInt(request.getParameter("seatCapacityBusinessClass"));
//        int economyClassSeats = Integer.parseInt(request.getParameter("seatCapacityEconomyClass"));
//        int executiveClassSeats = Integer.parseInt(request.getParameter("seatCapacityExecutiveClass"));
//
//        // Retrieve carrierId by carrierName from the database
//        int carrierId = carrierDAO.getCarrierIdByName(carrierName);
//
//        if (carrierId > 0) {
//            // Create a Flight object and set its values
//            Flight flight = new Flight();
//            flight.setCarrierId(carrierId);
//            flight.setOrigin(origin);
//            flight.setDestination(destination);
//            flight.setAirFare(airFare);
//            flight.setBusinessClassSeats(businessClassSeats);
//            flight.setEconomyClassSeats(economyClassSeats);
//            flight.setExecutiveClassSeats(executiveClassSeats);
//
//            // Save the flight details in the database
//            flightDAO.addFlight(flight);
//
//            // Redirect back with a success message
//            request.setAttribute("flightSuccessMessage", "Flight added successfully!");
//        } else {
//            request.setAttribute("flightErrorMessage", "Failed to add flight. Invalid carrier.");
//        }
//
//        // Forward request back to JSP with updated carrier list
//        doGet(request, response);
    }
    
    private void updateFlight(HttpServletRequest request, HttpServletResponse response) throws Exception {
      int flightId = Integer.parseInt(request.getParameter("flightId"));
      String carrierName = request.getParameter("carrierName");
      String origin = request.getParameter("origin");
      String destination = request.getParameter("destination");
      double airFare = Double.parseDouble(request.getParameter("airFare"));
      String status = request.getParameter("status");      
      int businessClassSeats = Integer.parseInt(request.getParameter("businessSeats"));
      int economyClassSeats = Integer.parseInt(request.getParameter("economySeats"));
      int executiveClassSeats = Integer.parseInt(request.getParameter("executiveSeats"));

      // Retrieve carrierId by carrierName from the database
      int carrierId = carrierDAO.getCarrierIdByName(carrierName);
        Flight flight = new Flight();

	      flight.setCarrierId(carrierId);
	      flight.setOrigin(origin);
	      flight.setDestination(destination);
	      flight.setAirFare(airFare);
	      flight.setStatus(status);
	      flight.setBusinessClassSeats(businessClassSeats);
	      flight.setEconomyClassSeats(economyClassSeats);
	      flight.setExecutiveClassSeats(executiveClassSeats);
	      flight.setFlightId(flightId);
	      flightDAO.updateFlight(flight);

        response.sendRedirect("AdminHome.jsp");
    }

    private void deleteFlight(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        flightDAO.deleteFlight(flightId);
        response.sendRedirect("AdminHome.jsp");
    }

    private void searchFlight(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        Flight flightDetails = flightDAO.getFlightById(flightId);
        Carrier carrierName = carrierDAO.getCarrierById(flightDetails.getCarrierId());
        request.setAttribute("flightDetails", flightDetails);
        request.setAttribute("flightId", flightId);
        request.setAttribute("carrierName", carrierName.getCarrierName());
        RequestDispatcher dispatcher = request.getRequestDispatcher("editFlight.jsp");
        dispatcher.forward(request, response);
    }
}