package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the veterinarios database table.
 * 
 */
@Entity
@Table(name="veterinarios")
@NamedQuery(name="Veterinario.findAll", query="SELECT v FROM Veterinario v")
public class Veterinario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String dni;

	private String apellidos;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to CuentasVeterinario
	@ManyToOne
	@JoinColumn(name="cuenta_veterinaria")
	private CuentasVeterinario cuentasVeterinario;

	//bi-directional many-to-one association to EspecialidadesVeterinario
	@ManyToOne
	@JoinColumn(name="id_especialidad")
	private EspecialidadesVeterinario especialidadesVeterinario;

	//bi-directional many-to-many association to Intervencione
	@ManyToMany(mappedBy="veterinarios")
	private List<Intervencione> intervenciones;

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

	public CuentasVeterinario getCuentasVeterinario() {
		return this.cuentasVeterinario;
	}

	public void setCuentasVeterinario(CuentasVeterinario cuentasVeterinario) {
		this.cuentasVeterinario = cuentasVeterinario;
	}

	public EspecialidadesVeterinario getEspecialidadesVeterinario() {
		return this.especialidadesVeterinario;
	}

	public void setEspecialidadesVeterinario(EspecialidadesVeterinario especialidadesVeterinario) {
		this.especialidadesVeterinario = especialidadesVeterinario;
	}

	public List<Intervencione> getIntervenciones() {
		return this.intervenciones;
	}

	public void setIntervenciones(List<Intervencione> intervenciones) {
		this.intervenciones = intervenciones;
	}

}