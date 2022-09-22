package py.softcr.sendsms.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.softcr.sendsms.bean.*;
import py.softcr.sendsms.service.interfaces.SendSimpleSMSInterface;

@RestController
//@RequestMapping("/apisms")
public class SendSimpleSMSController {
    private static final Logger logger = LogManager.getLogger(SendSimpleSMSController.class);


    @Autowired
    private SendSimpleSMSInterface sendSimpleSMSService;

    @RequestMapping(method = RequestMethod.POST, value ="/sendsimplesms")
    public @ResponseBody ResponseEntity<SendSimpleSMSResponse> sendsimplesms(@RequestBody SendSimpleSMSRequest sendSimpleSMSRequest){
        logger.info("Ejecutando REST SendSimpleSMS!!! + " + sendSimpleSMSRequest.toString());
        return sendSimpleSMSService.sendsimplesms(sendSimpleSMSRequest);
    }

}