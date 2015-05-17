package org.mydomain.academy.db.utils.formatters;

import java.text.ParseException;
import java.util.Date;

public interface StringDateFormatter {

	Date parseToDate(String str_date) throws ParseException;

	String parseToString(Date date);

}
