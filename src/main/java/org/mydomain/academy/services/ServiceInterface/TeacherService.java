package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Teacher;

import java.util.Date;
import java.util.List;

public interface TeacherService extends RootService {

	List<Teacher> findAllTeachersService();

	Teacher findTeacherByIdService(long id);

	List<Teacher> findTeacherByPersonIdService(long id);

	List<Teacher> findTeacherByStartService(Date start);

	List<Teacher> findTeacherByFinishService(Date finish);

	List<Teacher> findByCriteriaService(int flags, Object... values);
}
