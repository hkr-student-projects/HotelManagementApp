package hkr;

import java.io.Serializable;

public class Room implements Serializable {
    private String availableRooms;
    private boolean doubleBed;
    private int numberOfBeds;
    private double price;
    private double size;

    public Room(String availableRooms, boolean doubleBed, int numberOfBeds, double price, double size) {
        this.availableRooms = availableRooms;
        this.doubleBed = doubleBed;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.size = size;
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

    public String getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(String availableRooms) {
        this.availableRooms = availableRooms;
    }

    @Override
    public String toString() {
        return "Room{" +
                "availableRooms='" + availableRooms + '\'' +
                ", doubleBed=" + doubleBed +
                ", numberOfBeds=" + numberOfBeds +
                ", price=" + price +
                ", size=" + size +
                '}';
    }
}