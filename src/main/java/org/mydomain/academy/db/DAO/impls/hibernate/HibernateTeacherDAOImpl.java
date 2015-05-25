package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.TeacherDAO;
import org.mydomain.academy.db.entities.Teacher;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.*;

public class HibernateTeacherDAOImpl implements TeacherDAO {

	private static final Logger log = Logger.getLogger(HibernateTeacherDAOImpl.class);

	private HibernateUtils hibernateUtils;

	public HibernateTeacherDAOImpl() {
	}

	public HibernateTeacherDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Override
	public Teacher findTeacherById(long id) {
		Session session = hibernateUtils.getSession();
		return (Teacher) session.get(Teacher.class, id);
	}

	@Override
	public List<Teacher> findAllTeachers() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from Teacher").list();
	}

	@Override
	public boolean saveTeacher(Teacher teacher) {
		return saveOrDelete(teacher, false);
	}

	@Override
	public boolean deleteTeacher(Teacher teacher) {
		return saveOrDelete(teacher, true);
	}

	private boolean saveOrDelete(Teacher tch, boolean delete) {
		Session session = hibernateUtils.getSession();
		try {
			Transaction t = session.beginTransaction();
			if (delete) {
				session.delete(tch);
			} else {
				session.merge(tch);
			}
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<Teacher> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(Teacher.class);

		for (Object value : values) {
			if ((flags & FLAG_ID) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("id", value));
				}
				flags = flags ^ FLAG_ID;
			} else if ((flags & FLAG_PERSON) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("person_id", value));
				}
				flags = flags ^ FLAG_PERSON;
			} else if ((flags & FLAG_START) != 0) {
				if (value != null) {
					criteria.add(Restrictions.eq("start", value));
				}
				flags = flags ^ FLAG_START;
			} else if ((flags & FLAG_FINISH) != 0) {
				if (value != null) {
					criteria.add(Restrictions.eq("finish", value));
				}
				flags = flags ^ FLAG_FINISH;
			}

		}
		return criteria.list();
	}

	@Override
	@Deprecated
	public List<Teacher> findTeacherByPersonId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Teacher> findTeacherByStart(Date start) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Teacher> findTeacherByFinish(Date finish) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
