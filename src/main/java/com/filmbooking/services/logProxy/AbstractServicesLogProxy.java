package com.filmbooking.services.logProxy;

import com.filmbooking.model.IModel;
import com.filmbooking.model.LogModel;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractCRUDServices;
import com.filmbooking.services.impls.LogModelServicesImpl;
import com.filmbooking.utils.GSONUtils;
import com.google.gson.Gson;
import com.sun.istack.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public abstract class AbstractServicesLogProxy<T extends IModel> {
    protected LogModelServicesImpl logModelServices;
    protected HttpServletRequest request;
    @Setter
    protected User user;
    protected Gson gson;


    public AbstractServicesLogProxy(HttpServletRequest request) {
        this.logModelServices = new LogModelServicesImpl();
        this.gson = GSONUtils.getGson();
        this.user = (User) request.getSession().getAttribute("loginUser");
        this.request = request;
    }


    /**
     * Build log model for logging CRUD actions
     *
     * @param action action: INSERT, UPDATE, DELETE
     * @param t      object
     * @return LogModel
     */
    protected LogModel buildLogModel(String action, T t, AbstractCRUDServices<T> decoratedCRUDServices, boolean isActionSuccess) {
        String id;
        T oldT = null;
        if (t != null) {
            id = t.getStringID();
            oldT = decoratedCRUDServices.getByID(id);
        }
        String ip = request.getRemoteAddr();
        String targetTable = decoratedCRUDServices.getTableName();
        String level = null;
        String beforeValue = null;
        String afterValue = null;

        switch (action) {
            case LogModel.INSERT:
                level = LogModel.LOG_LVL_ALERT;
                afterValue = gson.toJson(t);

                return new LogModel(user, ip, level, targetTable, action, isActionSuccess, beforeValue, afterValue, true);

            case LogModel.UPDATE:
                level = LogModel.LOG_LVL_WARN;
                beforeValue = gson.toJson(oldT);
                afterValue = gson.toJson(t);

                return new LogModel(user, ip, level, targetTable, action, isActionSuccess, beforeValue, afterValue, false);

            case LogModel.DELETE:
                level = LogModel.LOG_LVL_WARN;
                beforeValue = gson.toJson(oldT);

                return new LogModel(user, ip, level, targetTable, action, isActionSuccess, beforeValue, afterValue, false);
            case LogModel.LOGIN_SERVICE, LogModel.FORGOT_PASSWORD_SERVICE, LogModel.CHANGE_PASSWORD_SERVICE:
                level = LogModel.LOG_LVL_INFO;

                return new LogModel(user, ip, level, targetTable, action, isActionSuccess, beforeValue, afterValue, false);

        }
        return null;
    }
}
