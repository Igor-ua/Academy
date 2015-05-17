package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.Form;
import org.mydomain.academy.services.ServiceInterface.FormService;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

public class ConsoleForm {

	private Scanner scanner = new Scanner(System.in);
	private FormService formService;
	private static final Logger log = Logger.getLogger(ConsoleForm.class);

	public ConsoleForm(FormService formService) {
		this.formService = formService;
	}

	public void printAllForms() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Form> forms = formService.findAllFormsService();
		Iterator<Form> itr = forms.iterator();
		System.out.println(RS_FORM_LIST_HEADER);
		while (itr.hasNext()) {
			Form form = itr.next();
			System.out.print(form.getId() + ",\t");
			System.out.println(form.getName());
		}
	}

	public void findFormById() {
		try {
			System.out.print(RS_FORM_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			Form form = formService.findFormByIdService(id);
			if (form.getId() != 0) {
				System.out.println(form);
				System.out.println(RS_FORM_GROUPS);
				System.out.println(form.getGroupSet());
				System.out.println(RS_FORM_MARKS);
				System.out.println(form.getMarkSet());
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findFormByName() {
		System.out.print(RS_FORM_NAME);
		String name = scanner.nextLine();
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<Form> forms = formService.findFormByNameService(name);
		Iterator<Form> itr = forms.iterator();
		System.out.println(RS_FORM_LIST_HEADER);
		while (itr.hasNext()) {
			Form form = itr.next();
			System.out.print(form.getId() + ",\t");
			System.out.println(form.getName());
		}
	}

	public void deleteForm() {
		try {
			printAllForms();
			System.out.print(RS_FORM_DELETE_BY_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			Form form = new Form();
			form.setId(id);
			if (formService.deleteFormService(form)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addForm() {
		System.out.print(RS_FORM_NEW_NAME);
		String formName = scanner.nextLine();
		Form form = new Form(formName);
		if (formService.saveFormService(form)) {
			System.out.println(RS_OPERATION_SUCCESS);
		} else System.out.println(RS_OPERATION_ERROR);
	}

	public void updateForm() {
		try {
			printAllForms();
			System.out.print(RS_FORM_UPDATE_BY_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.print(RS_FORM_NEW_NAME);
			String formName = scanner.nextLine();
			Form form = new Form(formName);
			form.setId(id);
			if (formService.saveFormService(form)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}
}