

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import service.NextBusinessDay;

public class NextBusinessDayTest {

	@Test
	public void testForWorkWeekWithCurrencySAR() {
		assertEquals(new Date("12-OCT-17"), NextBusinessDay.setNextBusinessDay(new Date("12-OCT-17"), "SAR"));
	}
	
	@Test
	public void testForWorkWeekWithCurrencyAED() {
		assertEquals(new Date("08-OCT-17"), NextBusinessDay.setNextBusinessDay(new Date("08-OCT-17"), "AED"));
	}
	
	@Test
	public void testForWorkWeekWithCurrencySGP() {
		assertEquals(new Date("04-OCT-17"), NextBusinessDay.setNextBusinessDay(new Date("04-OCT-17"), "SGP"));
	}
	
	@Test
	public void testForNotWorkWeekForCurrencySAR() {
		assertEquals(new Date("15-OCT-17"), NextBusinessDay.setNextBusinessDay(new Date("13-OCT-17"), "SAR"));
	}
	
	@Test
	public void testForNotWorkWeekForCurrencyAED() {
		assertEquals(new Date("15-OCT-17"), NextBusinessDay.setNextBusinessDay(new Date("14-OCT-17"), "AED"));
	}
	@Test
	public void testForNotWorkWeekSaturdayForCurrencySGP() {
		assertEquals(new Date("16-OCT-17"), NextBusinessDay.setNextBusinessDay(new Date("14-OCT-17"), "SGP"));
	}
	@Test
	public void testForNotWorkWeekSundayForCurrencySGP() {
		assertEquals(new Date("16-OCT-17"), NextBusinessDay.setNextBusinessDay(new Date("15-OCT-17"), "SGP"));
	}
	
}
