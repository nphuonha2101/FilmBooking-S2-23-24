package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements IDAO<Room> {
    private static final String TABLE_NAME = "room";
    private final DatabaseConnection databaseConnection;
    private final List<Room> roomList;

    public RoomDAOImpl() {
        this.roomList = new ArrayList<>();
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    @Override
    public List<Room> getAll() {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetAll = "SELECT * FROM " + TABLE_NAME;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String roomID = resultSet.getString("room_id");
                String roomName = resultSet.getString("room_name");
                int seatRows = resultSet.getInt("seat_rows");
                int seatCols = resultSet.getInt("seat_cols");
                String seatData = resultSet.getString("seats_data");
                String theaterID = resultSet.getString("theater_id");

                Room newRoom = new Room(roomID, roomName, seatRows, seatCols, seatData, theaterID);

                roomList.add(0, newRoom);

            }
            resultSet.close();
            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return roomList;
    }

    @Override
    public Room getByID(String id) {
        getAll();

        for (Room room : roomList
        ) {
            if (room.getRoomID().equalsIgnoreCase(id))
                return room;
        }
        return null;
    }

    @Override
    public void save(Room room) {
        int largestID = this.getAll().isEmpty() ? 0 : Integer.parseInt(this.getAll().get(0).getRoomID());
        for (Room r : this.getAll()) {
            if (Integer.parseInt(r.getRoomID()) > largestID)
                largestID = Integer.parseInt(r.getRoomID());
        }
        largestID++;
        room.setRoomID(String.valueOf(largestID));

        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String querySave = "INSERT INTO " + TABLE_NAME + "(room_id, room_name, seat_rows, seat_cols, seats_data, theater_id)" +
                " VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySave);

            preparedStatement.setString(1, room.getRoomID());
            preparedStatement.setString(2, room.getRoomName());
            preparedStatement.setInt(3, room.getSeatRows());
            preparedStatement.setInt(4, room.getSeatCols());
            preparedStatement.setString(5, room.getSeatData());
            preparedStatement.setString(6, room.getTheaterID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Room room) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryUpdate = "UPDATE " + TABLE_NAME +
                " SET room_name = ?, seat_rows = ?, seat_cols = ?, seats_data = ?, theater_id = ? WHERE room_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);


            preparedStatement.setString(1, room.getRoomName());
            preparedStatement.setInt(2, room.getSeatRows());
            preparedStatement.setInt(3, room.getSeatCols());
            preparedStatement.setString(4, room.getSeatData());
            preparedStatement.setString(5, room.getTheaterID());

            preparedStatement.setString(6, room.getRoomID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Room room) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryDelete = "DELETE FROM " + TABLE_NAME + " WHERE room_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);

            preparedStatement.setString(1, room.getRoomID());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}
