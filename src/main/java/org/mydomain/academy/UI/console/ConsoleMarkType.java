package org.mydomain.academy.UI.console;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.entities.MarkType;
import org.mydomain.academy.services.ServiceInterface.MarkTypeService;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.mydomain.academy.UI.console.messages.ConsoleResultsMessages.*;

public class ConsoleMarkType {

	private Scanner scanner = new Scanner(System.in);
	private static final Logger log = Logger.getLogger(ConsoleMarkType.class);
	private MarkTypeService markTypeService;

	public ConsoleMarkType(MarkTypeService markTypeService) {
		this.markTypeService = markTypeService;
	}

	public void printAllMarkTypes() {
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<MarkType> markTypes = markTypeService.findAllMarkTypesService();
		Iterator<MarkType> itr = markTypes.iterator();
		System.out.println(RS_MARKTYPE_LIST_HEADER);
		while (itr.hasNext()) {
			MarkType markType = itr.next();
			System.out.print(markType.getId() + ",\t");
			System.out.println(markType.getName());
		}
	}

	public void findMarkTypeById() {
		try {
			System.out.print(RS_MARKTYPE_ID);
			long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println(RS_SEARCH_RESULTS_MSG);
			MarkType markType = markTypeService.findMarkTypeByIdService(id);
			if (markType.getId() != 0) {
				System.out.println(markType);
				System.out.println(RS_MARKTYPE_MARKS);
				System.out.println(markType.getMarkSet());
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void findMarkTypeByName() {
		System.out.print(RS_MARKTYPE_NAME);
		String name = scanner.nextLine();
		System.out.println(RS_SEARCH_RESULTS_MSG);
		List<MarkType> markTypes = markTypeService.findMarkTypeByNameService(name);
		Iterator<MarkType> itr = markTypes.iterator();
		System.out.println(RS_MARKTYPE_LIST_HEADER);
		while (itr.hasNext()) {
			MarkType markType = itr.next();
			System.out.print(markType.getId() + ",\t");
			System.out.println(markType.getName());
		}
	}

	public void deleteMarkType() {
		try {
			printAllMarkTypes();
			System.out.print(RS_MARKTYPE_DELETE_BY_ID);
			long markTypeId = scanner.nextLong();
			scanner.nextLine();
			MarkType markType = new MarkType();
			markType.setId(markTypeId);
			if (markTypeService.deleteService(markType)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}

	public void addMarkType() {
		System.out.print(RS_MARKTYPE_NAME);
		String markTypeName = scanner.nextLine();
		MarkType markType = new MarkType(markTypeName);
		if (markTypeService.saveService(markType)) {
			System.out.println(RS_OPERATION_SUCCESS);
		} else System.out.println(RS_OPERATION_ERROR);
	}

	public void updateMarkType() {
		try {
			printAllMarkTypes();
			System.out.print(RS_MARKTYPE_UPDATE_BY_ID);
			long markTypeId = scanner.nextLong();
			scanner.nextLine();
			System.out.print(RS_MARKTYPE_NEW_NAME);
			String markTypeName = scanner.nextLine();

			MarkType markType = new MarkType(markTypeName);
			markType.setId(markTypeId);
			if (markTypeService.saveService(markType)) {
				System.out.println(RS_OPERATION_SUCCESS);
			} else System.out.println(RS_OPERATION_ERROR);
		} catch (InputMismatchException e) {
			System.out.println(RS_INPUT_ID_ERROR);
		}
	}
}