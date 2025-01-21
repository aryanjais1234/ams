<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Carrier</title>
    <link rel="stylesheet" href="addCarrier.css">
</head>
<body>
    <nav>
        <ul>
            <li><a href="AdminHome.jsp">Home</a></li>
            <li><a href="addCarrier.jsp">Add Carrier</a></li>
            <li><a href="editCarrier.jsp">Edit/Delete Carrier</a></li>
            <li><a href="addFlight.jsp">Add Flight</a></li>
            <li><a href="editFlight.jsp">Edit/Delete Flight</a></li>
        </ul>
    </nav>
    
    <h2>Add Carrier Details</h2>
    <div class="container">
    <div class="conatiner2">
    <form action="AddCarrierServlet" method="POST">
        <label for="carrierName">Carrier Name:</label>
        <input type="text" id="carrierName" name="carrierName" maxlength="50" required>
        
        <hr>
        <label>Discount Percentage</label>
        <input type="number" id="30DaysAdvanceBooking" name="30DaysAdvanceBooking" placeholder="30 Days Advance Booking">
        <input type="number" id="60DaysAdvanceBooking" name="60DaysAdvanceBooking" placeholder="60 Days Advance Booking">
        <input type="number" id="90DaysAdvanceBooking" name="90DaysAdvanceBooking" placeholder="90 Days Advance Booking">
        <input type="number" id="bulkBooking" name="bulkBooking" placeholder="Bulk Booking">
        <input type="number" id="silverUser" name="silverUser" placeholder="Silver User">
        <input type="number" id="goldUser" name="goldUser" placeholder="Gold User">
        <input type="number" id="platinumUser" name="platinumUser" placeholder="Platinum User">
        
        <hr>
        <label>Refund Percentage</label>
        <input type="number" id="2DaysBeforeTravelDate" name="2DaysBeforeTravelDate" placeholder="2 Days Before Travel Date">
        <input type="number" id="10DaysBeforeTravelDate" name="10DaysBeforeTravelDate" placeholder="10 Days Before Travel Date">
        <input type="number" id="20DaysOrMoreBeforeTravelDate" name="20DaysOrMoreBeforeTravelDate" placeholder="20 Days or More Before Travel Date">

        <button type="submit">Add Carrier</button>
    </form>
    <p id="successMessage" style="display: none;"></p>
    
    </div>
    </div>
</body>
<% 
    String message = (String) request.getAttribute("message");
    if (message != null) {
%>
    <p style="color: green;"><%= message %></p>
<%
    }
%>

</html>
