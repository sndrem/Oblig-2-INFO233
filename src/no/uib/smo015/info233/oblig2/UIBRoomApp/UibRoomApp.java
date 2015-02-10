package no.uib.smo015.info233.oblig2.UIBRoomApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Parser.Parser;

public class UibRoomApp {
	
	public static void main(String[] args) {
		Parser p = new Parser("http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233");
		p.docToLists();
		
		List<Activity> list = p.getActivityList();
		List<String> times = p.getDateStringList();
		
			for(int i = 0; i < list.size(); i++){
				System.out.println(list.get(i).getType() + " " +list.get(i).getRoom() + " " + list.get(i).getDescription() + ". KL: " + 
						times.get(i));
			}
				
	}
	
	public static boolean saveFile(Object o, String fileName){
		FileOutputStream output;
		try {
			output = new FileOutputStream(fileName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(output);
			out.writeObject(o);
			out.close();
			output.close();
			System.out.println(fileName + " was written to a file");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
	
	public static Activity readFromFile(String fileName){
		FileInputStream input;
		Activity activity = null;
		try {
			input = new FileInputStream(fileName + ".ser");
			ObjectInputStream obInput = new ObjectInputStream(input);
			activity = (Activity) obInput.readObject();
			System.out.println("The activity was successfully written back to memory");
			input.close();
			obInput.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activity;
	}

}
