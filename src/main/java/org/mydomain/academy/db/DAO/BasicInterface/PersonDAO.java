package org.mydomain.academy.db.DAO.BasicInterface;

import org.mydomain.academy.db.entities.Person;

import java.util.Date;
import java.util.List;

public interface PersonDAO {

	int FLAG_ID = 1;                        // 0001
	int FLAG_NAME = FLAG_ID << 1;           // 0010
	int FLAG_BIRTHDAY = FLAG_NAME << 1;     // 0100
	int FLAG_PASSPORT = FLAG_BIRTHDAY << 1; // 1000

	Person findPersonById(long id);

	List<Person> findAllPersons();

	List<Person> findPersonByName(String name);

	List<Person> findPersonByPassport(String passport);

	List<Person> findPersonByBirthday(Date birthday);

	boolean savePerson(Person person);

	boolean deletePerson(Person person);

	List<Person> findByCriteria(int flags, Object... values);
}
//todo javadoc