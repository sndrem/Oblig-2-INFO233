package no.uib.smo015.info233.oblig2.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.jsoup.nodes.TextNode;

/**
 * 
 * @author Sindre A util class used to easy extract the start time or end time
 *         for a given activity.
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
	 * @return a calender object with a date
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
	 * Method to remove the name of the days
	 */
	public static String removeNameOfDay(String date){
		String[] dateSplitter = date.split(" ");
		return dateSplitter[1];
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
	
	/**
	 * Method to collect the five weekdays
	 */
	public static boolean getWeekDays(TextNode node){
		if(node.text().startsWith("Man") || node.text().startsWith("Tir") || node.text().startsWith("Ons") || node.text().startsWith("Tors") || node.text().startsWith("Fre")){
			return true;
		} else return false;
	}

}
