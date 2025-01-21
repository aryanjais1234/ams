<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Refund Confirmation</title>
    <style>
        /* CSS provided by the user */
        .confirmation-container {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            text-align: center;
            width: 400px;
            margin: 50px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .confirmation-container h2 {
            color: #28a745;
        }

        p {
            font-size: 16px;
            margin: 10px 0;
        }

        .tick-mark {
            margin: 0 auto 20px;
            width: 52px;
            height: 52px;
        }

        .tick-circle {
            stroke: #28a745;
            stroke-width: 4;
            fill: none;
            animation: tick-circle-animation 0.6s ease;
        }

        .tick-path {
            stroke-dasharray: 29.7833385, 29.7833385;
            stroke-dashoffset: 29.7833385;
            animation: tick-path-animation 0.3s 0.4s ease forwards;
        }

        @keyframes tick-circle-animation {
            0% {
                stroke-dasharray: 0, 157;
                stroke-dashoffset: 157;
            }
            100% {
                stroke-dasharray: 157, 157;
                stroke-dashoffset: 0;
            }
        }

        @keyframes tick-path-animation {
            0% {
                stroke-dashoffset: 29.7833385;
            }
            100% {
                stroke-dashoffset: 0;
            }
        }

        .btn-confirm {
            display: inline-block;
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s;
        }

        .btn-confirm:hover {
            background-color: #218838;
        }
    </style>
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
        <h2>Refund Successful</h2>
        <p>Your refund of ₹<strong>${refundAmount}</strong> has been processed successfully.</p>
        <a href="HomePage.jsp" class="btn-confirm">Back to Home</a>
    </div>
</body>
</html>
    