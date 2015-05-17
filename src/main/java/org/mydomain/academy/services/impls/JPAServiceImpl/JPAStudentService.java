package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPAStudentDAO;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.entities.Student;
import org.mydomain.academy.services.ServiceInterface.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPAStudentService implements StudentService{

	private static final Logger log = Logger.getLogger(JPAStudentService.class);

	private JPAStudentDAO jpaStudentDAO;

	@Autowired
	public void setJpaStudentDAO(JPAStudentDAO jpaStudentDAO) {
		this.jpaStudentDAO = jpaStudentDAO;
	}

	public JPAStudentService() {
	}

	private boolean validateStudent(Student student) {
		if (student.getStart().after(student.getFinish())) {
			log.error("Student validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Student> findAllStudentsService() {
		return jpaStudentDAO.findAll();
	}

	@Override
	public Student findStudentByIdService(long id) {
		return jpaStudentDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<Student> findStudentByPersonIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Student> findStudentByGroupIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Student> findStudentByStartService(Date start) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Student> findStudentByFinishService(Date finish) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteStudentService(Student student) {
		try {
			jpaStudentDAO.delete(student);
			return true;
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	public boolean saveStudentService(Student student) {
		try {
			if (validateStudent(student)) {
				jpaStudentDAO.save(student);
				return true;
			}
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	@Deprecated
	public List<Student> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public List<Student> findByAny(Person person, Group group, Date start, Date finish) {
		return jpaStudentDAO.findByPersonOrGroupOrStartOrFinish(person, group, start, finish);
	}
}
