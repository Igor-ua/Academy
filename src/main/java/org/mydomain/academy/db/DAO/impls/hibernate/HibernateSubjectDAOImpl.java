package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.SubjectDAO;
import org.mydomain.academy.db.entities.Subject;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_HIBERNATE;

public class HibernateSubjectDAOImpl implements SubjectDAO {

	private static final Logger log = Logger.getLogger(HibernateSubjectDAOImpl.class);

	private HibernateUtils hibernateUtils;

	public HibernateSubjectDAOImpl() {
	}

	public HibernateSubjectDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Override
	public Subject findSubjectById(long id) {
		Session session = hibernateUtils.getSession();
		return (Subject) session.get(Subject.class, id);
	}

	@Override
	public List<Subject> findAllSubjects() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from Subject").list();
	}

	@Override
	public boolean saveSubject(Subject subject) {
		return saveOrDelete(subject, false);
	}

	@Override
	public boolean deleteSubject(Subject subject) {
		return false;
	}

	private boolean saveOrDelete(Subject s, boolean delete) {
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
	public List<Subject> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(Subject.class);

		for (Object value : values) {
			if ((flags & FLAG_ID) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("id", value));
				}
				flags = flags ^ FLAG_ID;
			} else if ((flags & FLAG_NAME) != 0) {
				if (value.toString().length() != 0) {
					criteria.add(Restrictions.eq("name", value));
				}
				flags = flags ^ FLAG_NAME;
			} else if ((flags & FLAG_SPECIALIZATION) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("specialization_id", value));
				}
				flags = flags ^ FLAG_SPECIALIZATION;
			}

		}
		return criteria.list();
	}

	@Override
	@Deprecated
	public List<Subject> findSubjectBySpecializationId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Subject> findSubjectByName(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
//todo javadoc