package com.filmbooking.controller.customer.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login/facebook")
public class FacebookLoginController extends HttpServlet {
	private CRUDServicesLogProxy<User> userServicesLog;
	private UserServicesImpl userServices;
	private String name = null;
	private String email = null;
	private String id= null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
		userServices = new UserServicesImpl(sessionProvider);

		// Đọc dữ liệu được gửi từ client-side
		StringBuilder requestData = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				requestData.append(line);
			}
		}

		// Xử lý dữ liệu JSON
		Gson gson = new Gson();
		JsonObject jsonObject = JsonParser.parseString(requestData.toString()).getAsJsonObject();

		// Lấy tên từ dữ liệu JSON
		name = jsonObject.get("name").getAsString();
		email = jsonObject.has("email") ? jsonObject.get("email").getAsString() : "Email không có sẵn";
		id = jsonObject.get("id").getAsString();
		

		// In ra thông tin nhận được
		System.out.println("Tên người dùng: " + name);
		System.out.println("Email người dùng: " + email);
		System.out.println("Id người dùng: " + id);
		User loginUser = userServices.getByUsername(id);
		if (loginUser == null) {
			System.out.println("Không tìm thấy người dùng, tạo mới...");
			loginUser = new User(id, name, email, id, AccountRoleEnum.CUSTOMER);
			userServices.save(loginUser);
		}

		// Thiết lập session và thông tin đặt phim
		HttpSession session = req.getSession();
		session.setAttribute("loginUser", loginUser);
		FilmBooking filmBooking = new FilmBooking();
		filmBooking.setUser(loginUser);
		session.setAttribute("filmBooking", filmBooking);

		// Chuyển hướng người dùng về trang chủ
		resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
	}

}
