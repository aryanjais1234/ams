<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "com.model.Booking" %>
 <%
 	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cahce");
	response.setDateHeader("Expires", 0);
    // Check if the session exists
    session = request.getSession(false); // Don't create a new session if one doesn't exist
    if (session == null || session.getAttribute("username") == null) {
        // If no session or no user is logged in, redirect to login page
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Booking Confirmation</title>
    <link rel="stylesheet" href="booking.css">
</head>
<body>
    <div class="confirmation-container">
        <div class="tick-mark">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
                <circle cx="26" cy="26" r="25" fill="none" class="tick-circle"/>
                <path fill="none" stroke="#28a745" stroke-width="4" stroke-linecap="round" stroke-linejoin="round"
                      d="M14 27l7 7 16-16" class="tick-path"/>
            </svg>
        </div>
        <h2>Booking Confirmed!</h2>
        <p>Your flight has been successfully booked. Here are your details:</p>

        <div class="booking-details">
            <p><strong>Flight ID:</strong> <%= ((Booking) request.getAttribute("booking")).getFlightId() %></p>
            <p><strong>User ID:</strong> <%= ((Booking) request.getAttribute("booking")).getUserId() %></p>
            <p><strong>No. of Seats:</strong> <%= ((Booking) request.getAttribute("booking")).getNoOfSeats() %></p>
            <p><strong>Seat Category:</strong> <%= ((Booking) request.getAttribute("booking")).getSeatCategory() %></p>
            <p><strong>Date of Travel:</strong> <%= ((Booking) request.getAttribute("booking")).getDateOfTravel() %></p>
            <p><strong>Total Amount:</strong> <%= ((Booking) request.getAttribute("booking")).getBookingAmount() %></p>
        </div>
        <a href="HomePage.jsp" class="btn-confirm">Back to Home</a>
    </div>
</body>
</html>