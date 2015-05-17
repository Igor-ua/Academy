package org.mydomain.academy.db.DAO.impls.JDBC;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.MarkDAO;
import org.mydomain.academy.db.entities.Mark;
import org.mydomain.academy.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCMarkDAOImpl implements MarkDAO {

	private static final Logger log = Logger.getLogger(JDBCMarkDAOImpl.class);

	private ConnectionManager connectionManager;

	public JDBCMarkDAOImpl(ConnectionManager connectionManager) {

		this.connectionManager = connectionManager;
	}

	@Override
	public Mark findMarkById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Mark mark = new Mark();
			while (resultSet.next()) {
				mark = makeMark(resultSet);
			}
			return mark;
		} catch (SQLException e) {
			log.error(e);

		}
		return null;
	}

	@Override
	public List<Mark> findAllMarks() {
		List<Mark> marks = new ArrayList<Mark>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + MARK);
			while (resultSet.next()) {
				marks.add(makeMark(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return marks;
	}

	@Override
	public List<Mark> findMarkByTeacherId(long id) {
		List<Mark> marks = new ArrayList<Mark>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.TEACHER_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				marks.add(makeMark(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return marks;
	}

	@Override
	public List<Mark> findMarkByStudentId(long id) {
		List<Mark> marks = new ArrayList<Mark>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.STUDENT_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				marks.add(makeMark(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return marks;
	}

	@Override
	public List<Mark> findMarkByGroupId(long id) {
		List<Mark> marks = new ArrayList<Mark>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.GROUP_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				marks.add(makeMark(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return marks;
	}

	@Override
	public List<Mark> findMarkBySubjectId(long id) {
		List<Mark> marks = new ArrayList<Mark>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.SUBJECT_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				marks.add(makeMark(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return marks;
	}

	@Override
	public List<Mark> findMarkByFormId(long id) {
		List<Mark> marks = new ArrayList<Mark>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.FORM_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				marks.add(makeMark(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return marks;
	}

	@Override
	public List<Mark> findMarkByMarkTypeId(long id) {
		List<Mark> marks = new ArrayList<Mark>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.MARK_TYPE_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				marks.add(makeMark(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return marks;
	}

	@Override
	public List<Mark> findMarkByDate(java.util.Date markDate) {
		List<Mark> marks = new ArrayList<Mark>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + MARK + " WHERE " +
							MarkColumns.DATE + " = ?");
			preparedStatement.setDate(1, new Date(markDate.getTime()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				marks.add(makeMark(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return marks;
	}

	@Override
	public boolean deleteMark(Mark mark) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + MARK +
					" WHERE " + MarkColumns.ID + " = ?");
			ps.setString(1, String.valueOf(mark.getId()));
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
	public boolean saveMark(Mark mark) {
		Boolean update = mark.getId() != 0;
		String query = update ?
				"UPDATE " + MARK + " SET " +
						MarkColumns.MARK_TYPE_ID + " = ?, " +
						MarkColumns.TEACHER_ID + " = ?, " +
						MarkColumns.STUDENT_ID + " = ?, " +
						MarkColumns.GROUP_ID + " = ?, " +
						MarkColumns.SUBJECT_ID + " = ?, " +
						MarkColumns.FORM_ID + " = ?, " +
						MarkColumns.DATE + " = ? WHERE " +
						MarkColumns.ID + " = ?" :
				"INSERT INTO " + MARK + " VALUES (NULL,?,?,?,?,?,?,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, mark.getMarkTypeId());
			ps.setLong(2, mark.getTeacher_id());
			ps.setLong(3, mark.getStudent_id());
			ps.setLong(4, mark.getGroup_id());
			ps.setLong(5, mark.getSubject_id());
			ps.setLong(6, mark.getForm_id());
			ps.setDate(7, new Date(mark.getDate().getTime()));
			if (update) {
				ps.setLong(8, mark.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				mark.setId(resultSet.getLong(1));
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
	public List<Mark> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}

	private Mark makeMark(ResultSet resultSet) throws SQLException {
		Mark mark = new Mark(resultSet.getLong(2), resultSet.getLong(3), resultSet.getLong(4), resultSet.getLong(5),
				resultSet.getLong(6), resultSet.getLong(7), resultSet.getDate(8));
		mark.setId(resultSet.getLong(1));
		return mark;
	}
}
//todo javadoc
