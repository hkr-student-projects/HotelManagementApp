package hkr;

public class Hotel {
    private String availableRooms;
    private String booking;
    private String bookingReference;
    private int bookingCounter;

    public Hotel(String availableRooms, String booking, String bookingReference,int bookingCounter) {
        this.availableRooms = availableRooms;
        this.booking = booking;
        this.bookingReference = bookingReference;
        this.bookingCounter = bookingCounter;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(String availableRooms) {
        this.availableRooms = availableRooms;
    }

    public String getBooking() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public int getBookingCounter() {
        return bookingCounter;
    }

    public void setBookingCounter(int bookingCounter) {
        this.bookingCounter = bookingCounter;
    }

    @Override
    public String toString() {
        return "Hotel Rooms=" + availableRooms +
                ", Booking=" + bookingReference ;
    }
}
