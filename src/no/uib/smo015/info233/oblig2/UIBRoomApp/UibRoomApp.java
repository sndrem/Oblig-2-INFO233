package no.uib.smo015.info233.oblig2.UIBRoomApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.DefaultListModel;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.GUI.Gui;
import no.uib.smo015.info233.oblig2.Parser.Parser;

public class UibRoomApp {


	private static Gui gui;
	private static Parser parser;

	public static void main(String[] args) {
		parser = new Parser("http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233");	
		gui = new Gui(parser);
		populateList(parser, gui.getListModel());
	}
	
	public static void populateList(Parser parser, DefaultListModel<Activity> listModel) {
		gui.setActivityDataList(parser.getActivityList());
		for(Activity a : gui.getActivityDataList()){
			listModel.addElement(a);
		}
	}

	public static boolean saveFile(List<Activity> listOfObjects, String fileName){
		FileOutputStream output;
		try {
			output = new FileOutputStream(fileName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(output);
			for(Object object : listOfObjects){
				out.writeObject(object);
				out.close();
				output.close();
			}
			System.out.println(fileName + " was written to a file");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}

	/**
	 * Method to read from a file
	 * @param fileName
	 * @return a Activity
	 */
	public static boolean readFromFile(String fileName){
		FileInputStream input = null;
		try {
			input = new FileInputStream(fileName + ".ser");
			ObjectInputStream obInput = new ObjectInputStream(input);
			parser.addActivity( (Activity) obInput.readObject());
			System.out.println("The activity was successfully written back to memory");
			input.close();
			obInput.close();
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try{
				input.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

}
