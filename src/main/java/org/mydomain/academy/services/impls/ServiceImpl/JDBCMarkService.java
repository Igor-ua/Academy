package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.MarkDAO;
import org.mydomain.academy.db.entities.Mark;
import org.mydomain.academy.services.ServiceInterface.MarkService;

import java.util.Date;
import java.util.List;

public class JDBCMarkService implements MarkService {

	private static final Logger log = Logger.getLogger(JDBCMarkService.class);

	private MarkDAO markDAO;

	public JDBCMarkService(MarkDAO markDAO) {
		this.markDAO = markDAO;

		log.info("MarkService has been initialized.");
	}

	private boolean validateMark(Mark mark) {
		if (mark.getDate().after(new Date())) {
			log.error("Mark validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Mark> findAllMarksService() {
		return markDAO.findAllMarks();
	}

	@Override
	public Mark findMarkByIdService(long id) {
		return markDAO.findMarkById(id);
	}

	@Override
	public List<Mark> findMarkByMarkTypeIdService(long id) {
		return markDAO.findMarkByMarkTypeId(id);
	}

	@Override
	public List<Mark> findMarkByTeacherIdService(long id) {
		return markDAO.findMarkByTeacherId(id);
	}

	@Override
	public List<Mark> findMarkByStudentIdService(long id) {
		return markDAO.findMarkByStudentId(id);
	}

	@Override
	public List<Mark> findMarkByGroupIdService(long id) {
		return markDAO.findMarkByGroupId(id);
	}

	@Override
	public List<Mark> findMarkBySubjectIdService(long id) {
		return markDAO.findMarkBySubjectId(id);
	}

	@Override
	public List<Mark> findMarkByFormIdService(long id) {
		return markDAO.findMarkByFormId(id);
	}

	@Override
	public List<Mark> findMarkByDateService(Date date) {
		return markDAO.findMarkByDate(date);
	}

	@Override
	public boolean deleteService(Object object) {
		return object instanceof Mark && markDAO.deleteMark((Mark) object);
	}

	@Override
	public boolean saveService(Object object) {
		return object instanceof Mark && validateMark((Mark) object) && markDAO.saveMark((Mark) object);
	}

	@Override
	public List<Mark> findByCriteriaService(int flags, Object... values) {
		return markDAO.findByCriteria(flags, values);
	}
}
