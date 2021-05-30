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

import pe.edu.upc.spring.model.Entrenador;
import pe.edu.upc.spring.service.IEntrenadorService;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {
	
	@Autowired
	private IEntrenadorService rService;
	
	@RequestMapping("/bienvenido")
	public String irEntrenadorBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoEntrenadores(Map<String, Object> model) {
		model.put("listaEntrenadores", rService.listar());
		return "listEntrenador";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("entrenador", new Entrenador());
		return "entrenador";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Entrenador objEntrenador, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "entrenador";
		else {
			boolean flag = rService.insertar(objEntrenador);
			if (flag)
				return "redirect:/entrenador/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/entrenador/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Entrenador> objEntrenador = rService.listarId(id);
		if(objEntrenador == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/entrenador/listar";
		}
		else {
			model.addAttribute("entrenador", objEntrenador);
			return "entrenador";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				rService.eliminar(id);
				model.put("listaEntrenadores", rService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaEntrenadores", rService.listar());
		}
		return "listEntrenador";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEntrenadores", rService.listar());
		return "listEntrenador";
	}
	
	
	
	
}
