package no.uib.smo015.info233.oblig2.UIBRoomApp;

import java.util.List;

import no.uib.smo015.info233.oblig2.Activity.Activity;
import no.uib.smo015.info233.oblig2.Parser.Parser;
import no.uib.smo015.info233.oblig2.Util.DateUtil;

public class UibRoomApp {
	
	public static void main(String[] args) {
		Parser p = new Parser("http://rom.app.uib.no/ukesoversikt/?entry=emne&input=info233");
		p.docToLists();
		
		List<Activity> list = p.getActivityList();
		List<String> times = p.getDateStringList();
		
			for(int i = 0; i < list.size(); i++){
				System.out.println(list.get(i).getType() + " " +list.get(i).getRoom() + " " + list.get(i).getDescription() + ". KL: " + 
						times.get(i));
			}
			
			for (String time : times){
				System.out.println("Start time: " + DateUtil.getStartTime(time) + " End time: " + DateUtil.getEndTime(time));
				
			}
		
	}

}
