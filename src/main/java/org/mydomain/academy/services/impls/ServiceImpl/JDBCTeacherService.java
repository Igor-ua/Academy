package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.TeacherDAO;
import org.mydomain.academy.db.entities.Teacher;
import org.mydomain.academy.services.ServiceInterface.TeacherService;

import java.util.Date;
import java.util.List;

public class JDBCTeacherService implements TeacherService {

	private static final Logger log = Logger.getLogger(JDBCTeacherService.class);

	private TeacherDAO teacherDAO;

	public JDBCTeacherService(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
		log.info("TeacherService has been initialized.");
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
		return teacherDAO.findAllTeachers();
	}

	@Override
	public Teacher findTeacherByIdService(long id) {
		return teacherDAO.findTeacherById(id);
	}

	@Override
	public List<Teacher> findTeacherByPersonIdService(long id) {
		return teacherDAO.findTeacherByPersonId(id);
	}

	@Override
	public List<Teacher> findTeacherByStartService(Date start) {
		return teacherDAO.findTeacherByStart(start);
	}

	@Override
	public List<Teacher> findTeacherByFinishService(Date finish) {
		return teacherDAO.findTeacherByFinish(finish);
	}

	@Override
	public boolean deleteService(Object object) {
		return object instanceof Teacher && teacherDAO.deleteTeacher((Teacher) object);
	}

	@Override
	public boolean saveService(Object object) {
		return object instanceof Teacher &&
				validateTeacher((Teacher) object) && teacherDAO.saveTeacher((Teacher) object);
	}

	@Override
	public List<Teacher> findByCriteriaService(int flags, Object... values) {
		return teacherDAO.findByCriteria(flags, values);
	}
}
