package ar.com.fdv.rentalBusiness.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	public static Date addHours(Integer hours){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}
}
