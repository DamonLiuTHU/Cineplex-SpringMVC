package com.cineplex.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cineplex.model.forms.LoginForm;
import com.cineplex.model.forms.PlanForm;
import com.cineplex.model.impl.HallModel;
import com.cineplex.model.impl.ManagerModel;
import com.cineplex.model.impl.MovieModel;
import com.cineplex.model.impl.OrderModel;
import com.cineplex.model.impl.StatisticsModel;
import com.cineplex.model.tables.Movie;
import com.cineplex.model.tables.Statistics;

@RequestMapping("manager")
@Controller
public class ManagerController{
	
	@RequestMapping("mlogin.do")
	public String getLoginPage(){
		return "manager/mlogin";
	}
	
	
	@RequestMapping("confirm")
	public String checkPsw(LoginForm lf,HttpSession session){
		if(ManagerModel.isPswValid(lf)){
			session.setAttribute("managerId", lf.getPhone());
			session.setMaxInactiveInterval(3600);
			return "redirect:mindex.jsp";

		}else{
			return "loginfail";
		}
		
	}
	@RequestMapping("/logout")
	public String logOut(HttpSession session){
		session.invalidate();
		return "redirect:mlogin.jsp";
	}
	
	@RequestMapping(value="getCheckPlansPage",method=RequestMethod.GET)
	public ModelAndView checkPlans(ModelAndView mav){
//		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/checkPlans");
		ArrayList<String> waiterlist = ManagerModel.getWaiters();
		mav.addObject("waiters",waiterlist);
		return mav;
	}
	
	/*
	 * 还在思考：如何让经理根据某一个放映厅来查看放映计划，考虑到可能多个服务员上传放映计划（还是不考虑这一点？）
	 * 在确定影厅之后，返回一个服务员的列表？  然后再根据服务员的列表去查询计划？
	 * 还是直接显示所有服务员上传的计划？  做一个list  然后每一项是一个服务员上传的该影厅的计划 form 经理在选定某一个批准或者不批准之后，
	 * 这样的话plan表还要新增一个状态字段来判断当前状态。
	 */
	@RequestMapping(value="checkPlans")
	public ModelAndView getPlans(@RequestParam("hallId") String hallId,@RequestParam("waiterId") String waiterId,ModelAndView mav){
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("manager/checkPlans");
//		System.out.println(hallId+"   "+waiterId);
//		String notice = "服务员："+waiterId+" 为放映厅："+hallId+"上传的放映计划为：";
//		mav.put("notice", notice);
		mav.addObject("waiterId",waiterId);
		mav.addObject("hallId", hallId);
		PlanForm pf = ManagerModel.getPlan(hallId, waiterId);
		mav.addObject("planform", pf);
		ArrayList<String> waiterlist = ManagerModel.getWaiters();
		mav.addObject("waiters",waiterlist);
		return mav;
	}
	
	public static ArrayList<String> getWaiters(){
		return ManagerModel.getWaiters();
	}
	
	
	/*
	 * 负责处理当经理同意这个计划之后，把这个服务员的计划整个覆盖到数据库中hallId相同的项目去。
	 */
	@RequestMapping("accept")
	public ModelAndView acceptPlan(@RequestParam("hallId") String hallId,@RequestParam("waiterId") String waiterId){
//		System.out.println(hallId+"======="+waiterId);
		ManagerModel.accepetPlan(hallId,waiterId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/planAccept");
		return mav;
	}
	@RequestMapping("refuse")
	public ModelAndView refusePlan(@RequestParam("hallId") String hallId,@RequestParam("waiterId") String waiterId){
//		System.out.println(hallId+"======="+waiterId);
		ManagerModel.refusePlan(hallId,waiterId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/planDecline");
		return mav;
	}
	
	@RequestMapping("getStatistics")
	public ModelAndView getStatistics(){
		ModelAndView mav = new ModelAndView();
		int[] cashandonline = StatisticsModel.getCashandOnline();
		mav.setViewName("manager/statistics");
		int cash = cashandonline[1];
		int online = cashandonline[0];
		
		mav.addObject("cash", cash);
		mav.addObject("online",online);
		return mav;
	}
	@RequestMapping("checkByMonth")
	public static ModelAndView checkByMonth(@RequestParam("user_date") String date){
		System.out.println(date);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/statistics");
		Statistics myStatistics = StatisticsModel.getStatistics(date);
		mav.addObject("mystat",myStatistics);
		return mav;
	}
	@RequestMapping("getPublishPage")
	public static ModelAndView getPublishMoviePage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/publishmovie");
		
		return mav;
	} 
	
	
	@RequestMapping("publishMovie")
	public static ModelAndView publishMovie(Movie m,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		m.setPublish_date(sdf.format(today));
		mav.setViewName("manager/mindex");
		System.out.println(m);
		//转型为MultipartHttpRequest(重点的所在)  
//		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        //  获得第1张图片（根据前台的name名称得到上传的文件）   
//        MultipartFile poster  =  ((MultipartRequest) request).getFile("poster");  
        File file = null;  
        String path="C:\\快盘\\大三中\\《J2EE与中间件》\\workspace\\Cineplex-SpringMVC\\WebContent\\images";
        path=request.getContextPath();
        m.setPoster("./images/"+m.getPoster());
        file = createFolder(path);
//        try {
////			poster.transferTo(file);
//		} catch (IllegalStateException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		MovieModel.save(m);
		return mav;
	}
	
	private static File createFolder(String path){
		File file = null;
		file = new File(path); 
		return file;
	}
	
	@RequestMapping("getWaiterPlanSuccessRate")
	public static ModelAndView getWaiterPlanSuccessRate(){
		ModelAndView mav = new ModelAndView("manager/waiterPlanSuccessRate");
		
		
		return mav;
	}
	
	//通过对座位不同位置的统计来为经理制定优惠政策提供决策支持。
	@RequestMapping("/getSeatUsageRate")
	public ModelAndView getSeatUsageRatePage(){
		ModelAndView mav = new ModelAndView("manager/seat_detail");
		
		
		return mav;
	}
	
	
	@RequestMapping("/getFinancialPage")
	public ModelAndView getFinancialPage(){
		ModelAndView mav = new ModelAndView("manager/financial");
		
		
		return mav;
	}
	
	@RequestMapping("/getTypeDetailForMonth")
	public ModelAndView getTypeDetialPage(@RequestParam("month") String month){
		ModelAndView mav = new ModelAndView("manager/typedetail");
		mav.addObject("month",month);
		double[] data = OrderModel.typeDetailForMonth(Integer.parseInt(month));
		mav.addObject("data",data);
		return mav;
	}
	
	@RequestMapping("/getHallFinancialCondition")
	public ModelAndView getHallDetialPage(){
		ModelAndView mav = new ModelAndView("manager/hallDetail");
		double[] data = HallModel.getHallFinancialCondition();
		mav.addObject("data",data);
		return mav;
	}
}
