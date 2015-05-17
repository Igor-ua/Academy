package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.ScheduleDAO;
import org.mydomain.academy.db.entities.Schedule;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_HIBERNATE;

public class HibernateScheduleDAOImpl implements ScheduleDAO {

	private static final Logger log = Logger.getLogger(HibernateScheduleDAOImpl.class);

	private HibernateUtils hibernateUtils;

	public HibernateScheduleDAOImpl() {
	}

	public HibernateScheduleDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Override
	public Schedule findScheduleById(long id) {
		Session session = hibernateUtils.getSession();
		return (Schedule) session.get(Schedule.class, id);
	}

	@Override
	public List<Schedule> findAllSchedules() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from Schedule").list();
	}

	@Override
	public boolean saveSchedule(Schedule schedule) {
		return saveOrDelete(schedule, false);
	}

	@Override
	public boolean deleteSchedule(Schedule schedule) {
		return saveOrDelete(schedule, true);
	}

	private boolean saveOrDelete(Schedule s, boolean delete) {
		Session session = hibernateUtils.getSession();
		try {
			Transaction t = session.beginTransaction();
			if (delete) {
				session.delete(s);
			} else {
				session.merge(s);
			}
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<Schedule> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(Schedule.class);

		for (Object value : values) {
			if ((flags & FLAG_ID) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("id", value));
				}
				flags = flags ^ FLAG_ID;
			} else if ((flags & FLAG_SUBJECT) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("subject_id", value));
				}
				flags = flags ^ FLAG_SUBJECT;
			} else if ((flags & FLAG_TEACHER) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("teacher_id", value));
				}
				flags = flags ^ FLAG_TEACHER;
			} else if ((flags & FLAG_GROUP) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("group_id", value));
				}
				flags = flags ^ FLAG_GROUP;
			} else if ((flags & FLAG_DAY) != 0) {
				if (value.toString().length() != 0) {
					criteria.add(Restrictions.eq("day", value));
				}
				flags = flags ^ FLAG_DAY;
			} else if ((flags & FLAG_CHIS_ZNAM) != 0) {
				if (value.toString().length() != 0) {
					criteria.add(Restrictions.eq("chisZnam", value));
				}
				flags = flags ^ FLAG_CHIS_ZNAM;
			} else if ((flags & FLAG_LENTA) != 0) {
				if (value.toString().length() != 0) {
					criteria.add(Restrictions.eq("lenta", value));
				}
				flags = flags ^ FLAG_LENTA;
			}

		}
		return criteria.list();
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleBySubjectId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByTeacherId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByGroupId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByDayId(int day_id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByChisZnam(int chisZnam_id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Schedule> findScheduleByLenta(int lenta) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
//todo javadoc