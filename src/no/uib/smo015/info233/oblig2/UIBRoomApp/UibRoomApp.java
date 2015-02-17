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

		parser = new Parser("http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233");
		
//		List<Activity> dates = parser.getActivityList();
//		for(Activity a : dates){
//			System.out.println(a.getDescription());
//		}
		

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new Gui(parser);
				if(InternetUtil.hasConnectivity()){
					populateList(parser, gui.getListModel());
					gui.getUrlLabel().setText("Status ok");
				} else {
					readFromFile("testGui2");
					gui.getUrlLabel().setText("Internett er nede");
				}					
			}
		});


	}

	/**
	 * Method to 
	 * @param parser
	 * @param listModel
	 */
	public static void populateList(Parser parser,
			DefaultListModel<Activity> listModel) {
		listModel.clear();
		for (Activity a : parser.getActivityList()) {
			listModel.addElement(a);

		}
	}

	/**
	 * Method to save a file
	 * @param listOfObjects
	 * @param fileName
	 * @return true if the file is saved, false otherwise
	 */
	public static boolean saveFile(List<Activity> listOfObjects, String fileName) {
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
	 * @return a list of activities
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
