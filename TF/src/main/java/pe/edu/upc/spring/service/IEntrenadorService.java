package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Entrenador;

public interface IEntrenadorService {
	public boolean insertar(Entrenador entrenador);
	public boolean modificar(Entrenador entrenador);
	public void eliminar(int idEntrenador);
	public Optional<Entrenador> listarId(int idEntrenador);
	List<Entrenador> listar();
	List<Entrenador> buscarNombre(String nombreEntrenador);

}
