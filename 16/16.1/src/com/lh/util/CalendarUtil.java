package com.lh.util;

import java.util.Calendar;
/**
 * 日期时间类
 * @author lh
 *
 */
public class CalendarUtil {
	/**
	 * 将指定日期时间转换为简化格式字符串
	 * @param calendar
	 * @return 如 2010-4-21 16:16:28
	 */
	public static String getParticularDateTime( Calendar calendar ){
		if( calendar == null ) return "";
		StringBuffer sb = new StringBuffer();
		sb.append( calendar.get( Calendar.YEAR ) );
		sb.append( "-" );
		sb.append( calendar.get( Calendar.MONTH ) + 1 );
		sb.append( "-" );
		sb.append( calendar.get( Calendar.DAY_OF_MONTH ) );
		sb.append( " " );
		sb.append( calendar.get( Calendar.HOUR_OF_DAY ) );
		sb.append( ":" );
		sb.append( calendar.get( Calendar.MINUTE ) );
		sb.append( ":" );
		sb.append( calendar.get( Calendar.SECOND ) );
		return sb.toString();
	}
}
