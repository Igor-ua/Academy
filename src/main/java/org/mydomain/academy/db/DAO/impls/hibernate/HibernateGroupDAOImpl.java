package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.GroupDAO;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_HIBERNATE;

public class HibernateGroupDAOImpl implements GroupDAO {

	private HibernateUtils hibernateUtils;

	public HibernateGroupDAOImpl() {
	}

	public HibernateGroupDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	private static final Logger log = Logger.getLogger(HibernateGroupDAOImpl.class);

	@Override
	public Group findGroupById(long id) {
		Session session = hibernateUtils.getSession();
		return (Group) session.get(Group.class, id);
	}

	@Override
	public List<Group> findAllGroups() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from Group").list();
	}

	@Override
	public boolean saveGroup(Group group) {
		return saveOrDelete(group, false);
	}

	@Override
	public boolean deleteGroup(Group group) {
		return saveOrDelete(group, true);
	}

	private boolean saveOrDelete(Group g, boolean delete) {
		Session session = hibernateUtils.getSession();
		try {
			Transaction t = session.beginTransaction();
			if (delete) {
				session.delete(g);
			} else {
				session.merge(g);
			}
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<Group> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(Group.class);

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
			} else if ((flags & FLAG_FORM) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("form_id", value));
				}
				flags = flags ^ FLAG_FORM;
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
	public List<Group> findGroupByFormId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Group> findGroupBySpecializationId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Group> findGroupByName(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
