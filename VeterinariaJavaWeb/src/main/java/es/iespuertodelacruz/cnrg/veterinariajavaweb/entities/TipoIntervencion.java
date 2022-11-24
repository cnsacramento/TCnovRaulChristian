package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_intervencion database table.
 * 
 */
@Entity
@Table(name="tipo_intervencion")
@NamedQuery(name="TipoIntervencion.findAll", query="SELECT t FROM TipoIntervencion t")
public class TipoIntervencion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String tipo;

	//bi-directional many-to-one association to Intervencion
	@OneToMany(mappedBy="tipoIntervencion")
	private List<Intervencion> intervencions;

	public TipoIntervencion() {
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Intervencion> getIntervencions() {
		return this.intervencions;
	}

	public void setIntervencions(List<Intervencion> intervencions) {
		this.intervencions = intervencions;
	}

	public Intervencion addIntervencion(Intervencion intervencion) {
		getIntervencions().add(intervencion);
		intervencion.setTipoIntervencion(this);

		return intervencion;
	}

	public Intervencion removeIntervencion(Intervencion intervencion) {
		getIntervencions().remove(intervencion);
		intervencion.setTipoIntervencion(null);

		return intervencion;
	}

}