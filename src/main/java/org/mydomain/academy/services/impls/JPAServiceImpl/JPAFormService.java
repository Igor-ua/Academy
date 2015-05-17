package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPAFormDAO;
import org.mydomain.academy.db.entities.Form;
import org.mydomain.academy.services.ServiceInterface.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPAFormService implements FormService {

	private static final Logger log = Logger.getLogger(JPAFormService.class);

	private JPAFormDAO jpaFormDAO;

	@Autowired
	public void setJpaFormDAO(JPAFormDAO jpaFormDAO) {
		this.jpaFormDAO = jpaFormDAO;
	}

	public JPAFormService() {
	}

	private boolean validateForm(Form form) {
		if (!form.getName().matches("[ 0-9A-Za-zР-пр-џ\\-]{0,}")) {
			log.error("Form validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Form> findAllFormsService() {
		return jpaFormDAO.findAll();
	}

	@Override
	public Form findFormByIdService(long id) {
		return jpaFormDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<Form> findFormByNameService(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteFormService(Form form) {
		try {
			jpaFormDAO.delete(form);
			return true;
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	public boolean saveFormService(Form form) {
		try {
			if (validateForm(form)) {
				jpaFormDAO.save(form);
				return true;
			}
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	@Deprecated
	public List<Form> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public List<Form> findByAny(String name) {
		return jpaFormDAO.findByName(name);
	}
}
