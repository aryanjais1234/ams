package com.model;

import java.util.Date;

public class User {
	private int userId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String email;
    private String address;
    private String contactNumber;

    // Constructor
    public User(int userId, String firstName, String lastName, Date dob, String email, String address, String contactNumber) {
        this.userId = userId;
    	this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    // Default constructor
    public User() {}

    // Getters
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", email=" + email
                + ", address=" + address + ", contactNumber=" + contactNumber + "]";
    }
}
