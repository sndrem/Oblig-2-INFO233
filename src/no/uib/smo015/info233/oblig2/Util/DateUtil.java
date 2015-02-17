package no.uib.smo015.info233.oblig2.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 
 * @author Sindre A util class used to easy extract the start time or end time
 *         for a given activity as well as to format a string to a given date format
 *
 */
public class DateUtil {

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
	 * Method to format a string with a given date
	 * @param timeDateString
	 * @param format
	 * @return Calendar a calender object with a date
	 */
	public static Calendar stringToCalendar(String timeDateString, String format){
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			cal.setTime(sdf.parse(timeDateString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cal;
	}
	
	/**
	 * Method to remove the name of the days such as "Mandag", "Tirsdag" and so forth.
	 * @return String 
	 */
	public static String removeNameOfDay(String date){
		String[] dateSplitter = date.split(" ");
		if(dateSplitter.length > 1){
			return dateSplitter[1];
		}
		return null;
	}

	/**
	 * 
	 * @param time
	 *            The string must be of type xx:xx-xx:xx
	 * @return String The end time of a time string.
	 */
	public static String getEndTime(String time) {
		String[] times = time.split("-");
		return times[1];
	}

	/**
	 * Method to return this weeks week number
	 * @return int The number of the week
	 */
	public static int getWeekNumber() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.DATE, 0);

		int weekNumber = gc.get(Calendar.WEEK_OF_YEAR);

		return weekNumber;
	}
		
}
