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
	private Calendar startTime, endTime;
	
	public Activity(String type, String room, String description){
		this.type = type;
		this.room = room;
		this.description = description;
		startTime = Calendar.getInstance();
		endTime = Calendar.getInstance();
	}

	@Override
	public Node getNode() {
		// TODO Implementer denne din latsabb
		return null;
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
	
	public String toString(){
		// TODO Implementer en deilig toString metode her 
		return null;
	}
	
	

}
