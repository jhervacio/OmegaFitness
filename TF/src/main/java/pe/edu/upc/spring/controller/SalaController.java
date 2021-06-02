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

import pe.edu.upc.spring.model.Sala;
import pe.edu.upc.spring.service.ISalaService;

@Controller
@RequestMapping("/sala")
public class SalaController {

	@Autowired
	private ISalaService sService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoSalas(Map<String, Object> model) {
		model.put("listaSalas", sService.listar());
		return "listSala";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("sala", new Sala());
		return "sala";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Sala objSala, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
		return "sala";
		else {
			boolean flag = sService.insertar(objSala);
			if (flag)
				return "redirect:/sala/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/sala/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Sala> objSala= sService.listarId(id);
		if(objSala == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/sala/listar";
		}
		else {
			model.addAttribute("sala", objSala);
			return "sala";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				sService.eliminar(id);
				model.put("listaSalas", sService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaSalas", sService.listar());
		}
		return "listSala";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSalas", sService.listar());
		return "listSala";
	}
}
