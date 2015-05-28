package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPASpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/db/specialization")
public class SpecializationController {

	@Autowired
	private JPASpecializationService jpaSpecializationService;
	private static final String SPECIALIZATION_ROUTE = "/fragments/entities/specialization";

	@RequestMapping(value = {"", "/"})
	public String specializationRootPage() {
		return SPECIALIZATION_ROUTE + "/specialization";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllSpecializations(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Specialization> page = new PageWrapper<>(
				jpaSpecializationService.findAllSpecializationsService(pageable),
				"/db/specialization/show_all");
		modelMap.addAttribute("page", page);
		return SPECIALIZATION_ROUTE + "/specializationlist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showSpecializationById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("specialization", jpaSpecializationService.findSpecializationByIdService(id));
		return "/fragments/entities/specialization/specializationlist";
	}

	@RequestMapping(value = "/find")
	public String findSpecialization() {
		return SPECIALIZATION_ROUTE + "/find_specialization";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveNewSpecialization(
			@RequestParam(value = "id") String id,
			ModelMap modelMap) {
		modelMap.addAttribute("id", id);
		return SPECIALIZATION_ROUTE + "/save_specialization";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "name"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean saveSpecializationById(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "name") String name) {
		Specialization specialization = new Specialization();
		if (!id.equals("")) {
			specialization.setId(Long.parseLong(id));
		}
		specialization.setName(name);
		return jpaSpecializationService.saveService(specialization);
	}

	@RequestMapping(
			value = "/find",
			params = {"name"})
	public String findByAny(
			@RequestParam(value = "name") String name,
			ModelMap modelMap,
			Pageable pageable) {
		String url = "/db/specialization/find" + "?name=" + name;
		PageWrapper<Specialization> page = new PageWrapper<>(
				jpaSpecializationService.findByAny(name, pageable), url);
		modelMap.addAttribute("page", page);
		return SPECIALIZATION_ROUTE + "/specializationlist";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deleteSpecialization(@RequestParam(value = "id") long id,
									   ModelMap modelMap, Pageable pageable) {
		jpaSpecializationService.deleteService(jpaSpecializationService.findSpecializationByIdService(id));
		PageWrapper<Specialization> page = new PageWrapper<>(
				jpaSpecializationService.findAllSpecializationsService(pageable), "/db/specialization/delete");
		modelMap.addAttribute("page", page);
		return SPECIALIZATION_ROUTE + "/delete_specialization";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteSpecialization(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Specialization> page = new PageWrapper<>(
				jpaSpecializationService.findAllSpecializationsService(pageable), "/db/specialization/delete");
		modelMap.addAttribute("page", page);
		return SPECIALIZATION_ROUTE + "/delete_specialization";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateSpecialization(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Specialization> page = new PageWrapper<>(
				jpaSpecializationService.findAllSpecializationsService(pageable), "/db/specialization/update");
		modelMap.addAttribute("page", page);
		return SPECIALIZATION_ROUTE + "/update_specialization";
	}
}