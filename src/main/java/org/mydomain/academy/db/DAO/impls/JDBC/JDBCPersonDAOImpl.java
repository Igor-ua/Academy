package org.mydomain.academy.db.DAO.impls.JDBC;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.PersonDAO;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.entities.Student;
import org.mydomain.academy.db.entities.Teacher;
import org.mydomain.academy.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCPersonDAOImpl implements PersonDAO {

	private static final Logger log = Logger.getLogger(JDBCPersonDAOImpl.class);

	private ConnectionManager connectionManager;

	public JDBCPersonDAOImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public Person findPersonById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + PERSON + " WHERE " +
							PersonColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Person person = new Person();
			while (resultSet.next()) {
				person = makePerson(resultSet);
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + TEACHER + " WHERE " +
							TeacherColumns.PERSON_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				person.addTeacher(makeTeacher(resultSet));
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + STUDENT + " WHERE " +
							StudentColumns.PERSON_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				person.addStudent(makeStudent(resultSet));
			}

			return person;
		} catch (SQLException e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public List<Person> findAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + PERSON);
			while (resultSet.next()) {
				persons.add(makePerson(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return persons;
	}

	@Override
	public List<Person> findPersonByName(String name) {
		List<Person> persons = new ArrayList<Person>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + PERSON + " WHERE " +
							PersonColumns.NAME + " = ?");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				persons.add(makePerson(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return persons;
	}

	@Override
	public List<Person> findPersonByPassport(String passport) {
		List<Person> persons = new ArrayList<Person>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + PERSON + " WHERE " +
							PersonColumns.PASSPORT + " = ?");
			preparedStatement.setString(1, passport);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				persons.add(makePerson(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return persons;
	}

	@Override
	public List<Person> findPersonByBirthday(java.util.Date birthday) {
		List<Person> persons = new ArrayList<Person>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + PERSON + " WHERE " +
							PersonColumns.BIRTHDAY + " = ?");
			preparedStatement.setDate(1, new java.sql.Date(birthday.getTime()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				persons.add(makePerson(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return persons;
	}

	@Override
	public boolean deletePerson(Person person) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + PERSON + " WHERE " +
					PersonColumns.ID + " = ?");
			ps.setString(1, String.valueOf(person.getId()));
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
	public boolean savePerson(Person person) {
		Boolean update = person.getId() != 0;
		String query = update ?
				"UPDATE " + PERSON + " SET " +
						PersonColumns.NAME + " = ?, " +
						PersonColumns.BIRTHDAY + " = ?, " +
						PersonColumns.PASSPORT + " = ? WHERE " +
						PersonColumns.ID + " = ?" :
				"INSERT INTO " + PERSON + " VALUES (NULL,?,?,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, person.getName());
			ps.setDate(2, new java.sql.Date(person.getBirthday().getTime()));
			ps.setString(3, person.getPassport());
			if (update) {
				ps.setLong(4, person.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				person.setId(resultSet.getLong(1));
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
	public List<Person> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}

	private Person makePerson(ResultSet resultSet) throws SQLException {
		Person person = new Person(resultSet.getString(2), resultSet.getDate(3), resultSet.getString(4));
		person.setId(resultSet.getLong(1));
		return person;
	}

	private Student makeStudent(ResultSet resultSet) throws SQLException {
		Student student = new Student(resultSet.getLong(2), resultSet.getLong(3), resultSet.getDate(4),
				resultSet.getDate(5));
		student.setId(resultSet.getLong(1));
		return student;
	}

	private Teacher makeTeacher(ResultSet resultSet) throws SQLException {
		Teacher teacher = new Teacher(resultSet.getLong(2), resultSet.getDate(3), resultSet.getDate(4));
		teacher.setId(resultSet.getLong(1));
		return teacher;
	}
}
//todo javadoc