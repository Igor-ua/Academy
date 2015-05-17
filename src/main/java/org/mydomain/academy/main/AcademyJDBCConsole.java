package org.mydomain.academy.main;


import org.apache.log4j.Logger;
import org.mydomain.academy.UI.UserInterface;
import org.mydomain.academy.UI.impl.ConsoleUserInterface;
import org.mydomain.academy.db.DAO.BasicInterface.*;
import org.mydomain.academy.db.DAO.impls.JDBC.*;
import org.mydomain.academy.db.utils.Bootstrap;
import org.mydomain.academy.db.utils.ConnectionManager;
import org.mydomain.academy.db.utils.SingleConnectionManager;
import org.mydomain.academy.services.ServiceInterface.*;
import org.mydomain.academy.services.impls.ServiceImpl.*;

import java.sql.SQLException;

import static org.mydomain.academy.UI.console.messages.ConsoleMessages.MSG_CONNECTION_ERROR;
import static org.mydomain.academy.db.utils.LogMessages.*;

public class AcademyJDBCConsole {

	private static final Logger log = Logger.getLogger(AcademyJDBCConsole.class);

	private static final String PROPERTIES_FILE_PATH = "src/main/resources/jdbc.properties";

	private static ConnectionManager connectionManager;

	private static FormService formService;
	private static GroupService groupService;
	private static MarkTypeService mark_typeService;
	private static MarkService markService;
	private static PersonService personService;
	private static ScheduleService scheduleService;
	private static SpecializationService specializationService;
	private static StudentService studentService;
	private static SubjectService subjectService;
	private static TeacherService teacherService;

	public static void main(String[] args) {
		log.info(LOG_MAIN_START);

		try {
			init();
			UserInterface userInterface = new ConsoleUserInterface(
					formService, groupService, mark_typeService,
					markService, personService, scheduleService,
					specializationService, studentService,
					subjectService, teacherService);
			try {
				new Bootstrap(connectionManager).init();
				userInterface.run();
			} catch (SQLException e) {
				System.err.println(MSG_CONNECTION_ERROR);
				log.error(LOG_MAIN_CON_ERROR.toString() + e);
			}
		} finally {
			try {
				connectionManager.close();
				log.info(LOG_MAIN_FINISH_SUCCESS);
				log.info(LOG_MAIN_FINISH_DIVIDER);
			} catch (SQLException e) {
				log.error(e);
				log.info(LOG_MAIN_FINISH_ERROR);
				log.info(LOG_MAIN_FINISH_DIVIDER);
			}
		}
	}

	private static void init() {

		connectionManager = new SingleConnectionManager(PROPERTIES_FILE_PATH);

		FormDAO formDAO = new JDBCFormDAOImpl(connectionManager);
		GroupDAO groupDAO = new JDBCGroupDAOImpl(connectionManager);
		MarkTypeDAO markTypeDAO = new JDBCMarkTypeDAOImpl(connectionManager);
		MarkDAO markDAO = new JDBCMarkDAOImpl(connectionManager);
		PersonDAO personDAO = new JDBCPersonDAOImpl(connectionManager);
		ScheduleDAO scheduleDAO = new JDBCScheduleDAOImpl(connectionManager);
		SpecializationDAO specializationDAO = new JDBCSpecializationDAOImpl(connectionManager);
		StudentDAO studentDAO = new JDBCStudentDAOImpl(connectionManager);
		SubjectDAO subjectDAO = new JDBCSubjectDAOImpl(connectionManager);
		TeacherDAO teacherDAO = new JDBCTeacherDAOImpl(connectionManager);

		formService = new JDBCFormService(formDAO);
		groupService = new JDBCGroupService(groupDAO);
		mark_typeService = new JDBCMarkTypeService(markTypeDAO);
		markService = new JDBCMarkService(markDAO);
		personService = new JDBCPersonService(personDAO);
		scheduleService = new JDBCScheduleService(scheduleDAO);
		specializationService = new JDBCSpecializationService(specializationDAO);
		studentService = new JDBCStudentService(studentDAO);
		subjectService = new JDBCSubjectService(subjectDAO);
		teacherService = new JDBCTeacherService(teacherDAO);
	}
}
