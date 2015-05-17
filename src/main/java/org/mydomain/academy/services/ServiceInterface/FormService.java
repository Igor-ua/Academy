package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Form;

import java.util.List;

public interface FormService {

	List<Form> findAllFormsService();

	Form findFormByIdService(long id);

	List<Form> findFormByNameService(String name);

	boolean deleteFormService(Form form);

	boolean saveFormService(Form form);

	List<Form> findByCriteriaService(int flags, Object... values);
}