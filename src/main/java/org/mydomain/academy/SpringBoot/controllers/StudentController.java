package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.entities.Student;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAGroupService;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAPersonService;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAStudentService;
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
@RequestMapping("/db/student")
public class StudentController {

	@Autowired
	private JPAStudentService jpaStudentService;
	@Autowired
	private JPAPersonService jpaPersonService;
	@Autowired
	private JPAGroupService jpaGroupService;

	private StringDateFormatter sdf;
	private static final String STUDENT_ROUTE = "/fragments/entities/student";

	@Autowired
	public void setSdf(BasicStringDateFormatter sdf) {
		this.sdf = sdf;
	}

	@RequestMapping(value = {"", "/"})
	public String studentRootPage() {
		return STUDENT_ROUTE + "/student";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllStudents(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Student> page = new PageWrapper<>(
				jpaStudentService.findAllStudentsService(pageable), "/db/student/show_all");
		modelMap.addAttribute("page", page);
		return STUDENT_ROUTE + "/studentlist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showStudentById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("student", jpaStudentService.findStudentByIdService(id));
		return STUDENT_ROUTE + "/studentlist";
	}

	@RequestMapping(
			value = "/find",
			method = RequestMethod.GET)
	public String findStudent() {
		return STUDENT_ROUTE + "/find_student";
	}

	@RequestMapping(
			value = "/find",
			params = {"personName", "groupName"},
			method = RequestMethod.GET)
	public String findByAny(
			@RequestParam(value = "personName") String personName,
			@RequestParam(value = "groupName") String groupName,
			ModelMap modelMap,
			Pageable pageable) {
		String url = "/db/student/find" + "?personName=" + personName + "&groupName=" + groupName;
		url = url.replaceAll(" ", "%20");
		PageWrapper<Student> page = new PageWrapper<>(
				jpaStudentService.findByAny(personName, groupName, pageable), url);
		modelMap.addAttribute("page", page);
		return STUDENT_ROUTE + "/studentlist";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveNewStudent(
			@RequestParam(value = "id") String id,
			ModelMap modelMap) {

		List<Person> persons = jpaPersonService.findAllPersonsService();
		modelMap.addAttribute("persons", persons);

		List<Group> groups = jpaGroupService.findAllGroupsService();
		modelMap.addAttribute("groups", groups);

		modelMap.addAttribute("id", id);
		return STUDENT_ROUTE + "/save_student";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "person_id", "group_id", "start", "finish"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean saveStudentById(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "person_id") String personId,
			@RequestParam(value = "group_id") String group_id,
			@RequestParam(value = "start") String start,
			@RequestParam(value = "finish") String finish) {
		Student student = new Student();
		if (!id.equals("")) {
			student.setId(Long.parseLong(id));
		}
		student.setPerson(jpaPersonService.findPersonByIdService(Long.parseLong(personId)));
		try {
			student.setStart(sdf.parseToDate(start));
			student.setFinish(sdf.parseToDate(finish));
		} catch (ParseException e) {
			//supposed to be sent into logs
		}
		student.setGroup(jpaGroupService.findGroupByIdService(Long.parseLong(group_id)));
		return jpaStudentService.saveService(student);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateStudent(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Student> page = new PageWrapper<>(
				jpaStudentService.findAllStudentsService(pageable), "/db/student/update");
		modelMap.addAttribute("page", page);
		return STUDENT_ROUTE + "/update_student";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deleteStudent(@RequestParam(value = "id") long id,
								ModelMap modelMap, Pageable pageable) {
		jpaStudentService.deleteService(jpaStudentService.findStudentByIdService(id));
		PageWrapper<Student> page = new PageWrapper<>(
				jpaStudentService.findAllStudentsService(pageable), "/db/student/delete");
		modelMap.addAttribute("page", page);
		return STUDENT_ROUTE + "/delete_student";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteStudent(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Student> page = new PageWrapper<>(
				jpaStudentService.findAllStudentsService(pageable), "/db/student/delete");
		modelMap.addAttribute("page", page);
		return STUDENT_ROUTE + "/delete_student";
	}
}