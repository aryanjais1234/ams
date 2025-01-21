package com.airline;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookingDao;
import com.model.Booking;
import com.model.Carrier;
import com.model.Flight;
import com.dao.FlightDao;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private BookingDao bookingDao;
    private FlightDao flightDao;

    @Override
    public void init() throws ServletException {
        bookingDao = new BookingDao(); // Initialize the DAO for handling bookings
        flightDao = new FlightDao();   // Initialize the FlightDao to retrieve flight details
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        request.setAttribute("flightId", flightId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("booking.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("search")) {
            search(request, response);
        } else if (action.equals("book")) {
            book(request, response);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        String userId = (String) request.getAttribute("userID");
        try {
            Flight flightDetails = flightDao.getFlightById(flightId); // Handle SQLException here
            request.setAttribute("FlightDetails", flightDetails);
            request.setAttribute("FlightPrice", flightDetails.getAirFare());
            request.setAttribute("userId", userId);
            request.setAttribute("flightId", flightId);

            RequestDispatcher dispatcher = request.getRequestDispatcher("booking.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            // Handle the SQLException properly
            e.printStackTrace();
            request.setAttribute("message", "Error retrieving flight details. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void book(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Booking functionality here
    	HttpSession session=request.getSession();
    	
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        System.out.println(flightId);
        String userIds = (String) session.getAttribute("loggedInUserID");
        int userId = Integer.parseInt(userIds);
        System.out.println(userId + userIds);
        int noOfSeats = Integer.parseInt(request.getParameter("noOfSeats"));
        String seatCategory = request.getParameter("seatCategory");
        String dateOfTravel = request.getParameter("dateOfTravel");
        double bookingAmount = Double.parseDouble(request.getParameter("bookingAmount"));

        // Create Booking object and set properties
        Booking booking = new Booking();
        booking.setFlightId(flightId);
        booking.setUserId(userId);
        booking.setNoOfSeats(noOfSeats);
        booking.setSeatCategory(seatCategory);
        booking.setDateOfTravel(dateOfTravel);
        booking.setBookingAmount(bookingAmount);
        booking.setBookingStatus("Booked");

        // Save the booking to the database
        try {
            bookingDao.addBooking(booking);
            request.setAttribute("booking", booking);
            request.setAttribute("message", "Flight booked successfully!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
        	 e.printStackTrace();
             request.setAttribute("message", "Booking failed! Please try again.");
             RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
             dispatcher.forward(request, response);
        }

        // Forward to the confirmation JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
        dispatcher.forward(request, response);
    }
}
    
    
//    }
