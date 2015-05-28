package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.db.entities.Subject;
import org.mydomain.academy.services.impls.JPAServiceImpl.*;
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
@RequestMapping("/db/subject")
public class SubjectController {

	@Autowired
	private JPASubjectService jpaSubjectService;

	@Autowired
	private JPASpecializationService jpaSpecializationService;

	private static final String SUBJECT_ROUTE = "/fragments/entities/subject";


	@RequestMapping(value = {"", "/"})
	public String subjectRootPage() {
		return SUBJECT_ROUTE + "/subject";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllSubjects(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Subject> page = new PageWrapper<>(
				jpaSubjectService.findAllSubjectsService(pageable), "/db/subject/show_all");
		modelMap.addAttribute("page", page);
		return SUBJECT_ROUTE + "/subjectlist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showSubjectById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("subject", jpaSubjectService.findSubjectByIdService(id));
		return SUBJECT_ROUTE + "/subjectlist";
	}

	@RequestMapping(
			value = "/find",
			method = RequestMethod.GET)
	public String findSubject() {
		return SUBJECT_ROUTE + "/find_subject";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveNewSubject(
			@RequestParam(value = "id") String id,
			ModelMap modelMap) {

		List<Specialization> specializations = jpaSpecializationService.findAllSpecializationsService();
		modelMap.addAttribute("specializations", specializations);

		modelMap.addAttribute("id", id);
		return SUBJECT_ROUTE + "/save_subject";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "name", "specialization_id"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean saveSubjectById(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "specialization_id") String specialization_id) {
		Subject subject = new Subject();
		if (!id.equals("")) {
			subject.setId(Long.parseLong(id));
		}

		subject.setName(name);
		subject.setSpecialization(jpaSpecializationService.findSpecializationByIdService
				(Long.parseLong(specialization_id)));

		return jpaSubjectService.saveService(subject);
	}


	@RequestMapping(
			value = "/find",
			params = {"subjectName", "specializationName"},
			method = RequestMethod.GET)
	public String findByAny(
			@RequestParam(value = "subjectName") String subjectName,
			@RequestParam(value = "specializationName") String specializationName,
			ModelMap modelMap,
			Pageable pageable) {
		String url = "/db/subject/find" + "?subjectName=" + subjectName + "&specializationName=" + specializationName;
		PageWrapper<Subject> page = new PageWrapper<>(
				jpaSubjectService.findByAny(subjectName, specializationName, pageable), url);
		modelMap.addAttribute("page", page);
		return SUBJECT_ROUTE + "/subjectlist";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deleteSubject(@RequestParam(value = "id") long id,
								ModelMap modelMap, Pageable pageable) {
		jpaSubjectService.deleteService(jpaSubjectService.findSubjectByIdService(id));
		PageWrapper<Subject> page = new PageWrapper<>(
				jpaSubjectService.findAllSubjectsService(pageable), "/db/subject/delete");
		modelMap.addAttribute("page", page);
		return SUBJECT_ROUTE + "/delete_subject";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteSubject(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Subject> page = new PageWrapper<>(
				jpaSubjectService.findAllSubjectsService(pageable), "/db/subject/delete");
		modelMap.addAttribute("page", page);
		return SUBJECT_ROUTE + "/delete_subject";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateSubject(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Subject> page = new PageWrapper<>(
				jpaSubjectService.findAllSubjectsService(pageable), "/db/subject/update");
		modelMap.addAttribute("page", page);
		return SUBJECT_ROUTE + "/update_subject";
	}
}