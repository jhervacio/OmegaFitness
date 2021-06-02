package pe.edu.upc.spring.controller;

import java.util.List;
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


import pe.edu.upc.spring.model.ClientePlan;
import pe.edu.upc.spring.service.IClientePlanService;

import pe.edu.upc.spring.model.Plan;
import pe.edu.upc.spring.service.IPlanService;

import pe.edu.upc.spring.model.Cliente;
import pe.edu.upc.spring.service.IClienteService;

@Controller
@RequestMapping("/clienteplan")
public class ClientePlanController {
	
	@Autowired
	private IClientePlanService cpService;
	
	
	@Autowired
	private IPlanService tService;

	@Autowired
	private IClienteService cService;
	
	@RequestMapping("/bienvenido")
	public String irPlanBienvenida() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoPlanes(Map<String, Object> model) {
		model.put("listaClientePlanes", cpService.listar());
		return "listClientePlan";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaPlanes", tService.listar());
		model.addAttribute("listaClientes", cService.listar());
		
		model.addAttribute("clienteplan", new ClientePlan());
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("plan", new Plan());
		
		return "clienteplan";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute ClientePlan objClientePlan, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
		{
			model.addAttribute("listaPlanes", tService.listar());
			model.addAttribute("listaClientes", cService.listar());
			
			return "clienteplan";}
		else {
			boolean flag = cpService.insertar(objClientePlan);
			if (flag)
				return "redirect:/clienteplan/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/clienteplan/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<ClientePlan> objClientePlan = cpService.listarId(id);
		if(objClientePlan == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/clienteplan/listar";
		}
		else {
			model.addAttribute("listaPlanes", tService.listar());
			model.addAttribute("listaClientes", cService.listar());
			
			if(objClientePlan.isPresent())
				objClientePlan.ifPresent(o-> model.addAttribute("clienteplan",o));
			
			return "clienteplan";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				cpService.eliminar(id);
				model.put("listaClientePlanes", cpService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaClientePlanes", cpService.listar());
		}
		return "listClientePlan";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaClientePlanes", cpService.listar());
		return "listClientePlan";
	}
	
	@RequestMapping("/irBuscar")
	public String irPaginaBuscar(Model model ) {
		
		model.addAttribute("listaPlanes", tService.listar());
		model.addAttribute("listaClientes", cService.listar());
		
		model.addAttribute("clienteplan", new ClientePlan());
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("plan", new Plan());
		
		return "clienteplanbuscar";
		
		
	}
	
	
	@RequestMapping("/clienteplanbuscar")
	public String findbyPlan(Map<String, Object> model,
			@ModelAttribute ClientePlan plan ) throws ParseException {
		List<ClientePlan> listaClientesPlanes;
		plan.getCliente().setNombreCliente(plan.getCliente().getNombreCliente());
		listaClientesPlanes = cpService.findByNombrePlan(plan.getCliente().getNombreCliente());
		model.put("listaClientePlanes",listaClientesPlanes);
		return "clienteplanbuscar";
	}
	
}
