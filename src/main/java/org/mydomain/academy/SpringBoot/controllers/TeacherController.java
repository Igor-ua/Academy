package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.entities.Teacher;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAPersonService;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPATeacherService;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/db/teacher")
public class TeacherController {

	@Autowired
	private JPATeacherService jpaTeacherService;
	@Autowired
	private JPAPersonService jpaPersonService;
	private StringDateFormatter sdf;
	private static final String TEACHER_ROUTE = "/fragments/entities/teacher";

	@Autowired
	public void setSdf(BasicStringDateFormatter sdf) {
		this.sdf = sdf;
	}

	@RequestMapping(value = {"", "/"})
	public String teacherRootPage() {
		return TEACHER_ROUTE + "/teacher";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllTeachers(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Teacher> page = new PageWrapper<>(
				jpaTeacherService.findAllTeachersService(pageable), "/db/teacher/show_all");
		modelMap.addAttribute("page", page);
		return TEACHER_ROUTE + "/teacherlist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showTeacherById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("teacher", jpaTeacherService.findTeacherByIdService(id));
		return TEACHER_ROUTE + "/teacherlist";
	}

	@RequestMapping(
			value = "/find",
			method = RequestMethod.GET)
	public String findTeacher() {
		return TEACHER_ROUTE + "/find_teacher";
	}

	@RequestMapping(
			value = "/find",
			params = {"name", "start", "finish"},
			method = RequestMethod.GET)
	public String findByAny(@RequestParam(value = "name") String personName,
							@RequestParam(value = "start") String start,
							@RequestParam(value = "finish") String finish,
							ModelMap modelMap,
							Pageable pageable) {
		Date dStart = null;
		Date dFinish = null;
		try {
			dStart = sdf.parseToDate(start);
			dFinish = sdf.parseToDate(finish);
		} catch (ParseException e) {
			//supposed to be sent into logs
		}
		String url = "/db/teacher/find" + "?name=" + personName + "&start=" + start + "&finish=" + finish;
		url = url.replaceAll(" ", "%20");
		PageWrapper<Teacher> page = new PageWrapper<>(
				jpaTeacherService.findByAny(personName, dStart, dFinish, pageable), url);
		modelMap.addAttribute("page", page);
		return TEACHER_ROUTE + "/teacherlist";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveTeacher(@RequestParam(value = "id") String id,
							  ModelMap modelMap) {
		List<Person> persons = jpaPersonService.findAllPersonsService();
		modelMap.addAttribute("persons", persons);
		modelMap.addAttribute("id", id);
		return TEACHER_ROUTE + "/save_teacher";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "person_id", "start", "finish"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean saveTeacher(@RequestParam(value = "id", required = false, defaultValue = "") String id,
							   @RequestParam(value = "person_id") String personId,
							   @RequestParam(value = "start") String start,
							   @RequestParam(value = "finish") String finish) {
		Teacher teacher = new Teacher();
		if (!id.equals("")) {
			teacher.setId(Long.parseLong(id));
		}
		teacher.setPerson(jpaPersonService.findPersonByIdService(Long.parseLong(personId)));
		try {
			teacher.setStart(sdf.parseToDate(start));
			teacher.setFinish(sdf.parseToDate(finish));
		} catch (ParseException e) {
			//supposed to be sent into logs
		}
		return jpaTeacherService.saveService(teacher);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateTeacher(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Teacher> page = new PageWrapper<>(
				jpaTeacherService.findAllTeachersService(pageable), "/db/teacher/update");
		modelMap.addAttribute("page", page);
		return TEACHER_ROUTE + "/update_teacher";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deleteTeacher(@RequestParam(value = "id") long id,
								ModelMap modelMap, Pageable pageable) {
		jpaTeacherService.deleteService(jpaTeacherService.findTeacherByIdService(id));
		PageWrapper<Teacher> page = new PageWrapper<>(
				jpaTeacherService.findAllTeachersService(pageable), "/db/teacher/delete");
		modelMap.addAttribute("page", page);
		return TEACHER_ROUTE + "/delete_teacher";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteTeacher(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Teacher> page = new PageWrapper<>(
				jpaTeacherService.findAllTeachersService(pageable), "/db/teacher/delete");
		modelMap.addAttribute("page", page);
		return TEACHER_ROUTE + "/delete_teacher";
	}
}