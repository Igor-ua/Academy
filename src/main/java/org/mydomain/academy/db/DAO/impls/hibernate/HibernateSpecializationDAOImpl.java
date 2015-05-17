package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.SpecializationDAO;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_HIBERNATE;

public class HibernateSpecializationDAOImpl implements SpecializationDAO {

	private static final Logger log = Logger.getLogger(HibernateSpecializationDAOImpl.class);

	private HibernateUtils hibernateUtils;

	public HibernateSpecializationDAOImpl() {
	}

	public HibernateSpecializationDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Override
	public Specialization findSpecializationById(long id) {
		Session session = hibernateUtils.getSession();
		return (Specialization) session.get(Specialization.class, id);
	}

	@Override
	public List<Specialization> findAllSpecializations() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from Specialization").list();
	}

	@Override
	public boolean saveSpecialization(Specialization specialization) {
		return saveOrDelete(specialization, false);
	}

	@Override
	public boolean deleteSpecialization(Specialization specialization) {
		return saveOrDelete(specialization, true);
	}

	private boolean saveOrDelete(Specialization s, boolean delete) {
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
	public List<Specialization> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(Specialization.class);

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
			}
		}
		return criteria.list();
	}

	@Override
	@Deprecated
	public List<Specialization> findSpecializationByName(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
//todo javadoc