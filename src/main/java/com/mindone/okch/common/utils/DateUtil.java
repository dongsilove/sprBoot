package com.mindone.okch.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 *  클래스
 * @author 정호열
 * @since 2010.10.17
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일        수정자       수정내용
 *  -------       --------    ---------------------------
 *   2010.10.17   정호열       최초 생성
 *   2013.12.19	표준프레임워크	공통컴포넌트 추가 적용 (패키지 변경)
 * </pre>
 */
public abstract class DateUtil {

	/**
	 * 현재 날짜를 yyyyMMdd 포맷으로 반환.
	 * @return String 현재 날짜.
	 */
	public static String getSysDate()  throws Exception {
		return getSysDate("yyyyMMdd");
	}

	/**
	 * 날짜 입력양식을 전달받아 현재 날짜를 반환.
	 * @param pattern 입력 양식.
	 * @return String 날짜 입력 양식.
	 */
	public static String getSysDate(String pattern)  throws Exception {
		return getDateString(new Date(), pattern);
	}

	/**
	 * Date 객체와 입력양식을 전달받아 날짜를 반환.
	 * @param date Date 객체.
	 * @param pattern 입력 양식.
	 * @return String 날짜.
	 */
	public static String getDateString(Date date, String pattern) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 숫자형태의 시간과 포맷을 전달받아 날짜를 반환.
	 * @param time 숫자형태의 시간
	 * @param pattern 시간 포맷
	 * @return String 날짜
	 */
	public static String getDateString(long time, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date d = new Date(time);
		return sdf.format(d);
	}

