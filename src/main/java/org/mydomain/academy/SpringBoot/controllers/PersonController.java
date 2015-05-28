package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/db/person")
public class PersonController {

	@Autowired
	private JPAPersonService jpaPersonService;
	private StringDateFormatter sdf;
	private static final String PERSON_ROUTE = "/fragments/entities/person";

	@Autowired
	public void setSdf(BasicStringDateFormatter sdf) {
		this.sdf = sdf;
	}

	@RequestMapping(value = {"", "/"})
	public String personRootPage() {
		return PERSON_ROUTE + "/person";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllPersons(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Person> page = new PageWrapper<>(jpaPersonService.findAllPersonsService(pageable),
				"/db/person/show_all");
		modelMap.addAttribute("page", page);
		return PERSON_ROUTE + "/personlist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showPersonById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("person", jpaPersonService.findPersonByIdService(id));
		return "/fragments/entities/person/personlist";
	}

	@RequestMapping(value = "/find")
	public String findPerson() {
		return PERSON_ROUTE + "/find_person";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveNewPerson(@RequestParam(value = "id") String id,
								ModelMap modelMap) {
		modelMap.addAttribute("id", id);
		return PERSON_ROUTE + "/save_person";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "name", "birthday", "passport"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean savePersonById(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "birthday") String birthday,
			@RequestParam(value = "passport") String passport) {
		Person person = new Person();
		if (!id.equals("")) {
			person.setId(Long.parseLong(id));
		}
		person.setName(name);
		person.setPassport(passport);
		try {
			person.setBirthday(sdf.parseToDate(birthday));
		} catch (ParseException ignored) {
			//supposed to be sent into logs
		}
		return jpaPersonService.saveService(person);
	}

	@RequestMapping(
			value = "/find",
			params = {"name", "birthday", "passport"})
	public String findByAny(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "birthday") String birthday,
			@RequestParam(value = "passport") String passport,
			ModelMap modelMap,
			Pageable pageable) {
		Date bday = null;
		try {
			bday = sdf.parseToDate(birthday);
		} catch (ParseException e) {
			//supposed to be sent into logs
		}
		String url = "/db/person/find" + "?name=" + name + "&birthday=" + birthday + "&passport=" + passport;
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findByAny(name, bday, passport, pageable), url);
		modelMap.addAttribute("page", page);
		return PERSON_ROUTE + "/personlist";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deletePerson(@RequestParam(value = "id") long id,
							   ModelMap modelMap, Pageable pageable) {
		jpaPersonService.deleteService(jpaPersonService.findPersonByIdService(id));
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findAllPersonsService(pageable), "/db/person/delete");
		modelMap.addAttribute("page", page);
		return PERSON_ROUTE + "/delete_person";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deletePerson(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findAllPersonsService(pageable), "/db/person/delete");
		modelMap.addAttribute("page", page);
		return PERSON_ROUTE + "/delete_person";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updatePerson(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findAllPersonsService(pageable), "/db/person/update");
		modelMap.addAttribute("page", page);
		return PERSON_ROUTE + "/update_person";
	}
}