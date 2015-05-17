package org.mydomain.academy.db.DAO.BasicInterface;


import org.mydomain.academy.db.entities.Schedule;

import java.util.List;

public interface ScheduleDAO {

	int FLAG_ID = 1;
	int FLAG_SUBJECT = FLAG_ID << 1;
	int FLAG_TEACHER = FLAG_SUBJECT << 1;
	int FLAG_GROUP = FLAG_TEACHER << 1;
	int FLAG_DAY = FLAG_GROUP << 1;
	int FLAG_CHIS_ZNAM = FLAG_DAY << 1;
	int FLAG_LENTA = FLAG_CHIS_ZNAM << 1;

	Schedule findScheduleById(long id);

	List<Schedule> findAllSchedules();

	List<Schedule> findScheduleBySubjectId(long id);

	List<Schedule> findScheduleByTeacherId(long id);

	List<Schedule> findScheduleByGroupId(long id);

	List<Schedule> findScheduleByDayId(int day_id);

	List<Schedule> findScheduleByChisZnam(int chisZnam_id);

	List<Schedule> findScheduleByLenta(int lenta);

	boolean saveSchedule(Schedule schedule);

	boolean deleteSchedule(Schedule schedule);

	List<Schedule> findByCriteria(int flags, Object... values);
}
//todo javadoc