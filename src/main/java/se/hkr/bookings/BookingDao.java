package se.hkr.bookings;

import se.hkr.data.ConnectionProvider;
import se.hkr.data.Dao;

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
        }, "SELECT `Room_number`,`reference`,`Order`.`id` AS oid,`guests`,`movein`,`moveout` " +
                "FROM Customer, Order, Booking, BookedRoom " +
                "WHERE Customer.id = Order.Customer_id AND Order.Customer_id = ? AND " +
                "Order.Booking_reference = Booking.reference AND " +
                "Booking.reference = BookedRoom.Booking_reference;", cusId);
    }
}
