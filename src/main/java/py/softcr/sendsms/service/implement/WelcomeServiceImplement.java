package py.softcr.sendsms.service.implement;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import py.softcr.sendsms.service.interfaces.WelcomeServiceInterface;

@Service
public class WelcomeServiceImplement implements Serializable, WelcomeServiceInterface {

	private static final long serialVersionUID = 1L;

	@Override
	public ResponseEntity<String> saludar() {
		String saludar = "Bienvenido a projectcore";
		ResponseEntity<String> response = new ResponseEntity<String>(saludar,HttpStatus.OK);
		return response;
	}

}
