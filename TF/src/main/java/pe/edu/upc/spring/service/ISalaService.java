package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Sala;

public interface ISalaService {

	public boolean insertar(Sala sala);
	public boolean modificar(Sala sala);
	public void eliminar(int idSala);
	public Optional<Sala> listarId(int idSala);
	List<Sala> listar();
	List<Sala> findByName(String nombreSala);
	//List<Sala> findByNameGym(String nombreGimnasio);
}
