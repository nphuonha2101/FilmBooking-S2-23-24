package com.filmbooking.controller.customer.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.enumsAndConstants.enums.AccountTypeEnum;
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
		JsonObject jsonObject = JsonParser.parseString(requestData.toString()).getAsJsonObject();

		// Lấy tên từ dữ liệu JSON
		name = jsonObject.get("name").getAsString();
		email = jsonObject.has("email") ? jsonObject.get("email").getAsString() : "Email không có sẵn";
		id = jsonObject.get("id").getAsString();
		System.out.println(name + "," + email +"," + id);

		User loginUser = userServices.getByUsername(id);
		if (loginUser == null) {
			loginUser = new User(id, name, email, null, AccountRoleEnum.CUSTOMER,AccountTypeEnum.FACEBOOK.getAccountType(),1);
			userServices.save(loginUser);
		}
		HttpSession session = req.getSession();
		session.setAttribute("loginUser", loginUser);
		FilmBooking filmBooking = new FilmBooking();
		filmBooking.setUser(loginUser);
		session.setAttribute("filmBooking", filmBooking);


		resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
	}

}
