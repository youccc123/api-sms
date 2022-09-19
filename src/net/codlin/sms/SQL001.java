package src.net.codlin.sms;

import java.sql.Connection;
//import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
//	public class JdbcSQLServerConnection {
public class SQL001 {	
 
	public SQL001()
	{
	}
	
	public void execute(String SMSAccion, String SMSCel, String SMSMensaje, String SMSDocumento) {
		
		//System.out.println("Test de Prueba -");

		// Declare the JDBC objects.
        Connection conn = null;
        java.sql.Statement stmt = null;

        Date fecha = new Date();

        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat shs = new SimpleDateFormat("HH:mm:ss");
        
        String sfecha =  sdfr.format(fecha);
        String shora = shs.format(fecha); 

        try {
 
            String dbURL = "jdbc:jtds:sqlserver://SrvDesa01:1433/BDPRODUC_AYER_DESA";
            String user = "carsaweb";
            String pass = "carsaweb2014";
            conn = DriverManager.getConnection(dbURL, user, pass);
//            if (conn != null) {
////                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
////                System.out.println("-------------- Driver ---------------");
////                System.out.println("Driver name: " + dm.getDriverName());
////                System.out.println("Driver version: " + dm.getDriverVersion());
////                System.out.println("Product name: " + dm.getDatabaseProductName());
////                System.out.println("Product version: " + dm.getDatabaseProductVersion());
////                System.out.println("------------------------------------");
//            //System.out.println("Conexion a Base de Datos Existosa...");
//            }
            if (SMSAccion=="RECEPCION")
            {
            String SQL = "INSERT INTO smsrecep"+
            		"(SMSEd,SMSCd,SMSTp,SMSFch,SMSHs,SMSCel,SMSMnt,SMSMsg,"+
            		"SMSNCuo,SMSOCd,SMSMdF,SMSMdH,SMSMdU,SMSDoc,SMSCodR,SMSSeg,SMSAgD)"+
            		"VALUES ('INS','RECEPCION','SCR',{ts '"+
            		sfecha+
            		" 00:00:00.'},'"+
            		shora+
            		"','"+
            		SMSCel+
            		"',0,'"+
            		SMSMensaje+
            		"',0,' ',{ts '"+
            		sfecha+
            		" 00:00:00.'},'"+
            		shora+
            		"','SMS','"+
            		SMSDocumento
            		+"','','','')";

            stmt = conn.createStatement();
            
            //System.out.println("Inserting records into the table SMSResp");
            System.out.println(SQL);
            stmt.executeUpdate(SQL);
            //System.out.println("Inserted record into the table SMSResp");
            }
            else
            {
            	if (SMSAccion=="ENVIO")
            	{
            	String SQL = "INSERT INTO smsrecep"+
                		"(SMSEd,SMSCd,SMSTp,SMSFch,SMSHs,SMSCel,SMSMnt,SMSMsg,"+
                		"SMSNCuo,SMSOCd,SMSMdF,SMSMdH,SMSMdU,SMSDoc,SMSCodR,SMSSeg,SMSAgD)"+
                		"VALUES ('INS','ENVIO','SCR',{ts '"+
                		sfecha+
                		" 00:00:00.'},'"+
                		shora+
                		"','"+
                		SMSCel+
                		"',0,'"+
                		SMSMensaje+
                		"',0,' ',{ts '"+
                		sfecha+
                		" 00:00:00.'},'"+
                		shora+
                		"','SMS','','','','')";

                stmt = conn.createStatement();
                
                //System.out.println("Inserting records into the table SMSResp");
                System.out.println(SQL);
                stmt.executeUpdate(SQL);
                //System.out.println("Inserted record into the table SMSResp");
            	}
            	else
            	{
            		System.out.println("Accion no definida: "+SMSAccion);
            	}
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }		
	}
//    public static void main(String[] args) {
// 
//    	// Declare the JDBC objects.
//        Connection conn = null;
//        java.sql.Statement stmt = null;
//
//        Date fecha = new Date();
//
//        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat shs = new SimpleDateFormat("HH:mm:ss");
//        
//        String sfecha =  sdfr.format(fecha);
//        String shora = shs.format(fecha); 
//
//        try {
// 
//            String dbURL = "jdbc:jtds:sqlserver://SrvDesa01:1433/BDPRODUC_AYER_DESA";
//            String user = "carsaweb";
//            String pass = "carsaweb2014";
//            conn = DriverManager.getConnection(dbURL, user, pass);
//            if (conn != null) {
//                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//                System.out.println("-------------- Driver ---------------");
//                System.out.println("Driver name: " + dm.getDriverName());
//                System.out.println("Driver version: " + dm.getDriverVersion());
//                System.out.println("Product name: " + dm.getDatabaseProductName());
//                System.out.println("Product version: " + dm.getDatabaseProductVersion());
//                System.out.println("------------------------------------");
//            }
//                 
//            String SQL = "INSERT INTO smsrecep"+
//            		"(SMSEd,SMSCd,SMSTp,SMSFch,SMSHs,SMSCel,SMSMnt,SMSMsg,"+
//            		"SMSNCuo,SMSOCd,SMSMdF,SMSMdH,SMSMdU,SMSDoc)"+
//            		"VALUES ('INS','                    ','SCR',{ts '"+
//            		sfecha+
//            		" 00:00:00.'},'"+
//            		shora+
//            		"','963920',5000000.00,'"+
//            		"mensaje"+
//            		"',3,' ',{ts '"+
//            		sfecha+
//            		" 00:00:00.'},'"+
//            		shora+
//            		"','SISTEMA','65657705')";
//
//            stmt = conn.createStatement();
//            
//            System.out.println("Inserting records into the table SMSResp");
//            System.out.println(SQL);
//            stmt.executeUpdate(SQL);
//            System.out.println("Inserted record into the table SMSResp");
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

}
