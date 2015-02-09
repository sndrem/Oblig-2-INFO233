package no.uib.smo015.info233.oblig2.Util;

/**
 * 
 * @author Sindre
 * A util class used to easy extract the start time or end time for a given activity.
 *
 */
public class DateUtil {
	
	public DateUtil(){
	}
	
	/**
	 * 
	 * @param time The string must be of type xx:xx-xx:xx
	 * @return the start time of an activity
	 */
	public static String getStartTime(String time){
		String[] times = time.split("-");
		return times[0];
	}
	
	/**
	 * 
	 * @param time	The string must be of type xx:xx-xx:xx
	 * @return The end time of a time string.
	 */
	public static String getEndTime(String time){
		String[] times = time.split("-");
		return times[1];
	}
	
	
	
}
