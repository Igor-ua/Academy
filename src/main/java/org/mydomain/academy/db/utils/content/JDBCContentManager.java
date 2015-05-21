package org.mydomain.academy.db.utils.content;

import org.mydomain.academy.db.entities.*;
import org.mydomain.academy.db.utils.TableNames;
import org.mydomain.academy.services.ServiceInterface.*;

import java.util.*;

public class JDBCContentManager {

	private Map<String, RootService> rootServices;

	private List<Form> forms;
	private List<Group> groups;
	private List<Mark> marks;
	private List<MarkType> markTypes;
	private List<Person> persons;
	private List<Schedule> schedules;
	private List<Specialization> specializations;
	private List<Student> students;
	private List<Subject> subjects;
	private List<Teacher> teachers;

	private FormService formService;
	private GroupService groupService;
	private MarkService markService;
	private MarkTypeService markTypeService;
	private PersonService personService;
	private ScheduleService scheduleService;
	private SpecializationService specializationService;
	private StudentService studentService;
	private SubjectService subjectService;
	private TeacherService teacherService;

	public JDBCContentManager() {
		this.forms = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.marks = new ArrayList<>();
		this.markTypes = new ArrayList<>();
		this.persons = new ArrayList<>();
		this.schedules = new ArrayList<>();
		this.specializations = new ArrayList<>();
		this.students = new ArrayList<>();
		this.subjects = new ArrayList<>();
		this.teachers = new ArrayList<>();
	}

	public JDBCContentManager(Map<String, RootService> rootServices) {
		this.rootServices = rootServices;
		this.forms = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.marks = new ArrayList<>();
		this.markTypes = new ArrayList<>();
		this.persons = new ArrayList<>();
		this.schedules = new ArrayList<>();
		this.specializations = new ArrayList<>();
		this.students = new ArrayList<>();
		this.subjects = new ArrayList<>();
		this.teachers = new ArrayList<>();

		formService = (FormService) rootServices.get("formService");
		groupService = (GroupService) rootServices.get("groupService");
		markService = (MarkService) rootServices.get("markService");
		markTypeService = (MarkTypeService) rootServices.get("markTypeService");
		personService = (PersonService) rootServices.get("personService");
		scheduleService = (ScheduleService) rootServices.get("scheduleService");
		specializationService = (SpecializationService) rootServices.get("specializationService");
		studentService = (StudentService) rootServices.get("studentService");
		subjectService = (SubjectService) rootServices.get("subjectService");
		teacherService = (TeacherService) rootServices.get("teacherService");
	}

	public void fillDB() {
		try {
			addPersons();
			persons.forEach(personService::saveService);

			addTeachers();
			teachers.forEach(teacherService::saveService);

			addForms();
			forms.forEach(formService::saveService);

			addSpecializations();
			specializations.forEach(specializationService::saveService);

			addGroups();
			groups.forEach(groupService::saveService);

			addMarkTypes();
			markTypes.forEach(markTypeService::saveService);

			addStudents();
			students.forEach(studentService::saveService);

			addSubjects();
			subjects.forEach(subjectService::saveService);

			addSchedules();
			schedules.forEach(scheduleService::saveService);

			addMarks();
			marks.forEach(markService::saveService);
		} catch (ArrayIndexOutOfBoundsException ignored) {

		}
	}

	private void addForms() {
		forms.add(new Form("Full time"));
		forms.add(new Form("Part time"));
		forms.add(new Form("External studies"));
		forms.add(new Form("Distance learning"));
	}

	private void addGroups() {
		try {
			groups.add(new Group(
					"R101",
					formService.findFormByNameService("Full time").get(0),
					specializationService.findSpecializationByNameService("Programming").get(0)));
			groups.add(new Group(
					"R102",
					formService.findFormByNameService("Part time").get(0),
					specializationService.findSpecializationByNameService("Administration").get(0)));
			groups.add(new Group(
					"R103",
					formService.findFormByNameService("External studies").get(0),
					specializationService.findSpecializationByNameService("Design").get(0)));
			groups.add(new Group(
					"R104",
					formService.findFormByNameService("Distance learning").get(0),
					specializationService.findSpecializationByNameService("Anykey Specialist").get(0)));
		} catch (ArrayIndexOutOfBoundsException ignored) {

		}
	}

	private void addMarks() {
		try {
			marks.add(new Mark(
					markTypeService.findMarkTypeByNameService("one").get(0),
					teacherService.findAllTeachersService().get(0),
					studentService.findAllStudentsService().get(0),
					groupService.findGroupByNameService("R101").get(0),
					subjectService.findSubjectByNameService("Java").get(0),
					formService.findFormByNameService("Full time").get(0),
					new Date()
			));
			marks.add(new Mark(
					markTypeService.findMarkTypeByNameService("two").get(0),
					teacherService.findAllTeachersService().get(0),
					studentService.findAllStudentsService().get(0),
					groupService.findGroupByNameService("R102").get(0),
					subjectService.findSubjectByNameService("Csharp").get(0),
					formService.findFormByNameService("Part time").get(0),
					new Date()
			));
			marks.add(new Mark(
					markTypeService.findMarkTypeByNameService("three").get(0),
					teacherService.findAllTeachersService().get(0),
					studentService.findAllStudentsService().get(0),
					groupService.findGroupByNameService("R103").get(0),
					subjectService.findSubjectByNameService("SQL").get(0),
					formService.findFormByNameService("External studies").get(0),
					new Date()
			));
		} catch (ArrayIndexOutOfBoundsException ignored) {

		}
	}

