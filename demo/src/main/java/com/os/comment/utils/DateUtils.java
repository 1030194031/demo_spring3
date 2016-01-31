/**
 * Administrator
 * 2015-5-14
 */
package com.os.comment.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 时间处理类
 * @author Administrator
 * 2015-5-14
 */
public class DateUtils {
	private static final String formatPattern = "yyyy-MM-dd";
	private static final String formatPattern_Short = "yyyy-MM-dd HH:mm:ss";
	/**本类方法**/

	/**获得当前时间**/
	/**获取指定时间几天后的日期**/
	/**获取指定时间几月后的日期**/
	/**日期转换成字符串**/
	/**字符串转换日期**/
	/**计算两个时间差**/
	/**获得某天中最早的时间**/
	/**返回这一天的最晚时候**/
	/**获得本月的第一天**/
	/**获得本月的最后一天**/
	/**比较两个日期大小**/
	/**计算两个时间相差的天数**/
	/**根据生日获取星座**/
	/**判断日期是否有效,包括闰年的情况 **/
	/**计算时间距离当前时间多久  格式必须为yyyy-MM-dd HH:mm:ss**/
	/**查询某日期的星期**/
		
	/**
	 * 获取当前日期
	 * @return
     */
	public static String getCurrentDate(){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern_Short);
		return format.format(new Date());
	}

	/**
	 * 获取指定时间几天后的日期
	 * @param date 指定时间
	 * @param count 计算的天数
     * @return
     */
	public static Date dateAddDay(Date date,int count){
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.DATE,count);   // int amount   代表天数
		return  cal.getTime();
	}

	/**
	 * 获取指定时间几月后的日期
	 * @param date 指定时间
	 * @param months 计算的月数
	 * @return
	 */
	public static Date dateAddMonth(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 日期转换成字符串
	 * @param date Date时间
	 * @return
	 */
	public static String formatDate(Date date,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 字符串转换日期
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str,String pattern){
		//str =  " 2008-07-10 19:20:00 " 格式
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if(!str.equals("")&&str!=null){
			try {
				return format.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 计算两个时间差
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static String timeSubtract(String time1,String time2){
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begin = null;
		Date end = null;
		try {
		begin = dfs.parse(time1);
		end = dfs.parse(time2);
		} catch (ParseException e) {
		e.printStackTrace();
		}

		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

		long day1 = between / (24 * 3600);
		long hour1 = between % (24 * 3600) / 3600;
		long minute1 = between % 3600 / 60;
		long second1 = between % 60;
		return "" + day1 + "天" + hour1 + "小时" + minute1 + "分"
		+ second1 + "秒";
	}

	/**
	 * 获得某天中最早的时间
	 * @param date
	 * @return
	 */
	public static Date getEarliest(Date date) {
		return parseTime(date, 0, 0, 0,0);
	}

	/**
	 * 返回这一天的最晚时候
	 * @param date
	 * @return
     */
	public static Date getLastest(Date date) {
		return parseTime(date, 23, 59, 59,999);
	}

	/**
	 * 获得本月的第一天
	 * @param date
	 * @return
     */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if(date!=null){
			cal.setTime(date);
		}
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 获得本月的最后一天
	 * @param date
	 * @return
     */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if(date!=null){
			cal.setTime(date);
		}
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static Date parseTime(Date date, int hourOfDay, int minute,int second,int milliSecond) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		setCalendarTime(cal,hourOfDay,minute,second,milliSecond);
		return cal.getTime();
	}
	private static void setCalendarTime(Calendar cal, int hourOfDay,int minute, int second,int milliSecond) {
		cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, milliSecond);
	}

	/**
	 * 比较两个日期大小
	 * @param date1
	 * @param date2
     * @return
     */
	public static boolean isAfterTime(Date date1,Date date2) {
		return date1.after(date2);
	}

	/**
	 * 计算两个时间相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long dayDiff(Date date1, Date date2) {
		return (date2.getTime() - date1.getTime()) / 86400000;
	}

	/**
	 * 根据生日获取星座
	 * @param birth
	 * @return
	 */
	public static String getAstro(String birth) {
			if (!isDate(birth)) {
			birth = "2000" + birth;
		}
		if (!isDate(birth)) {
			return "";
		}
		int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1,
				birth.lastIndexOf("-")));
		int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
		String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
		int[] arr = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
		int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
		return s.substring(start, start + 2) + "座";
	}

	/**
	 * 判断日期是否有效,包括闰年的情况
	 * @param date
	 * @return
     */
	public static boolean isDate(String date) {
		StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
		reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
		reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
		reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
		reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
		reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
		reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
		reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
		Pattern p = Pattern.compile(reg.toString());
		return p.matcher(date).matches();
	}

	/**
	 * 计算时间距离当前时间多久  格式必须为yyyy-MM-dd HH:mm:ss
	 * @param oldDate
     * @return
     */
	public static String getModelDate(Date oldDate){
		if (ObjectUtils.isNotNull(oldDate)) {
			Date newDate = new Date();
			long second = (newDate.getTime() - oldDate.getTime()) / 1000L;
			if (second <= 60L)
				return new StringBuilder().append(second).append("秒前").toString();
			if ((60L < second) && (second <= 3600L)) {
				second /= 60L;
				return new StringBuilder().append(second).append("分钟前").toString();
			}if ((3600L < second) && (second <= 86400L)) {
				second = second / 60L / 60L;
				return new StringBuilder().append(second).append("小时前").toString();
			}if ((86400L < second) && (second <= 864000L)) {
				String formatDate = DateUtils.formatDate(oldDate, "HH:mm:ss");
				second = second / 60L / 60L / 24L;
				return new StringBuilder().append(second).append("天前 ").append(formatDate).toString();
			}
			return DateUtils.formatDate(oldDate, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	/**
	 * 计算两个时间差
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String disTime(String date1, String date2) {
		StringBuffer sb = new StringBuffer();

		try {
			SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = e.parse(date1);
			Date date = e.parse(date2);
			long l = now.getTime() - date.getTime();
			long day = l / 86400000L;
			long hour = l / 3600000L - day * 24L;
			long min = l / 60000L - day * 24L * 60L - hour * 60L;
			if(day <= 0L && hour <= 0L && min <= 0L) {
				return "1";
			}

			if(day > 0L) {
				sb.append(day + "天");
			}

			if(hour > 0L) {
				sb.append(hour + "小时");
			}

			if(min > 0L) {
				sb.append(min + "");
			}
		} catch (Exception var14) {
			var14.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * 查询某日期的星期
	 * @param sdate
	 * @return
     */
	public static String getWeekStr(String sdate) {
		String str = "";
		str = getWeek(sdate);
		if("1".equals(str)) {
			str = "星期日";
		} else if("2".equals(str)) {
			str = "星期一";
		} else if("3".equals(str)) {
			str = "星期二";
		} else if("4".equals(str)) {
			str = "星期三";
		} else if("5".equals(str)) {
			str = "星期四";
		} else if("6".equals(str)) {
			str = "星期五";
		} else if("7".equals(str)) {
			str = "星期六";
		}

		return str;
	}
	public static String getWeek(String sdate) {
		Date date = strToDate(sdate,formatPattern);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return (new SimpleDateFormat("EEEE")).format(c.getTime());
	}
}
