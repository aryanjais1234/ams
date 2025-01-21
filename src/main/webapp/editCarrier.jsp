<%@ page import="java.util.List" %>
<%@ page import="com.model.Carrier" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Carrier</title>
    <link rel="stylesheet" href="addCarrier.css">
</head>
<body>
    <nav>
        <ul>
            <li><a href="AdminHome.jsp">Home</a></li>    
            <li><a href="addCarrier.jsp">Add Carrier</a></li>
            <li><a href="addFlight.jsp">Add Flight</a></li>
            <li><a href="editFlight.jsp">Edit/Delete Flight</a></li>
            <li><a href="bookFlight.jsp">Book Flight</a></li>
        </ul>
    </nav>
  
    <h2>Edit Carrier Details</h2>

    <!-- Carrier details section for editing -->
    <%
        Carrier carrierDetails = (Carrier) request.getAttribute("carrierDetails");
        if (carrierDetails != null) {
    %>
    <form action="CarrierServlet" method="POST">
    <input type="hidden" name="action" value="editCarrier">

    <label for="carrierName">Carrier Name:</label>
    <input type="text" id="carrierName" name="carrierName" value="<%= carrierDetails.getCarrierName() %>" readonly>

    <hr>
    <label for="status">Status:</label>
    <select id="status" name="status">
        <option value="Active">Active</option>
        <option value="Cancel">Cancel</option>
    </select>
    <hr>
    <h3>Discount Percentage</h3>

    <label for="30DaysAdvanceBooking">30 Days Advance Booking:</label>
    <input type="number" id="30DaysAdvanceBooking" name="30DaysAdvanceBooking" value="<%= carrierDetails.get_30DaysAdvanceBooking() %>">

    <label for="60DaysAdvanceBooking">60 Days Advance Booking:</label>
    <input type="number" id="60DaysAdvanceBooking" name="60DaysAdvanceBooking" value="<%= carrierDetails.get_60DaysAdvanceBooking() %>">

    <label for="90DaysAdvanceBooking">90 Days Advance Booking:</label>
    <input type="number" id="90DaysAdvanceBooking" name="90DaysAdvanceBooking" value="<%= carrierDetails.get_90DaysAdvanceBooking() %>">

    <label for="bulkBooking">Bulk Booking:</label>
    <input type="number" id="bulkBooking" name="bulkBooking" value="<%= carrierDetails.getBulkBooking() %>">

    <label for="silverUser">Silver User:</label>
    <input type="number" id="silverUser" name="silverUser" value="<%= carrierDetails.getSilverUser() %>">

    <label for="goldUser">Gold User:</label>
    <input type="number" id="goldUser" name="goldUser" value="<%= carrierDetails.getGoldUser() %>">

    <label for="platinumUser">Platinum User:</label>
    <input type="number" id="platinumUser" name="platinumUser" value="<%= carrierDetails.getPlatinumUser() %>">

    <hr>
    <h3>Refund Percentage</h3>

    <label for="2DaysBeforeTravelDate">2 Days Before Travel Date:</label>
    <input type="number" id="2DaysBeforeTravelDate" name="2DaysBeforeTravelDate" value="<%= carrierDetails.get_2DaysBeforeTravelDate() %>">

    <label for="10DaysBeforeTravelDate">10 Days Before Travel Date:</label>
    <input type="number" id="10DaysBeforeTravelDate" name="10DaysBeforeTravelDate" value="<%= carrierDetails.get_10DaysBeforeTravelDate() %>">

    <label for="20DaysOrMoreBeforeTravelDate">20 Days or More Before Travel Date:</label>
    <input type="number" id="20DaysOrMoreBeforeTravelDate" name="20DaysOrMoreBeforeTravelDate" value="<%= carrierDetails.get_20DaysOrMoreBeforeTravelDate() %>">


    <button type="submit" id="editCarrier">Save Changes</button>
</form>


    <form action="CarrierServlet" method="POST">
        <input type="hidden" name="action" value="deleteCarrier">
        <input type="hidden" name="carrierName" value="<%= carrierDetails.getCarrierName() %>">
        <button type="submit" id="deleteCarrier">Delete Carrier</button>
    </form>
    <% } else { %>
        <p>No carrier selected for editing.</p>
    <% } %>
</body>
</html>
