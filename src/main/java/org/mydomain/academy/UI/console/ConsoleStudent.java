package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.Student;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.services.ServiceInterface.*;

import java.text.ParseException;
import java.util.*;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

public class ConsoleStudent {

	private Scanner scanner = new Scanner(System.in);
	private static final Logger log = Logger.getLogger(ConsoleStudent.class);
	private StudentService studentService;
	private GroupService groupService;
	private FormService formService;
	private SpecializationService specializationService;
	private PersonService personService;
	private StringDateFormatter sdf = new BasicStringDateFormatter();

	public ConsoleStudent(StudentService studentService, GroupService groupService, FormService formService,
						  SpecializationService specializationService, PersonService personService) {
		this.studentService = studentService;
		this.groupService = groupService;
		this.formService = formService;
		this.specializationService = specializationService;
		this.personService = personService;
	}

	public void printAllStudents() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Student> students = studentService.findAllStudentsService();
		Iterator<Student> itr = students.iterator();
		System.out.println(RS_STUDENT_LIST_HEADER);
		while (itr.hasNext()) {
			Student student = itr.next();
			System.out.print(student.getId() + ",\t");
			System.out.print(student.getPerson_id() + ",\t");
			System.out.print(student.getGroup_id() + ",\t");
			System.out.print(sdf.parseToString(student.getStart()) + ",\t");
			System.out.println(sdf.parseToString(student.getFinish()));
		}
	}

	public void findStudentById() {
		try {
			System.out.print(RS_STUDENT_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			Student student = studentService.findStudentByIdService(id);
			if (student.getId() != 0)
				System.out.println(student);
			else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findStudentByPersonId() {
		try {
			ConsolePerson consolePerson = new ConsolePerson(personService);
			consolePerson.printAllPersons();
			System.out.print(RS_STUDENT_PERSON);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Student> students = studentService.findStudentByPersonIdService(id);
			Iterator<Student> itr = students.iterator();
			System.out.println(RS_STUDENT_LIST_HEADER);
			while (itr.hasNext()) {
				Student student = itr.next();
				System.out.print(student.getId() + ",\t");
				System.out.print(student.getPerson_id() + ",\t");
				System.out.print(student.getGroup_id() + ",\t");
				System.out.print(sdf.parseToString(student.getStart()) + ",\t");
				System.out.println(sdf.parseToString(student.getFinish()));
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findStudentByGroupId() {
		try {
			ConsoleGroup consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
			consoleGroup.printAllGroups();
			System.out.print(RS_STUDENT_GROUP);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Student> students = studentService.findStudentByGroupIdService(id);
			Iterator<Student> itr = students.iterator();
			System.out.println(RS_STUDENT_LIST_HEADER);
			while (itr.hasNext()) {
				Student student = itr.next();
				System.out.print(student.getId() + ",\t");
				System.out.print(student.getPerson_id() + ",\t");
				System.out.print(student.getGroup_id() + ",\t");
				System.out.print(sdf.parseToString(student.getStart()) + ",\t");
				System.out.println(sdf.parseToString(student.getFinish()));
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findStudentByStart() {
		StringDateFormatter std = new BasicStringDateFormatter();
		System.out.print(RS_STUDENT_START);
		String str = scanner.nextLine();
		try {
			Date start = std.parseToDate(str);
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Student> students = studentService.findStudentByStartService(start);
			Iterator<Student> itr = students.iterator();
			System.out.println(RS_STUDENT_LIST_HEADER);
			while (itr.hasNext()) {
				Student student = itr.next();
				System.out.print(student.getId() + ",\t");
				System.out.print(student.getPerson_id() + ",\t");
				System.out.print(student.getGroup_id() + ",\t");
				System.out.print(sdf.parseToString(student.getStart()) + ",\t");
				System.out.println(sdf.parseToString(student.getFinish()));
			}
		} catch (ParseException e) {
			System.out.println(RS_OPERATION_ERROR);
			log.error(e);
		}
	}

	public void findStudentByFinish() {
		StringDateFormatter std = new BasicStringDateFormatter();
		System.out.print(RS_STUDENT_FINISH);
		String str = scanner.nextLine();
		try {
			Date finish = std.parseToDate(str);
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Student> students = studentService.findStudentByFinishService(finish);
			Iterator<Student> itr = students.iterator();
			System.out.println(RS_STUDENT_LIST_HEADER);
			while (itr.hasNext()) {
				Student student = itr.next();
				System.out.print(student.getId() + ",\t");
				System.out.print(student.getPerson_id() + ",\t");
				System.out.print(student.getGroup_id() + ",\t");
				System.out.print(sdf.parseToString(student.getStart()) + ",\t");
				System.out.println(sdf.parseToString(student.getFinish()));
			}
		} catch (ParseException e) {
			System.out.println(RS_OPERATION_ERROR);
			log.error(e);
		}
	}

	public void deleteStudent() {
		try {
			printAllStudents();
			System.out.print(RS_STUDENT_DELETE_BY_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			Student student = new Student();
			student.setId(id);

			if (studentService.deleteService(student)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addStudent() {
		try {
			ConsolePerson consolePerson = new ConsolePerson(personService);
			consolePerson.printAllPersons();
			System.out.print(RS_STUDENT_PERSON);
			long personId = scanner.nextLong();
			scanner.nextLine();

			ConsoleGroup consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
			consoleGroup.printAllGroups();
			System.out.print(RS_STUDENT_GROUP);
			long groupId = scanner.nextLong();
			scanner.nextLine();

			StringDateFormatter std = new BasicStringDateFormatter();
			System.out.print(RS_STUDENT_START);
			String str = scanner.nextLine();
			try {
				Date start = std.parseToDate(str);
				System.out.print(RS_STUDENT_FINISH);
				str = scanner.nextLine();
				Date finish = std.parseToDate(str);

				Student student = new Student(personId, groupId, start, finish);
				if (studentService.saveService(student)) {
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

	public void updateStudent() {
		try {
			printAllStudents();
			System.out.print(RS_STUDENT_UPDATE_BY_ID);
			long studentId = scanner.nextLong();
			scanner.nextLine();

			ConsolePerson consolePerson = new ConsolePerson(personService);
			consolePerson.printAllPersons();
			System.out.print(RS_STUDENT_PERSON);
			long personId = scanner.nextLong();
			scanner.nextLine();

			ConsoleGroup consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
			consoleGroup.printAllGroups();
			System.out.print(RS_STUDENT_GROUP);
			long groupId = scanner.nextLong();
			scanner.nextLine();

			StringDateFormatter std = new BasicStringDateFormatter();
			System.out.print(RS_STUDENT_START);
			scanner.nextLine();
			String str = scanner.nextLine();
			try {
				Date start = std.parseToDate(str);
				System.out.print(RS_STUDENT_FINISH);
				str = scanner.nextLine();
				Date finish = std.parseToDate(str);

				Student student = new Student(personId, groupId, start, finish);
				student.setId(studentId);
				if (studentService.saveService(student)) {
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
