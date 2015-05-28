package org.mydomain.academy.db.entities;

import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component

@Entity
@Table(name = "Teacher_sql")
public class Teacher implements AcademyEntity {

	@Id
	@GeneratedValue
	private long id;
	private Date start;
	private Date finish;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Person person;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id")
	private Set<Mark> markSet;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id")
	private Set<Schedule> scheduleSet;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "teacher_subject")
	private Set<Subject> subjectSet;

	@Transient
	private long person_id;

	@Transient
	private StringDateFormatter sdf;

	public Teacher() {
		sdf = new BasicStringDateFormatter();
		this.start = new Date();
		this.finish = new Date();
		scheduleSet = new HashSet<Schedule>();
		subjectSet = new HashSet<Subject>();
		markSet = new HashSet<Mark>();
	}

	public Teacher(long person_id, Date start, Date finish) {
		this.start = start;
		this.finish = finish;
		this.person_id = person_id;
		sdf = new BasicStringDateFormatter();
		scheduleSet = new HashSet<Schedule>();
		subjectSet = new HashSet<Subject>();
		markSet = new HashSet<Mark>();
	}

	public Teacher(Person person, Date start, Date finish) {
		this.start = start;
		this.finish = finish;
		this.person = person;
		this.person_id = person.getId();
		sdf = new BasicStringDateFormatter();
		this.person_id = person.getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean addMark(Mark m) {
		return markSet.add(m);
	}

	public boolean removeMark(Mark m) {
		return markSet.remove(m);
	}

	public Set<Mark> getMarkSet() {
		return markSet;
	}

	public void setMarkSet(Set<Mark> markSet) {
		this.markSet = markSet;
	}

	public boolean addSchedule(Schedule s) {
		return scheduleSet.add(s);
	}

	public boolean removeSchedule(Schedule s) {
		return scheduleSet.remove(s);
	}

	public Set<Schedule> getScheduleSet() {
		return scheduleSet;
	}

	public void setScheduleSet(Set<Schedule> scheduleSet) {
		this.scheduleSet = scheduleSet;
	}

	public boolean addSubject(Subject s) {
		return subjectSet.add(s);
	}

	public boolean removeSubject(Subject s) {
		return subjectSet.remove(s);
	}

	public Set<Subject> getSubjectSet() {
		return subjectSet;
	}

	public void setSubjectSet(Set<Subject> subjectSet) {
		this.subjectSet = subjectSet;
	}

	public long getPerson_id() {
		return person_id;
	}

	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Teacher teacher = (Teacher) o;

		if (id != teacher.id) return false;
		if (finish != null ? !finish.equals(teacher.finish) : teacher.finish != null) return false;
		if (markSet != null ? !markSet.equals(teacher.markSet) : teacher.markSet != null) return false;
		if (person != null ? !person.equals(teacher.person) : teacher.person != null) return false;
		if (scheduleSet != null ? !scheduleSet.equals(teacher.scheduleSet) : teacher.scheduleSet != null) return false;
		if (start != null ? !start.equals(teacher.start) : teacher.start != null) return false;
		if (subjectSet != null ? !subjectSet.equals(teacher.subjectSet) : teacher.subjectSet != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (start != null ? start.hashCode() : 0);
		result = 31 * result + (finish != null ? finish.hashCode() : 0);
		result = 31 * result + (person != null ? person.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", start=" + sdf.parseToString(start) +
				", finish=" + sdf.parseToString(finish) +
				", markSet=" + markSet +
				", scheduleSet=" + scheduleSet +
				", subjectSet=" + subjectSet +
				'}';
	}
}