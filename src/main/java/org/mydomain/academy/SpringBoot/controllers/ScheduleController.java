package org.mydomain.academy.SpringBoot.controllers;

import org.mydomain.academy.SpringBoot.utils.PageWrapper;
import org.mydomain.academy.db.entities.*;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.mydomain.academy.services.impls.JPAServiceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/db/schedule")
public class ScheduleController {

	@Autowired
	private JPASubjectService jpaSubjectService;
	@Autowired
	private JPATeacherService jpaTeacherService;
	@Autowired
	private JPAGroupService jpaGroupService;
	@Autowired
	private JPAScheduleService jpaScheduleService;

	private StringDateFormatter sdf;

	@Autowired
	public void setSdf(BasicStringDateFormatter sdf) {
		this.sdf = sdf;
	}

	private static final String SCHEDULE_ROUTE = "/fragments/entities/schedule";

	@RequestMapping(value = {"", "/"})
	public String scheduleRootPage() {
		return SCHEDULE_ROUTE + "/schedule";
	}

	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public String findAllSchedules(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Schedule> page = new PageWrapper<>(jpaScheduleService.findAllSchedulesService(pageable),
				"/db/schedule/show_all");
		modelMap.addAttribute("page", page);
		return SCHEDULE_ROUTE + "/schedulelist";
	}

	@RequestMapping(
			value = "/show",
			params = {"id"},
			method = RequestMethod.POST)
	public String showScheduleById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("schedule", jpaScheduleService.findScheduleByIdService(id));
		return "/fragments/entities/schedule/schedulelist";
	}

	@RequestMapping(value = "/find")
	public String findSchedule() {
		return SCHEDULE_ROUTE + "/find_schedule";
	}

	@RequestMapping(
			value = "/save",
			params = {"id"},
			method = RequestMethod.GET)
	public String saveNewSchedule(@RequestParam(value = "id") String id,
								  ModelMap modelMap) {

		List<Subject> subjects = jpaSubjectService.findAllSubjectsService();
		modelMap.addAttribute("subjects", subjects);

		List<Teacher> teachers = jpaTeacherService.findAllTeachersService();
		modelMap.addAttribute("teachers", teachers);

		List<Group> groups = jpaGroupService.findAllGroupsService();
		modelMap.addAttribute("groups", groups);

		modelMap.addAttribute("id", id);
		return SCHEDULE_ROUTE + "/save_schedule";
	}

	@RequestMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			value = "/save",
			params = {"id", "subject_id", "teacher_id", "group_id", "day", "chisznam", "lenta"},
			method = RequestMethod.POST)
	@ResponseBody
	public boolean saveScheduleById(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "subject_id") String subject_id,
			@RequestParam(value = "teacher_id") String teacher_id,
			@RequestParam(value = "group_id") String group_id,
			@RequestParam(value = "day") String day,
			@RequestParam(value = "chisznam") String chisznam,
			@RequestParam(value = "lenta") String lenta) {
		Schedule schedule = new Schedule();
		if (!id.equals("")) {
			schedule.setId(Long.parseLong(id));
		}

		schedule.setSubject(jpaSubjectService.findSubjectByIdService(Long.parseLong(subject_id)));
		schedule.setTeacher(jpaTeacherService.findTeacherByIdService(Long.parseLong(teacher_id)));
		schedule.setGroup(jpaGroupService.findGroupByIdService(Long.parseLong(group_id)));
		schedule.setDay(Integer.parseInt(day));
		schedule.setChisZnam(Integer.parseInt(chisznam));
		schedule.setLenta(Integer.parseInt(lenta));

		return jpaScheduleService.saveService(schedule);
	}

	@RequestMapping(
			value = "/find",
			params = {"groupName"})
	public String findByAny(
			@RequestParam(value = "groupName") String groupName,
			ModelMap modelMap,
			Pageable pageable) {
		String url = "/db/schedule/find" + "?groupName=" + groupName;
		url = url.replaceAll(" ", "%20");
		PageWrapper<Schedule> page = new PageWrapper<>(
				jpaScheduleService.findByAny(groupName, pageable), url);
		modelMap.addAttribute("page", page);
		return SCHEDULE_ROUTE + "/schedulelist";
	}

	@RequestMapping(
			value = "/delete",
			params = {"id"},
			method = RequestMethod.GET)
	public String deleteSchedule(@RequestParam(value = "id") long id,
								 ModelMap modelMap, Pageable pageable) {
		jpaScheduleService.deleteService(jpaScheduleService.findScheduleByIdService(id));
		PageWrapper<Schedule> page = new PageWrapper<>(
				jpaScheduleService.findAllSchedulesService(pageable), "/db/schedule/delete");
		modelMap.addAttribute("page", page);
		return SCHEDULE_ROUTE + "/delete_schedule";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteSchedule(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Schedule> page = new PageWrapper<>(
				jpaScheduleService.findAllSchedulesService(pageable), "/db/schedule/delete");
		modelMap.addAttribute("page", page);
		return SCHEDULE_ROUTE + "/delete_schedule";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateSchedule(ModelMap modelMap, Pageable pageable) {
		PageWrapper<Schedule> page = new PageWrapper<>(
				jpaScheduleService.findAllSchedulesService(pageable), "/db/schedule/update");
		modelMap.addAttribute("page", page);
		return SCHEDULE_ROUTE + "/update_schedule";
	}
}