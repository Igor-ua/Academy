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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/db/mark")
public class MarkController {

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

	@RequestMapping(value = {"", "/"})
	public String person(ModelMap modelMap) {
		return "/fragments/entities/person/person";
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
					jpaPersonService.saveService(new Person("Jack", new Date(), "FF223344"));
				} else {
					jpaPersonService.saveService(new Person("Mike", new Date(), "AB000111"));
				}
			}
			return true;
		}
		return false;
	}

	@RequestMapping(
			value = "/find",
			method = RequestMethod.GET)
	public String findPersonById(ModelMap modelMap) {
		return "/fragments/entities/person/find_person";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String findOne(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("person", jpaPersonService.findPersonByIdService(id));
		return "/fragments/entities/person/personlist";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String savePersonMapping(@RequestParam(value = "id") String id,
									ModelMap modelMap) {
		modelMap.addAttribute("id", id);
		return "/fragments/entities/person/save_person";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "name", "birthday", "passport"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean savePerson(@RequestParam(value = "id", required = false, defaultValue = "") String id,
							  @RequestParam(value = "name") String name,
							  @RequestParam(value = "birthday") String birthday,
							  @RequestParam(value = "passport") String passport,
							  ModelMap modelMap) {
		Person person = new Person();
		if (!id.equals("")) {
			person.setId(Long.parseLong(id));
		}
		person.setName(name);
		person.setPassport(passport);
		try {
			person.setBirthday(sdf.parseToDate(birthday));
		} catch (ParseException e) {
			System.err.println("Parse error");
		}
		return jpaPersonService.saveService(person);
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAll(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findAllPersonsService(pageable), "/db/person/show_all");
		modelMap.addAttribute("page", page);
		return "/fragments/entities/person/personlist";
	}

	@RequestMapping(
			value = "/find",
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
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deletePerson(@RequestParam(value = "id") long id,
							   ModelMap modelMap, Pageable pageable) {
		jpaPersonService.deleteService(jpaPersonService.findPersonByIdService(id));
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findAllPersonsService(pageable), "/db/person/delete");
		modelMap.addAttribute("page", page);
		return "/fragments/entities/person/delete_person";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deletePerson(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findAllPersonsService(pageable), "/db/person/delete");
		modelMap.addAttribute("page", page);
		return "/fragments/entities/person/delete_person";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updatePerson(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Person> page = new PageWrapper<>(
				jpaPersonService.findAllPersonsService(pageable), "/db/person/update");
		modelMap.addAttribute("page", page);
		return "/fragments/entities/person/update_person";
	}
}