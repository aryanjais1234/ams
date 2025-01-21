<%@ page import="java.util.List" %>
<%@ page import="com.model.Flight" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Flight</title>
    <link rel="stylesheet" href="addCarrier.css">
</head>
<body>
    <nav>
        <ul>
            <li><a href="AdminHome.jsp">Home</a></li>    
            <li><a href="addCarrier.jsp">Add Carrier</a></li>
            <li><a href="addFlight.jsp">Add Flight</a></li>
        </ul>
    </nav>
  
    <h2>Edit Flight Details</h2>

    <!-- Flight details section for editing -->
    <%	
    	String carrierName = (String) request.getAttribute("carrierName");
    	int flightId = (int) request.getAttribute("flightId");
        Flight flightDetails = (Flight) request.getAttribute("flightDetails");
        if (flightDetails != null) {
    %>
    <form action="EditFlightServlet" method="POST">
        <input type="hidden" name="action" value="editFlight">
        <input type="hidden" name="flightId" value="<%= flightDetails.getFlightId() %>">
        
        <label for="carrierName">Carrier Name:</label>
        <input type="text" id="carrierName" name="carrierName" value="<%= carrierName %>" readonly>
        <label for="flightId">Carrier Name:</label>
        <input type="text" id="flightId" name="flightId" value="<%= flightId %>" readonly>

        <label for="origin">Origin:</label>
        <input type="text" id="origin" name="origin" value="<%= flightDetails.getOrigin() %>">

        <label for="destination">Destination:</label>
        <input type="text" id="destination" name="destination" value="<%= flightDetails.getDestination() %>">

        <label for="airFare">Air Fare:</label>
        <input type="number" id="airFare" name="airFare" value="<%= flightDetails.getAirFare() %>">
        
        <label for="status">Status:</label>
		<select id="status" name="status">
		    <option value="Active">Active</option>
		    <option value="Cancel">Cancel</option>
		</select>


        <label for="businessSeats">Business Class Seats:</label>
        <input type="number" id="businessSeats" name="businessSeats" value="<%= flightDetails.getBusinessClassSeats() %>">

        <label for="economySeats">Economy Class Seats:</label>
        <input type="number" id="economySeats" name="economySeats" value="<%= flightDetails.getEconomyClassSeats() %>">

        <label for="executiveSeats">Executive Class Seats:</label>
        <input type="number" id="executiveSeats" name="executiveSeats" value="<%= flightDetails.getExecutiveClassSeats() %>">

        <button type="submit" id="editFlight">Save Changes</button>
    </form>

    <form action="FlightServlet" method="POST">
        <input type="hidden" name="action" value="deleteFlight">
        <input type="hidden" name="flightId" value="<%= flightDetails.getFlightId() %>">
        <button type="submit" id="deleteFlight">Delete Flight</button>
    </form>
    <% } else { %>
        <p>No flight selected for editing.</p>
    <% } %>
</body>
</html>
