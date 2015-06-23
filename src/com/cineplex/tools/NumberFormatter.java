package com.cineplex.tools;

import java.text.NumberFormat;

public class NumberFormatter {
	public static double formate(double number){
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
		df.format(number);
		return number;
	}
	
	
	public static String percentage(double value){
		NumberFormat nt = NumberFormat.getPercentInstance();
	  	nt.setMinimumFractionDigits(2);
	   	return nt.format(value);
	}
}
