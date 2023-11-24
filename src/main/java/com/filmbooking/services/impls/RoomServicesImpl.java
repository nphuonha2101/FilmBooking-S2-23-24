package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.RoomDAOImpl;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IGetObjectAndObjectIDService;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.IShowtimeServices;

import java.util.List;

public class RoomServicesImpl implements IRoomServices, IGetObjectAndObjectIDService<Room> {
    private IDAO<Room> roomDAO;
    private IShowtimeServices showtimeServices;
    private IFilmBookingServices filmBookingServices;

    public RoomServicesImpl() {
        roomDAO = new RoomDAOImpl();
        showtimeServices = new ShowtimeServicesImpl();
        filmBookingServices = new FilmBookingServicesImpl();
    }

    @Override
    public List<Room> getAll() {
        return roomDAO.getAll();
    }

    @Override
    public Room getByRoomID(String id) {
        return roomDAO.getByID(id);
    }

    @Override
    public void save(Room room) {
        roomDAO.save(room);
    }

    @Override
    public void update(Room room) {
        roomDAO.update(room);
    }

    @Override
    public void delete(Room room) {
        List<Showtime> showtimeList = showtimeServices.getAll();
        List<FilmBooking> filmBookingList = filmBookingServices.getAll();

        filmBookingList.stream().forEach(filmBooking -> {
            Showtime showtimeInFilmBooking = showtimeServices.getByID(filmBooking.getShowtimeID());
            if (showtimeInFilmBooking.getRoomID().equalsIgnoreCase(room.getRoomID()))
                filmBookingServices.delete(filmBooking);
        });

        showtimeList.stream().forEach(showtime -> {
            if (showtime.getRoomID().equalsIgnoreCase(room.getRoomID())) showtimeServices.delete(showtime);
        });
        roomDAO.delete(room);
    }


    @Override
    public String getObjectID(Room obj) {
        return obj.getRoomID();
    }
}
