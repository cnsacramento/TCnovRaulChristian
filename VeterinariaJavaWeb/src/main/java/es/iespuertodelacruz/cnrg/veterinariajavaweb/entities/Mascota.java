package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the mascota database table.
 * 
 */
@Entity
@Table(name="mascota")
@NamedQuery(name="Mascota.findAll", query="SELECT m FROM Mascota m")
public class Mascota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="fecha_nacimiento")
	private Timestamp fechaNacimiento;

	private String nombre;

	private Double peso;

	//bi-directional many-to-one association to Intervencion
	@OneToMany(mappedBy="mascota")
	private List<Intervencion> intervencions;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="dni_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to EspecieMascota
	@ManyToOne
	@JoinColumn(name="id_especie")
	private EspecieMascota especieMascota;

	public Mascota() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public List<Intervencion> getIntervencions() {
		return this.intervencions;
	}

	public void setIntervencions(List<Intervencion> intervencions) {
		this.intervencions = intervencions;
	}

	public Intervencion addIntervencion(Intervencion intervencion) {
		getIntervencions().add(intervencion);
		intervencion.setMascota(this);

		return intervencion;
	}

	public Intervencion removeIntervencion(Intervencion intervencion) {
		getIntervencions().remove(intervencion);
		intervencion.setMascota(null);

		return intervencion;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EspecieMascota getEspecieMascota() {
		return this.especieMascota;
	}

	public void setEspecieMascota(EspecieMascota especieMascota) {
		this.especieMascota = especieMascota;
	}

}