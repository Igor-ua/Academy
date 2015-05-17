package org.mydomain.academy.db.DAO.BasicInterface;


import org.mydomain.academy.db.entities.Teacher;

import java.util.Date;
import java.util.List;

public interface TeacherDAO {

	int FLAG_ID = 1;
	int FLAG_PERSON = FLAG_ID << 1;
	int FLAG_START = FLAG_PERSON << 1;
	int FLAG_FINISH = FLAG_START << 1;

	Teacher findTeacherById(long id);

	List<Teacher> findAllTeachers();

	List<Teacher> findTeacherByPersonId(long id);

	List<Teacher> findTeacherByStart(Date start);

	List<Teacher> findTeacherByFinish(Date finish);

	boolean saveTeacher(Teacher teacher);

	boolean deleteTeacher(Teacher teacher);

	List<Teacher> findByCriteria(int flags, Object... values);
}
//todo javadoc