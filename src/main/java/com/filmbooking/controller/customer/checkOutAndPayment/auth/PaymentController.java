package com.filmbooking.controller.customer.checkOutAndPayment.auth;

/*
 *  @created 13/01/2024 - 9:05 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.enumsAndConstants.enums.PaymentStatus;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.LogModel;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.services.impls.LogModelServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/auth/payment")
public class PaymentController extends HttpServlet {

    private FilmBookingServicesImpl filmBookingServices;
    private ShowtimeServicesImpl showtimeServices;
    private static final LogModelServicesImpl logModelServices = new LogModelServicesImpl();
    private static final Gson gson = GSONUtils.getGson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmBookingServices = new FilmBookingServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();

        String vnPayRespCode = req.getParameter("vnp_ResponseCode");

        HttpSession session = req.getSession(false);
        FilmBooking filmBooking = (FilmBooking) session.getAttribute("filmBooking");

        if (vnPayRespCode.equals("00")) {
            handlePayment(req, resp, filmBooking, showtimeServices, filmBookingServices, PaymentStatus.SUCCESS);
            System.out.println("OK");
        } else {
            handlePayment(req, resp, filmBooking, showtimeServices, filmBookingServices, PaymentStatus.FAILED);
            System.out.println("Not OK");
        }
    }

    // TODO: handle payment log
    static void handlePayment(HttpServletRequest req, HttpServletResponse resp, FilmBooking filmBooking,
                              ShowtimeServicesImpl showtimeServices,
                              FilmBookingServicesImpl filmBookingServices, PaymentStatus paymentStatus) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        LogModel logModel = new LogModel();
        logModel.setUsername(loginUser.getUsername());
        logModel.setAction(LogModel.PAYMENT);
        logModel.setReqIP(req.getRemoteAddr());
        logModel.setTargetTable(FilmBooking.TABLE_NAME);


        switch (paymentStatus) {
            case SUCCESS -> {
                filmBooking.setPaymentStatus("paid");
                handleSaveFilmBooking(req, filmBooking, showtimeServices, filmBookingServices);
                logModel.setBeforeValueJSON(gson.toJson(filmBooking));
                logModel.setLevel(LogModel.LOG_LVL_INFO);
                logModel.setActionSuccess(true);
                logModelServices.insert(logModel);

                resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/auth/payment-status?status=success"));
            }
            case FAILED -> {
                Showtime bookedShowtime = filmBooking.getShowtime();
                if (bookedShowtime.releaseSeats(filmBooking.getBookedSeats()))
                    showtimeServices.update(bookedShowtime);
                logModel.setLevel(LogModel.LOG_LVL_WARN);
                logModel.setActionSuccess(false);
                logModelServices.insert(logModel);

                resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/auth/payment-status?status=failed"));
            }
            case PENDING -> {
                handleSaveFilmBooking(req, filmBooking, showtimeServices, filmBookingServices);
                logModel.setBeforeValueJSON(gson.toJson(filmBooking));
                logModel.setLevel(LogModel.LOG_LVL_INFO);
                logModel.setActionSuccess(true);

                logModelServices.insert(logModel);

                resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/auth/payment-status?status=pending"));
            }
        }
    }

    private static void handleSaveFilmBooking(HttpServletRequest req, FilmBooking filmBooking, ShowtimeServicesImpl showtimeServices, FilmBookingServicesImpl filmBookingServices) {
        if (filmBookingServices.insert(filmBooking)) {
            Showtime bookedShowtime = filmBooking.getShowtime();
            if (bookedShowtime.bookSeats(filmBooking.getBookedSeats())) {
                showtimeServices.update(bookedShowtime);
            }
        }

        filmBooking.resetFilmBooking();
        filmBooking.createNewVNPayTxnRef();
        req.getSession(false).setAttribute("filmBooking", filmBooking);
    }


    @Override
    public void destroy() {
        filmBookingServices = null;
        showtimeServices = null;
    }
}
