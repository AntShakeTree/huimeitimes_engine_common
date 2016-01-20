package com.hm.engine.common.types;

import com.hm.engine.common.ConfigUtil;

public enum SportLevels {

	LevelAll(SportLevelCode.SPORT_LEVEL_ALL), A1(SportLevelCode.SPORT_LEVEL_A1), A2(
			SportLevelCode.SPORT_LEVEL_THIN_A2), A3(
			SportLevelCode.SPORT_LEVEL_FAT_A3), A4(
			SportLevelCode.SPORT_LEVEL_YOUNG_A4), A5(
			SportLevelCode.SPORT_LEVEL_OLD_A5);
	private SportLevels(int v) {
		this.value = v;
	}

	private int value;

	public int getValue() {
		return value;
	}
	public static String SPORT_HEARTLUNG_LEVEL_A2_CODES = ConfigUtil
			.getByKey("sport.heartlung.level.A2");
	public static String SPORT_HEARTLUNG_LEVEL_A3_CODES = ConfigUtil
			.getByKey("sport.heartlung.level.A3");
	public static String SPORT_HEARTLUNG_LEVEL_A4_CODES = ConfigUtil
			.getByKey("sport.heartlung.level.A4");
	public static String SPORT_HEARTLUNG_LEVEL_A5_CODES = ConfigUtil
			.getByKey("sport.heartlung.level.A5");

	//
	private  static String SPORT_RESISTANCD_LEVEL_A5_CODES = ConfigUtil
			.getByKey("sport.resistancd.level.A5");
	private static String SPORT_RESISTANCD_LEVEL_A4_CODES = ConfigUtil
			.getByKey("sport.resistancd.level.A4");
	private static String SPORT_RESISTANCD_LEVEL_A3_CODES = ConfigUtil
			.getByKey("sport.resistancd.level.A3");
	private static String SPORT_RESISTANCD_LEVEL_A2_CODES = ConfigUtil
			.getByKey("sport.resistancd.level.A2");
	private static String SPORT_RESISTANCD_LEVEL_A1_CODES = ConfigUtil
			.getByKey("sport.resistancd.level.A1");
	/**
	 * 运动编码得到心肺运动级别
	 * 
	 * @param code
	 * @return
	 */
	public static int sportHeartLungLevelFromSportCode(String code) {
		int leve = 0;
		if (SPORT_HEARTLUNG_LEVEL_A2_CODES.contains(code)) {
			leve |= SportLevelCode.SPORT_LEVEL_THIN_A2;
		}
		if (SPORT_HEARTLUNG_LEVEL_A3_CODES.contains(code)) {
			leve |= SportLevelCode.SPORT_LEVEL_FAT_A3;
		}
		if (SPORT_HEARTLUNG_LEVEL_A4_CODES.contains(code)) {
			leve |= SportLevelCode.SPORT_LEVEL_YOUNG_A4;
		}
		if (SPORT_HEARTLUNG_LEVEL_A5_CODES.contains(code)) {
			leve |= SportLevelCode.SPORT_LEVEL_OLD_A5;
		}
		return leve;
	}

	/**
	 * 根据运动编码得到抗阻级别
	 * 
	 * @param code
	 * @return
	 */
	public static int sportResistanceLevelFromSportCode(String code) {
		int leve = 0;
		if (SPORT_RESISTANCD_LEVEL_A2_CODES.contains(code)) {
			leve |= SportLevelCode.SPORT_LEVEL_THIN_A2;
		}
		if (SPORT_RESISTANCD_LEVEL_A3_CODES.contains(code)) {
			leve |= SportLevelCode.SPORT_LEVEL_FAT_A3;
		}
		if (SPORT_RESISTANCD_LEVEL_A4_CODES.contains(code)) {
			leve |= SportLevelCode.SPORT_LEVEL_YOUNG_A4;
		}
		if (SPORT_RESISTANCD_LEVEL_A5_CODES.contains(code)) {
			leve |= SportLevelCode.SPORT_LEVEL_OLD_A5;
		}
		if (SPORT_RESISTANCD_LEVEL_A1_CODES.contains(code)) {
			leve |= SportLevelCode.SPORT_LEVEL_OLD_A5;
		}
		return leve;
	}

	private static class SportLevelCode {
		public static int SPORT_LEVEL_ALL = Integer.MAX_VALUE;// 老年人以外
		public static int SPORT_LEVEL_YOUNG_A4 = 8;// 老年人以外
		public static int SPORT_LEVEL_THIN_A2 = 4;
		public static int SPORT_LEVEL_FAT_A3 = 2;
		public static int SPORT_LEVEL_OLD_A5 = 1;
		public static int SPORT_LEVEL_A1 = 16;
	}
}
