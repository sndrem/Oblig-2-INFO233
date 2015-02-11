package no.uib.smo015.info233.oblig2.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import no.uib.smo015.info233.oblig2.GUI.Gui;
import no.uib.smo015.info233.oblig2.Parser.Parser;
import no.uib.smo015.info233.oblig2.UIBRoomApp.UibRoomApp;

public class Event implements ActionListener {
	
	private Gui gui;
	public Event(Gui gui){
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == gui.getSearchComboBox()){
			String searchTerm = (String) gui.getSearchComboBox().getSelectedItem();
			String searchUrl = "http://rom.app.uib.no/ukesoversikt/?entry=emne&input=" + searchTerm;
//			gui.getListModel().clear();
			UibRoomApp.populateList(new Parser(searchUrl), gui.getListModel());
			gui.getUrlLabel().setText("Status: ok");	
		}

	}

}
