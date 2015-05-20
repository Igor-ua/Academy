package org.mydomain.academy.db.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component

@Entity
@Table(name = "Group_sql")
public class Group {

	@Id
	@GeneratedValue
	private long id;
	private String name;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Form form;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Specialization specialization;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "group_id")
	private Set<Schedule> scheduleSet;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "group_id")
	private Set<Mark> markSet;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "group_id")
	private Set<Student> studentSet;

	@Transient
	private long form_id;
	@Transient
	private long specialization_id;

	public Group() {
		scheduleSet = new HashSet<Schedule>();
		markSet = new HashSet<Mark>();
		studentSet = new HashSet<Student>();
	}

	public Group(String name, long form_id, long specialization_id) {
		this.name = name;
		this.form_id = form_id;
		this.specialization_id = specialization_id;
		scheduleSet = new HashSet<Schedule>();
		markSet = new HashSet<Mark>();
		studentSet = new HashSet<Student>();
	}

	public Group(String name, Form form, Specialization specialization) {
		this.name = name;
		this.form = form;
		this.specialization = specialization;
		scheduleSet = new HashSet<Schedule>();
		markSet = new HashSet<Mark>();
		studentSet = new HashSet<Student>();
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

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
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

	public boolean addStudent(Student s) {
		return studentSet.add(s);
	}

	public boolean removeStudent(Student s) {
		return studentSet.remove(s);
	}

	public Set<Student> getStudentSet() {
		return studentSet;
	}

	public void setStudentSet(Set<Student> studentSet) {
		this.studentSet = studentSet;
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

	public long getForm_id() {
		return form_id;
	}

	public void setForm_id(long form_id) {
		this.form_id = form_id;
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

		Group group = (Group) o;

		if (id != group.id) return false;
		if (form != null ? !form.equals(group.form) : group.form != null) return false;
		if (markSet != null ? !markSet.equals(group.markSet) : group.markSet != null) return false;
		if (name != null ? !name.equals(group.name) : group.name != null) return false;
		if (scheduleSet != null ? !scheduleSet.equals(group.scheduleSet) : group.scheduleSet != null) return false;
		if (specialization != null ? !specialization.equals(group.specialization) : group.specialization != null)
			return false;
		if (studentSet != null ? !studentSet.equals(group.studentSet) : group.studentSet != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (form != null ? form.hashCode() : 0);
		result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Group{" +
				"id=" + id +
				", name='" + name + '\'' +
				", scheduleSet=" + scheduleSet +
				", markSet=" + markSet +
				", studentSet=" + studentSet +
				'}';
	}
}
//todo javadoc