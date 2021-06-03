package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.ClientePlan;

public interface IClientePlanService {
	public boolean insertar(ClientePlan clientePlan);
	public boolean modificar(ClientePlan clientePlan);
	public void eliminar(int idClientePlanPlan);
	public Optional<ClientePlan> listarId(int idClientePlan);

	List<ClientePlan> listar();
	//List<ClientePlan> findByNombrePlan(String nombreCliente);

	

}
