package org.mydomain.academy.db.DAO.impls.JDBC;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.FormDAO;
import org.mydomain.academy.db.entities.Form;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Mark;
import org.mydomain.academy.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCFormDAOImpl implements FormDAO {

	private static final Logger log = Logger.getLogger(JDBCFormDAOImpl.class);

	private ConnectionManager connectionManager;

	public JDBCFormDAOImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public Form findFormById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + FORM + " WHERE " + FormColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Form form = new Form();
			while (resultSet.next()) {
				form = makeForm(resultSet);
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + GROUP + " WHERE " + GroupColumns.FORM_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				form.addGroup(makeGroup(resultSet));
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " + MarkColumns.FORM_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				form.addMark(makeMark(resultSet));
			}

			return form;
		} catch (SQLException e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public List<Form> findAllForms() {
		List<Form> forms = new ArrayList<Form>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + FORM);
			while (resultSet.next()) {
				forms.add(makeForm(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return forms;
	}

	@Override
	public List<Form> findFormByName(String name) {
		List<Form> forms = new ArrayList<Form>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + FORM + " WHERE " +
							FormColumns.NAME + " = ?");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				forms.add(makeForm(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return forms;
	}

	@Override
	public boolean deleteForm(Form form) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + FORM + " WHERE " +
					FormColumns.ID + " = ?");
			ps.setString(1, String.valueOf(form.getId()));
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
	public boolean saveForm(Form form) {
		Boolean update = form.getId() != 0;
		String query = update ?
				"UPDATE " + FORM + " SET " +
						FormColumns.NAME + " = ? WHERE " +
						FormColumns.ID + " = ?" :
				"INSERT INTO " + FORM + " VALUES (NULL,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, form.getName());
			if (update) {
				ps.setLong(2, form.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				form.setId(resultSet.getLong(1));
			}
			if (rows != 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return false;
	}

	private Form makeForm(ResultSet resultSet) throws SQLException {
		Form form = new Form(resultSet.getString(2));
		form.setId(resultSet.getLong(1));
		return form;
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

	@Override
	public List<Form> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}
}
