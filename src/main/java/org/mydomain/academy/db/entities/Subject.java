package org.mydomain.academy.db.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component

@Entity
@Table(name = "Subject_sql")
public class Subject implements AcademyEntity {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	private Set<Mark> markSet;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	private Set<Schedule> scheduleSet;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Specialization specialization;

	@ManyToMany(mappedBy = "subjectSet")
	private Set<Teacher> teacherSet;

	@Transient
	private long specialization_id;

	public Subject() {
		markSet = new HashSet<Mark>();
		scheduleSet = new HashSet<Schedule>();
		teacherSet = new HashSet<Teacher>();
	}

	public Subject(String name, long specialization_id) {
		this.name = name;
		this.specialization_id = specialization_id;
		markSet = new HashSet<Mark>();
		scheduleSet = new HashSet<Schedule>();
		teacherSet = new HashSet<Teacher>();
	}

	public Subject(String name, Specialization specialization) {
		this.name = name;
		this.specialization = specialization;
		markSet = new HashSet<Mark>();
		scheduleSet = new HashSet<Schedule>();
		teacherSet = new HashSet<Teacher>();
		this.specialization_id = specialization.getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
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

	public boolean addTeacher(Teacher t) {
		return teacherSet.add(t);
	}

	public boolean removeTeacher(Teacher t) {
		return teacherSet.remove(t);
	}

	public Set<Teacher> getTeacherSet() {
		return teacherSet;
	}

	public void setTeacherSet(Set<Teacher> teacherSet) {
		this.teacherSet = teacherSet;
	}

	public long getSpecialization_id() {
		return specialization_id;
	}

	public void setSpecialization_id(long specialization_id) {
		this.specialization_id = specialization_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Subject subject = (Subject) o;

		if (id != subject.id) return false;
		if (markSet != null ? !markSet.equals(subject.markSet) : subject.markSet != null) return false;
		if (name != null ? !name.equals(subject.name) : subject.name != null) return false;
		if (scheduleSet != null ? !scheduleSet.equals(subject.scheduleSet) : subject.scheduleSet != null) return false;
		if (specialization != null ? !specialization.equals(subject.specialization) : subject.specialization != null)
			return false;
		if (teacherSet != null ? !teacherSet.equals(subject.teacherSet) : subject.teacherSet != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Subject{" +
				"id=" + id +
				", name='" + name + '\'' +
				", markSet=" + markSet +
				", scheduleSet=" + scheduleSet +
				", teacherSet=" + teacherSet +
				'}';
	}
}