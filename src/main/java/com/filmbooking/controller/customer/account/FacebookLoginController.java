package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.enumsAndConstants.enums.AccountTypeEnum;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.services.logProxy.UserServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/login/facebook")
public class FacebookLoginController extends HttpServlet {
	private CRUDServicesLogProxy<User> userServicesLog;
	private UserServicesImpl userServices;
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userServices = new UserServicesImpl();
		userServicesLog = new CRUDServicesLogProxy<>(new UserServicesImpl(), req, User.class);

		// Đọc dữ liệu được gửi từ client-side
		StringBuilder requestData = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				requestData.append(line);
			}
		}

		// Xử lý dữ liệu JSON
		JsonObject jsonObject = JsonParser.parseString(requestData.toString()).getAsJsonObject();

		// Lấy tên từ dữ liệu JSON
        String name = jsonObject.get("name").getAsString();
        String email = jsonObject.has("email") ? jsonObject.get("email").getAsString() : "Email không có sẵn";
        String id = jsonObject.get("id").getAsString();
		System.out.println(name + "," + email +"," + id);

		User loginUser = userServices.getByUsername(id);
		User newUser = null;

		if (userServices.getByUsername(id) == null) {
			newUser = new User(id, name, email, null, AccountRoleEnum.CUSTOMER.getAccountRole(),AccountTypeEnum.FACEBOOK.getAccountType(),1);
			userServicesLog.insert(newUser);
		}
		HttpSession session = req.getSession();
		session.setAttribute("loginUser", loginUser);
		FilmBooking filmBooking = new FilmBooking();
		filmBooking.setUser(loginUser);
		session.setAttribute("filmBooking", filmBooking);


//		resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));


	}
	@Override
	public void destroy() {
		userServicesLog = null;
		userServices = null;
	}
}
