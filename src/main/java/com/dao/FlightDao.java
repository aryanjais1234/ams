package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.AmsDao;
import com.model.Carrier;
import com.model.Flight;

public class FlightDao {

    private static final String INSERT_FLIGHT_SQL = "INSERT INTO flight (carrierId, origin, destination, airFare, businessClassSeats, economyClassSeats, executiveClassSeats) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public Connection getConnection() throws SQLException {
        return AmsDao.getConnection();
    }

    public void addFlight(Flight flight) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FLIGHT_SQL)) {
            preparedStatement.setInt(1, flight.getCarrierId());
            preparedStatement.setString(2, flight.getOrigin());
            preparedStatement.setString(3, flight.getDestination());
            preparedStatement.setDouble(4, flight.getAirFare());
//            preparedStatement.setString(5, flight.getStatus());
            preparedStatement.setInt(5, flight.getBusinessClassSeats());
            preparedStatement.setInt(6, flight.getEconomyClassSeats());
            preparedStatement.setInt(7, flight.getExecutiveClassSeats());

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Flight searchFlight(String carrierName, String origin, String destination)  {
        String query = "SELECT * FROM Flight WHERE carrierName = ? AND origin = ? AND destination = ?";
        try (Connection connection = getConnection();
        		PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, carrierName);
            stmt.setString(2, origin);
            stmt.setString(3, destination);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	Flight flight = new Flight();
            	flight.setFlightId(rs.getInt("flightId"));
                flight.setAirFare(rs.getDouble("airFare"));
                flight.setBusinessClassSeats(rs.getInt("businessClassSeats"));
                flight.setEconomyClassSeats(rs.getInt("economyClassSeats"));
                flight.setExecutiveClassSeats(rs.getInt("executiveClassSeats"));
                rs.close();
                stmt.close();
                connection.close();
                return flight;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Flight> getAllFlight() {
    	
    	System.out.println("Try to ftech Flight origin and dest");
        List<Flight>flight = new ArrayList<>();
        String query = "SELECT origin,destination FROM Flight";
        
        try (Connection connection = AmsDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight f = new Flight();
                f.setOrigin(rs.getString("origin"));
                f.setDestination(rs.getString("destination"));
                flight.add(f);
            }
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return flight;
    }

    public void updateFlight(Flight flight) throws SQLException {
        String query = "UPDATE Flight SET origin = ?, destination = ?, airFare = ?, businessClassSeats = ?, economyClassSeats = ?, executiveClassSeats = ?, status = ? WHERE flightId = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set parameters in the correct order, starting with 1
            stmt.setString(1, flight.getOrigin());
            stmt.setString(2, flight.getDestination());
            stmt.setDouble(3, flight.getAirFare());
            stmt.setInt(4, flight.getBusinessClassSeats());
            stmt.setInt(5, flight.getEconomyClassSeats());
            stmt.setInt(6, flight.getExecutiveClassSeats());
            stmt.setString(7, flight.getStatus()); // Set status here
            stmt.setInt(8, flight.getFlightId()); // Update by flightId

            // Execute update query
            stmt.executeUpdate();
        }
    }



    public void deleteFlight(int flightId) throws SQLException {
        String query = "DELETE FROM Flight WHERE flightId = ?";
        try (Connection connection = getConnection();
        		PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, flightId);
            stmt.executeUpdate();
            connection.close();
        }
    }

    public Flight getFlightById(int flightId) throws SQLException  {
        String sql = "SELECT * FROM Flight WHERE flightId = ?";
        Flight flight = null;

        try (Connection conn = getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set the flightId parameter in the query
            pstmt.setInt(1, flightId);
            
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            
            // If a flight is found, map the result set to the Flight object
            if (rs.next()) {
                flight = new Flight();
                flight.setOrigin(rs.getString("origin"));
                flight.setDestination(rs.getString("destination"));
                flight.setCarrierId(rs.getInt("carrierId"));
            	flight.setFlightId(rs.getInt("flightId"));
                flight.setAirFare(rs.getDouble("airFare"));
                flight.setBusinessClassSeats(rs.getInt("businessClassSeats"));
                flight.setEconomyClassSeats(rs.getInt("economyClassSeats"));
                flight.setExecutiveClassSeats(rs.getInt("executiveClassSeats"));
            }
            System.out.println("Flight details fetched!");
            conn.close();
        }  
        
        return flight;
    }
}
