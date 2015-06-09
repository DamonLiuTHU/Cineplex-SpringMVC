package com.cineplex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.test.AssertThrows;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cineplex.model.forms.ActivateCodeForm;
import com.cineplex.model.forms.RegisterForm;
import com.cineplex.model.impl.RegisterModel;


@Controller
public class RegisterController {
	
	@RequestMapping("/register")
	public String registNewUser(RegisterForm rf,HttpSession session) {
		// System.out.println(rf.toString());
		String phone = rf.getPhone();
		String pw = rf.getPassword();
		RegisterModel rm = new RegisterModel();
		boolean exist = rm.isIDExist(phone);
		if (exist) {
			return "registerfail";
		} else {
			rm.saveRegisterInfo(phone, pw);
			session.setAttribute("phone", phone);
			return "verifyActivateCode";
		}
	}

	
	@RequestMapping("/verifyActivateCode")
	public String verifyActivateCode(ActivateCodeForm form , HttpSession session){
		String phone = (String) session.getAttribute("phone");
		if(phone != null){
			String code = form.getCode();
			
		}
		return "";
	}
	
	
}
