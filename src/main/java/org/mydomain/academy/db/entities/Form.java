package org.mydomain.academy.db.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component

@Entity
@Table(name = "Form_sql")
public class Form {

	@Id
	@GeneratedValue
	private long id;
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "form_id")
	private Set<Group> groupSet;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "form_id")
	private Set<Mark> markSet;

	public Form() {
		groupSet = new HashSet<Group>();
		markSet = new HashSet<Mark>();
	}

	public Form(String name) {
		this.name = name;
		groupSet = new HashSet<Group>();
		markSet = new HashSet<Mark>();
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Form form = (Form) o;

		if (id != form.id) return false;
		if (groupSet != null ? !groupSet.equals(form.groupSet) : form.groupSet != null) return false;
		if (markSet != null ? !markSet.equals(form.markSet) : form.markSet != null) return false;
		if (name != null ? !name.equals(form.name) : form.name != null) return false;

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
		return "Form{" +
				"id=" + id +
				", name='" + name + '\'' +
				", groupSet=" + groupSet +
				", markSet=" + markSet +
				'}';
	}
}
//todo javadoc