package hkr;

public class Hotel {
    private String availableRooms;
    private boolean Cleaned;

    public Hotel(String availableRooms) {
        this.availableRooms = availableRooms;
    }

    public String getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(String availableRooms) {
        this.availableRooms = availableRooms;
    }


    public boolean isCleaned() {
        return Cleaned;
    }

    public void setCleaned(boolean cleaned) {
        Cleaned = cleaned;
    }
}
