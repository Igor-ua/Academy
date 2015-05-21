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

	private JPATeacherService jpaTeacherService;
	private JPAPersonService jpaPersonService;
	private StringDateFormatter sdf;

	@Autowired
	public void setSdf(BasicStringDateFormatter sdf) {
		this.sdf = sdf;
	}

	@Autowired
	public void setJpaTeacherService(JPATeacherService jpaTeacherService) {
		this.jpaTeacherService = jpaTeacherService;
	}

	@Autowired
	public void setJpaPersonService(JPAPersonService jpaPersonService) {
		this.jpaPersonService = jpaPersonService;
	}

	@RequestMapping(value = {"", "/"})
	public String teacher(ModelMap modelMap) {
		return "/fragments/entities/teacher/teacher";
	}

	@RequestMapping(
			value = "/fill",
			params = {"count"},
			method = RequestMethod.GET)
	@ResponseBody
	public boolean fillWithData(@RequestParam(value = "count") short count) {
		if (count > 0) {
			for (short i = 0; i < count; i++) {
				if (i % 2 == 0) {
					Person p1 = new Person();
					p1.setId(100L);
					jpaTeacherService.saveService(new Teacher(p1, new Date(), new Date()));
				} else {
					Person p2 = new Person();
					p2.setId(101L);
					jpaTeacherService.saveService(new Teacher(p2, new Date(), new Date()));
				}
			}
			return true;
		}
		return false;
	}

	@RequestMapping(
			value = "/find",
			method = RequestMethod.GET)
	public String findTeacherById(ModelMap modelMap) {
		return "/fragments/entities/teacher/find_teacher";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String findOne(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("teacher", jpaTeacherService.findTeacherByIdService(id));
		return "/fragments/entities/teacher/teacherlist";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveTeacherMapping(@RequestParam(value = "id") String id,
									ModelMap modelMap) {
		List<Person> persons = jpaPersonService.findAllPersonsService();
		modelMap.addAttribute("persons", persons);
		modelMap.addAttribute("id", id);
		return "/fragments/entities/teacher/save_teacher";
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
							  @RequestParam(value = "finish") String finish,
							  ModelMap modelMap) {
//		System.out.println("id: " + id + ", pid: " + personId + ", start: " + start + ", finish: " + finish);
		Teacher teacher = new Teacher();
		if (!id.equals("")) {
			teacher.setId(Long.parseLong(id));
		}
		teacher.setPerson(jpaPersonService.findPersonByIdService(Long.parseLong(personId)));
		try {
			teacher.setStart(sdf.parseToDate(start));
			teacher.setFinish(sdf.parseToDate(finish));
		} catch (ParseException e) {
			System.err.println("Parse error");
		}
		return jpaTeacherService.saveService(teacher);
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAll(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Teacher> page = new PageWrapper<>(
				jpaTeacherService.findAllTeachersService(pageable), "/db/teacher/show_all");
		modelMap.addAttribute("page", page);
		return "/fragments/entities/teacher/teacherlist";
	}

//	@RequestMapping(
//			value = "/find",
//			params = {"name", "birthday", "passport"},
//			method = RequestMethod.GET)
//	public String findByAny(@RequestParam(value = "name") String name,
//							@RequestParam(value = "birthday") String birthday,
//							@RequestParam(value = "passport") String passport,
//							ModelMap modelMap,
//							Pageable pageable) {
//		Date bday = null;
//		try {
//			bday = sdf.parseToDate(birthday);
//		} catch (ParseException e) {
//			System.err.println("Parse error");
//		}
//		String url = "/db/person/find" + "?name=" + name + "&birthday=" + birthday + "&passport=" + passport;
//		PageWrapper<Person> page = new PageWrapper<>(
//				jpaPersonService.findByAny(name, bday, passport, pageable), url);
//		modelMap.addAttribute("page", page);
//		return "/fragments/entities/person/personlist";
//	}

//	@RequestMapping(
//			value = "/delete",
//			params = {"id"},
//			method = RequestMethod.GET)
//	public String deletePerson(@RequestParam(value = "id") long id,
//							   ModelMap modelMap, Pageable pageable) {
//		jpaPersonService.deleteService(jpaPersonService.findPersonByIdService(id));
//		PageWrapper<Person> page = new PageWrapper<>(
//				jpaPersonService.findAllPersonsService(pageable), "/db/person/delete");
//		modelMap.addAttribute("page", page);
//		return "/fragments/entities/person/delete_person";
//	}
//
//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
//	public String deletePerson(ModelMap modelMap, Pageable pageable) {
//		PageWrapper<Person> page = new PageWrapper<>(
//				jpaPersonService.findAllPersonsService(pageable), "/db/person/delete");
//		modelMap.addAttribute("page", page);
//		return "/fragments/entities/person/delete_person";
//	}
//
//	@RequestMapping(value = "/update", method = RequestMethod.GET)
//	public String updatePerson(ModelMap modelMap, Pageable pageable) {
//		PageWrapper<Person> page = new PageWrapper<>(
//				jpaPersonService.findAllPersonsService(pageable), "/db/person/update");
//		modelMap.addAttribute("page", page);
//		return "/fragments/entities/person/update_person";
//	}
}