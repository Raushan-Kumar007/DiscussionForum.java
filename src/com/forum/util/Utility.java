package com.forum.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utility {
	public static Date getCurrentDate() {
		return Calendar.getInstance(TimeZone.getTimeZone("IST")).getTime();
	}
	
	public static boolean isNotNullAndEmpty(String arg) {
		if (arg != null && !arg.equals("")) {
			return true;
		}
		return false;
	}
}
