package src.net.codlin.sms;

import ie.omk.smpp.BadCommandIDException;
import ie.omk.smpp.Connection;
import ie.omk.smpp.NotBoundException;
import ie.omk.smpp.event.ConnectionObserver;
import ie.omk.smpp.event.ReceiverExitEvent;
import ie.omk.smpp.event.SMPPEvent;
import ie.omk.smpp.message.SMPPPacket;
import ie.omk.smpp.message.SMPPProtocolException;
import ie.omk.smpp.message.SubmitSM;
//import ie.omk.smpp.message.SubmitSMResp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SMS001 extends Thread implements ConnectionObserver {
	
	private Connection conn;
	private boolean exit = false;
	private int i=0;
	private boolean bAckingLinks=true;
	
	private static SMS001 instance;
	
	private SMS001() {
	}
	
	public static SMS001 getInstance() {
		if (instance == null)
			instance = new SMS001();
		return instance;
	}
	
	private void connect() {
		try {
			//conn = new Connection("localhost", 2775, true);
			conn = new Connection("200.85.32.101", 5600, true);
			//conn = new Connection("localhost", 9500, true);
			
			conn.autoAckLink(true);
		    conn.autoAckMessages(true);
		        
			conn.addObserver(this);
		} catch (UnknownHostException uhe) {
			System.out.println("UnknownHostException");
			System.exit(0);
		}
		
		boolean retry = false;
		//System.out.println("retry "+retry);
		
		while (!retry) {
			try {
				//conn.bind(Connection.TRANSCEIVER, "smppclient1", "password", null);
//				conn.bind(Connection.RECEIVER, "smppclient1", "password", null);
				conn.bind(Connection.RECEIVER, "carsa", "C4rs4!1", null);	
				//conn.bind(Connection.TRANSCEIVER, "cliente1", "carsa", null);
				
				retry = true;
			} catch (IOException ioe) {
				try {
					System.out.println("IOException");
					sleep(60 * 1000);
				} catch (InterruptedException ie) {
					System.out.println("InterruptedException");
				}
			}
		}
		
	}
	
	public void run() {
		while (!exit) {
			connect();
			synchronized(this) {
//				try {
					//wait();
					while (!exit)
					{
						try {
							sleep(40000); //chequea cada 30 segundos
							
							if (bAckingLinks)
							{
								bAckingLinks=false;
							}
							else
							{
								System.out.println("\nFall� chequeo..");
								System.out.println("\nConexion State:"+conn.getState());
						        System.out.println("Conexion Type:"+conn.getConnectionType());
						        System.out.println("Conexion is Bound:"+conn.isBound());
						        System.out.println("Conexion is AckingLinks:"+conn.isAckingLinks());
						        System.out.println("Conexion is AckingMessages:"+conn.isAckingMessages());					        

						        //exit=true;
						        if (conn.isBound())
							        {
								        conn.unbind();
								        System.out.println("\nDesconectado(UnBind)");
							        }
						        connect();
							}
						}
						catch  (InterruptedException ie)
						{
							System.out.println("Ejecucion Interrupida");
						} catch (NotBoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SMPPProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					
//				} catch (InterruptedException ie) {
//					System.out.println("Ejecucion Interrupida");
//				}
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
//		case SMPPPacket.DELIVER_SM : 
//			try {
//				SubmitSM response = processRequest(pack);
//				SubmitSMResp smr = (SubmitSMResp)conn.sendRequest(response);				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			break;		
		
			case SMPPPacket.DELIVER_SM : 
				try {
					System.out.println("\nDELIVER_SM");
					//String[] parts = pack.getMessageText().split(" ");
					logPacket(pack, "IN");
					
					insertSQL(pack, "IN");
					
					//asmsf001 xx=null;
					
					//llamadaExterna();
					
//					SubmitSMResp smr = (SubmitSMResp)conn.sendRequest(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
//			case SMPPPacket.BIND_RECEIVER: 
//				try {
//					SubmitSM response = processRequest(pack);
//					SubmitSMResp smr = (SubmitSMResp)conn.sendRequest(response);
//					System.out.println("Error binding:... ");
//				} catch (Exception e) {
//					e.printStackTrace();
//					System.out.println("Error binding:... ");
//				}
//				break;				
//			case SMPPPacket.BIND_TRANSCEIVER_RESP :
//				if (pack.getCommandStatus() != 0) {
//					System.out.println("Error binding: " + pack.getCommandStatus());
//					exit = true;
//					synchronized(this) {
//						notify();
//					}
//				} else {
//					System.out.println("Bounded");
//				}
			case SMPPPacket.BIND_RECEIVER_RESP :
				if (pack.getCommandStatus() != 0) {
					System.out.println("Error binding: " + pack.getCommandStatus());
					exit = true;
					synchronized(this) {
						notify();
					}
				} else {
					
					Date fecha = new Date();

		            SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
		            SimpleDateFormat shs = new SimpleDateFormat("HH:mm:ss");
		            
		            String sfecha =  sdfr.format(fecha);
		            String shora = shs.format(fecha);

					System.out.println("\nConectado(Bounded) "+sfecha+" "+shora);

				}
				break;

			case  SMPPPacket.ENQUIRE_LINK : //15 hex - 21 decimal
				/*/
				 * Check the link status. 
				 * This message can originate from either an ESME or the SMSC. 
				 * It is used to check that the entity at the other end of the link is still alive 
				 * and responding to messages. Usually used by the SMSC after a period of inactivity 
				 * to decide whether to close the link
				 */
				{
				bAckingLinks=true;
				
	            System.out.print("."+i);
	            i=i+1;
	            
	            if (i==60)
	            {
					Date fecha = new Date();

		            SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
		            SimpleDateFormat shs = new SimpleDateFormat("HH:mm:ss");
		            
		            String sfecha =  sdfr.format(fecha);
		            String shora = shs.format(fecha);
	            	
	            	System.out.println("\n"+ Integer.toHexString(pack.getCommandId())+" "+ sfecha+" "+shora);
	            	i=0;
	            }
				}
	            break;

			default :
	            System.out.println("\nUnexpected packet received! Id = "+ Integer.toHexString(pack.getCommandId())+" "+pack.getCommandId());
	            
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
			SQL001 sql001 = new SQL001();
			sql001.execute("RECEPCION",phone,packet.getMessageText(),"");
			
			SMS003 sms003 = new SMS003();
			sms003.execute(phone,"Solicitud Recibida GRACIAS...  respuesta en 48hs - CARSA");
			}
		//System.out.println(direction + ": " + phone +  " - " + packet.getMessageText());
	}
	
	public Connection getConnection() {
		return conn;
	}	
	
	public static void main(String args[]) {
		Runtime.getRuntime().addShutdownHook(new Hook());
		//SMS001 demo = SMS001.getInstance();
		SMS001 recepcion = SMS001.getInstance();
		//demo.start();
		recepcion.start();
	}	

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