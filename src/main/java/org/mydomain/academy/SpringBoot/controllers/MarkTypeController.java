package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.MarkType;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAMarkTypeService;
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
@RequestMapping("/db/marktype")
public class MarkTypeController {

	@Autowired
	private JPAMarkTypeService jpaMarkTypeService;
	private static final String MARK_TYPE_ROUTE = "/fragments/entities/marktype";

	@RequestMapping(value = {"", "/"})
	public String markTypeRootPage() {
		return MARK_TYPE_ROUTE + "/marktype";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllMarkTypes(ModelMap modelMap, Pageable pageable) {
		PageWrapper<MarkType> page = new PageWrapper<>(jpaMarkTypeService.findAllMarkTypesService(pageable),
				"/db/markType/show_all");
		modelMap.addAttribute("page", page);
		return MARK_TYPE_ROUTE + "/marktypelist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showMarkTypeById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("markType", jpaMarkTypeService.findMarkTypeByIdService(id));
		return "/fragments/entities/markType/marktypelist";
	}

	@RequestMapping(value = "/find")
	public String findMarkType() {
		return MARK_TYPE_ROUTE + "/find_markType";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveNewMarkType(@RequestParam(value = "id") String id,
								  ModelMap modelMap) {
		modelMap.addAttribute("id", id);
		return MARK_TYPE_ROUTE + "/save_marktype";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "name"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean saveMarkTypeById(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "name") String name) {
		MarkType markType = new MarkType();
		if (!id.equals("")) {
			markType.setId(Long.parseLong(id));
		}
		markType.setName(name);
		return jpaMarkTypeService.saveService(markType);
	}

	@RequestMapping(
			value = "/find",
			params = {"name"})
	public String findByAny(
			@RequestParam(value = "name") String name,
			ModelMap modelMap,
			Pageable pageable) {
		String url = "/db/markType/find" + "?name=" + name;
		PageWrapper<MarkType> page = new PageWrapper<>(
				jpaMarkTypeService.findByAny(name, pageable), url);
		modelMap.addAttribute("page", page);
		return MARK_TYPE_ROUTE + "/marktypelist";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deleteMarkType(@RequestParam(value = "id") long id,
								 ModelMap modelMap, Pageable pageable) {
		jpaMarkTypeService.deleteService(jpaMarkTypeService.findMarkTypeByIdService(id));
		PageWrapper<MarkType> page = new PageWrapper<>(
				jpaMarkTypeService.findAllMarkTypesService(pageable), "/db/markType/delete");
		modelMap.addAttribute("page", page);
		return MARK_TYPE_ROUTE + "/delete_marktype";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteMarkType(ModelMap modelMap, Pageable pageable) {
		PageWrapper<MarkType> page = new PageWrapper<>(
				jpaMarkTypeService.findAllMarkTypesService(pageable), "/db/markType/delete");
		modelMap.addAttribute("page", page);
		return MARK_TYPE_ROUTE + "/delete_marktype";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateMarkType(ModelMap modelMap, Pageable pageable) {
		PageWrapper<MarkType> page = new PageWrapper<>(
				jpaMarkTypeService.findAllMarkTypesService(pageable), "/db/markType/update");
		modelMap.addAttribute("page", page);
		return MARK_TYPE_ROUTE + "/update_marktype";
	}
}