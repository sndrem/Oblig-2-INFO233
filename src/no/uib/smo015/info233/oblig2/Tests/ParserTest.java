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

	@Before
	public void setUp() throws Exception {
		testParser = new Parser(url);
		htmlTest = "<!DOCTYPE html> <html> <meta char=\"utf-8\"> "
				+ "<head>	"
				+ "<title>Hey og hå, en testfil her</title>"
				+ "</head>"
				+ "<body>"
				+ "<td valign='top' rowspan='2' class='week-data'><span class='item_type'><span  class='activity'>INFO233 lab</span></span> <span class='time'>10:15-12:00</span><br><span class='item_desc'>Labgruppe 1</span> <span class='item_room' title='PC-lab 205'><a href='/rominfo/index.php?roomid=UP%3A205' target='_blank' style='text-decoration: none;color:#000'>UP:205</a></span></td>"
				+ "</body>"
			    + "</html>";
		
	}

	@Test
	public void testConnection() {
		assertTrue("Hurra, du er koblet til", testParser.connect(url));
	}
	
	@Test
	public void testNodeListSize(){
		// TODO Implementer en nodeList som sjekker en gitt html-streng som jeg lager selv
		int nodesFrom233 = 509;
		int size = testParser.getNodeList().size();
		assertEquals(size, nodesFrom233);
	}
	
	@Test
	public void nodeToActivityTest(){
		testParser.getNodeList().clear();
		Document content = Jsoup.parse(htmlTest);
		Node root = content.childNode(1);
		List<Node> activityList = new ArrayList<>();
		testParser.nodesToList(root, null, activityList);
		
		
		Activity activity1 = testParser.getActivityList().get(0);
		Node tempNode = activity1.getNode();
		Activity activity2 = new Activity(null, "INFO233 lab", "PC-lab 205", "UP:205", "Labgruppe 1", "10:15", "12:00", "Tirsdag 17.02.2015");
		System.out.println(activity1);
		System.out.println(activity2);
		
		assertEquals(activity1.getType(), activity2.getType());
		assertEquals(activity1.getRoom(), activity2.getRoom());
		assertEquals(activity1.getDescription(), activity2.getDescription());
		assertEquals(activity1.getStartTime(), activity2.getStartTime());
		assertEquals(activity1.getEndTime(), activity2.getEndTime());
		
		
	}

}
