package org.mydomain.academy.UI.console.messages;

public enum ConsoleResultsMessages {

	RS_INPUT_ID_ERROR("[x] ID input error."),
	RS_SEARCH_RESULTS_MSG("[!] Search results: "),
	RS_OPERATION_SUCCESS("[!] Successful operation."),
	RS_OPERATION_ERROR("[x]  Unsuccessful operation. Check your input data."),

	RS_FORM_LIST_HEADER("Form id,\tName"),
	RS_FORM_ID("\nEnter Form id: "),
	RS_FORM_NAME("\nEnter Form name: "),
	RS_FORM_GROUPS("Groups with this Form: "),
	RS_FORM_MARKS("Marks with this Form: "),
	RS_FORM_DELETE_BY_ID("\nSelect Form id to delete from the list: "),
	RS_FORM_NEW_NAME("\nEnter new Form name: "),
	RS_FORM_UPDATE_BY_ID("\nSelect Form id to update from the list: "),

	RS_GROUP_LIST_HEADER("Group id,\tName,\tForm id,\tSpecialization id"),
	RS_GROUP_ID("\nEnter Group id: "),
	RS_GROUP_NAME("\nEnter Group name: "),
	RS_GROUP_STUDENTS("Students with this Group: "),
	RS_GROUP_SCHEDULE("Schedule with this Group: "),
	RS_GROUP_MARKS("Marks with this Group: "),
	RS_GROUP_FORM_ID("\nEnter Form id: "),
	RS_GROUP_SPEC_ID("\nEnter Specialization id: "),
	RS_GROUP_DELETE_BY_ID("\nSelect Group id to delete from the list: "),
	RS_GROUP_NEW_NAME("\nEnter new Group name: "),
	RS_GROUP_UPDATE_BY_ID("\nSelect Group id to update from the list: "),

	RS_MARK_LIST_HEADER("Mark id,\tMarkType id,\tTeacher id,\tStudent id,\tGroup id,\tSubject id,\tForm id,\tDate"),
	RS_MARK_ID("\nEnter Mark id: "),
	RS_MARK_MARKTYPE_ID("\nEnter MarkType id: "),
	RS_MARK_TEACHER_ID("\nEnter Teacher id: "),
	RS_MARK_STUDENT_ID("\nEnter Student id: "),
	RS_MARK_GROUP_ID("\nEnter Group id: "),
	RS_MARK_SUBJECT_ID("\nEnter Subject id: "),
	RS_MARK_FORM_ID("\nEnter Form id: "),
	RS_MARK_DATE("\nEnter date (in yyyy-MM-dd format): "),
	RS_MARK_DELETE_BY_ID("\nSelect Mark id to delete from the list: "),
	RS_MARK_NEW_DATE("Enter date (in yyyy-MM-dd format): "),
	RS_MARK_UPDATE_BY_ID("\nSelect Mark id to update from the list: "),

	RS_MARKTYPE_LIST_HEADER("MarkType id,\tName"),
	RS_MARKTYPE_ID("\nEnter MarkType id: "),
	RS_MARKTYPE_NAME("\nEnter MarkType name: "),
	RS_MARKTYPE_MARKS("Marks with this MarkType: "),
	RS_MARKTYPE_DELETE_BY_ID("\nSelect MarkType id to delete from the list: "),
	RS_MARKTYPE_NEW_NAME("\nEnter new MarkType name"),
	RS_MARKTYPE_UPDATE_BY_ID("\nSelect MarkType id to update from the list: "),

	RS_PERSON_LIST_HEADER("Person id,\tName,\tBirthday,\tPassport"),
	RS_PERSON_ID("\nEnter Person id: "),
	RS_PERSON_NAME("\nEnter Person name: "),
	RS_PERSON_BIRTHDAY("\nEnter Person birthday (in yyyy-MM-dd format): "),
	RS_PERSON_SELECT_PASSPORT("\nSelect Person passport (in ¿¡123456 format): "),
	RS_PERSON_DELETE_BY_ID("\nEnter Person id to delete from the list: "),
	RS_PERSON_UPDATE_BY_ID("\nSelect Person id to update from the list: "),

