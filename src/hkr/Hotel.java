package hkr;

public class Hotel {
    private String availableRooms;
    private String booking;
    private int bookingCounter;

    public Hotel(String availableRooms, String booking,int bookingCounter) {
        this.availableRooms = availableRooms;
        this.booking = booking;
        this.bookingCounter = bookingCounter;
    }

    public String getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(String availableRooms) {
        this.availableRooms = availableRooms;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        booking = booking;
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
                ", Booking=" + booking ;
    }
}
