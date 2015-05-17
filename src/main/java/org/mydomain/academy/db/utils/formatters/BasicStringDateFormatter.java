package org.mydomain.academy.db.utils.formatters;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mydomain.academy.db.utils.LogMessages.*;

@Service
public class BasicStringDateFormatter implements StringDateFormatter {

	private static final Logger log = Logger.getLogger(StringDateFormatter.class);
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public BasicStringDateFormatter() {
	}

	public Date parseToDate(String str_date) throws ParseException {
		log.debug(LOG_FORMATTER_STRING_TO_DATE + str_date);
		Date date = sdf.parse(str_date);
		log.debug(LOG_FORMATTER_STRING_TO_DATE_RESULT.toString() + date);
		return date;
	}

	@Override
	public String parseToString(Date date) {
		return sdf.format(date);
	}
}