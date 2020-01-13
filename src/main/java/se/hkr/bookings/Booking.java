package se.hkr.bookings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Booking{
    private int bId;
    private int orId;
    private byte guests;
    private String room;
    private LocalDate movein;
    private LocalDate moveout;

    public Booking(int bId, int orId, byte guests, String room, LocalDate movein, LocalDate moveout) {
        this.bId = bId;
        this.orId = orId;
        this.guests = guests;
        this.room = room;
        this.movein = movein;
        this.moveout = moveout;
    }

    public Booking(ResultSet resultSet) throws SQLException {
        this(
                resultSet.getInt(2),
                resultSet.getInt(3),
                (byte) resultSet.getInt(4),
                resultSet.getString(1),
                resultSet.getDate(5).toLocalDate(),
                resultSet.getDate(6).toLocalDate()
        );
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getOrId() {
        return orId;
    }

    public void setOrId(int orId) {
        this.orId = orId;
    }

    public byte getGuests() {
        return guests;
    }

    public void setGuests(byte guests) {
        this.guests = guests;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalDate getMovein() {
        return movein;
    }

    public void setMovein(LocalDate movein) {
        this.movein = movein;
    }

    public LocalDate getMoveout() {
        return moveout;
    }

    public void setMoveout(LocalDate moveout) {
        this.moveout = moveout;
    }

}