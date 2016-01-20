package com.hm.engine.common;

import java.text.SimpleDateFormat;

import com.hm.engine.common.types.Endpoint;

public class Constants {
	public static int CRF_AGE_LEVEL_1 = Integer.parseInt(ConfigUtil
			.getByKey("crf.age.level.1"));
	public static int CRF_AGE_LEVEL_2 = Integer.parseInt(ConfigUtil
			.getByKey("crf.age.level.2"));
	public static int MF_AGE_LEVEL_1 = Integer.parseInt(ConfigUtil
			.getByKey("mf.age.level.1"));
	public static int MF_AGE_LEVEL_2 = Integer.parseInt(ConfigUtil
			.getByKey("mf.age.level.2"));
	public static int TWO_DAYS_MILLISECOND = 48 * 60 * 60 * 1000;
	public static int THREE_DAYS_MILLISECOND = 72 * 60 * 60 * 1000;
	public static String DIABETES = "DIABETES";// ';//'2型糖尿病', 'other', '30',
	public static String SPORT = "SPORT";// ';'运动是否有风险', 'SPORT', '38', '0',
	public static String SPORT_REST = "SPORT_REST";// ';'运动是否有风险', 'SPORT',
	public static String NUTRITION = "NUTRITION";// ', '营养得分', 'NUTRITION',
													// '40', '-1', '15',
	public static String HABIT = "HABIT";// ', '习惯', 'HABIT', '44', '0', '1',
											// '0', NULL,
	public static String CHECK = "CHECK";// ', '体检', 'CHECK', '6', '0', '1',
	public static String CHECKPGTIMES = "CHECKPGTIMES";// ', '体检', 'CHECK', '6',
														// '0', '1',
	// '0', NULL,
	public static String ADVICE = "ADVICE";// ', '遵循医嘱', 'ADVICE', '48', '0',
											// '3', '0',
	public static String TWOHPG = "2HPG";// ', '餐后血糖', '2HPG', '50', '1',
											// '16.7', '0',
	public static String DIATOLICPRESSURE = "DIATOLICPRESSURE";// ', '舒张血压',
																// 'old', '52',
	public static final String TG = "TG";

	public static final String HEARTLUNGFUNCTION = "HEARTLUNGFUNCTION";

	public static final String HBA1C = "HBA1C";

	public static final String FPG = "FPG";

	public static final String FAT = "FAT";

	public static final String CHO = "CHO";

	public static final String SYSTOLIC_PRESSURE = "SYSTOLICPRESSURE";

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_HOURE_MINUTE_SENCOND = "yyyy-MM-dd HH:mm:ss";
	public static final String MONTH_FORMAT = "yyyy-MM";
	public static final int CLIENT_ENDPOINT = Endpoint.Customer.ordinal();
	public static final int SERVER_ENDPOINT = Endpoint.Service.ordinal();
	public static final int O_PLATFORM = 2;
	public static final int ALL_ENDPOINT = 3;
	public static final String BMI = "BMI";
	public static final String WORK = "WORK";
	public static final String DIATOLIC_PRESSURE = "DIATOLICPRESSURE"; // '1',
																		// '90',
	public static String SYSTOLICPRESSURE = "SYSTOLICPRESSURE";// ', '收缩',
																// 'SYSTOLICPRESSURE',
																// '53',
	public static String SYNDROME = "SYNDROME";// ', '是否有并发', 'SYNDROME', '55',
												// '0', '1',
	public static String DRINK = "DRINK";// ', '饮酒', 'DRINK', '6', '0', '0',
	public static String ERY = "ERY";//
	public static String PRO = "PRO";//
	// ~~
	// public static int SPORT_LEVEL_ALL = Integer.MAX_VALUE;// 老年人以外
	// public static int SPORT_LEVEL_YOUNG_A4 = 8;// 老年人以外
	// public static int SPORT_LEVEL_THIN_A2 = 4;
	// public static int SPORT_LEVEL_FAT_A3 = 2;
	// public static int SPORT_LEVEL_OLD_A5 = 1;
	// public static int SPORT_LEVEL_A1 = 16;

	//
	public static final int HEARTANDLUNG_EXCELLENT = 5;
	public static final int HEARTANDLUNG_FINE = 4;
	public static final int HEARTANDLUNG_GENERAL = 3;
	public static final int HEARTANDLUNG_MAY = 2;
	public static final int HEARTANDLUNG_POOR = 1;
	public static final int MUSCLE_EXCELLENT = 3;
	public static final int MUSCLE_FINE = 2;
	public static final int MUSCLE_POOR = 1;
	// public static final int FLOWID = 3;
	// public static final int END_TASK_STATE = 1;

	// TASK
	// 0，sport new ../1 fpg// 2 twopg //3 // 512 error
	// public static final int CONTENT_SPORT = 0;

	// public static final String TASK_PG_SCORE = "TASK_PG_SCORE";
	// public static final String TASK_GUID_DIET = "TASK_DIET";
	// public static final int CONTENT_FPG_EXCEPTION = 500;
	// public static final int CONTENT_TWOPG_EXCEPTION = 501;

	// public static final int CONTENT_2HPH_TASK=10;
	//
	// public static final int start = 0;
	// public static final int runing = 1;
	// public static final int ended = 2;
	// public static final int SPORT_TYPE = 0;
	// public static final int DIET_TYPE = 1;
	// public static final int MEDICINE_TYPE = 2;
	// public static final int CHECK_TYPE = 3;
	//

	public static final String TASK_GUID_PG = "TASK_PG";
	public static final String NORMAL_PG_NOTICE = "{0}三天没有录入血糖了。电话号码{1}";
	public static final String SERVER_URL = ConfigUtil.getByKey("server.url");
	public static final String TEST_URL = ConfigUtil.getByKey("test.url");
	public static final String REST_PG_TASK = ConfigUtil.getByKey("server.pg.task");
	public static final String REST_NOTIFY_PG_TASK = ConfigUtil.getByKey("server.notify.pg");
	public static final String REST_NOTIFY_SPORT = ConfigUtil
			.getByKey("server.notify.sport");
	public static final String REST_TASK_SPORT = ConfigUtil
			.getByKey("server.sport.task");
	public static final String REST_NUTRITION_TASK = ConfigUtil
			.getByKey("server.nutrition.task");
	public static final String PROJECT_NAME = "huimeitimes_engine :";
	public static final String RECEPE_GENERAL_INTRODUCE = "GENERAL";
	public static final int HEART_LUNG_TASK_TIMES = 1;
	public static final int HEART_LUNG_OFF_TASK_TIMES = 1;
	public static final int NUTRITION_MAX_LEVEL=Integer.parseInt(ConfigUtil.getByKey("nutrition.max.level"));
	public static int SPORT_LEVELS = Integer.parseInt(ConfigUtil
			.getByKey("sport.leves"));
	public static final SimpleDateFormat DATE_FORMAT_HOURE_MINUTE_SENCOND_FORMAT = new SimpleDateFormat(Constants.DATE_FORMAT_HOURE_MINUTE_SENCOND);
	public static final SimpleDateFormat DATE_PARSE_FORMAT = new SimpleDateFormat(Constants.DATE_FORMAT);

}
