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
	
	public Activity(String type, String room, String description){
		this.type = type;
		this.room = room;
		this.description = description;
	}

	@Override
	public Node getNode() {
		// TODO Implementer denne din latsabb
		return null;
	}

	@Override
	public String getType() {
		// TODO Implementer denne din latsabb
		return null;
	}

	@Override
	public String getRoom() {
		// TODO Implementer denne din latsabb
		return null;
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
		// TODO Implementer denne din latsabb
		return null;
	}

}
