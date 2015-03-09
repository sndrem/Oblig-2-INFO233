package no.uib.smo015.info233.oblig2.Events;

import java.text.SimpleDateFormat;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.GUI.Gui;

public class ListEvent implements ListSelectionListener {
	
	private Gui gui;
	
	public ListEvent(){
		this.gui = Gui.getInstance();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Activity activity = gui.getActivityList().getSelectedValue();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMM, yyyy");
		if(activity != null){
			gui.getWeekDayLabel().setText("Dag: " + sdf.format(activity.getStartTime().getTime()).substring(0,1).toUpperCase() + sdf.format(activity.getStartTime().getTime()).substring(1));
			gui.getDescLabel().setText("Beskrivelse: " + activity.getDescription());
			gui.getTypeLabel().setText("Type: " + activity.getType());
			gui.getTimeStartLabel().setText("Starter: " + activity.getStartTimeString());
			gui.getTimeEndLabel().setText("Slutter: " + activity.getEndTimeString());
			gui.getRoomLabel().setText("Rom: " + activity.getRoom());
		} 
		
	}

}
