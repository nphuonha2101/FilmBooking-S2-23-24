package com.filmbooking.utils;

import com.filmbooking.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtils {
    private static HibernateUtils instance;
    private final SessionFactory sessionFactory;

    private HibernateUtils() {
        sessionFactory = buildSessionFactory();
    }

    public static HibernateUtils getInstance() {
        if (instance == null)
            instance = new HibernateUtils();
        return instance;
    }

    /**
     * This {@link SessionFactory} must be created once and only one
     * @return {@link SessionFactory}
     */
    private SessionFactory buildSessionFactory() {
        Configuration configuration =
                new org.hibernate.cfg.Configuration().setProperties(PropertiesUtils.getInstance().getProperties());

        // add all Entity to Hibernate Config
        configuration.addAnnotatedClass(Film.class);
        configuration.addAnnotatedClass(FilmBooking.class);
        configuration.addAnnotatedClass(Genre.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(Showtime.class);
        configuration.addAnnotatedClass(Theater.class);
        configuration.addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(standardServiceRegistryBuilder.build());
    }

    /**
     * Open session of hibernate
     * @return {@link Session}
     */
    public Session openSession() {
        return this.sessionFactory.openSession();
    }

}
