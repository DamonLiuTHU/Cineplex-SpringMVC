package com.cineplex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cineplex.model.forms.UserRankForm;


@Controller
public class RankController {
	
	@RequestMapping("SubmitRank")
	public ModelAndView submitRank(HttpServletRequest request,HttpSession session,UserRankForm form){
		ModelAndView mav = new ModelAndView();
		
		System.out.println(form.toString());
		System.out.println(session.getAttribute("phone"));
		
		return mav;
	}
}
