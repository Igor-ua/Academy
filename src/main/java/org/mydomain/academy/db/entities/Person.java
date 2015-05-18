package org.mydomain.academy.db.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.mydomain.academy.SpringBoot.view.View;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component

@Entity
@Table(name = "Person_sql")
public class Person {

	@Id
	@GeneratedValue
	private long id;

	@JsonView(View.Summary.class)
	private String name;
	@JsonView(View.Summary.class)
	private Date birthday;
	@JsonView(View.Summary.class)
	private String passport;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Set<Student> studentSet;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Set<Teacher> teacherSet;

	@Transient
	private StringDateFormatter sdf;

	public Person() {
		sdf = new BasicStringDateFormatter();
		this.birthday = new Date();
		studentSet = new HashSet<Student>();
		teacherSet = new HashSet<Teacher>();
	}

	public Person(String name, Date birthday, String passport) {
		sdf = new BasicStringDateFormatter();
		this.name = name;
		this.birthday = birthday;
		this.passport = passport;
		studentSet = new HashSet<Student>();
		teacherSet = new HashSet<Teacher>();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassport() {
		return passport;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Person person = (Person) o;

		if (id != person.id) return false;
		if (birthday != null ? !birthday.equals(person.birthday) : person.birthday != null) return false;
		if (name != null ? !name.equals(person.name) : person.name != null) return false;
		if (passport != null ? !passport.equals(person.passport) : person.passport != null) return false;
		if (studentSet != null ? !studentSet.equals(person.studentSet) : person.studentSet != null) return false;
		if (teacherSet != null ? !teacherSet.equals(person.teacherSet) : person.teacherSet != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (passport != null ? passport.hashCode() : 0);
		result = 31 * result + (studentSet != null ? studentSet.hashCode() : 0);
		result = 31 * result + (teacherSet != null ? teacherSet.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				", birthday=" + sdf.parseToString(birthday) +
				", passport='" + passport + '\'' +
				", studentSet=" + studentSet +
				", teacherSet=" + teacherSet +
				'}';
	}
}
//todo javadoc