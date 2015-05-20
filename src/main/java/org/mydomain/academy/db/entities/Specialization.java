package org.mydomain.academy.db.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component

@Entity
@Table(name = "Specialization_sql")
public class Specialization {

	@Id
	@GeneratedValue
	private long id;
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "specialization_id")
	private Set<Group> groupSet;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "specialization_id")
	private Set<Subject> subjectSet;

	public Specialization() {
		groupSet = new HashSet<Group>();
		subjectSet = new HashSet<Subject>();
	}

	public Specialization(String name) {
		this.name = name;
		groupSet = new HashSet<Group>();
		subjectSet = new HashSet<Subject>();
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

	public boolean addGroup(Group g) {
		return groupSet.add(g);
	}

	public boolean removeGroup(Group g) {
		return groupSet.remove(g);
	}

	public Set<Group> getGroupSet() {
		return groupSet;
	}

	public void setGroupSet(Set<Group> groupSet) {
		this.groupSet = groupSet;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Specialization that = (Specialization) o;

		if (id != that.id) return false;
		if (groupSet != null ? !groupSet.equals(that.groupSet) : that.groupSet != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (subjectSet != null ? !subjectSet.equals(that.subjectSet) : that.subjectSet != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Specialization{" +
				"id=" + id +
				", name='" + name + '\'' +
				", groupSet=" + groupSet +
				", subjectSet=" + subjectSet +
				'}';
	}
}
//todo javadoc