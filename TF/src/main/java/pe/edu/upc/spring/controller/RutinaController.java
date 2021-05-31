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

import pe.edu.upc.spring.model.Rutina;
import pe.edu.upc.spring.service.IRutinaService;

@Controller
@RequestMapping("/rutina")
public class RutinaController {
	
	@Autowired
	private IRutinaService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoRutinas(Map<String, Object> model) {
		model.put("listaRutinas", rService.listar());
		return "listRutina";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("rutina", new Rutina());
		return "rutina";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Rutina objRutina, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "rutina";
		else {
			boolean flag = rService.insertar(objRutina);
			if (flag)
				return "redirect:/rutina/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/rutina/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Rutina> objRutina = rService.listarId(id);
		if(objRutina == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/rutina/listar";
		}
		else {
			model.addAttribute("rutina", objRutina);
			return "rutina";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				rService.eliminar(id);
				model.put("listaRutinas", rService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaRutinas", rService.listar());
		}
		return "listRutina";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaRutinas", rService.listar());
		return "listRutina";
	}
	
	
	
	
}
