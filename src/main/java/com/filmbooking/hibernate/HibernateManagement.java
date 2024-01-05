package com.filmbooking.hibernate;

import com.filmbooking.model.*;
import com.filmbooking.utils.PropertiesUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateManagement {
    private static HibernateManagement instance;
    private final SessionFactory sessionFactory;

    private HibernateManagement() {
        sessionFactory = buildSessionFactory();
    }

    public static HibernateManagement getInstance() {
        if (instance == null)
            synchronized (HibernateManagement.class) {
                if (instance == null)
                    instance = new HibernateManagement();
            }
        return instance;
    }

    /**
     * This {@link SessionFactory} must be created once and only one
     *
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
        configuration.addAnnotatedClass(FilmVote.class);

        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(standardServiceRegistryBuilder.build());
    }

    /**
     * Get {@link SessionFactory} of hibernate
     *
     * @return {@link Session}
     */
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

}
