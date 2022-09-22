package py.softcr.sendsms.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {
	private static final Logger logger = LogManager.getLogger(WelcomeController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> saludar(){
		logger.info("Ejecutando REST welcome!!!");
		String saludar = "Malvenido a SoftCR SMS to SMPP";
		return new ResponseEntity<String>(saludar,HttpStatus.OK);
	}
	
}
