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

import pe.edu.upc.spring.model.TipoPlan;
import pe.edu.upc.spring.service.ITipoPlanService;

@Controller
@RequestMapping("/tipoplan")
public class TipoPlanController {
	
	@Autowired
	private ITipoPlanService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoClientes(Map<String, Object> model) {
		model.put("listaTipoPlanes", rService.listar());
		return "listTipoPlan";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("tipoplan", new TipoPlan());
		return "tipoplan";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute TipoPlan objTipoPlan, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "tipoplan";
		else {
			boolean flag = rService.insertar(objTipoPlan);
			if (flag)
				return "redirect:/tipoplan/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/tipoplan/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<TipoPlan> objTipoPlan = rService.listarId(id);
		if(objTipoPlan == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/tipoplan/listar";
		}
		else {
			model.addAttribute("tipoplan", objTipoPlan);
			return "tipoplan";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				rService.eliminar(id);
				model.put("listaTipoPlanes", rService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaTipoPlanes", rService.listar());
		}
		return "listTipoPlan";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaTipoPlanes", rService.listar());
		return "listTipoPlan";
	}
	
	
	
	
}
