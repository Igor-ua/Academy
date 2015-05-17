package org.mydomain.academy.db.DAO.impls.JDBC;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.GroupDAO;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Mark;
import org.mydomain.academy.db.entities.Schedule;
import org.mydomain.academy.db.entities.Student;
import org.mydomain.academy.db.utils.ConnectionManager;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCGroupDAOImpl implements GroupDAO {

	private ConnectionManager connectionManager;

	private static final Logger log = Logger.getLogger(JDBCGroupDAOImpl.class);

	public JDBCGroupDAOImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public Group findGroupById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + GROUP + " WHERE " +
							GroupColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Group group = new Group();
			while (resultSet.next()) {
				group = makeGroup(resultSet);
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.GROUP_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				group.addMark(makeMark(resultSet));
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + SCHEDULE + " WHERE " +
							ScheduleColumns.GROUP_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				group.addSchedule(makeSchedule(resultSet));
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + STUDENT + " WHERE " +
							StudentColumns.GROUP_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				group.addStudent(makeStudent(resultSet));
			}

			return group;
		} catch (SQLException e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public List<Group> findAllGroups() {
		List<Group> groups = new ArrayList<Group>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + GROUP);
			while (resultSet.next()) {
				groups.add(makeGroup(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return groups;
	}

	@Override
	public List<Group> findGroupByFormId(long id) {
		List<Group> groups = new ArrayList<Group>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + GROUP + " WHERE " +
							GroupColumns.FORM_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				groups.add(makeGroup(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return groups;
	}

	@Override
	public List<Group> findGroupBySpecializationId(long id) {
		List<Group> groups = new ArrayList<Group>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + GROUP + " WHERE " +
							GroupColumns.SPECIALIZATION_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				groups.add(makeGroup(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return groups;
	}

	@Override
	public List<Group> findGroupByName(String name) {
		List<Group> groups = new ArrayList<Group>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + GROUP + " WHERE " +
							GroupColumns.NAME + " = ?");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				groups.add(makeGroup(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return groups;
	}

	@Override
	public boolean deleteGroup(Group group) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + GROUP + " WHERE " +
					GroupColumns.ID + " = ?");
			ps.setString(1, String.valueOf(group.getId()));
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
	public boolean saveGroup(Group group) {
		Boolean update = group.getId() != 0;
		String query = update ?
				"UPDATE " + GROUP + " SET " +
						GroupColumns.NAME + " = ?, " +
						GroupColumns.FORM_ID + " = ?, " +
						GroupColumns.SPECIALIZATION_ID + " = ? WHERE " +
						GroupColumns.ID + " = ?" :
				"INSERT INTO " + GROUP + " VALUES (NULL,?,?,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, group.getName());
			ps.setLong(2, group.getForm_id());
			ps.setLong(3, group.getSpecialization_id());
			if (update) {
				ps.setLong(4, group.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				group.setId(resultSet.getLong(1));
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
	public List<Group> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}

	private Group makeGroup(ResultSet resultSet) throws SQLException {
		Group group = new Group(resultSet.getString(2), resultSet.getLong(3), resultSet.getLong(4));
		group.setId(resultSet.getLong(1));
		return group;
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

	private Student makeStudent(ResultSet resultSet) throws SQLException {
		Student student = new Student(resultSet.getLong(2), resultSet.getLong(3), resultSet.getDate(4),
				resultSet.getDate(5));
		student.setId(resultSet.getLong(1));
		return student;
	}
}
//todo javadoc