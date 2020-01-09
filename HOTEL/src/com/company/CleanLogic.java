package com.company;

public class CleanLogic { private int roomNumber;
    private boolean cleaned;

    public CleanLogic(int roomNumber,boolean cleaned) {
        this.roomNumber = roomNumber;
        this.cleaned = cleaned;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public void setCleaned(boolean cleaned) {
        cleaned = cleaned;
    }

    @Override
    public String toString() {
        return "Room Number = " + roomNumber +
                ", Cleaned = " + cleaned;
    }
}

