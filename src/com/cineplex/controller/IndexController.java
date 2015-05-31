package com.cineplex.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cineplex.model.impl.MovieModel;
import com.cineplex.model.tables.Hall;
import com.cineplex.model.tables.Movie;



@Controller
public class IndexController {
	
	
	
	public static ArrayList<Movie> getMovies(){
		
		ArrayList<Movie> movies = MovieModel.getMovies();
		
		return movies;
	}
	
	@RequestMapping("/order")
	public String jumpToOrderPage(@RequestParam(value="movieId") String mId,Map<String,Object> map){
		map.put("mId", mId);
		ArrayList<Hall> list = MovieModel.getArrangements(mId);
		map.put("list", list);
		
		return "order";
	}
	
	
}
