package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.TipoDocumento;
import pe.edu.upc.spring.repository.ITipoDocumentoRepository;
import pe.edu.upc.spring.service.ITipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {

	@Autowired
	private ITipoDocumentoRepository dTipoDocumento;
	
	@Override
	@Transactional
	public boolean insertar(TipoDocumento td) {
		TipoDocumento objTipoDocumento = dTipoDocumento.save(td);
		if(objTipoDocumento == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(TipoDocumento td) {
		boolean flag = false;
		try {
			dTipoDocumento.save(td);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un problema");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idTipoDocumento) {
		dTipoDocumento.deleteById(idTipoDocumento);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoDocumento> listarId(int idTipoDocumento) {
		return dTipoDocumento.findById(idTipoDocumento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoDocumento> listar() {
		return dTipoDocumento.findAll();
	}



}
