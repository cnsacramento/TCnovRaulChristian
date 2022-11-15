package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the jornada database table.
 * 
 */
@Entity
@Table(name="jornada")
@NamedQuery(name="Jornada.findAll", query="SELECT j FROM Jornada j")
public class Jornada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to FraccionTiempo
	@OneToMany(mappedBy="jornada")
	private List<FraccionTiempo> fraccionTiempos;

	public Jornada() {
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<FraccionTiempo> getFraccionTiempos() {
		return this.fraccionTiempos;
	}

	public void setFraccionTiempos(List<FraccionTiempo> fraccionTiempos) {
		this.fraccionTiempos = fraccionTiempos;
	}

	public FraccionTiempo addFraccionTiempo(FraccionTiempo fraccionTiempo) {
		getFraccionTiempos().add(fraccionTiempo);
		fraccionTiempo.setJornada(this);

		return fraccionTiempo;
	}

	public FraccionTiempo removeFraccionTiempo(FraccionTiempo fraccionTiempo) {
		getFraccionTiempos().remove(fraccionTiempo);
		fraccionTiempo.setJornada(null);

		return fraccionTiempo;
	}

}