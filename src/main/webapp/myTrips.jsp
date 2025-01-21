<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.model.Booking" %>
<%@ page import="com.dao.AmsDao" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Trips - Airline Management System</title>
    <link rel="stylesheet" href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css">
    <style>
         body {
            background-color: #f0f4f7;
            font-family: 'Poppins', sans-serif;
            color: #333;            
        }

        header {
            background-color: #343a40;
            padding: 20px 0;
        }

        nav ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            justify-content: center;
            gap: 30px;
        }

        nav ul li a {
            color: #fff;
            font-size: 18px;
            padding: 12px 20px;
            border-radius: 50px;
            transition: all 0.3s ease;
        }

        nav ul li a:hover {
            background-color: #4a90e2;
            box-shadow: 0 4px 15px rgba(23, 162, 184, 0.4);
        }

        .trips-container {
            width: 90%;
            max-width: 800px;
            margin: 20px auto;
        }

        .trip-card {
            background-color: rgba(255, 255, 255, 0.2);
            border-radius: 10px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
            padding: 20px;
            margin: 20px 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            position: relative;
        }

        .trip-card h4 {
            margin: 0 0 10px 0;
            color: #ffc107;
        }

        .trip-card p {
            margin: 5px 0;
        }

        .trip-card button {
            background-color: #dc3545;
            color: #fff;
            padding: 10px 20px;
            border-radius: 50px;
            border: none;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 10px;
        }

        .trip-card button:hover {
            background-color: #c82333;
        }

        .no-button {
            display: none; /* Hide the button for past trips */
        }
        .complete{
        	color:#28a745;
        }
    </style>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="HomePage.jsp">Home</a></li>
                <li><a href="MyProfile.jsp">My Profile</a></li>
                <li><a href="myTrips.jsp">My Trips</a></li>
                <li><a href="Login.jsp" id="logOut">Logout</a></li>
            </ul>
        </nav>
        <div class="welcome-message">
            <% 
                String loggedInUserName = (String) session.getAttribute("loggedInUserName");
                if (loggedInUserName != null) { 
            %>
                Welcome <span id="passengerName"><%= loggedInUserName %></span>!
            <% } else { %>
                Welcome Passenger!
            <% } %>
        </div>
    </header>

     <section class="trips-container">
        <h2>My Trips</h2>
        
        <% 
            Connection conn = null;
            PreparedStatement ps = null;
            PreparedStatement flightStmt = null;
            ResultSet rs = null;
            ResultSet flightRs = null;

            String userIds = (String) session.getAttribute("loggedInUserID");
            int userId = Integer.parseInt(userIds);

            List<Booking> bookings = new ArrayList<>();
            
            try {
                conn = AmsDao.getConnection();
                ps = conn.prepareStatement("SELECT * FROM FlightBooking WHERE USERID = ?");
                ps.setInt(1, userId);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(rs.getInt("bookingId"));
                    booking.setFlightId(rs.getInt("flightId"));
                    booking.setUserId(rs.getInt("USERID"));
                    booking.setNoOfSeats(rs.getInt("noOfSeats"));
                    booking.setSeatCategory(rs.getString("seatCategory"));
                    booking.setDateOfTravel(rs.getString("dateOfTravel"));
                    booking.setBookingStatus(rs.getString("bookingStatus"));
                    booking.setBookingAmount(rs.getDouble("bookingAmount"));
                    
                    // Get flight details to check the status
                    flightStmt = conn.prepareStatement("SELECT status FROM Flight WHERE flightId = ?");
                    flightStmt.setInt(1, booking.getFlightId());
                    flightRs = flightStmt.executeQuery();
                    if (flightRs.next()&& !booking.getBookingStatus().equals("Refunded") && !booking.getBookingStatus().equals("UserRefund")) {
                        booking.setFlightStatus(flightRs.getString("status"));
                    }
                    bookings.add(booking);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (flightRs != null) try { flightRs.close(); } catch (SQLException ignore) {}
                if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
                if (flightStmt != null) try { flightStmt.close(); } catch (SQLException ignore) {}
                if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
                if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
            }

            Calendar currentDate = Calendar.getInstance();
            
            for (Booking booking : bookings) {
                String dateOfTravel = booking.getDateOfTravel();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date travelDate = sdf.parse(dateOfTravel);
                boolean isPastTrip = travelDate.before(currentDate.getTime());
                String bookingStatus = booking.getBookingStatus();
                String flightStatus = booking.getFlightStatus();
        %>

                <div class="trip-card">
                    <h4>Booking ID: <%= booking.getBookingId() %></h4>
                    <p>Flight ID: <%= booking.getFlightId() %></p>
                    <p>Seats: <%= booking.getNoOfSeats() %> (<%= booking.getSeatCategory() %>)</p>
                    <p>Date of Travel: <%= booking.getDateOfTravel() %></p>
                    <p>Booking Amount: ₹<%= booking.getBookingAmount() %></p>

                    <% if ("Cancel".equals(flightStatus) && !isPastTrip && !"Refunded".equals(bookingStatus)) { %>
                        <p class="complete">Flight has been canceled.</p>
                        <form action="RefundRequestServlet" method="post">
                        <input type="hidden" name="action" value="flightCancel">
                            <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                            <button type="submit">Request Refund</button>
                        </form>
                    
                    <% } else if ("Refunded".equals(bookingStatus)) { %>
                        <p class="complete">Flight was canceled, and the amount has already been refunded.</p>
                        
                    <% } else if ("UserRefund".equals(bookingStatus)) { %>
                        <p class="complete">Canceled booking, and the amount has already been refunded.</p>
                        
                    <% } else if (!isPastTrip && !"UserRefund".equals(bookingStatus)) { %>
                        <form action="RefundRequestServlet" method="post">
                        	<input type="hidden" name="action" value="userCancel">
                            <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                            <button type="submit">Cancel</button>
                        </form>
                    <% } else { %>
                        <p class="complete">Trip Already Completed!</p>
                    <% } %>
                </div>

        <%
            }
        %>
    </section>
</body>
</html>
