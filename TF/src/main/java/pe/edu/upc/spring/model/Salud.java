package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Salud")
public class Salud implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSalud;
	
	@Column(name="peso", nullable = false, length=30)
	private String peso;
	
	@Column(name = "estatura", nullable = false, length=50)
	private String estatura;
	
	@Column(name = "imc", nullable = false, length=50)
	private String imc;

	@ManyToOne
	@JoinColumn(name="idnombreCliente",nullable=false)
	private TipoPlan tipoplan;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaSalud", nullable=false,length = 50)
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date fechaSalud;
	
	public Salud() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salud(int idSalud, String peso, String estatura, String imc, Date fechaSalud) {
		super();
		this.idSalud = idSalud;
		this.peso = peso;
		this.estatura = estatura;
		this.imc = imc;
		this.fechaSalud = fechaSalud;
	}

	public int getIdSalud() {
		return idSalud;
	}

	public void setIdSalud(int idSalud) {
		this.idSalud = idSalud;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getEstatura() {
		return estatura;
	}

	public void setEstatura(String estatura) {
		this.estatura = estatura;
	}

	public String getImc() {
		return imc;
	}

	public void setImc(String imc) {
		this.imc = imc;
	}

	public Date getFechaSalud() {
		return fechaSalud;
	}

	public void setFechaSalud(Date fechaSalud) {
		this.fechaSalud = fechaSalud;
	}
	
	
}
