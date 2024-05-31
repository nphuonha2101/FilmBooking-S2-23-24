package com.filmbooking.services.impls;

import java.util.HashMap;
import java.util.Map;

import com.filmbooking.model.Showtime;
import com.filmbooking.repository.ShowtimeRepository;
import com.filmbooking.services.AbstractService;
import com.filmbooking.services.IShowtimeServices;

public class ShowtimeServicesImpl extends AbstractService<Showtime> implements IShowtimeServices {

    public ShowtimeServicesImpl() {
        super(new ShowtimeRepository(Showtime.class));
    }

    /**
     * Get a map storing showtimeID and its available seats
     *
     * @return HashMap<Long, Integer> showtimeID and its available seats
     */
    public Map<Long, Integer> getAvailableSeatsByShowtimeId() {
        HashMap<Long, Integer> result = new HashMap<>();

        for (Showtime showtime : this.selectAll()
        ) {
            int availableSeats = showtime.countAvailableSeats();
            result.put(showtime.getShowtimeID(), availableSeats);
        }
        return result;
    }

    /**
     * Get a map storing showtimeID and its seat matrix
     *
     * @return HashMap<Long, String [ ] [ ]> showtimeID and its seat matrix
     */
    public HashMap<Long, String[][]> getShowtimeIDAndSeatMatrix() {
        HashMap<Long, String[][]> result = new HashMap<>();

        for (Showtime showtime : this.selectAll()
        ) {
            String[][] seatsMatrix = showtime.getSeatsMatrix();
            result.put(showtime.getShowtimeID(), seatsMatrix);
        }
        return result;
    }




}
