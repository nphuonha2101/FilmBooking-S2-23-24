package com.filmbooking.services.logProxy;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.IModel;
import com.filmbooking.model.LogModel;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractCRUDServices;
import com.filmbooking.services.ICRUDServices;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

/**
 * <uL>
 * <li>This class is a proxy class for CRUD services</li>
 * <li>It is used to log CRUD actions</li>
 * <li>It extends {@link AbstractServicesLogProxy} and implements
 * {@link ICRUDServices}</li>
 * <li>For more please see <b> Proxy design pattern </b></li>
 * </uL>
 * 
 * @param <T>
 */
public class CRUDServicesLogProxy<T extends IModel> extends AbstractServicesLogProxy<T> implements ICRUDServices<T> {
    private final ICRUDServices<T> crudServices;

    public CRUDServicesLogProxy(ICRUDServices<T> crudServices, HttpServletRequest req,
            HibernateSessionProvider sessionProvider) {
        super(req);
        this.crudServices = crudServices;
        this.logModelServices.setSessionProvider(sessionProvider);
        this.crudServices.setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.crudServices.setSessionProvider(sessionProvider);
    }

    @Override
    public ICRUDServices<T> getAll() {
        return this.crudServices.getAll();
    }

    @Override
    public ICRUDServices<T> getByOffset(int offset, int limit) {
        return this.crudServices.getByOffset(offset, limit);
    }

    @Override
    public ICRUDServices<T> getByPredicates(Map<String, Object> conditions) {
        return this.crudServices.getByPredicates(conditions);
    }

    @Override
    public T getBySlug(String slug) {
        return this.crudServices.getBySlug(slug);
    }

    @Override
    public T getByID(String id) {
        return this.crudServices.getByID(id);
    }

    @Override
    public long getTotalRecordRows() {
        return this.crudServices.getTotalRecordRows();
    }

    @Override
    public boolean save(T t) {
        LogModel logModel = this.buildLogModel(LogModel.INSERT, t, (AbstractCRUDServices<T>) this.crudServices, true);
        boolean saveState = this.crudServices.save(t);

        logModel.setActionSuccess(saveState);
        this.logModelServices.save(logModel);

        return saveState;
    }

    @Override
    public boolean update(T t) {
        LogModel logModel = this.buildLogModel(LogModel.UPDATE, t, (AbstractCRUDServices<T>) this.crudServices, true);
        boolean updateState = this.crudServices.update(t);

        logModel.setActionSuccess(updateState);
        this.logModelServices.save(logModel);

        return updateState;
    }

    @Override
    public boolean delete(T t) {
        LogModel logModel = this.buildLogModel(LogModel.DELETE, t, (AbstractCRUDServices<T>) this.crudServices, true);
        boolean deleteState = this.crudServices.delete(t);

        logModel.setActionSuccess(deleteState);
        this.logModelServices.save(logModel);

        return deleteState;
    }

    @Override
    public List<T> getMultipleResults() {
        return this.crudServices.getMultipleResults();
    }

    @Override
    public T getSingleResult() {
        return this.crudServices.getSingleResult();
    }

    @Override
    public String getTableName() {
        return this.crudServices.getTableName();
    }

    @Override
    public User newUser(String username, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newUser'");
    }
}
