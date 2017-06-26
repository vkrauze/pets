package net.pets.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.pets.model.Pet;
import net.pets.service.PetService;

/**
 * Controller - Spring
 * 
 */
@Controller
public class PetController {

	private PetService petService;
	
	@RequestMapping("/pet/view.action")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		try{
			List<Pet> pets = petService.getPetList();
			return getModelMap(pets);
		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to retrieve pets.");
		}
	}

	@RequestMapping("/pet/create.action")
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			Object data = request.getParameter("data");
			List<Pet> pets = petService.create(data);
			return getModelMap(pets);
		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create pet.");
		}
	}
	
	@RequestMapping("/pet/update.action")
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			Object data = request.getParameter("data");
			List<Pet> pets = petService.update(data);
			return getModelMap(pets);
		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update pet.");
		}
	}
	
	@RequestMapping("/pet/delete.action")
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			String data = request.getParameter("data");
			petService.delete(data);
			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);
			return new ModelAndView("jsonView", modelMap);
		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete pet.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param pets
	 * @return
	 */
	private ModelAndView getModelMap(List<Pet> pets){
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", pets.size());
		modelMap.put("data", pets);
		modelMap.put("success", true);
		return new ModelAndView("jsonView", modelMap);
	}
	
	/**
	 * Generates modelMap to return in the modelAndView in case
	 * of exception
	 * @param msg message
	 * @return
	 */
	private ModelAndView getModelMapError(String msg){
		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);
		return new ModelAndView("jsonView",modelMap);
	} 

	/**
	 * Spring use - DI
	 * @param dadoService
	 */
	public void setPetService(PetService petService) {
		this.petService = petService;
	}
}
