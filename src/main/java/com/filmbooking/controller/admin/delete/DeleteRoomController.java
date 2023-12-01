package com.filmbooking.controller.admin.delete;

import com.filmbooking.model.Film;
import com.filmbooking.model.Room;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.utils.fileUtils.FileUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "deleteRoom", value = "/delete-room")
public class DeleteRoomController extends HttpServlet {
    private IRoomServices roomServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roomServices = new RoomServicesImpl();

        roomServices.openSession();

        String roomID = req.getParameter("room-id_hidden");
        System.out.println("DeleteFilmController Test: " + roomID);


        Room deletedRoom = roomServices.getByRoomID(roomID);
        roomServices.delete(deletedRoom);

        resp.sendRedirect("room-management");

        roomServices.closeSession();
    }

    @Override
    public void destroy() {
        roomServices = null;
    }
}
