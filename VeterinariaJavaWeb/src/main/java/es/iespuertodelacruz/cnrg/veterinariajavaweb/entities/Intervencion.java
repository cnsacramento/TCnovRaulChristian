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

	private String asunto;

	private String descripcion;

	@Column(name="id_equipo_intervencion")
	private int idEquipoIntervencion;

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
	@ManyToMany(mappedBy="intervencions")
	private List<Veterinario> veterinarios;

	//bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy="intervencion")
	private List<Reserva> reservas;

	public Intervencion() {
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

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva addReserva(Reserva reserva) {
		getReservas().add(reserva);
		reserva.setIntervencion(this);

		return reserva;
	}

	public Reserva removeReserva(Reserva reserva) {
		getReservas().remove(reserva);
		reserva.setIntervencion(null);

		return reserva;
	}

}