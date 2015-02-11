package no.uib.smo015.info233.oblig2.Activity;


import java.io.Serializable;
import java.util.Calendar;

import no.uib.smo015.info233.oblig2.Interfaces.ActivityInterface;

import org.jsoup.nodes.Node;

public class Activity implements ActivityInterface, Serializable {

	private static final long serialVersionUID = -4962193791986602328L;
	private String type;
	private String room;
	private String description;
	private String startTimeString, endTimeString;
	private Calendar startTime, endTime;
	private Node activityNode;
	
	public Activity(Node activityNode, String type, String room, String description, String startStringTime, String endStringTime){
		this.type = type;
		this.room = room;
		this.description = description;
		this.startTimeString = startStringTime;
		this.endTimeString = endStringTime;
		this.setActivityNode(activityNode);
		startTime = Calendar.getInstance();
		endTime = Calendar.getInstance();
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
		// TODO Implementer denne din latsabb
		return null;
	}

	@Override
	public Calendar getEndTime() {
		// TODO Implementer denne din latsabb
		return null;
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
		String info = "Aktivitet: " + type + " skjer på " + room + " kl " +  startTimeString + " og slutter kl " + endTimeString + " "
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
	
}
