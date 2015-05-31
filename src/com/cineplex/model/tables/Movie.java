package com.cineplex.model.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Movie {
	private String name;
	private String country;
	private String company;
	private String price;
	private String long_description;
	private String publish_date;
	private String director;
	private String main_star;
	private String type;
	private String short_description;
	@Id
	@GeneratedValue
	private String id;
	private String poster;

	public Movie(String name, String country, String company, String price,
			String long_description, String publish_date, String director,
			String main_star, String type, String short_description, String id,
			String poster) {
		super();
		this.name = name;
		this.country = country;
		this.company = company;
		this.price = price;
		this.long_description = long_description;
		this.publish_date = publish_date;
		this.director = director;
		this.main_star = main_star;
		this.type = type;
		this.short_description = short_description;
		this.id = id;
		this.poster = poster;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", country=" + country + ", company="
				+ company + ", price=" + price + ", long_description="
				+ long_description + ", publish_date=" + publish_date
				+ ", director=" + director + ", main_star=" + main_star
				+ ", type=" + type + ", short_description=" + short_description
				+ ", id=" + id + ", poster=" + poster + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLong_description() {
		return long_description;
	}

	public void setLong_description(String long_description) {
		this.long_description = long_description;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getMain_star() {
		return main_star;
	}

	public void setMain_star(String main_star) {
		this.main_star = main_star;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Movie(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
