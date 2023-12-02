package com.filmbooking.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateSessionProvider {
    private final SessionFactory sessionFactory;
    private Session session;

    /**
     * Create a new {@link HibernateSessionProvider} to provide {@link Session} from {@link SessionFactory}
     *
     * @param autoOpenSession if true, open a {@link Session} from {@link SessionFactory}.
     *                        If false, don't open a {@link Session} from {@link SessionFactory}, you must open it manually with {@link HibernateSessionProvider#openSession()}
     */
    public HibernateSessionProvider(boolean autoOpenSession) {
        sessionFactory = HibernateManagement.getInstance().getSessionFactory();

        if (autoOpenSession)
            this.openSession();
    }

    /**
     * Create a new {@link HibernateSessionProvider} to provide {@link Session} from {@link SessionFactory}
     * with autoOpenSession = true then auto open a {@link Session} from {@link SessionFactory}
     */
    public HibernateSessionProvider() {
        this(true);
    }


    /**
     * Open a {@link org.hibernate.Session} from {@link SessionFactory}
     */
    public void openSession() {
        this.session = this.sessionFactory.openSession();
    }

    /**
     * Close a {@link org.hibernate.Session} from {@link SessionFactory}
     */
    public void closeSession() {
        this.session.close();
    }

    /**
     * Get {@link org.hibernate.Session} from {@link SessionFactory}
     *
     * @return {@link Session}
     */
    public Session getSession() {
        return this.session;
    }

}
