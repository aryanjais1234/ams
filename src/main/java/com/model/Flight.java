package com.model;

public class Flight {
    private int flightId;
    private int carrierId;
    private String origin;
    private String destination;
    private double airFare;
    private String status;
    private int businessClassSeats;
    private int economyClassSeats;
    private int executiveClassSeats;

    // Getters and Setters
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getAirFare() {
        return airFare;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public void setAirFare(double airFare) {
        this.airFare = airFare;
    }
    
    

    public int getBusinessClassSeats() {
        return businessClassSeats;
    }

    public void setBusinessClassSeats(int businessClassSeats) {
        this.businessClassSeats = businessClassSeats;
    }

    public int getEconomyClassSeats() {
        return economyClassSeats;
    }

    public void setEconomyClassSeats(int economyClassSeats) {
        this.economyClassSeats = economyClassSeats;
    }

    public int getExecutiveClassSeats() {
        return executiveClassSeats;
    }

    public void setExecutiveClassSeats(int executiveClassSeats) {
        this.executiveClassSeats = executiveClassSeats;
    }
    
    public String getStatus() {
    	return this.status;
    }
}