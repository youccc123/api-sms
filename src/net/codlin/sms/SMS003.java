package src.net.codlin.sms;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ie.omk.smpp.Address;
import ie.omk.smpp.AlreadyBoundException;
import ie.omk.smpp.BadCommandIDException;
import ie.omk.smpp.Connection;
import ie.omk.smpp.message.BindResp;
import ie.omk.smpp.message.SMPPPacket;
import ie.omk.smpp.message.SMPPProtocolException;
import ie.omk.smpp.message.SMPPRequest;
import ie.omk.smpp.message.SMPPResponse;
import ie.omk.smpp.message.SubmitSM;
import ie.omk.smpp.message.SubmitSMResp;
import ie.omk.smpp.message.UnbindResp;
import ie.omk.smpp.util.GSMConstants;
import ie.omk.smpp.version.VersionException;
//extends SMPPAPIExample
public class SMS003  {

	public SMS003() {
		// TODO Auto-generated constructor stub
	}

	//private Log logger = LogFactory.getLog(SMS003.class);

		public void execute(String SMSCel, String SMSMensaje) {
			
			
			
		    try {
		        //logger.info("Binding to the SMSC");
		    	//System.out.println("Binding to the SMSC");

		        //Connection myConnection = new Connection("localhost", 2775);
		    	Connection myConnection = new Connection("200.85.32.101", 5600, true);
		        myConnection.autoAckLink(true);
		        myConnection.autoAckMessages(true);
		        
//		        try {
		        //BindResp resp =  myConnection.bind(
//		        myConnection.bind(
//		                Connection.TRANSMITTER,
//		                "carsa", 
//		                "C4rs4!1", 
//		                null);
		        myConnection.bind(
		                Connection.TRANSMITTER,
		                "carsa", 
		                "C4rs4!1", 
		                "SMS003");		        
		        Thread.sleep(200);
//		        if (resp.getCommandStatus() != 0) {
//		            //logger.info("SMSC bind failed.");
//		        	System.out.println("SMSC bind failed.");
//		            System.exit(1);
//		        }
//		    }
//		        catch (IOException ioe) {
//		        	System.out.println("Error IO");
//		        }
//		        catch (Exception e) {
//		        	System.out.println("Error General");
//		        }
//		        
		        /**
		        BindResp resp = myConnection.bind(
		                Connection.TRANSMITTER,
		                "smppclient1",
		                "password",
		                systemType,
		                sourceTON,
		                sourceNPI,
		                sourceAddress);

		         */
//		        if (resp.getCommandStatus() != 0) {
//		            //logger.info("SMSC bind failed.");
//		        	System.out.println("SMSC bind failed.");
//		            System.exit(1);
//		        }

		        //logger.info("Bind successful...submitting a message.");
		        

//		        System.out.println("Conexion State:"+myConnection.getState());
//		        System.out.println("Conexion Type:"+myConnection.getConnectionType());
//		        System.out.println("Conexion is Bound:"+myConnection.isBound());
		        
		        if (myConnection.isBound())
		        {
			        // Submit a simple message
			        SMPPPacket smpp_pck = myConnection.newInstance(SMPPPacket.SUBMIT_SM);
			        SubmitSM sm = (SubmitSM) smpp_pck;
			        //sm.setDestination(new Address(0, 0, "595985319480"));
			        sm.setDestination(new Address(0, 0, SMSCel));
			        sm.setSource(new Address(0, 0, "2386"));
			        
			        //sm.setServiceType("CCHDU"); 
			        
			        /* sm.setServiceType("CCHDU");
			         * Para que le llegue a todos los usuarios 
			         * que enviaron un mensaje al numero corto
			         * Para esto tiene que estar subscrito 
			         * - enviando si al numero corto se subcribe */
			        
		//		        sm.setDestination(new
		//		        Address(GSMConstants.GSM_TON_UNKNOWN,  
		//	                    GSMConstants.GSM_NPI_UNKNOWN, 
		//	                    "595985319480"));
		//		        sm.setSource(new 
		//				        Address(GSMConstants.GSM_TON_UNKNOWN,  
		//			                    GSMConstants.GSM_NPI_UNKNOWN,
		//		        				"2386"));
			        
			        //sm.setMessageText("Gracias . . .  - CARSA -");
			        sm.setMessageText(SMSMensaje);
			        
			        SMPPRequest sreq = (SMPPRequest) sm;
			        //SubmitSMResp smr = (SubmitSMResp) myConnection.sendRequest(sm);
			        myConnection.sendRequest(sreq);
			        //SMPPResponse sres = myConnection.sendRequest(sreq);
			        //SubmitSMResp smr = (SubmitSMResp) myConnection.sendRequest(sr);
			        //SubmitSMResp smr = (SubmitSMResp) sres;
			        //myConnection.sendRequest(sr);
		
			        //logger.info("Submitted message ID: " + smr.getMessageId());
			        //System.out.println("Submitted message ID: " + smr.getMessageId());
			        
		
			        // Unbind.
		//		        UnbindResp ubr = myConnection.unbind();
		//
		//		        if (ubr.getCommandStatus() == 0) {
		//		            //logger.info("Successfully unbound from the SMSC");
		//		            System.out.println("Successfully unbound from the SMSC");
		//		        } else {
		//		            //logger.info("There was an error unbinding.");
		//		            System.out.println("There was an error unbinding.");
		//		        }
		//		
			        	//System.out.println("Bind successful...submitting a message.");
			            
			            System.out.println("\nMensaje Enviado...");
			        	myConnection.unbind();
			        	
			        	SQL001 sql001 = new SQL001();
						sql001.execute("ENVIO",SMSCel,SMSMensaje,"");
		    	}
		    	else
		    	{
		    		System.out.println("No se Conecto (UnBind)...");
		    	}
		    
		    } 
//		    
//		    catch (SocketTimeoutException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    } catch (AlreadyBoundException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    } catch (VersionException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    } catch (SMPPProtocolException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    } catch (UnsupportedOperationException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    } catch (IOException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    } catch (BadCommandIDException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    }
		    catch (Exception x) {
		        //logger.info("An exception occurred.");
		    	//x.printStackTrace();
		        System.out.println("An exception occurred.");
		        System.out.println(x.toString());
		        System.out.println(x.getMessage());
		        //System.out.println(x..getMessage());
		        System.out.println(System.err.toString());
		        //x.printStackTrace(System.err);
		    }
		    }

		public static void main(String[] args) {

		    System.out.println("Test");
		    SMS003 st = new SMS003();
		    st.execute("595985319480","Test de envio . . .  - CARSA -");
		    System.out.println("Done�.");
		}
}
