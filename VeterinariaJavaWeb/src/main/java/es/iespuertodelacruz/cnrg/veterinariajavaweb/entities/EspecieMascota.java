package es.iespuertodelacruz.cnrg.veterinariajavaweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especie_mascota database table.
 * 
 */
@Entity
@Table(name="especie_mascota")
@NamedQuery(name="EspecieMascota.findAll", query="SELECT e FROM EspecieMascota e")
public class EspecieMascota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;

	private byte peligrosa;

	//bi-directional many-to-one association to Mascota
	@OneToMany(mappedBy="especieMascota")
	private List<Mascota> mascotas;

	public EspecieMascota() {
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
		mascota.setEspecieMascota(this);

		return mascota;
	}

	public Mascota removeMascota(Mascota mascota) {
		getMascotas().remove(mascota);
		mascota.setEspecieMascota(null);

		return mascota;
	}

}