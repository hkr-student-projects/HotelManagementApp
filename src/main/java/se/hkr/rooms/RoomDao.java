package se.hkr.rooms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.hkr.data.ConnectionProvider;
import se.hkr.data.Dao;

import java.time.LocalDate;
import java.util.function.Consumer;

public class RoomDao extends Dao {
    public RoomDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public void getAvailableRooms(LocalDate movein, LocalDate moveout, Consumer<ObservableList<String>> rooms) {
        select(resultSet -> {
            ObservableList<String> data = FXCollections.emptyObservableList();
            try {
                while (resultSet.next()) {
                    data.add(resultSet.getString("Room_number"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                rooms.accept(data);
            }
        }, "SELECT `Room_number` As Room\n" +
                "FROM BookedRoom, Booking \n" +
                "WHERE BookedRoom.Booking_reference = Booking.reference AND `Room_number` NOT IN\n" +
                "(SELECT `Room_number`\n" +
                "FROM BookedRoom, Booking \n" +
                "WHERE BookedRoom.Booking_reference = Booking.reference AND\n" +
                "((('?' >= `movein` AND '?' < `moveout`) OR ('?' >= `movein` AND '?' <= `moveout`)) OR (`movein` >= '?' AND `moveout` <= '?')))\n" +
                "UNION SELECT `number`\n" +
                "FROM Room \n" +
                "WHERE `number` \n" +
                "NOT IN (SELECT `Room_number`)\n" +
                "ORDER BY Room;", movein.toString(), movein.toString(), moveout.toString(), moveout.toString(), movein.toString(), moveout.toString());
    }
}
