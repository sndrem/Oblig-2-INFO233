package no.uib.smo015.info233.oblig2.Tests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import no.uib.smo015.info233.oblig2.Util.DateUtil;

import org.junit.Test;

public class DateUtilTest {

	/**
	 * Simple test to check that the date util class splits and returns the correct end time 
	 */
	@Test
	public void getEndTimeStringTest() {
		String time = "12:00-14:15";
		assertEquals("14:15", DateUtil.getEndTime(time));
	}
	
	/**
	 * Simple test to check that the date util class splits and returns the correct start time
	 */
	@Test
	public void getStartTimeStringTest(){
		String time = "12:00-14:15";
		assertEquals("12:00", DateUtil.getStartTime(time));
	}
	
	/**
	 * Simple test to check that the date util class removes the name of the day correctly
	 */
	@Test
	public void removeNameOfDayTest(){
		String dateString = "Mandag 24.12.2015";
		assertEquals("24.12.2015", DateUtil.removeNameOfDay(dateString));
	}
	
	/**
	 * Simple test to check that the date util returns the correct week number
	 */
	@Test
	public void getWeekNumberTest(){
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.DATE, 0);
		int weekNumber = gc.get(Calendar.WEEK_OF_YEAR);
		assertEquals(weekNumber, DateUtil.getWeekNumber());
	}
	
	

}
