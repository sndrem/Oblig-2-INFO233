package no.uib.smo015.info233.oblig2.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Parser.Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {
	private Parser testParser;
	private String url = "http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233";
	private String htmlTest;
	private int numberOfNodes;

	@Before
	public void setUp() throws Exception {
		testParser = new Parser(url);
		htmlTest = "<head>	"
				+ "<title>Hey, en testfil her</title>"
				+ "</head>"
				+ "<body>"
				+ "<td valign='top' rowspan='2' class='week-data'><span class='item_type'><span  class='activity'>INFO233 lab</span></span> <span class='time'>10:15-12:00</span><br><span class='item_desc'>Labgruppe 1</span> <span class='item_room' title='PC-lab 205'><a href='/rominfo/index.php?roomid=UP%3A205' target='_blank' style='text-decoration: none;color:#000'>UP:205</a></span></td>"
				+ "</body>"
			    + "</html>";
		
		numberOfNodes = 0;
		
	}

	/**
	 * Method to check if we have a connection. testParser.connect(url) returns true if the connection is succesfull.
	 */
	@Test
	public void testConnection() {
		assertTrue("Hurra, du er koblet til", testParser.connect(url));
	}
	
	/**
	 * Test to check that the recursive method in the Parser class generates the correct amount of 
	 * nodes. The variable nodesFrom233 = 509 is retrieved by running a nodetraversal to check that the method
	 * creates the correct amount of nodes. 
	 */
	@Test
	public void testNodeListSize(){
		int nodesFrom233 = 509;
		int size = testParser.getNodeList().size();
		assertEquals(size, nodesFrom233);
	}
	
	/**
	 * A thorough test to check that the parser is able to create the correct activity for a given node.
	 * I have created a dummy html page loaded with the basic structure of an html file, and a node represented the way it is at
	 * UIB's pages, so I can then do all the parsing in the parser, and create a dummy activity to check that their field are the same
	 */
	@Test
	public void nodeToActivityTest(){
		testParser.getNodeList().clear();
		Document content = Jsoup.parse(htmlTest);
		Node root = content.childNode(0);
		List<Node> activityList = new ArrayList<>();
		testParser.nodesToList(root, null, activityList);
		
		
		Activity activity1 = testParser.getActivityList().get(0);
		Activity activity2 = new Activity(null, "INFO233 lab", "PC-lab 205", "UP:205", "Labgruppe 1", "10:15", "12:00", "Tirsdag 17.02.2015");
	
		assertEquals(activity1.getType(), activity2.getType());
		assertEquals(activity1.getRoom(), activity2.getRoom());
		assertEquals(activity1.getDescription(), activity2.getDescription());
		assertEquals(activity1.getStartTime(), activity2.getStartTime());
		assertEquals(activity1.getEndTime(), activity2.getEndTime());
		
		
	}

}
