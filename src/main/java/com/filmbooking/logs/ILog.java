package com.filmbooking.logs;

import com.filmbooking.enumsAndConstants.enums.LogActionsEnums;
import com.filmbooking.model.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public interface ILog<T> {
    void log(HttpServletRequest request, User user, T data, LogActionsEnums logAction);
}
