package com.cineplex.model.forms;

import java.util.Arrays;


public class PlanForm {
	private String hallId;
	private String movieId;
	private String[] start;
	private String[] end;
	private String moviename;
	
	public PlanForm(String hallId, String movieId, String[] start,
			String[] end, String moviename) {
		super();
		this.hallId = hallId;
		this.movieId = movieId;
		this.start = start;
		this.end = end;
		this.moviename = moviename;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String[] getStart() {
		return start;
	}
	public void setStart(String[] start) {
		this.start = start;
	}
	public String[] getEnd() {
		return end;
	}
	public void setEnd(String[] end) {
		this.end = end;
	}
	public PlanForm(String hallId, String movieId, String[] start, String[] end) {
		super();
		this.hallId = hallId;
		this.movieId = movieId;
		this.start = start;
		this.end = end;
	}
	@Override
	public String toString() {
		return "PlanForm [hallId=" + hallId + ", movieId=" + movieId
				+ ", start=" + Arrays.toString(start) + ", end="
				+ Arrays.toString(end) + "]";
	}
	public PlanForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
