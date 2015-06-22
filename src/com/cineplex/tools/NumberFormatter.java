package com.cineplex.tools;

public class NumberFormatter {
	public static double formate(double number){
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
		df.format(number);
		return number;
	}
}
