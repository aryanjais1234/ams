<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.model.Flight" %>
<%@ page import="java.util.List" %>
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
    <title>Home - Airline Management System</title>
   
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        /* Modernized Color Scheme */
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

        .welcome-message {
            position: absolute;
            top: 20px;
            right: 30px;
            font-size: 20px;
            color: #ffc107;
        }

        /* Form Styling */
        #bookingForm {
            background-color: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
            margin: 40px auto;
            width: 80%;
            max-width: 700px;
        }

        #bookingForm input,select, #bookingForm button {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: 2px solid #ced4da;
            border-radius: 8px;
            transition: all 0.3s ease;
            box-sizing: border-box;
        }

        #bookingForm input:focus, select{
            border-color: #17a2b8;
            outline: none;
            box-shadow: 0 4px 10px rgba(23, 162, 184, 0.3);
        }
        .seats-input-container {
    		display: flex;
    		align-items: center;
    		justify-content: center;
    		gap:20px;
		}

		.seat-btn {
		    background-color: #3498db;
		    color: white;
		    padding: 10px 15px;
		    border: none;
		    font-size: 18px;
		    cursor: pointer;
		    transition: background-color 0.3s ease;
		    border-radius: 5px;
		}
		
		.seat-btn:hover {
		    background-color: #2980b9;
		}
		
		#seatsInput {
		    width: 60px;
		    text-align: center;
		    font-size: 18px;
		    border: none;
		    background-color: white;
		    color: #2c3e50;
		    border-radius: 5px;
		    margin: 0 10px;
		    pointer-events: none;
		}
        #bookingForm button {
            background-color: #4a90e2;
            color: #fff;
            border: none;
            font-size: 18px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        #bookingForm button:hover {
            background-color: #138496;
            box-shadow: 0 4px 10px rgba(19, 132, 150, 0.4);
        }

        /* Table and Cards */
        h3 {
            text-align: center;
            margin-top: 40px;
        }

        .flight-card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin: 20px auto;
            width: 90%;
            max-width: 800px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .flight-card div {
            flex: 1;
            padding: 0 10px;
        }

        .flight-card button {
            background-color: #28a745;
            color: #fff;
            padding: 10px 20px;
            border-radius: 50px;
            border: none;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .flight-card button:hover {
            background-color: #218838;
            box-shadow: 0 4px 10px rgba(40, 167, 69, 0.4);
        }

        /* Dropdown Animations */
        #dropbtn {
            background-color: transparent;
            color: white;
            border: none;
            font-size: 18px;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #343a40;
            min-width: 160px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            transition: all 0.3s ease-in-out;
        }

        .dropdown-content a {
            color: white;
            padding: 12px 16px;
            display: block;
            text-decoration: none;
        }

        .dropdown-content a:hover {
            background-color: #495057;
        }

        .dropdown:hover .dropdown-content {
            display: block;
            opacity: 1;
            transform: translateY(10px);
        }
        p{
        color:black;
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
                
                <li><a href="LogOutServlet" id="logOut">Logout</a></li>
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

    <!-- Search Form -->
    <section class="container">
        <form id="bookingForm" action="SearchFlightServlet" method="post">
            <label for="flightSearchOrigin">Origin:</label>
			<select id="flightSearchOrigin" name="origin" required>
            <% 
            	List<String> origins = (List<String>) session.getAttribute("origins");
            	
            	for(String origin:origins){
            %>
			        <option value="<%= origin%>"><%= origin %></option>
            <% 
                } 
            %>
            
			</select>
			
			<label for="flightSearchDestination">Destination:</label>
			<select id="flightSearchDestination" name="destination" required>
			    <% 
            	List<String> destinations = (List<String>) session.getAttribute("destinations");
            	
            	for(String destination:destinations){
            %>
			        <option value="<%= destination%>"><%= destination %></option>
            <% 
                } 
            %>
			</select>


            <!-- New Seats Input Field -->
            <div class="form-group">
                <label for="seatsInput">Number of Seats:</label>
                <div class="seats-input-container">
                    <button type="button" class="seat-btn" id="seatMinus">-</button>
                    <input type="text" id="seatsInput" name="seats" value="1" readonly>
                    <button type="button" class="seat-btn" id="seatPlus">+</button>
                </div>
            </div>

            <button type="submit">Search Flights</button>
        </form>
    </section>

    <!-- Flight Cards -->
    <h3>Available Flights</h3>
    <section id="flightSection">
    <% 	
    	String userId = (String) session.getAttribute("loggedInUserID");
	
        List<Flight> flights = (List<Flight>) request.getAttribute("flights");
        String message = (String) request.getAttribute("message");

        if (flights != null && !flights.isEmpty()) { 
            for (Flight flight : flights) {
    %>
                <div class="flight-card">
                    <div>Flight ID: <%= flight.getFlightId() %></div>
                    <div>From: <%= flight.getOrigin() %> To: <%= flight.getDestination() %></div>
                    <div>Seats Available: Econ <%= flight.getEconomyClassSeats() %>, Bus <%= flight.getBusinessClassSeats() %>, Exec <%= flight.getExecutiveClassSeats() %></div>
                    <div>Fare: ₹ <%= flight.getAirFare() %></div>
                    <div>
                        <form action="BookingServlet" method="post">
                    	    <input type="hidden" name="action" value="search">
							<input type="hidden" name="userId" value="<%= userId %>">
                            <input type="hidden" name="flightId" value="<%= flight.getFlightId() %>">
                            <button type="submit">Book</button>
                        </form>
                    </div>
                </div>
    <% 
            } 
        } else { 
    %>
            <div class="alert alert-warning">
                <h3><%= message != null ? message : " " %></h3>
            </div>
    <% 
        } 
    %>
</section>


</body>
<script>
    const seatMinus = document.getElementById("seatMinus");
    const seatPlus = document.getElementById("seatPlus");
    const seatsInput = document.getElementById("seatsInput");

    let seatCount = parseInt(seatsInput.value);
    const maxSeats = 5;  // Maximum limit for the number of seats

    // Event listener for minus button
    seatMinus.addEventListener("click", function() {
        if (seatCount > 1) {
            seatCount--;
            seatsInput.value = seatCount;
        }
    });

    // Event listener for plus button
    seatPlus.addEventListener("click", function() {
        if (seatCount < maxSeats) {  // Only increment if seatCount is below the max limit
            seatCount++;
            seatsInput.value = seatCount;
        }
    });
</script>

</html>
