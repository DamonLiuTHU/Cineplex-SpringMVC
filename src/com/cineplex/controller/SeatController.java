package com.cineplex.controller;

import org.springframework.stereotype.Controller;

import com.cineplex.model.impl.StatisticsModel;


@Controller
public class SeatController {
	/*
	 * 获取一个座位在所有的影厅所有时段的上座率。
	 * @return 小于1的值
	 */
	public static double getTotalUsage(int seatId)
	{
		return StatisticsModel.getTotalUsageForSeat(seatId);
	}
}
