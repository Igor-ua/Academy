package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Form;

import java.util.List;

public interface FormService extends RootService {

	List<Form> findAllFormsService();

	Form findFormByIdService(long id);

	List<Form> findFormByNameService(String name);

	List<Form> findByCriteriaService(int flags, Object... values);
}