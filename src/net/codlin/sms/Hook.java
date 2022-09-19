package src.net.codlin.sms;

import ie.omk.smpp.Connection;

public class Hook extends Thread {
	
	public void run() {
		System.out.println("Unbinding");
		Connection conn = SMS001.getInstance().getConnection();
		if(conn != null && conn.isBound())
			conn.force_unbind();
	}
}
