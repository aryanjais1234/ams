<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Success</title>
<link rel="stylesheet" href="booking.css">
</head>
<body>
<div class="confirmation-container">
    <div class="tick-mark">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
                <circle cx="26" cy="26" r="25" fill="none" class="tick-circle"/>
                <path fill="none" stroke="#28a745" stroke-width="4" stroke-linecap="round" stroke-linejoin="round"
                      d="M14 27l7 7 16-16" class="tick-path"/>
            </svg>
        </div>

    <h2>Success!</h2>
    <p><%= request.getAttribute("successMessage") %></p>
    <p>ID: <strong><%= request.getAttribute("userId") %></strong></p>
    <p>Password: <strong><%= request.getAttribute("password") %></strong></p>
    <a href="Login.jsp" class="btn-confirm">Go to Login</a>
</div>
</body>
</html>