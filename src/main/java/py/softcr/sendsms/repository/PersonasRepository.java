package py.softcr.sendsms.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import py.softcr.sendsms.bean.Personas;
import py.softcr.sendsms.model.PersonasBean;

import java.util.List;


@Repository
@Component(value="personasRepository")
public interface PersonasRepository extends CrudRepository<Personas, Long>{
}
