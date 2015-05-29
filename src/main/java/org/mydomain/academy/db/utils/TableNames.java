package org.mydomain.academy.db.utils;

import org.springframework.stereotype.Service;

/**
 * {@link TableNames} contains table and column names for using them in SQL syntax
 *
 * @author IgorZ
 */
public final class TableNames {
	public static final String FORM = "forms";
	public static final String GROUP = "groups";
	public static final String MARK_TYPE = "mark_type";
	public static final String MARK = "marks";
	public static final String PERSON = "persons";
	public static final String SCHEDULE = "schedule";
	public static final String SPECIALIZATION = "specializations";
	public static final String STUDENT = "students";
	public static final String SUBJECT = "subjects";
	public static final String TEACHER = "teachers";
	public static final String TEACHER_SUBJECT = "teachers_subjects";

	/**
	 * Contains column names for a {@link #FORM} table
	 */
	public static final class FormColumns {
		public static final String ID = "id";
		public static final String NAME = "name";
	}

	/**
	 * Contains column names for a {@link #GROUP} table
	 */
	public static final class GroupColumns {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String FORM_ID = "form_id";
		public static final String SPECIALIZATION_ID = "specialization_id";

	}

	/**
	 * Contains column names for a {@link #MARK_TYPE} table
	 */
	public static final class MarkTypeColumns {
		public static final String ID = "id";
		public static final String DESCRIPTION = "description";
	}

	/**
	 * Contains column names for a {@link #MARK} table
	 */
	public static final class MarkColumns {
		public static final String ID = "id";
		public static final String MARK_TYPE_ID = "markType_id";
		public static final String TEACHER_ID = "teacher_id";
		public static final String STUDENT_ID = "student_id";
		public static final String GROUP_ID = "group_id";
		public static final String SUBJECT_ID = "subject_id";
		public static final String FORM_ID = "Forms_id";
		public static final String DATE = "Mark_date";
	}

	/**
	 * Contains column names for a {@link #PERSON} table
	 */
	public static final class PersonColumns {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String BIRTHDAY = "birthday";
		public static final String PASSPORT = "passport";
	}

	/**
	 * Contains column names for a {@link #SCHEDULE} table
	 */
	public static final class ScheduleColumns {
		public static final String ID = "id";
		public static final String SUBJECT_ID = "subject_id";
		public static final String TEACHER_ID = "teacher_id";
		public static final String GROUP_ID = "group_id";
		public static final String SCHEDULE_DAY = "schedule_day";
		public static final String CHIS_ZNAM = "chis_znam";
		public static final String LENTA = "lenta";

		public static enum DAY_ENUM {
			Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
		}
		;

		public static enum CHIS_ZNAM_ENUM {
			CH, ZN
		}
		;

	}

	/**
	 * Contains column names for a {@link #SPECIALIZATION} table
	 */
	public static final class SpecializationsColumns {
		public static final String ID = "id";
		public static final String NAME = "name";
	}

	/**
	 * Contains column names for a {@link #STUDENT} table
	 */
	public static final class StudentColumns {
		public static final String ID = "id";
		public static final String PERSON_ID = "person_id";
		public static final String GROUP_ID = "group_id";
		public static final String START = "start";
		public static final String FINISH = "finish";
	}

	/**
	 * Contains column names for a {@link #SUBJECT} table
	 */
	public static final class SubjectColumns {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String SPECIALIZATION_ID = "specialization_id";
	}

	/**
	 * Contains column names for a {@link #TEACHER} table
	 */
	public static final class TeacherColumns {
		public static final String ID = "id";
		public static final String PERSON_ID = "person_id";
		public static final String START = "start";
		public static final String FINISH = "finish";
	}

	/**
	 * Contains column names for a {@link #TEACHER_SUBJECT} table
	 */
	public static final class Teacher_SubjectColumns {
		public static final String ID = "id";
		public static final String TEACHER_ID = "teacher_id";
		public static final String SUBJECT_ID = "subject_id";
	}
}