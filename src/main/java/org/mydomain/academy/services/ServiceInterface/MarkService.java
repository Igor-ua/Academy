package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Mark;

import java.util.Date;
import java.util.List;

public interface MarkService extends RootService {

	List<Mark> findAllMarksService();

	Mark findMarkByIdService(long id);

	List<Mark> findMarkByMarkTypeIdService(long id);

	List<Mark> findMarkByTeacherIdService(long id);

	List<Mark> findMarkByStudentIdService(long id);

	List<Mark> findMarkByGroupIdService(long id);

	List<Mark> findMarkBySubjectIdService(long id);

	List<Mark> findMarkByFormIdService(long id);

	List<Mark> findMarkByDateService(Date date);

	List<Mark> findByCriteriaService(int flags, Object... values);
}
