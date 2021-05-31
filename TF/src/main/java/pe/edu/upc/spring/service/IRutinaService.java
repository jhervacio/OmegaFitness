package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Rutina;

public interface IRutinaService {
	public boolean insertar(Rutina rutina);
	public boolean modificar(Rutina rutina);
	public void eliminar(int idRutina);
	public Optional<Rutina> listarId(int idRutina);
	List<Rutina> listar();
	List<Rutina> buscarNombre(String nameRutina);

}
