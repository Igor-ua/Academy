package org.mydomain.academy.SpringBoot.controllers.REST;

import com.fasterxml.jackson.annotation.JsonView;
import org.mydomain.academy.SpringBoot.view.View;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/person")
public class PersonRESTController {

	@Autowired
	private JPAPersonService jpaPersonService;

	@JsonView(View.Summary.class)
	@RequestMapping(method = RequestMethod.GET)
	public List<Person> getAllPersons() {
		return jpaPersonService.findAllPersonsService();
	}

	@JsonView(View.Summary.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Person getPerson(@PathVariable Long id) {
		return jpaPersonService.findPersonByIdService(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public boolean savePerson(@RequestBody Person person) {
		return jpaPersonService.saveService(person);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deletePerson(@PathVariable Long id) {
		return jpaPersonService.deleteService(jpaPersonService.findPersonByIdService(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public boolean updatePerson(@RequestBody Person person, @PathVariable Long id) {
		Person update = jpaPersonService.findPersonByIdService(id);
		update.setName(person.getName());
		update.setPassport(person.getPassport());
		update.setBirthday(person.getBirthday());
		return jpaPersonService.saveService(update);
	}

}
