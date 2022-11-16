package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the mascotas database table.
 * 
 */
@Entity
@Table(name="mascotas")
@NamedQuery(name="Mascota.findAll", query="SELECT m FROM Mascota m")
public class Mascota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="fecha_nacimiento")
	private Timestamp fechaNacimiento;

	private String nombre;

	private BigDecimal peso;

	//bi-directional many-to-one association to Intervencione
	@OneToMany(mappedBy="mascota")
	private List<Intervencione> intervenciones;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="dni_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to EspeciesMascota
	@ManyToOne
	@JoinColumn(name="id_especie")
	private EspeciesMascota especiesMascota;

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

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public List<Intervencione> getIntervenciones() {
		return this.intervenciones;
	}

	public void setIntervenciones(List<Intervencione> intervenciones) {
		this.intervenciones = intervenciones;
	}

	public Intervencione addIntervencione(Intervencione intervencione) {
		getIntervenciones().add(intervencione);
		intervencione.setMascota(this);

		return intervencione;
	}

	public Intervencione removeIntervencione(Intervencione intervencione) {
		getIntervenciones().remove(intervencione);
		intervencione.setMascota(null);

		return intervencione;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EspeciesMascota getEspeciesMascota() {
		return this.especiesMascota;
	}

	public void setEspeciesMascota(EspeciesMascota especiesMascota) {
		this.especiesMascota = especiesMascota;
	}

}