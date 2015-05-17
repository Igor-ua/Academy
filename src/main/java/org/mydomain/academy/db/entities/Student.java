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
@Table(name = "Student_sql")
public class Student {
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Person person;

	@ManyToOne
	private Group group;

	private Date start;
	private Date finish;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Set<Mark> markSet = new HashSet<Mark>();

	@Transient
	private long person_id;
	@Transient
	private long group_id;

	@Transient
	private StringDateFormatter sdf;

	public Student() {
		sdf = new BasicStringDateFormatter();
		this.start = new Date();
		this.finish = new Date();
	}

	public Student(long person_id, long group_id, Date start, Date finish) {
		sdf = new BasicStringDateFormatter();
		this.start = start;
		this.finish = finish;
		this.person_id = person_id;
		this.group_id = group_id;
	}

	public Student(Person person, Group group, Date start, Date finish) {
		sdf = new BasicStringDateFormatter();
		this.start = start;
		this.finish = finish;
		this.person = person;
		this.group = group;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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

	public long getPerson_id() {
		return person_id;
	}

	public void setPerson_id(long person_id) {
		this.person_id = person_id;
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

		Student student = (Student) o;

		if (id != student.id) return false;
		if (finish != null ? !finish.equals(student.finish) : student.finish != null) return false;
		if (group != null ? !group.equals(student.group) : student.group != null) return false;
		if (markSet != null ? !markSet.equals(student.markSet) : student.markSet != null) return false;
		if (person != null ? !person.equals(student.person) : student.person != null) return false;
		if (start != null ? !start.equals(student.start) : student.start != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (person != null ? person.hashCode() : 0);
		result = 31 * result + (group != null ? group.hashCode() : 0);
		result = 31 * result + (start != null ? start.hashCode() : 0);
		result = 31 * result + (finish != null ? finish.hashCode() : 0);
		result = 31 * result + (markSet != null ? markSet.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", start=" + sdf.parseToString(start) +
				", finish=" + sdf.parseToString(finish) +
				", markSet=" + markSet +
				'}';
	}
}
//todo javadoc