package com.cineplex.model.tables;

import java.sql.Time;

public class Plan {
	int movieId;
	Time start;
	Time end;
	int waiterId;
	int hallId;
	String state;
	
	
	public Plan(int movieId, Time start, Time end, int waiterId, int hallId,
			String state) {
		super();
		this.movieId = movieId;
		this.start = start;
		this.end = end;
		this.waiterId = waiterId;
		this.hallId = hallId;
		this.state = state;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public Time getStart() {
		return start;
	}
	public void setStart(Time start) {
		this.start = start;
	}
	public Time getEnd() {
		return end;
	}
	public void setEnd(Time end) {
		this.end = end;
	}
	public int getWaiterId() {
		return waiterId;
	}
	public void setWaiterId(int waiterId) {
		this.waiterId = waiterId;
	}
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
