package no.uib.smo015.info233.oblig2.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Interfaces.ParserInterface;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class Parser implements ParserInterface {

	private String url;
	private Document rootDocument;
	private Node root;
	private List<Node> nodeList;
	private List<Activity> listActivities;
	private List<String> dateList;
	private Map<Integer, Activity> weekMap;

	public Parser(String url){
		this.url = url;
		nodeList = new ArrayList<>();
		listActivities = new ArrayList<>();
		dateList = new ArrayList<>();
		weekMap = new HashMap<>();

		try {
			rootDocument = Jsoup.connect(url).get();
			root = rootDocument.childNode(1);
			System.out.println(rootDocument.title() + " sucessfully retrieved\n");


		} catch (IOException e) {
			System.out.println("There was a problem retrieving the website");
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
			nodeToDateStringList(node);
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
//		List<Node> descendants = new ArrayList<>();
//		nodesToList(node, node.parent(), descendants);
//
//		int index = 0;
//		for(Node descendant : descendants){
//			if(descendant.attr("class").equals("week-header")){
//				List<Node> babies = descendant.childNodes();
//				for(Node n : babies){
//					System.out.println(n.nodeName());
//					if(n instanceof TextNode){
//						TextNode tnode = (TextNode) descendant.childNode(0);
//						System.out.println("Index " + index + " "  + tnode.text());
//					} 
//				}
//			}
//			index++;
//		}
	}

	/**
	 * Method to add an activity to the activity list
	 * @param node
	 */
	private void nodeToActivity(Node node) {
		List<Node> descendants = new ArrayList<>();
		nodesToList(node, node.parent(), descendants);
		String type = "", room = "", description = "";

		for(Node descendant : descendants){
			if(descendant.attr("class").equals("activity")){
				TextNode textNode = (TextNode) descendant.childNode(0);
				type = textNode.text();
			} else if(descendant.attr("class").equals("item_desc")){
				TextNode textNode = (TextNode) descendant.childNode(0);
				description = textNode.text();
			} else if(descendant.hasAttr("title")){
				room = descendant.attr("title");	
			}
		}

		listActivities.add(new Activity(node, type, room, description));
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

	@Override
	public List<String> getDateStringList() {
		return dateList;
	}

}
