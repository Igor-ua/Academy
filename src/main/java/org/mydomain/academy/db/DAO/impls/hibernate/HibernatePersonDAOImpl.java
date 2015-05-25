package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.PersonDAO;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_HIBERNATE;

public class HibernatePersonDAOImpl implements PersonDAO {

	private HibernateUtils hibernateUtils;

	public HibernatePersonDAOImpl() {
	}

	public HibernatePersonDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Override
	public Person findPersonById(long id) {
		Session session = hibernateUtils.getSession();
		return (Person) session.get(Person.class, id);
	}

	@Override
	public List<Person> findAllPersons() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from Person").list();
	}

	@Override
	public boolean savePerson(Person person) {
		return saveOrDelete(person, false);
	}

	@Override
	public boolean deletePerson(Person person) {
		return saveOrDelete(person, true);
	}

	private boolean saveOrDelete(Person p, boolean delete) {
		Session session = hibernateUtils.getSession();
		try {
			Transaction t = session.beginTransaction();
			if (delete) {
				session.delete(p);
			} else {
				session.merge(p);
			}
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<Person> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(Person.class);

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
			} else if ((flags & FLAG_BIRTHDAY) != 0) {
				if (value != null) {
					criteria.add(Restrictions.eq("birthday", value));
				}
				flags = flags ^ FLAG_BIRTHDAY;
			} else if ((flags & FLAG_PASSPORT) != 0) {
				if (value.toString().length() != 0) {
					criteria.add(Restrictions.eq("passport", value));
				}
				flags = flags ^ FLAG_PASSPORT;
			}

		}
		return criteria.list();
	}

	@Override
	@Deprecated
	public List<Person> findPersonByName(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Person> findPersonByPassport(String passport) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Person> findPersonByBirthday(Date birthday) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
