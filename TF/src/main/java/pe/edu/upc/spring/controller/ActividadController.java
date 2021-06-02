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

import pe.edu.upc.spring.model.Actividad;
import pe.edu.upc.spring.service.IActividadService;

@Controller
@RequestMapping("/actividad")
public class ActividadController {

	@Autowired
	private IActividadService aService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoActividades(Map<String, Object> model) {
		model.put("listaActividades", aService.listar());
		return "listActividad";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("actividad", new Actividad());
		return "actividad";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Actividad objActividad, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
		return "actividad";
		else {
			boolean flag = aService.insertar(objActividad);
			if (flag)
				return "redirect:/actividad/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/actividad/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Actividad> objActividad = aService.listarId(id);
		if(objActividad == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/actividad/listar";
		}
		else {
			model.addAttribute("actividad", objActividad);
			return "actividad";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				aService.eliminar(id);
				model.put("listaActividades", aService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaActividades", aService.listar());
		}
		return "listActividad";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaActividades", aService.listar());
		return "listActividad";
	}
	
	
}
