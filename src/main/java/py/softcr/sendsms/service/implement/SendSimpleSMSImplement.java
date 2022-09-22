package py.softcr.sendsms.service.implement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import py.softcr.sendsms.bean.*;
import net.codlin.sms.*;
import py.softcr.sendsms.service.interfaces.SendSimpleSMSInterface;

@Service
public class SendSimpleSMSImplement implements Serializable, SendSimpleSMSInterface {
    private static final Logger logger = LogManager.getLogger(SendSimpleSMSImplement.class);
    private static final long serialVersionUID = 1L;

    @Override
    public ResponseEntity<SendSimpleSMSResponse> sendsimplesms(SendSimpleSMSRequest sendSimpleSMSRequest) {

        ResponseEntity<SendSimpleSMSResponse> response = null;
        SendSimpleSMSResponse sendSimpleSMSResponse = new SendSimpleSMSResponse();
        sendSimpleSMSRequest.getEmisor();
        sendSimpleSMSRequest.getMensaje();
        sendSimpleSMSRequest.getOperadora();
        sendSimpleSMSRequest.getReceptor();

        // ESTO HABILITAREMOS CUANDO SE REQUIERA IR AL SMPP.
//        EnviaSMS enviaSMS = new EnviaSMS();
//        enviaSMS.execute(sendSimpleSMSRequest.getReceptor(), sendSimpleSMSRequest.getMensaje());

        //AGREGAR PARA QUE ARME EL RESPONSE PARA EL CLIENTE.

        response = new ResponseEntity<SendSimpleSMSResponse>(sendSimpleSMSResponse, HttpStatus.OK);
        return response;
    }

}
