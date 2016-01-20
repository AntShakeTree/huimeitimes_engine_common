package com.hm.engine.common.types;


public enum PgStateType {

	BREAKFAST_BEFORE(TYPE_CODE.BREAKFAST_BEFORE), BREAKFAST_AFTER(
			TYPE_CODE.BREAKFAST_AFTER), LUNCH_BEFORE(TYPE_CODE.LUNCH_BEFORE), LUNCH_AFTER(
			TYPE_CODE.LUNCH_AFTER), DINNER_BEFORE(TYPE_CODE.DINNER_BEFORE), DINNER_AFTER(
			TYPE_CODE.DINNER_AFTER), BEDTIME_BEFORE(TYPE_CODE.BEDTIME_BEFORE), HELF_NIGHT(
			TYPE_CODE.HELF_NIGHT), RANDOM_PG(TYPE_CODE.RANDOM_PG);
	private int typeValue = 0;

	private PgStateType(int typeValue) {
		this.typeValue = typeValue;
	}

	public int getValue() {
		return typeValue;
	}

	public static int getPgStatefpg() {
		return PgStateType.BREAKFAST_BEFORE.getValue();
	}

	public static int getPgStateBefore() {
		return PgStateType.BREAKFAST_BEFORE.getValue()
				| PgStateType.LUNCH_BEFORE.getValue()
				| PgStateType.DINNER_BEFORE.getValue();
	}

	public static int getPgStateAfter() {
		return PgStateType.BREAKFAST_AFTER.getValue()
				| PgStateType.LUNCH_AFTER.getValue()
				| PgStateType.DINNER_AFTER.getValue()
				| PgStateType.BREAKFAST_AFTER.getValue();
	}

	public static int getPgStateRandom() {
		return PgStateType.BREAKFAST_AFTER.getValue()
				| PgStateType.LUNCH_AFTER.getValue()
				| PgStateType.DINNER_AFTER.getValue()
				| PgStateType.BEDTIME_BEFORE.getValue()
				| PgStateType.LUNCH_BEFORE.getValue()
				| PgStateType.DINNER_BEFORE.getValue();
	}
	// pg 内容 计算
	public static final int CONTENT_PG = 1<<9;
	public static int pgStateTypes(PgStateType... counts) {
		int r = CONTENT_PG;
		for (PgStateType c : counts) {
			r = r | c.typeValue;
		}
		return r;
	}
	
	/**
	 * 包含那种类型的血糖
	 * @param state
	 * @param type
	 * @return
	 */
	public static final boolean isContain(int state,PgStateType type){
		return ((type.getValue())&state)!=0;
	}

	public static int pgStateTypeAll() {
		int r = CONTENT_PG;
		for (PgStateType c : PgStateType.values()) {
			r = r | c.typeValue;
		}
		return r;
	}
	private final static class TYPE_CODE {
		public static final int BREAKFAST_BEFORE = 1;// 1000000011 1011111111
		public static final int BREAKFAST_AFTER = 2;
		public static final int LUNCH_BEFORE = 4;
		public static final int LUNCH_AFTER = 8;
		public static final int DINNER_BEFORE = 16;
		public static final int DINNER_AFTER = 32;
		public static final int BEDTIME_BEFORE = 64;
		public static final int HELF_NIGHT = 128;
		public static final int RANDOM_PG = 256;
	}
	
	public static void main(String[] args) {
		for (PgStateType  s : PgStateType.values()) {
			if(isContain(524,s)){
				System.out.println(s);
			}	
		} 
	}
}
