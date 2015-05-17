package org.mydomain.academy.SpringBoot.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.SpringBoot.view.View;
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
public class PersonController {

	private JPAPersonService jpaPersonService;
	private StringDateFormatter sdf;

	@Autowired
	public void setSdf(BasicStringDateFormatter sdf) {
		this.sdf = sdf;
	}

	@Autowired
	public void setJpaPersonService(JPAPersonService jpaPersonService) {
		this.jpaPersonService = jpaPersonService;
	}

	@RequestMapping("/db/person")
	public String person(ModelMap modelMap) {
		return "/fragments/entities/person/person";
	}

	@RequestMapping(
			value = "/db/person/find",
			method = RequestMethod.GET)
	public String findPersonById(ModelMap modelMap) {
		return "/fragments/entities/person/find_person";
	}

	@RequestMapping(value = "/db/person/show",
			params = {"id"}, method = RequestMethod.POST)
	public String findOne(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("person", jpaPersonService.findPersonByIdService(id));
		return "/fragments/entities/person/personlist";
	}

	@RequestMapping(value = "/db/person/save", method = RequestMethod.GET)
	public String savePersonMapping(ModelMap modelMap) {
		return "/fragments/entities/person/save_person";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/db/person/save",
			params = {"name", "birthday", "passport"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean savePerson(@RequestParam(value = "name") String name,
							  @RequestParam(value = "birthday") String birthday,
							  @RequestParam(value = "passport") String passport,
							  ModelMap modelMap) {
		Person person = new Person();
		person.setName(name);
		person.setPassport(passport);
		try {
			person.setBirthday(sdf.parseToDate(birthday));
		} catch (ParseException e) {
			System.err.println("Parse error");
		}
		return jpaPersonService.savePersonService(person);
	}

	@RequestMapping(value = "/db/person/show_all", method = RequestMethod.GET)
	public String findAll(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findAllPersonsService(pageable), "/db/person/show_all");
		modelMap.addAttribute("page", page);
		return "/fragments/entities/person/personlist";
	}

	@RequestMapping(
			value = "/db/person/find",
			params = {"name", "birthday", "passport"},
			method = RequestMethod.GET)
	public String findByAny(@RequestParam(value = "name") String name,
							@RequestParam(value = "birthday") String birthday,
							@RequestParam(value = "passport") String passport,
							ModelMap modelMap,
							Pageable pageable) {
		Date bday = null;
		try {
			bday = sdf.parseToDate(birthday);
		} catch (ParseException e) {
			System.err.println("Parse error");
		}
		String url = "/db/person/find" + "?name=" + name + "&birthday=" + birthday + "&passport=" + passport;
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findByAny(name, bday, passport, pageable), url);
		modelMap.addAttribute("page", page);
		return "/fragments/entities/person/personlist";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/db/person/delete",
			params = {"id"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean deletePerson(@RequestParam(value = "id") long id,
							  ModelMap modelMap) {
		return jpaPersonService.deletePersonService(jpaPersonService.findPersonByIdService(id));
	}
}