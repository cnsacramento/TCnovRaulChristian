package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the veterinario database table.
 * 
 */
@Entity
@Table(name="veterinario")
@NamedQuery(name="Veterinario.findAll", query="SELECT v FROM Veterinario v")
public class Veterinario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String dni;

	private String apellidos;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to EspecialidadVeterinario
	@ManyToOne
	@JoinColumn(name="id_especialidad")
	private EspecialidadVeterinario especialidadVeterinario;

	//bi-directional one-to-one association to CuentaVeterinaria
	@OneToOne
	@JoinColumn(name="cuenta_veterinaria")
	private CuentaVeterinaria cuentaVeterinariaBean;

	//bi-directional many-to-many association to Intervencion
	@ManyToMany(mappedBy="veterinarios")
	private List<Intervencion> intervencions;

	public Veterinario() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public EspecialidadVeterinario getEspecialidadVeterinario() {
		return this.especialidadVeterinario;
	}

	public void setEspecialidadVeterinario(EspecialidadVeterinario especialidadVeterinario) {
		this.especialidadVeterinario = especialidadVeterinario;
	}

	public CuentaVeterinaria getCuentaVeterinariaBean() {
		return this.cuentaVeterinariaBean;
	}

	public void setCuentaVeterinariaBean(CuentaVeterinaria cuentaVeterinariaBean) {
		this.cuentaVeterinariaBean = cuentaVeterinariaBean;
	}

	public List<Intervencion> getIntervencions() {
		return this.intervencions;
	}

	public void setIntervencions(List<Intervencion> intervencions) {
		this.intervencions = intervencions;
	}

}