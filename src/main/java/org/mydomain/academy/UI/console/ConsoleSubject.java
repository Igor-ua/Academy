package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.Subject;
import org.mydomain.academy.services.ServiceInterface.SpecializationService;
import org.mydomain.academy.services.ServiceInterface.SubjectService;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

public class ConsoleSubject {

	private Scanner scanner = new Scanner(System.in);

	private static final Logger log = Logger.getLogger(ConsoleSubject.class);

	private SubjectService subjectService;
	private SpecializationService specializationService;

	public ConsoleSubject(SubjectService subjectService, SpecializationService specializationService) {
		this.subjectService = subjectService;
		this.specializationService = specializationService;
	}

	public void printAllSubjects() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Subject> subjects = subjectService.findAllSubjectsService();
		Iterator<Subject> itr = subjects.iterator();
		System.out.println(RS_SUBJECT_LIST_HEADER);
		while (itr.hasNext()) {
			Subject subject = itr.next();
			System.out.print(subject.getId() + ",\t");
			System.out.print(subject.getName() + ",\t");
			System.out.println(subject.getSpecialization_id());
		}
	}

	public void findSubjectById() {
		try {
			System.out.print(RS_SUBJECT_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			Subject subject = subjectService.findSubjectByIdService(id);
			if (subject.getId() != 0)
				System.out.println(subject);
			else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findSubjectByName() {
		System.out.print(RS_SUBJECT_NAME);
		String name = scanner.nextLine();
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Subject> subjects = subjectService.findSubjectByNameService(name);
		Iterator<Subject> itr = subjects.iterator();
		System.out.println(RS_SUBJECT_LIST_HEADER);
		while (itr.hasNext()) {
			Subject subject = itr.next();
			System.out.print(subject.getId() + ",\t");
			System.out.print(subject.getName() + ",\t");
			System.out.println(subject.getSpecialization_id());
		}
	}

	public void findSubjectBySpecializationId() {
		try {
			ConsoleSpecialization consoleSpecialization = new ConsoleSpecialization(specializationService);
			consoleSpecialization.printAllSpecializations();
			System.out.print(RS_SUBJECT_SPEC_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Subject> subjects = subjectService.findSubjectBySpecializationIdService(id);
			Iterator<Subject> itr = subjects.iterator();
			System.out.println(RS_SUBJECT_LIST_HEADER);
			while (itr.hasNext()) {
				Subject subject = itr.next();
				System.out.print(subject.getId() + ",\t");
				System.out.print(subject.getName() + ",\t");
				System.out.println(subject.getSpecialization_id());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void deleteSubject() {
		try {
			printAllSubjects();
			System.out.print(RS_SUBJECT_DELETE_BY_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			Subject subject = new Subject();
			subject.setId(id);

			if (subjectService.deleteSubjectService(subject)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addSubject() {
		try {
			ConsoleSpecialization consoleSpecialization = new ConsoleSpecialization(specializationService);
			consoleSpecialization.printAllSpecializations();
			System.out.print(RS_SUBJECT_SPEC_ID);
			long specializationId = scanner.nextLong();
			scanner.nextLine();

			System.out.print(RS_SUBJECT_NAME);
			String subjectName = scanner.nextLine();

			Subject subject = new Subject(subjectName, specializationId);
			if (subjectService.saveSubjectService(subject)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void updateSubject() {
		try {
			printAllSubjects();
			System.out.print(RS_SUBJECT_UPDATE_BY_ID);
			long subjectId = scanner.nextLong();
			scanner.nextLine();

			ConsoleSpecialization consoleSpecialization = new ConsoleSpecialization(specializationService);
			consoleSpecialization.printAllSpecializations();
			System.out.print(RS_SUBJECT_SPEC_ID);
			long specializationId = scanner.nextLong();
			scanner.nextLine();

			System.out.print(RS_SUBJECT_NAME);
			String subjectName = scanner.nextLine();

			Subject subject = new Subject(subjectName, specializationId);
			subject.setId(subjectId);
			if (subjectService.saveSubjectService(subject)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}
}
