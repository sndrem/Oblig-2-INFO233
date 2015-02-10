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
import org.jsoup.select.Elements;

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

			nodesToList(root, null, nodeList);			
			
		} catch (IOException e) {
			System.out.println("There was a problem retrieving the website");
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void docToLists() {
		Elements activity = rootDocument.getElementsByClass("week-data");
		Elements days = rootDocument.getElementsByClass("week-header");
		Elements subject = rootDocument.getElementsByClass("emnekode");
		Elements week = rootDocument.getElementsByClass("uke");
			
		for(int i = 0; i < activity.size(); i++){
			Elements type = activity.get(i).getElementsByClass("activity");
			Elements time = activity.get(i).getElementsByClass("time");
			Elements desc = activity.get(i).getElementsByClass("item_desc");
			Elements room = activity.get(i).getElementsByClass("item_room");
			
			String roomtitle = room.attr("title");
			
			listActivities.add(new Activity(type.text(), roomtitle, desc.text()));
			dateList.add(time.text());
		}
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
