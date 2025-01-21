<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
    <link rel="stylesheet" href="adminLogin.css">
</head>
<body>
    <h2 class="title">Admin Login</h2>
    <form id="loginForm" >
        <label for="username">Username:</label>
        <input type="text" id="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" required><br><br>

        <button type="submit" id="btnId">Login</button>
    </form>
    <p id="errorMessage" style="color: red; display: none;">Invalid credentials!</p>

   
</body>
 <script src="adminLogin.js"></script>
</html>
