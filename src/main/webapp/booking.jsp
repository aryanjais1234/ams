<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.Flight" %>
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
<html>
<head>
    <title>Book Flight</title>
    <link rel="stylesheet" href="booking.css">
</head>
<body>
    <% 
        int seats = (Integer) session.getAttribute("seats");
        int flightId = (Integer) request.getAttribute("flightId");
        String userId = (String) request.getAttribute("userID");
        Flight flight = (Flight) request.getAttribute("flightDetails");
        double price = (double) request.getAttribute("FlightPrice");
    %>

    <div class="booking-container">
        <h2>Book Your Flight</h2>
        <form action="BookingServlet" method="post" class="booking-form">
            <input type="hidden" name="action" value="book">
            <input type="hidden" name="flightId" value="<%= flightId %>">
            <input type="hidden" name="userId" value="1"> <!-- Example UserId -->

            <div class="form-group">
                <label for="seatCategory">Seat Category:</label>
                <select name="seatCategory" required>
                    <option value="Economy">Economy</option>
                    <option value="Executive">Executive</option>
                    <option value="Business">Business</option>
                </select>
            </div>

            <div class="form-group">
                <label for="dateOfTravel">Date of Travel:</label>
                <input type="date" name="dateOfTravel" required>
            </div>

            <div class="form-group">
                <label for="bookingAmount">Flight Per Ticket:</label>
                <input type="number" name="flightPerTicket" value="<%= price %>" readonly>
            </div>

            <div class="form-group">
                <label for="bookingAmount">No. of Passengers:</label>
                <input type="number" name="noOfSeats" value="<%= seats %>" readonly>
            </div>

            <div class="form-group">
                <label for="bookingAmount">Total Amount:</label>
                <input type="number" name="bookingAmount" value="<%= price * seats %>" readonly>
            </div>

            <input type="submit" value="Confirm Booking" class="btn-confirm">
        </form>
    </div>
</body>
</html>