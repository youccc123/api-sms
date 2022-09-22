package py.softcr.sendsms.service.interfaces;

import org.springframework.http.ResponseEntity;
import py.softcr.sendsms.bean.*;
public interface SendSimpleSMSInterface {
    public ResponseEntity<SendSimpleSMSResponse> sendsimplesms(SendSimpleSMSRequest sendSimpleSMSRequest);
}
