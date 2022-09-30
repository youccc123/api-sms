package py.softcr.sendsms.service.implement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import py.softcr.security.postgressql.payload.response.MessageResponse;
import py.softcr.sendsms.bean.*;
import py.softcr.sendsms.smpp.SimpleSMSTransmitter;
import py.softcr.sendsms.service.interfaces.SendSimpleSMSInterface;

import javax.validation.Valid;

@Service
public class SendSimpleSMSImplement implements Serializable, SendSimpleSMSInterface {
    private static final Logger logger = LogManager.getLogger(SendSimpleSMSImplement.class);
    private static final long serialVersionUID = 1L;
//    private EnviaSMS enviaSMS;
    @Override
    public ResponseEntity<?> sendsimplesms(@Valid SendSimpleSMSRequest sendSimpleSMSRequest) {

        if (sendSimpleSMSRequest.getReceptor().isEmpty() || sendSimpleSMSRequest.getReceptor() == null){
            return ResponseEntity.ok(new MessageResponse("Numero del Receptor no puede ser vacio"));
        }
        if (sendSimpleSMSRequest.getMensaje().isEmpty() || sendSimpleSMSRequest.getMensaje() == null){
            return ResponseEntity.ok(new MessageResponse("El mensaje no puede ser vacio"));
        }

        //Enviamos el mensaje.
        SimpleSMSTransmitter objSimpleSMSTransmitter = new SimpleSMSTransmitter();
        //crea la session
        objSimpleSMSTransmitter.bindToSMSC();
        // envia el sms
        objSimpleSMSTransmitter.sendSingleSMS(sendSimpleSMSRequest.getReceptor(),sendSimpleSMSRequest.getMensaje());

//        EnviaSMS enviaSMS = new EnviaSMS();
//        enviaSMS.execute(sendSimpleSMSRequest.getReceptor(), sendSimpleSMSRequest.getMensaje());

        return ResponseEntity.ok(new SendSimpleSMSResponse(sendSimpleSMSRequest.getOperadora(), sendSimpleSMSRequest.getEmisor(),
                sendSimpleSMSRequest.getReceptor(), "Mensaje enviado Con Exito"));

    }

}
