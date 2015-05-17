package org.mydomain.academy.db.DAO.BasicInterface;


import org.mydomain.academy.db.entities.Mark;

import java.util.Date;
import java.util.List;

public interface MarkDAO {

	int FLAG_ID = 1;
	int FLAG_MARK_TYPE = FLAG_ID << 1;
	int FLAG_TEACHER = FLAG_MARK_TYPE << 1;
	int FLAG_STUDENT = FLAG_TEACHER << 1;
	int FLAG_GROUP = FLAG_STUDENT << 1;
	int FLAG_SUBJECT = FLAG_GROUP << 1;
	int FLAG_FORM = FLAG_SUBJECT << 1;
	int FLAG_DATE = FLAG_FORM << 1;

	Mark findMarkById(long id);

	List<Mark> findAllMarks();

	List<Mark> findMarkByTeacherId(long id);

	List<Mark> findMarkByStudentId(long id);

	List<Mark> findMarkByGroupId(long id);

	List<Mark> findMarkBySubjectId(long id);

	List<Mark> findMarkByFormId(long id);

	List<Mark> findMarkByMarkTypeId(long id);

	List<Mark> findMarkByDate(Date date);

	boolean saveMark(Mark mark);

	boolean deleteMark(Mark mark);

	List<Mark> findByCriteria(int flags, Object... values);
}
//todo javadoc