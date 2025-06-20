package com.filmbooking.controller.admin.update;

import com.filmbooking.model.Room;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.services.impls.TheaterServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "editRoom", value = "/admin/edit/room")
public class EditRoomController extends HttpServlet {
    private RoomServicesImpl roomServices;
    private CRUDServicesLogProxy<Room> roomServicesLog;
    private TheaterServicesImpl theaterServices;
    private Room editRoom;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roomServices = new RoomServicesImpl();
        theaterServices = new TheaterServicesImpl();

        String roomSlug = req.getParameter("room");
        if (roomSlug != null)
            editRoom = roomServices.getBySlug(roomSlug);

        Page editRoomPage = new AdminPage(
                "editRoomTitle",
                "edit-room",
                "master");
        editRoomPage.putAttribute("editRoom", editRoom);
        editRoomPage.putAttribute("theaters", theaterServices.selectAll());

        editRoomPage.render(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roomServicesLog = new CRUDServicesLogProxy<>(new RoomServicesImpl(), req, Room.class);

        String roomName = StringUtils.handlesInputString(req.getParameter("room-name"));
        int seatRows = Integer.parseInt(req.getParameter("seat-rows"));
        int seatCols = Integer.parseInt(req.getParameter("seat-cols"));

        editRoom.setRoomName(roomName);
        editRoom.setSeatRows(seatRows);
        editRoom.setSeatCols(seatCols);

        roomServicesLog.update(editRoom);

        req.setAttribute("statusCodeSuccess", StatusCodeEnum.UPDATE_ROOM_SUCCESSFUL.getStatusCode());
        doGet(req, resp);

    }

    @Override
    public void destroy() {
        roomServices = null;
        editRoom = null;
        theaterServices = null;
    }
}
