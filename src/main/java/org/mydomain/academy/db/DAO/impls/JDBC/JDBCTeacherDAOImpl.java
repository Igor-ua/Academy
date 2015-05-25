package org.mydomain.academy.db.DAO.impls.JDBC;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.TeacherDAO;
import org.mydomain.academy.db.entities.Teacher;
import org.mydomain.academy.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCTeacherDAOImpl implements TeacherDAO {

	private static final Logger log = Logger.getLogger(JDBCTeacherDAOImpl.class);

	private ConnectionManager connectionManager;

	public JDBCTeacherDAOImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public Teacher findTeacherById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + TEACHER + " WHERE " +
							TeacherColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Teacher teacher = new Teacher();
			while (resultSet.next()) {
				teacher = makeTeacher(resultSet);
			}
			return teacher;
		} catch (SQLException e) {
			log.error(e);

		}
		return null;
	}

	@Override
	public List<Teacher> findAllTeachers() {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TEACHER);
			while (resultSet.next()) {
				teachers.add(makeTeacher(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return teachers;
	}

	@Override
	public List<Teacher> findTeacherByPersonId(long id) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + TEACHER + " WHERE " +
							TeacherColumns.PERSON_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				teachers.add(makeTeacher(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return teachers;
	}

	@Override
	public List<Teacher> findTeacherByStart(Date start) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + TEACHER + " WHERE " +
							TeacherColumns.START + " = ?");
			preparedStatement.setDate(1, new java.sql.Date(start.getTime()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				teachers.add(makeTeacher(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return teachers;
	}

	@Override
	public List<Teacher> findTeacherByFinish(Date finish) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + TEACHER + " WHERE " +
							TeacherColumns.FINISH + " = ?");
			preparedStatement.setDate(1, new java.sql.Date(finish.getTime()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				teachers.add(makeTeacher(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return teachers;
	}

	@Override
	public boolean deleteTeacher(Teacher teacher) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + TEACHER + " WHERE " +
					TeacherColumns.ID + " = ?");
			ps.setString(1, String.valueOf(teacher.getId()));
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
	public boolean saveTeacher(Teacher teacher) {
		Boolean update = teacher.getId() != 0;
		String query = update ?
				"UPDATE " + TEACHER + " SET " +
						TeacherColumns.PERSON_ID + " = ?, " +
						TeacherColumns.START + " = ?, " +
						TeacherColumns.FINISH + " = ? WHERE " +
						TeacherColumns.ID + " = ?" :
				"INSERT INTO " + TEACHER + " VALUES (NULL,?,?,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, teacher.getPerson_id());
			ps.setDate(2, new java.sql.Date(teacher.getStart().getTime()));
			ps.setDate(3, new java.sql.Date(teacher.getFinish().getTime()));
			if (update) {
				ps.setLong(4, teacher.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				teacher.setId(resultSet.getLong(1));
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
	public List<Teacher> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}

	private Teacher makeTeacher(ResultSet resultSet) throws SQLException {
		Teacher teacher = new Teacher(resultSet.getLong(2), resultSet.getDate(3), resultSet.getDate(4));
		teacher.setId(resultSet.getLong(1));
		return teacher;
	}
}
