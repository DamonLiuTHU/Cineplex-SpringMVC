package com.cineplex.model.tables;

public class Hall {
	
	
	private String ordernumber;   //在数据库中代表该电影在Hall表中，在哪个时间段被放映。
	private String movieId;  
	private String start;  //开始时间
	private String end;  //结束时间
	private String hallId;  //
	private String left_tickets;
	
	
	
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
	}
	public String getLeft_tickets() {
		return left_tickets;
	}
	public void setLeft_tickets(String left_tickets) {
		this.left_tickets = left_tickets;
	}
	@Override
	public String toString() {
		return "Hall [ordernumber=" + ordernumber + ", movieId=" + movieId
				+ ", start=" + start + ", end=" + end + ", hallId=" + hallId
				+ ", left_tickets=" + left_tickets + "]";
	}
	public Hall(String ordernumber, String movieId, String start, String end,
			String hallId, String left_tickets) {
		super();
		this.ordernumber = ordernumber;
		this.movieId = movieId;
		this.start = start;
		this.end = end;
		this.hallId = hallId;
		this.left_tickets = left_tickets;
	}
	
	
	
	
}
