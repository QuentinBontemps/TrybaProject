package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.UserLog;

public class UserLogAdapter implements Adapter<UserLog, Integer>{

	public static final String TABLE = "clientOrder";
	public static final String COL_ID = "id";
	public static final String COL_USER_ID = "userId";
	public static final String COL_STATION_ID = "stationId";
	
	private ApplicationSQLiteOpenHelper helper;
	private SQLiteDatabase db;
	
	public UserLogAdapter(){
	}
	
	public UserLogAdapter(ApplicationSQLiteOpenHelper helper){
		if(helper != null){
			this.db = db;
			this.helper = helper;
		}
	}
	
	@Override
	public String createTable() {
		return "CREATE TABLE " 	+ TABLE + "("
				+ COL_ID 		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COL_USER_ID 	+ " INTEGER NOT NULL, "
				+ COL_STATION_ID+ " INTEGER NOT NULL); ";
	}

	@Override
	public long insert(UserLog item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_USER_ID, item.getUser().getId());
			values.put(COL_STATION_ID, item.getStation().getId());
			
			i = db.insert(TABLE, null, values);

			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public long update(UserLog item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_USER_ID, item.getUser().getId());
			values.put(COL_STATION_ID, item.getStation().getId());
		
			i = db.update(TABLE, values, COL_ID + " = ?",
						new String[] { String.valueOf(item.getId())});
			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public void delete(UserLog item) {
		try {
			throw new Exception("Methode non implémentée");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserLog get(Integer id) {
		UserLog log = null;
		if(db != null){
			Cursor cursor = db.query(TABLE,
					new String[]{COL_ID, COL_USER_ID, COL_STATION_ID},
					COL_ID + " = ? ",
					new String[]{String.valueOf(id)}, null, null, null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				log = new UserLog();
				log.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
				StationAdapter stationAdapter = new StationAdapter(null);
				stationAdapter.setDatabase(db);
				log.setStation(stationAdapter.get(cursor.getInt(
									cursor.getColumnIndex(COL_STATION_ID))));
				UserAdapter userAdapter = new UserAdapter(null);
				userAdapter.setDatabase(db);
				log.setUser(userAdapter.get(cursor.getInt(
									cursor.getColumnIndex(COL_USER_ID))));
			}
			if(helper != null)			
				db.close();
		}
		return log;
	}

	@Override
	public ArrayList<UserLog> getAll() {
		ArrayList<UserLog> logs = new ArrayList<UserLog>();
		if(db != null){
			Cursor cursor = db.query(TABLE,
					new String[]{COL_ID, COL_USER_ID, COL_STATION_ID},
					null,null, null, null, null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				do {
					UserLog log = new UserLog();
					log.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
					StationAdapter stationAdapter = new StationAdapter(null);
					stationAdapter.setDatabase(db);
					log.setStation(stationAdapter.get(cursor.getInt(
									cursor.getColumnIndex(COL_STATION_ID))));
					UserAdapter userAdapter = new UserAdapter(null);
					userAdapter.setDatabase(db);
					log.setUser(userAdapter.get(cursor.getInt(
									cursor.getColumnIndex(COL_USER_ID))));
					logs.add(log);
				} while (cursor.moveToNext());
			}
			if(helper != null)			
				db.close();
		}
		return logs;
	}

	@Override
	public void setDatabase(SQLiteDatabase db) {
		this.db = db;
	}

}
