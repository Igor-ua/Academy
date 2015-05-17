package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Mark;

import java.util.Date;
import java.util.List;

public interface MarkService {

	List<Mark> findAllMarksService();

	Mark findMarkByIdService(long id);

	List<Mark> findMarkByMarkTypeIdService(long id);

	List<Mark> findMarkByTeacherIdService(long id);

	List<Mark> findMarkByStudentIdService(long id);

	List<Mark> findMarkByGroupIdService(long id);

	List<Mark> findMarkBySubjectIdService(long id);

	List<Mark> findMarkByFormIdService(long id);

	List<Mark> findMarkByDateService(Date date);

	boolean deleteMarkService(Mark mark);

	boolean saveMarkService(Mark mark);

	List<Mark> findByCriteriaService(int flags, Object... values);
}
