package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Cliente;
import pe.edu.upc.spring.model.Salud;
import pe.edu.upc.spring.service.IClienteService;
import pe.edu.upc.spring.service.ISaludService;

@Controller
@RequestMapping("/salud")
public class SaludController {
	
	@Autowired
	private ISaludService sService;
	
	@Autowired
	private IClienteService cService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoSaludes(Map<String, Object> model) {
		model.put("listaSaludes", sService.listar());
		return "listSalud";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
	
		model.addAttribute("listaClientes", cService.listar());
		
		model.addAttribute("salud", new Salud());
		model.addAttribute("cliente", new Cliente());
		return "salud";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Salud objSalud, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaClientes", cService.listar());
			return "salud";
		}
		else {
			boolean flag = sService.insertar(objSalud);
			if (flag)
				return "redirect:/salud/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/salud/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Salud> objSalud = sService.listarId(id);
		if(objSalud == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/salud/listar";
		}
		else {
			model.addAttribute("listaClientes", cService.listar());
			
			if(objSalud.isPresent())
				objSalud.ifPresent(o-> model.addAttribute("salud",o));
		//	model.addAttribute("salud", objSalud);
			return "salud";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				sService.eliminar(id);
				model.put("listaSaludes", sService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaSaludes", sService.listar());
		}
		return "listSalud";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSaludes", sService.listar());
		return "listSalud";
	}
	
}
