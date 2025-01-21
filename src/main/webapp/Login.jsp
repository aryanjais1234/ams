<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="Login.css">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="login-container">
        <div class="login-form">
            <h1>EasyGo - AMS</h1>
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label for="Input">User Id</label>
                    <i class="bx bxs-user icon"></i>
                    <input class="UserId-bxs-user" id="userId" name="userId" type="text" placeholder="User ID">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <i class="bx bxs-lock icon"></i>
                    <input type="password" id="pwd" name="password" placeholder="Password">
                </div>
                <button type="submit">Login</button>
                <div id="error">
                    <% if (request.getAttribute("errorMessage") != null) { %>
                        <p><%= request.getAttribute("errorMessage") %></p>
                    <% } %>
                </div>
            </form>
            <div class="register">
                <p>New User? <a href="Registration.jsp"><b>Register Yourself</b></a></p>
            </div>
        </div>
    </div>
</body>
</html>
