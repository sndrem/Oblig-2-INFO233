package no.uib.smo015.info233.oblig2.Events;

import java.awt.event.WindowListener;

import no.uib.smo015.info233.oblig2.UIBRoomApp.UibRoomApp;
import no.uib.smo015.info233.oblig2.Util.InternetUtil;

/**
 * Class representing an event that happens when the user interacts with the window
 * @author Sindre
 * @version 0.0.1
 *
 */
public class WindowEvent implements WindowListener {

		/**
		 * Constructor for the WindowEvent class
		 * @param gui
		 */
		public WindowEvent(){
		}
		
		@Override
		public void windowOpened(java.awt.event.WindowEvent e) {
			
		}

		/**
		 * Method invoked when the user closes the application by the X in the corner of the frame.
		 * The method first checks if the user has internet connectivity and if yes, then it saves whatever the user is 
		 * looking at. Otherwise it won't save because we might save inconsistent data or no data at all.
		 */
		@Override
		public void windowClosing(java.awt.event.WindowEvent e) {
			if(InternetUtil.hasConnectivity()){
				UibRoomApp.saveFile("testGui2");
			} else {
				System.out.println("No connection to internet. \n"
						+ "I wont overwrite your stuff. No worries bro..");
			}
		}

		@Override
		public void windowClosed(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}


