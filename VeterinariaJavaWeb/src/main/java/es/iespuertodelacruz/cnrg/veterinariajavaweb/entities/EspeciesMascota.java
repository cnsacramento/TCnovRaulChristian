package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especies_mascota database table.
 * 
 */
@Entity
@Table(name="especies_mascota")
@NamedQuery(name="EspeciesMascota.findAll", query="SELECT e FROM EspeciesMascota e")
public class EspeciesMascota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nombre;

	private byte peligrosa;

	//bi-directional many-to-one association to Mascota
	@OneToMany(mappedBy="especiesMascota")
	private List<Mascota> mascotas;

	public EspeciesMascota() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte getPeligrosa() {
		return this.peligrosa;
	}

	public void setPeligrosa(byte peligrosa) {
		this.peligrosa = peligrosa;
	}

	public List<Mascota> getMascotas() {
		return this.mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	public Mascota addMascota(Mascota mascota) {
		getMascotas().add(mascota);
		mascota.setEspeciesMascota(this);

		return mascota;
	}

	public Mascota removeMascota(Mascota mascota) {
		getMascotas().remove(mascota);
		mascota.setEspeciesMascota(null);

		return mascota;
	}

}