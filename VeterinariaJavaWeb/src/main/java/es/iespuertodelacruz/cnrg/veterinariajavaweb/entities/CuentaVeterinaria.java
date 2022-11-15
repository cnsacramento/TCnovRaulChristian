package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cuenta_veterinaria database table.
 * 
 */
@Entity
@Table(name="cuenta_veterinaria")
@NamedQuery(name="CuentaVeterinaria.findAll", query="SELECT c FROM CuentaVeterinaria c")
public class CuentaVeterinaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String correo;

	private String contrasenia;

	//bi-directional one-to-one association to Veterinario
	@OneToOne(mappedBy="cuentaVeterinariaBean")
	private Veterinario veterinario;

	public CuentaVeterinaria() {
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

	public Veterinario getVeterinario() {
		return this.veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

}