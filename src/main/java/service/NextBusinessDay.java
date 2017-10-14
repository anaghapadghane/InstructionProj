package service;

import java.util.Calendar;
import java.util.Date;

public class NextBusinessDay {

	public static Date setNextBusinessDay(Date settlementdate, String currency) {
		Calendar c = Calendar.getInstance();
		c.setTime(settlementdate);
		if (currency.equals("SAR") || currency.equals("AED")) {

			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				c.add(Calendar.DAY_OF_WEEK, 2);
			} else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				c.add(Calendar.DAY_OF_WEEK, 1);
			}

		} else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			c.add(Calendar.DAY_OF_WEEK, 2);

		} else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			c.add(Calendar.DAY_OF_WEEK, 1);
		}
		settlementdate = c.getTime();
		return settlementdate;
	}
}
