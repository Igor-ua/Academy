package org.mydomain.academy.db.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component

@Entity
@Table(name = "Mark_sql")
public class Mark {

	@Id
	@GeneratedValue
	private long id;
	private Date date;

	@ManyToOne
	private MarkType markType;

	@ManyToOne
	private Teacher teacher;

	@ManyToOne
	private Student student;

	@ManyToOne
	private Group group;

	@ManyToOne
	private Subject subject;

	@ManyToOne
	private Form form;

	@Transient
	private long markTypeId;
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
		this.markTypeId = markType_id;
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

	public long getMarkTypeId() {
		return markTypeId;
	}

	public void setMarkTypeId(long markTypeId) {
		this.markTypeId = markTypeId;
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
	public String toString() {
		return "Mark{" +
				"id=" + id +
				", date=" + date +
				'}';
	}
}
//todo javadoc