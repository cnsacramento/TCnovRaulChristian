package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especialidad_veterinario database table.
 * 
 */
@Entity
@Table(name="especialidad_veterinario")
@NamedQuery(name="EspecialidadVeterinario.findAll", query="SELECT e FROM EspecialidadVeterinario e")
public class EspecialidadVeterinario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Veterinario
	@OneToMany(mappedBy="especialidadVeterinario")
	private List<Veterinario> veterinarios;

	public EspecialidadVeterinario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Veterinario> getVeterinarios() {
		return this.veterinarios;
	}

	public void setVeterinarios(List<Veterinario> veterinarios) {
		this.veterinarios = veterinarios;
	}

	public Veterinario addVeterinario(Veterinario veterinario) {
		getVeterinarios().add(veterinario);
		veterinario.setEspecialidadVeterinario(this);

		return veterinario;
	}

	public Veterinario removeVeterinario(Veterinario veterinario) {
		getVeterinarios().remove(veterinario);
		veterinario.setEspecialidadVeterinario(null);

		return veterinario;
	}

}