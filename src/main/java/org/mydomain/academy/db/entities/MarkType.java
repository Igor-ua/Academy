package org.mydomain.academy.db.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component

@Entity
@Table(name = "Mark_Type_sql")
public class MarkType implements AcademyEntity {

	@Id
	@GeneratedValue
	private long id;
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "mark_type_id")
	private Set<Mark> markSet;

	public MarkType() {
		markSet = new HashSet<Mark>();
	}

	public MarkType(String markType) {
		this.name = markType;
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

		MarkType mark_type = (MarkType) o;

		if (id != mark_type.id) return false;
		if (markSet != null ? !markSet.equals(mark_type.markSet) : mark_type.markSet != null) return false;
		if (name != null ? !name.equals(mark_type.name) : mark_type.name != null) return false;

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
		return "MarkType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", markSet=" + markSet +
				'}';
	}
}