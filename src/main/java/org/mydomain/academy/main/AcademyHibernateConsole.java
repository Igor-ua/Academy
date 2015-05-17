package org.mydomain.academy.main;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.mydomain.academy.UI.UserInterface;
import org.mydomain.academy.UI.impl.ConsoleUserInterface;
import org.mydomain.academy.db.DAO.BasicInterface.*;
import org.mydomain.academy.db.DAO.impls.hibernate.*;
import org.mydomain.academy.db.utils.HibernateSettings;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.mydomain.academy.services.ServiceInterface.*;
import org.mydomain.academy.services.impls.ServiceImpl.*;

import java.util.logging.Level;

import static org.mydomain.academy.UI.console.messages.ConsoleMessages.*;
import static org.mydomain.academy.db.H2Server.H2ConsoleServer.*;
import static org.mydomain.academy.db.utils.LogMessages.*;

public class AcademyHibernateConsole {

	private static final Logger log = Logger.getLogger(AcademyHibernateConsole.class);

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

	public static void main(String[] args) {

		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		log.info(LOG_MAIN_START);
		log.info(LOG_MAIN_FINISH_DIVIDER);

		Integer port = Integer.parseInt(HibernateSettings.getPort());

		if (startServer(port)) {
			try (HibernateUtils hibernateUtils = new HibernateUtils()) {
				init(hibernateUtils);

				UserInterface userInterface = new ConsoleUserInterface(
						formService, groupService, markTypeService,
						markService, personService, scheduleService,
						specializationService, studentService,
						subjectService, teacherService);

				userInterface.run();

			} catch (HibernateException e) {
				System.err.println(MSG_CONNECTION_ERROR);
				log.error(LOG_MAIN_CON_ERROR.toString() + e);
			} finally {
				stopServer(getAvailablePort());
				stopServer(port);
				log.info(LOG_MAIN_FINISH_SUCCESS);
				log.info(LOG_MAIN_FINISH_DIVIDER);
			}
		}
	}

	private static void init(HibernateUtils hibernateUtils) {

		FormDAO formDAO = new HibernateFormDAOImpl(hibernateUtils);
		GroupDAO groupDAO = new HibernateGroupDAOImpl(hibernateUtils);
		MarkTypeDAO markTypeDAO = new HibernateMarkTypeDAOImpl(hibernateUtils);
		MarkDAO markDAO = new HibernateMarkDAOImpl(hibernateUtils);
		PersonDAO personDAO = new HibernatePersonDAOImpl(hibernateUtils);
		ScheduleDAO scheduleDAO = new HibernateScheduleDAOImpl(hibernateUtils);
		SpecializationDAO specializationDAO = new HibernateSpecializationDAOImpl(hibernateUtils);
		StudentDAO studentDAO = new HibernateStudentDAOImpl(hibernateUtils);
		SubjectDAO subjectDAO = new HibernateSubjectDAOImpl(hibernateUtils);
		TeacherDAO teacherDAO = new HibernateTeacherDAOImpl(hibernateUtils);

		formService = new JDBCFormService(formDAO);
		groupService = new JDBCGroupService(groupDAO);
		markTypeService = new JDBCMarkTypeService(markTypeDAO);
		markService = new JDBCMarkService(markDAO);
		personService = new JDBCPersonService(personDAO);
		scheduleService = new JDBCScheduleService(scheduleDAO);
		specializationService = new JDBCSpecializationService(specializationDAO);
		studentService = new JDBCStudentService(studentDAO);
		subjectService = new JDBCSubjectService(subjectDAO);
		teacherService = new JDBCTeacherService(teacherDAO);
	}
}
