package no.uib.smo015.info233.oblig2.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Parser.Parser;

public class Gui extends JFrame {

	private static final long serialVersionUID = 2452589433133321974L;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	private DefaultListModel<Activity> listModel;
	private JList<Activity> activityList;
	private List<Activity> activityDataList;
	private Parser parser;
	private JComboBox<String> searchComboBox;
	private JLabel urlLabel;

	//	private DefaultTableModel dTableModel = new DefaultTableModel(activities, columnHeader){
	//		       public Class getColumnClass(int column) {
	//		            Class returnValue;
	//			             
	//			            // Verifying that the column exists (index > 0 && index < number of columns
	//			             
	//			            if ((column >= 0) && (column < getColumnCount())) {
	//			              returnValue = getValueAt(0, column).getClass();
	//			            } else {
	//			                 
	//			              // Returns the class for the item in the column  
	//			                 
	//			              returnValue = Object.class;
	//			            }
	//			            return returnValue;
	//			          }
	//		        };


	public Gui(Parser p){
		parser = p;
		activityDataList = parser.getActivityList();
		setupFrame();
		createPanels();
		setupTopPanel();
		setupBottomPanel();
		//		Sets up a list of activities
		setupActivityList();
		//		createTable();
		this.setVisible(true);
	}

	//	private void createTable() {
	//		
	//		
	//		for(Activity activity : activityList){
	//			activities = new Object[][] {{activity.getRoom(), activity.getType(), activity.getStartTimeString(),
	//									activity.getEndTimeString(), activity.getDescription()}};
	//		}
	//		
	//		Object[][] realActivities = new Object[activityList.size()][activityList.size()];
	//		JTable activityTable = new JTable(activities, columnHeader);
	//		JScrollPane tableScroll = new JScrollPane(activityTable);
	//		centerPanel.add(tableScroll);
	//	}

	/**
	 * Method to show the information for an activity
	 */
	private void setupActivityList() {
		listModel = new DefaultListModel<Activity>();
		activityList = new JList<>(listModel);
		JScrollPane listScrollPane = new JScrollPane(activityList);

		populateList(parser, listModel);

		centerPanel.add(listScrollPane);		
	}

	private void populateList(Parser parser, DefaultListModel<Activity> listModel) {
		activityDataList = parser.getActivityList();
		for(Activity a : activityDataList){
			listModel.addElement(a);
		}
	}

	/**
	 * Method to create the panels
	 */
	private void createPanels() {
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2,0));
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * Method to add a textbox to the top panel
	 */
	private void setupTopPanel(){

		String[] topics = {"info233", "info110", "info132", "info262", "info216"};
		searchComboBox = new JComboBox<>(topics);
		topPanel.add(searchComboBox);
		Event e = new Event();
		searchComboBox.addActionListener(e);
	}

	/**
	 * Method to setup the bottom panel
	 */
	private void setupBottomPanel(){
		urlLabel = new JLabel("Status: ");
		bottomPanel.add(urlLabel);
	}

	private void setupFrame(){
		// Set the bounds of the application
		this.setBounds(new Rectangle(800, 500));
		//Set default close operation
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Set to object serialization when closing
		WindowEvent we = new WindowEvent();
		this.addWindowListener(we);
		//Set title
		this.setTitle("UIB Kalender-app");
		// Position the frame to the center of the screen
		centerFrame();
		// Set the frame to use a border layout
		this.setLayout(new BorderLayout());

	}

	/**
	 * Method to center the frame
	 */
	private void centerFrame(){
		Toolkit tk = Toolkit.getDefaultToolkit();

		Dimension dim = tk.getScreenSize();

		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		this.setLocation(xPos, yPos);
	}

	/**
	 * Class to represent the actionListener events
	 * @author Sindre
	 *
	 */
	private class Event implements ActionListener, Serializable {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == searchComboBox){
				String searchTerm = (String) searchComboBox.getSelectedItem();
				String searchUrl = "http://rom.app.uib.no/ukesoversikt/?entry=emne&input=" + searchTerm;
				listModel.clear();
				populateList(new Parser(searchUrl), listModel);
				urlLabel.setText("Status: ok");	
			}

		}

	}
	
	/**
	 * Class to take of the serialization of the program
	 */
	private class WindowEvent implements WindowListener, Serializable{

		@Override
		public void windowOpened(java.awt.event.WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(java.awt.event.WindowEvent e) {
//			UibRoomApp.saveFile(activityDataList, "testGui2");
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

}


