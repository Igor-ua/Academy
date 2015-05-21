package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.PersonDAO;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.services.ServiceInterface.PersonService;

import java.util.Date;
import java.util.List;

public class JDBCPersonService implements PersonService {

	private static final Logger log = Logger.getLogger(JDBCPersonService.class);

	private PersonDAO personDAO;

	public JDBCPersonService(PersonDAO personDAO) {
		this.personDAO = personDAO;
		log.info("PersonService has been initialized.");
	}

	private boolean validatePerson(Person person) {
		if (!person.getName().matches("[ A-Za-zР-пр-џ\\-]{0,}")) {
			log.error("Person validation error.");
			return false;
		}
		if (person.getBirthday().after(new Date())) {
			log.error("Person validation error.");
			return false;
		}
		if (!person.getPassport().matches("[A-ZР-пр-џ0-9]{0,8}")) {
			log.error("Person validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Person> findAllPersonsService() {
		return personDAO.findAllPersons();
	}

	@Override
	public Person findPersonByIdService(long id) {
		return personDAO.findPersonById(id);
	}

	@Override
	public List<Person> findPersonByNameService(String name) {
		return personDAO.findPersonByName(name);
	}

	@Override
	public List<Person> findPersonByPassportService(String passport) {
		return personDAO.findPersonByPassport(passport);
	}

	@Override
	public List<Person> findPersonByBirthdayService(Date birthday) {
		return personDAO.findPersonByBirthday(birthday);
	}

	@Override
	public boolean deleteService(Object object) {
		return object instanceof Person && personDAO.deletePerson((Person) object);
	}

	@Override
	public boolean saveService(Object object) {
		return object instanceof Person && validatePerson((Person) object) && personDAO.savePerson((Person) object);
	}

	@Override
	public List<Person> findByCriteriaService(int flags, Object... values) {
		return personDAO.findByCriteria(flags, values);
	}
}