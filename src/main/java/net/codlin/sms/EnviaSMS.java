package net.codlin.sms;

import ie.omk.smpp.Address;
import ie.omk.smpp.Connection;
import ie.omk.smpp.message.SMPPPacket;
import ie.omk.smpp.message.SMPPRequest;
import ie.omk.smpp.message.SubmitSM;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import py.softcr.sendsms.util.SoftCRSMSProperties;

//extends SMPPAPIExample
public class EnviaSMS  {

	public EnviaSMS() {
		// TODO Auto-generated constructor stub
	}

	//private Log logger = LogFactory.getLog(SMS003.class);
	private static final Logger logger = LogManager.getLogger(EnviaSMS.class);
	private SoftCRSMSProperties propiedades = new SoftCRSMSProperties();
		public void execute(String SMSCel, String SMSMensaje) {
				
		    try {
		        logger.info("Binding to the SMSC");
		    	System.out.println("Binding to the SMSC");

				String host  = propiedades.getProperty("PERSONAL.host");
				Integer port = Integer.parseInt(propiedades.getProperty("PERSONAL.port"));
				Boolean async = Boolean.parseBoolean(propiedades.getProperty("PERSONAL.async"));
				String systemid  = propiedades.getProperty("PERSONAL.systemid");
				String systemtype  = propiedades.getProperty("PERSONAL.systemtype");
				String password  = propiedades.getProperty("PERSONAL.pasword");
				Integer sleepSegundos = Integer.parseInt(propiedades.getProperty("PERSONAL.sleepTime"));
				String serviceType  = propiedades.getProperty("PERSONAL.serviceType");

				Connection myConnection = new Connection("softcr.ddns.net", port, async);
		        myConnection.autoAckLink(true);
		        myConnection.autoAckMessages(true);

				myConnection.bind(
						Connection.TRANSMITTER,
						systemid,
						password,
						systemtype);
		        //Thread.sleep(200);
		        Thread.sleep(500);

		        logger.info("Bind successful...submitting a message.");
		        

		        System.out.println("Conexion State:"+myConnection.getState());
		        System.out.println("Conexion Type:"+myConnection.getConnectionType());
		        System.out.println("Conexion is Bound:"+myConnection.isBound());
		        
		        if (myConnection.isBound())
		        {
			        // Submit a simple message
			        SMPPPacket smpp_pck = myConnection.newInstance(SMPPPacket.SUBMIT_SM);
			        SubmitSM sm = (SubmitSM) smpp_pck;
			        //sm.setDestination(new Address(0, 0, "595985319480"));
			        sm.setDestination(new Address(0, 0, SMSCel));
			        sm.setSource(new Address(0, 0, "2386"));
			        
			        sm.setServiceType(serviceType);
			        
			        /* sm.setServiceType("CCHDU");
			         * Para que le llegue a todos los usuarios 
			         * que enviaron un mensaje al numero corto
			         * Para esto tiene que estar subscrito 
			         * - enviando si al numero corto se subcribe */

			        sm.setMessageText(SMSMensaje);
			        
			        SMPPRequest sreq = (SMPPRequest) sm;
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
			        	
//			        	SQL001 sql001 = new SQL001();
//						sql001.execute("ENVIO",SMSCel,SMSMensaje,"");
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

//		public static void main(Strings[] args) {
//			String celular;
//            String mensaje;
//
//		    if (args.length>1) {
//		    	celular = args[0];
//		    	mensaje = args[1];
//		    }
//            else
//            {
//            	celular="595985319480";
//            	mensaje="Test de envio . . .  - CARSA -";
//            }
//			System.out.println("Enviando SMS");
//
//		    EnviaSMS st = new EnviaSMS();
//		    st.execute(celular,mensaje);
//
//		    System.out.println("\nListo..");
//		}
}
