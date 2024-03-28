package com.filmbooking.logs;

import com.filmbooking.enumsAndConstants.enums.LogActionsEnums;
import com.filmbooking.model.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class LoginLogImpls<T> implements ILog<T>{

    @Override
    public void log(HttpServletRequest request, User user, T data, LogActionsEnums logAction) {
        String reqIP = request.getRemoteAddr();
        String logActionDescription = logAction.getDescription();
//        String

    }
}
