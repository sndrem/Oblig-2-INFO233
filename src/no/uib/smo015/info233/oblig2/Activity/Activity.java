package no.uib.smo015.info233.oblig2.Activity;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import no.uib.smo015.info233.oblig2.Interfaces.ActivityInterface;
import no.uib.smo015.info233.oblig2.Util.DateUtil;

import org.jsoup.nodes.Node;

/**
 * Class representing an activity
 * @author Sindre
 * @version 0.0.1
 *
 */

public class Activity implements ActivityInterface, Serializable {

	private static final long serialVersionUID = -4962193791986602328L;
	private String type;
	private String room;
	private String description;
	private String startTimeString, endTimeString;
	private Calendar startTime, endTime;
	private transient Node activityNode;
	
	/**
	 * Constructor for the Activity class
	 * @param activityNode
	 * @param type
	 * @param room
	 * @param description
	 * @param startStringTime
	 * @param endStringTime
	 * @param dateAsString
	 */
	public Activity(Node activityNode, String type, String room, String roomCode, String description, String startStringTime, String endStringTime, String dateAsString){
		this.type = type;
		this.room = roomCode + " " + room;
		this.description = description;
		this.startTimeString = startStringTime;
		this.endTimeString = endStringTime;
		this.setActivityNode(activityNode);
		
		String format = "HH:mm dd.MM.yyyy";
		setStartTime(DateUtil.stringToCalendar(startStringTime + " " + DateUtil.removeNameOfDay(dateAsString), format));
		setEndTime(DateUtil.stringToCalendar(endStringTime + " " + DateUtil.removeNameOfDay(dateAsString), format));
	}

	public String toString(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE/MMM - yyyy HH:mm");
		String info = type + " skjer på " + room + ", " +  sdf.format(startTime.getTime()) + " og slutter " + sdf.format(endTime.getTime()) + " "
				+ " Beskrivelse: " + description;
		return info;
	}
	
	@Override
	public Node getNode() {
		return activityNode;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getRoom() {
		return this.room;
	}

	@Override
	public Calendar getBeginTime() {
		return startTime;
	}

	@Override
	public Calendar getEndTime() {
		return endTime;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
	
	public Node getActivityNode() {
		return activityNode;
	}

	public void setActivityNode(Node activityNode) {
		this.activityNode = activityNode;
	}
	

	public String getEndTimeString() {
		return endTimeString;
	}

	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}

	public String getStartTimeString() {
		return startTimeString;
	}

	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
}
