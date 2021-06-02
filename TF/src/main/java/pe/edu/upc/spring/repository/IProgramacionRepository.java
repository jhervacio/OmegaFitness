package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Actividad;
import pe.edu.upc.spring.model.Programacion;

@Repository
public interface IProgramacionRepository extends JpaRepository<Programacion, Integer>{
	@Query("from Actividad p where p.actividad.nombreActividad like %:nombreActividad%")
	List<Actividad> buscarActividad(@Param("nombreActividad") String nombreActividad);
	
	List<Actividad> buscarPorFecProgram(Date fechaProgramacion );
}
