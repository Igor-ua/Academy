package org.mydomain.academy.db.DAO.impls.JDBC;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.MarkTypeDAO;
import org.mydomain.academy.db.entities.Mark;
import org.mydomain.academy.db.entities.MarkType;
import org.mydomain.academy.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCMarkTypeDAOImpl implements MarkTypeDAO {

	private static final Logger log = Logger.getLogger(JDBCMarkTypeDAOImpl.class);

	private ConnectionManager connectionManager;

	public JDBCMarkTypeDAOImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public MarkType findMarkTypeById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK_TYPE + " WHERE " +
							MarkTypeColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			MarkType markType = new MarkType();
			while (resultSet.next()) {
				markType = makeMarkType(resultSet);
			}

			preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.MARK_TYPE_ID + " = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				markType.addMark(makeMark(resultSet));
			}

			return markType;
		} catch (SQLException e) {
			log.error(e);

		}
		return null;
	}

	@Override
	public List<MarkType> findAllMarkTypes() {
		List<MarkType> mark_types = new ArrayList<MarkType>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + MARK_TYPE);
			while (resultSet.next()) {
				mark_types.add(makeMarkType(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return mark_types;
	}

	@Override
	public List<MarkType> findMarkTypeByName(String name) {
		List<MarkType> mark_types = new ArrayList<MarkType>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK_TYPE + " WHERE " +
							MarkTypeColumns.DESCRIPTION + " = ?");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				mark_types.add(makeMarkType(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return mark_types;
	}

	@Override
	public boolean deleteMarkType(MarkType markType) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + MARK_TYPE + " WHERE " +
					MarkTypeColumns.ID + " = ?");
			ps.setString(1, String.valueOf(markType.getId()));
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
	public boolean saveMarkType(MarkType markType) {
		Boolean update = markType.getId() != 0;
		String query = update ?
				"UPDATE " + MARK_TYPE + " SET " +
						MarkTypeColumns.DESCRIPTION + " = ? WHERE " +
						MarkTypeColumns.ID + " = ?" :
				"INSERT INTO " + MARK_TYPE + " VALUES (NULL,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, markType.getName());
			if (update) {
				ps.setLong(2, markType.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				markType.setId(resultSet.getLong(1));
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
	public List<MarkType> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}

	private MarkType makeMarkType(ResultSet resultSet) throws SQLException {
		MarkType markType = new MarkType(resultSet.getString(2));
		markType.setId(resultSet.getLong(1));
		return markType;
	}

	private Mark makeMark(ResultSet resultSet) throws SQLException {
		Mark mark = new Mark(resultSet.getLong(2), resultSet.getLong(3), resultSet.getLong(4), resultSet.getLong(5),
				resultSet.getLong(6), resultSet.getLong(7), resultSet.getDate(8));
		mark.setId(resultSet.getLong(1));
		return mark;
	}
}
//todo javadoc