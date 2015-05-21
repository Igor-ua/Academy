package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Specialization;

import java.util.List;

public interface SpecializationService extends RootService {

	List<Specialization> findAllSpecializationsService();

	Specialization findSpecializationByIdService(long id);

	List<Specialization> findSpecializationByNameService(String name);

	List<Specialization> findByCriteriaService(int flags, Object... values);
}
