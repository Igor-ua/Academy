package org.mydomain.academy.UI.console.messages;

public enum ConsoleMessages {

	MSG_GREETING("[!] Welcome into 'Academy' database."),
	MSG_INPUT_ERROR("[x] Input error.\n"),
	MSG_ABNORMAL_EXIT("[x] Abnormal exit"),
	MSG_CONNECTION_ERROR("Error. Check your connection properties!"),
	MSG_INVALID_PORT("Invalid port number"),

	MSG_SELECT_ACTION("\n[?] Select an action: "),
	MSG_CHOOSE_FORM("1.  Form"),
	MSG_CHOOSE_GROUP("2.  Group"),
	MSG_CHOOSE_MARK("3.  Mark"),
	MSG_CHOOSE_MARK_TYPE("4.  MarkType"),
	MSG_CHOOSE_PERSON("5.  Person"),
	MSG_CHOOSE_SCHEDULE("6.  Schedule"),
	MSG_CHOOSE_SPECIALIZATION("7.  Specialization"),
	MSG_CHOOSE_STUDENT("8.  Student"),
	MSG_CHOOSE_SUBJECT("9.  Subject"),
	MSG_CHOOSE_TEACHER("10. Teacher"),
	MSG_SELECT_ACTION_EXIT("0.  Exit from database"),

	MSG_FORM_ACTION("\n[?] Select an action for Form: "),
	MSG_FORM_SHOW_ALL_FORMS("1.  Show all Forms"),
	MSG_FORM_FIND_FORM_BY_ID("2.  Find Form by ID"),
	MSG_FORM_FIND_FORM_BY_NAME("3.  Find Form by name"),
	MSG_FORM_DELETE_FORM("4.  Delete a Form by ID"),
	MSG_FORM_ADD_NEW_FORM("5.  Add new Form"),
	MSG_FORM_UPDATE_FORM("6.  Update a Form"),
	MSG_FORM_EXIT("0.  Exit from Form"),

	MSG_GROUP_ACTION("\n[?] Select an action for Group: "),
	MSG_GROUP_SHOW_ALL_GROUPS("1.  Show all Groups"),
	MSG_GROUP_FIND_GROUP_BY_ID("2.  Find Group by ID"),
	MSG_GROUP_FIND_GROUP_BY_NAME("3.  Find Group by Name"),
	MSG_GROUP_FIND_GROUP_BY_FORM_ID("4.  Find Group by Form ID"),
	MSG_GROUP_FIND_GROUP_BY_SPEC_ID("5.  Find Group by Specialization ID"),
	MSG_GROUP_DELETE_GROUP("6.  Delete a Group by ID"),
	MSG_GROUP_ADD_NEW_GROUP("7.  Add new Group"),
	MSG_GROUP_UPDATE_GROUP("8.  Update a Group"),
	MSG_GROUP_EXIT("0.  Exit from Group"),

	MSG_MARK_ACTION("\n[?] Select an action for Mark: "),
	MSG_MARK_SHOW_ALL_MARKS("1.  Show all Marks"),
	MSG_MARK_FIND_MARK_BY_ID("2.  Find Mark by ID"),
	MSG_MARK_FIND_MARK_BY_MARKTYPE_ID("3.  Find Mark by MarkType ID"),
	MSG_MARK_FIND_MARK_BY_TEACHER_ID("4.  Find Mark by Teacher ID"),
	MSG_MARK_FIND_MARK_BY_STUDENT_ID("5.  Find Mark by Student ID"),
	MSG_MARK_FIND_MARK_BY_GROUP_ID("6.  Find Mark by Group ID"),
	MSG_MARK_FIND_MARK_BY_SUBJECT_ID("7.  Find Mark by Subject ID"),
	MSG_MARK_FIND_MARK_BY_FORM_ID("8.  Find Mark by Form ID"),
	MSG_MARK_FIND_MARK_BY_DATE("9.  Find Mark by Date"),
	MSG_MARK_DELETE_MARK("10. Delete a Mark by ID"),
	MSG_MARK_ADD_NEW_MARK("11. Add new Mark"),
	MSG_MARK_UPDATE_MARK("12. Update a Mark"),
	MSG_MARK_EXIT("0.  Exit from Mark"),

	MSG_MARKTYPE_ACTION("\n[?] Select an action for MarkType: "),
	MSG_MARKTYPE_SHOW_ALL_MARKTYPES("1.  Show all MarkTypes"),
	MSG_MARKTYPE_FIND_MARKTYPES_BY_ID("2.  Find MarkType by ID"),
	MSG_MARKTYPE_FIND_MARKTYPES_BY_NAME("3.  Find MarkType by Name"),
	MSG_MARKTYPE_DELETE_MARKTYPE("4.  Delete a MarkType by ID"),
	MSG_MARKTYPE_ADD_NEW_MARKTYPE("5.  Add new MarkType"),
	MSG_MARKTYPE_UPDATE_MARKTYPE("6.  Update a MarkType"),
	MSG_MARKTYPE_EXIT("0.  Exit from MarkType"),

	MSG_PERSON_ACTION("\n[?] Select an action for Person: "),
	MSG_PERSON_SHOW_ALL_PERSONS("1.  Show all Persons"),
	MSG_PERSON_FIND_PERSON_BY_ID("2.  Find Person by ID"),
	MSG_PERSON_FIND_PERSON_BY_NAME("3.  Find Person by Name"),
	MSG_PERSON_FIND_PERSON_BY_PASSPORT("4.  Find Person by Passport"),
	MSG_PERSON_FIND_PERSON_BY_BIRTHDAY("5.  Find Person by Birthday"),
	MSG_PERSON_DELETE_PERSON("6.  Delete a Person by ID"),
	MSG_PERSON_ADD_NEW_PERSON("7.  Add new Person"),
	MSG_PERSON_UPDATE_PERSON("8.  Update a Person"),
	MSG_PERSON_EXIT("0.  Exit from Person"),

