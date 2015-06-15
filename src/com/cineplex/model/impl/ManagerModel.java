package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import com.cineplex.model.enums.State;
import com.cineplex.model.forms.LoginForm;
import com.cineplex.model.forms.PlanForm;
import com.cineplex.model.tables.Plan;

public class ManagerModel {
	
	public static boolean isPswValid(LoginForm lf){
		String sql = "select * from manager where Id=? and Psw=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,lf.getPhone());
			ps.setString(2,lf.getPassword());
			ps.execute();
			ResultSet rs = ps.getResultSet();
			return rs.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static ArrayList<String> getWaiters(String hallId){
		String sql = "select DISTINCT waiterId from plans where hallId=? ORDER BY waiterId";
		ArrayList<String> result = new ArrayList<String>();
//		PreparedStatement ps = DBTools.getPreStmt(sql);
		Connection con = DBTools.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,hallId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				result.add(rs.getString("waiterId"));
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static ArrayList<String> getWaiters() {
		// TODO Auto-generated method stub
		String sql = "select DISTINCT waiterId from plans ORDER BY waiterId";
		ArrayList<String> result = new ArrayList<String>();
//		PreparedStatement ps = DBTools.getPreStmt(sql);
		Connection con  = DBTools.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			 ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				result.add(rs.getString("waiterId"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static PlanForm getPlan(String hallId,String waiterId){
		String sql = "select * from plans,movie where movie.id=plans.movieId and hallId=? and waiterId=? and plans.state=?";
//		String sql = "select * from plans,movie where movie.id=plans.movieId and hallId=? and waiterId=? ";
//		PreparedStatement ps = DBTools.getPreStmt(sql);
		Connection con = DBTools.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, hallId);
			ps.setString(2,waiterId);
			String statekeyword = State.CHECKING.toString();
			ps.setString(3, statekeyword);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			String moviename = null ;
			String movieId = null;
			ArrayList<String> start = new ArrayList<String>();
			ArrayList<String> end = new ArrayList<String>();
			while(rs.next()){
				moviename = rs.getString("name");
				movieId = rs.getString("movieId");
				start.add(rs.getString("start"));
				end.add(rs.getString("end"));
			}
			String[] startlist = start.toArray(new String[0]);
			String[] endlist =  end.toArray(new String[0]);
			PlanForm pf = new PlanForm(hallId, movieId, startlist, endlist, moviename);
			return pf;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/*
	 * 将plan表中，对应的项覆盖到 hall中
	 */
	public static void accepetPlan(String hallId, String waiterId) {
		// TODO Auto-generated method stub
	
		ArrayList<Plan> planlist = getWaiterPlanList(hallId,waiterId);
		
		//委托给HallModel去做。
		HallModel.updateHallArrange(planlist);
		
		
		//还要负责修改其他这个hall的plan的state字段refused ,实现状态的修改 。
		changeStateForAcceptPlans(hallId,waiterId);
		
	}
	
	/*
	 * 为接受了的计划修改状态信息。
	 */
	private static void changeStateForAcceptPlans(String hallId,String waiterId){
		String sql = "update plans set state=? where hallId=? and waiterId=? and state = ?";
		String sql_2 = "update plans set state=? where hallId=? and waiterId<>? and state=?";
		Connection con = DBTools.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,State.ACCEPT.toString());
			ps.setString(2,hallId);
			ps.setString(3,waiterId);
			ps.setString(4,State.CHECKING.toString());
			ps.execute();
			
			ps = con.prepareStatement(sql_2);
			ps.setString(1,State.REFUSED.toString());
			ps.setString(2,hallId);
			ps.setString(3,waiterId);
			ps.setString(4,State.CHECKING.toString());
			ps.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static ArrayList<Plan> getWaiterPlanList(String hallId, String waiterId){
		String sql = "select * from plans where hallId=? and waiterId=?";
		ArrayList<Plan> planlist = new ArrayList<Plan>(10);
//		PreparedStatement ps = DBTools.getPreStmt(sql);
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, hallId);
			ps.setString(2, waiterId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				int movieId = rs.getInt("movieId");
//				int waiterId = rs.getInt("waiterId");
//				int 
				Time start = (Time) rs.getObject("start");
				Time end = (Time) rs.getObject("end");
				String state = rs.getString("state");
				Plan temp_p = new Plan(movieId, start, end, Integer.parseInt(waiterId), Integer.parseInt(hallId), state);
				planlist.add(temp_p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return planlist;
	}

	
	/*
	 * 经理在不同意该服务员的计划时调用。 将这个plan符合项state置成refuse
	 */
	public static void refusePlan(String hallId, String waiterId) {
		// TODO Auto-generated method stub
		String sql ="update plans set state=? where hallId=? and waiterId=? and state=?";
//		PreparedStatement ps = DBTools.getPreStmt(sql);
		Connection con = DBTools.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,State.REFUSED.toString());
			ps.setString(2,hallId);
			ps.setString(3,waiterId);
			ps.setString(4,State.CHECKING.toString());
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}
}