	public static Date getStringToDate(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date d;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid Date format: " + date);
		}
		return d;
	}

	public static LocalDateTime getStringToLocalDateTime(String date) {
		LocalDateTime d;
		try {
			d = getStringToLocalDateTime(date, "yyyy-MM-dd HH:mm");
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid LocalDateTime format: " + date);
		}
		return d;
	}

	public static LocalDateTime getStringToLocalDateTime(String date, String pattern) {
		LocalDateTime d;
		try {
			if (date == null) {
				return null;
			}
			d = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid LocalDateTime format: " + date);
		}
		return d;
	}

	/**
	 * LocalDateTime to String
	 * @param date
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getLocalDateTimeToString(LocalDateTime date) {
		String d;
		try {
			d = getLocalDateTimeToString(date, "yyyy-MM-dd HH:mm");
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid LocalDate format: " + date);
		}
		return d;
	}

	/**
	 * LocalDateTime to 날짜형식의 문자열로 변환한다.
	 * @param LocalDateTime 객체
	 * @param pattern 날짜형태
	 * @return pattern 형태의 String
	 */
	public static String getLocalDateTimeToString(LocalDateTime date, String pattern) {
		String d;
		try {
			if (date == null) {
				return null;
			}
			d = date.format(DateTimeFormatter.ofPattern(pattern));
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid LocalDate format: " + date);
		}
		return d;
	}

	public static LocalDate getStringToLocalDate(String date, String pattern) {
		LocalDate d;
		try {
			d = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid LocalDate format: " + date);
		}
		return d;
	}
	
	/**
	 * 입력받은 날에 year,month,day만큼 더한다.
	 * @param sDate 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param year
	 * @param month
	 * @param day
	 * @return Date
	 */
	public static Date addYearMonthDay2(String sDate, int year, int month, int day) {
		
		String dateStr = validChkDate(sDate);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		
		try {
			cal.setTime(sdf.parse(dateStr));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr);
		}
		
		if (year != 0)
			cal.add(Calendar.YEAR, year);
		
		if (month != 0)
			cal.add(Calendar.MONTH, month);
		
		if (day != 0)
			cal.add(Calendar.DATE, day);
		
		return cal.getTime();
		
	}

	/**
	 * 입력받은 날에 day만큼 더한다.
	 * @param dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param day
	 * @return Date
	 */
	public static Date addDay2(String dateStr, int day) {
		
		return addYearMonthDay2(dateStr, 0, 0, day);
		
	}
	
	/**
	 * 입력받은 날에 23시59분59초를 더한 시각을 얻는다.
	 * @param dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param day
	 * @return Date
	 */
	public static Date getEndTime(String dStr) {
		
		String dateStr = validChkDate(dStr);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		
		try {
			cal.setTime(sdf.parse(dateStr));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr);
		}
		
		cal.add(Calendar.HOUR_OF_DAY , 23);
		cal.add(Calendar.MINUTE, 59);
		cal.add(Calendar.SECOND, 59);
		
		return cal.getTime();
		
	}
	/**
	 * 입력받은 날에 23시59분59초를 더한 시각을 얻는다.
	 * @param dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param day
	 * @return LocalDateTime
	 */
	public static LocalDateTime getEndLocalDateTime(String dStr) {
		
		String dateStr = validChkDate(dStr);
		LocalDateTime r;
		
		r = LocalDateTime.parse(dateStr+" 23:59:59",DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"));
		
		return r;
		
	}
	

	/**
	 * 입력된 일자 문자열을 확인하고 8자리로 리턴
	 * @param sDate
	 * @return
	 */
	public static String validChkDate(String dateStr) {

		String _dateStr = dateStr;

		if (dateStr == null || !(dateStr.trim().length() == 8 || dateStr.trim().length() == 10)) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr);
		}

		if (dateStr.length() == 10) {
			_dateStr = StringUtil.removeMinusChar(dateStr);
		}

		return _dateStr;

	}
	
	/**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열 <code>dateStr1</code>과 <code>
	 * dateStr2</code> 사이의 일 수를 구한다.<br>
	 * <code>dateStr2</code>가 <code>dateStr1</code> 보다 과거 날짜일 경우에는
	 * 음수를 반환한다. 동일한 경우에는 0을 반환한다.</p>
	 *
	 * <pre>
	 * DateUtil.getDaysDiff("20060228","20060310") = 10
	 * DateUtil.getDaysDiff("20060101","20070101") = 365
	 * DateUtil.getDaysDiff("19990228","19990131") = -28
	 * DateUtil.getDaysDiff("20060801","20060802") = 1
	 * DateUtil.getDaysDiff("20060801","20060801") = 0
	 * </pre>
	 *
	 * @param  dateStr1 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param  dateStr2 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @return  일 수 차이.
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우.
	 *         입력 값이 <code>null</code>인 경우.
	 */
	public static int getDaysDiff(String sDate1, String sDate2) {
		String dateStr1 = validChkDate(sDate1);
		String dateStr2 = validChkDate(sDate2);

		if (!checkDate(sDate1) || !checkDate(sDate2)) {
			throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

		Date date1 = null;
		Date date2 = null;
		try {
			date1 = sdf.parse(dateStr1);
			date2 = sdf.parse(dateStr2);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format: args[0]=" + dateStr1 + " args[1]=" + dateStr2);
		}

		if (date1 != null && date2 != null) {
			int days1 = (int) ((date1.getTime() / 3600000) / 24);
			int days2 = (int) ((date2.getTime() / 3600000) / 24);
			return days2 - days1;
		} else {
			return 0;
		}

	}
	
	public static int getDaysDiff(LocalDateTime sDate1, LocalDateTime sDate2) {
		String dateStr1 = DateUtil.getLocalDateTimeToString(sDate1, "yyyyMMdd");
		String dateStr2 = DateUtil.getLocalDateTimeToString(sDate2, "yyyyMMdd");
		return getDaysDiff(dateStr1, dateStr2);
	}
	
	/**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 유효한 날짜인지 검사.</p>
	 *
	 * <pre>
	 * DateUtil.checkDate("1999-02-35") = false
	 * DateUtil.checkDate("2000-13-31") = false
	 * DateUtil.checkDate("2006-11-31") = false
	 * DateUtil.checkDate("2006-2-28")  = false
	 * DateUtil.checkDate("2006-2-8")   = false
	 * DateUtil.checkDate("20060228")   = true
	 * DateUtil.checkDate("2006-02-28") = true
	 * </pre>
	 *
	 * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @return  유효한 날짜인지 여부
	 */
	public static boolean checkDate(String sDate) {
		String dateStr = validChkDate(sDate);

		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(4, 6);
		String day = dateStr.substring(6);

		return checkDate(year, month, day);
	}
	
	/**
	 * <p>입력한 년, 월, 일이 유효한지 검사.</p>
	 *
	 * @param  year 연도
	 * @param  month 월
	 * @param  day 일
	 * @return  유효한 날짜인지 여부
	 */
	public static boolean checkDate(String year, String month, String day) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());

			Date result = formatter.parse(year + "." + month + "." + day);
			String resultStr = formatter.format(result);
			if (resultStr.equalsIgnoreCase(year + "." + month + "." + day))
				return true;
			else
				return false;
		} catch (ParseException e) {
			return false;
		}
	}


}
