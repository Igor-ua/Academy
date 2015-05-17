package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.services.ServiceInterface.PersonService;

import java.text.ParseException;
import java.util.*;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

public class ConsolePerson {

	private Scanner scanner = new Scanner(System.in);
	private static final Logger log = Logger.getLogger(ConsolePerson.class);
	private PersonService personService;
	private StringDateFormatter sdf = new BasicStringDateFormatter();

	public ConsolePerson(PersonService personService) {
		this.personService = personService;
	}

	public void printAllPersons() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Person> persons = personService.findAllPersonsService();
		Iterator<Person> itr = persons.iterator();
		System.out.println(RS_PERSON_LIST_HEADER);
		while (itr.hasNext()) {
			Person person = itr.next();
			System.out.print(person.getId() + ",\t");
			System.out.print(person.getName() + ",\t");
			System.out.print(sdf.parseToString(person.getBirthday()) + ",\t");
			System.out.println(person.getPassport());
		}
	}

	public void findPersonById() {
		try {
			System.out.print(RS_PERSON_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			Person person = personService.findPersonByIdService(id);
			if (person.getId() != 0)
				System.out.println(person);
			else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findPersonByName() {
		System.out.print(RS_PERSON_NAME);
		String name = scanner.nextLine();
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Person> persons = personService.findPersonByNameService(name);
		Iterator<Person> itr = persons.iterator();
		System.out.println(RS_PERSON_LIST_HEADER);
		while (itr.hasNext()) {
			Person person = itr.next();
			System.out.print(person.getId() + ",\t");
			System.out.print(person.getName() + ",\t");
			System.out.print(sdf.parseToString(person.getBirthday()) + ",\t");
			System.out.println(person.getPassport());
		}
	}

	public void findPersonByBirthday() {
		System.out.print(RS_PERSON_BIRTHDAY);
		StringDateFormatter std = new BasicStringDateFormatter();
		try {
			Date birthday = std.parseToDate(scanner.nextLine());
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Person> persons = personService.findPersonByBirthdayService(birthday);
			Iterator<Person> itr = persons.iterator();
			System.out.println(RS_PERSON_LIST_HEADER);
			while (itr.hasNext()) {
				Person person = itr.next();
				System.out.print(person.getId() + ",\t");
				System.out.print(person.getName() + ",\t");
				System.out.print(sdf.parseToString(person.getBirthday()) + ",\t");
				System.out.println(person.getPassport());
			}
		} catch (ParseException e) {
			System.out.println(RS_OPERATION_ERROR);
			log.error(e);
		}
	}

	public void findPersonByPassport() {
		System.out.print(RS_PERSON_SELECT_PASSPORT);
		scanner.nextLine();
		String passport = scanner.nextLine();
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Person> persons = personService.findPersonByPassportService(passport);
		Iterator<Person> itr = persons.iterator();
		System.out.println(RS_PERSON_LIST_HEADER);
		while (itr.hasNext()) {
			Person person = itr.next();
			System.out.print(person.getId() + ",\t");
			System.out.print(person.getName() + ",\t");
			System.out.print(sdf.parseToString(person.getBirthday()) + ",\t");
			System.out.println(person.getPassport());
		}
	}

	public void deletePerson() {
		try {
			printAllPersons();
			System.out.print(RS_PERSON_DELETE_BY_ID);
			Person person = new Person();
			person.setId(scanner.nextLong());
			scanner.nextLine();
			if (personService.deletePersonService(person)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addPerson() {
		StringDateFormatter std = new BasicStringDateFormatter();
		System.out.print(RS_PERSON_NAME);
		String personName = scanner.nextLine();
		System.out.print(RS_PERSON_BIRTHDAY);
		String str = scanner.nextLine();
		try {
			Date personBirthday = std.parseToDate(str);
			System.out.print(RS_PERSON_SELECT_PASSPORT);
			String personPassport = scanner.nextLine();
			if (personService.savePersonService(new Person(personName, personBirthday, personPassport))) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (ParseException e) {
			System.out.println(RS_OPERATION_ERROR);
			log.error(e);
		}
	}

	public void updatePerson() {
		try {
			printAllPersons();
			System.out.print(RS_PERSON_UPDATE_BY_ID);
			long personId = scanner.nextLong();
			scanner.nextLine();
			StringDateFormatter std = new BasicStringDateFormatter();
			System.out.print(RS_PERSON_NAME);
			String personName = scanner.nextLine();
			System.out.print(RS_PERSON_BIRTHDAY);
			String str = scanner.nextLine();
			try {
				Date personBirthday = std.parseToDate(str);
				System.out.print(RS_PERSON_SELECT_PASSPORT);
				String personPassport = scanner.nextLine();
				Person person = new Person(personName, personBirthday, personPassport);
				person.setId(personId);
				if (personService.savePersonService(person)) {
					System.out.println(RS_OPERATION_SUCCESS);
				} else System.out.println(RS_OPERATION_ERROR);
			} catch (ParseException e) {
				System.out.println(RS_OPERATION_ERROR);
				log.error(e);
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}
}