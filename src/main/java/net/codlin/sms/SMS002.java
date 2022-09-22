package net.codlin.sms;

import ie.omk.smpp.BadCommandIDException;
import ie.omk.smpp.Connection;
import ie.omk.smpp.event.ConnectionObserver;
import ie.omk.smpp.event.ReceiverExitEvent;
import ie.omk.smpp.event.SMPPEvent;
import ie.omk.smpp.message.SMPPPacket;
import ie.omk.smpp.message.SubmitSM;
//import ie.omk.smpp.message.SubmitSMResp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;


public class SMS002 extends Thread implements ConnectionObserver {
	
	private Connection conn;
	private boolean exit = false;
	
	private static SMS002 instance;
	
	private SMS002() {
	}
	
	public static SMS002 getInstance() {
		if (instance == null)
			instance = new SMS002();
		return instance;
	}
	
	private void connect() {
		try {
			//conn = new Connection("localhost", 2775, true);
			conn = new Connection("softcr.ddns.net", 2775, true);
			//conn = new Connection("localhost", 9500, true);
			conn.addObserver(this);
		} catch (UnknownHostException uhe) {
			System.exit(0);
		}
		
		boolean retry = false;
		
		while (!retry) {
			try {
				//conn.bind(Connection.TRANSCEIVER, "smppclient1", "password", null);
//				conn.bind(Connection.RECEIVER, "smppclient1", "password", null);
				//conn.bind(Connection.RECEIVER, "carsa", "C4rs4!1", null);	
				conn.bind(Connection.TRANSMITTER, "smppclient1", "password", null);
				retry = true;
			} catch (IOException ioe) {
				try {
					sleep(60 * 1000);
				} catch (InterruptedException ie) {
				}
			}
		}
		
	}
	
	public void run() {
		while (!exit) {
			connect();
			synchronized(this) {
				try {
					wait();		
				} catch (InterruptedException ie) {
					System.out.println("Ejecucion Interrupida");
				}
			}
		}
	}
	
	public void update(Connection conn, SMPPEvent ev) {
		if (ev.getType() == SMPPEvent.RECEIVER_EXIT && ((ReceiverExitEvent)ev).isException()) {
			synchronized(this) {
				notify();
			}
		}
	}

	public void packetReceived(Connection conn, SMPPPacket pack) {
		switch(pack.getCommandId()) {		
			case SMPPPacket.SUBMIT_SM_RESP : 
				try {
					System.out.println("SUBMIT_SM_RESP");
					System.out.println("Mensaje Enviado . . .");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case SMPPPacket.BIND_TRANSMITTER_RESP :
				if (pack.getCommandStatus() != 0) {
					System.out.println("Error binding: " + pack.getCommandStatus());
					exit = true;
					synchronized(this) {
						notify();
					}
				} else {
					System.out.println("Conectado(Bounded) Para Envio . . .");
					
					//SubmitSM sm2 = processRequest();
				}
		}
	}
	
	private SubmitSM processRequest(SMPPPacket request) throws BadCommandIDException {
		SubmitSM sm = (SubmitSM)conn.newInstance(SMPPPacket.SUBMIT_SM);
		sm.setDestination(request.getSource());
		String[] parts = request.getMessageText().split(" ");
		logPacket(request, "IN");
		//if (parts[0].equalsIgnoreCase("balance")) {
		if (parts[0].equalsIgnoreCase("credito")) {
			User user = User.findByPhone(request.getSource().getAddress());
			if (user == null)
				sm.setMessageText("Your phone number is not registered in our database! Please contact one of our offices");
			else if (!user.getAccountNumber().equalsIgnoreCase(parts[1]))
				sm.setMessageText("Account number that you have entered is not correct! Please try again");
			else
				sm.setMessageText("Balance on your account is " + user.getBalance() + "$");
		} else {
			//sm.setMessageText("Wrong message format! Please send BALANCE <ACCOUNT_NUMBER>");
			sm.setMessageText("Enviar... por favor: CREDITO <monto>");
		}
		logPacket(sm, "OUT");
		return sm;
	}
		
	private void logPacket(SMPPPacket packet, String direction) {
		String phone;
		if (direction.equals("OUT"))
			phone = packet.getDestination().getAddress();
		else
			{
			phone = packet.getSource().getAddress();
			//System.out.println("msg: "+ packet.getMessageText());
			}
		System.out.println(direction + ": " + phone +  " - " + packet.getMessageText());
	}
	
	private void insertSQL(SMPPPacket packet, String direction) {
		String phone;
		if (direction.equals("OUT"))
			phone = packet.getDestination().getAddress();
		else
			{
			phone = packet.getSource().getAddress();
			//SQL001 sql001= new SQL001(phone,packet.getMessageText());
			}
		//System.out.println(direction + ": " + phone +  " - " + packet.getMessageText());
	}
	
	public Connection getConnection() {
		return conn;
	}	
	
//	public static void main(String args[]) {
//		Runtime.getRuntime().addShutdownHook(new Hook());
//		//SMS001 demo = SMS001.getInstance();
//		SMS002 recepcion = SMS002.getInstance();
//		//demo.start();
//		recepcion.start();
//	}

	public void llamadaExterna() {
	 try
     {
		 //String comando="cmd /c java -version";
		 String comando="cmd /c dir";
		 //String comando="D:/java.exe -version";
		 
		 System.out.println(comando);
		 
         // Se lanza el ejecutable.
         Process p=Runtime.getRuntime().exec(comando);
         
         // Se obtiene el stream de salida del programa
         InputStream is = p.getInputStream();
         
         /* Se prepara un bufferedReader para poder leer la salida m�s comodamente. */
         BufferedReader br = new BufferedReader (new InputStreamReader (is));
         
         // Se lee la primera linea
         String aux = br.readLine();
         
         // Mientras se haya leido alguna linea
         while (aux!=null)
         {
             // Se escribe la linea en pantalla
             System.out.println(aux);
             
             // y se lee la siguiente.
             aux = br.readLine();
         }
     } 
     catch (Exception e)
     {
         // Excepciones si hay alg�n problema al arrancar el ejecutable o al leer su salida.*/
         e.printStackTrace();
     }
 } 
}