package com.cineplex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cineplex.model.forms.UserRankForm;
import com.cineplex.model.impl.CommentModel;
import com.cineplex.model.tables.Comment;


@Controller
public class RankController {
	
	public String rankMoviePage  = "rankmovie";
	
	@RequestMapping("SubmitRank")
	public ModelAndView submitRank(HttpServletRequest request,HttpSession session,UserRankForm form){
		ModelAndView mav = new ModelAndView(rankMoviePage);
		String email = (String) session.getAttribute("phone");
		if(form.getMovieId()=="" || form.getMovieId() == null){
			
		}else{
			Comment comment = new Comment(form.getScore(), form.getComment(), form.getMovieId(), email);
			CommentModel.save(comment);
			String note = "评论上传成功！";
			mav.addObject("note",note);
		}
		return mav;
	}
	
	
	@RequestMapping("goToRank")
	public ModelAndView getRankPage(HttpSession session,@RequestParam("movieId") String mId){
		ModelAndView mav = new ModelAndView(rankMoviePage);
		mav.addObject("movieId", mId);
		
		
		return mav;
	}
}
