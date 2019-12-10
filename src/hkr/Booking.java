package hkr;

public class Booking {
    private String bookingReference;
    private int bookingCounter;

    public Booking(String bookingReference, int bookingCounter) {
        this.bookingReference = bookingReference;
        this.bookingCounter = bookingCounter;
    }

    public String getBookingReference() {
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
}
