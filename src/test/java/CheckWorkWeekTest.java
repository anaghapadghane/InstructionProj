

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import service.CheckWorkWeek;

public class CheckWorkWeekTest {
	
	@Test
	public void checkWorkWeekWithCurrencySAR() {
		assertTrue(CheckWorkWeek.isWorkWeek(new Date("12-OCT-17"), "SAR"));
	}

	@Test
	public void checkWorkWeekWithCurrencyAED() {
		assertTrue(CheckWorkWeek.isWorkWeek(new Date("08-OCT-17"), "AED"));
	}
	
	@Test
	public void checkWorkWeekWithCurrencySGP() {
		assertTrue(CheckWorkWeek.isWorkWeek(new Date("13-OCT-17"), "SGP"));
	}
	
	@Test
	public void checkNotWorkWeekWithCurrencySAR() {
		assertFalse(CheckWorkWeek.isWorkWeek(new Date("13-OCT-17"), "SAR"));
	
	}
	
	@Test
	public void checkNotWorkWeekWithCurrencyAED() {
		assertFalse(CheckWorkWeek.isWorkWeek(new Date("13-OCT-17"), "AED"));
		
	}
	@Test
	public void checkNotWorkWeekWithCurrencySGP() {
		assertFalse(CheckWorkWeek.isWorkWeek(new Date("14-OCT-17"), "SGP"));
		
	}
	
}
