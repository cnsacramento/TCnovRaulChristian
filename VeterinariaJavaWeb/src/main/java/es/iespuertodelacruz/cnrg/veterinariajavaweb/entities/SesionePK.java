package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sesiones database table.
 * 
 */
@Embeddable
public class SesionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_jornada", insertable=false, updatable=false)
	private int idJornada;

	@Column(name="id_intervencion", insertable=false, updatable=false)
	private int idIntervencion;

	public SesionePK() {
	}
	public int getIdJornada() {
		return this.idJornada;
	}
	public void setIdJornada(int idJornada) {
		this.idJornada = idJornada;
	}
	public int getIdIntervencion() {
		return this.idIntervencion;
	}
	public void setIdIntervencion(int idIntervencion) {
		this.idIntervencion = idIntervencion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SesionePK)) {
			return false;
		}
		SesionePK castOther = (SesionePK)other;
		return 
			(this.idJornada == castOther.idJornada)
			&& (this.idIntervencion == castOther.idIntervencion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idJornada;
		hash = hash * prime + this.idIntervencion;
		
		return hash;
	}
}