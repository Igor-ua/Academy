package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.MarkType;

import java.util.List;

public interface MarkTypeService extends RootService {

	List<MarkType> findAllMarkTypesService();

	MarkType findMarkTypeByIdService(long id);

	List<MarkType> findMarkTypeByNameService(String name);

	List<MarkType> findByCriteriaService(int flags, Object... values);
}
