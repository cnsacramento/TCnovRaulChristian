package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the intervenciones database table.
 * 
 */
@Entity
@Table(name="intervenciones")
@NamedQuery(name="Intervencione.findAll", query="SELECT i FROM Intervencione i")
public class Intervencione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String asunto;

	private String descripcion;

	@Column(name="id_equipo_intervencion")
	private int idEquipoIntervencion;

	//bi-directional many-to-one association to TiposIntervencion
	@ManyToOne
	@JoinColumn(name="id_tipo_intervencion")
	private TiposIntervencion tiposIntervencion;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumn(name="id_factura")
	private Factura factura;

	//bi-directional many-to-one association to Mascota
	@ManyToOne
	@JoinColumn(name="id_mascota")
	private Mascota mascota;

	//bi-directional many-to-one association to Sesione
	@OneToMany(mappedBy="intervencione")
	private List<Sesione> sesiones;

	//bi-directional many-to-many association to Veterinario
	@ManyToMany
	@JoinTable(
		name="equipo_intervencion"
		, joinColumns={
			@JoinColumn(name="id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="dni_veterinario")
			}
		)
	private List<Veterinario> veterinarios;

	public Intervencione() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdEquipoIntervencion() {
		return this.idEquipoIntervencion;
	}

	public void setIdEquipoIntervencion(int idEquipoIntervencion) {
		this.idEquipoIntervencion = idEquipoIntervencion;
	}

	public TiposIntervencion getTiposIntervencion() {
		return this.tiposIntervencion;
	}

	public void setTiposIntervencion(TiposIntervencion tiposIntervencion) {
		this.tiposIntervencion = tiposIntervencion;
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

	public List<Sesione> getSesiones() {
		return this.sesiones;
	}

	public void setSesiones(List<Sesione> sesiones) {
		this.sesiones = sesiones;
	}

	public Sesione addSesione(Sesione sesione) {
		getSesiones().add(sesione);
		sesione.setIntervencione(this);

		return sesione;
	}

	public Sesione removeSesione(Sesione sesione) {
		getSesiones().remove(sesione);
		sesione.setIntervencione(null);

		return sesione;
	}

	public List<Veterinario> getVeterinarios() {
		return this.veterinarios;
	}

	public void setVeterinarios(List<Veterinario> veterinarios) {
		this.veterinarios = veterinarios;
	}

}