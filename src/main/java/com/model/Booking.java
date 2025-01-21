package com.model;

import java.util.Date;

public class Booking {

    private int bookingId;
    private int flightId;
    private int userId;
    private int noOfSeats;
    private String seatCategory;
    private String dateOfTravel;
    private String bookingStatus;
    private double bookingAmount;

    // Getters and Setters

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }
    
    public String getFlightStatus() {
    	return bookingStatus;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(String seatCategory) {
        this.seatCategory = seatCategory;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public double getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(double bookingAmount) {
        this.bookingAmount = bookingAmount;
    }
    public void setFlightStatus(String status) {
    	this.bookingStatus = status;
    }
    
    // Constructor with parameters
    public Booking(int flightId, int userId, int noOfSeats, String seatCategory, String dateOfTravel, String bookingStatus, int bookingAmount) {
        this.flightId = flightId;
        this.userId = userId;
        this.noOfSeats = noOfSeats;
        this.seatCategory = seatCategory;
        this.dateOfTravel = dateOfTravel;
        this.bookingStatus = bookingStatus;
        this.bookingAmount = bookingAmount;
    }

    // Default constructor
    public Booking() {
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", flightId=" + flightId +
                ", userId=" + userId +
                ", noOfSeats=" + noOfSeats +
                ", seatCategory='" + seatCategory + '\'' +
                ", dateOfTravel=" + dateOfTravel +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", bookingAmount=" + bookingAmount +
                '}';
    }
}