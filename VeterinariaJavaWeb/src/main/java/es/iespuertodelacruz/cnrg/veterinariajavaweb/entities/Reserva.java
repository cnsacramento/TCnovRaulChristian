package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the reserva database table.
 * 
 */
@Entity
@Table(name="reserva")
@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="fecha_fin")
	private Timestamp fechaFin;

	@Column(name="fecha_inicio")
	private Timestamp fechaInicio;

	//bi-directional many-to-one association to Intervencion
	@ManyToOne
	@JoinColumn(name="id_intervencion")
	private Intervencion intervencion;

	//bi-directional many-to-one association to TipoRestriccionDia
	@ManyToOne
	@JoinColumn(name="id_restriccion_dia")
	private TipoRestriccionDia tipoRestriccionDia;

	public Reserva() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Timestamp getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Intervencion getIntervencion() {
		return this.intervencion;
	}

	public void setIntervencion(Intervencion intervencion) {
		this.intervencion = intervencion;
	}

	public TipoRestriccionDia getTipoRestriccionDia() {
		return this.tipoRestriccionDia;
	}

	public void setTipoRestriccionDia(TipoRestriccionDia tipoRestriccionDia) {
		this.tipoRestriccionDia = tipoRestriccionDia;
	}

}