	MSG_SCHEDULE_ACTION("\n[?] Select an action for Schedule: "),
	MSG_SCHEDULE_SHOW_ALL_SCHEDULES("1.  Show all Schedules"),
	MSG_SCHEDULE_FIND_SCHEDULE_BY_ID("2.  Find Schedule by ID"),
	MSG_SCHEDULE_FIND_SCHEDULE_BY_SUBJECT_ID("3.  Find Schedule by Subject ID"),
	MSG_SCHEDULE_FIND_SCHEDULE_BY_TEACHER_ID("4.  Find Schedule by Teacher ID"),
	MSG_SCHEDULE_FIND_SCHEDULE_BY_GROUP_ID("5.  Find Schedule by Group ID"),
	MSG_SCHEDULE_FIND_SCHEDULE_BY_DAY("6.  Find Schedule by Day"),
	MSG_SCHEDULE_FIND_SCHEDULE_BY_CHIS_ZNAM("7.  Find Schedule by ChisZnam"),
	MSG_SCHEDULE_FIND_SCHEDULE_BY_LENTA("8.  Find Schedule by Lenta"),
	MSG_SCHEDULE_DELETE_SCHEDULE("9. Delete a Schedule by ID"),
	MSG_SCHEDULE_ADD_NEW_SCHEDULE("10. Add new Schedule"),
	MSG_SCHEDULE_UPDATE_SCHEDULE("11. Update a Schedule"),
	MSG_SCHEDULE_EXIT("0.  Exit from Schedule"),

	MSG_SPECIALIZATION_ACTION("\n[?] Select an action for Specialization: "),
	MSG_SPECIALIZATION_SHOW_ALL_SPECS("1.  Show all Specializations"),
	MSG_SPECIALIZATION_FIND_SPEC_BY_ID("2.  Find Specialization by ID"),
	MSG_SPECIALIZATION_FIND_SPEC_BY_NAME("3.  Find Specialization by Name"),
	MSG_SPECIALIZATION_DELETE_SPEC("4.  Delete a Specialization by ID"),
	MSG_SPECIALIZATION_ADD_NEW_SPEC("5.  Add new Specialization"),
	MSG_SPECIALIZATION_UPDATE_SPEC("6.  Update a Specialization"),
	MSG_SPECIALIZATION_EXIT("0.  Exit from Specialization"),

	MSG_STUDENT_ACTION("\n[?] Select an action for Student: "),
	MSG_STUDENT_SHOW_ALL_STUDENTS("1.  Show all Students"),
	MSG_STUDENT_FIND_STUDENT_BY_ID("2.  Find Student by ID"),
	MSG_STUDENT_FIND_STUDENT_BY_PERSON_ID("3.  Find Student by Person ID"),
	MSG_STUDENT_FIND_STUDENT_BY_GROUP_ID("4.  Find Student by Group ID"),
	MSG_STUDENT_FIND_STUDENT_BY_START("5.  Find Student by Start"),
	MSG_STUDENT_FIND_STUDENT_BY_FINISH("6.  Find Student by Finish"),
	MSG_STUDENT_DELETE_STUDENT("7.  Delete a Student by ID"),
	MSG_STUDENT_ADD_NEW_STUDENT("8.  Add new Student"),
	MSG_STUDENT_UPDATE_STUDENT("9.  Update a Student"),
	MSG_STUDENT_EXIT("0.  Exit from Student"),

	MSG_SUBJECT_ACTION("\n[?] Select an action for Subject: "),
	MSG_SUBJECT_SHOW_ALL_SUBJECTS("1.  Show all Subjects"),
	MSG_SUBJECT_FIND_SUBJECT_BY_ID("2.  Find Subject by ID"),
	MSG_SUBJECT_FIND_SUBJECT_BY_SPEC("3.  Find Subject by Specialization ID"),
	MSG_SUBJECT_FIND_SUBJECT_BY_NAME("4.  Find Subject by Name"),
	MSG_SUBJECT_DELETE_SUBJECT("5.  Delete a Subject by ID"),
	MSG_SUBJECT_ADD_NEW_SUBJECT("6.  Add new Subject"),
	MSG_SUBJECT_UPDATE_SUBJECT("7.  Update a Subject"),
	MSG_SUBJECT_EXIT("0.  Exit from Subject"),

	MSG_TEACHER_ACTION("\n[?] Select an action for Teacher: "),
	MSG_TEACHER_SHOW_ALL_TEACHERS("1.  Show all Teachers"),
	MSG_TEACHER_FIND_TEACHER_BY_ID("2.  Find Teacher by ID"),
	MSG_TEACHER_FIND_TEACHER_BY_PERSON_ID("3.  Find Teacher by Person ID"),
	MSG_TEACHER_FIND_TEACHER_BY_START("4.  Find Teacher by Start"),
	MSG_TEACHER_FIND_TEACHER_BY_FINISH("5.  Find Teacher by Finish"),
	MSG_TEACHER_DELETE_TEACHER("6.  Delete a Teacher by ID"),
	MSG_TEACHER_ADD_NEW_TEACHER("7.  Add new Teacher"),
	MSG_TEACHER_UPDATE_TEACHER("8.  Update a Teacher"),
	MSG_TEACHER_EXIT("0.  Exit from Teacher");

	private String message;

	ConsoleMessages(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}