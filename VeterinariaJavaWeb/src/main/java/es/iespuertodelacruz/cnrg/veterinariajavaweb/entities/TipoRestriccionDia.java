package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the tipo_restriccion_dia database table.
 * 
 */
@Entity
@Table(name="tipo_restriccion_dia")
@NamedQuery(name="TipoRestriccionDia.findAll", query="SELECT t FROM TipoRestriccionDia t")
public class TipoRestriccionDia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String tipo;

	@Column(name="hora_apertura")
	private Time horaApertura;

	@Column(name="hora_cierre")
	private Time horaCierre;

	@Column(name="intervalo_tiempo")
	private int intervaloTiempo;

	//bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy="tipoRestriccionDia")
	private List<Reserva> reservas;

	public TipoRestriccionDia() {
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Time getHoraApertura() {
		return this.horaApertura;
	}

	public void setHoraApertura(Time horaApertura) {
		this.horaApertura = horaApertura;
	}

	public Time getHoraCierre() {
		return this.horaCierre;
	}

	public void setHoraCierre(Time horaCierre) {
		this.horaCierre = horaCierre;
	}

	public int getIntervaloTiempo() {
		return this.intervaloTiempo;
	}

	public void setIntervaloTiempo(int intervaloTiempo) {
		this.intervaloTiempo = intervaloTiempo;
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva addReserva(Reserva reserva) {
		getReservas().add(reserva);
		reserva.setTipoRestriccionDia(this);

		return reserva;
	}

	public Reserva removeReserva(Reserva reserva) {
		getReservas().remove(reserva);
		reserva.setTipoRestriccionDia(null);

		return reserva;
	}

}