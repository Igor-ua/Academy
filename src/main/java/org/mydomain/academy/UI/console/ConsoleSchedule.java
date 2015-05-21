package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.Schedule;
import org.mydomain.academy.services.ServiceInterface.*;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

import static org.mydomain.academy.db.utils.TableNames.*;

public class ConsoleSchedule {

	private Scanner scanner = new Scanner(System.in);

	private static final Logger log = Logger.getLogger(ConsoleSchedule.class);

	private ScheduleService scheduleService;
	private SubjectService subjectService;
	private TeacherService teacherService;
	private GroupService groupService;
	private FormService formService;
	private SpecializationService specializationService;
	private PersonService personService;

	public ConsoleSchedule(ScheduleService scheduleService, SubjectService subjectService,
						   TeacherService teacherService, GroupService groupService,
						   FormService formService, SpecializationService specializationService,
						   PersonService personService) {
		this.scheduleService = scheduleService;
		this.subjectService = subjectService;
		this.groupService = groupService;
		this.teacherService = teacherService;
		this.formService = formService;
		this.specializationService = specializationService;
		this.personService = personService;
	}

	public void printAllSchedules() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Schedule> schedules = scheduleService.findAllSchedulesService();
		Iterator<Schedule> itr = schedules.iterator();
		System.out.println(RS_SCHEDULE_LIST_HEADER);
		while (itr.hasNext()) {
			Schedule schedule = itr.next();
			System.out.print(schedule.getId() + ",\t");
			System.out.print(schedule.getSubject_id() + ",\t");
			System.out.print(schedule.getTeacher_id() + ",\t");
			System.out.print(schedule.getGroup_id() + ",\t");
			System.out.print(schedule.getDay() + ",\t");
			System.out.print(schedule.getChisZnam() + ",\t");
			System.out.println(schedule.getLenta());
		}
	}

	public void findScheduleById() {
		try {
			System.out.print(RS_SCHEDULE_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			Schedule schedule = scheduleService.findScheduleByIdService(id);
			if (schedule.getId() != 0)
				System.out.println(schedule);
			else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findScheduleBySubjectId() {
		try {
			ConsoleSubject consoleSubject = new ConsoleSubject(subjectService, specializationService);
			consoleSubject.printAllSubjects();
			System.out.print(RS_SCHEDULE_SUBJECT_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.print(RS_SEARCH_RESULTS_MSG);
			List<Schedule> schedules = scheduleService.findScheduleBySubjectIdService(id);
			Iterator<Schedule> itr = schedules.iterator();
			System.out.println(RS_SCHEDULE_LIST_HEADER);
			while (itr.hasNext()) {
				Schedule schedule = itr.next();
				System.out.print(schedule.getId() + ",\t");
				System.out.print(schedule.getSubject_id() + ",\t");
				System.out.print(schedule.getTeacher_id() + ",\t");
				System.out.print(schedule.getGroup_id() + ",\t");
				System.out.print(schedule.getDay() + ",\t");
				System.out.print(schedule.getChisZnam() + ",\t");
				System.out.println(schedule.getLenta());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findScheduleByTeacherId() {
		try {
			ConsoleTeacher consoleTeacher = new ConsoleTeacher(teacherService, personService);
			consoleTeacher.printAllTeachers();
			System.out.print(RS_SCHEDULE_TEACHER_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.print(RS_SEARCH_RESULTS_MSG);
			List<Schedule> schedules = scheduleService.findScheduleByTeacherIdService(id);
			Iterator<Schedule> itr = schedules.iterator();
			System.out.println(RS_SCHEDULE_LIST_HEADER);
			while (itr.hasNext()) {
				Schedule schedule = itr.next();
				System.out.print(schedule.getId() + ",\t");
				System.out.print(schedule.getSubject_id() + ",\t");
				System.out.print(schedule.getTeacher_id() + ",\t");
				System.out.print(schedule.getGroup_id() + ",\t");
				System.out.print(schedule.getDay() + ",\t");
				System.out.print(schedule.getChisZnam() + ",\t");
				System.out.println(schedule.getLenta());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findScheduleByGroupId() {
		try {
			ConsoleGroup consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
			consoleGroup.printAllGroups();
			System.out.print(RS_SCHEDULE_GROUP_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.print(RS_SEARCH_RESULTS_MSG);
			List<Schedule> schedules = scheduleService.findScheduleByGroupIdService(id);
			Iterator<Schedule> itr = schedules.iterator();
			System.out.println(RS_SCHEDULE_LIST_HEADER);
			while (itr.hasNext()) {
				Schedule schedule = itr.next();
				System.out.print(schedule.getId() + ",\t");
				System.out.print(schedule.getSubject_id() + ",\t");
				System.out.print(schedule.getTeacher_id() + ",\t");
				System.out.print(schedule.getGroup_id() + ",\t");
				System.out.print(schedule.getDay() + ",\t");
				System.out.print(schedule.getChisZnam() + ",\t");
				System.out.println(schedule.getLenta());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findScheduleByDayId() {
		try {
			System.out.println(RS_SCHEDULE_DAY_HEADER);
			int counter = 0;
			for (ScheduleColumns.DAY_ENUM day : ScheduleColumns.DAY_ENUM.values()) {
				System.out.println(counter + " - " + day.name());
				counter++;
			}
			System.out.print(RS_SCHEDULE_DAY);
			int day = scanner.nextInt();
			scanner.nextLine();

			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Schedule> schedules = scheduleService.findScheduleByDayIdService(day);
			Iterator<Schedule> itr = schedules.iterator();
			System.out.println(RS_SCHEDULE_LIST_HEADER);
			while (itr.hasNext()) {
				Schedule schedule = itr.next();
				System.out.print(schedule.getId() + ",\t");
				System.out.print(schedule.getSubject_id() + ",\t");
				System.out.print(schedule.getTeacher_id() + ",\t");
				System.out.print(schedule.getGroup_id() + ",\t");
				System.out.print(schedule.getDay() + ",\t");
				System.out.print(schedule.getChisZnam() + ",\t");
				System.out.println(schedule.getLenta());
			}
		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findScheduleByChisZnam() {
		try {
			System.out.println(RS_SCHEDULE_CHIS_ZNAM_HEADER);
			int counter = 0;
			for (ScheduleColumns.CHIS_ZNAM_ENUM chisZnam : ScheduleColumns.CHIS_ZNAM_ENUM.values()) {
				System.out.println(counter + " - " + chisZnam.name());
				counter++;
			}
			System.out.print(RS_SCHEDULE_CHIS_ZNAM);
			int chisZnam = scanner.nextInt();
			scanner.nextLine();

			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Schedule> schedules = scheduleService.findScheduleByChisZnamService(chisZnam);
			Iterator<Schedule> itr = schedules.iterator();
			System.out.println(RS_SCHEDULE_LIST_HEADER);
			while (itr.hasNext()) {
				Schedule schedule = itr.next();
				System.out.print(schedule.getId() + ",\t");
				System.out.print(schedule.getSubject_id() + ",\t");
				System.out.print(schedule.getTeacher_id() + ",\t");
				System.out.print(schedule.getGroup_id() + ",\t");
				System.out.print(schedule.getDay() + ",\t");
				System.out.print(schedule.getChisZnam() + ",\t");
				System.out.println(schedule.getLenta());
			}
		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findScheduleByLenta() {
		try {
			System.out.print(RS_SCHEDULE_LENTA);
			int lenta = scanner.nextInt();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Schedule> schedules = scheduleService.findScheduleByLentaService(lenta);
			Iterator<Schedule> itr = schedules.iterator();
			System.out.println(RS_SCHEDULE_LIST_HEADER);
			while (itr.hasNext()) {
				Schedule schedule = itr.next();
				System.out.print(schedule.getId() + ",\t");
				System.out.print(schedule.getSubject_id() + ",\t");
				System.out.print(schedule.getTeacher_id() + ",\t");
				System.out.print(schedule.getGroup_id() + ",\t");
				System.out.print(schedule.getDay() + ",\t");
				System.out.print(schedule.getChisZnam() + ",\t");
				System.out.println(schedule.getLenta());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void deleteSchedule() {
		try {
			printAllSchedules();
			System.out.print(RS_SCHEDULE_DELETE_BY_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			Schedule schedule = new Schedule();
			schedule.setId(id);
			if (scheduleService.deleteService(schedule)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addSchedule() {
		try {
			ConsoleSubject consoleSubject = new ConsoleSubject(subjectService, specializationService);
			consoleSubject.printAllSubjects();
			System.out.print(RS_SCHEDULE_SUBJECT_ID);
			long subjectId = scanner.nextLong();
			scanner.nextLine();

			ConsoleTeacher consoleTeacher = new ConsoleTeacher(teacherService, personService);
			consoleTeacher.printAllTeachers();
			System.out.print(RS_SCHEDULE_TEACHER_ID);
			long teacherId = scanner.nextLong();
			scanner.nextLine();

			ConsoleGroup consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
			consoleGroup.printAllGroups();
			System.out.print(RS_SCHEDULE_GROUP_ID);
			long groupId = scanner.nextLong();
			scanner.nextLine();

			System.out.println(RS_SCHEDULE_DAY_HEADER);
			int counter = 0;
			for (ScheduleColumns.DAY_ENUM day : ScheduleColumns.DAY_ENUM.values()) {
				System.out.println(counter + " - " + day.name());
				counter++;
			}
			System.out.print(RS_SCHEDULE_DAY);
			String day = ScheduleColumns.DAY_ENUM.values()[scanner.nextInt()].toString();
			scanner.nextLine();

			System.out.println(RS_SCHEDULE_CHIS_ZNAM_HEADER);
			counter = 0;
			for (ScheduleColumns.CHIS_ZNAM_ENUM chisZnam : ScheduleColumns.CHIS_ZNAM_ENUM.values()) {
				System.out.println(counter + " - " + chisZnam.name());
				counter++;
			}
			System.out.print(RS_SCHEDULE_CHIS_ZNAM);
			String chisZnam = ScheduleColumns.CHIS_ZNAM_ENUM.values()[scanner.nextInt()].toString();
			scanner.nextLine();

			System.out.print(RS_SCHEDULE_LENTA);
			int lenta = scanner.nextInt();
			scanner.nextLine();

			Schedule schedule = new Schedule(subjectId, teacherId, groupId, day, chisZnam, lenta);
			if (scheduleService.saveService(schedule)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void updateSchedule() {
		try {
			printAllSchedules();
			System.out.print(RS_SCHEDULE_UPDATE_BY_ID);
			long scheduleId = scanner.nextLong();
			scanner.nextLine();

			ConsoleSubject consoleSubject = new ConsoleSubject(subjectService, specializationService);
			consoleSubject.printAllSubjects();
			System.out.print(RS_SCHEDULE_SUBJECT_ID);
			long subjectId = scanner.nextLong();
			scanner.nextLine();

			ConsoleTeacher consoleTeacher = new ConsoleTeacher(teacherService, personService);
			consoleTeacher.printAllTeachers();
			System.out.print(RS_SCHEDULE_TEACHER_ID);
			long teacherId = scanner.nextLong();
			scanner.nextLine();

			ConsoleGroup consoleGroup = new ConsoleGroup(groupService, formService, specializationService);
			consoleGroup.printAllGroups();
			System.out.print(RS_SCHEDULE_GROUP_ID);
			long groupId = scanner.nextLong();
			scanner.nextLine();

			System.out.println(RS_SCHEDULE_DAY_HEADER);
			int counter = 0;
			for (ScheduleColumns.DAY_ENUM day : ScheduleColumns.DAY_ENUM.values()) {
				System.out.println(counter + " - " + day.name());
				counter++;
			}
			System.out.print(RS_SCHEDULE_DAY);
			String day = ScheduleColumns.DAY_ENUM.values()[scanner.nextInt()].toString();
			scanner.nextLine();

			System.out.println(RS_SCHEDULE_CHIS_ZNAM_HEADER);
			counter = 0;
			for (ScheduleColumns.CHIS_ZNAM_ENUM chisZnam : ScheduleColumns.CHIS_ZNAM_ENUM.values()) {
				System.out.println(counter + " - " + chisZnam.name());
				counter++;
			}
			System.out.print(RS_SCHEDULE_CHIS_ZNAM);
			String chisZnam = ScheduleColumns.CHIS_ZNAM_ENUM.values()[scanner.nextInt()].toString();
			scanner.nextLine();

			System.out.print(RS_SCHEDULE_LENTA);
			int lenta = scanner.nextInt();
			scanner.nextLine();

			Schedule schedule = new Schedule(subjectId, teacherId, groupId, day, chisZnam, lenta);
			schedule.setId(scheduleId);
			if (scheduleService.saveService(schedule)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}
}
