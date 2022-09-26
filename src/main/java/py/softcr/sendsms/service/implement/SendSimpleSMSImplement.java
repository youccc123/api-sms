package py.softcr.sendsms.service.implement;

import ie.omk.smpp.AlreadyBoundException;
import ie.omk.smpp.BadCommandIDException;
import ie.omk.smpp.message.SMPPProtocolException;
import ie.omk.smpp.version.VersionException;
import net.codlin.sms.EnviaSMS;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import py.softcr.security.postgressql.model.ERole;
import py.softcr.security.postgressql.model.Role;
import py.softcr.security.postgressql.payload.response.JwtResponse;
import py.softcr.security.postgressql.payload.response.MessageResponse;
import py.softcr.sendsms.bean.*;
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
        // ESTO HABILITAREMOS CUANDO SE REQUIERA IR AL SMPP.

//        try {
//            enviaSMS.execute(sendSimpleSMSRequest.getReceptor(), sendSimpleSMSRequest.getMensaje());
//        } catch (SocketTimeoutException | IOException | VersionException |
//                    BadCommandIDException | SMPPProtocolException | RuntimeException |
//                    InterruptedException | UnknownHostException e) {
//            throw new RuntimeException(e);
//        }
       //AGREGAR PARA QUE ARME EL RESPONSE PARA EL CLIENTE.
        EnviaSMS enviaSMS = new EnviaSMS();
        enviaSMS.execute(sendSimpleSMSRequest.getReceptor(), sendSimpleSMSRequest.getMensaje());

        return ResponseEntity.ok(new SendSimpleSMSResponse(sendSimpleSMSRequest.getOperadora(), sendSimpleSMSRequest.getEmisor(),
                sendSimpleSMSRequest.getReceptor(), "Mensaje enviado Con Exito"));

    }

}
