package py.softcr.sendsms.service.implement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import py.softcr.security.postgressql.payload.response.MessageResponse;
import py.softcr.sendsms.bean.Personas;
import py.softcr.sendsms.model.PersonasBean;
import py.softcr.sendsms.repository.PersonasRepository;
import py.softcr.sendsms.service.interfaces.PersonasServiceInterface;

@Service
@Validated
public class PersonasServiceImplement implements Serializable, PersonasServiceInterface {

	private static final long serialVersionUID = 1L;
	@Qualifier("personasRepository")
	@Autowired
	private PersonasRepository personasRepository;

	@Override
	public ResponseEntity<?> getAllPersonas() {
		List<PersonasBean> personaBeanList = new ArrayList<PersonasBean>();
		Iterable<Personas> personasEntityList = personasRepository.findAll();

		PersonasBean personaBean;
		for (Personas personasEntity : personasEntityList) {
			personaBean = new PersonasBean();
			personaBean.setIdpersonas(personasEntity.getIdpersonas());
			personaBean.setDocumentonro(personasEntity.getDocumentonro());
			personaBean.setNombre(personasEntity.getNombre());
			personaBean.setApellido(personasEntity.getApellido());
			personaBeanList.add(personaBean);
		}

		if (personaBeanList.size() > 0) {
			return ResponseEntity.ok(personaBeanList);
		} else {
			return ResponseEntity.ok(new MessageResponse("No existe ninguna persona registrada!"));
		}
	}
}
