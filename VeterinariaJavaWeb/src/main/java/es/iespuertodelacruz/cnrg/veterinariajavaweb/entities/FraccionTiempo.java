package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the fraccion_tiempo database table.
 * 
 */
@Entity
@Table(name="fraccion_tiempo")
@NamedQuery(name="FraccionTiempo.findAll", query="SELECT f FROM FraccionTiempo f")
public class FraccionTiempo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="hora_fin")
	private Time horaFin;

	@Column(name="hora_inicio")
	private Time horaInicio;

	//bi-directional many-to-one association to Intervencion
	@ManyToOne
	@JoinColumn(name="id_intervencion")
	private Intervencion intervencion;

	//bi-directional many-to-one association to Jornada
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="id_fraccion_tiempo")
	private Jornada jornada;

	public FraccionTiempo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Intervencion getIntervencion() {
		return this.intervencion;
	}

	public void setIntervencion(Intervencion intervencion) {
		this.intervencion = intervencion;
	}

	public Jornada getJornada() {
		return this.jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

}