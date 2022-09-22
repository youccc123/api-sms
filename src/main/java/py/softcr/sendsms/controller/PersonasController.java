package py.softcr.sendsms.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import py.softcr.sendsms.model.PersonasBean;
import py.softcr.sendsms.service.interfaces.PersonasServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonasController {
	private static final Logger logger = LogManager.getLogger(PersonasController.class);
	
	@Autowired
	private PersonasServiceInterface personasService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PersonasBean>> saludar(){
		logger.info("Ejecutando REST persona!!!");
		return personasService.getAllPersonas();
	}
	
}
