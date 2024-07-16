package com.filmbooking.services.logProxy;

import com.filmbooking.model.Film;
import com.filmbooking.model.IModel;
import com.filmbooking.model.LogModel;
import com.filmbooking.services.AbstractService;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class FilmServicesLogProxy extends AbstractServicesLogProxy<Film> implements IFilmServices {
    private final FilmServicesImpl filmServices;

    public FilmServicesLogProxy(FilmServicesImpl filmServices, HttpServletRequest req) {
        super(req, Film.class);
        this.filmServices = filmServices;
//        this.logModelServices.setSessionProvider(sessionProvider);
//        this.filmServices.setSessionProvider(sessionProvider);
    }

    @Override
    public boolean update(Film film, String... genreIDs) {
        LogModel logModel = buildLogModel(LogModel.UPDATE, film, filmServices, true);

        boolean updateState = filmServices.update(film, genreIDs);
//        boolean updateState = filmServices.update(film);

        logModel.setActionSuccess(updateState);
        logModelServices.insert(logModel);

        return updateState;
    }

    @Override
    public boolean save(Film film, String... genreIDs) {
        LogModel logModel = buildLogModel(LogModel.INSERT, film, filmServices, true);

        boolean saveState = filmServices.insert(film, genreIDs);
        logModel.setActionSuccess(saveState);
        logModelServices.insert(logModel);

        return saveState;
    }
}
