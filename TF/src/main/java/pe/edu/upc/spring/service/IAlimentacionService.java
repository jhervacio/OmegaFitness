package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Alimentacion;

public interface IAlimentacionService {
	public boolean insertar(Alimentacion alimentacion);
	public boolean modificar(Alimentacion alimentacion);
	public void eliminar(int idAlimentacion);
	public Optional<Alimentacion> listarId(int idAlimentacion);
	public Optional<Alimentacion> buscarId(int idAlimentacion);
	List<Alimentacion> listar();
	List<Alimentacion> buscarNombre(String nombreAlimentacion);

}
