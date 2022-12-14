package py.softcr.sendsms.bean;

import java.io.Serializable;

public class SendSimpleSMSRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operadora;
    private String emisor;
    private String receptor;
    private String mensaje;

    public SendSimpleSMSRequest(){}

    public String toString(){
        return "{"+
                "\"operadora\":\""+operadora+"\","+
                "\"emisor\":\""+emisor+"\","+
                "\"receptor\":\""+receptor+"\","+
                "\"mensaje\":\""+mensaje+"\"}";
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
