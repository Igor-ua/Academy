package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPASpecializationDAO;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.services.ServiceInterface.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPASpecializationService implements SpecializationService {

	private static final Logger log = Logger.getLogger(JPASpecializationService.class);

	private JPASpecializationDAO jpaSpecializationDAO;

	@Autowired
	public void setJpaSpecializationDAO(JPASpecializationDAO jpaSpecializationDAO) {
		this.jpaSpecializationDAO = jpaSpecializationDAO;
	}

	public JPASpecializationService() {
	}

	private boolean validateSpecialization(Specialization specialization) {
		if (!specialization.getName().matches("[ A-Za-z�-��-�\\-]{0,}")) {
			log.error("Specialization validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Specialization> findAllSpecializationsService() {
		return jpaSpecializationDAO.findAll();
	}

	@Override
	public Specialization findSpecializationByIdService(long id) {
		return jpaSpecializationDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<Specialization> findSpecializationByNameService(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteSpecializationService(Specialization specialization) {
		try {
			jpaSpecializationDAO.delete(specialization);
			return true;
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	public boolean saveSpecializationService(Specialization specialization) {
		try {
			if (validateSpecialization(specialization)) {
				jpaSpecializationDAO.save(specialization);
				return true;
			}
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	@Deprecated
	public List<Specialization> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public List<Specialization> findByAny(String name) {
		return jpaSpecializationDAO.findByName(name);
	}
}
