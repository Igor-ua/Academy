package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPAMarkTypeDAO;
import org.mydomain.academy.db.entities.MarkType;
import org.mydomain.academy.services.ServiceInterface.MarkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPAMarkTypeService implements MarkTypeService{

	private static final Logger log = Logger.getLogger(JPAMarkTypeService.class);

	private JPAMarkTypeDAO jpaMarkTypeDAO;

	@Autowired
	public void setJpaMarkTypeDAO(JPAMarkTypeDAO jpaMarkTypeDAO) {
		this.jpaMarkTypeDAO = jpaMarkTypeDAO;
	}

	public JPAMarkTypeService() {
	}

	private boolean validateMarkType(MarkType mark_type) {
		if (!mark_type.getName().matches("[ 0-9A-Za-zР-пр-џ\\-]{0,}")) {
			log.error("MarkType validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<MarkType> findAllMarkTypesService() {
		return jpaMarkTypeDAO.findAll();
	}

	public Page<MarkType> findAllMarkTypesService(Pageable pageable) {
		return jpaMarkTypeDAO.findAll(pageable);
	}

	@Override
	public MarkType findMarkTypeByIdService(long id) {
		return jpaMarkTypeDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<MarkType> findMarkTypeByNameService(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteMarkTypeService(MarkType markType) {
		try {
			jpaMarkTypeDAO.delete(markType);
			return true;
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	public boolean saveMarkTypeService(MarkType markType) {
		try {
			if (validateMarkType(markType)) {
				jpaMarkTypeDAO.save(markType);
				return true;
			}
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	@Deprecated
	public List<MarkType> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public Page<MarkType> findByAny(String name, Pageable pageable) {
		return jpaMarkTypeDAO.findByName(name, pageable);
	}
}
