package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the facturas database table.
 * 
 */
@Entity
@Table(name="facturas")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private BigDecimal coste;

	private String detalles;

	private Timestamp fecha;

	//bi-directional many-to-one association to Intervencione
	@OneToMany(mappedBy="factura")
	private List<Intervencione> intervenciones;

	public Factura() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCoste() {
		return this.coste;
	}

	public void setCoste(BigDecimal coste) {
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

	public List<Intervencione> getIntervenciones() {
		return this.intervenciones;
	}

	public void setIntervenciones(List<Intervencione> intervenciones) {
		this.intervenciones = intervenciones;
	}

	public Intervencione addIntervencione(Intervencione intervencione) {
		getIntervenciones().add(intervencione);
		intervencione.setFactura(this);

		return intervencione;
	}

	public Intervencione removeIntervencione(Intervencione intervencione) {
		getIntervenciones().remove(intervencione);
		intervencione.setFactura(null);

		return intervencione;
	}

}