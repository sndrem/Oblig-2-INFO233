package no.uib.smo015.info233.oblig2.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import no.uib.smo015.info233.oblig2.Parser.Parser;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {
	private Parser testParser;
	private String url = "http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233";

	@Before
	public void setUp() throws Exception {
		testParser = new Parser(url);
	}

	@Test
	public void testConnection() {
		assertTrue("Hurra, du er koblet til", testParser.connect(url));
	}
	@Test
	public void testNodeListSize(){
		int nodesFrom233 = 509;
		int size = testParser.getNodeList().size();
		assertEquals(size, nodesFrom233);
	}
	

}
