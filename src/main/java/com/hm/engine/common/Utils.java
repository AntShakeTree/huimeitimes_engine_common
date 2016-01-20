package com.hm.engine.common;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateUtils;

public class Utils {

	public static final Random random = new Random();

	/**
	 * 国旗到现在距离当前时间多少个约
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static int beforeDistanceMonth(String dateString)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Constants.MONTH_FORMAT);
		Date d = dateFormat.parse(dateString);
		long l = 30 * 24 * 60 * 60 * 1000l;
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return (int) ((System.currentTimeMillis() - d.getTime()) / l);
	}

	//
	/**
	 * 现在到将来还有几天
	 * 
	 * @param checkDate
	 * @return
	 * @throws ParseException
	 */
	public static int futureDistanceDates(String checkDate)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Constants.DATE_FORMAT);
		Date d = dateFormat.parse(checkDate);
		long l = 24 * 60 * 60 * 1000l;
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		long future = d.getTime();
		return (int) ((future - System.currentTimeMillis()) / l);
	}

	/**
	 * 到晚上23:59:59
	 *
	 * @param checkDate
	 * @return
	 * @throws ParseException
	 */
	public static long futureMidNightDistanceMillis(String checkDate)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Constants.DATE_FORMAT);
		Date d = dateFormat.parse(checkDate);
		long future = d.getTime();
		return future-System.currentTimeMillis();
	}

	/**
	 *今晚半夜到现在多少毫秒
	 ** @return
	 * @throws ParseException
	 */
	public static long futureMidNighDistanceMillis() {
		return  getEndTime()-System.currentTimeMillis();
	}
	//
	/**
	 * 过去到现在有几天
	 * 
	 * @param checkDate
	 * @return
	 */
	public static int beforeDistanceDates(long checkDate) {
		Date d = new Date(checkDate);
		long l = 24 * 60 * 60 * 1000l;
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return (int) ((System.currentTimeMillis() - d.getTime()) / l);
	}

	/**
	 * 过去到现在有多少浩渺
	 * 
	 * @param beforeDays
	 * @return
	 */
	public static long beforeTimeFromCurrenttime(int beforeDays) {
		Calendar date = new GregorianCalendar();
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return date.getTimeInMillis() - beforeDays * 24 * 60 * 60 * 1000l;
	}

	// HRmax=207-0.7*年龄
	/**
	 * 最大心率计算公式
	 * 
	 * @param age
	 * @return
	 */
	public static double HRMax(int age) {
		return 207 - 0.7 * age;
	}

	
	

	/**
	 * 储备心率
	 * 
	 * @param age
	 * @param hrest
	 * @return
	 */
	public static double HRR(int age, double hrest) {
		return HRMax(age) - hrest;
	}

	/**
	 * @param hrest
	 * @return
	 */
	public static double THR(double factor, double hrr, double hrest) {
		return factor * hrr + hrest;
	}

	public static double StrenghtConvert(double factor, int age, double hrest) {
		return THR(factor, HRR(age, hrest), hrest);
	}
	

	// 今天开始时间
	public static Long getStartTime() {
		return beforeTimeFromCurrenttime(0);
	}

	// 今天结束时间
	public static long getEndTime() {
		Calendar todayEnd = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		;
//		todayEnd.set(Calendar.DAY_OF_MONTH,-1);

		return todayEnd.getTime().getTime();
	}

	/**
	 * bmi
	 * 
	 * @param weight
	 * @param height
	 * @param unit
	 * @return caculator bmi
	 */
	public static double bmi(double weight, double height, HeightUnit unit) {
		return (weight / Math.pow(HeightUnit.M.convert(height, unit), 2));
	}

	// 根据字段排序
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> sortCollection(Collection<T> rs,
			String comparableColumn, boolean reversed) {
		Comparator<?> comparator = ComparableComparator.getInstance();
		comparator = ComparatorUtils.nullLowComparator(comparator); // 允许null
		if (reversed)
			comparator = ComparatorUtils.reversedComparator(comparator); // 逆序
		List<BeanComparator> sortFields = new ArrayList<BeanComparator>();
		sortFields.add(new BeanComparator(comparableColumn, comparator));//
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		if (rs instanceof List) {
			Collections.sort((List<T>) rs, multiSort);
			return rs;
		} else {
			List<T> rss = Lists.newLinkedList();
			for (T object : rs) {
				rss.add(object);
			}
			Collections.sort(rss, multiSort);
			return rss;
		}
	}

	/**
	 * 卡路里计算公式
	 */
	public static double kcal(int gendar, double hrav, double weight, int age,
			double sporttime, TimeUnit unit) {
		// sporttime = TimeUnit.MINUTES.convert(sporttime, unit);
		if (unit.equals(TimeUnit.SECONDS)) {
			sporttime = sporttime / 60.0;
		}
		double k = 0.0;
		if (gendar == 1) {
			k = ((-55.0969 + (0.6309 * hrav) + (0.1988 * weight) + (0.2017 * age)) / 4.184)
					* sporttime;
		} else {
			k = ((-20.4022 + (0.4472 * hrav) + (0.1263 * weight) + (0.074 * age)) / 4.184)
					* sporttime;
		}
		return k;
	}

	/**
	 * 卡路里计算公式
	 */
	public static double kcal(double height, double weight, double workType) {
		double fator = 1;

		// 1.2 卧床
		// 1.3 轻体力
		// 1.6 中体力
		// 2.0 重体力
		/**
		 * 体型 卧床 轻体力 中体力 重体力 消瘦 25 35 40 45 正常 20 30 35 40 超重/肥胖 15 25 30 35
		 */
		if (workType == 1.2) {
			if ( normalWeight(height,weight)<0) {
				fator = 25;
			} else if (weight == normalWeight(height,weight)) {
				fator = 20;
			} else {
				fator = 15;
			}
		} else if (workType == 1.3) {
			if (normalWeight(height,weight)<0) {
				fator = 35;
			} else if (normalWeight(height,weight)==0) {
				fator = 30;
			} else {
				fator = 25;
			}
		} else if (workType == 1.6) {
			if ( normalWeight(height,weight)<0) {
				fator = 40;
			} else if (0 == normalWeight(height,weight)) {
				fator = 35;
			} else {
				fator = 30;
			}
		} else {
			if (normalWeight(height,weight)<0) {
				fator = 45;
			} else if (0 == normalWeight(height,weight)) {
				fator = 40;
			} else {
				fator = 35;
			}
		}
		return weight*fator;
	}

	public static int normalWeight(double height,double weight) {
		 double bmi =Utils.bmi(weight, height, HeightUnit.CM);
		 if(bmi>24){
			 return 1;
		 }else if(bmi<18.5){
			 return -1;
		 }else{
			 return 0;
		 }
		 
	}

	/**
	 * 判断真实年龄对应的心肺年龄级别
	 * 
	 * @param realAge
	 * @return
	 */
	public static int ageLevelFromCRF(int realAge) {
		return ageLevelFromCRF(realAge, 8, Constants.CRF_AGE_LEVEL_2);
	}

	private static int ageLevelFromCRF(int realAge, int maxLevel, int maxAge) {
		for (int n = 1; n < maxLevel; n++) {
			if (Predicate.LTE.eval(realAge, Constants.CRF_AGE_LEVEL_1 + 5
					* (n - 1))) {
				return n;
			}
		}
		if (Predicate.GTE.eval(realAge, Constants.CRF_AGE_LEVEL_2)) {
			return maxLevel;
		}
		return 1;
	}

	public static int getAgeByBirthday(String birthday) throws ParseException {
		return getAgeByBirthday(new SimpleDateFormat(Constants.DATE_FORMAT)
				.parse(birthday));
	}

	public static int getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return age;
	}

	/**
	 * 判断真实年龄对应的肌肉年龄级别
	 * 
	 * @param realAge
	 * @return
	 */
	public static int ageLevelFromMF(int realAge) {
		return ageLevelFromCRF(realAge, 10, Constants.MF_AGE_LEVEL_2);
	}

	public static double cfrScore(int min, double sum, TimeUnit un) {
		long senconds = TimeUnit.SECONDS.convert(min, un);
		return senconds * 50 / sum;
	}

	public static long getDiseaseLevel(int i, Integer j) {
		if (j != null)
			return (long) (Math.pow(2, i) - Math.pow(2, j));
		return (long) Math.pow(2, i);
	}

	static final long[] byteTable = createLookupTable();
	static final long HSTART = 0xBB40E64DA205B064L;
	static final long HMULT = 7664345821815920749L;

	private static final long[] createLookupTable() {
		long[] byteTable = new long[256];
		long h = 0x544B2FBACAAF1684L;
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 31; j++) {
				h = (h >>> 7) ^ h;
				h = (h << 11) ^ h;
				h = (h >>> 10) ^ h;
			}
			byteTable[i] = h;
		}
		return byteTable;
	}

	public static long hashCode(CharSequence cs) {
		long h = HSTART;
		final long hmult = HMULT;
		final long[] ht = byteTable;
		final int len = cs.length();
		for (int i = 0; i < len; i++) {
			char ch = cs.charAt(i);
			h = (h * hmult) ^ ht[ch & 0xff];
			h = (h * hmult) ^ ht[(ch >>> 8) & 0xff];
		}
		return h;
	}
	
	/**
	 * 数据类型必须一致
	 * 
	 * @param value
	 * @param low
	 * @param high
	 * @return
	 */
	public static boolean MID(Object value, Object low, Object high) {
		//
		return Predicate.GTE.eval(value, low) && Predicate.LT.eval(value, high);
	}
	
	
	  /**匹配&或全角状态字符或标点*/  
    public static final String PATTERN="&|[\uFE30-\uFFA0]|‘’|“”";  
    
    public static String replaceSpecialtyStr(String str,String pattern,String replace){  
        if(isBlankOrNull(pattern))  
            pattern="\\s*|\t|\r|\n";//去除字符串中空格、换行、制表  
        if(isBlankOrNull(replace))  
            replace="";  
        return Pattern.compile(pattern).matcher(str).replaceAll(replace);  
          
    }  
    public static boolean isBlankOrNull(String str){  
        if(null==str)return true;  
        //return str.length()==0?true:false;  
                   return str.length()==0;  
    }  
      
    /**清除数字和空格*/  
    public static  String cleanBlankOrDigit(String str){  
        if(isBlankOrNull(str))return "null";  
        return Pattern.compile("\\d|\\s").matcher(str).replaceAll("");  
    }
	public static String getIp(String url)  {

		String page = Constants.SERVER_URL; // 读网页
		Pattern p = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)");

		Matcher m = p.matcher(page);

		while(m.find()) {
			return m.group(1);
		}
		return "localhost";
	}


	public static Long getStartTime(Long mirsecond){
		GregorianCalendar date = new GregorianCalendar();
		date.setTimeInMillis(mirsecond);

		date.set(11, 0);
		date.set(12, 0);
		date.set(13, 0);
		date.set(14, 0);
		return date.getTimeInMillis();
	}
	/**
	 *
	 * @param @param ip
	 * @param @return
	 * @return boolean
	 * @throws
	 * @since : v1.0.0.0
	 */
	public static boolean isIp(String ip) {
		String reg = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
		if (!matches(ip, reg)) {
			return false;
		}
		return true;
	}
	/**
	 * Title: matches : d
	 *
	 * @param value
	 * @param regular
	 * @param
	 * @return boolean
	 * @throws
	 */
	public static boolean matches(String value, String regular) {
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(value); // 娴犮儵鐛欑拠锟�7.400.600.2娑撹桨绶�
		return matcher.matches();
	}

	/**
	 * Title: matches : d
	 *
	 * @param value
	 * @param regular
	 * @param
	 * @return boolean
	 * @throws
	 */
	public static String group(String value, String regular,int index) {
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(value); // 娴犮儵鐛欑拠锟�7.400.600.2娑撹桨绶�
		return matcher.group(index);
	}

	/**
	 *
	 * @param @param ip
	 * @param @return
	 * @return boolean
	 * @throws
	 * @since : v1.0.0.0
	 */
	public static boolean isInetAddress(String ip) {
		String reg = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
		if (!matches(ip, reg)) {
			try {
				String add = InetAddress.getByName(ip).getHostAddress();
				return matches(add, reg);
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

	public static int distanceCurYears(Date degestDate){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(degestDate);
		Calendar calendar2 =   Calendar.getInstance();
		calendar2.setTimeInMillis(System.currentTimeMillis());
		int pathogenesis=calendar2.getWeekYear()-calendar.getWeekYear();
		return pathogenesis;
	}
	private static final String PRODUCT_CACHE_TEMPLATE="D_AVICE_{0}";

	public static String getCommonCacheName(String name){
		return MessageFormat.format(PRODUCT_CACHE_TEMPLATE,name);
	}
}
