package com.cineplex.tools;

import java.util.TimerTask;

public class DailyTast extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		VIPStatusCheck.checkVIP();
	}
	
}
