package hkr.eduard.rooms;

public class Room {
    private int availableRooms;
    private boolean doubleBed;
    private int numberOfBeds;
    private double price;
    private double size;
    private boolean balcony;
    private String bookingId;

    public Room(int availableRooms, boolean doubleBed, int numberOfBeds, double price, double size, boolean balcony, String bookingId) {
        this.availableRooms = availableRooms;
        this.doubleBed = doubleBed;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.size = size;
        this.balcony = balcony;
        this.bookingId = bookingId;
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

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
