package org.mydomain.academy.UI.impl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.UI.UserInterface;
import org.mydomain.academy.UI.console.*;
import org.mydomain.academy.services.ServiceInterface.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.mydomain.academy.UI.console.messages.ConsoleMessages.*;
import static org.mydomain.academy.db.utils.LogMessages.*;

public class ConsoleUserInterface implements UserInterface {

	private static final Logger log = Logger.getLogger(ConsoleUserInterface.class);

	private Scanner scanner = new Scanner(System.in);

	private ConsoleForm consoleForm;
	private ConsoleGroup consoleGroup;
	private ConsoleMark consoleMark;
	private ConsoleMarkType consoleMarkType;
	private ConsolePerson consolePerson;
	private ConsoleSchedule consoleSchedule;
	private ConsoleSpecialization consoleSpecialization;
	private ConsoleStudent consoleStudent;
	private ConsoleSubject consoleSubject;
	private ConsoleTeacher consoleTeacher;

	public ConsoleUserInterface(FormService formService, GroupService groupService,
								MarkTypeService mark_typeService, MarkService markService,
								PersonService personService, ScheduleService scheduleService,
								SpecializationService specializationService, StudentService studentService,
								SubjectService subjectService, TeacherService teacherService) {

		this.consoleForm = new ConsoleForm(formService);
		this.consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
		this.consolePerson = new ConsolePerson(personService);
		this.consoleMark = new ConsoleMark(markService, mark_typeService, teacherService, studentService,
				groupService, formService, specializationService, subjectService, personService);
		this.consoleMarkType = new ConsoleMarkType(mark_typeService);
		this.consoleSchedule = new ConsoleSchedule(scheduleService, subjectService, teacherService,
				groupService, formService, specializationService, personService);
		this.consoleSpecialization = new ConsoleSpecialization(specializationService);
		this.consoleStudent = new ConsoleStudent(studentService, groupService, formService,
				specializationService, personService);
		this.consoleSubject = new ConsoleSubject(subjectService, specializationService);
		this.consoleTeacher = new ConsoleTeacher(teacherService, personService);

		log.info(LOG_CONSOLE_INIT);
	}

	@Override
	public void run() {
		System.out.println();
		log.info(LOG_CONSOLE_INTERFACE_INIT);
		System.out.println(MSG_GREETING);
		label_Main:
		try {
			while (true) {
				try {
					switchTitle();
					String usersChoice = scanner.nextLine();
					switch (usersChoice) {
						case "1":
							caseForm();
							break;
						case "2":
							caseGroup();
							break;
						case "3":
							caseMark();
							break;
						case "4":
							caseMarkType();
							break;
						case "5":
							casePerson();
							break;
						case "6":
							caseSchedule();
							break;
						case "7":
							caseSpecialization();
							break;
						case "8":
							caseStudent();
							break;
						case "9":
							caseSubject();
							break;
						case "10":
							caseTeacher();
							break;
						case "0":
							break label_Main;
						default:
							System.out.println(MSG_INPUT_ERROR);
							break;
					}
				} catch (NotImplementedException e) {
					System.err.println(((NotImplementedException) e).getLocalizedMessage());
					log.error(e);
				}
			}
		} catch (NoSuchElementException e) {
			System.err.println(MSG_ABNORMAL_EXIT);
		}
		log.info(LOG_CONSOLE_INTERFACE_FINISH);
	}


	@Override
	public void printErrorInfo() {
		System.err.println(MSG_CONNECTION_ERROR);
	}

	public void switchTitle() {
		System.out.println(MSG_SELECT_ACTION);
		System.out.println(MSG_CHOOSE_FORM);
		System.out.println(MSG_CHOOSE_GROUP);
		System.out.println(MSG_CHOOSE_MARK);
		System.out.println(MSG_CHOOSE_MARK_TYPE);
		System.out.println(MSG_CHOOSE_PERSON);
		System.out.println(MSG_CHOOSE_SCHEDULE);
		System.out.println(MSG_CHOOSE_SPECIALIZATION);
		System.out.println(MSG_CHOOSE_STUDENT);
		System.out.println(MSG_CHOOSE_SUBJECT);
		System.out.println(MSG_CHOOSE_TEACHER);
		System.out.println(MSG_SELECT_ACTION_EXIT);
	}

