package no.uib.smo015.info233.oblig2.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import no.uib.smo015.info233.oblig2.GUI.Gui;
import no.uib.smo015.info233.oblig2.Parser.Parser;
import no.uib.smo015.info233.oblig2.UIBRoomApp.UibRoomApp;
import no.uib.smo015.info233.oblig2.Util.InternetUtil;

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
			if(InternetUtil.hasConnectivity()){
				Parser parser = new Parser(searchUrl);
				parser.docToLists();
				UibRoomApp.populateList(parser, gui.getListModel());
//				parser.setActivityList(gui.getActivityDataList());
				System.out.println("Antall aktiviteter " + gui.getListModel().size());
				System.out.println("Antall aktiviteter i følge parseren " + parser.getActivityList().size());
//				gui.setActivityDataList(parser.getActivityList());
				if(gui.getActivityDataList().size() > 0){
					gui.getUrlLabel().setText("Status ok");
					gui.getActivityList().setSelectedIndex(0);
					gui.getActivityList().setEnabled(true);
				} else {
					gui.getUrlLabel().setText("Ingen info tilgjengelig for valgt emne");
					gui.getDescLabel().setText("Beskrivelse: ");
					gui.getRoomLabel().setText("Rom: ");
					gui.getTimeEndLabel().setText("Slutt: ");
					gui.getTimeStartLabel().setText("Start: ");
					gui.getTypeLabel().setText("Type: ");
					gui.getActivityList().setEnabled(true);
				}
			} else {
				JOptionPane.showMessageDialog(gui, "Please connect to internet");
				gui.getActivityList().setEnabled(false);
			}
		}

	}

}
