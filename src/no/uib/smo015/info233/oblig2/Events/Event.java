package no.uib.smo015.info233.oblig2.Events;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import no.uib.smo015.info233.oblig2.GUI.Gui;
import no.uib.smo015.info233.oblig2.Parser.Parser;
import no.uib.smo015.info233.oblig2.UIBRoomApp.UibRoomApp;
import no.uib.smo015.info233.oblig2.Util.InternetUtil;

import org.apache.commons.io.FilenameUtils;

/**
 * Class representing an event for a gui object
 * @author Sindre
 * @version 0.0.1
 *
 */
public class Event implements ActionListener {

	private Gui gui;
	
	/**
	 * Constructor for the Event class
	 * @param gui
	 */
	public Event(Gui gui){
		this.gui = gui;
	}

	/**
	 * Method to respond on a user event
	 * @param event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == gui.getSearchComboBox()){
			if(InternetUtil.hasConnectivity()){
				String searchTerm = (String) gui.getSearchComboBox().getSelectedItem();
				String searchUrl = "http://rom.app.uib.no/ukesoversikt/?entry=emne&input=" + searchTerm;
				gui.getActivityDataList().clear();
				Parser parser = new Parser(searchUrl, gui.getActivityDataList());
				UibRoomApp.populateList(parser, gui.getListModel());
				parser.docToLists();
				gui.getUrlLabel().setText("Status: ok");
				gui.getTextField().setText("");
				if(gui.getActivityDataList().size() > 0){
					gui.getUrlLabel().setText("Status ok");
					gui.getActivityList().setSelectedIndex(0);
				} else {
					gui.setBlankLabels();
				}
			} else {
				gui.getUrlLabel().setText("Status: Internett er nede");
				gui.getLoadButton().setEnabled(true);
				JOptionPane.showMessageDialog(gui, "Vennligst koble deg til internett", "Internett er nede", JOptionPane.ERROR_MESSAGE);
			}
		} else if (event.getSource() == gui.getLoadButton()){
			JFileChooser chooser = new JFileChooser(".");
			int choice = chooser.showOpenDialog(gui);
			if(choice == JFileChooser.APPROVE_OPTION){
				gui.getListModel().clear();
				File file = chooser.getSelectedFile();
				String fileName = FilenameUtils.removeExtension(file.getName());
				
				UibRoomApp.populateList(UibRoomApp.fetchSerializedFile(fileName));
				gui.getActivityList().revalidate();
			}
			
		} else if (event.getSource() == gui.getSaveButton()){
			JFileChooser chooser = new JFileChooser(".");
			int choice = chooser.showSaveDialog(gui);
			if(choice == JFileChooser.APPROVE_OPTION){
				File file = chooser.getSelectedFile();
				UibRoomApp.saveFile(file.getName());
			}
		} else if (event.getSource() == gui.getSearchButton()){
			String searchResult = gui.getTextField().getText();
			if(searchResult != null){
				String searchUrl = "http://rom.app.uib.no/ukesoversikt/?entry=emne&input=" + searchResult;
				gui.getActivityDataList().clear();
				Parser parser = new Parser(searchUrl, gui.getActivityDataList());
				UibRoomApp.populateList(parser, gui.getListModel());
				parser.docToLists();
				gui.getUrlLabel().setText("Status: ok");
				if(gui.getActivityDataList().size() > 0){
					gui.getUrlLabel().setText("Status ok");
					gui.getActivityList().setSelectedIndex(0);
				} else {
					gui.setBlankLabels();
				}
			}
		}

	}

}
