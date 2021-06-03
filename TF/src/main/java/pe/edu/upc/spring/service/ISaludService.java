package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Salud;

public interface ISaludService {

	public boolean insertar(Salud salud);
	public boolean modificar(Salud salud);
	public void eliminar(int idSalud);
	public Optional<Salud> listarId(int idSalud);
	List<Salud> listar();
	List<Salud> findByNombreCliente(String nombreCliente);
}
