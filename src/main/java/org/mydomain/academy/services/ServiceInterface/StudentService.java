package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Student;

import java.util.Date;
import java.util.List;

public interface StudentService {

	List<Student> findAllStudentsService();

	Student findStudentByIdService(long id);

	List<Student> findStudentByPersonIdService(long id);

	List<Student> findStudentByGroupIdService(long id);

	List<Student> findStudentByStartService(Date start);

	List<Student> findStudentByFinishService(Date finish);

	boolean deleteStudentService(Student student);

	boolean saveStudentService(Student student);

	List<Student> findByCriteriaService(int flags, Object... values);
}
