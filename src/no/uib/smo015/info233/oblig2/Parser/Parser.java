package no.uib.smo015.info233.oblig2.Parser;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Interfaces.ParserInterface;
import no.uib.smo015.info233.oblig2.Util.DateUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/**
 * Class representing a parser used to parse the 
 * html content of a web page
 * @author Sindre
 *
 */
public class Parser implements ParserInterface, Serializable {

	private String url;
	private Document rootDocument;
	private Node root;
	private List<Node> nodeList;
	private List<Activity> listActivities;
	private List<String> dateList;

	/**
	 * Constructor for the Parser class
	 * @param url The url you want to parse
	 */
	public Parser(String url){
		this.url = url;
		nodeList = new ArrayList<>();
		listActivities = new ArrayList<>();
		dateList = new ArrayList<>();
		connect(url);
		docToLists();
	}

	/**
	 * Method to connect to the the given url and retrieve the data
	 * @param url
	 * @return true if it successfully connects, false otherwise
	 */
	public boolean connect(String url) {
		try {
			rootDocument = Jsoup.connect(url).get();
			root = rootDocument.childNode(1);
			return true;
		} catch (IOException e) {
			System.out.println("There was a problem retrieving the website");
			return false;
		}
	}

	/**
	 * Method to create the nodelist, activityList and dateToStringList
	 */
	@Override
	public void docToLists() {
		nodesToList(root, null, nodeList);

		for (Node node : nodeList){
			if(node.attr("class").equals("week-data")){
				nodeToActivity(node);
			} else if (node.attr("class").equals("week-header"));
			// TODO Implementer denne 
			//			System.out.println("Kalender node: " + node.nodeName());
			//			nodeToDateStringList(node);
			//			getWeekDays(node);
		}

		//		Elements activity = rootDocument.getElementsByClass("week-data");
		//		Elements days = rootDocument.getElementsByClass("week-header");
		//		Elements subject = rootDocument.getElementsByClass("emnekode");
		//		Elements week = rootDocument.getElementsByClass("uke");
		//			
		//		for(int i = 0; i < activity.size(); i++){
		//			Elements type = activity.get(i).getElementsByClass("activity");
		//			Elements time = activity.get(i).getElementsByClass("time");
		//			Elements desc = activity.get(i).getElementsByClass("item_desc");
		//			Elements room = activity.get(i).getElementsByClass("item_room");
		//			
		//			String roomtitle = room.attr("title");
		//			
		//			listActivities.add(new Activity(type.text(), roomtitle, desc.text()));
		//			dateList.add(time.text());
		//		}
	}

	
	/**
	 * Method to add a timestring to the dateStringList
	 * @param node
	 * TODO Denne virker ikke. Fiks den
	 */
	private void nodeToDateStringList(Node node) {
		
	}

	/**
	 * Method to add an activity to the activity list
	 * @param node
	 */
	private void nodeToActivity(Node node) {
		List<Node> descendants = new ArrayList<>();
		nodesToList(node, node.parent(), descendants);
		String type = "", room = "", description = "", time = "";

		for(Node descendant : descendants){
			if(descendant.attr("class").equals("activity")){
				TextNode textNode = (TextNode) descendant.childNode(0);
				type = textNode.text();
			} else if(descendant.attr("class").equals("item_desc")){
				TextNode textNode = (TextNode) descendant.childNode(0);
				description = textNode.text();
			} else if(descendant.hasAttr("title")){
				room = descendant.attr("title");	
			} else if (descendant.attr("class").equals("time")){
				TextNode textNode = (TextNode) descendant.childNode(0);
				time = textNode.text();
				// TODO Oppdater konstruktøren for Activity så den tar inn klokkeslettet også. 
				// TODO Sjekk om man kan bruke dagens dato når man opprettet aktiviteter siden
				// det tross alt er for den uken man henter ned data.
				//				System.out.println(time);
			}
		}

		listActivities.add(new Activity(node, type, room, description, DateUtil.getStartTime(time), DateUtil.getEndTime(time) ));
	}

	/**
	 * Method to recursively traverse the html document
	 * @param node 		Whole document
	 * @param parent	The Parent 
	 * @param nodeList 	the list of nodes
	 */
	@Override
	public List<Node> nodesToList(Node node , Node parent, List<Node> nodeList) {

		if(node.childNodeSize() > 0){
			Node child = node.childNode(0);
			while(child != null){
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
		return listActivities;
	}
	
	/**
	 * Method to add an activity to the activitylist
	 * @param activity An activity to be added
	 */
	public void addActivity(Activity activity){
		listActivities.add(activity);
	}
	

	@Override
	public List<String> getDateStringList() {
		return dateList;
	}

}
