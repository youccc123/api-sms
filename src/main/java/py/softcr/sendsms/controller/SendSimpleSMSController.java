package py.softcr.sendsms.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.softcr.sendsms.bean.*;
import py.softcr.sendsms.service.interfaces.SendSimpleSMSInterface;

import javax.validation.Valid;

@RestController
@RequestMapping("/sms")
public class SendSimpleSMSController {
    private static final Logger logger = LogManager.getLogger(SendSimpleSMSController.class);

    @Autowired
    private SendSimpleSMSInterface sendSimpleSMSService;

    @GetMapping("/send")
    public @ResponseBody ResponseEntity<?> sendsimplesms(@Valid @RequestBody SendSimpleSMSRequest sendSimpleSMSRequest){
        logger.info("Ejecutando REST SendSimpleSMS!!! + " + sendSimpleSMSRequest.toString());
        return sendSimpleSMSService.sendsimplesms(sendSimpleSMSRequest);
    }

}