package org.mydomain.academy.db.DAO.BasicInterface;


import org.mydomain.academy.db.entities.Student;

import java.util.Date;
import java.util.List;

public interface StudentDAO {

	int FLAG_ID = 1;
	int FLAG_PERSON = FLAG_ID << 1;
	int FLAG_GROUP = FLAG_PERSON << 1;
	int FLAG_START = FLAG_GROUP << 1;
	int FLAG_FINISH = FLAG_START << 1;

	Student findStudentById(long id);

	List<Student> findAllStudents();

	List<Student> findStudentByPersonId(long id);

	List<Student> findStudentByGroupId(long id);

	List<Student> findStudentByStart(Date start);

	List<Student> findStudentByFinish(Date finish);

	boolean saveStudent(Student student);

	boolean deleteStudent(Student student);

	List<Student> findByCriteria(int flags, Object... values);
}
