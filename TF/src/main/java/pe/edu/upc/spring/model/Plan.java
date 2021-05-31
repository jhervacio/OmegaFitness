package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Plan")
public class Plan implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPlan;
	
	@Column(name="nombrePlan", length = 60, nullable=false)
	private String nombrePlan;

	@Column(name="PrecioPlan", length = 60, nullable=false)
	private String PrecioPlan;
	
	@Column(name="DescripcionPlan", length = 60, nullable=false)
	private String DescripcionPlan;
	
	@Column(name="Duracion", length = 60, nullable=false)
	private String duracion;
	
	
	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	
}
