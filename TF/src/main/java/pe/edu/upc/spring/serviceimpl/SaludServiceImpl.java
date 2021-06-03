package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Salud;
import pe.edu.upc.spring.repository.ISaludRepository;
import pe.edu.upc.spring.service.ISaludService;

@Service
public class SaludServiceImpl implements ISaludService {

	@Autowired
	private ISaludRepository saludR;
	
	@Override
	@Transactional
	public boolean insertar(Salud salud) {
		Salud objSalud = saludR.save(salud);
		if(objSalud == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Salud salud) {
		boolean flag = false;
		try {
			saludR.save(salud);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un problema");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idSalud) {
		saludR.deleteById(idSalud);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Salud> listarId(int idSalud) {
		return saludR.findById(idSalud);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Salud> listar() {
		return saludR.findAll();
	}



	@Override
	@Transactional(readOnly=true)
	public List<Salud> findByNombreCliente(String salud) {
		List<Salud> lista = saludR.findByNombreCliente(salud);
		return lista;
	}



}
