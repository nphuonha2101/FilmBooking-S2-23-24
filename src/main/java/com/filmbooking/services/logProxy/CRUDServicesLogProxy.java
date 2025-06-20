package com.filmbooking.services.logProxy;

import com.filmbooking.model.IModel;
import com.filmbooking.model.LogModel;
import com.filmbooking.services.AbstractService;
import com.filmbooking.services.IService;
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
 * {@link IService}</li>
 * <li>For more please see <b> Proxy design pattern </b></li>
 * </uL>
 * 
 * @param <T>
 */
public class CRUDServicesLogProxy<T extends IModel> extends AbstractServicesLogProxy<T> implements IService<T> {
    private final IService<T> crudService;

    public CRUDServicesLogProxy(IService<T> crudService, HttpServletRequest req, Class<T> modelClass) {
        super(req, modelClass);
        this.crudService = crudService;
    }

    @Override
    public T getBySlug(String slug) {
        return this.crudService.getBySlug(slug);
    }

    @Override
    public T select(Object id) {
        return this.crudService.select(id);
    }

    @Override
    public long countRecords() {
        return this.crudService.countRecords();
    }

    @Override
    public boolean insert(T t) {
        LogModel logModel = this.buildLogModel(LogModel.INSERT, t, (AbstractService<T>) this.crudService, true);
        boolean saveState = this.crudService.insert(t);

        logModel.setActionSuccess(saveState);
        this.logModelServices.insert(logModel);

        return saveState;
    }

    @Override
    public boolean update(T t) {
        LogModel logModel = this.buildLogModel(LogModel.UPDATE, t, (AbstractService<T>) this.crudService, true);
        boolean updateState = this.crudService.update(t);

        logModel.setActionSuccess(updateState);
        this.logModelServices.insert(logModel);

        return updateState;
    }

    @Override
    public boolean delete(T t) {
        LogModel logModel = this.buildLogModel(LogModel.DELETE, t, (AbstractService<T>) this.crudService, true);
        boolean deleteState = this.crudService.delete(t);

        logModel.setActionSuccess(deleteState);
        this.logModelServices.insert(logModel);

        return deleteState;
    }

    @Override
    public List<T> selectAll() {
        return crudService.selectAll();
    }

    @Override
    public List<T> selectAll(int limit, int offset) {
        return crudService.selectAll(limit, offset);
    }

    @Override
    public List<T> selectAll(Map<String, Object> filters) {
        return crudService.selectAll(filters);
    }

    @Override
    public List<T> selectAll(int limit, int offset, String order) {
        return crudService.selectAll(limit,offset,order);
    }

    @Override
    public List<T> selectAll(int limit, int offset, String order, Map<String, Object> filters) {
        return crudService.selectAll(limit, offset, order, filters);
    }

}