	private void addMarkTypes() {
		markTypes.add(new MarkType("one"));
		markTypes.add(new MarkType("two"));
		markTypes.add(new MarkType("three"));
		markTypes.add(new MarkType("four"));
		markTypes.add(new MarkType("five"));
	}

	private void addPersons() {
		persons.add(new Person("Mike", new Date(), "FF001122"));
		persons.add(new Person("John", new Date(), "FF001133"));
		persons.add(new Person("Bob", new Date(), "FF001144"));
		persons.add(new Person("Jim", new Date(), "FF001155"));
		persons.add(new Person("James", new Date(), "FF001166"));
	}

	private void addSchedules() {
		try {
			schedules.add(new Schedule(
					subjectService.findSubjectByNameService("Java").get(0),
					teacherService.findAllTeachersService().get(0),
					groupService.findGroupByNameService("R101").get(0),
					TableNames.ScheduleColumns.DAY_ENUM.Monday.toString(),
					TableNames.ScheduleColumns.CHIS_ZNAM_ENUM.CH.toString(),
					1
			));
			schedules.add(new Schedule(
					subjectService.findSubjectByNameService("Cplusplus").get(0),
					teacherService.findAllTeachersService().get(0),
					groupService.findGroupByNameService("R101").get(0),
					TableNames.ScheduleColumns.DAY_ENUM.Monday.toString(),
					TableNames.ScheduleColumns.CHIS_ZNAM_ENUM.CH.toString(),
					2
			));
			schedules.add(new Schedule(
					subjectService.findSubjectByNameService("Csharp").get(0),
					teacherService.findAllTeachersService().get(0),
					groupService.findGroupByNameService("R101").get(0),
					TableNames.ScheduleColumns.DAY_ENUM.Monday.toString(),
					TableNames.ScheduleColumns.CHIS_ZNAM_ENUM.CH.toString(),
					3
			));
		} catch (ArrayIndexOutOfBoundsException ignored) {

		}
	}

	private void addSpecializations() {
		specializations.add(new Specialization("Programming"));
		specializations.add(new Specialization("Administration"));
		specializations.add(new Specialization("Design"));
		specializations.add(new Specialization("Anykey Specialist"));
	}

	private void addStudents() {
		try {
			students.add(new Student(
					personService.findPersonByPassportService("FF001122").get(0),
					groupService.findGroupByNameService("R101").get(0),
					new Date(), new Date()
			));
			students.add(new Student(
					personService.findPersonByPassportService("FF001133").get(0),
					groupService.findGroupByNameService("R102").get(0),
					new Date(), new Date()
			));
			students.add(new Student(
					personService.findPersonByPassportService("FF001144").get(0),
					groupService.findGroupByNameService("R103").get(0),
					new Date(), new Date()
			));
			students.add(new Student(
					personService.findPersonByPassportService("FF001155").get(0),
					groupService.findGroupByNameService("R104").get(0),
					new Date(), new Date()
			));
			students.add(new Student(
					personService.findPersonByPassportService("FF001166").get(0),
					groupService.findGroupByNameService("R101").get(0),
					new Date(), new Date()
			));
			students.add(new Student(
					personService.findPersonByPassportService("FF001133").get(0),
					groupService.findGroupByNameService("R101").get(0),
					new Date(), new Date()
			));
		} catch (ArrayIndexOutOfBoundsException ignored) {

		}
	}

	private void addSubjects() {
		try {
			subjects.add(new Subject(
					"Java",
					specializationService.findSpecializationByNameService("Programming").get(0)));
			subjects.add(new Subject(
					"Cplusplus",
					specializationService.findSpecializationByNameService("Programming").get(0)));
			subjects.add(new Subject(
					"Csharp",
					specializationService.findSpecializationByNameService("Programming").get(0)));
			subjects.add(new Subject(
					"HTML",
					specializationService.findSpecializationByNameService("Programming").get(0)));
			subjects.add(new Subject(
					"JavaScript",
					specializationService.findSpecializationByNameService("Programming").get(0)));
			subjects.add(new Subject(
					"SQL",
					specializationService.findSpecializationByNameService("Administration").get(0)));
			subjects.add(new Subject(
					"Linux",
					specializationService.findSpecializationByNameService("Administration").get(0)));
			subjects.add(new Subject(
					"Windows for dummies",
					specializationService.findSpecializationByNameService("Anykey Specialist").get(0)));
		} catch (ArrayIndexOutOfBoundsException ignored) {

		}
	}

	private void addTeachers() {
		try {
			teachers.add(
					new Teacher(personService.findPersonByPassportService("FF001122").get(0),
							new Date(), new Date())
			);
			teachers.add(
					new Teacher(personService.findPersonByPassportService("FF001133").get(0),
							new Date(), new Date())
			);
			teachers.add(
					new Teacher(personService.findPersonByPassportService("FF001144").get(0),
							new Date(), new Date())
			);
			teachers.add(
					new Teacher(personService.findPersonByPassportService("FF001155").get(0),
							new Date(), new Date())
			);
			teachers.add(
					new Teacher(personService.findPersonByPassportService("FF001166").get(0),
							new Date(), new Date())
			);
		} catch (ArrayIndexOutOfBoundsException ignored) {

		}
	}

}
