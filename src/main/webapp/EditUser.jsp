<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dao.UserDao" %>
<%@ page import="com.model.User" %>
<%
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
<title>Edit Profile</title>
<link rel="stylesheet" href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css">
    <style>
        body {
            background-color: #f0f4f7; /* Light background */
            display: flex;
            justify-content: center;
            align-items: center;
            color: #333;
            height: 100vh;
            font-family: 'Poppins', sans-serif; /* Modern font */
            margin: 0;
            transition: background-color 0.3s ease-in-out;
        }

        h2 {
            text-align: center;
            font-size: 2rem;
            color: #4a90e2; /* Modern blue color */
            margin-bottom: 20px;
        }

        .register-container {
            background-color: rgba(255, 255, 255, 0.8); /* White with transparency */
            backdrop-filter: blur(15px);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
            width: 400px;
            transition: all 0.4s ease-in-out;
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
	<%
		UserDao userDao = new UserDao();
		String userIds = (String) session.getAttribute("loggedInUserID");
		int userId = Integer.parseInt(userIds);
		User user = userDao.getUserById(userId);
	%>
	<div class="register-container">
    <div class="register-form">
        <h2>Edit Details</h2>
        <form action="EditUserServlet" method="POST">
            <input type="hidden" name="action" value="edit">
            <div class="form-group">
                <label for="firstName">First Name</label>
                <i class="bx bxs-user"></i>
                <input type="text" id="firstName" name="firstName" value="<%= user.getFirstName() %>" required>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <i class="bx bxs-user"></i>
                <input type="text" id="lastName" name="lastName" value="<%= user.getLastName() %>" required>
            </div>
            <div class="form-group">
                <label for="dob">DOB</label>
                <i class="bx bxs-calendar"></i>
                <input type="date" id="dob" name="dob" value="<%= user.getDob() %>" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <i class="bx bxs-envelope"></i>
                <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <i class="bx bxs-home"></i>
                <textarea name="address" id="address" required><%= user.getAddress() %></textarea>
            </div>
            <div class="form-group">
                <label for="contactNumber">Contact Number</label>
                <i class="bx bxs-phone"></i>
                <input type="text" id="contactNumber" name="contactNumber" value="<%= user.getContactNumber() %>" required>
            </div>
            <button type="submit">Save</button>
        </form>
        <form action="EditUserServlet" method="POST">
            <input type="hidden" name="action" value="cancel">
            <button type="submit">Cancel</button>
        </form>
    </div>
</div>


</body>
</html>