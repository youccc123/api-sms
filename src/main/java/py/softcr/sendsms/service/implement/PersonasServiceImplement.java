package py.softcr.sendsms.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import py.softcr.sendsms.model.PersonasBean;
import py.softcr.sendsms.repository.PersonasRepository;
import py.softcr.sendsms.service.interfaces.PersonasServiceInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Service
public class PersonasServiceImplement implements Serializable, PersonasServiceInterface {

	private static final long serialVersionUID = 1L;
	
	//@Autowired
	private PersonasRepository personasRepository;
	
	@Override
	public ResponseEntity<List<PersonasBean>> getAllPersonas() {
		ResponseEntity<List<PersonasBean>> response = null;
		List<PersonasBean> personaBeanList = new ArrayList<PersonasBean>();
		personasRepository.findAll();
//		Iterable<Personas> personasEntityList = personasRepository.findAll();
//
//		if (personasEntityList != null) {
//			PersonasBean personaBean;
//
//			for (Personas personasEntity : personasEntityList) {
//				personaBean = new PersonasBean();
//				personaBean.setIdpersonas(personasEntity.getIdpersonas());
//				personaBean.setDocumentonro(personasEntity.getDocumentonro());
//				personaBean.setNombre(personasEntity.getNombre());
//				personaBean.setApellido(personasEntity.getApellido());
//				personaBeanList.add(personaBean);
//			}
			response = new ResponseEntity<List<PersonasBean>>(personaBeanList,HttpStatus.OK);
//		}else {
//			response = new ResponseEntity<List<PersonasBean>>(personaBeanList,HttpStatus.NOT_FOUND);
//		}
			
		return response;
	}

}
