package com.cineplex.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cineplex.model.forms.LoginForm;
import com.cineplex.model.impl.LoginModel;
import com.cineplex.model.impl.RegisterModel;
import com.cineplex.model.impl.UserModel;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login(LoginForm lf, HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, String> map) {
		String phone = lf.getPhone();
		String pw = lf.getPassword();
		LoginModel lm = new LoginModel();
		if (lm.isPwValid(phone, pw)) {
			session.setAttribute("phone", phone);
			Cookie c = new Cookie("phone", phone);
			c.setMaxAge(3600);
			response.addCookie(c);
			String notice;
			if (UserModel.isVIPExpired(phone)) {
//				UserModel.pauseVIP(phone);
				notice = "你的会员已经过期，请及时充值，否则会员记录将在一年后停止";
			} else if (UserModel.isVIPPaused(phone)) {
				int days = UserController.getExpirationDays(phone);
				notice = "你的会员状态处于暂停状态，请及时充值，否则将会在" + days + "天后停止记录";
			} else if (UserModel.isVIP(phone)) {
				notice = "您好！会员" + UserModel.getID(phone);
			} else {
				notice = "您好！用户" + UserModel.getID(phone);
			}
			
			
			if(RegisterModel.isUserVerified(phone)){
			}else{
				notice += "Code not Verified!";
			}

			map.put("notice", notice);
			session.setAttribute("notice", notice);

			return "forward:loginsuccess.jsp";
		} else {
			return "loginfail";
		}
	}
	
}
