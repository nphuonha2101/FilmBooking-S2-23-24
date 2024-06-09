package com.filmbooking.services.impls;

import java.util.Objects;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Genre;
import com.filmbooking.model.User;
import com.filmbooking.repository.AbstractRepository;
import com.filmbooking.repository.GenreRepository;
import com.filmbooking.services.AbstractService;
import com.filmbooking.services.IGenreService;

public class GenreServicesImpl extends AbstractService<Genre> implements IGenreService {

    protected GenreServicesImpl(AbstractRepository<Genre> repository) {
        super(new GenreRepository(Genre.class));
    }

    
}
