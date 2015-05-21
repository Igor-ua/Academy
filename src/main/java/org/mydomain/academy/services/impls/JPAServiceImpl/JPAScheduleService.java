package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPAScheduleDAO;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Schedule;
import org.mydomain.academy.db.entities.Subject;
import org.mydomain.academy.db.entities.Teacher;
import org.mydomain.academy.services.ServiceInterface.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPAScheduleService implements ScheduleService {

	private static final Logger log = Logger.getLogger(JPAScheduleService.class);

	private JPAScheduleDAO jpaScheduleDAO;

	@Autowired
	public void setJpaScheduleDAO(JPAScheduleDAO jpaScheduleDAO) {
		this.jpaScheduleDAO = jpaScheduleDAO;
	}

	public JPAScheduleService() {
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
		return jpaScheduleDAO.findAll();
	}

	public Page<Schedule> findAllSchedulesService(Pageable pageable) {
		return jpaScheduleDAO.findAll(pageable);
	}

	@Override
	public Schedule findScheduleByIdService(long id) {
		return jpaScheduleDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleBySubjectIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByTeacherIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByGroupIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByDayIdService(int day_id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByChisZnamService(int chisZnam_id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByLentaService(int lenta) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteService(Object object) {
		if (object instanceof Schedule) {
			try {
				jpaScheduleDAO.delete((Schedule) object);
				return true;
			} catch (RuntimeException e) {
				log.error(e);
			}
		}
		return false;
	}

	@Override
	public boolean saveService(Object object) {
		if (object instanceof Schedule) {
			try {
				if (validateSchedule((Schedule) object)) {
					jpaScheduleDAO.save((Schedule) object);
					return true;
				}
			} catch (RuntimeException e) {
				log.error(e);
			}
		}
		return false;
	}

	@Override
	@Deprecated
	public List<Schedule> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public Page<Schedule> findByAny(Subject subject, Teacher teacher, Group group,
									String day, String chisZnam, Integer lenta, Pageable pageable) {
		return jpaScheduleDAO.findBySubjectOrTeacherOrGroupOrDayOrChisZnamOrLenta(
				subject, teacher, group, day, chisZnam, lenta, pageable);
	}
}
