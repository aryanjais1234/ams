package com.model;

public class Carrier {
    private String carrierName;
    private int _30DaysAdvanceBooking;
    private int _60DaysAdvanceBooking;
    private int _90DaysAdvanceBooking;
    private int bulkBooking;
    private int silverUser;
    private int goldUser;
    private int platinumUser;
    private int _2DaysBeforeTravelDate;
    private int _10DaysBeforeTravelDate;
    private int _20DaysOrMoreBeforeTravelDate;
    private String status;

    public Carrier() {
    }

    public Carrier(String carrierName, int _30DaysAdvanceBooking ,int _60DaysAdvanceBooking, int _90DaysAdvanceBooking, int bulkBooking,
                   int silverUser, int goldUser, int platinumUser, int _2DaysBeforeTravelDate,
                   int _10DaysBeforeTravelDate, int _20DaysOrMoreBeforeTravelDate) {
        this.carrierName = carrierName;
        this._30DaysAdvanceBooking = _30DaysAdvanceBooking;
        this._60DaysAdvanceBooking = _60DaysAdvanceBooking;
        this._90DaysAdvanceBooking = _90DaysAdvanceBooking;
        this.bulkBooking = bulkBooking;
        this.silverUser = silverUser;
        this.goldUser = goldUser;
        this.platinumUser = platinumUser;
        this._2DaysBeforeTravelDate = _2DaysBeforeTravelDate;
        this._10DaysBeforeTravelDate = _10DaysBeforeTravelDate;
        this._20DaysOrMoreBeforeTravelDate = _20DaysOrMoreBeforeTravelDate;
    }

    // Getters and Setters
    
    public String getCarrierStatus() {
    	return this.status;
    }
    public void setCarrierStatus(String status) {
    	this.status = status;
    }
    
    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public int get_30DaysAdvanceBooking() {
        return _30DaysAdvanceBooking;
    }
    public void set_30DaysAdvanceBooking(int _30DaysAdvanceBooking) {
    	this._30DaysAdvanceBooking = _30DaysAdvanceBooking;
    }
    public int get_60DaysAdvanceBooking() {
    	return _60DaysAdvanceBooking;
    }

    public void set_60DaysAdvanceBooking(int _60DaysAdvanceBooking) {
        this._60DaysAdvanceBooking = _60DaysAdvanceBooking;
    }

    public int get_90DaysAdvanceBooking() {
        return _90DaysAdvanceBooking;
    }

    public void set_90DaysAdvanceBooking(int _90DaysAdvanceBooking) {
        this._90DaysAdvanceBooking = _90DaysAdvanceBooking;
    }

    public int getBulkBooking() {
        return bulkBooking;
    }

    public void setBulkBooking(int bulkBooking) {
        this.bulkBooking = bulkBooking;
    }

    public int getSilverUser() {
        return silverUser;
    }

    public void setSilverUser(int silverUser) {
        this.silverUser = silverUser;
    }

    public int getGoldUser() {
        return goldUser;
    }

    public void setGoldUser(int goldUser) {
        this.goldUser = goldUser;
    }

    public int getPlatinumUser() {
        return platinumUser;
    }

    public void setPlatinumUser(int platinumUser) {
        this.platinumUser = platinumUser;
    }

    public int get_2DaysBeforeTravelDate() {
        return _2DaysBeforeTravelDate;
    }

    public void set_2DaysBeforeTravelDate(int _2DaysBeforeTravelDate) {
        this._2DaysBeforeTravelDate = _2DaysBeforeTravelDate;
    }

    public int get_10DaysBeforeTravelDate() {
        return _10DaysBeforeTravelDate;
    }

    public void set_10DaysBeforeTravelDate(int _10DaysBeforeTravelDate) {
        this._10DaysBeforeTravelDate = _10DaysBeforeTravelDate;
    }

    public int get_20DaysOrMoreBeforeTravelDate() {
        return _20DaysOrMoreBeforeTravelDate;
    }

    public void set_20DaysOrMoreBeforeTravelDate(int _20DaysOrMoreBeforeTravelDate) {
        this._20DaysOrMoreBeforeTravelDate = _20DaysOrMoreBeforeTravelDate;
    }
}
