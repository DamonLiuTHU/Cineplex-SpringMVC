package com.cineplex.controller;

import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cineplex.model.impl.HallModel;
import com.cineplex.model.impl.MovieModel;
import com.cineplex.model.impl.OrderModel;
import com.cineplex.model.impl.UserModel;
import com.cineplex.model.tables.Hall;
import com.cineplex.model.tables.Order;



@Controller
public class OrderController {
	
	
	public static ArrayList<Hall> getArrangement(String movieId){
		return MovieModel.getArrangements(movieId);
	}
	
	
	/*
	 * @param Id 代表数据库中唯一标示一个 电影+播放时间+播放大厅 的记录。  即指定一张电影票。  数据库的设计不大合理，日后理解起来可能有问题。
	 * 方法接受来自客户端的请求，为用户购买一张电影票(先暂存用户的
	 */
	@RequestMapping("/buyTicket")
	public String buyTicket(@RequestParam("seatlist") String seatlist,@RequestParam("Id") String Id,@RequestParam("phone") String phone){
		String[] seats = seatlist.split(",");
		int ticket_number = seats.length;
		int price = HallModel.getPrice(Id);
		int total  = ticket_number*price;
		int user_balance = UserModel.getBalance(phone);
		if(user_balance >= total){
			MovieModel.reduceTicket(Id,ticket_number);
			UserModel.reduceBalance(phone, total);
			for(String seat : seats){
				OrderModel.makeOrder(phone, Id, seat);
				
			}
			return "redirect:myaccount.jsp";
		}else{
			return "redirect:balanceNotEnough.jsp";
		}
	}
	
	public static ArrayList<String> getUnavailableSeats(String periodId){
		return OrderModel.getUnavailableSeats(periodId);
	}
	public static LinkedList<Order> getOrders(String phone){
		return OrderModel.getOrders(phone);
	}
}
