<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Carrier" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Flight</title>
    <link rel="stylesheet" href="addCarrier.css">
</head>
<body>
    <nav>
        <ul>
            <li><a href="AdminHome.jsp">Home</a></li>
            <li><a href="addCarrier.jsp">Add Carrier</a></li>
            <<li><a href="CarrierServlet">Edit/Delete Carrier</a></li>
            
        </ul>
    </nav>
    
    <h2>Add Flight Details</h2>
    <form action="AddFlightServlet" method="post">
        <label for="carrierName">Carrier Name:</label>
        <select id="carrierName" name="carrierName">
            <option value="">Select Carrier</option>
            <!-- Populate carrier options dynamically -->
            <% 
                List<Carrier> carriers = (List<Carrier>) request.getAttribute("carriers");
                if (carriers != null) {
                    for (Carrier carrier : carriers) { 
            %>
                <option value="<%= carrier.getCarrierName() %>" <%= carrier.getCarrierName().equals(request.getParameter("carrierName")) ? "selected" : "" %>><%= carrier.getCarrierName() %></option>
            <% 
                    }
                } else { 
            %>
                <option value="">No Carriers Available</option>
            <% 
                } 
            %>
        </select>
        
        <label for="origin">Origin:</label>
        <input id="origin" type="text" name="origin">
        
        <label for="destination">Destination:</label>
        <input id="destination" type="text" name="destination">

        <label for="airFare">Air Fare:</label>
        <input type="number" id="airFare" name="airFare" required>
        
        <label for="airFare">Status:</label>
        <input type="text" id="status" name="status" value="Active" required readonly>
        
        
        
        <label for="seatCapacityBusinessClass">Seat Capacity (Business Class):</label>
        <input type="number" id="seatCapacityBusinessClass" name="seatCapacityBusinessClass" required>
        
        <label for="seatCapacityEconomyClass">Seat Capacity (Economy Class):</label>
        <input type="number" id="seatCapacityEconomyClass" name="seatCapacityEconomyClass" required>
        
        <label for="seatCapacityExecutiveClass">Seat Capacity (Executive Class):</label>
        <input type="number" id="seatCapacityExecutiveClass" name="seatCapacityExecutiveClass" required>

        <button type="submit">Add Flight</button>
    </form>

    <!-- Show success or error message -->
    <c:if test="${not empty flightSuccessMessage}">
        <p id="flightSuccessMessage">${flightSuccessMessage}</p>
    </c:if>
    <c:if test="${not empty flightErrorMessage}">
        <p id="flightErrorMessage">${flightErrorMessage}</p>
    </c:if>

</body>
</html>
