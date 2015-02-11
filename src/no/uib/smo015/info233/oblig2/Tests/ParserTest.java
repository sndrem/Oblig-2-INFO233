package no.uib.smo015.info233.oblig2.Tests;

import static org.junit.Assert.fail;
import no.uib.smo015.info233.oblig2.Parser.Parser;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {
	private Parser testParser;

	@Before
	public void setUp() throws Exception {
		testParser = new Parser("http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233");
	}

	@Test
	public void testConnection() {
		fail("Not yet implemented");
	}

}
