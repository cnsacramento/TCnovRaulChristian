package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the intervencion database table.
 * 
 */
@Entity
@Table(name="intervencion")
@NamedQuery(name="Intervencion.findAll", query="SELECT i FROM Intervencion i")
public class Intervencion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String descripcion;

	//bi-directional many-to-one association to FraccionTiempo
	@OneToMany(mappedBy="intervencion")
	private List<FraccionTiempo> fraccionTiempos;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumn(name="id_factura")
	private Factura factura;

	//bi-directional many-to-one association to Mascota
	@ManyToOne
	@JoinColumn(name="id_mascota")
	private Mascota mascota;

	//bi-directional many-to-one association to TipoIntervencion
	@ManyToOne
	@JoinColumn(name="id_tipo_intervencion")
	private TipoIntervencion tipoIntervencion;

	//bi-directional many-to-many association to Veterinario
	@ManyToMany
	@JoinTable(
		name="equipo_intervencion"
		, joinColumns={
			@JoinColumn(name="dni_veterinario", referencedColumnName="asunto"),
			@JoinColumn(name="id", referencedColumnName="id_equipo_intervencion")
			}
		, inverseJoinColumns={
			@JoinColumn(name="dni_veterinario")
			}
		)
	private List<Veterinario> veterinarios;

	public Intervencion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<FraccionTiempo> getFraccionTiempos() {
		return this.fraccionTiempos;
	}

	public void setFraccionTiempos(List<FraccionTiempo> fraccionTiempos) {
		this.fraccionTiempos = fraccionTiempos;
	}

	public FraccionTiempo addFraccionTiempo(FraccionTiempo fraccionTiempo) {
		getFraccionTiempos().add(fraccionTiempo);
		fraccionTiempo.setIntervencion(this);

		return fraccionTiempo;
	}

	public FraccionTiempo removeFraccionTiempo(FraccionTiempo fraccionTiempo) {
		getFraccionTiempos().remove(fraccionTiempo);
		fraccionTiempo.setIntervencion(null);

		return fraccionTiempo;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Mascota getMascota() {
		return this.mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public TipoIntervencion getTipoIntervencion() {
		return this.tipoIntervencion;
	}

	public void setTipoIntervencion(TipoIntervencion tipoIntervencion) {
		this.tipoIntervencion = tipoIntervencion;
	}

	public List<Veterinario> getVeterinarios() {
		return this.veterinarios;
	}

	public void setVeterinarios(List<Veterinario> veterinarios) {
		this.veterinarios = veterinarios;
	}

}