package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.SpecializationDAO;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.services.ServiceInterface.SpecializationService;

import java.util.List;

public class JDBCSpecializationService implements SpecializationService {

	private static final Logger log = Logger.getLogger(JDBCSpecializationService.class);

	private SpecializationDAO specializationDAO;

	public JDBCSpecializationService(SpecializationDAO specializationDAO) {
		this.specializationDAO = specializationDAO;

		log.info("SpecializationService has been initialized.");
	}

	private boolean validateSpecialization(Specialization specialization) {
		if (!specialization.getName().matches("[ A-Za-z\\-]{0,}")) {
			log.error("Specialization validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Specialization> findAllSpecializationsService() {
		return specializationDAO.findAllSpecializations();
	}

	@Override
	public Specialization findSpecializationByIdService(long id) {
		return specializationDAO.findSpecializationById(id);
	}

	@Override
	public List<Specialization> findSpecializationByNameService(String name) {
		return specializationDAO.findSpecializationByName(name);
	}

	@Override
	public boolean deleteService(Object object) {
		return object instanceof Specialization &&
				specializationDAO.deleteSpecialization((Specialization) object);
	}

	@Override
	public boolean saveService(Object object) {
		return object instanceof Specialization && validateSpecialization((Specialization) object)
				&& specializationDAO.saveSpecialization((Specialization) object);
	}

	@Override
	public List<Specialization> findByCriteriaService(int flags, Object... values) {
		return specializationDAO.findByCriteria(flags, values);
	}
}
