package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cuentas_veterinarios database table.
 * 
 */
@Entity
@Table(name="cuentas_veterinarios")
@NamedQuery(name="CuentasVeterinario.findAll", query="SELECT c FROM CuentasVeterinario c")
public class CuentasVeterinario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String correo;

	private String contrasenia;

	//bi-directional many-to-one association to Veterinario
	@OneToMany(mappedBy="cuentasVeterinario")
	private List<Veterinario> veterinarios;

	public CuentasVeterinario() {
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
		veterinario.setCuentasVeterinario(this);

		return veterinario;
	}

	public Veterinario removeVeterinario(Veterinario veterinario) {
		getVeterinarios().remove(veterinario);
		veterinario.setCuentasVeterinario(null);

		return veterinario;
	}

}