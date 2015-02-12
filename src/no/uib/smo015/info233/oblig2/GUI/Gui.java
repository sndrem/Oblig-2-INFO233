package no.uib.smo015.info233.oblig2.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Events.Event;
import no.uib.smo015.info233.oblig2.Events.ListEvent;
import no.uib.smo015.info233.oblig2.Events.WindowEvent;
import no.uib.smo015.info233.oblig2.Parser.Parser;
import no.uib.smo015.info233.oblig2.Util.DateUtil;

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
	private JLabel typeLabel;
	private JLabel descLabel;
	private JLabel roomLabel;
	private JLabel timeStartLabel;
	private JLabel timeEndLabel;

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
		setupActivityLabels();
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
		activityList.addListSelectionListener(new ListEvent(this));
		JScrollPane listScrollPane = new JScrollPane(activityList);
		centerPanel.add(listScrollPane, BorderLayout.NORTH);		
	}
	
	/**
	 * Method to setup the jlabels for showing different activities
	 */
	private void setupActivityLabels(){
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(5,1));
		Font font = new Font("Helvetica", Font.BOLD, 25);
		typeLabel = new JLabel("Type: ");
		descLabel = new JLabel("Beskrivelse: ");
		roomLabel = new JLabel("Rom: ");
		timeStartLabel = new JLabel("Starter: ");
		timeEndLabel = new JLabel("Slutter: ");
		
		typeLabel.setFont(font);
		descLabel.setFont(font);
		roomLabel.setFont(font);
		timeStartLabel.setFont(font);
		timeEndLabel.setFont(font);
		
		labelPanel.add(typeLabel);
		labelPanel.add(descLabel);
		labelPanel.add(roomLabel);
		labelPanel.add(timeStartLabel);
		labelPanel.add(timeEndLabel);
		
		centerPanel.add(labelPanel, BorderLayout.CENTER);
	}

	

	/**
	 * Method to create the panels
	 */
	private void createPanels() {
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * Method to add a textbox to the top panel
	 */
	private void setupTopPanel(){

		String[] topics = {"info233", "info110", "info132", "info262", "info216", "info125"};
		searchComboBox = new JComboBox<>(topics);
		topPanel.add(searchComboBox);
		JLabel weekLabel = new JLabel("Uke " + DateUtil.getWeekNumber());
		topPanel.add(weekLabel);
		Event e = new Event(this);
		searchComboBox.addActionListener(e);
	}

	/**
	 * Method to setup the bottom panel
	 */
	private void setupBottomPanel(){
		urlLabel = new JLabel("");
		bottomPanel.add(urlLabel);
	}

	/**
	 * Method to setup the frame
	 */
	private void setupFrame(){
		// Set the bounds of the application
		this.setBounds(new Rectangle(800, 500));
		//Set default close operation
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Set to object serialization when closing
		WindowEvent we = new WindowEvent(this);
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
	 * @return the topPanel
	 */
	public JPanel getTopPanel() {
		return topPanel;
	}

	/**
	 * @param topPanel the topPanel to set
	 */
	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	/**
	 * @return the bottomPanel
	 */
	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	/**
	 * @param bottomPanel the bottomPanel to set
	 */
	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

	/**
	 * @return the centerPanel
	 */
	public JPanel getCenterPanel() {
		return centerPanel;
	}

	/**
	 * @param centerPanel the centerPanel to set
	 */
	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	/**
	 * @return the listModel
	 */
	public DefaultListModel<Activity> getListModel() {
		return listModel;
	}

	/**
	 * @param listModel the listModel to set
	 */
	public void setListModel(DefaultListModel<Activity> listModel) {
		this.listModel = listModel;
	}

	/**
	 * @return the activityList
	 */
	public JList<Activity> getActivityList() {
		return activityList;
	}

	/**
	 * @param activityList the activityList to set
	 */
	public void setActivityList(JList<Activity> activityList) {
		this.activityList = activityList;
	}

	/**
	 * @return the activityDataList
	 */
	public List<Activity> getActivityDataList() {
		return activityDataList;
	}

	/**
	 * @param activityDataList the activityDataList to set
	 */
	public void setActivityDataList(List<Activity> activityDataList) {
		this.activityDataList = activityDataList;
	}

	/**
	 * @return the parser
	 */
	public Parser getParser() {
		return parser;
	}

	/**
	 * @param parser the parser to set
	 */
	public void setParser(Parser parser) {
		this.parser = parser;
	}

	/**
	 * @return the searchComboBox
	 */
	public JComboBox<String> getSearchComboBox() {
		return searchComboBox;
	}

	/**
	 * @param searchComboBox the searchComboBox to set
	 */
	public void setSearchComboBox(JComboBox<String> searchComboBox) {
		this.searchComboBox = searchComboBox;
	}

	/**
	 * @return the urlLabel
	 */
	public JLabel getUrlLabel() {
		return urlLabel;
	}

	/**
	 * @param urlLabel the urlLabel to set
	 */
	public void setUrlLabel(JLabel urlLabel) {
		this.urlLabel = urlLabel;
	}

	/**
	 * @return the typeLabel
	 */
	public JLabel getTypeLabel() {
		return typeLabel;
	}

	/**
	 * @param typeLabel the typeLabel to set
	 */
	public void setTypeLabel(JLabel typeLabel) {
		this.typeLabel = typeLabel;
	}

	/**
	 * @return the descLabel
	 */
	public JLabel getDescLabel() {
		return descLabel;
	}

	/**
	 * @param descLabel the descLabel to set
	 */
	public void setDescLabel(JLabel descLabel) {
		this.descLabel = descLabel;
	}

	/**
	 * @return the roomLabel
	 */
	public JLabel getRoomLabel() {
		return roomLabel;
	}

	/**
	 * @param roomLabel the roomLabel to set
	 */
	public void setRoomLabel(JLabel roomLabel) {
		this.roomLabel = roomLabel;
	}

	/**
	 * @return the timeStartLabel
	 */
	public JLabel getTimeStartLabel() {
		return timeStartLabel;
	}

	/**
	 * @param timeStartLabel the timeStartLabel to set
	 */
	public void setTimeStartLabel(JLabel timeStartLabel) {
		this.timeStartLabel = timeStartLabel;
	}

	/**
	 * @return the timeEndLabel
	 */
	public JLabel getTimeEndLabel() {
		return timeEndLabel;
	}

	/**
	 * @param timeEndLabel the timeEndLabel to set
	 */
	public void setTimeEndLabel(JLabel timeEndLabel) {
		this.timeEndLabel = timeEndLabel;
	}
}


