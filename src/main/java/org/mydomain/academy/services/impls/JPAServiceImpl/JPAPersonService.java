package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPAPersonDAO;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.services.ServiceInterface.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPAPersonService implements PersonService {

	private static final Logger log = Logger.getLogger(JPAPersonService.class);

	private JPAPersonDAO jpaPersonDAO;

	@Autowired
	public void setJpaPersonDAO(JPAPersonDAO jpaPersonDAO) {
		this.jpaPersonDAO = jpaPersonDAO;
	}

	public JPAPersonService() {
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
		if (!person.getPassport().matches("[A-ZР-пр-џ0-9]{8,8}")) {
			log.error("Person validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Person> findAllPersonsService() {
		return jpaPersonDAO.findAll();
	}

	public Page<Person> findAllPersonsService(Pageable pageable) {
		return jpaPersonDAO.findAll(pageable);
	}

	@Override
	public Person findPersonByIdService(long id) {
		return jpaPersonDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<Person> findPersonByNameService(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Person> findPersonByPassportService(String passport) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Person> findPersonByBirthdayService(Date birthday) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteService(Object object) {
		if (object instanceof Person) {
			try {
				jpaPersonDAO.delete((Person) object);
				return true;
			} catch (RuntimeException e) {
				log.error(e);
			}
		}
		return false;
	}

	@Override
	public boolean saveService(Object object) {
		if (object instanceof Person) {
			try {
				if (validatePerson((Person) object)) {
					jpaPersonDAO.save((Person) object);
					return true;
				}
			} catch (RuntimeException e) {
				log.error(e);
			}
		}
		return false;
	}

	@Override
	@Deprecated
	public List<Person> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public Page<Person> findByAny(String name, Date birthday, String passport, Pageable pageable) {
		return jpaPersonDAO.findByNameOrBirthdayOrPassport(name, birthday, passport, pageable);
	}
}
