package org.mydomain.academy.db.DAO.impls.JDBC;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.SpecializationDAO;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.db.entities.Subject;
import org.mydomain.academy.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCSpecializationDAOImpl implements SpecializationDAO {

	private static final Logger log = Logger.getLogger(JDBCSpecializationDAOImpl.class);

	private ConnectionManager connectionManager;

	public JDBCSpecializationDAOImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public Specialization findSpecializationById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SPECIALIZATION + " WHERE " +
							SpecializationsColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Specialization specialization = new Specialization();
			while (resultSet.next()) {
				specialization = makeSpecialization(resultSet);
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + GROUP + " WHERE " +
							GroupColumns.SPECIALIZATION_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				specialization.addGroup(makeGroup(resultSet));
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + SUBJECT + " WHERE " +
							SubjectColumns.SPECIALIZATION_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				specialization.addSubject(makeSubject(resultSet));
			}

			return specialization;
		} catch (SQLException e) {
			log.error(e);

		}
		return null;
	}

	@Override
	public List<Specialization> findAllSpecializations() {
		List<Specialization> specializations = new ArrayList<Specialization>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + SPECIALIZATION);
			while (resultSet.next()) {
				specializations.add(makeSpecialization(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return specializations;
	}

	@Override
	public List<Specialization> findSpecializationByName(String name) {
		List<Specialization> specializations = new ArrayList<Specialization>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SPECIALIZATION + " WHERE " +
							SpecializationsColumns.NAME + " = ?");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				specializations.add(makeSpecialization(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return specializations;
	}

	@Override
	public boolean deleteSpecialization(Specialization specialization) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + SPECIALIZATION + " WHERE " +
					SpecializationsColumns.ID + " = ?");
			ps.setString(1, String.valueOf(specialization.getId()));
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
	public boolean saveSpecialization(Specialization specialization) {
		Boolean update = specialization.getId() != 0;
		String query = update ?
				"UPDATE " + SPECIALIZATION + " SET " +
						SpecializationsColumns.NAME + " = ? WHERE " +
						SpecializationsColumns.ID + " = ?" :
				"INSERT INTO " + SPECIALIZATION + " VALUES (NULL,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, specialization.getName());
			if (update) {
				ps.setLong(2, specialization.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				specialization.setId(resultSet.getLong(1));
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
	public List<Specialization> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}

	private Specialization makeSpecialization(ResultSet resultSet) throws SQLException {
		Specialization specialization = new Specialization(resultSet.getString(2));
		specialization.setId(resultSet.getLong(1));
		return specialization;
	}

	private Group makeGroup(ResultSet resultSet) throws SQLException {
		Group group = new Group(resultSet.getString(2), resultSet.getLong(3), resultSet.getLong(4));
		group.setId(resultSet.getLong(1));
		return group;
	}

	private Subject makeSubject(ResultSet resultSet) throws SQLException {
		Subject subject = new Subject(resultSet.getString(2), resultSet.getLong(3));
		subject.setId(resultSet.getLong(1));
		return subject;
	}
}
//todo javadoc