package no.uib.smo015.info233.oblig2.UIBRoomApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.GUI.Gui;
import no.uib.smo015.info233.oblig2.Parser.Parser;
import no.uib.smo015.info233.oblig2.Util.InternetUtil;

public class UibRoomApp {

	private static Gui gui;
	private static Parser parser;

	public static void main(String[] args) {
		
		parser = new Parser(
				"http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233");

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new Gui(parser);
				populateList(parser, gui.getListModel());
				gui.getUrlLabel().setText("Status ok");
				if(!InternetUtil.hasConnectivity()){
					readFromFile("testGui2");
				}
			}
		});
		
		
	}

	/**
	 * Method to populate a list with activities
	 * @param parser
	 * @param listModel
	 */
	public static void populateList(Parser parser, DefaultListModel<Activity> listModel) {
		gui.setActivityDataList(parser.getActivityList());
		listModel.clear();
		for (Activity a : gui.getActivityDataList()) {
			listModel.addElement(a);
		}
	}

	/**
	 * Method to save a file
	 * @param listOfObjects L
	 * @param fileName
	 * @return true if the file is saved, false otherwise
	 */
	public static boolean saveFile(List<Activity> listOfObjects, String fileName) {
		FileOutputStream output;
		List<Activity> activityList = parser.getActivityList();
		try {
			System.out.println("Writing activities as objects...");
			int index = 0;
			for(Activity ac : activityList){
				System.out.println(index + " " + ac.getType());
				index++;
			}
			System.out.println(index + " activities written to file");
			output = new FileOutputStream(fileName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(output);
			out.writeObject(activityList);
			out.close();
			output.close();
			System.out.println(fileName + " was written to a file");
			return true;
		} catch (IOException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * Method to read from a file
	 * 
	 * @param fileName
	 * @return a List of activities
	 */
	public static List<Activity> readFromFile(String fileName) {
		File inputFile = new File(fileName + ".ser");
		if (inputFile.exists()) {
			System.out.println("The file " + fileName + " exists");
			FileInputStream input = null;
			List<Activity> activityList = new ArrayList<Activity>();
			try {
				input = new FileInputStream(inputFile);
				ObjectInputStream obInput = new ObjectInputStream(input);
				activityList = (List<Activity>) obInput.readObject();
				System.out
				.println("The activity was successfully written back to memory");
				System.out.println("Activities retrieved: " + activityList.size());
				
				for(Activity ac : activityList){
					System.out.println(ac);
					gui.getListModel().addElement(ac);
				}
				
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
		}
//		else if(InternetUtil.hasConnectivity()) {
//			parser = new Parser("http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233");
//			return parser.getActivityList();
//		} else {
//			System.out.println("Please connect to the Internet");
//			return null;
//		}
		return null;
	}

}
