package com.filmbooking.services.logProxy;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.IModel;
import com.filmbooking.model.LogModel;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractCRUDServices;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class FilmServicesLogProxy<T extends IModel> extends AbstractServicesLogProxy<T> implements IFilmServices {
    private final FilmServicesImpl filmServices;

    public FilmServicesLogProxy(FilmServicesImpl filmServices, HttpServletRequest req, HibernateSessionProvider sessionProvider) {
        super(req);
        this.filmServices = filmServices;
        this.logModelServices.setSessionProvider(sessionProvider);
        this.filmServices.setSessionProvider(sessionProvider);
    }

    @Override
    public boolean update(Film film, String... genreIDs) {
        LogModel logModel = buildLogModel(LogModel.UPDATE, (T) film, (AbstractCRUDServices<T>) filmServices, true);

        boolean updateState = filmServices.update(film, genreIDs);
        logModel.setActionSuccess(updateState);
        logModelServices.save(logModel);

        return updateState;
    }

    @Override
    public boolean save(Film film, String... genreIDs) {
        LogModel logModel = buildLogModel(LogModel.INSERT, (T) film, (AbstractCRUDServices<T>) filmServices, true);

        boolean saveState = filmServices.save(film, genreIDs);
        logModel.setActionSuccess(saveState);
        logModelServices.save(logModel);

        return saveState;
    }
}
