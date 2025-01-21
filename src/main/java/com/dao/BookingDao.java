package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.model.Booking;

public class BookingDao {

    private static final String INSERT_BOOKING_SQL = "INSERT INTO FlightBooking (flightId, userId, noOfSeats, seatCategory, dateOfTravel, bookingStatus, bookingAmount) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public Connection getConnection() throws SQLException {
        return AmsDao.getConnection();
    }

    public void addBooking(Booking booking) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING_SQL)) {
            preparedStatement.setInt(1, booking.getFlightId());
            preparedStatement.setInt(2, booking.getUserId());
            preparedStatement.setInt(3, booking.getNoOfSeats());
            preparedStatement.setString(4, booking.getSeatCategory());
            preparedStatement.setString(5, booking.getDateOfTravel());
            preparedStatement.setString(6, booking.getBookingStatus());
            preparedStatement.setDouble(7, booking.getBookingAmount());

            preparedStatement.executeUpdate();
        }
    }
}