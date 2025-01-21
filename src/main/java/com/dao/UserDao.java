package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.dao.AmsDao;
import com.model.Flight;
import com.model.User;
public class UserDao {
	
	public Connection getConnection() throws SQLException {
        return AmsDao.getConnection();
    }
	
	public User getUserById(int userId) {
	    String query = "SELECT * FROM Users WHERE USERID = ?";
	    System.out.println("Try to get user details: " + userId);

	    try (Connection connection = getConnection();
	         PreparedStatement stmt = connection.prepareStatement(query)) {
	         
	        stmt.setInt(1, userId);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            User user = new User();
	            user.setFirstName(rs.getString("firstname")); // Corrected the column name
	            user.setLastName(rs.getString("lastname"));
	            user.setAddress(rs.getString("address1"));
	            user.setContactNumber(rs.getString("PHONE"));
	            user.setEmail(rs.getString("EMAILID"));
	            user.setDob(rs.getDate("dob"));

	            System.out.println("User Retrieved");
	            return user;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	public boolean updateUser(User user) {
	    String query = "UPDATE Users SET FIRSTNAME = ?, LASTNAME = ?, DOB = ?, EMAILID = ?, ADDRESS1 = ?, PHONE = ? WHERE USERID = ?";
	    
	    try (Connection connection = getConnection();
	         PreparedStatement stmt = connection.prepareStatement(query)) {
	         
	        stmt.setString(1, user.getFirstName());
	        stmt.setString(2, user.getLastName());
	        stmt.setDate(3, new java.sql.Date(user.getDob().getTime()));
	        stmt.setString(4, user.getEmail());
	        stmt.setString(5, user.getAddress());
	        stmt.setString(6, user.getContactNumber());
	        stmt.setInt(7, user.getUserId());

	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}