	RS_SCHEDULE_LIST_HEADER("Schedule id,\tSubject id,\tTeacher id,\tGroup id,\tDay,\tChisZnam,\tLenta"),
	RS_SCHEDULE_ID("\nEnter Schedule id: "),
	RS_SCHEDULE_SUBJECT_ID("\nEnter Subject id: "),
	RS_SCHEDULE_TEACHER_ID("\nEnter Teacher id: "),
	RS_SCHEDULE_GROUP_ID("\nEnter Group id: "),
	RS_SCHEDULE_DAY_HEADER("Id,\tDay"),
	RS_SCHEDULE_DAY("\nEnter Day id: "),
	RS_SCHEDULE_CHIS_ZNAM_HEADER("Id,\tChisZnam"),
	RS_SCHEDULE_CHIS_ZNAM("\nEnter ChisZnam id: "),
	RS_SCHEDULE_LENTA("\nEnter the number of the lesson: "),
	RS_SCHEDULE_DELETE_BY_ID("\nSelect Schedule id to delete from the list: "),
	RS_SCHEDULE_UPDATE_BY_ID("\nSelect Schedule id to update from the list: "),

	RS_SPECIALIZATION_LIST_HEADER("Specialization id,\tName"),
	RS_SPECIALIZATION_ID("\nEnter Specialization id: "),
	RS_SPECIALIZATION_NAME("\nEnter specialization name: "),
	RS_SPECIALIZATION_DELETE_BY_ID("\nSelect specialization id to delete from the list: "),
	RS_SPECIALIZATION_NEW_NAME("\nEnter new Specialization name: "),
	RS_SPECIALIZATION_UPDATE_BY_ID("\nSelect specialization id to update from the list: "),

	RS_STUDENT_LIST_HEADER("Student id,\tPerson id,\tGroup id,\tStart,\tFinish"),
	RS_STUDENT_ID("\nEnter Student id: "),
	RS_STUDENT_PERSON("\nEnter Person id: "),
	RS_STUDENT_GROUP("\nEnter Group id: "),
	RS_STUDENT_START("\nEnter the entrance date (in yyyy-MM-dd fromat): "),
	RS_STUDENT_FINISH("\nEnter the finish date (in yyyy-MM-dd format): "),
	RS_STUDENT_DELETE_BY_ID("\nSelect Student id to delete from the list: "),
	RS_STUDENT_UPDATE_BY_ID("\nSelect Student id to update from the list: "),

	RS_SUBJECT_LIST_HEADER("Subject id,\tName,\tSpecialization id"),
	RS_SUBJECT_ID("\nEnter Subject id: "),
	RS_SUBJECT_NAME("\nEnter Subject name: "),
	RS_SUBJECT_SPEC_ID("\nEnter Specialization id: "),
	RS_SUBJECT_DELETE_BY_ID("\nSelect Subject id to delete from the list: "),
	RS_SUBJECT_UPDATE_BY_ID("\nSelect Subject id to update from the list: "),

	RS_TEACHER_LIST_HEADER("Teacher id,\tPerson id,\tStart,\tFinish"),
	RS_TEACHER_ID("\nEnter Teacher id: "),
	RS_TEACHER_PERSON_ID("\nEnter Person id: "),
	RS_TEACHER_START("\nEnter the entrance date (in yyyy-MM-dd fromat): "),
	RS_TEACHER_FINISH("\nEnter the finish date (in yyyy-MM-dd format): "),
	RS_TEACHER_DELETE_BY_ID("\nSelect Teacher id to delete from the list: "),
	RS_TEACHER_UPDATE_BY_ID("\nSelect Teacher id to update from the list: ");


	private String message;

	ConsoleResultsMessages(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
