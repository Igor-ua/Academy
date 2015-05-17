package org.mydomain.academy.db.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mydomain.academy.db.utils.LogMessages.*;

//can't find @SuppressWarnings value for datasource/sql dialect. Using -all.
//Only once, only here.
@SuppressWarnings("all")
public class Bootstrap {

	private static final Logger log = Logger.getLogger(Bootstrap.class);

	private ConnectionManager connectionManager;
	private String dbName;
	private String CREATE_DB;

	public Bootstrap(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		try {
			this.dbName = ((SingleConnectionManager) connectionManager).getDbName();
			CREATE_DB = "CREATE DATABASE IF NOT EXISTS " + dbName;
		} catch (ClassCastException e) {
			log.error(e);
		}

		log.info(LOG_BOOTSTRAP_INIT);
	}

	private static final String CREATE_PERSON_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.PERSON + " (" +
					TableNames.PersonColumns.ID + " SERIAL, " +
					TableNames.PersonColumns.NAME + " VARCHAR(100) NOT NULL, " +
					TableNames.PersonColumns.BIRTHDAY + " Date NOT NULL, " +
					TableNames.PersonColumns.PASSPORT + " VARCHAR(8) NOT NULL, " +
					"UNIQUE KEY Person_Passport (" + TableNames.PersonColumns.PASSPORT + "), " +
					"PRIMARY KEY(" + TableNames.PersonColumns.ID + "))";

	private static final String CREATE_FORM_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.FORM + " (" +
					TableNames.FormColumns.ID + " SERIAL, " +
					TableNames.FormColumns.NAME + " VARCHAR(100) NOT NULL, " +
					"UNIQUE KEY Forms_Name (" + TableNames.FormColumns.NAME + "), " +
					"PRIMARY KEY(" + TableNames.FormColumns.ID + "))";

