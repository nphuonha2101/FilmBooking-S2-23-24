package com.filmbooking.controller.apis;

import com.filmbooking.controller.apis.apiResponse.APIJSONResponse;
import com.filmbooking.controller.apis.apiResponse.RespCodeEnum;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.utils.APIUtils;
import com.filmbooking.utils.FilmBookingUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/v1/film-bookings/*", "/api/v1/film-bookings"})
public class FilmBookingAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilmBookingServicesImpl filmBookingServices = new FilmBookingServicesImpl();

        String command = req.getParameter("command");
        String language = (String) req.getSession().getAttribute("lang");

        APIUtils<FilmBooking> apiUtils = new APIUtils<>(filmBookingServices, req, resp);

        if (command.equals("current")) {
            FilmBooking currentFilmBooking = (FilmBooking) req.getSession().getAttribute("filmBooking");
            System.out.println("currentFilmBooking: " + currentFilmBooking);
            if (currentFilmBooking == null || currentFilmBooking.getShowtimeId() == 0) {
                APIJSONResponse apijsonResponse = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), language, null);
                apiUtils.setJsonResponse(apijsonResponse);
                apiUtils.writeResponse(null, 0);
                return;
            } else {
                generateFilmBookingResp(language, apiUtils, currentFilmBooking);
            }
        } else if (command.equals("byUsername")) {
            User loginUser = (User) req.getSession().getAttribute("loginUser");

            if (loginUser == null) {
                APIJSONResponse apijsonResponse = new APIJSONResponse(RespCodeEnum.UNAUTHORIZED.getCode(), RespCodeEnum.UNAUTHORIZED.getMessage(), language, null);
                apiUtils.setJsonResponse(apijsonResponse);
                apiUtils.writeResponse(null, 0);
            } else {

                List<FilmBooking> filmBookingList = filmBookingServices.selectAllByUser(loginUser);

                generateFilmBookingListResp(language, apiUtils, filmBookingList);
            }
        } else if (command.equals("all")) {
            List<FilmBooking> filmBookingList = filmBookingServices.selectAll();
            generateFilmBookingListResp(language, apiUtils, filmBookingList);
        } else if (command.equals("id")) {
            FilmBooking filmBooking = filmBookingServices.select(Long.parseLong(req.getParameter("id")));
            System.out.println("filmBooking: " + filmBooking);

            generateFilmBookingResp(language, apiUtils, filmBooking);
        } else if (command.equals("offset")) {
            int offset = Integer.parseInt(req.getParameter("offset"));

            if (offset < 0) {
                APIJSONResponse apijsonResponse = new APIJSONResponse(RespCodeEnum.BAD_REQUEST.getCode(), RespCodeEnum.BAD_REQUEST.getMessage(), language, null);
                apiUtils.setJsonResponse(apijsonResponse);
                apiUtils.writeResponse(null, 0);
            } else if (req.getParameter("limit") == null) {
                List<FilmBooking> filmBookingList = filmBookingServices.selectAll(100, offset);
                generateFilmBookingListResp(language, apiUtils, filmBookingList);
            } else {

                int limit = Integer.parseInt(req.getParameter("limit"));
                List<FilmBooking> filmBookingList = filmBookingServices.selectAll(limit, offset);
                generateFilmBookingListResp(language, apiUtils, filmBookingList);
            }
        }



    }
    
    /**
     * Generate film booking response
     *
     * @param language    language response
     * @param apiUtils    api utils used to write response
     * @param filmBooking film booking to generate response
     * @throws IOException if error occurs when writing response
     */
    private void generateFilmBookingResp(String language, APIUtils<FilmBooking> apiUtils, FilmBooking filmBooking) throws IOException {
        String jsonResp;
        APIJSONResponse apijsonResponse;
        if (filmBooking == null) {
            apijsonResponse = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), language, null);
            jsonResp = apijsonResponse.getResponse();
        } else {
            String filmBookingJson = FilmBookingUtil.generateFilmBookingJson(filmBooking);
            apijsonResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), language, filmBookingJson);
            jsonResp = "{\"status\":" + apijsonResponse.getStatus() + ",\"language\": " + language + ",\"message\":\"" + apijsonResponse.getMessage() + "\",\"data\":" + filmBookingJson + "}";
        }

        apiUtils.writeResponse(jsonResp, null, 0);
    }

    /**
     * Generate film booking list response
     *
     * @param language        language response
     * @param apiUtils        api utils used to write response
     * @param filmBookingList film booking list to generate response
     * @throws IOException if error occurs when writing response
     */
    private void generateFilmBookingListResp(String language, APIUtils<FilmBooking> apiUtils, List<FilmBooking> filmBookingList) throws IOException {
        APIJSONResponse apijsonResponse;
        String jsonResp;
        if (filmBookingList == null) {
            apijsonResponse = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), language, null);
            jsonResp = apijsonResponse.getResponse();
        } else {
            List<String> filmBookingJsonList = FilmBookingUtil.generateFilmBookingJsonList(filmBookingList);
            apijsonResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), language, filmBookingJsonList);
            jsonResp = "{\"status\":" + apijsonResponse.getStatus() + ",\"language\": " + language + ",\"message\":\"" + apijsonResponse.getMessage() + "\",\"data\":" + filmBookingJsonList + "}";
        }

        apiUtils.writeResponse(jsonResp, null, 0);
    }
}
