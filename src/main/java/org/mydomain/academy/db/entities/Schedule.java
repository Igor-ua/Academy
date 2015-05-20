package org.mydomain.academy.db.entities;

import org.mydomain.academy.db.utils.TableNames;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component

@Entity
@Table(name = "Schedule_sql")
public class Schedule {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Subject subject;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Group group;

	private String day;
	private String chisZnam;
	private int lenta;

	@Transient
	private long subject_id;
	@Transient
	private long teacher_id;
	@Transient
	private long group_id;

	public Schedule() {
	}

	public Schedule(long subject_id, long teacher_id, long group_id, String day, String chisZnam, int lenta) {
		this.subject_id = subject_id;
		this.teacher_id = teacher_id;
		this.group_id = group_id;
		this.day = day;
		this.chisZnam = chisZnam;
		this.lenta = lenta;
	}

	public Schedule(Subject subject, Teacher teacher, Group group, String day, String chisZnam, int lenta) {
		this.subject = subject;
		this.teacher = teacher;
		this.group = group;
		this.day = day;
		this.chisZnam = chisZnam;
		this.lenta = lenta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setDay(int day_id) {
		this.day = TableNames.ScheduleColumns.DAY_ENUM.values()[day_id].toString();
	}

	public String getChisZnam() {
		return chisZnam;
	}

	public void setChisZnam(String chisZnam) {
		this.chisZnam = chisZnam;
	}

	public void setChisZnam(int chisZnam_id) {
		this.chisZnam = TableNames.ScheduleColumns.CHIS_ZNAM_ENUM.values()[chisZnam_id].toString();
	}

	public int getLenta() {
		return lenta;
	}

	public void setLenta(int lenta) {
		this.lenta = lenta;
	}

	public long getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}

	public long getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(long teacher_id) {
		this.teacher_id = teacher_id;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Schedule schedule = (Schedule) o;

		if (id != schedule.id) return false;
		if (lenta != schedule.lenta) return false;
		if (subject_id != schedule.subject_id) return false;
		if (teacher_id != schedule.teacher_id) return false;
		if (group_id != schedule.group_id) return false;
		if (subject != null ? !subject.equals(schedule.subject) : schedule.subject != null) return false;
		if (teacher != null ? !teacher.equals(schedule.teacher) : schedule.teacher != null) return false;
		if (group != null ? !group.equals(schedule.group) : schedule.group != null) return false;
		if (day != null ? !day.equals(schedule.day) : schedule.day != null) return false;
		return !(chisZnam != null ? !chisZnam.equals(schedule.chisZnam) : schedule.chisZnam != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (subject != null ? subject.hashCode() : 0);
		result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
		result = 31 * result + (group != null ? group.hashCode() : 0);
		result = 31 * result + (day != null ? day.hashCode() : 0);
		result = 31 * result + (chisZnam != null ? chisZnam.hashCode() : 0);
		result = 31 * result + lenta;
		result = 31 * result + (int) (subject_id ^ (subject_id >>> 32));
		result = 31 * result + (int) (teacher_id ^ (teacher_id >>> 32));
		result = 31 * result + (int) (group_id ^ (group_id >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Schedule{" +
				"id=" + id +
				", chisZnam='" + chisZnam + '\'' +
				", lenta=" + lenta +
				", day='" + day + '\'' +
				'}';
	}
}
//todo javadoc