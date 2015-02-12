package no.uib.smo015.info233.oblig2.Events;

import java.awt.event.WindowListener;

import no.uib.smo015.info233.oblig2.GUI.Gui;

public class WindowEvent implements WindowListener {

		private Gui gui;

		public WindowEvent(Gui gui){
			this.gui = gui;
		}
		
		@Override
		public void windowOpened(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(java.awt.event.WindowEvent e) {
			// TODO Fiks slik at man kan lagre når man lukker programmet
//			UibRoomApp.saveFile(gui.getActivityDataList(), "testGui2");
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


