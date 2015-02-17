package no.uib.smo015.info233.oblig2.Util;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Class representing a utility to check for internet connectivity
 * @author Sindre
 *
 */
public class InternetUtil {
	
	/**
	 * Static method to check if the system is connected to the Internet
	 * @return boolean true if the used is online, false otherwise
	 */
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
