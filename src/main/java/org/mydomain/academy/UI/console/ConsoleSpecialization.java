package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.services.ServiceInterface.SpecializationService;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

public class ConsoleSpecialization {

	private Scanner scanner = new Scanner(System.in);
	private static final Logger log = Logger.getLogger(ConsoleSpecialization.class);
	private SpecializationService specializationService;

	public ConsoleSpecialization(SpecializationService specializationService) {
		this.specializationService = specializationService;
	}

	public void printAllSpecializations() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Specialization> specializations = specializationService.findAllSpecializationsService();
		Iterator<Specialization> itr = specializations.iterator();
		System.out.println(RS_SPECIALIZATION_LIST_HEADER);
		while (itr.hasNext()) {
			Specialization specialization = itr.next();
			System.out.print(specialization.getId() + ",\t");
			System.out.println(specialization.getName());
		}
	}

	public void findSpecializationById() {
		try {
			System.out.print(RS_SPECIALIZATION_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			Specialization specialization = specializationService.findSpecializationByIdService(id);
			if (specialization.getId() != 0)
				System.out.println(specialization);
			else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findSpecializationByName() {
		System.out.print(RS_SPECIALIZATION_NAME);
		String name = scanner.nextLine();
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Specialization> specializations = specializationService.findSpecializationByNameService(name);
		Iterator<Specialization> itr = specializations.iterator();
		System.out.println(RS_SPECIALIZATION_LIST_HEADER);
		while (itr.hasNext()) {
			Specialization specialization = itr.next();
			System.out.print(specialization.getId() + ",\t");
			System.out.println(specialization.getName());
		}
	}

	public void deleteSpecialization() {
		try {
			printAllSpecializations();
			System.out.print(RS_SPECIALIZATION_DELETE_BY_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			Specialization specialization = new Specialization();
			specialization.setId(id);
			if (specializationService.deleteSpecializationService(specialization)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addSpecialization() {
		try {
			System.out.print(RS_SPECIALIZATION_NEW_NAME);
			String specializationName = scanner.nextLine();
			Specialization specialization = new Specialization(specializationName);
			if (specializationService.saveSpecializationService(specialization)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void updateSpecialization() {
		try {
			printAllSpecializations();
			System.out.print(RS_SPECIALIZATION_UPDATE_BY_ID);
			long specializationId = scanner.nextLong();
			scanner.nextLine();

			System.out.print(RS_SPECIALIZATION_NAME);
			String specializationName = scanner.nextLine();

			Specialization specialization = new Specialization(specializationName);
			specialization.setId(specializationId);
			if (specializationService.saveSpecializationService(specialization)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}
}
