package py.softcr.sendsms.service.interfaces;

import org.springframework.http.ResponseEntity;
import py.softcr.sendsms.model.PersonasBean;

import java.util.List;

public interface PersonasServiceInterface {
	public ResponseEntity<List<PersonasBean>> getAllPersonas();
}
