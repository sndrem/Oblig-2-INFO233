package no.uib.smo015.info233.oblig2.Activity;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import no.uib.smo015.info233.oblig2.Interfaces.ActivityInterface;
import no.uib.smo015.info233.oblig2.Util.DateUtil;

import org.jsoup.nodes.Node;

public class Activity implements ActivityInterface, Serializable {

	private static final long serialVersionUID = -4962193791986602328L;
	private String type;
	private String room;
	private String description;
	private String startTimeString, endTimeString;
	private Calendar startTime, endTime;
	private transient Node activityNode;
	
	public Activity(Node activityNode, String type, String room, String description, String startStringTime, String endStringTime, String weekDay){
		this.type = type;
		this.room = room;
		this.description = description;
		this.startTimeString = startStringTime;
		this.endTimeString = endStringTime;
		this.setActivityNode(activityNode);
		
		String format = "HH:mm dd.MM.yyyy";
		setStartTime(DateUtil.stringToCalendar(startStringTime + " " + DateUtil.removeNameOfDay(weekDay), format));
		setEndTime(DateUtil.stringToCalendar(endStringTime + " " + DateUtil.removeNameOfDay(weekDay), format));
		
		
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
	
	public String toString(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE/MMM - yyyy HH:mm");
		String info = type + " skjer på " + room + ", " +  sdf.format(startTime.getTime()) + " og slutter " + sdf.format(endTime.getTime()) + " "
				+ " Beskrivelse: " + description;
		return info;
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
