package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.MarkTypeDAO;
import org.mydomain.academy.db.entities.MarkType;
import org.mydomain.academy.services.ServiceInterface.MarkTypeService;

import java.util.List;

public class JDBCMarkTypeService implements MarkTypeService {

	private static final Logger log = Logger.getLogger(JDBCMarkTypeService.class);

	private MarkTypeDAO markTypeDAO;

	public JDBCMarkTypeService(MarkTypeDAO markTypeDAO) {
		this.markTypeDAO = markTypeDAO;
		log.info("MarkTypeService has been initialized.");
	}

	private boolean validateMarkType(MarkType mark_type) {
		if (!mark_type.getName().matches("[ 0-9A-Za-z\\-]{0,}")) {
			log.error("MarkType validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<MarkType> findAllMarkTypesService() {
		return markTypeDAO.findAllMarkTypes();
	}

	@Override
	public MarkType findMarkTypeByIdService(long id) {
		return markTypeDAO.findMarkTypeById(id);
	}

	@Override
	public List<MarkType> findMarkTypeByNameService(String name) {
		return markTypeDAO.findMarkTypeByName(name);
	}

	@Override
	public boolean deleteService(Object object) {
		return object instanceof MarkType &&
				markTypeDAO.deleteMarkType((MarkType) object);
	}

	@Override
	public boolean saveService(Object object) {
		return object instanceof MarkType &&
				validateMarkType((MarkType) object) && markTypeDAO.saveMarkType((MarkType) object);
	}

	@Override
	public List<MarkType> findByCriteriaService(int flags, Object... values) {
		return markTypeDAO.findByCriteria(flags, values);
	}
}
