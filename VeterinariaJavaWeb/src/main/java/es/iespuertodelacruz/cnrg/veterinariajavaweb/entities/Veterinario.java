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

	//bi-directional many-to-many association to Intervencion
	@ManyToMany
	@JoinTable(
		name="equipo_intervencion"
		, joinColumns={
			@JoinColumn(name="dni_veterinario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_intervencion")
			}
		)
	private List<Intervencion> intervencions;

	//bi-directional many-to-one association to CuentaVeterinario
	@ManyToOne
	@JoinColumn(name="cuenta_veterinaria")
	private CuentaVeterinario cuentaVeterinario;

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

	public List<Intervencion> getIntervencions() {
		return this.intervencions;
	}

	public void setIntervencions(List<Intervencion> intervencions) {
		this.intervencions = intervencions;
	}

	public CuentaVeterinario getCuentaVeterinario() {
		return this.cuentaVeterinario;
	}

	public void setCuentaVeterinario(CuentaVeterinario cuentaVeterinario) {
		this.cuentaVeterinario = cuentaVeterinario;
	}

}