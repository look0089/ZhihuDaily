package org.jzs.mybaseapp.common.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化类
 * 
 * @author jzs
 * @e-mail 355392668@qq.com
 * @version v1.0
 * @create-time 2017年7月26日17:03:29
 */

@SuppressLint("SimpleDateFormat")
public class DateFormatUtils {
	private static final String TAG = "DateFormatUtils";

	/**
	 * 转为yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatWithYMD(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(date));
	}

	/**
	 * 返回时间差
	 * @param date
	 * @return
	 */
	public static String deltaT(long date) {
		long time = (System.currentTimeMillis() - date) / 1000;
		if (time < 60) {
			return "刚刚";
		} else if (time < 3600) {
			return time / 60 + "分钟前";
		} else if (time < 24 * 3600) {
			return time / 3600 + "小时前";
		} else {
			return time / 24 / 3600 + "天前";
		}
	}

	/**
	 * 返回时间差超过1个月显示时间
	 * @param date
	 * @return
	 */
	public static String deltaTF(long date) {
		long time = (System.currentTimeMillis() - date) / 1000;
		if (time < 60) {
			return "刚刚";
		} else if (time < 3600) {
			return time / 60 + "分钟前";
		} else if (time < 24 * 3600) {
			return time / 3600 + "小时前";
		} else {
			return formatWithYMDHm(date);
		}
	}

	/**
	 * 转为yyyy
	 * @param date
	 * @return
	 */
	public static String formatWithY(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(new Date(date));

	}

	/**
	 * 转为yyyy-MM
	 * @param date
	 * @return
	 */
	public static String formatWithYM(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(new Date(date));

	}

	/**
	 * 转为HH:mm
	 * @param date
	 * @return
	 */
	public static String formatWithHm(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(new Date(date));
	}

	/**
	 * 转为dd
	 * @param date
	 * @return
	 */
	public static String formatWithd(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(new Date(date));
	}

	/**
	 * 转为HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatWithHms(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date(date));
	}

	/**
	 * 转为 yyyy-MM-dd HH:mm
	 * @param date
	 * @return
	 */
	public static String formatWithYMDHm(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(new Date(date));
	}

	/**
	 * 转为 yyyy年MM月dd日 HH:mm
	 * @param date
	 * @return
	 */
	public static String formatWithYMDHmChinese(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		return sdf.format(new Date(date));
	}

	/**
	 * 转为MM-dd
	 * @param date
	 * @return
	 */
	public static String formatWithMD(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		return sdf.format(new Date(date));
	}

	/**
	 * 转为MM-dd HH:mm
	 * @param date
	 * @return
	 */
	public static String formatWithMDHm(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		return sdf.format(new Date(date));
	}

	/**
	 * 转为 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatWithYMDHms(long date) {
		if (date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(date));
	}

	/**
	 * 由yyyy-MM-dd'T'HH:mm:ssZ -> 时间戳
	 * 
	 * @param datetime
	 * @return
	 */
	public static long parse(String datetime) {
		try {

			if (TextUtils.isEmpty(datetime)) {
				return System.currentTimeMillis();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			return sdf.parse(datetime).getTime();
		} catch (ParseException e) {
			AppLog.redLog(TAG, e + "");
		}

		return System.currentTimeMillis();
	}

	/**
	 * 由yyyy-MM-dd'T'HH:mm\ -> 时间戳
	 * @param datetime
	 * @return
	 */
	public static long parseToLong(String datetime) {
		try {

			if (TextUtils.isEmpty(datetime)) {
				return System.currentTimeMillis();
			}
			// SimpleDateFormat sdf = new
			// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.parse(datetime).getTime();
		} catch (ParseException e) {
			AppLog.redLog(TAG, e + "");
		}

		return System.currentTimeMillis();
	}

	public static Date parseToDate(String datetime) {
		try {
			if (TextUtils.isEmpty(datetime)) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(datetime);
		} catch (ParseException e) {
			AppLog.redLog(TAG, e + "");
		}

		return null;
	}

	/**
	 * 将日期转化为年月日时分秒
	 * @param date
	 * @return
	 */
	public static String parseDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 由yyyy-MM-dd -> 时间戳
	 * 
	 * @param datetime
	 * @return
	 */
	public static long parseToYMD(String datetime) {
		try {
			if (TextUtils.isEmpty(datetime)) {
				return System.currentTimeMillis();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(datetime).getTime();
		} catch (ParseException e) {
			AppLog.redLog(TAG, e + "");
		}
		return System.currentTimeMillis();
	}

	/**
	 * 由yyyy-MM-dd -> 时间戳
	 * @param datetime
	 * @return
	 */
	public static long parseToYMDHM(String datetime) {
		try {
			if (TextUtils.isEmpty(datetime)) {
				return System.currentTimeMillis();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.parse(datetime).getTime();
		} catch (ParseException e) {
			AppLog.redLog(TAG, e + "");
		}
		return System.currentTimeMillis();
	}

	/**
	 * 时间转换成毫秒
	 * @param expireDate
	 * @return
	 */
	public static long getSecondsFromDate(String expireDate) {
		if (expireDate == null || expireDate.trim().equals(""))
			return 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(expireDate);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 转换成时间格式(类似音乐、视频计算时间)
	 * 
	 * @param time
	 * @return
	 */
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return timeStr;
	}

	/**
	 * 之上的附属方法
	 * @param i
	 * @return
	 */
	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else retStr = "" + i;
		return retStr;
	}
}
