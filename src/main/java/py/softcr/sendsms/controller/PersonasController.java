package py.softcr.sendsms.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import py.softcr.sendsms.model.PersonasBean;
import py.softcr.sendsms.service.interfaces.PersonasServiceInterface;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/persona")
public class PersonasController {
	private static final Logger logger = LogManager.getLogger(PersonasController.class);
	@Autowired
    private PersonasServiceInterface personasService;

	@GetMapping("/init")
	public ResponseEntity<?> init(){
		logger.info("Ejecutando REST persona!!!");
		return personasService.getAllPersonas();
	}
	
}
