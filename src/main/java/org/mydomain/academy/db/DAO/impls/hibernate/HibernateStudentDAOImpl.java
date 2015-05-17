package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.StudentDAO;
import org.mydomain.academy.db.entities.Student;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_HIBERNATE;

public class HibernateStudentDAOImpl implements StudentDAO {

	private static final Logger log = Logger.getLogger(HibernateStudentDAOImpl.class);

	private HibernateUtils hibernateUtils;

	public HibernateStudentDAOImpl() {
	}

	public HibernateStudentDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Override
	public Student findStudentById(long id) {
		Session session = hibernateUtils.getSession();
		return (Student) session.get(Student.class, id);
	}

	@Override
	public List<Student> findAllStudents() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from Student").list();
	}

	@Override
	public boolean saveStudent(Student student) {
		return saveOrDelete(student, false);
	}

	@Override
	public boolean deleteStudent(Student student) {
		return saveOrDelete(student, true);
	}

	private boolean saveOrDelete(Student s, boolean delete) {
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
	public List<Student> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(Student.class);

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
			} else if ((flags & FLAG_GROUP) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("group_id", value));
				}
				flags = flags ^ FLAG_GROUP;
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
	public List<Student> findStudentByPersonId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Student> findStudentByGroupId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Student> findStudentByStart(Date start) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Student> findStudentByFinish(Date finish) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
//todo javadoc