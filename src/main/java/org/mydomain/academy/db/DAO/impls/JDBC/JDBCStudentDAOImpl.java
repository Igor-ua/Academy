package org.mydomain.academy.db.DAO.impls.JDBC;


import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.StudentDAO;
import org.mydomain.academy.db.entities.Mark;
import org.mydomain.academy.db.entities.Student;
import org.mydomain.academy.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCStudentDAOImpl implements StudentDAO {

	private static final Logger log = Logger.getLogger(JDBCStudentDAOImpl.class);

	private ConnectionManager connectionManager;

	public JDBCStudentDAOImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public Student findStudentById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + STUDENT + " WHERE " +
							StudentColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Student student = new Student();
			while (resultSet.next()) {
				student = makeStudent(resultSet);
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.STUDENT_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				student.addMark(makeMark(resultSet));
			}

			return student;
		} catch (SQLException e) {
			log.error(e);

		}
		return null;
	}

	@Override
	public List<Student> findAllStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + STUDENT);
			while (resultSet.next()) {
				students.add(makeStudent(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return students;
	}

	@Override
	public List<Student> findStudentByPersonId(long id) {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + STUDENT + " WHERE " +
							StudentColumns.PERSON_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				students.add(makeStudent(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return students;
	}

	@Override
	public List<Student> findStudentByGroupId(long id) {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + STUDENT + " WHERE " +
							StudentColumns.GROUP_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				students.add(makeStudent(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return students;
	}

	@Override
	public List<Student> findStudentByStart(Date start) {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + STUDENT + " WHERE " +
							StudentColumns.START + " = ?");
			preparedStatement.setDate(1, new java.sql.Date(start.getTime()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				students.add(makeStudent(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return students;
	}

	@Override
	public List<Student> findStudentByFinish(Date finish) {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + STUDENT + " WHERE " +
							StudentColumns.FINISH + " = ?");
			preparedStatement.setDate(1, new java.sql.Date(finish.getTime()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				students.add(makeStudent(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return students;
	}

	@Override
	public boolean deleteStudent(Student student) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + STUDENT + " WHERE " +
					StudentColumns.ID + " = ?");
			ps.setString(1, String.valueOf(student.getId()));
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
	public boolean saveStudent(Student student) {
		Boolean update = student.getId() != 0;
		String query = update ?
				"UPDATE " + STUDENT + " SET " +
						StudentColumns.PERSON_ID + " = ? AND " +
						StudentColumns.GROUP_ID + " = ? AND " +
						StudentColumns.START + " = ? AND " +
						StudentColumns.FINISH + " = ? WHERE " +
						StudentColumns.ID + " = ?" :
				"INSERT INTO " + STUDENT + " VALUES (NULL,?,?,?,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, student.getPerson_id());
			ps.setLong(2, student.getGroup_id());
			ps.setDate(3, new java.sql.Date(student.getStart().getTime()));
			ps.setDate(4, new java.sql.Date(student.getFinish().getTime()));
			if (update) {
				ps.setLong(5, student.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				student.setId(resultSet.getLong(1));
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
	public List<Student> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}

	private Student makeStudent(ResultSet resultSet) throws SQLException {
		Student student = new Student(resultSet.getLong(2), resultSet.getLong(3), resultSet.getDate(4),
				resultSet.getDate(5));
		student.setId(resultSet.getLong(1));
		return student;
	}

	private Mark makeMark(ResultSet resultSet) throws SQLException {
		Mark mark = new Mark(resultSet.getLong(2), resultSet.getLong(3), resultSet.getLong(4), resultSet.getLong(5),
				resultSet.getLong(6), resultSet.getLong(7), resultSet.getDate(8));
		mark.setId(resultSet.getLong(1));
		return mark;
	}
}
