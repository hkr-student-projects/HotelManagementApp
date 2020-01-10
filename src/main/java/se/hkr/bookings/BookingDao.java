package se.hkr.bookings;

import se.hkr.data.ConnectionProvider;
import se.hkr.data.Dao;

import java.time.LocalDate;

public class BookingDao extends Dao {
    public BookingDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public void addEntry(String ssn, String name, String surname, String addr, String phone, LocalDate movein, LocalDate moveout, String roomnum) {
        insert("INSERT INTO `Booking` " +
                "(`movein`,`moveout`) " +
                "VALUES ('?','?');" +
                "SELECT @bid:=LAST_INSERT_ID();" +
                "INSERT INTO `Customer` " +
                "(`ssn`,`name`,`surname`,`address`,`phone`) " +
                "VALUES ('?','?','?','?','phone');" +
                "SELECT @cid:=LAST_INSERT_ID();" +
                "INSERT INTO `CustomerOrder` " +
                "(`Customer_id`,`Booking_reference`) " +
                "VALUES (@cid,@bid);" +
                "INSERT INTO `BookedRoom`" +
                "(`Booking_reference`,`Room_number`) " +
                "VALUES (@bid,'" + roomnum + "');", movein.toString(), moveout.toString(), ssn, name, surname, addr, phone);
    }
}
