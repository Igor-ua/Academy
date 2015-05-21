package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Schedule;

import java.util.List;

public interface ScheduleService extends RootService {

	List<Schedule> findAllSchedulesService();

	Schedule findScheduleByIdService(long id);

	List<Schedule> findScheduleBySubjectIdService(long id);

	List<Schedule> findScheduleByTeacherIdService(long id);

	List<Schedule> findScheduleByGroupIdService(long id);

	List<Schedule> findScheduleByDayIdService(int day_id);

	List<Schedule> findScheduleByChisZnamService(int chisZnam_id);

	List<Schedule> findScheduleByLentaService(int lenta);

	List<Schedule> findByCriteriaService(int flags, Object... values);
}
