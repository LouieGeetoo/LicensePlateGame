package com.johnpray.licenseplategame;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.johnpray.licenseplategame.LicensePlateDatabase.LicensePlates;

public class LicensePlateGame extends Activity {

	protected LicensePlateDatabaseHelper mDatabase = null; 
	protected Cursor mCursor = null;
	protected SQLiteDatabase mDB = null;
	private SimpleCursorAdapter adapter;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		mDatabase = new LicensePlateDatabaseHelper(this.getApplicationContext());
		mDB = mDatabase.getWritableDatabase();
		
		ListView list = (ListView)findViewById(R.id.listStates);
		list.setFastScrollEnabled(true);
		
		fillListView();
		populateDatabase();
		
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(mDB != null)
		{
			mDB.close();
		}
		
		if(mDatabase != null)
		{
			mDatabase.close();
		}
	}
	
	private void populateDatabase() {
		if (mCursor.getCount() == 0) {
			ContentValues entry = new ContentValues();
			
			// Add US States
			String[] states = getResources().getStringArray(R.array.USStates);
			Log.d("LicensePlateGame", "String Array - first: " + states[0]);
			for (int i = 0; i < states.length; i++) {
				entry.put(LicensePlates.POINT_VALUE, 1);
				entry.put(LicensePlates.STATE_NAME, states[i]);
				entry.put(LicensePlates.FINDER_NAME, "(Finder)");
				mDB.insert(LicensePlates.LICENSE_PLATE_TABLE, null, entry);
			}
			states = null;
			
			// Add Canadian Provices
			String[] provinces = getResources().getStringArray(R.array.CanadianProvinces);
			Log.d("LicensePlateGame", "String Array - first: " + provinces[0]);
			for (int i = 0; i < provinces.length; i++) {
				entry.put(LicensePlates.POINT_VALUE, 1);
				entry.put(LicensePlates.STATE_NAME, provinces[i]);
				entry.put(LicensePlates.FINDER_NAME, "(Finder)");
				mDB.insert(LicensePlates.LICENSE_PLATE_TABLE, null, entry);
			}
			provinces = null;
			
			adapter.getCursor().requery();
		}
	}
	

	
	private void fillListView() {
		// Populate the ListView
    	SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
    	queryBuilder.setTables(
    		LicensePlates.LICENSE_PLATE_TABLE
    	);
    	
    	String asColumnsToReturn[] = { 
    			LicensePlates.LICENSE_PLATE_TABLE + "."
    			+ LicensePlates.STATE_NAME + "," +
    			LicensePlates.LICENSE_PLATE_TABLE + "."
    			+ LicensePlates.FOUND_TODAY + "," +
    			LicensePlates.LICENSE_PLATE_TABLE + "."
    			+ LicensePlates.FINDER_NAME + "," +
    			LicensePlates.LICENSE_PLATE_TABLE + "."
    			+ LicensePlates._ID
    	};
    	
    	mCursor = queryBuilder.query(mDB, asColumnsToReturn, null, null,
    			null, null, LicensePlates.DEFAULT_SORT_ORDER);
    	
    	startManagingCursor(mCursor);
    	
    	adapter = new SimpleCursorAdapter(this,
    			R.layout.state_list_item, mCursor,
    			new String[]{
    				LicensePlates.STATE_NAME,
    				LicensePlates.FOUND_TODAY},
    			new int[]{
    				R.id.chkboxFound,
    				R.id.txtFound} 
    	);
    	
    	ListView av = (ListView)findViewById(R.id.listStates);
    	av.setAdapter(adapter);
    	
    	av.setOnItemClickListener(
    		new AdapterView.OnItemClickListener() {
	    		public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
    				ContentValues entry = new ContentValues();
    				entry.put(LicensePlates.FOUND_TODAY, getResources().getString(R.string.found));
	    			mDB.update(LicensePlates.LICENSE_PLATE_TABLE, entry, "_id=" + id, null);
	    			adapter.getCursor().requery();
				}
    		});
    	
    	av.setOnItemLongClickListener(
    		new AdapterView.OnItemLongClickListener() {
	    		public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
	    			ContentValues entry = new ContentValues();
    				entry.put(LicensePlates.FOUND_TODAY, getResources().getString(R.string.not_found));
	    			mDB.update(LicensePlates.LICENSE_PLATE_TABLE, entry, "_id=" + id, null);
	    			adapter.getCursor().requery();
	    			
					return true;
				}
    		});
	}
}



	