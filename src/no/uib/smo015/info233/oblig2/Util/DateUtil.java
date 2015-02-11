package no.uib.smo015.info233.oblig2.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author Sindre A util class used to easy extract the start time or end time
 *         for a given activity.
 *
 */
public class DateUtil {

	public DateUtil() {
	}

	/**
	 * 
	 * @param time
	 *            The string must be of type xx:xx-xx:xx
	 * @return the start time of an activity
	 */
	public static String getStartTime(String time) {
		String[] times = time.split("-");
		return times[0];
	}

	/**
	 * 
	 * @param time
	 *            The string must be of type xx:xx-xx:xx
	 * @return The end time of a time string.
	 */
	public static String getEndTime(String time) {
		String[] times = time.split("-");
		return times[1];
	}

	/**
	 * Method to return this weeks week number
	 * 
	 * @return weekNumber The number of the week
	 */
	public static int getWeekNumber() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.DATE, 0);

		int weekNumber = gc.get(Calendar.WEEK_OF_YEAR);

		return weekNumber;
	}
	
	/**
	 * Method to return todays date and time as a string
	 * @return date	Todays date
	 */
	public static String getTodaysDate(){
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime()); 
		
		return date;
	}

}
