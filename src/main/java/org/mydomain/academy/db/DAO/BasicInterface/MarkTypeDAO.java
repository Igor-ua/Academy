package org.mydomain.academy.db.DAO.BasicInterface;


import org.mydomain.academy.db.entities.MarkType;

import java.util.List;

public interface MarkTypeDAO {

	int FLAG_ID = 1;
	int FLAG_NAME = FLAG_ID << 1;

	MarkType findMarkTypeById(long id);

	List<MarkType> findAllMarkTypes();

	List<MarkType> findMarkTypeByName(String name);

	boolean saveMarkType(MarkType mark_type);

	boolean deleteMarkType(MarkType mark_type);

	List<MarkType> findByCriteria(int flags, Object... values);
}
