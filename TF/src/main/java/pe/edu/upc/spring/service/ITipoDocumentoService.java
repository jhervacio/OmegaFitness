package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.TipoDocumento;

public interface ITipoDocumentoService {
	public boolean insertar(TipoDocumento td);
	public boolean modificar(TipoDocumento td);
	public void eliminar(int idTipoDocumento);
	public Optional<TipoDocumento> listarId(int idTipoDocumento);
	List<TipoDocumento> listar();

}
