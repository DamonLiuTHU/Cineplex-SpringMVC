package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cineplex.model.impl.StatisticsModel;

public class TotalUsageForSeatTest {

	@Test
	public void test() {
		int seatId = 1;
		double result = StatisticsModel.getTotalUsageForSeat(seatId);
		System.out.println(result);
		seatId = 3;
		result = StatisticsModel.getTotalUsageForSeat(seatId);
		System.out.println(result);
	}

}
