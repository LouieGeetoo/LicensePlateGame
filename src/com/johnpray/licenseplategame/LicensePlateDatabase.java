package com.johnpray.licenseplategame;


import android.provider.BaseColumns;

public final class LicensePlateDatabase {
	
	private LicensePlateDatabase() {}
	
	/** License Plate Game database schema */
	public static final class LicensePlates implements BaseColumns {
		private LicensePlates() {}
		public static final String LICENSE_PLATE_TABLE = "table_license_plates";
		public static final String TEMP_TABLE = "table_temp";
		public static final String STATE_NAME = "STATE_NAME";
		public static final String POINT_VALUE = "POINT_VALUE";
		public static final String FOUND_TODAY = "FOUND_TODAY";
		public static final String FINDER_NAME = "FINDER_NAME";
		public static final String DEFAULT_SORT_ORDER = "STATE_NAME ASC";
	}
}