package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Administrador;
import pe.edu.upc.spring.repository.IAdministradorRepository;
import pe.edu.upc.spring.service.IAdministradorService;

@Service
public class AdministradorServiceImpl implements IAdministradorService{

	@Autowired
	private IAdministradorRepository adminR;
	
	@Override
	@Transactional
	public Integer insertar(Administrador administrador) {
		int rpta= adminR.buscarDocumentoAdministrador(administrador.getDocumentoAdministrador());
		if (rpta == 0)
			adminR.save(administrador);
		return rpta;
	}

	@Override
	@Transactional
	public boolean modificar(Administrador administrador) {
		boolean flag = false;
		try {
			adminR.save(administrador);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un problema");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idAdministrador) {
		adminR.deleteById(idAdministrador);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Administrador> listarId(int idAdministrador) {
		return adminR.findById(idAdministrador);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Administrador> listar() {
		return adminR.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Administrador> findByName(String nombreAdministrador) {
		List<Administrador> lista = adminR.findByName(nombreAdministrador);
		return lista;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Administrador> findByDocument(String documentoAdministrador) {
		List<Administrador> lista = adminR.findByDocument(documentoAdministrador);
		return lista;
	}

}
