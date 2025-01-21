package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.dao.UserDao;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("EditUser.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		if (action.equals("edit")) {
            editUser(request, response);
        } else if (action.equals("cancel")) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("MyProfile.jsp");
        	dispatcher.forward(request, response);
            
        } else if(action.equals("search")) {
        	
        	doGet(request, response);
        }
	}
	
	 private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Retrieve form parameters
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String dob = request.getParameter("dob");
	        String email = request.getParameter("email");
	        String address = request.getParameter("address");
	        String contactNumber = request.getParameter("contactNumber");

	        // Get the logged-in user's ID from the session
	        String userIds = (String) request.getSession().getAttribute("loggedInUserID");
	        int userId = Integer.parseInt(userIds);

	        // Create User object with updated details
	        User user = new User();
	        user.setUserId(userId);
	        user.setFirstName(firstName);
	        user.setLastName(lastName);
	        user.setDob(java.sql.Date.valueOf(dob));
	        user.setEmail(email);
	        user.setAddress(address);
	        user.setContactNumber(contactNumber);

	        // Update the user in the database
	        UserDao userDao = new UserDao();
	        boolean success = userDao.updateUser(user);

	        if (success) {
	            request.setAttribute("successMessage", "Profile updated successfully!");
	        } else {
	            request.setAttribute("errorMessage", "Failed to update profile.");
	        }

	        RequestDispatcher dispatcher = request.getRequestDispatcher("MyProfile.jsp");
	        dispatcher.forward(request, response);
	    }

}
