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
	 * @param weekDay
	 */
	public Activity(Node activityNode, String type, String room, String roomCode, String description, String startStringTime, String endStringTime, String weekDay){
		this.type = type;
		this.room = roomCode + " " + room;
		this.description = description;
		this.startTimeString = startStringTime;
		this.endTimeString = endStringTime;
		this.setActivityNode(activityNode);
		
		String format = "HH:mm dd.MM.yyyy";
		setStartTime(DateUtil.stringToCalendar(startStringTime + " " + DateUtil.removeNameOfDay(weekDay), format));
		setEndTime(DateUtil.stringToCalendar(endStringTime + " " + DateUtil.removeNameOfDay(weekDay), format));
	}

	public String toString(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMM. yyyy HH:mm");
		String info = type + ", Bygg: " + room + ", " +  sdf.format(startTime.getTime()) + " og slutter " + sdf.format(endTime.getTime()) + " "
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

	/**
	 * @return the startTimeString
	 */
	public String getStartTimeString() {
		return startTimeString;
	}

	/**
	 * @param startTimeString the startTimeString to set
	 */
	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}

	/**
	 * @return the endTimeString
	 */
	public String getEndTimeString() {
		return endTimeString;
	}

	/**
	 * @param endTimeString the endTimeString to set
	 */
	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}

	/**
	 * @return the startTime
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the activityNode
	 */
	public Node getActivityNode() {
		return activityNode;
	}

	/**
	 * @param activityNode the activityNode to set
	 */
	public void setActivityNode(Node activityNode) {
		this.activityNode = activityNode;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	

}
