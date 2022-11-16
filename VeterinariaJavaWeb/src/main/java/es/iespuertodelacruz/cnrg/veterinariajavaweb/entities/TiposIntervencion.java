package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipos_intervencion database table.
 * 
 */
@Entity
@Table(name="tipos_intervencion")
@NamedQuery(name="TiposIntervencion.findAll", query="SELECT t FROM TiposIntervencion t")
public class TiposIntervencion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String tipo;

	//bi-directional many-to-one association to Intervencione
	@OneToMany(mappedBy="tiposIntervencion")
	private List<Intervencione> intervenciones;

	public TiposIntervencion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Intervencione> getIntervenciones() {
		return this.intervenciones;
	}

	public void setIntervenciones(List<Intervencione> intervenciones) {
		this.intervenciones = intervenciones;
	}

	public Intervencione addIntervencione(Intervencione intervencione) {
		getIntervenciones().add(intervencione);
		intervencione.setTiposIntervencion(this);

		return intervencione;
	}

	public Intervencione removeIntervencione(Intervencione intervencione) {
		getIntervenciones().remove(intervencione);
		intervencione.setTiposIntervencion(null);

		return intervencione;
	}

}