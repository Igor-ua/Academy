package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.StudentDAO;
import org.mydomain.academy.db.entities.Student;
import org.mydomain.academy.services.ServiceInterface.StudentService;

import java.util.Date;
import java.util.List;

public class JDBCStudentService implements StudentService {

	private static final Logger log = Logger.getLogger(JDBCStudentService.class);

	private StudentDAO studentDAO;

	public JDBCStudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		log.info("StudentService has been initialized.");
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
		return studentDAO.findAllStudents();
	}

	@Override
	public Student findStudentByIdService(long id) {
		return studentDAO.findStudentById(id);
	}

	@Override
	public List<Student> findStudentByPersonIdService(long id) {
		return studentDAO.findStudentByPersonId(id);
	}

	@Override
	public List<Student> findStudentByGroupIdService(long id) {
		return studentDAO.findStudentByGroupId(id);
	}

	@Override
	public List<Student> findStudentByStartService(Date start) {
		return studentDAO.findStudentByStart(start);
	}

	@Override
	public List<Student> findStudentByFinishService(Date finish) {
		return studentDAO.findStudentByFinish(finish);
	}

	@Override
	public boolean deleteService(Object object) {
		return object instanceof Student && studentDAO.deleteStudent((Student) object);
	}

	@Override
	public boolean saveService(Object object) {
		return object instanceof Student &&
				validateStudent((Student) object) && studentDAO.saveStudent((Student) object);
	}

	@Override
	public List<Student> findByCriteriaService(int flags, Object... values) {
		return studentDAO.findByCriteria(flags, values);
	}
}
