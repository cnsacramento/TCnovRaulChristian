package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cuenta_veterinario database table.
 * 
 */
@Entity
@Table(name="cuenta_veterinario")
@NamedQuery(name="CuentaVeterinario.findAll", query="SELECT c FROM CuentaVeterinario c")
public class CuentaVeterinario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String correo;

	private String contrasenia;

	//bi-directional many-to-one association to Veterinario
	@OneToMany(mappedBy="cuentaVeterinario")
	private List<Veterinario> veterinarios;

	public CuentaVeterinario() {
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public List<Veterinario> getVeterinarios() {
		return this.veterinarios;
	}

	public void setVeterinarios(List<Veterinario> veterinarios) {
		this.veterinarios = veterinarios;
	}

	public Veterinario addVeterinario(Veterinario veterinario) {
		getVeterinarios().add(veterinario);
		veterinario.setCuentaVeterinario(this);

		return veterinario;
	}

	public Veterinario removeVeterinario(Veterinario veterinario) {
		getVeterinarios().remove(veterinario);
		veterinario.setCuentaVeterinario(null);

		return veterinario;
	}

}