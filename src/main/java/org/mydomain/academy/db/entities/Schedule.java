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
	private Subject subject;

	@ManyToOne
	private Teacher teacher;

	@ManyToOne
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