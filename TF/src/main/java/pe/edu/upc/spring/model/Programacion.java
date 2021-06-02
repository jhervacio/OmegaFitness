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
@Table(name="Programacion")
public class Programacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProgramacion;
	

	@Column(name = "HoraInicial", nullable = false, length=50)
	private String horainicial;

	@Column(name = "HoraFinal", nullable = false, length=50)
	private String horafinal;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaProgramacion", nullable=false,length = 50)
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date fechaProgramacion;
	
	
	@ManyToOne
	@JoinColumn(name="idActividad", nullable=false)
	private Actividad actividad;
	
	public Programacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Programacion(int idProgramacion, String horainicial, String horafinal, Date fechaProgramacion,
			Actividad actividad) {
		super();
		this.idProgramacion = idProgramacion;
		this.horainicial = horainicial;
		this.horafinal = horafinal;
		this.fechaProgramacion = fechaProgramacion;
		this.actividad = actividad;
	}

	public int getIdProgramacion() {
		return idProgramacion;
	}

	public void setIdProgramacion(int idProgramacion) {
		this.idProgramacion = idProgramacion;
	}

	public String getHorainicial() {
		return horainicial;
	}

	public void setHorainicial(String horainicial) {
		this.horainicial = horainicial;
	}

	public String getHorafinal() {
		return horafinal;
	}

	public void setHorafinal(String horafinal) {
		this.horafinal = horafinal;
	}

	public Date getFechaProgramacion() {
		return fechaProgramacion;
	}

	public void setFechaProgramacion(Date fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	
	
}
