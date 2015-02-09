package no.uib.smo015.info233.oblig2.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Interfaces.ParserInterface;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

public class Parser implements ParserInterface {
	
	private String url;
	private Document rootDocument;
	private List<Node> nodeList;
	
	public Parser(String url){
		this.url = url;
		nodeList = new ArrayList<>();
		try {
			rootDocument = Jsoup.connect(url).get();
			System.out.println(rootDocument.title() + " sucessfully retrieved\n");
//			System.out.println(rootDocument);
//			System.out.println(rootDocument.child(0));
//			nodesToList(rootDocument.childNode(0), null, nodeList);
			rootDocument.traverse(new NodeVisitor() {
			    public void head(Node node, int depth) {
			        System.out.println("Entering tag: " + node.nodeName());
			    }
			    public void tail(Node node, int depth) {
			        System.out.println("Exiting tag: " + node.nodeName());
			    }
			});
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Method to retrieve a document
	 */
	@Override
	public void docToLists() {
				
	}

	/**
	 * Method to recursively traverse the html document
	 * @param node 		Whole document
	 * @param parent	The Parent 
	 * @param nodeList 	the list of nodes
	 */
	@Override
	public List<Node> nodesToList(Node node , Node parent, List<Node> nodeList) {
		// TODO Denne metoden skal være rekursiv
		// Add the html tag the first time the method is executed
		if (parent == null){
			this.nodeList.add(node);
			nodesToList(node, nodeList.get(0), nodeList);
		}
				
		return nodeList;
	}

	@Override
	public List<Node> getNodeList() {
		// TODO Implementer denne din latsabb
		return null;
	}

	@Override
	public List<Activity> getActivityList() {
		// TODO Implementer denne din latsabb
		return null;
	}

	@Override
	public List<String> getDateStringList() {
		// TODO Implementer denne din latsabb
		return null;
	}

}
