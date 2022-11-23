package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@Table(name="factura")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Double coste;

	private String detalles;

	private Timestamp fecha;

	//bi-directional many-to-one association to Intervencion
	@OneToMany(mappedBy="factura")
	private List<Intervencion> intervencions;

	public Factura() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getCoste() {
		return this.coste;
	}

	public void setCoste(Double coste) {
		this.coste = coste;
	}

	public String getDetalles() {
		return this.detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public List<Intervencion> getIntervencions() {
		return this.intervencions;
	}

	public void setIntervencions(List<Intervencion> intervencions) {
		this.intervencions = intervencions;
	}

	public Intervencion addIntervencion(Intervencion intervencion) {
		getIntervencions().add(intervencion);
		intervencion.setFactura(this);

		return intervencion;
	}

	public Intervencion removeIntervencion(Intervencion intervencion) {
		getIntervencions().remove(intervencion);
		intervencion.setFactura(null);

		return intervencion;
	}

}