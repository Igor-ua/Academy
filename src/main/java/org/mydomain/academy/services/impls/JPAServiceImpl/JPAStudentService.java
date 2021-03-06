package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPAStudentDAO;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.entities.Student;
import org.mydomain.academy.services.ServiceInterface.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Service
public class JPAStudentService implements StudentService {

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

	public Page<Student> findAllStudentsService(Pageable pageable) {
		return jpaStudentDAO.findAll(pageable);
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
	public boolean deleteService(Object object) {
		if (object instanceof Student) {
			try {
				jpaStudentDAO.delete((Student) object);
				return true;
			} catch (RuntimeException e) {
				log.error(e);
			}
		}
		return false;
	}

	@Override
	public boolean saveService(Object object) {
		if (object instanceof Student) {
			try {
				if (validateStudent((Student) object)) {
					jpaStudentDAO.save((Student) object);
					return true;
				}
			} catch (RuntimeException e) {
				log.error(e);
			}
		}
		return false;
	}

	@Override
	@Deprecated
	public List<Student> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public Page<Student> findByAny(String personName, String groupName, Pageable pageable) {
		return jpaStudentDAO.findByPersonNameOrGroupName(personName, groupName, pageable);
	}
}
