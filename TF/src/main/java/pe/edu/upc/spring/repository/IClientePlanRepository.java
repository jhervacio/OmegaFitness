package pe.edu.upc.spring.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import pe.edu.upc.spring.model.ClientePlan;


@Repository
public interface IClientePlanRepository extends JpaRepository<ClientePlan, Integer>{
	
	//@Query("from Cliente p where p.nombreCliente like %:nombreCliente%")
	//List<ClientePlan> findByNombreCliente(String nombreCliente);
	
}
