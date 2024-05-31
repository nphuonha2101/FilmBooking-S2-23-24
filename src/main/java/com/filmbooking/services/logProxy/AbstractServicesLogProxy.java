package com.filmbooking.services.logProxy;

import com.filmbooking.annotations.TableName;
import com.filmbooking.model.IModel;
import com.filmbooking.model.LogModel;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractService;
import com.filmbooking.services.impls.LogModelServicesImpl;
import com.filmbooking.utils.annotation.ClazzAnnotationProcessor;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;
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
    protected String tableName;


    public AbstractServicesLogProxy(HttpServletRequest request, Class<T> modelClass) {
        this.logModelServices = new LogModelServicesImpl();
        this.gson = GSONUtils.getGson();
        this.user = (User) request.getSession().getAttribute("loginUser");
        this.request = request;
        ClazzAnnotationProcessor clazzAnnotationProcessor = ClazzAnnotationProcessor.getInstance(modelClass);
        tableName = (String) clazzAnnotationProcessor.getAnnotationValue(TableName.class, "value");
    }


    /**
     * Build log model for logging CRUD actions
     *
     * @param action action: INSERT, UPDATE, DELETE
     * @return LogModel
     */
    protected LogModel buildLogModel(String action, T t, AbstractService<T> decoratedCRUDService, boolean isActionSuccess) {
        Object id;
        T oldT = null;
        if (t != null) {
            id = t.getIdValue();
            try {
                oldT = decoratedCRUDService.select(id);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        String ip = request.getRemoteAddr();
        String targetTable = this.tableName;
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
