package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the jornadas database table.
 * 
 */
@Entity
@Table(name="jornadas")
@NamedQuery(name="Jornada.findAll", query="SELECT j FROM Jornada j")
public class Jornada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="hora_fin")
	private Time horaFin;

	@Column(name="hora_inicio")
	private Time horaInicio;

	//bi-directional many-to-one association to Sesione
	@OneToMany(mappedBy="jornada")
	private List<Sesione> sesiones;

	public Jornada() {
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

	public List<Sesione> getSesiones() {
		return this.sesiones;
	}

	public void setSesiones(List<Sesione> sesiones) {
		this.sesiones = sesiones;
	}

	public Sesione addSesione(Sesione sesione) {
		getSesiones().add(sesione);
		sesione.setJornada(this);

		return sesione;
	}

	public Sesione removeSesione(Sesione sesione) {
		getSesiones().remove(sesione);
		sesione.setJornada(null);

		return sesione;
	}

}