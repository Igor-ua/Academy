package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.ScheduleDAO;
import org.mydomain.academy.db.entities.Schedule;
import org.mydomain.academy.services.ServiceInterface.ScheduleService;

import java.util.List;

public class JDBCScheduleService implements ScheduleService {

	private ScheduleDAO scheduleDAO;

	private static final Logger log = Logger.getLogger(JDBCScheduleService.class);

	public JDBCScheduleService(ScheduleDAO scheduleDAO) {
		this.scheduleDAO = scheduleDAO;

		log.info("ScheduleService has been initialized.");
	}

	private boolean validateSchedule(Schedule schedule) {
		if (!schedule.getChisZnam().matches("[A-Za-zР-пр-џ]{0,10}")) {
			log.error("Schedule validation error.");
			return false;
		}
		if (schedule.getLenta() > 24 || schedule.getLenta() < 0) {
			log.error("Schedule validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Schedule> findAllSchedulesService() {
		return scheduleDAO.findAllSchedules();
	}

	@Override
	public Schedule findScheduleByIdService(long id) {
		return scheduleDAO.findScheduleById(id);
	}

	@Override
	public List<Schedule> findScheduleBySubjectIdService(long id) {
		return scheduleDAO.findScheduleBySubjectId(id);
	}

	@Override
	public List<Schedule> findScheduleByTeacherIdService(long id) {
		return scheduleDAO.findScheduleByTeacherId(id);
	}

	@Override
	public List<Schedule> findScheduleByGroupIdService(long id) {
		return scheduleDAO.findScheduleByGroupId(id);
	}

	@Override
	public List<Schedule> findScheduleByDayIdService(int day_id) {
		return scheduleDAO.findScheduleByDayId(day_id);
	}

	@Override
	public List<Schedule> findScheduleByChisZnamService(int chisZnam_id) {
		return scheduleDAO.findScheduleByChisZnam(chisZnam_id);
	}

	@Override
	public List<Schedule> findScheduleByLentaService(int lenta) {
		return scheduleDAO.findScheduleByLenta(lenta);
	}

	@Override
	public boolean deleteScheduleService(Schedule schedule) {
		return scheduleDAO.deleteSchedule(schedule);
	}

	@Override
	public boolean saveScheduleService(Schedule schedule) {
		return validateSchedule(schedule) && scheduleDAO.saveSchedule(schedule);
	}

	@Override
	public List<Schedule> findByCriteriaService(int flags, Object... values) {
		return scheduleDAO.findByCriteria(flags, values);
	}
}
