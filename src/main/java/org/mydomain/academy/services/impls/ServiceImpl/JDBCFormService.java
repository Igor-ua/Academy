package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.FormDAO;
import org.mydomain.academy.db.entities.Form;
import org.mydomain.academy.services.ServiceInterface.FormService;

import java.util.List;

public class JDBCFormService implements FormService {

	private static final Logger log = Logger.getLogger(JDBCFormService.class);

	private FormDAO formDAO;

	public JDBCFormService(FormDAO formDAO) {
		this.formDAO = formDAO;
		log.info("FormService has been initialized.");
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
		return formDAO.findAllForms();
	}

	@Override
	public Form findFormByIdService(long id) {
		return formDAO.findFormById(id);
	}

	@Override
	public List<Form> findFormByNameService(String name) {
		return formDAO.findFormByName(name);
	}

	@Override
	public boolean deleteFormService(Form form) {
		return formDAO.deleteForm(form);
	}

	@Override
	public boolean saveFormService(Form form) {
		return validateForm(form) && formDAO.saveForm(form);
	}

	@Override
	public List<Form> findByCriteriaService(int flags, Object... values) {
		return formDAO.findByCriteria(flags, values);
	}
}
