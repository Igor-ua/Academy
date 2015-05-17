package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.Mark;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.services.ServiceInterface.*;

import java.text.ParseException;
import java.util.*;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

public class ConsoleMark {

	private Scanner scanner = new Scanner(System.in);

	private static final Logger log = Logger.getLogger(ConsoleMark.class);

	private MarkService markService;
	private MarkTypeService markTypeService;
	private TeacherService teacherService;
	private StudentService studentService;
	private GroupService groupService;
	private FormService formService;
	private SpecializationService specializationService;
	private SubjectService subjectService;
	private PersonService personService;

	public ConsoleMark(MarkService markService, MarkTypeService markTypeService,
					   TeacherService teacherService, StudentService studentService, GroupService groupService,
					   FormService formService, SpecializationService specializationService,
					   SubjectService subjectService, PersonService personService) {
		this.groupService = groupService;
		this.markTypeService = markTypeService;
		this.markService = markService;
		this.teacherService = teacherService;
		this.studentService = studentService;
		this.groupService = groupService;
		this.specializationService = specializationService;
		this.subjectService = subjectService;
		this.formService = formService;
		this.personService = personService;
	}

	public void printAllMarks() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Mark> marks = markService.findAllMarksService();
		Iterator<Mark> itr = marks.iterator();
		System.out.println(RS_MARK_LIST_HEADER);
		while (itr.hasNext()) {
			Mark mark = itr.next();
			System.out.print(mark.getId() + ",\t");
			System.out.print(mark.getMarkTypeId() + ",\t");
			System.out.print(mark.getTeacher_id() + ",\t");
			System.out.print(mark.getStudent_id() + ",\t");
			System.out.print(mark.getGroup_id() + ",\t");
			System.out.print(mark.getSubject_id() + ",\t");
			System.out.print(mark.getForm_id() + ",\t");
			System.out.println(mark.getDate());
		}
	}

	public void findMarkById() {
		try {
			System.out.print(RS_MARK_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			Mark mark = markService.findMarkByIdService(id);
			if (mark.getId() != 0)
				System.out.println(mark);
			else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findMarkByMarkTypeId() {
		try {
			ConsoleMarkType consoleMarkType = new ConsoleMarkType(markTypeService);
			consoleMarkType.printAllMarkTypes();
			System.out.print(RS_MARK_MARKTYPE_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Mark> marks = markService.findMarkByMarkTypeIdService(id);
			Iterator<Mark> itr = marks.iterator();
			System.out.println(RS_MARK_LIST_HEADER);
			while (itr.hasNext()) {
				Mark mark = itr.next();
				System.out.print(mark.getId() + ",\t");
				System.out.print(mark.getMarkTypeId() + ",\t");
				System.out.print(mark.getTeacher_id() + ",\t");
				System.out.print(mark.getStudent_id() + ",\t");
				System.out.print(mark.getGroup_id() + ",\t");
				System.out.print(mark.getSubject_id() + ",\t");
				System.out.print(mark.getForm_id() + ",\t");
				System.out.println(mark.getDate());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findMarkByTeacherId() {
		try {
			ConsoleTeacher consoleTeacher = new ConsoleTeacher(teacherService, personService);
			consoleTeacher.printAllTeachers();
			System.out.print(RS_MARK_TEACHER_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Mark> marks = markService.findMarkByTeacherIdService(id);
			Iterator<Mark> itr = marks.iterator();
			System.out.println(RS_MARK_LIST_HEADER);
			while (itr.hasNext()) {
				Mark mark = itr.next();
				System.out.print(mark.getId() + ",\t");
				System.out.print(mark.getMarkTypeId() + ",\t");
				System.out.print(mark.getTeacher_id() + ",\t");
				System.out.print(mark.getStudent_id() + ",\t");
				System.out.print(mark.getGroup_id() + ",\t");
				System.out.print(mark.getSubject_id() + ",\t");
				System.out.print(mark.getForm_id() + ",\t");
				System.out.println(mark.getDate());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findMarkByStudentId() {
		try {
			ConsoleStudent consoleStudent = new ConsoleStudent(
					studentService, groupService, formService, specializationService, personService);
			consoleStudent.printAllStudents();
			System.out.print(RS_MARK_STUDENT_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Mark> marks = markService.findMarkByStudentIdService(id);
			Iterator<Mark> itr = marks.iterator();
			System.out.println(RS_MARK_LIST_HEADER);
			while (itr.hasNext()) {
				Mark mark = itr.next();
				System.out.print(mark.getId() + ",\t");
				System.out.print(mark.getMarkTypeId() + ",\t");
				System.out.print(mark.getTeacher_id() + ",\t");
				System.out.print(mark.getStudent_id() + ",\t");
				System.out.print(mark.getGroup_id() + ",\t");
				System.out.print(mark.getSubject_id() + ",\t");
				System.out.print(mark.getForm_id() + ",\t");
				System.out.println(mark.getDate());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findMarkByGroupId() {
		try {
			ConsoleGroup consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
			consoleGroup.printAllGroups();
			System.out.print(RS_MARK_GROUP_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Mark> marks = markService.findMarkByGroupIdService(id);
			Iterator<Mark> itr = marks.iterator();
			System.out.println(RS_MARK_LIST_HEADER);
			while (itr.hasNext()) {
				Mark mark = itr.next();
				System.out.print(mark.getId() + ",\t");
				System.out.print(mark.getMarkTypeId() + ",\t");
				System.out.print(mark.getTeacher_id() + ",\t");
				System.out.print(mark.getStudent_id() + ",\t");
				System.out.print(mark.getGroup_id() + ",\t");
				System.out.print(mark.getSubject_id() + ",\t");
				System.out.print(mark.getForm_id() + ",\t");
				System.out.println(mark.getDate());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findMarkBySubjectId() {
		try {
			ConsoleSubject consoleSubject = new ConsoleSubject(subjectService, specializationService);
			consoleSubject.printAllSubjects();
			System.out.print(RS_MARK_SUBJECT_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Mark> marks = markService.findMarkBySubjectIdService(id);
			Iterator<Mark> itr = marks.iterator();
			System.out.println(RS_MARK_LIST_HEADER);
			while (itr.hasNext()) {
				Mark mark = itr.next();
				System.out.print(mark.getId() + ",\t");
				System.out.print(mark.getMarkTypeId() + ",\t");
				System.out.print(mark.getTeacher_id() + ",\t");
				System.out.print(mark.getStudent_id() + ",\t");
				System.out.print(mark.getGroup_id() + ",\t");
				System.out.print(mark.getSubject_id() + ",\t");
				System.out.print(mark.getForm_id() + ",\t");
				System.out.println(mark.getDate());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findMarkByFormId() {
		try {
			ConsoleForm consoleForm = new ConsoleForm(formService);
			consoleForm.printAllForms();
			System.out.print(RS_MARK_FORM_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Mark> marks = markService.findMarkByFormIdService(id);
			Iterator<Mark> itr = marks.iterator();
			System.out.println(RS_MARK_LIST_HEADER);
			while (itr.hasNext()) {
				Mark mark = itr.next();
				System.out.print(mark.getId() + ",\t");
				System.out.print(mark.getMarkTypeId() + ",\t");
				System.out.print(mark.getTeacher_id() + ",\t");
				System.out.print(mark.getStudent_id() + ",\t");
				System.out.print(mark.getGroup_id() + ",\t");
				System.out.print(mark.getSubject_id() + ",\t");
				System.out.print(mark.getForm_id() + ",\t");
				System.out.println(mark.getDate());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findMarkByDate() {
		System.out.print(RS_MARK_DATE);
		StringDateFormatter std = new BasicStringDateFormatter();
		try {
			Date markDate = std.parseToDate(scanner.nextLine());
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Mark> marks = markService.findMarkByDateService(markDate);
			Iterator<Mark> itr = marks.iterator();
			System.out.println(RS_MARK_LIST_HEADER);
			while (itr.hasNext()) {
				Mark mark = itr.next();
				System.out.print(mark.getId() + ",\t");
				System.out.print(mark.getMarkTypeId() + ",\t");
				System.out.print(mark.getTeacher_id() + ",\t");
				System.out.print(mark.getStudent_id() + ",\t");
				System.out.print(mark.getGroup_id() + ",\t");
				System.out.print(mark.getSubject_id() + ",\t");
				System.out.print(mark.getForm_id() + ",\t");
				System.out.println(mark.getDate());
			}
		} catch (ParseException e) {
			System.out.println(RS_OPERATION_ERROR);
			log.error(e);
		}
	}

	public void deleteMark() {
		try {
			printAllMarks();
			System.out.print(RS_MARK_DELETE_BY_ID);
			long markId = scanner.nextLong();
			scanner.nextLine();
			Mark mark = new Mark();
			mark.setId(markId);
			if (markService.deleteMarkService(mark)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addMark() {
		try {
			StringDateFormatter std = new BasicStringDateFormatter();
			System.out.print(RS_MARK_NEW_DATE);
			String str = scanner.nextLine();
			try {
				Date markDate = std.parseToDate(str);

				ConsoleMarkType consoleMark_type = new ConsoleMarkType(markTypeService);
				consoleMark_type.printAllMarkTypes();
				System.out.print(RS_MARK_MARKTYPE_ID);
				long markTypeId = scanner.nextLong();
				scanner.nextLine();

				ConsoleTeacher consoleTeacher = new ConsoleTeacher(teacherService, personService);
				consoleTeacher.printAllTeachers();
				System.out.print(RS_MARK_TEACHER_ID);
				long teacherId = scanner.nextLong();
				scanner.nextLine();

				ConsoleStudent consoleStudent = new ConsoleStudent(
						studentService, groupService, formService, specializationService, personService);
				consoleStudent.printAllStudents();
				System.out.print(RS_MARK_STUDENT_ID);
				long studentId = scanner.nextLong();
				scanner.nextLine();

				ConsoleGroup consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
				consoleGroup.printAllGroups();
				System.out.print(RS_MARK_GROUP_ID);
				long groupId = scanner.nextLong();
				scanner.nextLine();

				ConsoleSubject consoleSubject = new ConsoleSubject(subjectService, specializationService);
				consoleSubject.printAllSubjects();
				System.out.print(RS_MARK_SUBJECT_ID);
				long subjectId = scanner.nextLong();
				scanner.nextLine();

				ConsoleForm consoleForm = new ConsoleForm(formService);
				consoleForm.printAllForms();
				System.out.print(RS_MARK_FORM_ID);
				long formId = scanner.nextLong();
				scanner.nextLine();

				Mark mark = new Mark(markTypeId, teacherId, studentId, groupId, subjectId, formId, markDate);
				if (markService.saveMarkService(mark)) {
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

	public void updateMark() {
		try {
			printAllMarks();
			System.out.print(RS_MARK_UPDATE_BY_ID);
			long markId = scanner.nextLong();
			scanner.nextLine();

			StringDateFormatter std = new BasicStringDateFormatter();
			System.out.print(RS_MARK_NEW_DATE);
			String str = scanner.nextLine();
			try {
				Date markDate = std.parseToDate(str);

				ConsoleMarkType consoleMark_type = new ConsoleMarkType(markTypeService);
				consoleMark_type.printAllMarkTypes();
				System.out.print(RS_MARK_MARKTYPE_ID);
				long markTypeId = scanner.nextLong();
				scanner.nextLine();

				ConsoleTeacher consoleTeacher = new ConsoleTeacher(teacherService, personService);
				consoleTeacher.printAllTeachers();
				System.out.print(RS_MARK_TEACHER_ID);
				long teacherId = scanner.nextLong();
				scanner.nextLine();

				ConsoleStudent consoleStudent = new ConsoleStudent(
						studentService, groupService, formService, specializationService, personService);
				consoleStudent.printAllStudents();
				System.out.print(RS_MARK_STUDENT_ID);
				long studentId = scanner.nextLong();
				scanner.nextLine();

				ConsoleGroup consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
				consoleGroup.printAllGroups();
				System.out.print(RS_MARK_GROUP_ID);
				long groupId = scanner.nextLong();
				scanner.nextLine();

				ConsoleSubject consoleSubject = new ConsoleSubject(subjectService, specializationService);
				consoleSubject.printAllSubjects();
				System.out.print(RS_MARK_SUBJECT_ID);
				long subjectId = scanner.nextLong();
				scanner.nextLine();

				ConsoleForm consoleForm = new ConsoleForm(formService);
				consoleForm.printAllForms();
				System.out.print(RS_MARK_FORM_ID);
				long formId = scanner.nextLong();
				scanner.nextLine();

				Mark mark = new Mark(markTypeId, teacherId, studentId, groupId, subjectId, formId, markDate);
				mark.setId(markId);
				if (markService.saveMarkService(mark)) {
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
