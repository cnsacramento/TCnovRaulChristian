package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the sesiones database table.
 * 
 */
@Entity
@Table(name="sesiones")
@NamedQuery(name="Sesione.findAll", query="SELECT s FROM Sesione s")
public class Sesione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SesionePK id;

	@Column(name="hora_fin")
	private Time horaFin;

	@Column(name="hora_inicio")
	private Time horaInicio;

	//bi-directional many-to-one association to Intervencione
	@ManyToOne
	@JoinColumn(name="id_intervencion")
	private Intervencione intervencione;

	//bi-directional many-to-one association to Jornada
	@ManyToOne
	@JoinColumn(name="id_jornada")
	private Jornada jornada;

	public Sesione() {
	}

	public SesionePK getId() {
		return this.id;
	}

	public void setId(SesionePK id) {
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

	public Intervencione getIntervencione() {
		return this.intervencione;
	}

	public void setIntervencione(Intervencione intervencione) {
		this.intervencione = intervencione;
	}

	public Jornada getJornada() {
		return this.jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

}