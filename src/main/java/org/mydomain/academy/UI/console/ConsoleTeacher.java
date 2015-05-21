package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.Teacher;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.services.ServiceInterface.PersonService;
import org.mydomain.academy.services.ServiceInterface.TeacherService;

import java.text.ParseException;
import java.util.*;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

public class ConsoleTeacher {

	private Scanner scanner = new Scanner(System.in);
	private static final Logger log = Logger.getLogger(ConsoleTeacher.class);
	private TeacherService teacherService;
	private PersonService personService;
	private StringDateFormatter sdf = new BasicStringDateFormatter();

	public ConsoleTeacher(TeacherService teacherService, PersonService personService) {
		this.teacherService = teacherService;
		this.personService = personService;
	}

	public void printAllTeachers() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Teacher> teachers = teacherService.findAllTeachersService();
		Iterator<Teacher> itr = teachers.iterator();
		System.out.println(RS_TEACHER_LIST_HEADER);
		while (itr.hasNext()) {
			Teacher teacher = itr.next();
			System.out.print(teacher.getId() + ",\t");
			System.out.print(teacher.getPerson_id() + ",\t");
			System.out.print(sdf.parseToString(teacher.getStart()) + ",\t");
			System.out.println(sdf.parseToString(teacher.getFinish()));
		}
	}

	public void findTeacherById() {
		try {
			System.out.print(RS_TEACHER_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			Teacher teacher = teacherService.findTeacherByIdService(id);
			if (teacher.getId() != 0)
				System.out.println(teacher);
			else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findTeacherByPersonId() {
		try {
			ConsolePerson consolePerson = new ConsolePerson(personService);
			consolePerson.printAllPersons();
			System.out.print(RS_TEACHER_PERSON_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Teacher> teachers = teacherService.findTeacherByPersonIdService(id);
			Iterator<Teacher> itr = teachers.iterator();
			System.out.println(RS_TEACHER_LIST_HEADER);
			while (itr.hasNext()) {
				Teacher teacher = itr.next();
				System.out.print(teacher.getId() + ",\t");
				System.out.print(teacher.getPerson_id() + ",\t");
				System.out.print(sdf.parseToString(teacher.getStart()) + ",\t");
				System.out.println(sdf.parseToString(teacher.getFinish()));
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findTeacherByStart() {
		StringDateFormatter std = new BasicStringDateFormatter();
		System.out.print(RS_TEACHER_START);
		String str = scanner.nextLine();
		try {
			Date start = std.parseToDate(str);
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Teacher> teachers = teacherService.findTeacherByStartService(start);
			Iterator<Teacher> itr = teachers.iterator();
			System.out.println(RS_TEACHER_LIST_HEADER);
			while (itr.hasNext()) {
				Teacher teacher = itr.next();
				System.out.print(teacher.getId() + ",\t");
				System.out.print(teacher.getPerson_id() + ",\t");
				System.out.print(sdf.parseToString(teacher.getStart()) + ",\t");
				System.out.println(sdf.parseToString(teacher.getFinish()));
			}
		} catch (ParseException e) {
			System.out.println(RS_OPERATION_ERROR);
			log.error(e);
		}
	}

	public void findTeacherByFinish() {
		StringDateFormatter std = new BasicStringDateFormatter();
		System.out.print(RS_TEACHER_FINISH);
		String str = scanner.nextLine();
		try {
			Date finish = std.parseToDate(str);
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Teacher> teachers = teacherService.findTeacherByFinishService(finish);
			Iterator<Teacher> itr = teachers.iterator();
			System.out.println(RS_TEACHER_LIST_HEADER);
			while (itr.hasNext()) {
				Teacher teacher = itr.next();
				System.out.print(teacher.getId() + ",\t");
				System.out.print(teacher.getPerson_id() + ",\t");
				System.out.print(sdf.parseToString(teacher.getStart()) + ",\t");
				System.out.println(sdf.parseToString(teacher.getFinish()));
			}
		} catch (ParseException e) {
			System.out.println(RS_OPERATION_ERROR);
			log.error(e);
		}
	}

	public void deleteTeacher() {
		try {
			printAllTeachers();
			System.out.print(RS_TEACHER_DELETE_BY_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			Teacher teacher = new Teacher();
			teacher.setId(id);
			if (teacherService.deleteService(teacher)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addTeacher() {
		try {
			ConsolePerson consolePerson = new ConsolePerson(personService);
			consolePerson.printAllPersons();
			System.out.print(RS_TEACHER_PERSON_ID);
			long personId = scanner.nextLong();
			scanner.nextLine();

			StringDateFormatter std = new BasicStringDateFormatter();
			System.out.print(RS_TEACHER_START);
			String str = scanner.nextLine();
			try {
				Date start = std.parseToDate(str);
				System.out.print(RS_TEACHER_FINISH);
				str = scanner.nextLine();
				Date finish = std.parseToDate(str);

				Teacher teacher = new Teacher(personId, start, finish);
				if (teacherService.saveService(teacher)) {
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

	public void updateTeacher() {
		try {
			printAllTeachers();
			System.out.print(RS_TEACHER_UPDATE_BY_ID);
			long teacherId = scanner.nextLong();
			scanner.nextLine();

			ConsolePerson consolePerson = new ConsolePerson(personService);
			consolePerson.printAllPersons();
			System.out.print(RS_TEACHER_PERSON_ID);
			long personId = scanner.nextLong();
			scanner.nextLine();

			StringDateFormatter std = new BasicStringDateFormatter();
			System.out.print(RS_TEACHER_START);
			String str = scanner.nextLine();
			try {
				Date start = std.parseToDate(str);
				System.out.print(RS_TEACHER_FINISH);
				str = scanner.nextLine();
				Date finish = std.parseToDate(str);

				Teacher teacher = new Teacher(personId, start, finish);
				teacher.setId(teacherId);
				if (teacherService.saveService(teacher)) {
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
