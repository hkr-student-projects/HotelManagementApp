package com.company;

public class Room {
    private int availableRooms;
    private boolean doubleBed;
    private int numberOfBeds;
    private double price;
    private double size;
    private boolean balcony;
    public Room(int availableRooms, boolean doubleBed, int numberOfBeds, double price, double size,boolean balcony) {
        this.availableRooms = availableRooms;
        this.doubleBed = doubleBed;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.size = size;
        this.balcony = balcony;
    }

    public boolean isDoubleBed() {

        return doubleBed;
    }

    public void setDoubleBed(boolean doubleBed) {
        this.doubleBed = doubleBed;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    @Override
    public String toString() {
        return "Room No = "+ availableRooms +
                " Double Bed = " + doubleBed +
                " Number Of Beds = " + numberOfBeds +
                " Price = " + price +" sek"+
                " Size = " + size+" meter square"+
                " Balcony = "+balcony;
    }

}
