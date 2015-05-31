/**
 * 
 */
package com.cineplex.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cineplex.model.forms.ActivateForm;
import com.cineplex.model.forms.AnswerForm;
import com.cineplex.model.forms.LoginForm;
import com.cineplex.model.forms.PayForm;
import com.cineplex.model.forms.QuestionnaireForm;
import com.cineplex.model.forms.RegisterForm;
import com.cineplex.model.impl.AnswerModel;
import com.cineplex.model.impl.BankModel;
import com.cineplex.model.impl.LoginModel;
import com.cineplex.model.impl.QuestionnaireModel;
import com.cineplex.model.impl.RegisterModel;
import com.cineplex.model.impl.UserModel;
import com.cineplex.model.tables.Movie;
import com.cineplex.model.tables.Order;
import com.cineplex.model.tables.RechargeRecord;
import com.cineplex.model.tables.User;

/**
 * @author liuwt
 *
 */
@Controller
public class UserController {

	/**
	 * @param args
	 */

	@RequestMapping("/register")
	public String registNewUser(RegisterForm rf) {
		// System.out.println(rf.toString());
		String phone = rf.getPhone();
		String pw = rf.getPassword();
		RegisterModel rm = new RegisterModel();
		boolean exist = rm.isIDExist(phone);
		if (exist) {
			return "registerfail";
		} else {
			rm.saveRegisterInfo(phone, pw);
			return "success";
		}
	}

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
				int days = getExpirationDays(phone);
				notice = "你的会员状态处于暂停状态，请及时充值，否则将会在" + days + "天后停止记录";
			} else if (UserModel.isVIP(phone)) {
				notice = "您好！会员" + UserModel.getID(phone);
			} else {
				notice = "您好！用户" + UserModel.getID(phone);
			}

			map.put("notice", notice);
			session.setAttribute("notice", notice);

			return "forward:loginsuccess.jsp";
		} else {
			return "loginfail";
		}

	}

	private int getExpirationDays(String phone) {
		// TODO Auto-generated method stub
		int days = UserModel.getExpirationDaysLeft(phone);
		int result = 365 - days;
		if(result<0){
			return 0;
		}else{
			return result;
		}
	}

	@RequestMapping("/logout")
	public String logOut(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 清除保存用户身份的Cookie
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().compareTo("phone") == 0) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}

		// 清除Session属性
		session.removeAttribute("notice");

		return "redirect:index.jsp";
	}

	/*
	 * get ID and fill it with zeros to make it length 7.
	 */
	public static String getID(String phone) {

		String result = UserModel.getID(phone);
		int id = Integer.parseInt(result);
		String str = String.format("%07d", id);
		// System.out.println("ID : " + str);
		return str;
	}

	@RequestMapping("/activate")
	public String activateVIP(ActivateForm af, HttpSession session) {
		// System.out.println(af.toString());
		session.setAttribute("account", af.getAccount());
		session.setAttribute("phone", af.getPhone());
		return af.getBank();
	}

	@RequestMapping("/pay")
	public String confirmBankAccount(PayForm pf) {
		// System.out.println(pf.toString());
		boolean valid = BankModel.isPasswordValid(pf.getAccount(),
				pf.getPassword());
		if (valid) {
			UserModel.addBalance(pf.getPhone(), pf.getMoney());
			RechargeController.addRecord(pf);
			if (pf.getMoney() >= 200 && !isVIP(pf.getPhone()))
				UserModel.setVIP(pf.getPhone());
			return "paysuccess";
		} else {
			return "payfail";
		}
	}

	@RequestMapping("myaccount")
	public ModelAndView getMyAccount(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String phone = (String) session.getAttribute("phone");
		User u = UserModel.getUser(phone);
		mav.addObject("user", u);
		ArrayList<Movie> mymovies = UserModel.getMyMovies(phone);
		mav.addObject("movielist", mymovies);
		mav.setViewName("myaccount");
		return mav;
	}

	public static boolean isVIP(String phone) {
		return UserModel.isVIP(phone);
	}

	// public static void setVIP(String phone){
	// UserModel.setVIP(phone);
	// }
	public static String getExpirationDate(String phone) {
		return UserModel.getExpirationDate(phone);
	}

	public static int getBalance(String phone) {

		int result = 0;
		result = UserModel.getBalance(phone);
		return result;
	}

	@RequestMapping("recharge")
	public String jumpToRecharge() {
		return "redirect:recharge.jsp";
	}

	@RequestMapping("personalinfo")
	public ModelAndView checkAndModifyPersonalInfo(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("personalinfo");
		String phone = (String) session.getAttribute("phone");
		User user = UserModel.getUser(phone);
		LinkedList<Order> orderlist = OrderController.getOrders(phone);
		LinkedList<RechargeRecord> rechargelist = RechargeController
				.getRecharges(user);
		mav.addObject("orderlist", orderlist);
		mav.addObject("rechargerecord", rechargelist);
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping("updatepersonalinfo")
	public String updatePersonalInfo(@RequestParam("phone") String phone,
			@RequestParam("gender") String gender,
			@RequestParam("age") int age, @RequestParam("city") String city) {
		// ModelAndView mav = new ModelAndView();
		// mav.addObject("user", user);
		try {
			gender = new String(gender.getBytes("ISO-8859-1"), "UTF-8");
			city = new String(city.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User u = new User(phone, age, gender, city);
		// System.out.println(u.toString());
		// mav.setViewName(mav.getViewName());
		UserModel.updateUser(u);
		return "redirect:personalinfo";
	}

	/*
	 * 查找这个电影Id的问题。并显示。 供用户选择。
	 */
	@RequestMapping("getQuestion")
	public ModelAndView checkQuestions(@RequestParam("movieId") String id,
			HttpSession session) {
		// System.out.println(id);
		List<QuestionnaireForm> questionlist = QuestionnaireModel.getQuestions(id);
		List<QuestionnaireForm> not_answered_question = new ArrayList<QuestionnaireForm>();
		String phone = (String) session.getAttribute("phone");
		for (QuestionnaireForm q : questionlist) {
			int qId = q.getId();
			if(!AnswerModel.alreadyAnswered(phone, qId+"")){
				not_answered_question.add(q);
			}
		}
		ModelAndView mav = new ModelAndView("forward:answerquestion.jsp");
		mav.addObject("list", not_answered_question);

		return mav;
	}

	@RequestMapping("postAnswer")
	public ModelAndView postAnswer(AnswerForm af) {
		// System.out.println(af);
		ModelAndView mav = new ModelAndView("redirect:myaccount");
		AnswerModel.save(af);
		return mav;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
