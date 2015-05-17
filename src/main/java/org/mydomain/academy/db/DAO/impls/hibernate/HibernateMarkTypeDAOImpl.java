package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.MarkTypeDAO;
import org.mydomain.academy.db.entities.MarkType;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_HIBERNATE;

public class HibernateMarkTypeDAOImpl implements MarkTypeDAO {

	private static final Logger log = Logger.getLogger(HibernateMarkTypeDAOImpl.class);

	private HibernateUtils hibernateUtils;

	public HibernateMarkTypeDAOImpl() {
	}

	public HibernateMarkTypeDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Override
	public MarkType findMarkTypeById(long id) {
		Session session = hibernateUtils.getSession();
		return (MarkType) session.get(MarkType.class, id);
	}

	@Override
	public List<MarkType> findAllMarkTypes() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from MarkType").list();
	}

	@Override
	public boolean saveMarkType(MarkType markType) {
		return saveOrDelete(markType, false);
	}

	@Override
	public boolean deleteMarkType(MarkType markType) {
		return saveOrDelete(markType, true);
	}

	private boolean saveOrDelete(MarkType mt, boolean delete) {
		Session session = hibernateUtils.getSession();
		try {
			Transaction t = session.beginTransaction();
			if (delete) {
				session.delete(mt);
			} else {
				session.merge(mt);
			}
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<MarkType> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(MarkType.class);

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
	public List<MarkType> findMarkTypeByName(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
//todo javadoc