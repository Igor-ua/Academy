package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.services.ServiceInterface.FormService;
import org.mydomain.academy.services.ServiceInterface.GroupService;
import org.mydomain.academy.services.ServiceInterface.SpecializationService;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

public class ConsoleGroup {

	private Scanner scanner = new Scanner(System.in);

	private static final Logger log = Logger.getLogger(ConsoleGroup.class);

	private GroupService groupService;
	private FormService formService;
	private SpecializationService specializationService;

	public ConsoleGroup(GroupService groupService, FormService formService,
						SpecializationService specializationService) {
		this.groupService = groupService;
		this.formService = formService;
		this.specializationService = specializationService;
	}

	public void printAllGroups() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Group> groups = groupService.findAllGroupsService();
		Iterator<Group> itr = groups.iterator();
		System.out.println(RS_GROUP_LIST_HEADER);
		while (itr.hasNext()) {
			Group group = itr.next();
			System.out.print(group.getId() + ",\t");
			System.out.print(group.getName() + ",\t");
			System.out.print(group.getForm_id() + ",\t");
			System.out.println(group.getSpecialization_id());
		}
	}

	public void findGroupById() {
		try {
			System.out.print(RS_GROUP_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			Group group = groupService.findGroupByIdService(id);
			if (group.getId() != 0) {
				System.out.println(group);
				System.out.println(RS_GROUP_STUDENTS);
				System.out.println(group.getStudentSet());
				System.out.println(RS_GROUP_SCHEDULE);
				System.out.println(group.getScheduleSet());
				System.out.println(RS_GROUP_MARKS);
				System.out.println(group.getMarkSet());
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findGroupByName() {
		System.out.print(RS_GROUP_NAME);
		String name = scanner.nextLine();
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Group> groups = groupService.findGroupByNameService(name);
		Iterator<Group> itr = groups.iterator();
		System.out.println(RS_GROUP_LIST_HEADER);
		while (itr.hasNext()) {
			Group group = itr.next();
			System.out.print(group.getId() + ",\t");
			System.out.print(group.getName() + ",\t");
			System.out.print(group.getForm_id() + ",\t");
			System.out.println(group.getSpecialization_id());
		}
	}

	public void findGroupByFormId() {
		try {
			ConsoleForm consoleForm = new ConsoleForm(formService);
			consoleForm.printAllForms();
			System.out.print(RS_GROUP_FORM_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.print(RS_SEARCH_RESULTS_MSG);
			List<Group> groups = groupService.findGroupByFormIdService(id);
			Iterator<Group> itr = groups.iterator();
			System.out.println(RS_GROUP_LIST_HEADER);
			while (itr.hasNext()) {
				Group group = itr.next();
				System.out.print(group.getId() + ",\t");
				System.out.print(group.getName() + ",\t");
				System.out.print(group.getForm_id() + ",\t");
				System.out.println(group.getSpecialization_id());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findGroupBySpecializationId() {
		try {
			ConsoleSpecialization consoleSpecialization = new ConsoleSpecialization(specializationService);
			consoleSpecialization.printAllSpecializations();
			System.out.print(RS_GROUP_SPEC_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			List<Group> groups = groupService.findGroupBySpecializationIdService(id);
			Iterator<Group> itr = groups.iterator();
			System.out.println(RS_GROUP_LIST_HEADER);
			while (itr.hasNext()) {
				Group group = itr.next();
				System.out.print(group.getId() + ",\t");
				System.out.print(group.getName() + ",\t");
				System.out.print(group.getForm_id() + ",\t");
				System.out.println(group.getSpecialization_id());
			}
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void deleteGroup() {
		try {
			printAllGroups();
			System.out.print(RS_GROUP_DELETE_BY_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			Group group = new Group();
			group.setId(id);
			if (groupService.deleteService(group)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addGroup() {
		try {
			System.out.print(RS_GROUP_NEW_NAME);
			String groupName = scanner.nextLine();
			ConsoleForm consoleForm = new ConsoleForm(formService);
			consoleForm.printAllForms();
			System.out.print(RS_GROUP_FORM_ID);
			long formId = scanner.nextLong();
			scanner.nextLine();
			ConsoleSpecialization consoleSpecialization = new ConsoleSpecialization(specializationService);
			consoleSpecialization.printAllSpecializations();
			System.out.print(RS_GROUP_SPEC_ID);
			long specializationId = scanner.nextLong();
			scanner.nextLine();
			Group group = new Group(groupName, formId, specializationId);
			if (groupService.saveService(group)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void updateGroup() {
		try {
			printAllGroups();
			System.out.print(RS_GROUP_UPDATE_BY_ID);
			long groupId = scanner.nextLong();
			scanner.nextLine();
			System.out.print(RS_GROUP_NEW_NAME);
			String groupName = scanner.nextLine();
			ConsoleForm consoleForm = new ConsoleForm(formService);
			consoleForm.printAllForms();
			System.out.print(RS_GROUP_FORM_ID);
			long formId = scanner.nextLong();
			scanner.nextLine();
			ConsoleSpecialization consoleSpecialization = new ConsoleSpecialization(specializationService);
			consoleSpecialization.printAllSpecializations();
			System.out.print(RS_GROUP_SPEC_ID);
			long specializationId = scanner.nextLong();
			scanner.nextLine();
			Group group = new Group(groupName, formId, specializationId);
			group.setId(groupId);
			if (groupService.saveService(group)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}
}
