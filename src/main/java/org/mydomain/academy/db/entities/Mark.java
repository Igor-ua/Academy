package org.mydomain.academy.db.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component

@Entity
@Table(name = "Mark_sql")
public class Mark implements AcademyEntity {

	@Id
	@GeneratedValue
	private long id;
	private Date date;

	@ManyToOne
	@JoinColumn(nullable = false)
	private MarkType markType;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Student student;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Group group;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Subject subject;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Form form;

	@Transient
	private long markType_id;
	@Transient
	private long teacher_id;
	@Transient
	private long student_id;
	@Transient
	private long group_id;
	@Transient
	private long subject_id;
	@Transient
	private long form_id;

	public Mark() {
	}

	public Mark(long markType_id, long teacher_id, long student_id,
				long group_id, long subject_id, long form_id, Date date) {
		this.markType_id = markType_id;
		this.teacher_id = teacher_id;
		this.student_id = student_id;
		this.group_id = group_id;
		this.subject_id = subject_id;
		this.form_id = form_id;
		this.date = date;
	}

	public Mark(MarkType markType, Teacher teacher, Student student,
				Group group, Subject subject, Form form, Date date) {
		this.markType = markType;
		this.teacher = teacher;
		this.student = student;
		this.group = group;
		this.subject = subject;
		this.form = form;
		this.date = date;
		this.markType_id = markType.getId();
		this.teacher_id = teacher.getId();
		this.student_id = student.getId();
		this.group_id = group.getId();
		this.subject_id = subject.getId();
		this.form_id = form.getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MarkType getMarkType() {
		return markType;
	}

	public void setMarkType(MarkType markType) {
		this.markType = markType;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getMarkType_id() {
		return markType_id;
	}

	public void setMarkType_id(long markType_id) {
		this.markType_id = markType_id;
	}

	public long getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(long teacher_id) {
		this.teacher_id = teacher_id;
	}

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public long getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}

	public long getForm_id() {
		return form_id;
	}

	public void setForm_id(long form_id) {
		this.form_id = form_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Mark mark = (Mark) o;

		if (id != mark.id) return false;
		if (markType_id != mark.markType_id) return false;
		if (teacher_id != mark.teacher_id) return false;
		if (student_id != mark.student_id) return false;
		if (group_id != mark.group_id) return false;
		if (subject_id != mark.subject_id) return false;
		if (form_id != mark.form_id) return false;
		if (date != null ? !date.equals(mark.date) : mark.date != null) return false;
		if (markType != null ? !markType.equals(mark.markType) : mark.markType != null) return false;
		if (teacher != null ? !teacher.equals(mark.teacher) : mark.teacher != null) return false;
		if (student != null ? !student.equals(mark.student) : mark.student != null) return false;
		if (group != null ? !group.equals(mark.group) : mark.group != null) return false;
		if (subject != null ? !subject.equals(mark.subject) : mark.subject != null) return false;
		return !(form != null ? !form.equals(mark.form) : mark.form != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (markType != null ? markType.hashCode() : 0);
		result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
		result = 31 * result + (student != null ? student.hashCode() : 0);
		result = 31 * result + (group != null ? group.hashCode() : 0);
		result = 31 * result + (subject != null ? subject.hashCode() : 0);
		result = 31 * result + (form != null ? form.hashCode() : 0);
		result = 31 * result + (int) (markType_id ^ (markType_id >>> 32));
		result = 31 * result + (int) (teacher_id ^ (teacher_id >>> 32));
		result = 31 * result + (int) (student_id ^ (student_id >>> 32));
		result = 31 * result + (int) (group_id ^ (group_id >>> 32));
		result = 31 * result + (int) (subject_id ^ (subject_id >>> 32));
		result = 31 * result + (int) (form_id ^ (form_id >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Mark{" +
				"id=" + id +
				", date=" + date +
				'}';
	}
}