package org.mydomain.academy.db.DAO.impls.JDBC;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.ScheduleDAO;
import org.mydomain.academy.db.entities.Schedule;
import org.mydomain.academy.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JDBC;
import static org.mydomain.academy.db.utils.TableNames.*;

public class JDBCScheduleDAOImpl implements ScheduleDAO {

	private static final Logger log = Logger.getLogger(JDBCScheduleDAOImpl.class);

	private ConnectionManager connectionManager;

	public JDBCScheduleDAOImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public Schedule findScheduleById(long id) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SCHEDULE + " WHERE " +
							ScheduleColumns.ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Schedule schedule = new Schedule();
			while (resultSet.next()) {
				schedule = makeSchedule(resultSet);
			}
			return schedule;
		} catch (SQLException e) {
			log.error(e);

		}
		return null;
	}

	@Override
	public List<Schedule> findAllSchedules() {
		List<Schedule> schedules = new ArrayList<Schedule>();
		try {
			Connection c = connectionManager.getConnection();
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + SCHEDULE);
			while (resultSet.next()) {
				schedules.add(makeSchedule(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return schedules;
	}

	@Override
	public List<Schedule> findScheduleBySubjectId(long id) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SCHEDULE + " WHERE " +
							ScheduleColumns.SUBJECT_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				schedules.add(makeSchedule(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return schedules;
	}

	@Override
	public List<Schedule> findScheduleByTeacherId(long id) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SCHEDULE + " WHERE " +
							ScheduleColumns.TEACHER_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				schedules.add(makeSchedule(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return schedules;
	}

	@Override
	public List<Schedule> findScheduleByGroupId(long id) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SCHEDULE + " WHERE " +
							ScheduleColumns.GROUP_ID + " = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				schedules.add(makeSchedule(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return schedules;
	}

	@Override
	public List<Schedule> findScheduleByDayId(int day_id) {
		String day = ScheduleColumns.DAY_ENUM.values()[day_id].toString();
		List<Schedule> schedules = new ArrayList<Schedule>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SCHEDULE + " WHERE " +
							ScheduleColumns.SCHEDULE_DAY + " = ?");
			preparedStatement.setString(1, day);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				schedules.add(makeSchedule(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return schedules;
	}

	@Override
	public List<Schedule> findScheduleByChisZnam(int chisZnam_id) {
		String chisZnam = ScheduleColumns.CHIS_ZNAM_ENUM.values()[chisZnam_id].toString();
		List<Schedule> schedules = new ArrayList<Schedule>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SCHEDULE + " WHERE " +
							ScheduleColumns.CHIS_ZNAM + " = ?");
			preparedStatement.setString(1, chisZnam);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				schedules.add(makeSchedule(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return schedules;
	}

	@Override
	public List<Schedule> findScheduleByLenta(int lenta) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement
					("SELECT * FROM " + SCHEDULE + " WHERE " +
							ScheduleColumns.LENTA + " = ?");
			preparedStatement.setInt(1, lenta);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				schedules.add(makeSchedule(resultSet));
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return schedules;
	}

	@Override
	public boolean deleteSchedule(Schedule schedule) {
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM " + SCHEDULE + " WHERE " +
					StudentColumns.ID + " = ?");
			ps.setString(1, String.valueOf(schedule.getId()));
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
	public boolean saveSchedule(Schedule schedule) {
		Boolean update = schedule.getId() != 0;
		String query = update ?
				"UPDATE " + SCHEDULE + " SET " +
						ScheduleColumns.SUBJECT_ID + " = ?, " +
						ScheduleColumns.TEACHER_ID + " = ?, " +
						ScheduleColumns.GROUP_ID + " = ?, " +
						ScheduleColumns.SCHEDULE_DAY + " = ?, " +
						ScheduleColumns.CHIS_ZNAM + " = ?, " +
						ScheduleColumns.LENTA + " = ? WHERE " +
						ScheduleColumns.ID + " = ?" :
				"INSERT INTO " + SCHEDULE + " VALUES (NULL,?,?,?,?,?,?)";
		try {
			Connection c = connectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement
					(query, update ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, schedule.getSubject_id());
			ps.setLong(2, schedule.getTeacher_id());
			ps.setLong(3, schedule.getGroup_id());
			ps.setString(4, schedule.getDay());
			ps.setString(5, schedule.getChisZnam());
			ps.setInt(6, schedule.getLenta());
			if (update) {
				ps.setLong(7, schedule.getId());
			}
			int rows = ps.executeUpdate();
			if (!update) {
				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				schedule.setId(resultSet.getLong(1));
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
	public List<Schedule> findByCriteria(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JDBC.toString());
	}

	private Schedule makeSchedule(ResultSet resultSet) throws SQLException {
		Schedule schedule = new Schedule(resultSet.getLong(2), resultSet.getLong(3), resultSet.getLong(4),
				resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
		schedule.setId(resultSet.getLong(1));
		return schedule;
	}
}
