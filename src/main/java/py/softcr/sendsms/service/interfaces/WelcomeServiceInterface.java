package py.softcr.sendsms.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface WelcomeServiceInterface {
	public ResponseEntity<String> saludar();
}
