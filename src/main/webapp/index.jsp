<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        
        <div class="content">
            <h1>Welcome to Airline Management System</h1>
            <p>Choose how you'd like to log in:</p>
            <div class="button-container">
                <a href='Login.jsp'><button class="btn user-btn">Login as User</button></a>
                <a href='AdminLogin.jsp'><button class="btn admin-btn">Login as Admin</button></a>
            </div>
        </div>
    </div>
</body>

</html>
