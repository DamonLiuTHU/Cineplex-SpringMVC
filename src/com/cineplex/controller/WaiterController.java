package com.cineplex.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cineplex.model.forms.LoginForm;
import com.cineplex.model.forms.PlanForm;
import com.cineplex.model.forms.QuestionnaireForm;
import com.cineplex.model.impl.HallModel;
import com.cineplex.model.impl.MovieModel;
import com.cineplex.model.impl.OrderModel;
import com.cineplex.model.impl.PlanModel;
import com.cineplex.model.impl.QuestionnaireModel;
import com.cineplex.model.impl.UserModel;
import com.cineplex.model.impl.WaiterModel;
import com.cineplex.model.tables.Hall;
import com.cineplex.model.tables.Movie;

@RequestMapping("waiter")
@Controller
public class WaiterController {

	@RequestMapping("login")
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("waiter/login");
		return mav;
	}

	@RequestMapping("logout")
	public ModelAndView logOut(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("waiter/login");
		session.invalidate();
		return mav;
	}

	@RequestMapping("/confirm")
	public String checkPsw(LoginForm lf, Map<String, ArrayList<Movie>> map,
			HttpSession session) {
		// System.out.println(lf.toString());
		if (WaiterModel.isPswValid(lf.getPhone(), lf.getPassword())) {
			// mav.setViewName("waiter/waiterindex");
			session.setAttribute("waiterId", lf.getPhone());
			return "redirect:waiterindex.jsp";
		} else {
			// mav.setViewName("loginfail");
			return "redirect:loginfail.jsp";
		}

		// return mav;
	}

	/*
	 * 接收到服务员传来的计划之后，如何存储计划？ 方案一 有一张计划表，在计划中保存服务员的Id 作为外键。
	 */
	@RequestMapping("upload")
	public String uploadPlans(@ModelAttribute("planform") PlanForm pf,
			HttpSession session) {
		// System.out.println(pf.toString());
		String waiterId = (String) session.getAttribute("waiterId");
		if (waiterId == null) {
			return "You haven't log in";
		}
		String movieId = pf.getMovieId();
		String hallId = pf.getHallId();
		String start[] = pf.getStart();
		String end[] = pf.getEnd();
		int period_number = start.length;
		for (int i = 0; i < period_number; i++) {
			String starttime = start[i];
			String endtime = end[i];
			PlanModel.savePlan(movieId, starttime, endtime, waiterId, hallId);
		}

		return "./waiter/savesuccess";

	}

	@RequestMapping(value = "/getPostQPage", method = RequestMethod.GET)
	public ModelAndView uploadQuestionPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/waiter/postquestionnaire");
		ArrayList<Movie> movielist = MovieModel.getMovies();
		mav.addObject("movielist", movielist);
		return mav;
	}

	/*
	 * 调用QuestionnaireModel的Hibernate方法
	 */
	@RequestMapping(value = "postQuestionnaire")
	public ModelAndView postQue(QuestionnaireForm qf, HttpSession session) {
		String waiterId = (String) session.getAttribute("waiterId");
		qf.setWaiterId(waiterId);
		QuestionnaireModel.save(qf);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/waiter/publishsuccess");

		return mav;
	}

	@RequestMapping("getCloseQuestionPage")
	public ModelAndView getCloseQuestionPage(HttpSession session) {
		String waiterId = (String) session.getAttribute("waiterId");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/waiter/closequestionpage");
		ArrayList<QuestionnaireForm> myqlist = WaiterModel
				.getMyQuestions(waiterId);
		mav.addObject("myqlist", myqlist);
		return mav;
	}

	@RequestMapping("closequestion")
	public ModelAndView closequestion(@RequestParam("id") String questionId,
			HttpSession session) {
		String waiterId = (String) session.getAttribute("waiterId");
		ModelAndView mav = new ModelAndView();
		// System.out.println(questionId);
		QuestionnaireModel.closeQuestino(questionId);
		mav.setViewName("/waiter/closequestionpage");
		ArrayList<QuestionnaireForm> myqlist = WaiterModel
				.getMyQuestions(waiterId);
		mav.addObject("myqlist", myqlist);
		return mav;
	}

	@RequestMapping("getSalePage")
	public ModelAndView getSalePage(HttpSession session) {
		String waiterId = (String) session.getAttribute("waiterId");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/waiter/sellticket");
		return mav;
	}

	@RequestMapping("order")
	public String jumpToOrderPage(@RequestParam(value = "movieId") String mId,
			Map<String, Object> map) {
		map.put("mId", mId);
		ArrayList<Hall> list = MovieModel.getArrangements(mId);
		map.put("list", list);

		return "waiter/worder";
	}

	@RequestMapping("/buyTicket")
	public String buyTicketInCash(@RequestParam("seatlist") String seatlist,
			@RequestParam("Id") String Id,
			@RequestParam("waiterId") String waiterId) {
		String[] seats = seatlist.split(",");
		int ticket_number = seats.length;
		double price = HallModel.getPrice(Id);
		double total = ticket_number * price;
		MovieModel.reduceTicket(Id, ticket_number);
		for (String seat : seats) {
			OrderModel.waiterMakeOrder(waiterId, Id, seat);
		}
		return "redirect:waiterindex.jsp";
	}
	
	@RequestMapping("getPeriodSeatUsage")
	public ModelAndView getPeriodSeatUsage() {
		ModelAndView mav = new ModelAndView("waiter/periodSeatUsage");
		
		
		
		return mav;
	}
	
	
}
