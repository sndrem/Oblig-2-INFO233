package no.uib.smo015.info233.oblig2.Events;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.GUI.Gui;

public class ListEvent implements ListSelectionListener {
	
	private Gui gui;
	
	public ListEvent(Gui gui){
		this.gui = gui;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Activity activity = gui.getActivityList().getSelectedValue();
		if(activity != null){
			gui.getDescLabel().setText("Beskrivelse: " + activity.getDescription());
			gui.getTypeLabel().setText("Type: " + activity.getType());
			gui.getTimeStartLabel().setText("Starter: " + activity.getStartTimeString());
			gui.getTimeEndLabel().setText("Slutter: " + activity.getEndTimeString());
			gui.getRoomLabel().setText("Rom: " + activity.getRoom());
		} 
		
	}

}
