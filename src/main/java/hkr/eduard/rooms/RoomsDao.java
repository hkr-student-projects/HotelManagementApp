package hkr.eduard.rooms;

import hkr.eduard.database.ConnectionProvider;
import hkr.eduard.database.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RoomsDao extends Dao {
    public RoomsDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public void selectAllRooms(Consumer<List<Room>> roomsConsumer) {
        List<Room> rooms = new ArrayList<>();
        select(resultReader -> {
            while (resultReader.hasNext()) {
                rooms.add(new Room(
                        resultReader.readInt("available_rooms"),
                        resultReader.readInt("double_bed") == 1,
                        resultReader.readInt("number_of_beds"),
                        resultReader.readDouble("price"),
                        resultReader.readDouble("size"),
                        resultReader.readInt("balcony") == 1,
                        resultReader.readString("booking_id")
                        ));
            }

            roomsConsumer.accept(rooms);
        }, "SELECT * FROM rooms;");
    }
}
