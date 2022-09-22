package py.softcr.sendsms.bean;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Personas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idpersonas;
	private String apellido;
	private String documentonro;
	private String nombre;

	public Personas() {
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