	public void caseForm() {
		String usersChoice;
		label_Form:
		while (true) {
			System.out.println(MSG_FORM_ACTION);
			System.out.println(MSG_FORM_SHOW_ALL_FORMS);
			System.out.println(MSG_FORM_FIND_FORM_BY_ID);
			System.out.println(MSG_FORM_FIND_FORM_BY_NAME);
			System.out.println(MSG_FORM_DELETE_FORM);
			System.out.println(MSG_FORM_ADD_NEW_FORM);
			System.out.println(MSG_FORM_UPDATE_FORM);
			System.out.println(MSG_GROUP_EXIT);

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consoleForm.printAllForms();
					break;
				case "2":
					consoleForm.findFormById();
					break;
				case "3":
					consoleForm.findFormByName();
					break;
				case "5":
					consoleForm.deleteForm();
					break;
				case "6":
					consoleForm.addForm();
					break;
				case "7":
					consoleForm.updateForm();
					break;
				case "0":
					break label_Form;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}

	public void caseGroup() {
		String usersChoice;
		label_Group:
		while (true) {
			System.out.println(MSG_GROUP_ACTION);
			System.out.println(MSG_GROUP_SHOW_ALL_GROUPS);
			System.out.println(MSG_GROUP_FIND_GROUP_BY_ID);
			System.out.println(MSG_GROUP_FIND_GROUP_BY_NAME);
			System.out.println(MSG_GROUP_FIND_GROUP_BY_FORM_ID);
			System.out.println(MSG_GROUP_FIND_GROUP_BY_SPEC_ID);
			System.out.println(MSG_GROUP_DELETE_GROUP);
			System.out.println(MSG_GROUP_ADD_NEW_GROUP);
			System.out.println(MSG_GROUP_UPDATE_GROUP);
			System.out.println(MSG_GROUP_EXIT);

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consoleGroup.printAllGroups();
					break;
				case "2":
					consoleGroup.findGroupById();
					break;
				case "3":
					consoleGroup.findGroupByName();
					break;
				case "4":
					consoleGroup.findGroupByFormId();
					break;
				case "5":
					consoleGroup.findGroupBySpecializationId();
					break;
				case "6":
					consoleGroup.deleteGroup();
					break;
				case "7":
					consoleGroup.addGroup();
					break;
				case "8":
					consoleGroup.updateGroup();
					break;
				case "0":
					break label_Group;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}

	public void caseMark() {
		String usersChoice;
		label_Mark:
		while (true) {
			System.out.println(MSG_MARK_ACTION);
			System.out.println(MSG_MARK_SHOW_ALL_MARKS);
			System.out.println(MSG_MARK_FIND_MARK_BY_ID);
			System.out.println(MSG_MARK_FIND_MARK_BY_MARKTYPE_ID);
			System.out.println(MSG_MARK_FIND_MARK_BY_TEACHER_ID);
			System.out.println(MSG_MARK_FIND_MARK_BY_STUDENT_ID);
			System.out.println(MSG_MARK_FIND_MARK_BY_GROUP_ID);
			System.out.println(MSG_MARK_FIND_MARK_BY_SUBJECT_ID);
			System.out.println(MSG_MARK_FIND_MARK_BY_FORM_ID);
			System.out.println(MSG_MARK_FIND_MARK_BY_DATE);
			System.out.println(MSG_MARK_DELETE_MARK);
			System.out.println(MSG_MARK_ADD_NEW_MARK);
			System.out.println(MSG_MARK_UPDATE_MARK);
			System.out.println(MSG_MARK_EXIT);

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consoleMark.printAllMarks();
					break;
				case "2":
					consoleMark.findMarkById();
					break;
				case "3":
					consoleMark.findMarkByMarkTypeId();
					break;
				case "4":
					consoleMark.findMarkByTeacherId();
					break;
				case "5":
					consoleMark.findMarkByStudentId();
					break;
				case "6":
					consoleMark.findMarkByGroupId();
					break;
				case "7":
					consoleMark.findMarkBySubjectId();
					break;
				case "8":
					consoleMark.findMarkByFormId();
					break;
				case "9":
					consoleMark.findMarkByDate();
					break;
				case "10":
					consoleMark.deleteMark();
					break;
				case "11":
					consoleMark.addMark();
					break;
				case "12":
					consoleMark.updateMark();
					break;
				case "0":
					break label_Mark;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}

	public void caseMarkType() {
		String usersChoice;
		label_MarkType:
		while (true) {
			System.out.println(MSG_MARKTYPE_ACTION);
			System.out.println(MSG_MARKTYPE_SHOW_ALL_MARKTYPES);
			System.out.println(MSG_MARKTYPE_FIND_MARKTYPES_BY_ID);
			System.out.println(MSG_MARKTYPE_FIND_MARKTYPES_BY_NAME);
			System.out.println(MSG_MARKTYPE_DELETE_MARKTYPE);
			System.out.println(MSG_MARKTYPE_ADD_NEW_MARKTYPE);
			System.out.println(MSG_MARKTYPE_UPDATE_MARKTYPE);
			System.out.println();

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consoleMarkType.printAllMarkTypes();
					break;
				case "2":
					consoleMarkType.findMarkTypeById();
					break;
				case "3":
					consoleMarkType.findMarkTypeByName();
					break;
				case "4":
					consoleMarkType.deleteMarkType();
					break;
				case "5":
					consoleMarkType.addMarkType();
					break;
				case "6":
					consoleMarkType.updateMarkType();
					break;
				case "0":
					break label_MarkType;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}

	public void casePerson() {
		String usersChoice;
		label_Person:
		while (true) {
			System.out.println(MSG_PERSON_ACTION);
			System.out.println(MSG_PERSON_SHOW_ALL_PERSONS);
			System.out.println(MSG_PERSON_FIND_PERSON_BY_ID);
			System.out.println(MSG_PERSON_FIND_PERSON_BY_NAME);
			System.out.println(MSG_PERSON_FIND_PERSON_BY_PASSPORT);
			System.out.println(MSG_PERSON_FIND_PERSON_BY_BIRTHDAY);
			System.out.println(MSG_PERSON_DELETE_PERSON);
			System.out.println(MSG_PERSON_ADD_NEW_PERSON);
			System.out.println(MSG_PERSON_UPDATE_PERSON);
			System.out.println(MSG_PERSON_EXIT);

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consolePerson.printAllPersons();
					break;
				case "2":
					consolePerson.findPersonById();
					break;
				case "3":
					consolePerson.findPersonByName();
					break;
				case "4":
					consolePerson.findPersonByPassport();
					break;
				case "5":
					consolePerson.findPersonByBirthday();
					break;
				case "6":
					consolePerson.deletePerson();
					break;
				case "7":
					consolePerson.addPerson();
					break;
				case "8":
					consolePerson.updatePerson();
					break;
				case "0":
					break label_Person;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}

	public void caseSchedule() {
		String usersChoice;
		label_Schedule:
		while (true) {
			System.out.println(MSG_SCHEDULE_ACTION);
			System.out.println(MSG_SCHEDULE_SHOW_ALL_SCHEDULES);
			System.out.println(MSG_SCHEDULE_FIND_SCHEDULE_BY_ID);
			System.out.println(MSG_SCHEDULE_FIND_SCHEDULE_BY_SUBJECT_ID);
			System.out.println(MSG_SCHEDULE_FIND_SCHEDULE_BY_TEACHER_ID);
			System.out.println(MSG_SCHEDULE_FIND_SCHEDULE_BY_GROUP_ID);
			System.out.println(MSG_SCHEDULE_FIND_SCHEDULE_BY_DAY);
			System.out.println(MSG_SCHEDULE_FIND_SCHEDULE_BY_CHIS_ZNAM);
			System.out.println(MSG_SCHEDULE_FIND_SCHEDULE_BY_LENTA);
			System.out.println(MSG_SCHEDULE_DELETE_SCHEDULE);
			System.out.println(MSG_SCHEDULE_ADD_NEW_SCHEDULE);
			System.out.println(MSG_SCHEDULE_UPDATE_SCHEDULE);
			System.out.println(MSG_SCHEDULE_EXIT);

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consoleSchedule.printAllSchedules();
					break;
				case "2":
					consoleSchedule.findScheduleById();
					break;
				case "3":
					consoleSchedule.findScheduleBySubjectId();
					break;
				case "4":
					consoleSchedule.findScheduleByTeacherId();
					break;
				case "5":
					consoleSchedule.findScheduleByGroupId();
					break;
				case "6":
					consoleSchedule.findScheduleByDayId();
					break;
				case "7":
					consoleSchedule.findScheduleByChisZnam();
					break;
				case "8":
					consoleSchedule.findScheduleByLenta();
					break;
				case "9":
					consoleSchedule.deleteSchedule();
					break;
				case "10":
					consoleSchedule.addSchedule();
					break;
				case "11":
					consoleSchedule.updateSchedule();
					break;
				case "0":
					break label_Schedule;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}

	public void caseSpecialization() {
		String usersChoice;
		label_Specialization:
		while (true) {
			System.out.println(MSG_SPECIALIZATION_ACTION);
			System.out.println(MSG_SPECIALIZATION_SHOW_ALL_SPECS);
			System.out.println(MSG_SPECIALIZATION_FIND_SPEC_BY_ID);
			System.out.println(MSG_SPECIALIZATION_FIND_SPEC_BY_NAME);
			System.out.println(MSG_SPECIALIZATION_DELETE_SPEC);
			System.out.println(MSG_SPECIALIZATION_ADD_NEW_SPEC);
			System.out.println(MSG_SPECIALIZATION_UPDATE_SPEC);
			System.out.println(MSG_SPECIALIZATION_EXIT);

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consoleSpecialization.printAllSpecializations();
					break;
				case "2":
					consoleSpecialization.findSpecializationById();
					break;
				case "3":
					consoleSpecialization.findSpecializationByName();
					break;
				case "4":
					consoleSpecialization.deleteSpecialization();
					break;
				case "5":
					consoleSpecialization.addSpecialization();
					break;
				case "6":
					consoleSpecialization.updateSpecialization();
					break;
				case "0":
					break label_Specialization;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}

	public void caseStudent() {
		String usersChoice;
		label_Student:
		while (true) {
			System.out.println(MSG_STUDENT_ACTION);
			System.out.println(MSG_STUDENT_SHOW_ALL_STUDENTS);
			System.out.println(MSG_STUDENT_FIND_STUDENT_BY_ID);
			System.out.println(MSG_STUDENT_FIND_STUDENT_BY_PERSON_ID);
			System.out.println(MSG_STUDENT_FIND_STUDENT_BY_GROUP_ID);
			System.out.println(MSG_STUDENT_FIND_STUDENT_BY_START);
			System.out.println(MSG_STUDENT_FIND_STUDENT_BY_FINISH);
			System.out.println(MSG_STUDENT_DELETE_STUDENT);
			System.out.println(MSG_STUDENT_ADD_NEW_STUDENT);
			System.out.println(MSG_STUDENT_UPDATE_STUDENT);
			System.out.println(MSG_STUDENT_EXIT);

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consoleStudent.printAllStudents();
					break;
				case "2":
					consoleStudent.findStudentById();
					break;
				case "3":
					consoleStudent.findStudentByPersonId();
					break;
				case "4":
					consoleStudent.findStudentByGroupId();
					break;
				case "5":
					consoleStudent.findStudentByStart();
					break;
				case "6":
					consoleStudent.findStudentByFinish();
					break;
				case "7":
					consoleStudent.deleteStudent();
					break;
				case "8":
					consoleStudent.addStudent();
					break;
				case "9":
					consoleStudent.updateStudent();
					break;
				case "0":
					break label_Student;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}

	public void caseSubject() {
		String usersChoice;
		label_Subject:
		while (true) {
			System.out.println(MSG_SUBJECT_ACTION);
			System.out.println(MSG_SUBJECT_SHOW_ALL_SUBJECTS);
			System.out.println(MSG_SUBJECT_FIND_SUBJECT_BY_ID);
			System.out.println(MSG_SUBJECT_FIND_SUBJECT_BY_SPEC);
			System.out.println(MSG_SUBJECT_FIND_SUBJECT_BY_NAME);
			System.out.println(MSG_SUBJECT_DELETE_SUBJECT);
			System.out.println(MSG_SUBJECT_ADD_NEW_SUBJECT);
			System.out.println(MSG_SUBJECT_UPDATE_SUBJECT);
			System.out.println(MSG_SUBJECT_EXIT);

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consoleSubject.printAllSubjects();
					break;
				case "2":
					consoleSubject.findSubjectById();
					break;
				case "3":
					consoleSubject.findSubjectBySpecializationId();
					break;
				case "4":
					consoleSubject.findSubjectByName();
					break;
				case "5":
					consoleSubject.deleteSubject();
					break;
				case "6":
					consoleSubject.addSubject();
					break;
				case "7":
					consoleSubject.updateSubject();
					break;
				case "0":
					break label_Subject;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}

	public void caseTeacher() {
		String usersChoice;
		label_Teacher:
		while (true) {
			System.out.println(MSG_TEACHER_ACTION);
			System.out.println(MSG_TEACHER_SHOW_ALL_TEACHERS);
			System.out.println(MSG_TEACHER_FIND_TEACHER_BY_ID);
			System.out.println(MSG_TEACHER_FIND_TEACHER_BY_PERSON_ID);
			System.out.println(MSG_TEACHER_FIND_TEACHER_BY_START);
			System.out.println(MSG_TEACHER_FIND_TEACHER_BY_FINISH);
			System.out.println(MSG_TEACHER_DELETE_TEACHER);
			System.out.println(MSG_TEACHER_ADD_NEW_TEACHER);
			System.out.println(MSG_TEACHER_UPDATE_TEACHER);
			System.out.println(MSG_TEACHER_EXIT);

			usersChoice = scanner.nextLine();
			switch (usersChoice) {
				case "1":
					consoleTeacher.printAllTeachers();
					break;
				case "2":
					consoleTeacher.findTeacherById();
					break;
				case "3":
					consoleTeacher.findTeacherByPersonId();
					break;
				case "4":
					consoleTeacher.findTeacherByStart();
					break;
				case "5":
					consoleTeacher.findTeacherByFinish();
					break;
				case "6":
					consoleTeacher.deleteTeacher();
					break;
				case "7":
					consoleTeacher.addTeacher();
					break;
				case "8":
					consoleTeacher.updateTeacher();
					break;
				case "0":
					break label_Teacher;
				default:
					System.out.println(MSG_INPUT_ERROR);
					break;
			}
		}
	}
}