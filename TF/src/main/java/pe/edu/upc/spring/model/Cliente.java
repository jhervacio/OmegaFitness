package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	
	@Column(name="nombreCliente", nullable = false, length=30)
	private String nombreCliente;
	@Column(name = "apellidoCliente", nullable = false, length=30)
	private String apellidoCliente;
	@Column(name = "edadCliente", nullable = false, length=30)
	private String edadCliente;
	@Column(name = "pesoCliente",nullable = false, length=30)
	private String pesoCliente;
	@Column(name = "estaturaCliente", nullable = false, length=30)
	private String estaturaCliente;
	@Column(name = "planCliente", nullable = false, length=30)
	private String planCliente;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaNacimiento", nullable=false,length = 50)
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date fechaNacimiento;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(int idCliente, String nombreCliente, String apellidoCliente, String edadCliente, String pesoCliente,
			String estaturaCliente,String planCliente,  Date fechaNacimiento) {
		super();
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.edadCliente = edadCliente;
		this.pesoCliente = pesoCliente;
		this.estaturaCliente = estaturaCliente;
		this.fechaNacimiento = fechaNacimiento;
		this.planCliente = planCliente;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoCliente() {
		return apellidoCliente;
	}
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}
	public String getEdadCliente() {
		return edadCliente;
	}
	public void setEdadCliente(String edadCliente) {
		this.edadCliente = edadCliente;
	}
	public String getPesoCliente() {
		return pesoCliente;
	}
	public void setPesoCliente(String pesoCliente) {
		this.pesoCliente = pesoCliente;
	}
	public String getEstaturaCliente() {
		return estaturaCliente;
	}
	public void setEstaturaCliente(String estaturaCliente) {
		this.estaturaCliente = estaturaCliente;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPlanCliente() {
		return planCliente;
	}
	public void setPlanCliente(String planCliente) {
		this.planCliente = planCliente;
	}

	
	
	
}
