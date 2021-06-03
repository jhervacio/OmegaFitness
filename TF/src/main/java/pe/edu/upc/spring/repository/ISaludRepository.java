package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Salud;

@Repository
public interface ISaludRepository extends JpaRepository<Salud, Integer>{

	@Query("from Cliente a where a.nombreCliente like %:nombreCliente%")
	List<Salud> findByNombreCliente(String nombreCliente);
	
}
