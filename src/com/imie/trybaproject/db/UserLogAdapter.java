package com.imie.trybaproject.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.imie.trybaproject.model.Station;
import com.imie.trybaproject.model.UserLog;

public class UserLogAdapter implements Adapter<UserLog, Integer>{

	public static final String TABLE = "userLog";
	public static final String COL_ID = "_id";
	public static final String COL_USER_ID = "userId";
	public static final String COL_STATION_ID = "stationId";
	public static final String COL_START_DATE = "startDate";
	public static final String COL_END_DATE = "endDate";
	
	private String dateFormat = "yyyy-MM-dd'T'HH:mm";
	private SimpleDateFormat sdf = 
								new SimpleDateFormat(dateFormat, Locale.FRANCE);
	
	private ApplicationSQLiteOpenHelper helper;
	private SQLiteDatabase db;
	
	public UserLogAdapter(){
	}
	
	public UserLogAdapter(ApplicationSQLiteOpenHelper helper){
		if(helper != null){
			this.db = helper.getDb();
			this.helper = helper;
		}		
	}
	
	@Override
	public String createTable() {
		return "CREATE TABLE " 	+ TABLE + "("
				+ COL_ID 		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COL_USER_ID 	+ " INTEGER NOT NULL, "
				+ COL_STATION_ID+ " INTEGER NOT NULL, " 
				+ COL_START_DATE+ " DATETIME NOT NULL,"
				+ COL_END_DATE	+ " DATETIME); ";
	}

	@Override
	public long insert(UserLog item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_USER_ID, item.getUser().getId());
			values.put(COL_STATION_ID, item.getStation().getId());
			if(item.getDateEntree() != null)
				values.put(COL_START_DATE, sdf.format(item.getDateEntree()));
			if(item.getDateSortie() != null)
				values.put(COL_END_DATE, sdf.format(item.getDateSortie()));
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
			values.put(COL_START_DATE, sdf.format(item.getDateEntree()));
			values.put(COL_END_DATE, sdf.format(item.getDateSortie()));
			
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
					new String[]{COL_ID, COL_USER_ID, COL_STATION_ID, 
												COL_START_DATE, COL_END_DATE},
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
				try {
					Date startDate = null;
					Date endDate = null;
					if(!cursor.getString(
							cursor.getColumnIndex(COL_START_DATE)).equals("")){
								startDate = new SimpleDateFormat(
								dateFormat, Locale.FRANCE).
								parse(cursor.getString(
										cursor.getColumnIndex(COL_START_DATE)));
					}
										
					if(cursor.getString(
						cursor.getColumnIndex(COL_END_DATE)) != null && 
						!cursor.getString(
							cursor.getColumnIndex(COL_END_DATE)).equals("")){
						endDate = new SimpleDateFormat(
								dateFormat,Locale.FRANCE).
								parse(cursor.getString(
										cursor.getColumnIndex(COL_END_DATE)));
					}
					log.setDateEntree(startDate);
					log.setDateSortie(endDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
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
			Cursor cursor = this.getAllWithCursor();
			
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
					try {
						Date startDate = new SimpleDateFormat(
								dateFormat, Locale.FRANCE).
								parse(cursor.getString(
										cursor.getColumnIndex(COL_START_DATE)));
						Date endDate = new SimpleDateFormat(
							dateFormat,Locale.FRANCE).parse(cursor.getString(
										cursor.getColumnIndex(COL_END_DATE)));
						log.setDateEntree(startDate);
						log.setDateSortie(endDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					logs.add(log);
				} while (cursor.moveToNext());
			}
			if(helper != null)			
				db.close();
		}
		return logs;
	}
	
	@Override
	public Cursor getAllWithCursor() {
		Cursor cursor = null;
		if(db != null){
			cursor = db.query(TABLE,
					new String[]{COL_ID, COL_USER_ID, COL_STATION_ID, 
												COL_START_DATE, COL_END_DATE},
					null,null, null, null, null);
		}
		return cursor;
	}

	public Boolean isFreeStation(Station station){
		if(db != null){
			Cursor cursor = db.query(TABLE,
				new String[]{COL_ID, COL_STATION_ID, COL_USER_ID, 
					COL_START_DATE, COL_END_DATE},
				COL_STATION_ID + " = ? AND " + COL_END_DATE + " IS NULL",
				new String[]{String.valueOf(station.getId())},
				null, null, COL_ID + " DESC", String.valueOf(1));
		
			if(cursor.getCount() <=  0){
				return true;
			}
		}
		return false;
	}
	
	public void addEndDate(UserLog userLog, Date date){
		userLog.setDateSortie(date);
		this.update(userLog);
	}
	
	@Override
	public void setDatabase(SQLiteDatabase db) {
		this.db = db;
	}

}
