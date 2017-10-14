package service;

import java.util.Calendar;
import java.util.Date;

public class CheckWorkWeek {

	public static boolean isWorkWeek(Date settlementDate, String currency) {
		Calendar c = Calendar.getInstance();
		c.setTime(settlementDate);
		boolean isWorkWeek = true;

		if (currency.equals("SAR") || currency.equals("AED")) {

			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
					|| c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				isWorkWeek = false;
			}
		} else {
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				isWorkWeek = false;
			}
		}
		return isWorkWeek;
	}
}
