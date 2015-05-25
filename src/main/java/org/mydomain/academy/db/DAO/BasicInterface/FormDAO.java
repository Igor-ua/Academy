package org.mydomain.academy.db.DAO.BasicInterface;

import org.mydomain.academy.db.entities.Form;

import java.util.List;

public interface FormDAO {

	int FLAG_ID = 1;                        // 0001
	int FLAG_NAME = FLAG_ID << 1;           // 0010

	Form findFormById(long id);

	List<Form> findAllForms();

	List<Form> findFormByName(String name);

	boolean saveForm(Form form);

	boolean deleteForm(Form form);

	List<Form> findByCriteria(int flags, Object... values);
}