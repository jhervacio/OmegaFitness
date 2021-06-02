package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Sala")
public class Sala implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSala;
	
	@Column(name="nombreSala", nullable = false, length=30)
	private String nombreSala;
	@Column(name = "capacidadSala", nullable = false, length=30)
	private String capacidadSala;
	
	@ManyToOne
	@JoinColumn(name="idGimnasio",nullable=false)
	private Gimnasio gimnasio;
	
	public Sala() {
		super();
	}

	public Sala(int idSala, String nombreSala, String capacidadSala, Gimnasio gimnasio) {
		super();
		this.idSala = idSala;
		this.nombreSala = nombreSala;
		this.capacidadSala = capacidadSala;
		this.gimnasio = gimnasio;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public String getCapacidadSala() {
		return capacidadSala;
	}

	public void setCapacidadSala(String capacidadSala) {
		this.capacidadSala = capacidadSala;
	}

	public Gimnasio getGimnasio() {
		return gimnasio;
	}

	public void setGimnasio(Gimnasio gimnasio) {
		this.gimnasio = gimnasio;
	}

	
}
	