	private static final String CREATE_GROUP_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.GROUP + " (" +
					TableNames.GroupColumns.ID + " SERIAL, " +
					TableNames.GroupColumns.NAME + " VARCHAR(100) NOT NULL, " +
					TableNames.GroupColumns.FORM_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.GroupColumns.SPECIALIZATION_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					"UNIQUE KEY Group_Name (" + TableNames.GroupColumns.NAME + "), " +
					"PRIMARY KEY(" + TableNames.GroupColumns.ID + "), " +
					"FOREIGN KEY(" + TableNames.GroupColumns.FORM_ID + ") REFERENCES " +
					TableNames.FORM + "(" + TableNames.FormColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.GroupColumns.SPECIALIZATION_ID + ") REFERENCES " +
					TableNames.SPECIALIZATION + "(" + TableNames.SpecializationsColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE)";

	private static final String CREATE_MARKTYPE_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.MARK_TYPE + " (" +
					TableNames.MarkTypeColumns.ID + " SERIAL, " +
					TableNames.MarkTypeColumns.DESCRIPTION + " VARCHAR(100) NOT NULL, " +
					"UNIQUE KEY MarkType_Description (" + TableNames.MarkTypeColumns.DESCRIPTION + "), " +
					"PRIMARY KEY(" + TableNames.MarkTypeColumns.ID + "))";

	private static final String CREATE_TEACHER_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.TEACHER + " (" +
					TableNames.TeacherColumns.ID + " SERIAL, " +
					TableNames.TeacherColumns.PERSON_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.TeacherColumns.START + " date NOT NULL, " +
					TableNames.TeacherColumns.FINISH + " date NOT NULL, " +
					"PRIMARY KEY(" + TableNames.TeacherColumns.ID + "), " +
					"FOREIGN KEY(" + TableNames.TeacherColumns.PERSON_ID + ") REFERENCES " +
					TableNames.PERSON + "(" + TableNames.PersonColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE)";

	private static final String CREATE_STUDENT_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.STUDENT + " (" +
					TableNames.StudentColumns.ID + " SERIAL, " +
					TableNames.StudentColumns.PERSON_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.StudentColumns.GROUP_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.StudentColumns.START + " date NOT NULL, " +
					TableNames.StudentColumns.FINISH + " date NOT NULL, " +
					"CONSTRAINT UQ_PersonID_GroupID UNIQUE (" + TableNames.StudentColumns.PERSON_ID +
					", " + TableNames.StudentColumns.GROUP_ID + "), " +
					"PRIMARY KEY(" + TableNames.StudentColumns.ID + "), " +
					"FOREIGN KEY(" + TableNames.StudentColumns.PERSON_ID + ") REFERENCES " +
					TableNames.PERSON + "(" + TableNames.PersonColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.StudentColumns.GROUP_ID + ") REFERENCES " +
					TableNames.GROUP + "(" + TableNames.GroupColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE)";

	private static final String CREATE_SPECIALIZATION_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.SPECIALIZATION + " (" +
					TableNames.SpecializationsColumns.ID + " SERIAL, " +
					TableNames.SpecializationsColumns.NAME + " VARCHAR(100) NOT NULL, " +
					"UNIQUE KEY Specializations_Name (" + TableNames.SpecializationsColumns.NAME + "), " +
					"PRIMARY KEY(" + TableNames.SpecializationsColumns.ID + "))";

	private static final String CREATE_SUBJECT_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.SUBJECT + " (" +
					TableNames.SubjectColumns.ID + " SERIAL, " +
					TableNames.SubjectColumns.NAME + " VARCHAR(100) NOT NULL, " +
					TableNames.SubjectColumns.SPECIALIZATION_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					"CONSTRAINT UQ_SubjectName_SpecializationID UNIQUE (" + TableNames.SubjectColumns.NAME +
					", " + TableNames.SubjectColumns.SPECIALIZATION_ID + "), " +
					"PRIMARY KEY(" + TableNames.SubjectColumns.ID + "), " +
					"FOREIGN KEY(" + TableNames.SubjectColumns.SPECIALIZATION_ID + ") REFERENCES " +
					TableNames.SPECIALIZATION + "(" + TableNames.SpecializationsColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE)";

	private static final String CREATE_SCHEDULE_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.SCHEDULE + " (" +
					TableNames.ScheduleColumns.ID + " SERIAL, " +
					TableNames.ScheduleColumns.SUBJECT_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.ScheduleColumns.TEACHER_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.ScheduleColumns.GROUP_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.ScheduleColumns.SCHEDULE_DAY +
					" enum('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL, " +
					TableNames.ScheduleColumns.CHIS_ZNAM + " set('CH', 'ZN') NOT NULL, " +
					TableNames.ScheduleColumns.LENTA + " tinyint(1) unsigned NOT NULL, " +
					"PRIMARY KEY(" + TableNames.ScheduleColumns.ID + "), " +
					"FOREIGN KEY(" + TableNames.ScheduleColumns.SUBJECT_ID + ") REFERENCES " +
					TableNames.SUBJECT + "(" + TableNames.SubjectColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.ScheduleColumns.TEACHER_ID + ") REFERENCES " +
					TableNames.TEACHER + "(" + TableNames.TeacherColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.ScheduleColumns.GROUP_ID + ") REFERENCES " +
					TableNames.GROUP + "(" + TableNames.GroupColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE)";

	private static final String CREATE_MARK_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.MARK + " (" +
					TableNames.MarkColumns.ID + " SERIAL, " +
					TableNames.MarkColumns.MARK_TYPE_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.MarkColumns.TEACHER_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.MarkColumns.STUDENT_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.MarkColumns.GROUP_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.MarkColumns.SUBJECT_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.MarkColumns.FORM_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.MarkColumns.DATE + " date NOT NULL, " +
					"PRIMARY KEY(" + TableNames.MarkColumns.ID + "), " +
					"FOREIGN KEY(" + TableNames.MarkColumns.MARK_TYPE_ID + ") REFERENCES " +
					TableNames.MARK_TYPE + "(" + TableNames.MarkTypeColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.MarkColumns.TEACHER_ID + ") REFERENCES " +
					TableNames.TEACHER + "(" + TableNames.TeacherColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.MarkColumns.STUDENT_ID + ") REFERENCES " +
					TableNames.STUDENT + "(" + TableNames.StudentColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.MarkColumns.GROUP_ID + ") REFERENCES " +
					TableNames.GROUP + "(" + TableNames.GroupColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.MarkColumns.SUBJECT_ID + ") REFERENCES " +
					TableNames.SUBJECT + "(" + TableNames.SubjectColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.MarkColumns.FORM_ID + ") REFERENCES " +
					TableNames.FORM + "(" + TableNames.FormColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE)";

	private static final String CREATE_TEACHERS_SUBJECTS_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TableNames.TEACHER_SUBJECT + " (" +
					TableNames.Teacher_SubjectColumns.ID + " SERIAL, " +
					TableNames.Teacher_SubjectColumns.TEACHER_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					TableNames.Teacher_SubjectColumns.SUBJECT_ID + " BIGINT(20) UNSIGNED NOT NULL, " +
					"PRIMARY KEY(" + TableNames.Teacher_SubjectColumns.ID + "), " +
					"FOREIGN KEY(" + TableNames.Teacher_SubjectColumns.TEACHER_ID + ") REFERENCES " +
					TableNames.TEACHER + "(" + TableNames.TeacherColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE, " +
					"FOREIGN KEY(" + TableNames.Teacher_SubjectColumns.SUBJECT_ID + ") REFERENCES " +
					TableNames.SUBJECT + "(" + TableNames.SubjectColumns.ID + ") " +
					"ON DELETE RESTRICT ON UPDATE CASCADE)";


	public void init() throws SQLException {
		log.info(LOG_BOOTSTRAP_START);

		Connection c = connectionManager.getConnection();
		Statement statement = c.createStatement();

		statement.execute(CREATE_DB);

		statement.executeUpdate(CREATE_PERSON_TABLE);
		statement.executeUpdate(CREATE_FORM_TABLE);
		statement.executeUpdate(CREATE_SPECIALIZATION_TABLE);
		statement.executeUpdate(CREATE_GROUP_TABLE);
		statement.executeUpdate(CREATE_MARKTYPE_TABLE);
		statement.executeUpdate(CREATE_TEACHER_TABLE);
		statement.executeUpdate(CREATE_STUDENT_TABLE);
		statement.executeUpdate(CREATE_SUBJECT_TABLE);
		statement.executeUpdate(CREATE_SCHEDULE_TABLE);
		statement.executeUpdate(CREATE_MARK_TABLE);
		statement.executeUpdate(CREATE_TEACHERS_SUBJECTS_TABLE);

		log.info(LOG_BOOTSTRAP_FINISH);
	}

	public void checkDB() throws SQLException {
		Connection c = connectionManager.getConnection();
		Statement statement = c.createStatement();
		statement.executeUpdate(CREATE_DB);
	}
}
