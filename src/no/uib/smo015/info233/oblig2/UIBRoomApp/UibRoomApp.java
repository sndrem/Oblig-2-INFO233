package no.uib.smo015.info233.oblig2.UIBRoomApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Events.Event;
import no.uib.smo015.info233.oblig2.Events.ListEvent;
import no.uib.smo015.info233.oblig2.GUI.Gui;
import no.uib.smo015.info233.oblig2.Parser.Parser;
import no.uib.smo015.info233.oblig2.Util.InternetUtil;

/**
 * Class used to represent the application as a whole. The application can fetch activities from a given url, and 
 * parse the data back into a list which is easy to look trough. The list is sorted from monday to friday.
 * @author sindremoldeklev
 *
 */
public class UibRoomApp {

	private static Gui gui;
	private static Parser parser;

	/**
	 * Main method for the application
	 * @param args
	 */
	public static void main(String[] args) {

		parser = new Parser("http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233");
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = Gui.getInstance();
				assignActionListeners();
				if(InternetUtil.hasConnectivity()){
					populateList(parser, gui.getListModel());
					gui.getActivityList().setSelectedIndex(0);
					gui.getUrlLabel().setText("Status ok");
					gui.getLoadButton().setEnabled(false);
				} else {
					List<Activity> activityList = readFromFile("testGui2");
					DefaultListModel<Activity> activityModel = new DefaultListModel<>();
					for(Activity a : activityList){
						activityModel.addElement(a);
					}
					populateList(activityModel);
					gui.getUrlLabel().setText("Internett er nede");
				}					
			}
		});
	}

	/**
	 * Method to assign actionListeners to the gui components
	 */
	private static void assignActionListeners(){
		gui.getLoadButton().addActionListener(new Event());
		gui.getSaveButton().addActionListener(new Event());
		gui.getSearchButton().addActionListener(new Event());
		gui.getSearchComboBox().addActionListener(new Event());
		gui.getActivityList().addListSelectionListener(new ListEvent());
	}
	
	/**
	 * Method to populate the list of the gui with activities
	 * @param parser
	 * @param listModel
	 */
	public static void populateList(Parser parser,
			DefaultListModel<Activity> listModel) {
		listModel.clear();
		// Sort the list so that the activities are sorted by start time
		Collections.sort(parser.getActivityList(), new Comparator<Activity>() {
			public int compare(Activity o1, Activity o2) {
				return o1.getStartTime().compareTo(o2.getStartTime());
			}
		});
		for (Activity a : parser.getActivityList()) {
			listModel.addElement(a);
		}
	}
	
	/**
	 * Method to populate the list of the gui with activities. This method does not use the parser
	 * @param activityModel
	 */
	public static void populateList(DefaultListModel<Activity> activityModel){
		List<Activity> tempList = new ArrayList<>();
		for(int i = 0; i < activityModel.size(); i++){
			tempList.add(activityModel.get(i));
		}
		Collections.sort(tempList, new Comparator<Activity>() {
			public int compare(Activity o1, Activity o2) {
				return o1.getStartTime().compareTo(o2.getStartTime());
			}
		});
		
		for(Activity a : tempList){
			gui.getListModel().addElement(a);
		}
		
		
	}

	/**
	 * Method to save a file with activities
	 * The user does not need to provide a file ending, but it works even though
	 * the user wrote a file ending such as .ser
	 * @param fileName
	 * @return boolean true if the file is saved, false otherwise
	 */
	public static boolean saveFile(String fileName) {
		FileOutputStream output;
		DefaultListModel<Activity> listModel = gui.getListModel();
		List<Activity> activityList = gui.getActivityDataList();
		activityList.clear();
		for(int i = 0; i < listModel.size(); i++){
			activityList.add(listModel.get(i));
		}
		try {
			output = new FileOutputStream(fileName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(output);
			out.writeObject(activityList);
			System.out.println("Writing objects....");
			out.close();
			output.close();
			System.out.println(fileName + ".ser was written to a file");
			return true;
		} catch (IOException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Method to retrieve a serialized file
	 * @param fileName
	 * @return DefaultListModel<Activity>
	 */
	@SuppressWarnings("unchecked")
	public static DefaultListModel<Activity> fetchSerializedFile(String fileName){
		DefaultListModel<Activity> activityModel = new DefaultListModel<>();
		File inputFile = new File(fileName + ".ser");
		if(inputFile.exists()){
			FileInputStream inputStream = null;
			ObjectInputStream obInput = null;
			
			try{
				inputStream = new FileInputStream(inputFile);
				obInput = new ObjectInputStream(inputStream);
				
				List<Activity> tempList = new ArrayList<>();
				
				tempList = (List<Activity>) obInput.readObject();
				
				for(Activity activity : tempList){
					activityModel.addElement(activity);
				}
				
			} catch (IOException | ClassNotFoundException e){
				e.printStackTrace();
			} finally{
				try{
					inputStream.close();
					obInput.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		return activityModel;
	}

	/**
	 * Method to read from a file
	 * @param fileName
	 * @return a list of activities
	 */
	@SuppressWarnings("unchecked")
	public static List<Activity> readFromFile(String fileName) {
		File inputFile = new File(fileName + ".ser");
		if (inputFile.exists()) {
			FileInputStream input = null;
			List<Activity> activityList = new ArrayList<Activity>();
			try {
				input = new FileInputStream(inputFile);
				ObjectInputStream obInput = new ObjectInputStream(input);

				activityList = (List<Activity>) obInput.readObject();

				input.close();
				obInput.close();
				return activityList;

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(gui, "Ingen fil med data tilgjengelig", "Ooops", JOptionPane.ERROR_MESSAGE);
		}

		return null;
	}

}
