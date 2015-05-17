package org.mydomain.academy.db.DAO.BasicInterface;


import org.mydomain.academy.db.entities.Specialization;

import java.util.List;

public interface SpecializationDAO {

	int FLAG_ID = 1;
	int FLAG_NAME = FLAG_ID << 1;

	Specialization findSpecializationById(long id);

	List<Specialization> findAllSpecializations();

	List<Specialization> findSpecializationByName(String name);

	boolean saveSpecialization(Specialization specialization);

	boolean deleteSpecialization(Specialization specialization);

	List<Specialization> findByCriteria(int flags, Object... values);
}
//todo javadoc