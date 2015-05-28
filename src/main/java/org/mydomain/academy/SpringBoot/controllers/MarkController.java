package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.*;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
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

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/db/mark")
public class MarkController {

	@Autowired
	private JPAMarkService jpaMarkService;
	@Autowired
	private JPAMarkTypeService jpaMarkTypeService;
	@Autowired
	private JPATeacherService jpaTeacherService;
	@Autowired
	private JPAStudentService jpaStudentService;
	@Autowired
	private JPAGroupService jpaGroupService;
	@Autowired
	private JPASubjectService jpaSubjectService;
	@Autowired
	private JPAFormService jpaFormService;

	private StringDateFormatter sdf;

	@Autowired
	public void setSdf(BasicStringDateFormatter sdf) {
		this.sdf = sdf;
	}

	private static final String MARK_ROUTE = "/fragments/entities/mark";

	@RequestMapping(value = {"", "/"})
	public String markRootPage() {
		return MARK_ROUTE + "/mark";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllMarks(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Mark> page = new PageWrapper<>(jpaMarkService.findAllMarksService(pageable),
				"/db/mark/show_all");
		modelMap.addAttribute("page", page);
		return MARK_ROUTE + "/marklist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showMarkById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("mark", jpaMarkService.findMarkByIdService(id));
		return "/fragments/entities/mark/marklist";
	}

	@RequestMapping(value = "/find")
	public String findMark() {
		return MARK_ROUTE + "/find_mark";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveNewMark(@RequestParam(value = "id") String id,
							  ModelMap modelMap) {
		modelMap.addAttribute("id", id);

		List<MarkType> markTypes = jpaMarkTypeService.findAllMarkTypesService();
		modelMap.addAttribute("markTypes", markTypes);

		List<Teacher> teachers = jpaTeacherService.findAllTeachersService();
		modelMap.addAttribute("teachers", teachers);

		List<Student> students = jpaStudentService.findAllStudentsService();
		modelMap.addAttribute("students", students);

		List<Group> groups = jpaGroupService.findAllGroupsService();
		modelMap.addAttribute("groups", groups);

		List<Subject> subjects = jpaSubjectService.findAllSubjectsService();
		modelMap.addAttribute("subjects", subjects);

		List<Form> forms = jpaFormService.findAllFormsService();
		modelMap.addAttribute("forms", forms);

		return MARK_ROUTE + "/save_mark";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "date", "markType_id", "teacher_id", "student_id", "group_id", "subject_id", "form_id"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean saveMarkById(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "date") String date,
			@RequestParam(value = "markType_id") String markType_id,
			@RequestParam(value = "teacher_id") String teacher_id,
			@RequestParam(value = "student_id") String student_id,
			@RequestParam(value = "group_id") String group_id,
			@RequestParam(value = "subject_id") String subject_id,
			@RequestParam(value = "form_id") String form_id) {
		Mark mark = new Mark();
		if (!id.equals("")) {
			mark.setId(Long.parseLong(id));
		}
		try {
			mark.setDate(sdf.parseToDate(date));
		} catch (ParseException ignored) {
			//supposed to be sent into logs
		}
		mark.setMarkType(jpaMarkTypeService.findMarkTypeByIdService(Long.parseLong(markType_id)));
		mark.setTeacher(jpaTeacherService.findTeacherByIdService(Long.parseLong(teacher_id)));
		mark.setStudent(jpaStudentService.findStudentByIdService(Long.parseLong(student_id)));
		mark.setGroup(jpaGroupService.findGroupByIdService(Long.parseLong(group_id)));
		mark.setSubject(jpaSubjectService.findSubjectByIdService(Long.parseLong(subject_id)));
		mark.setForm(jpaFormService.findFormByIdService(Long.parseLong(form_id)));

		return jpaMarkService.saveService(mark);
	}

	@RequestMapping(
			value = "/find",
			params = {"teacherName", "studentName"})
	public String findByAny(
			@RequestParam(value = "teacherName") String teacherName,
			@RequestParam(value = "studentName") String studentName,
			ModelMap modelMap,
			Pageable pageable) {
		String url = "/db/mark/find" + "?teacherName=" + teacherName + "&studentName=" + studentName;
		url = url.replaceAll(" ", "%20");
		PageWrapper<Mark> page = new PageWrapper<>(
				jpaMarkService.findByAny(teacherName, studentName, pageable), url);
		modelMap.addAttribute("page", page);
		return MARK_ROUTE + "/marklist";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deleteMark(@RequestParam(value = "id") long id,
							 ModelMap modelMap, Pageable pageable) {
		jpaMarkService.deleteService(jpaMarkService.findMarkByIdService(id));
		PageWrapper<Mark> page = new PageWrapper<>(
				jpaMarkService.findAllMarksService(pageable), "/db/mark/delete");
		modelMap.addAttribute("page", page);
		return MARK_ROUTE + "/delete_mark";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteMark(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Mark> page = new PageWrapper<>(
				jpaMarkService.findAllMarksService(pageable), "/db/mark/delete");
		modelMap.addAttribute("page", page);
		return MARK_ROUTE + "/delete_mark";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateMark(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Mark> page = new PageWrapper<>(
				jpaMarkService.findAllMarksService(pageable), "/db/mark/update");
		modelMap.addAttribute("page", page);
		return MARK_ROUTE + "/update_mark";
	}
}