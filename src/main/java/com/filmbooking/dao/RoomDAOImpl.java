package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.Theater;
import com.filmbooking.utils.HibernateUtils;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements IDAO<Room> {
    private static RoomDAOImpl instance = null;
    private final HibernateUtils hibernateUtils;
    private Session session;

    private RoomDAOImpl() {
        hibernateUtils = HibernateUtils.getInstance();
    }

    public static RoomDAOImpl getInstance() {
        if (instance == null) {
            instance = new RoomDAOImpl();
        }
        return instance;
    }

    @Override
    public void openSession() {
        this.session = hibernateUtils.openSession();
    }

    @Override
    public List<Room> getAll() {
        List<Room> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        // declare an object that want to query
        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> rootEntry = criteriaQuery.from(Room.class);
        CriteriaQuery<Room> all = criteriaQuery.select(rootEntry);

        TypedQuery<Room> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }
    @Override
    public Room getByID(String id) {

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> rootEntry = criteriaQuery.from(Room.class);
        criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), id));

        TypedQuery<Room> typedQuery = this.session.createQuery(criteriaQuery);


        return typedQuery.getSingleResult();
    }
    @Override
    public void save(Room room) {
        Transaction transaction = session.beginTransaction();
        session.persist(room);
        transaction.commit();
    }

    @Override
    public void update(Room room) {
        Transaction transaction = session.beginTransaction();
        session.merge(room);
        transaction.commit();
    }

    @Override
    public void delete(Room room) {
        Transaction transaction = session.beginTransaction();
        session.remove(room);
        transaction.commit();
    }
    @Override
    public Session getSession() {
        if (session != null)
            return this.session;
        else {
            throw new RuntimeException("Not active Hibernate Session");
        }
    }
}
