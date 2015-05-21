package org.mydomain.academy.services.ServiceInterface;


import org.mydomain.academy.db.entities.Person;

import java.util.Date;
import java.util.List;

public interface PersonService extends RootService {

	List<Person> findAllPersonsService();

	Person findPersonByIdService(long id);

	List<Person> findPersonByNameService(String name);

	List<Person> findPersonByPassportService(String passport);

	List<Person> findPersonByBirthdayService(Date birthday);

	List<Person> findByCriteriaService(int flags, Object... values);
}
