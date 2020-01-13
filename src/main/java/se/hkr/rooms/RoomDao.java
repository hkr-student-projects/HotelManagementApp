package se.hkr.rooms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.hkr.data.ConnectionProvider;
import se.hkr.data.Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RoomDao extends Dao {
    public RoomDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public void getAvailableRooms(LocalDate movein, LocalDate moveout, Consumer<ObservableList<String>> rooms) {
        select(resultSet -> {
            List<String> data = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    data.add(resultSet.getString("Room"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                rooms.accept(FXCollections.observableArrayList(data));
            }
        }, "SELECT `Room_number` As Room\n" +
                "FROM BookedRoom, Booking \n" +
                "WHERE BookedRoom.Booking_reference = Booking.reference AND `Room_number` NOT IN\n" +
                "(SELECT `Room_number`\n" +
                "FROM BookedRoom, Booking \n" +
                "WHERE BookedRoom.Booking_reference = Booking.reference AND\n" +
                "(\t\n" +
                "\t((? >= `movein` AND ? < `moveout`) OR (? >= `movein` AND ? <= `moveout`)) \n" +
                "    OR \n" +
                "\t(`movein` >= ? AND `moveout` <= ?)\n" +
                "))\n" +
                "UNION SELECT `number`\n" +
                "FROM Room \n" +
                "WHERE `number` \n" +
                "NOT IN (SELECT `Room_number` \n" +
                "FROM BookedRoom)\n" +
                "ORDER BY Room;\n", movein.toString(), movein.toString(), moveout.toString(), moveout.toString(), movein.toString(), moveout.toString());
    }
}
