package com.johnpray.licenseplategame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.johnpray.licenseplategame.LicensePlateDatabase.*;

class LicensePlateDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "licenseplategame.db";
	private static final int DATABASE_VERSION = 1;
	
	LicensePlateDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " +
			LicensePlates.LICENSE_PLATE_TABLE + " ("
			+ LicensePlates._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ LicensePlates.STATE_NAME + " TEXT,"
			+ LicensePlates.POINT_VALUE + " INTEGER,"
			+ LicensePlates.FOUND_TODAY + " TEXT,"
			+ LicensePlates.FINDER_NAME + " TEXT"
			+ ");");
		/*
		db.execSQL("CREATE TABLE " +
			LicensePlates.TEMP_TABLE + " ("
			+ LicensePlates._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ LicensePlates.LAST_NAME + " TEXT,"
			+ LicensePlates.FIRST_NAME + " TEXT,"
			+ LicensePlates.MIDDLE_NAME + " TEXT,"
			+ LicensePlates.FAC_STAFF_DIR + " TEXT,"
			+ LicensePlates.JOB_TITLE + " TEXT,"
			+ LicensePlates.DEPARTMENT + " TEXT,"
			+ LicensePlates.OFFICE + " TEXT,"
			+ LicensePlates.PHONE_NUMBER + " TEXT,"
			+ LicensePlates.EMAIL + " TEXT"
			+ ");");
		*/
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This is where things are moved around
		// or new tables and columns are added
		// for the first start after a update to the app
		// with a new database structure.
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
}