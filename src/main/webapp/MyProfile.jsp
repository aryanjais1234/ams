<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dao.UserDao" %>
<%@ page import="com.model.User" %>
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
<html>
<head>
<meta charset="UTF-8">
<title>MyProfile</title>
<link rel="stylesheet" href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css">
    <style>
        body {
		    background-color: #f0f4f7;
    font-family: Arial, sans-serif;
    color: white;
    
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
		    top: 15px;
		    right: 20px;
		    font-size: 18px;
		}

        h2 {
            text-align: center;
            font-size: 2rem;
            color: #4a90e2; /* Modern blue color */
            margin-bottom: 20px;
        }
        
        .main{
       	display: flex;
	    justify-content: center;
	    align-items: center;
	    min-height: 100vh;
        }

        .register-container {
        
            background-color: rgba(255, 255, 255, 0.8); /* White with transparency */
            backdrop-filter: blur(15px);
            
            border-radius: 15px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
            
            transition: all 0.4s ease-in-out;
            width: 400px;
            padding: 20px;
        }

        .register-container:hover {
            box-shadow: 0px 6px 25px rgba(0, 0, 0, 0.2); /* Hover effect */
        }

        .form-group {
            margin-bottom: 25px;
            position: relative;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 1rem;
            color: #4a4a4a; /* Darker label */
        }

        .form-group input, .form-group textarea {
            box-sizing: border-box;
            width: 100%;
            padding: 12px 40px; /* Padding for space and alignment */
            border: 1px solid #ddd;
            border-radius: 30px;
            background-color: #f9f9f9;
            transition: border-color 0.3s, background-color 0.3s;
        }

        .form-group input:focus, .form-group textarea:focus {
            outline: none;
            border-color: #4a90e2; /* Focus color */
            background-color: #fff;
        }

        .form-group i {
            position: absolute;
            left: 15px;
            top: 50%;
            font-size: 18px;
            color: #888;
            transition: color 0.3s;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-bottom:12px;
            border: none;
            border-radius: 30px;
            background-color: #4a90e2;
            color: white;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out;
        }

        button:hover {
            background-color: #357ABD;
        }

        #error, #success {
            text-align: center;
            margin-top: 10px;
        }

        #error p, #success p {
            color:#AFE1AF;
        }

        .login {
            text-align: center;
            margin-top: 20px;
        }

        .login a {
            color: #4a90e2;
            text-decoration: none;
            font-weight: bold;
        }

        .login a:hover {
            color: #357ABD;
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
    
	<%
		UserDao userDao = new UserDao();
		String userIds = (String) session.getAttribute("loggedInUserID");
		int userId = Integer.parseInt(userIds);
		User user = userDao.getUserById(userId);
	%>
	<div class="main">
	<div class="register-container">
        <div class="register-form">
            <h2>User Details</h2>
            <form action="EditUserServlet" method="POST">
            <input type="hidden" name="action" value="search">
                <div class="form-group">
                    <label for="firstName">First Name</label>
                    
                    <i class="bx bxs-user"></i>
                    <input type="text" id="firstName" name="firstName" placeholder="First Name" value="<%= user.getFirstName() %>" readonly required>
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name</label>
                    <i class="bx bxs-user"></i>
                    <input type="text" id="lastName" name="lastName" placeholder="Last Name" value="<%= user.getLastName() %>" readonly required>
                </div>
                <div class="form-group">
                    <label for="dob">DOB</label>
                    <i class="bx bxs-calendar"></i>
                    <input type="date" id="dob" name="dob" value="<%= user.getDob() %>" readonly required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <i class="bx bxs-envelope"></i>
                    <input type="email" id="email" name="email" placeholder="Email" value="<%= user.getEmail() %>" readonly required>
                </div>
                <div class="form-group">
                <label for="address">Address</label>
                <i class="bx bxs-home"></i>
                <textarea name="address" id="address" required readonly><%= user.getAddress() %></textarea>
            </div>
                <div class="form-group">
                    <label for="contactNumber">Contact Number</label>
                    <i class="bx bxs-phone"></i>
                    <input type="text" id="contactNumber" name="contactNumber" placeholder="Contact Number" value="<%= user.getContactNumber() %>" readonly required>
                </div>
                <button type="submit">Edit</button>
            </form>
        </div>
    </div>
	</div>

</body>
</html>