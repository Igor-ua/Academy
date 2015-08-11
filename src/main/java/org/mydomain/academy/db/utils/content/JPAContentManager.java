package org.mydomain.academy.db.utils.content;

import org.mydomain.academy.db.entities.*;
import org.mydomain.academy.db.utils.TableNames;
import org.mydomain.academy.services.impls.JPAServiceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JPAContentManager {

	@Autowired
	private JPAFormService formService;
	@Autowired
	private JPAGroupService groupService;
	@Autowired
	private JPAMarkService markService;
	@Autowired
	private JPAMarkTypeService markTypeService;
	@Autowired
	private JPAPersonService personService;
	@Autowired
	private JPAScheduleService scheduleService;
	@Autowired
	private JPASpecializationService specializationService;
	@Autowired
	private JPAStudentService studentService;
	@Autowired
	private JPASubjectService subjectService;
	@Autowired
	private JPATeacherService teacherService;

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

	public JPAContentManager() {
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

	//	@PostConstruct
	public void addContent() {
		if (personService.findAllPersonsService().isEmpty()) {

			addPersons();
			for (Person person : persons) {
				personService.saveService(person);
			}

			addTeachers();
			for (Teacher teacher : teachers) {
				teacherService.saveService(teacher);
			}

			addForms();
			for (Form form : forms) {
				formService.saveService(form);
			}

			addSpecializations();
			for (Specialization specialization : specializations) {
				specializationService.saveService(specialization);
			}

			addGroups();
			for (Group group : groups) {
				groupService.saveService(group);
			}

			addMarkTypes();
			for (MarkType markType : markTypes) {
				markTypeService.saveService(markType);
			}

			addStudents();
			for (Student student : students) {
				studentService.saveService(student);
			}

			addSubjects();
			for (Subject subject : subjects) {
				subjectService.saveService(subject);
			}

			addSchedules();
			for (Schedule schedule : schedules) {
				scheduleService.saveService(schedule);
			}

			addMarks();
			for (Mark mark : marks) {
				personService.saveService(mark);
			}

		}
	}

	private void addForms() {
		forms.add(new Form("Full time"));
		forms.add(new Form("Part time"));
		forms.add(new Form("External studies"));
		forms.add(new Form("Distance learning"));
	}

	private void addGroups() {
		groups.add(new Group(
				"R101",
				formService.findByAny("Full time", null).getContent().get(0),
				specializationService.findByAny("Programming", null).getContent().get(0)));
		groups.add(new Group(
				"R102",

				formService.findByAny("Part time", null).getContent().get(0),
				specializationService.findByAny("Administration", null).getContent().get(0)));
		groups.add(new Group(
				"R103",
				formService.findByAny("External studies", null).getContent().get(0),
				specializationService.findByAny("Design", null).getContent().get(0)));
		groups.add(new Group(
				"R104",
				formService.findByAny("Distance learning", null).getContent().get(0),
				specializationService.findByAny("Anykey Specialist", null).getContent().get(0)));
	}

	private void addMarks() {
		marks.add(new Mark(
				markTypeService.findByAny("one", null).getContent().get(0),
				teacherService.findAllTeachersService().get(0),
				studentService.findAllStudentsService().get(0),
				groupService.findByAny("R101", null, null, null).getContent().get(0),
				subjectService.findByAny("Java", null, null).getContent().get(0),
				formService.findByAny("Full time", null).getContent().get(0),
				new Date()
		));
		marks.add(new Mark(
				markTypeService.findByAny("two", null).getContent().get(0),
				teacherService.findAllTeachersService().get(0),
				studentService.findAllStudentsService().get(0),
				groupService.findByAny("R102", null, null, null).getContent().get(0),
				subjectService.findByAny("Csharp", null, null).getContent().get(0),
				formService.findByAny("Part time", null).getContent().get(0),
				new Date()
		));
		marks.add(new Mark(
				markTypeService.findByAny("three", null).getContent().get(0),
				teacherService.findAllTeachersService().get(0),
				studentService.findAllStudentsService().get(0),
				groupService.findByAny("R103", null, null, null).getContent().get(0),
				subjectService.findByAny("SQL", null, null).getContent().get(0),
				formService.findByAny("External studies", null).getContent().get(0),
				new Date()
		));
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
		schedules.add(new Schedule(
				subjectService.findByAny("Java", null, null).getContent().get(0),
				teacherService.findAllTeachersService().get(0),
				groupService.findByAny("R101", null, null, null).getContent().get(0),
				TableNames.ScheduleColumns.DAY_ENUM.Monday.toString(),
				TableNames.ScheduleColumns.CHIS_ZNAM_ENUM.CH.toString(),
				1
		));
		schedules.add(new Schedule(
				subjectService.findByAny("Cplusplus", null, null).getContent().get(0),
				teacherService.findAllTeachersService().get(0),
				groupService.findByAny("R101", null, null, null).getContent().get(0),
				TableNames.ScheduleColumns.DAY_ENUM.Monday.toString(),
				TableNames.ScheduleColumns.CHIS_ZNAM_ENUM.CH.toString(),
				2
		));
		schedules.add(new Schedule(
				subjectService.findByAny("Csharp", null, null).getContent().get(0),
				teacherService.findAllTeachersService().get(0),
				groupService.findByAny("R101", null, null, null).getContent().get(0),
				TableNames.ScheduleColumns.DAY_ENUM.Monday.toString(),
				TableNames.ScheduleColumns.CHIS_ZNAM_ENUM.CH.toString(),
				3
		));
	}

	private void addSpecializations() {
		specializations.add(new Specialization("Programming"));
		specializations.add(new Specialization("Administration"));
		specializations.add(new Specialization("Design"));
		specializations.add(new Specialization("Anykey Specialist"));
	}

	private void addStudents() {
		students.add(new Student(
				personService.findByAny(null, null, "FF001122", null).getContent().get(0),
				groupService.findByAny("R101", null, null, null).getContent().get(0),
				new Date(), new Date()
		));
		students.add(new Student(
				personService.findByAny(null, null, "FF001133", null).getContent().get(0),
				groupService.findByAny("R102", null, null, null).getContent().get(0),
				new Date(), new Date()
		));
		students.add(new Student(
				personService.findByAny(null, null, "FF001144", null).getContent().get(0),
				groupService.findByAny("R103", null, null, null).getContent().get(0),
				new Date(), new Date()
		));
		students.add(new Student(
				personService.findByAny(null, null, "FF001155", null).getContent().get(0),
				groupService.findByAny("R104", null, null, null).getContent().get(0),
				new Date(), new Date()
		));
		students.add(new Student(
				personService.findByAny(null, null, "FF001166", null).getContent().get(0),
				groupService.findByAny("R101", null, null, null).getContent().get(0),
				new Date(), new Date()
		));
		students.add(new Student(
				personService.findByAny(null, null, "FF001133", null).getContent().get(0),
				groupService.findByAny("R101", null, null, null).getContent().get(0),
				new Date(), new Date()
		));
	}

	private void addSubjects() {
		subjects.add(new Subject(
				"Java",
				specializationService.findByAny("Programming", null).getContent().get(0)));
		subjects.add(new Subject(
				"Cplusplus",
				specializationService.findByAny("Programming", null).getContent().get(0)));
		subjects.add(new Subject(
				"Csharp",
				specializationService.findByAny("Programming", null).getContent().get(0)));
		subjects.add(new Subject(
				"HTML",
				specializationService.findByAny("Programming", null).getContent().get(0)));
		subjects.add(new Subject(
				"JavaScript",
				specializationService.findByAny("Programming", null).getContent().get(0)));
		subjects.add(new Subject(
				"SQL",
				specializationService.findByAny("Administration", null).getContent().get(0)));
		subjects.add(new Subject(
				"Linux",
				specializationService.findByAny("Administration", null).getContent().get(0)));
		subjects.add(new Subject(
				"Windows for dummies",
				specializationService.findByAny("Anykey Specialist", null).getContent().get(0)));
	}

	private void addTeachers() {
		teachers.add(
				new Teacher(personService.findByAny(null, null, "FF001122", null).getContent().get(0),
						new Date(), new Date())
		);
		teachers.add(
				new Teacher(personService.findByAny(null, null, "FF001133", null).getContent().get(0),
						new Date(), new Date())
		);
		teachers.add(
				new Teacher(personService.findByAny(null, null, "FF001144", null).getContent().get(0),
						new Date(), new Date())
		);
		teachers.add(
				new Teacher(personService.findByAny(null, null, "FF001155", null).getContent().get(0),
						new Date(), new Date())
		);
		teachers.add(
				new Teacher(personService.findByAny(null, null, "FF001166", null).getContent().get(0),
						new Date(), new Date())
		);
	}
}
