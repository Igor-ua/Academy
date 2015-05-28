package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.Form;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAFormService;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAGroupService;
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

import java.util.List;

@Controller
@RequestMapping("/db/group")
public class GroupController {

	@Autowired
	private JPAGroupService jpaGroupService;

	@Autowired
	private JPAFormService jpaFormService;

	@Autowired
	private JPASpecializationService jpaSpecializationService;

	private static final String GROUP_ROUTE = "/fragments/entities/group";

	@RequestMapping(value = {"", "/"})
	public String groupRootPage() {
		return GROUP_ROUTE + "/group";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllGroups(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Group> page = new PageWrapper<>(jpaGroupService.findAllGroupsService(pageable),
				"/db/group/show_all");
		modelMap.addAttribute("page", page);
		return GROUP_ROUTE + "/grouplist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showGroupById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("group", jpaGroupService.findGroupByIdService(id));
		return "/fragments/entities/group/grouplist";
	}

	@RequestMapping(value = "/find")
	public String findGroup() {
		return GROUP_ROUTE + "/find_group";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveNewGroup(@RequestParam(value = "id") String id,
							   ModelMap modelMap) {

		List<Form> forms = jpaFormService.findAllFormsService();
		modelMap.addAttribute("forms", forms);

		List<Specialization> specializations = jpaSpecializationService.findAllSpecializationsService();
		modelMap.addAttribute("specializations", specializations);

		modelMap.addAttribute("id", id);
		return GROUP_ROUTE + "/save_group";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "name"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean saveGroupById(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "name") String name) {
		Group group = new Group();
		if (!id.equals("")) {
			group.setId(Long.parseLong(id));
		}
		group.setName(name);
		return jpaGroupService.saveService(group);
	}

	@RequestMapping(
			value = "/find",
			params = {"groupName", "formName", "specializationName"})
	public String findByAny(
			@RequestParam(value = "groupName") String groupName,
			@RequestParam(value = "formName") String formName,
			@RequestParam(value = "specializationName") String specializationName,
			ModelMap modelMap,
			Pageable pageable) {
		String url = "/db/group/find" + "?groupName=" + groupName + "&formName="
				+ formName + "&specializationName=" + specializationName;
		url = url.replaceAll(" ", "%20");
		PageWrapper<Group> page = new PageWrapper<>(
				jpaGroupService.findByAny(groupName, formName, specializationName, pageable), url);
		modelMap.addAttribute("page", page);
		return GROUP_ROUTE + "/grouplist";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deleteGroup(@RequestParam(value = "id") long id,
							  ModelMap modelMap, Pageable pageable) {
		jpaGroupService.deleteService(jpaGroupService.findGroupByIdService(id));
		PageWrapper<Group> page = new PageWrapper<>(
				jpaGroupService.findAllGroupsService(pageable), "/db/group/delete");
		modelMap.addAttribute("page", page);
		return GROUP_ROUTE + "/delete_group";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGroup(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Group> page = new PageWrapper<>(
				jpaGroupService.findAllGroupsService(pageable), "/db/group/delete");
		modelMap.addAttribute("page", page);
		return GROUP_ROUTE + "/delete_group";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGroup(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Group> page = new PageWrapper<>(
				jpaGroupService.findAllGroupsService(pageable), "/db/group/update");
		modelMap.addAttribute("page", page);
		return GROUP_ROUTE + "/update_group";
	}
}