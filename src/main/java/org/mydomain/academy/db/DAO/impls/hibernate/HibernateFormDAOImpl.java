package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.FormDAO;
import org.mydomain.academy.db.entities.Form;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_HIBERNATE;

public class HibernateFormDAOImpl implements FormDAO {

	private static final Logger log = Logger.getLogger(HibernateFormDAOImpl.class);

	private HibernateUtils hibernateUtils;

	public HibernateFormDAOImpl() {
	}

	public HibernateFormDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Override
	public Form findFormById(long id) {
		Session session = hibernateUtils.getSession();
		return (Form) session.get(Form.class, id);
	}

	@Override
	public List<Form> findAllForms() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from Form").list();
	}

	@Override
	public boolean saveForm(Form form) {
		return saveOrDelete(form, false);
	}

	@Override
	public boolean deleteForm(Form form) {
		return saveOrDelete(form, true);
	}

	private boolean saveOrDelete(Form f, boolean delete) {
		Session session = hibernateUtils.getSession();
		try {
			Transaction t = session.beginTransaction();
			if (delete) {
				session.delete(f);
			} else {
				session.merge(f);
			}
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<Form> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(Form.class);

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
	public List<Form> findFormByName(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
