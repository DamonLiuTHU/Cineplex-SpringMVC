package com.cineplex.tools;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TaskController implements ServletContextListener{

	private Timer timer = null;  
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer=new Timer(true);  
		DailyTast t = new DailyTast();
        timer.schedule(t,0,1000*24*60*60);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		if(timer!=null)
			timer.cancel();  
	}
	
}
