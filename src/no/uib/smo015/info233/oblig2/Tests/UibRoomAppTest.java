package no.uib.smo015.info233.oblig2.Tests;

import static org.junit.Assert.assertTrue;

import javax.swing.DefaultListModel;

import no.uib.smo015.info233.oblig2.Activity.Activity;

import org.junit.Before;
import org.junit.Test;

public class UibRoomAppTest {

	@Before
	public void setUp() throws Exception {
		
	}
	
	/**
	 * Method to test if one or more activities are being added to the defaultListModel used
	 * to represent the activities in the gui.
	 */
	@Test
	public void testPopulateListDefaultListModelOfActivity() {
		DefaultListModel<Activity> activityModel = new DefaultListModel<>();
		assertTrue("Modellen er tom", activityModel.size() == 0);
		Activity activity1 = new Activity(null, "INFO233 lab", "PC-lab 205", "UP:205", "Labgruppe 1", "10:15", "12:00", "Tirsdag 17.02.2015");
		Activity activity2 = new Activity(null, "INFO110 lab", "PC-lab 215", "UP:205", "Labgruppe 3", "11:30", "14:15", "Onsdag 21.03.2015");
		activityModel.addElement(activity1);
		activityModel.addElement(activity2);
		assertTrue("Modellen er ikke tom", activityModel.size() == 2);
		activityModel.removeElement(activity2);
		assertTrue("Modellen er ikke tom", activityModel.size() == 1);
	}

}
