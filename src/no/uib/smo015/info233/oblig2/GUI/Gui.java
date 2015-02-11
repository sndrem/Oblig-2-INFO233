package no.uib.smo015.info233.oblig2.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Parser.Parser;

public class Gui extends JFrame {
	
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	private DefaultListModel<Activity> listModel;
	private JButton submitButton;
	private List<Activity> activityList;
	private Parser parser;

	public Gui(){
		parser = new Parser("http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233");
		activityList = parser.getActivityList();
		setupFrame();
		createPanels();
		setupTopPanel();
//		Sets up a list of activities
//		setupActivityList();
		
		createTable();
		
		this.setVisible(true);
	}
	
	private void createTable() {
		String[] weekDays = {"Hvor", 
							"Hva",
							"Når",
							"Beskrivelse"};
		Object[][] activities = {{"UPhil", "INFO233-lab", "Kl. 10.15-12.00", "Lab for nerder"}, 
								{"SV Lite auditorium", "Forelesning", "KL 08.45 - 14.00", "Forelesning for morgenfugler"}};
		
		Object[][] realActivities = new Object[activityList.size()][activityList.size()];
		
		
		
	
		JTable activityTable = new JTable(activities, weekDays);
		JScrollPane tableScroll = new JScrollPane(activityTable);
		centerPanel.add(tableScroll);
	}

//	/**
//	 * Method to show the information for an activity
//	 */
//	private void setupActivityList() {
//		listModel = new DefaultListModel<Activity>();
//		activityList = new JList<>(listModel);
//		JScrollPane listScrollPane = new JScrollPane(activityList);
//		centerPanel.add(listScrollPane);		
//	}
	
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
		JLabel searchLabel = new JLabel("Search");
		topPanel.add(searchLabel);
		// 15 er antall kolonner for teksten
		JTextField textBox = new JTextField(15);
		topPanel.add(textBox);
		submitButton = new JButton("Submit");
		Event e = new Event();
		submitButton.addActionListener(e);
		topPanel.add(submitButton);
	}


	private void setupFrame(){
		// Set the bounds of the application
		this.setBounds(new Rectangle(800, 500));
		//Set default close operation
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
	private class Event implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submitButton){
				System.out.println(e.getActionCommand() + " was pressed");
			}
			
		}
		
	}
	

}
