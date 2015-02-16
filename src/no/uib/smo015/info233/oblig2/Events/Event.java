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
			if(InternetUtil.hasConnectivity()){
				String searchTerm = (String) gui.getSearchComboBox().getSelectedItem();
				String searchUrl = "http://rom.app.uib.no/ukesoversikt/?entry=emne&input=" + searchTerm;
				gui.getActivityDataList().clear();
				Parser parser = new Parser(searchUrl, gui.getActivityDataList());
				UibRoomApp.populateList(parser, gui.getListModel());
				parser.docToLists();
				
				if(gui.getActivityDataList().size() > 0){
					gui.getUrlLabel().setText("Status ok");
					gui.getActivityList().setSelectedIndex(0);
				} else {
					gui.setBlankLabels();
				}
			} else {
				JOptionPane.showMessageDialog(gui, "Vennligst koble deg til internett", "Internett er nede", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
