package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPATeacherDAO;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.entities.Teacher;
import org.mydomain.academy.services.ServiceInterface.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPATeacherService implements TeacherService {

	private static final Logger log = Logger.getLogger(JPATeacherService.class);

	private JPATeacherDAO jpaTeacherDAO;

	@Autowired
	public void setJpaTeacherDAO(JPATeacherDAO jpaTeacherDAO) {
		this.jpaTeacherDAO = jpaTeacherDAO;
	}

	public JPATeacherService() {
	}

	private boolean validateTeacher(Teacher teacher) {
		if (teacher.getStart().after(teacher.getFinish())) {
			log.error("Teacher validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Teacher> findAllTeachersService() {
		return jpaTeacherDAO.findAll();
	}

	public Page<Teacher> findAllTeachersService(Pageable pageable) {
		return jpaTeacherDAO.findAll(pageable);
	}

	@Override
	public Teacher findTeacherByIdService(long id) {
		return jpaTeacherDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<Teacher> findTeacherByPersonIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Teacher> findTeacherByStartService(Date start) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Teacher> findTeacherByFinishService(Date finish) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteService(Object object) {
		if (object instanceof Teacher) {
			try {
				jpaTeacherDAO.delete((Teacher) object);
				return true;
			} catch (RuntimeException e) {
				log.error(e);
			}
		}
		return false;
	}

	@Override
	public boolean saveService(Object object) {
		if (object instanceof Teacher) {
			try {
				if (validateTeacher((Teacher) object)) {
					jpaTeacherDAO.save((Teacher) object);
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
	public List<Teacher> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public Page<Teacher> findByAny(String personName, Date start, Date finish, Pageable pageable) {
		return jpaTeacherDAO.findByPersonNameOrStartOrFinish(personName, start, finish, pageable);
	}
}
