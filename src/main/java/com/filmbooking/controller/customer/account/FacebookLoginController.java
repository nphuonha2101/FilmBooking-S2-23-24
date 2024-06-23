package com.filmbooking.controller.customer.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.enumsAndConstants.enums.AccountTypeEnum;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
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

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServicesImpl userServices = new UserServicesImpl();

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
		if (loginUser == null) {
			loginUser = new User(id, name, email, null, AccountRoleEnum.CUSTOMER,AccountTypeEnum.FACEBOOK.getAccountType(),1);
			userServices.insert(loginUser);
		}
		HttpSession session = req.getSession();
		session.setAttribute("loginUser", loginUser);
		FilmBooking filmBooking = new FilmBooking();
		filmBooking.setUser(loginUser);
		session.setAttribute("filmBooking", filmBooking);


		resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
	}

}
