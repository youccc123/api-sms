package py.softcr.sendsms.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
public class PersonasBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idpersonas;
	private String apellido;
	private String documentonro;
	private String nombre;

	public PersonasBean() {
	}

	public Long getIdpersonas() {
		return this.idpersonas;
	}

	public void setIdpersonas(Long idpersonas) {
		this.idpersonas = idpersonas;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumentonro() {
		return this.documentonro;
	}

	public void setDocumentonro(String documentonro) {
		this.documentonro = documentonro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}