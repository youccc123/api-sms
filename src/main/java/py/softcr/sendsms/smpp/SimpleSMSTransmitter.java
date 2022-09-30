package py.softcr.sendsms.smpp;

import ie.omk.smpp.AlreadyBoundException;
import ie.omk.smpp.message.SMPPProtocolException;
import ie.omk.smpp.version.VersionException;
import org.smpp.*;
import org.smpp.pdu.*;
import py.softcr.sendsms.util.SoftCRSMSProperties;

import java.io.IOException;
import java.net.SocketTimeoutException;


public class SimpleSMSTransmitter {

	/**
	 * @param args
	 */
	private Session session = null;
//	private String ipAddress = "192.168.100.100";
//	private String systemId = "smppclient1";
//	private String password = "password";
//	private int port = 2775;
//	private String shortMessage = "Texto del Mensaje";
//	private String sourceAddress = "1234";
//	private String destinationAddress = "5678";

	private SoftCRSMSProperties propiedades = new SoftCRSMSProperties();
	private	String ipAddress  		= propiedades.getProperty("smsc.host");
	private int port 			= Integer.parseInt(propiedades.getProperty("smsc.port"));
	private Boolean async 			= Boolean.parseBoolean(propiedades.getProperty("smsc.async"));
	private	String systemId  		= propiedades.getProperty("smsc.systemid");
	private	String systemtype  		= propiedades.getProperty("smsc.systemtype");
	private	String password  		= propiedades.getProperty("smsc.pasword");
	private	Integer sleepSegundos 	= Integer.parseInt(propiedades.getProperty("smsc.sleepTime"));
	private	String serviceType  	= propiedades.getProperty("smsc.serviceType");
	private	String sourceAddress	= propiedades.getProperty("smsc.emisor");
	private	Integer ton 			= Integer.parseInt(propiedades.getProperty("smsc.ton"));;
	private	Integer npi 			= Integer.parseInt(propiedades.getProperty("smsc.npi"));;


	public static void main(String[] args) {

		SimpleSMSTransmitter objSimpleSMSTransmitter = new SimpleSMSTransmitter();
		objSimpleSMSTransmitter.bindToSMSC();
		objSimpleSMSTransmitter.sendSingleSMS("987654","Este es El texto del mensaje");

		System.out.println("Program terminated");
		System.exit(0);
	}

	public void bindToSMSC() {
		try {

			if (ipAddress==null){
				throw new RuntimeException("Propertie Host is null");
			}

			if (port==0){
				throw new RuntimeException("Propertie Port is null");
			}
			if (systemId==null){
				throw new RuntimeException("systemId systemId is null");
			}
			if (password==null){
				throw new RuntimeException("systemId password is null");
			}

			Connection conn = new TCPIPConnection(ipAddress, port);
			session = new Session(conn);

			BindRequest breq = new BindTransmitter();
			breq.setSystemId(systemId);
			breq.setPassword(password);
			BindTransmitterResp bresp = (BindTransmitterResp) session.bind(breq);
			
			if(bresp.getCommandStatus() == Data.ESME_ROK) {
				System.out.println("Connected to SMSC");
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
		} catch (WrongLengthOfStringException e) {
			throw new RuntimeException(e);
		} catch (WrongSessionStateException e) {
			throw new RuntimeException(e);
		} catch (ValueNotSetException e) {
			throw new RuntimeException(e);
		} catch (PDUException e) {
			throw new RuntimeException(e);
		} catch (TimeoutException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendSingleSMS(String destinationAddress, String shortMessage) {
		try {
			SubmitSM request = new SubmitSM();

			// set values
			request.setSourceAddr(sourceAddress);
			request.setDestAddr(destinationAddress);
			request.setShortMessage(shortMessage);

			// send the request
			SubmitSMResp resp = session.submit(request);

			if (resp.getCommandStatus() == Data.ESME_ROK) {
				System.out.println("Message submitted....");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to submit message....");
		}
	}
}
