package org.mydomain.academy.services.ServiceInterface;


import org.mydomain.academy.db.entities.Person;

import java.util.Date;
import java.util.List;

public interface PersonService {

	List<Person> findAllPersonsService();

	Person findPersonByIdService(long id);

	List<Person> findPersonByNameService(String name);

	List<Person> findPersonByPassportService(String passport);

	List<Person> findPersonByBirthdayService(Date birthday);

	boolean deletePersonService(Person person);

	boolean savePersonService(Person person);

	List<Person> findByCriteriaService(int flags, Object... values);
}
