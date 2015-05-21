package org.mydomain.academy.main;


import org.apache.log4j.Logger;
import org.mydomain.academy.UI.UserInterface;
import org.mydomain.academy.UI.impl.ConsoleUserInterface;
import org.mydomain.academy.db.DAO.BasicInterface.*;
import org.mydomain.academy.db.DAO.impls.JDBC.*;
import org.mydomain.academy.db.utils.Bootstrap;
import org.mydomain.academy.db.utils.ConnectionManager;
import org.mydomain.academy.db.utils.SingleConnectionManager;
import org.mydomain.academy.db.utils.content.JDBCContentManager;
import org.mydomain.academy.services.ServiceInterface.*;
import org.mydomain.academy.services.impls.ServiceImpl.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.mydomain.academy.UI.console.messages.ConsoleMessages.MSG_CONNECTION_ERROR;
import static org.mydomain.academy.db.utils.LogMessages.*;

public class AcademyJDBCConsole {

	private static final Logger log = Logger.getLogger(AcademyJDBCConsole.class);

	private static final String PROPERTIES_FILE_PATH = "src/main/resources/jdbc.properties";

	private static ConnectionManager connectionManager;

	private static FormService formService;
	private static GroupService groupService;
	private static MarkTypeService markTypeService;
	private static MarkService markService;
	private static PersonService personService;
	private static ScheduleService scheduleService;
	private static SpecializationService specializationService;
	private static StudentService studentService;
	private static SubjectService subjectService;
	private static TeacherService teacherService;

	private static Map<String, RootService> rootServices;

	public static void main(String[] args) {
		log.info(LOG_MAIN_START);

		try {
			init();
			UserInterface userInterface = new ConsoleUserInterface(
					formService, groupService, markTypeService,
					markService, personService, scheduleService,
					specializationService, studentService,
					subjectService, teacherService);
			try {
				new Bootstrap(connectionManager).init();
				new JDBCContentManager(rootServices).fillDB();
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
		markService = new JDBCMarkService(markDAO);
		markTypeService = new JDBCMarkTypeService(markTypeDAO);
		personService = new JDBCPersonService(personDAO);
		scheduleService = new JDBCScheduleService(scheduleDAO);
		specializationService = new JDBCSpecializationService(specializationDAO);
		studentService = new JDBCStudentService(studentDAO);
		subjectService = new JDBCSubjectService(subjectDAO);
		teacherService = new JDBCTeacherService(teacherDAO);

		rootServices = new HashMap<>();

		rootServices.put("formService", formService);
		rootServices.put("groupService", groupService);
		rootServices.put("markService", markService);
		rootServices.put("markTypeService", markTypeService);
		rootServices.put("personService", personService);
		rootServices.put("scheduleService", scheduleService);
		rootServices.put("specializationService", specializationService);
		rootServices.put("studentService", studentService);
		rootServices.put("subjectService", subjectService);
		rootServices.put("teacherService", teacherService);
	}
}
