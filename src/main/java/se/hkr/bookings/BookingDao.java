package se.hkr.bookings;

import se.hkr.data.ConnectionProvider;
import se.hkr.data.Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BookingDao extends Dao {
    public BookingDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public void getBookings(int cusId, Consumer<List<Booking>> bookingsConsumer) {
        select(resultSet -> {
            List<Booking> bookings = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    bookings.add(new Booking(resultSet));
                }
                bookingsConsumer.accept(bookings);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, "SELECT `Room_number`, `reference`, `Order`.`id`, `guests`, `movein`, `moveout` " +
                "FROM `Customer`, `Order`, `Booking`, `BookedRoom` WHERE Customer.id = `Order`.Customer_id " +
                "AND `Order`.Customer_id = ? AND `Order`.Booking_reference = Booking.reference " +
                "AND Booking.reference = BookedRoom.Booking_reference;", cusId);
    }

    public void createBooking(int cusId, LocalDate in, LocalDate out, int guests, String room) {
        insert("INSERT INTO `Booking` (`guests`,`movein`,`moveout`) VALUES (?,?,?);", rowCount -> {
            insert("INSERT INTO `BookedRoom` " +
                    "(`Booking_reference`,`Room_number`) " +
                    "VALUES (?,?);", rowCount, room);
            insert("INSERT INTO `Order` " +
                    "(`Customer_id`,`Booking_reference`) " +
                    "VALUES (?,?);", cusId, rowCount);
        }, guests, in.toString(), out.toString());
    }

    public void deleteBooking(int refId) {
        delete("DELETE FROM `Booking` WHERE `reference`= ?;", refId);
    }

    public void updateBooking(int bookId, LocalDate in, LocalDate out, int guests, String room) {
        update("UPDATE `Booking` " +
                "SET `movein` = ?, `moveout` = ?, `guests` = ? " +
                "WHERE Booking.reference = ?;", in.toString(), out.toString(), guests, bookId);
        update("UPDATE `BookedRoom` " +
                "SET `Room_number` = ? " +
                "WHERE BookedRoom.Booking_reference = ?;", room, bookId);
    }
}
