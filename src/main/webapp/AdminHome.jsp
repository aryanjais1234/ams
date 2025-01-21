<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
    <link rel="stylesheet" href="adminHome.css">
</head>
<body>
    <nav>
        <ul>
            <li><a href="addCarrier.jsp">Add Carrier</a></li>
            <li><a href="CarrierServlet">Edit/Delete Carrier</a></li>
            <li><a href="AddFlightServlet">Add Flight</a></li>
            <li><a href="editFlight.jsp">Edit/Delete Flight</a></li>
        </ul>
    </nav>
    
    <h2>All Carriers</h2>
    <table id="carrierTable">
        <thead>
            <tr>
                <th>Carrier Name</th>
                <th>30 Days Advance Booking</th>
                <th>60 Days Advance Booking</th>
                <th>90 Days Advance Booking</th>
                <th>Bulk Booking</th>
                <th>Status</th> 
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
     
                	Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); // Step 1
            		
                	conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\21053012\\eclipse-workspace\\AMS_Project\\AMS_DB;create=true");// Create a statement
                    stmt = conn.createStatement();
                    
                    // Execute a query to retrieve carrier details
                    String sql = "SELECT carrierId, carrierName, Days30, Days60, Days90, bulkBooking, status FROM Carrier";
                    rs = stmt.executeQuery(sql);
                    
                    // Iterate through the result set and dynamically insert rows into the table
                    while (rs.next()) {
                    	int carrierId = rs.getInt("carrierId");
                        String carrierName = rs.getString("carrierName");
                        double _30DaysAdvanceBooking = rs.getDouble("Days30");
                        double _60DaysAdvanceBooking = rs.getDouble("Days60");
                        double _90DaysAdvanceBooking = rs.getDouble("Days90");
                        double bulkBooking = rs.getDouble("bulkBooking");
                        String status = rs.getString("status");
            %>
            
            <tr>
            <td><%= carrierName %></td>
            <td><%= _30DaysAdvanceBooking %></td>
            <td><%= _60DaysAdvanceBooking %></td>
            <td><%= _90DaysAdvanceBooking %></td>
            <td><%= bulkBooking %></td>
            <td><%= status %></td>
            <td>
                <form action="CarrierServlet" method="post" style="display:inline;">
                	<input type="hidden" name="action" value="searchCarrier">
                    <input type="hidden" name="carrierName" value="<%= carrierName %>">
                    <button type="submit" class="edit-button">Edit</button>
                </form>
                <form action="CarrierServlet" method="post" style="display:inline;">
                	<input type="hidden" name="action" value="deleteCarrier">
                    <input type="hidden" name="carrierName" value="<%= carrierName %>">
                    <button type="submit" class="delete-button">Delete</button>
                </form>
            </td>
        </tr>
        <% 
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
        %>
        </tbody>
    </table>
    
    <h2>All Flights</h2>
    <table id="flightTable">
        <thead>
            <tr>
                <th>Carrier Name</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Air Fare</th>
                <th>Seat Capacity (Business)</th>
                <th>Seat Capacity (Economy)</th>
                <th>Seat Capacity (Executive)</th>
                <th>Status</th>                
                <th>Actions</th>
                
            </tr>
        </thead>
        <tbody>
            <% 
                conn = null;
                stmt = null;
                rs = null;

                try {
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                    conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\21053012\\eclipse-workspace\\AMS_Project\\AMS_DB;create=true");// Create a statement
                    stmt = conn.createStatement();
                    
                    // Join Flight and Carrier tables to get carrier name for each flight
                    String sql = "SELECT c.carrierName,f.flightId, f.origin, f.destination, f.airFare, " +
                                 "f.businessClassSeats, f.economyClassSeats, f.executiveClassSeats, f.status " +
                                 "FROM Flight f " +
                                 "JOIN Carrier c ON f.carrierId = c.carrierId";
                    rs = stmt.executeQuery(sql);
                    
                    // Iterate through the result set to display flight details
                    while (rs.next()) {
                    	int flightId = rs.getInt("flightId");
                        String carrierName = rs.getString("carrierName");
                        String origin = rs.getString("origin");
                        String destination = rs.getString("destination");
                        double airFare = rs.getDouble("airFare");
                        int businessSeats = rs.getInt("businessClassSeats");
                        int economySeats = rs.getInt("economyClassSeats");
                        int executiveSeats = rs.getInt("executiveClassSeats");
                        String status = rs.getString("status");
            %>
            <tr>
                <td><%= carrierName %></td>
                <td><%= origin %></td>
                <td><%= destination %></td>
                <td><%= airFare %></td>
                <td><%= businessSeats %></td>
                <td><%= economySeats %></td>
                <td><%= executiveSeats %></td>
                <td><%= status %></td>
                <td>
                <form action="EditFlightServlet" method="post" style="display:inline;">
                	<input type="hidden" name="action" value="searchFlight">
                    <input type="hidden" name="flightId" value="<%= flightId %>">
                    <button type="submit" class="edit-button">Edit</button>
                </form>
                <form action="EditFlightServlet" method="post" style="display:inline;">
                	<input type="hidden" name="action" value="deleteFlight">
                    <input type="hidden" name="flightId" value="<%= flightId %>">
                    <button type="submit" class="delete-button">Delete</button>
                </form>
            </td>
            </tr>
            <% 
                    } 
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                }
            %>
        </tbody>
    </table>

    <script src="adminHome.js"></script>
</body>
</html>
