package org.mydomain.academy.db.DAO.impls.JDBC;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.SubjectDAO;
import org.mydomain.academy.db.entities.Mark;
import org.mydomain.academy.db.entities.Schedule;
import org.mydomain.academy.db.entities.Subject;
import org.mydomain.academy.db.entities.Teacher;
import org.mydomain.academy.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCSubjectDAOImpl implements SubjectDAO {

	private static final Logger log = Logger.getLogger(JDBCSubjectDAOImpl.class);

	private ConnectionManager connectionManager;

	public JDBCSubjectDAOImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public Subject findSubjectById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SUBJECT + " WHERE " +
							SubjectColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Subject subject = new Subject();
			while (resultSet.next()) {
				subject = makeSubject(resultSet);
			}

			return subject;
		} catch (SQLException e) {
			log.error(e);

		}
		return null;
	}

	@Override
	public List<Subject> findAllSubjects() {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + SUBJECT);
			while (resultSet.next()) {
				subjects.add(makeSubject(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return subjects;
	}

	@Override
	public List<Subject> findSubjectBySpecializationId(long id) {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SUBJECT + " WHERE " +
							SubjectColumns.SPECIALIZATION_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				subjects.add(makeSubject(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return subjects;
	}

	@Override
	public List<Subject> findSubjectByName(String name) {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SUBJECT + " WHERE " +
							SubjectColumns.NAME + " = ?");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				subjects.add(makeSubject(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return subjects;
	}

	@Override
	public boolean deleteSubject(Subject subject) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + SUBJECT + " WHERE " +
					SubjectColumns.ID + " = ?");
			ps.setString(1, String.valueOf(subject.getId()));
			int rows = ps.executeUpdate();
			if (rows != 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	public boolean saveSubject(Subject subject) {
		Boolean update = subject.getId() != 0;
		String query = update ?
				"UPDATE " + SUBJECT + " SET " +
						SubjectColumns.NAME + " = ?, " +
						SubjectColumns.SPECIALIZATION_ID + " = ? WHERE " +
						SubjectColumns.ID + " = ?" :
				"INSERT INTO " + SUBJECT + " VALUES (NULL,?,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, subject.getName());
			ps.setLong(2, subject.getSpecialization_id());
			if (update) {
				ps.setLong(3, subject.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				subject.setId(resultSet.getLong(1));
			}
			if (rows != 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return false;
	}

	@Override
	public List<Subject> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}

	private Subject makeSubject(ResultSet resultSet) throws SQLException {
		Subject subject = new Subject(resultSet.getString(2), resultSet.getLong(3));
		subject.setId(resultSet.getLong(1));
		return subject;
	}

	private Teacher makeTeacher(ResultSet resultSet) throws SQLException {
		Teacher teacher = new Teacher(resultSet.getLong(2), resultSet.getDate(3), resultSet.getDate(4));
		teacher.setId(resultSet.getLong(1));
		return teacher;
	}

	private Mark makeMark(ResultSet resultSet) throws SQLException {
		Mark mark = new Mark(resultSet.getLong(2), resultSet.getLong(3), resultSet.getLong(4), resultSet.getLong(5),
				resultSet.getLong(6), resultSet.getLong(7), resultSet.getDate(8));
		mark.setId(resultSet.getLong(1));
		return mark;
	}

	private Schedule makeSchedule(ResultSet resultSet) throws SQLException {
		Schedule schedule = new Schedule(resultSet.getLong(2), resultSet.getLong(3), resultSet.getLong(4),
				resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
		schedule.setId(resultSet.getLong(1));
		return schedule;
	}
}
