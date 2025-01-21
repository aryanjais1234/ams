package com.dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Flight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class AmsDao
 */
@WebServlet("/AmsDao")
public class AmsDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmsDao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); // Step 1
		
			con = DriverManager.getConnection("jdbc:derby:C:\\Users\\21053012\\eclipse-workspace\\AMS_Project\\AMS_DB;create=true"); // Step 2
			System.out.println("Connected with DB");
			
		}
		catch (Exception e) {
			
			System.out.println(e);
		}
		System.out.println(con);
		return con;
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
//	public ResultSet getAllCarriers() throws SQLException {
//		Connection con = null;
//	    String sql = "SELECT carrierName FROM Carrier";
//	    PreparedStatement stmt = con.prepareStatement(sql);
//	    return stmt.executeQuery();
//	}
//
//	// DAO Method to Fetch Flight by Carrier, Origin, Destination
//	public Flight getFlightDetails(String carrierName, String origin, String destination) throws SQLException {
//	    Flight flight = null;
//	    Connection con = null;
//	    String sql = "SELECT * FROM Flight f JOIN Carrier c ON f.carrierId = c.carrierId WHERE c.carrierName = ? AND f.origin = ? AND f.destination = ?";
//	    
//	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
//	        stmt.setString(1, carrierName);
//	        stmt.setString(2, origin);
//	        stmt.setString(3, destination);
//	        
//	        ResultSet rs = stmt.executeQuery();
//	        
//	        if (rs.next()) {
//	            flight = new Flight();
//	            flight.setFlightId(rs.getInt("flightId"));
//	            flight.setAirFare(rs.getDouble("airFare"));
//	            flight.setBusinessClassSeats(rs.getInt("businessClassSeats"));
//	            flight.setEconomyClassSeats(rs.getInt("economyClassSeats"));
//	            flight.setExecutiveClassSeats(rs.getInt("executiveClassSeats"));
//	        }
//	    }
//	    
//	    return flight;
//	}
//
//	// DAO Method to Edit Flight
//	public boolean updateFlight(Flight flight) throws SQLException {
//	    String sql = "UPDATE Flight SET airFare = ?, businessClassSeats = ?, economyClassSeats = ?, executiveClassSeats = ? WHERE flightId = ?";
//	    Connection con = null;
//	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
//	        stmt.setDouble(1, flight.getAirFare());
//	        stmt.setInt(2, flight.getBusinessClassSeats());
//	        stmt.setInt(3, flight.getEconomyClassSeats());
//	        stmt.setInt(4, flight.getExecutiveClassSeats());
//	        stmt.setInt(5, flight.getFlightId());
//	        
//	        return stmt.executeUpdate() > 0;
//	    }
//	}
//
//	// DAO Method to Delete Flight
//	public boolean deleteFlight(int flightId) throws SQLException {
//	    String sql = "DELETE FROM Flight WHERE flightId = ?";
//	    Connection con = null;
//	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
//	        stmt.setInt(1, flightId);
//	        return stmt.executeUpdate() > 0;
//	    }
//	}

}
