package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.Form;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAFormService;
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
@RequestMapping("/db/form")
public class FormController {

	@Autowired
	private JPAFormService jpaFormService;
	private static final String FORM_ROUTE = "/fragments/entities/form";

	@RequestMapping(value = {"", "/"})
	public String formRootPage() {
		return FORM_ROUTE + "/form";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllForms(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Form> page = new PageWrapper<>(jpaFormService.findAllFormsService(pageable),
				"/db/form/show_all");
		modelMap.addAttribute("page", page);
		return FORM_ROUTE + "/formlist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showFormById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("form", jpaFormService.findFormByIdService(id));
		return "/fragments/entities/form/formlist";
	}

	@RequestMapping(value = "/find")
	public String findForm() {
		return FORM_ROUTE + "/find_form";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveNewForm(@RequestParam(value = "id") String id,
							  ModelMap modelMap) {
		modelMap.addAttribute("id", id);
		return FORM_ROUTE + "/save_form";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "name"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean saveFormById(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "name") String name) {
		Form form = new Form();
		if (!id.equals("")) {
			form.setId(Long.parseLong(id));
		}
		form.setName(name);
		return jpaFormService.saveService(form);
	}

	@RequestMapping(
			value = "/find",
			params = {"name"})
	public String findByAny(
			@RequestParam(value = "name") String name,
			ModelMap modelMap,
			Pageable pageable) {
		String url = "/db/form/find" + "?name=" + name;
		PageWrapper<Form> page = new PageWrapper<>(
				jpaFormService.findByAny(name, pageable), url);
		modelMap.addAttribute("page", page);
		return FORM_ROUTE + "/formlist";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deleteForm(@RequestParam(value = "id") long id,
							 ModelMap modelMap, Pageable pageable) {
		jpaFormService.deleteService(jpaFormService.findFormByIdService(id));
		PageWrapper<Form> page = new PageWrapper<>(
				jpaFormService.findAllFormsService(pageable), "/db/form/delete");
		modelMap.addAttribute("page", page);
		return FORM_ROUTE + "/delete_form";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteForm(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Form> page = new PageWrapper<>(
				jpaFormService.findAllFormsService(pageable), "/db/form/delete");
		modelMap.addAttribute("page", page);
		return FORM_ROUTE + "/delete_form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateForm(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Form> page = new PageWrapper<>(
				jpaFormService.findAllFormsService(pageable), "/db/form/update");
		modelMap.addAttribute("page", page);
		return FORM_ROUTE + "/update_form";
	}
}