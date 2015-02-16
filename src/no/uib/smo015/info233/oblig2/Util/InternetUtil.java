package no.uib.smo015.info233.oblig2.Util;

import java.net.InetSocketAddress;
import java.net.Socket;

public class InternetUtil {
	
	public static boolean hasConnectivity(){
		Socket socket = new Socket();
		InetSocketAddress adress = new InetSocketAddress("www.google.com", 80);
		
		try{
			socket.connect(adress, 3000);
			return true;
		} catch (Exception e){
			return false;
		} finally {
			try {
				socket.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

}
