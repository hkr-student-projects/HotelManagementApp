package com.company;

import java.util.Date;

public class Booking extends Room {
    private String bookingReference;
    private Date checkIn;
    private Date checkOut;

    public Booking(int availableRooms, boolean doubleBed, int numberOfBeds, double price, double size,boolean balcony, String bookingReference,Date checkIn,Date checkOut) {
        super(availableRooms, doubleBed, numberOfBeds, price, size,balcony);
        this.bookingReference = bookingReference;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }


    @Override
    public String toString() {
        return super.toString()+
                " Booking Reference = " + bookingReference+
                " CheckIn Date = "+checkIn+
                " Checkout Date = "+checkOut;
    }
}
