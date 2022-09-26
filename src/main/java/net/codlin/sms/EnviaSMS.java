package net.codlin.sms;

import ie.omk.smpp.*;
import ie.omk.smpp.message.*;
import ie.omk.smpp.version.VersionException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import py.softcr.sendsms.util.SoftCRSMSProperties;

import javax.management.RuntimeErrorException;
import java.io.IOException;
import java.lang.UnsupportedOperationException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

//extends SMPPAPIExample
public class EnviaSMS  {
	private static final Logger logger = LogManager.getLogger(EnviaSMS.class);
	private SoftCRSMSProperties propiedades = new SoftCRSMSProperties();
	public void execute(String SMSCel, String SMSMensaje)
				throws
				RuntimeException
//				SocketTimeoutException, IOException, VersionException,
//				BadCommandIDException, SMPPProtocolException, RuntimeException,
//				InterruptedException, UnknownHostException
				{

		    try {
		        logger.info("Binding to the SMSC");
		    	System.out.println("Binding to the SMSC");

				String operadora  		= propiedades.getProperty("smpp.operadora");
				String host  			= propiedades.getProperty(operadora+".host");
				Integer port 			= Integer.parseInt(propiedades.getProperty(operadora+".port"));
				Boolean async 			= Boolean.parseBoolean(propiedades.getProperty(operadora+".async"));
				String systemid  		= propiedades.getProperty(operadora+".systemid");
				String systemtype  		= propiedades.getProperty(operadora+".systemtype");
				String password  		= propiedades.getProperty(operadora+".pasword");
				Integer sleepSegundos 	= Integer.parseInt(propiedades.getProperty(operadora+".sleepTime"));
				String serviceType  	= propiedades.getProperty(operadora+".serviceType");
				String adress 			= propiedades.getProperty(operadora+".emisor");
				Integer ton 			= Integer.parseInt(propiedades.getProperty(operadora+".ton"));;
				Integer npi 			= Integer.parseInt(propiedades.getProperty(operadora+".npi"));;

				Connection myConnection = new Connection(host, port, async);
		        myConnection.autoAckLink(true);
		        myConnection.autoAckMessages(true);

				myConnection.bind(
						Connection.TRANSMITTER,
						systemid,
						password,
						systemtype);
		        Thread.sleep(sleepSegundos);

				logger.info("Bind successful...submitting a message.");
				logger.info("Conexion State:"+myConnection.getState());
				logger.info("Conexion Type:"+myConnection.getConnectionType());
				logger.info("Conexion is Bound:"+myConnection.isBound());

		        System.out.println("Conexion State:"+myConnection.getState());
		        System.out.println("Conexion Type:"+myConnection.getConnectionType());
		        System.out.println("Conexion is Bound:"+myConnection.isBound());
		        
		        if (myConnection.isBound())
		        {
			        // Submit a simple message
			        SMPPPacket smpp_pck = myConnection.newInstance(SMPPPacket.SUBMIT_SM);
			        SubmitSM sm = (SubmitSM) smpp_pck;
					// Destino
			        sm.setDestination(new Address(ton, npi, SMSCel));
					// Origen
			        sm.setSource(new Address(ton, npi,adress));
			        sm.setServiceType(serviceType);
					sm.setMessageText(SMSMensaje);
			        SMPPRequest sreq = (SMPPRequest) sm;
					System.out.println("SMPPRequest is :" + sreq.isRequest());
			        SMPPResponse sres = myConnection.sendRequest(sreq);
					System.out.println("SMPPResponse CommandStatus is :" + sres.getCommandStatus());
					logger.info("SMPPResponse CommandStatus is :" + sres.getCommandStatus());
			        logger.info("Submitted message ID: " + sreq.getMessageId());
			        System.out.println("Submitted message ID: " + sreq.getMessageId());

					System.out.println("\nMensaje Enviado...");
					myConnection.unbind();
		    	}
		    	else
		    	{
					System.out.println("No se Conecto (UnBind)...");
		    	}
		    
		    } catch (SocketTimeoutException e) {
		        // TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
		    } catch (AlreadyBoundException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
				throw new RuntimeException(e);
		    } catch (VersionException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
				throw new RuntimeException(e);
		    } catch (SMPPProtocolException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
				throw new RuntimeException(e);
		    } catch (UnsupportedOperationException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
				throw new RuntimeException(e);
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
				throw new RuntimeException(e);
		    } catch (BadCommandIDException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}
}
