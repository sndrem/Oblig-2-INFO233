package no.uib.smo015.info233.oblig2.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Events.WindowEvent;
import no.uib.smo015.info233.oblig2.Util.DateUtil;

public class Gui extends JFrame {

	private static final long serialVersionUID = 2452589433133321974L;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	private DefaultListModel<Activity> listModel;
	private JList<Activity> activityList;
	private List<Activity> activityDataList;
	private JComboBox<String> searchComboBox;
	private JLabel urlLabel;
	private JLabel typeLabel;
	private JLabel descLabel;
	private JLabel roomLabel;
	private JLabel timeStartLabel;
	private JLabel timeEndLabel;
	private JLabel weekDayLabel;
	private JButton loadButton;
	private JButton saveButton;
	private JButton searchButton;
	private JTextField textField;
	private static Gui instance = null;

	/**
	 * Constructor for the gui class
	 * @param parser
	 */
	private Gui(){
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		activityDataList = new ArrayList<>();
		setupFrame();
		createPanels();
		setupTopPanel();
		setupBottomPanel();
		setupActivityList();
		setupActivityLabels();
		this.setVisible(true);
	}
	
	public static synchronized Gui getInstance(){
		if(instance == null){
			instance = new Gui();
		}
		return instance;
	}

	/**
	 * Method to show the information for an activity
	 */
	private void setupActivityList() {
		listModel = new DefaultListModel<Activity>();
		activityList = new JList<>(listModel);
		activityList.setSelectedIndex(0);
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
		weekDayLabel = new JLabel("Dag: ");
		typeLabel = new JLabel("Type: ");
		descLabel = new JLabel("Beskrivelse: ");
		roomLabel = new JLabel("Rom: ");
		timeStartLabel = new JLabel("Starter: ");
		timeEndLabel = new JLabel("Slutter: ");
			
		weekDayLabel.setFont(font);
		typeLabel.setFont(font);
		descLabel.setFont(font);
		roomLabel.setFont(font);
		timeStartLabel.setFont(font);
		timeEndLabel.setFont(font);
		
		labelPanel.add(weekDayLabel);
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
		centerPanel.setBorder(new TitledBorder("Aktiviteter"));
		centerPanel.setLayout(new BorderLayout());
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * Method to add a textbox to the top panel
	 */
	private void setupTopPanel(){
		setLoadButton(new JButton("Last aktiviteter fra fil"));
		topPanel.add(getLoadButton());
		String[] topics = {"info233", "info110", "info132", "info262", "info216", "info125", "inf144", "NOLI103"};
		searchComboBox = new JComboBox<>(topics);
		topPanel.add(searchComboBox);
		Font font = new Font("Arial", Font.BOLD, 15);
		JLabel dateInfo = new JLabel("Dagens dato:");
		JLabel todaysDate = new JLabel(DateUtil.getTodaysDate());
		dateInfo.setFont(font);
		todaysDate.setFont(font);
		topPanel.add(dateInfo);
		topPanel.add(todaysDate);
		JLabel weekLabel = new JLabel("Uke " + DateUtil.getWeekNumber());
		weekLabel.setFont(font);
		topPanel.add(weekLabel);
		saveButton = new JButton("Lagre aktive aktivitet");
		topPanel.add(saveButton);
	}
	
	/**
	 * Method to setup the bottom panel
	 */
	private void setupBottomPanel(){
		JLabel infoLabel = new JLabel("Search area");
		bottomPanel.add(infoLabel);
		setTextField(new JTextField(20));
		bottomPanel.add(getTextField());
		setSearchButton(new JButton("Search"));
		bottomPanel.add(getSearchButton());
		urlLabel = new JLabel("");
		bottomPanel.add(urlLabel);
	}

	/**
	 * Method to setup the frame
	 */
	private void setupFrame(){
		// Set the bounds of the application
		this.setBounds(new Rectangle(1000, 550));
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
	 * Method to set the labels to blank
	 */
	public void setBlankLabels(){
		this.getWeekDayLabel().setText("Dag: ");
		this.getUrlLabel().setText("Ingen info tilgjengelig for valgt emne");
		this.getDescLabel().setText("Beskrivelse: ");
		this.getRoomLabel().setText("Rom: ");
		this.getTimeEndLabel().setText("Slutt: ");
		this.getTimeStartLabel().setText("Start: ");
		this.getTypeLabel().setText("Type: ");
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

	public JLabel getWeekDayLabel() {
		return weekDayLabel;
	}

	public void setWeekDayLabel(JLabel weekDayLabel) {
		this.weekDayLabel = weekDayLabel;
	}

	public JButton getLoadButton() {
		return loadButton;
	}

	public void setLoadButton(JButton loadButton) {
		this.loadButton = loadButton;
	}

	/**
	 * @return the saveButton
	 */
	public JButton getSaveButton() {
		return saveButton;
	}

	/**
	 * @param saveButton the saveButton to set
	 */
	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	/**
	 * @return the searchButton
	 */
	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	 * @param searchButton the searchButton to set
	 */
	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	/**
	 * @return the textField
	 */
	public JTextField getTextField() {
		return textField;
	}

	/**
	 * @param textField the textField to set
	 */
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}



}


