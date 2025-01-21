package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.AmsDao.*;
import com.model.Carrier;
public class CarrierDAO {
//    private String jdbcURL = "jdbc:mysql://localhost:3306/airline";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "password";

    private static final String SELECT_CARRIER_BY_NAME = "SELECT * FROM carrier WHERE carrierName = ?";
    private static final String SELECT_ALL_CARRIERS = "SELECT * FROM carrier";
    private static final String UPDATE_CARRIER = "UPDATE carrier SET Days30=?,Days60=?, Days90=?, bulkBooking=?, silverUser=?, goldUser=?, platinumUser=?, DaysBeforeTravelDate2=?, DaysBeforeTravelDate10=?, DaysOrMoreBeforeTravelDate20=?, Status=? WHERE carrierName=?";
    private static final String DELETE_CARRIER = "DELETE FROM carrier WHERE carrierName=?";
    
    
    
    public Connection getConnection() throws SQLException {
    	System.out.println("Get connection called");
        Connection conn = AmsDao.getConnection();
        return conn;
    }
    
    public Carrier getCarrierById(int carrierId) throws SQLException {
        String sql = "SELECT * FROM Carrier WHERE carrierId = ?";
        Carrier carrier = null;

        try (Connection conn = getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the carrierId parameter in the query
            pstmt.setInt(1, carrierId);
            
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            
            // If a carrier is found, map the result set to the Carrier object
            if (rs.next()) {
            	carrier = new Carrier(rs.getString("carrierName"),  rs.getInt("Days30"),rs.getInt("Days60"),
                        rs.getInt("Days90"), rs.getInt("bulkBooking"), rs.getInt("silverUser"),
                        rs.getInt("goldUser"), rs.getInt("platinumUser"), rs.getInt("DaysBeforeTravelDate2"),
                        rs.getInt("DaysBeforeTravelDate10"), rs.getInt("DaysOrMoreBeforeTravelDate20"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // rethrow the exception
        }

        return carrier;
    }


    public Carrier getCarrierByName(String carrierName) {
    	
        Carrier carrier = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARRIER_BY_NAME)) {
            preparedStatement.setString(1, carrierName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                carrier = new Carrier(rs.getString("carrierName"),  rs.getInt("Days30"),rs.getInt("Days60"),
                        rs.getInt("Days90"), rs.getInt("bulkBooking"), rs.getInt("silverUser"),
                        rs.getInt("goldUser"), rs.getInt("platinumUser"), rs.getInt("DaysBeforeTravelDate2"),
                        rs.getInt("DaysBeforeTravelDate10"), rs.getInt("DaysOrMoreBeforeTravelDate20"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carrier;
    }

    public List<Carrier> getAllCarriers() {
        List<Carrier> carriers = new ArrayList<>();
        Connection connection = null;
        
        try {
            connection = getConnection();  // Establish connection
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARRIERS);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Carrier carrier = new Carrier(rs.getString("carrierName"), rs.getInt("Days30"), rs.getInt("Days60"),
                            rs.getInt("Days90"), rs.getInt("bulkBooking"), rs.getInt("silverUser"),
                            rs.getInt("goldUser"), rs.getInt("platinumUser"), rs.getInt("DaysBeforeTravelDate2"),
                            rs.getInt("DaysBeforeTravelDate10"), rs.getInt("DaysOrMoreBeforeTravelDate20"));
                    carriers.add(carrier);
                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return carriers;
    }
    
    public List<Carrier> getAllCarriersNames() {
    	System.out.println("Try to ftech carrier names");
        List<Carrier> carriers = new ArrayList<>();
        String query = "SELECT carrierName FROM Carrier";
        
        try (Connection connection = AmsDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Carrier carrier = new Carrier();
                carrier.setCarrierName(rs.getString("carrierName"));
                carriers.add(carrier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return carriers;
    }


    public void updateCarrier(Carrier carrier) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            // Step 1: Update the carrier details
            preparedStatement = connection.prepareStatement(UPDATE_CARRIER);
            preparedStatement.setInt(1, carrier.get_30DaysAdvanceBooking());
            preparedStatement.setInt(2, carrier.get_60DaysAdvanceBooking());
            preparedStatement.setInt(3, carrier.get_90DaysAdvanceBooking());
            preparedStatement.setInt(4, carrier.getBulkBooking());
            preparedStatement.setInt(5, carrier.getSilverUser());
            preparedStatement.setInt(6, carrier.getGoldUser());
            preparedStatement.setInt(7, carrier.getPlatinumUser());
            preparedStatement.setInt(8, carrier.get_2DaysBeforeTravelDate());
            preparedStatement.setInt(9, carrier.get_10DaysBeforeTravelDate());
            preparedStatement.setInt(10, carrier.get_20DaysOrMoreBeforeTravelDate());
            preparedStatement.setString(11, carrier.getCarrierStatus());
            preparedStatement.setString(12, carrier.getCarrierName());

            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println(rowsUpdated + " carrier(s) updated.");

            // Step 2: If the carrier status is cancelled, update the flights as well
            if ("Cancel".equalsIgnoreCase(carrier.getCarrierStatus())) {
                // Retrieve the carrier ID
                String getCarrierIdSQL = "SELECT carrierid FROM carrier WHERE carriername = ?";
                try (PreparedStatement idStmt = connection.prepareStatement(getCarrierIdSQL)) {
                    idStmt.setString(1, carrier.getCarrierName());
                    resultSet = idStmt.executeQuery();

                    if (resultSet.next()) {
                        int carrierId = resultSet.getInt("carrierid");

                        // Update all flights associated with this carrier
                        String updateFlightsSQL = "UPDATE flight SET status = 'Cancel' WHERE carrierid = ?";
                        try (PreparedStatement flightStmt = connection.prepareStatement(updateFlightsSQL)) {
                            flightStmt.setInt(1, carrierId);
                            int flightsUpdated = flightStmt.executeUpdate();
                            System.out.println(flightsUpdated + " flight(s) cancelled due to carrier cancellation.");
                        }
                    } else {
                        System.out.println("Carrier not found.");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Ensure the resources are closed
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
    private static final String GET_CARRIER_ID_BY_NAME = "SELECT carrierId FROM Carrier WHERE carrierName = ?";

    public int getCarrierIdByName(String carrierName) {
        int carrierId = -1;
        try (Connection connection = AmsDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CARRIER_ID_BY_NAME)) {
            preparedStatement.setString(1, carrierName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                carrierId = rs.getInt("carrierId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carrierId;
    }

    public void deleteCarrier(String carrierName) {
        try {Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARRIER);
            preparedStatement.setString(1, carrierName);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
