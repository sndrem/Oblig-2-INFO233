package no.uib.smo015.info233.oblig2.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Interfaces.ParserInterface;
import no.uib.smo015.info233.oblig2.Util.DateUtil;
import no.uib.smo015.info233.oblig2.Util.InternetUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/**
 * Class representing a parser used to parse the html content of a web page
 * @author Sindre
 * @version 0.0.1
 *
 */
public class Parser implements ParserInterface {

	private String url;
	private Document rootDocument;
	private Node root;
	private List<Node> nodeList;
	private List<Activity> activityList;
	private List<String> dateList;

	/**
	 * Constructor for the Parser class
	 * 
	 * @param url The url you want to parse
	 */
	public Parser(String url) {
		this.setUrl(url);
		nodeList = new ArrayList<>();
		activityList = new ArrayList<>();
		dateList = new ArrayList<>();
		if (InternetUtil.hasConnectivity()){
			connect(url);
			docToLists();
		} else {
			System.out.println("Internet is down, Go outside and play");
		}
	}

	/**
	 * Constructor for the Parser Class
	 * This constructor is mainly used for changing activities based of the topic you want to check
	 * @param url
	 * @param activityList
	 */
	public Parser(String url, List<Activity> activityList){
		this.setUrl(url);
		nodeList = new ArrayList<>();
		this.activityList = activityList;
		dateList = new ArrayList<>();
		if (InternetUtil.hasConnectivity()){
			connect(url);
			docToLists();
		} else {
			System.out.println("Internet is down, Go outside and play");
		}
	}

	/**
	 * Method to connect to the the given url and retrieve the data
	 * 
	 * @param url
	 * @return true if it successfully connects, false otherwise
	 */
	public boolean connect(String url) {
		try {
			rootDocument = Jsoup.connect(url).get();
			root = rootDocument.childNode(1);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
	}

	/**
	 * Method to create the nodelist, activityList and dateToStringList
	 */
	@Override
	public void docToLists() {
		nodesToList(root, null, nodeList);

		for (Node node : nodeList) {
			if (node.attr("class").equals("week-header") && node.nodeName().equals("tr")){
				for (int i = 1; i < 6; i++){
					dateList.add(node.childNode(i).childNode(0).toString());
				}
			}

			if (node.attr("class").equals("week-data")) {
				nodeToActivity(node);
			}
		}
	}

	/**
	 * Method used to retrieve the correct index of the week for an activity.
	 * @param node
	 * @param i
	 * @param match
	 * @return
	 */
	public int getTrueActivityIndex(Node node, int i, String tableData){
		node = node.previousSibling();
		if(node == null)
			return i;

		if (node.nodeName().equals(tableData))
			i++;
		return getTrueActivityIndex(node, i, tableData);

	}

	/**
	 * Method to add an activity to the activity list
	 * @param node
	 */
	private void nodeToActivity(Node node) {
		List<Node> descendants = new ArrayList<>();
		nodesToList(node, node.parent(), descendants);
		String type = "", room = "", description = "", time = "", roomCode = "";
		int weekIndex = getTrueActivityIndex(node, 1, "td");
		String weekDay = dateList.get(weekIndex - 1);

		for (Node descendant : descendants) {
			if (descendant.attr("class").equals("activity")) {
				TextNode textNode = (TextNode) descendant.childNode(0);
				type = textNode.text();
			} else if (descendant.attr("class").equals("item_desc")) {
				TextNode textNode = (TextNode) descendant.childNode(0);
				description = textNode.text();
			} else if (descendant.hasAttr("title")) {
				room = descendant.attr("title");
				Node roomCodeNode = descendant.childNode(0);
				if(roomCodeNode.childNodeSize() >= 1){
					roomCode = roomCodeNode.childNode(0).toString();
				}
			} else if (descendant.attr("class").equals("time")) {
				TextNode textNode = (TextNode) descendant.childNode(0);
				time = textNode.text();
			} 

		}

		activityList.add(new Activity(node, type, room, roomCode, description, DateUtil
				.getStartTime(time), DateUtil.getEndTime(time), weekDay));
	}

	/**
	 * Method to recursively traverse the html document
	 * @param node Whole document
	 * @param parent The Parent
	 * @param nodeList the list of nodes
	 * @return a list of nodes
	 */
	@Override
	public List<Node> nodesToList(Node node, Node parent, List<Node> nodeList) {

		if (node.childNodeSize() > 0) {
			Node child = node.childNode(0);
			while (child != null) {
				nodeList.add(child);
				nodesToList(child, child.parentNode(), nodeList);
				child = child.nextSibling();
			}
		}
		return nodeList;
	}

	@Override
	public List<Node> getNodeList() {
		return nodeList;
	}

	@Override
	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activities) {
		this.activityList = activities;
	}

	@Override
	public List<String> getDateStringList() {
		return dateList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the rootDocument
	 */
	public Document getRootDocument() {
		return rootDocument;
	}

	/**
	 * @param rootDocument
	 *            the rootDocument to set
	 */
	public void setRootDocument(Document rootDocument) {
		this.rootDocument = rootDocument;
	}

	/**
	 * @return the root
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * @return the listActivities
	 */
	public List<Activity> getListActivities() {
		return activityList;
	}

	/**
	 * @param listActivities
	 *            the listActivities to set
	 */
	public void setListActivities(List<Activity> listActivities) {
		this.activityList = listActivities;
	}

	/**
	 * @return the dateList
	 */
	public List<String> getDateList() {
		return dateList;
	}

	/**
	 * @param dateList
	 *            the dateList to set
	 */
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}

	/**
	 * @param nodeList
	 *            the nodeList to set
	 */
	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

}
