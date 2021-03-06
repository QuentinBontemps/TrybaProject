package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.Station;
import com.imie.trybaproject.model.User;

public class UserAdapter implements Adapter<User, Integer>{
	
	public static final String TABLE = "user";
	public static final String COL_ID = "_id";
	public static final String COL_LOGIN = "login";
	public static final String COL_PASSWORD = "password";
	public static final String COL_FIRSTNAME = "firstname";
	public static final String COL_LASTNAME = "lastname";
	public static final String COL_TYPE = "type";
	public static final String COL_CURRENT_STATION_ID = "currentStationId";
	
	private SQLiteDatabase db;
	private ApplicationSQLiteOpenHelper helper;
	
	public UserAdapter(){
		
	}
	
	public UserAdapter(ApplicationSQLiteOpenHelper helper){
		if(helper != null){
			this.db = helper.getDb();
			this.helper = helper;
		}
	}
	
	@Override
	public String createTable() {
		
		return "CREATE TABLE " + TABLE + "( "
				+ COL_ID 		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COL_LOGIN 	+ " TEXT NOT NULL,"
				+ COL_PASSWORD 	+ " TEXT NOT NULL,"
				+ COL_FIRSTNAME + " TEXT NOT NULL," 
				+ COL_LASTNAME 	+ " TEXT NOT NULL,"
				+ COL_TYPE 		+ " INTEGER NOT NULL,"
				+ COL_CURRENT_STATION_ID + 	" INTEGER )";
	}

	@Override
	public long insert(User item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_LOGIN, item.getLogin());
			values.put(COL_PASSWORD, item.getPassword());
			values.put(COL_FIRSTNAME, item.getFirstname());
			values.put(COL_LASTNAME, item.getLastname());
			values.put(COL_TYPE, item.getType());
			if(item.getCurrentStation() != null)
			values.put(COL_CURRENT_STATION_ID, item.getCurrentStation().getId());
			
			i = db.insert(TABLE, null, values);
			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public long update(User item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_LOGIN, item.getLogin());
			values.put(COL_PASSWORD, item.getPassword());
			values.put(COL_FIRSTNAME, item.getFirstname());
			values.put(COL_LASTNAME, item.getLastname());
			values.put(COL_TYPE, item.getType());
			if(item.getCurrentStation() != null)
			values.put(COL_CURRENT_STATION_ID, item.getCurrentStation().getId());
			
			i = db.update(TABLE, values, COL_ID + " = ?",
					new String[] { String.valueOf(item.getId())});
			if(helper != null)			
				db.close();
		}
		return i;		
	}

	@Override
	public void delete(User item) {
		if(this.db != null){
			db.delete(TABLE, COL_ID + " = ? ",
					new String[] {String.valueOf(item.getId())});
			if(helper != null)
				db.close();
		}
	}

	@Override
	public User get(Integer id) {
		User user = null;
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
					new String[] {COL_ID, COL_LOGIN, COL_PASSWORD,COL_FIRSTNAME,
								COL_LASTNAME, COL_TYPE, COL_CURRENT_STATION_ID}, 
					COL_ID + " = ? ",
					new String[] {String.valueOf(id)},null,null,null,null);
					
			if(cursor.getCount() > 0)
			{
				cursor.moveToFirst();
				user = new User();
				
				user.setId(cursor.getInt(
										cursor.getColumnIndex(COL_ID)));
				user.setLogin(cursor.getString(
										cursor.getColumnIndex(COL_LOGIN)));
				user.setPassword(cursor.getString(
										cursor.getColumnIndex(COL_PASSWORD)));
				user.setFirstname(cursor.getString(
										cursor.getColumnIndex(COL_FIRSTNAME)));
				user.setLastname(cursor.getString(
										cursor.getColumnIndex(COL_LASTNAME)));
				user.setType(cursor.getString(
										cursor.getColumnIndex(COL_TYPE)));
				
				if(cursor.getString(
						cursor.getColumnIndex(COL_CURRENT_STATION_ID)) != null){
					StationAdapter stationAdapter = new StationAdapter(null);
					stationAdapter.setDatabase(db);
					
					user.setCurrentStation(stationAdapter.get(
							Integer.parseInt(cursor.getString(
							cursor.getColumnIndex(COL_CURRENT_STATION_ID)))));
				}
			}
			if(helper != null)
				db.close();
		}
		return user;
	}

	@Override
	public ArrayList<User> getAll() {
		ArrayList<User> users = new ArrayList<User>();
		if(this.db != null){
			Cursor cursor = this.getAllWithCursor();
			
			if(cursor.getCount() > 0){
				
				cursor.moveToFirst();				
				do {
					User user = new User();
					user.setId(cursor.getInt(
										cursor.getColumnIndex(COL_ID)));
					user.setLogin(cursor.getString(
										cursor.getColumnIndex(COL_LOGIN)));
					user.setPassword(cursor.getString(
										cursor.getColumnIndex(COL_PASSWORD)));
					user.setFirstname(cursor.getString(
										cursor.getColumnIndex(COL_FIRSTNAME)));
					user.setLastname(cursor.getString(
										cursor.getColumnIndex(COL_LASTNAME)));
					user.setType(cursor.getString(
										cursor.getColumnIndex(COL_TYPE)));
					
					if(cursor.getString(
						cursor.getColumnIndex(COL_CURRENT_STATION_ID)) != null){
						StationAdapter stationAdapter =
								new StationAdapter(null);
						stationAdapter.setDatabase(db);
						
						user.setCurrentStation(stationAdapter.get(
							Integer.parseInt(cursor.getString(
							cursor.getColumnIndex(COL_CURRENT_STATION_ID)))));
					}
					
					users.add(user);
				} while (cursor.moveToNext());
			}
			if(helper != null)
				db.close();
		}
		return users;	
	}
	
	public Cursor getAllWithCursor(){
		Cursor cursor = null;
		if(this.db != null){
			cursor = db.query(TABLE, 
				new String[] {COL_ID, COL_LOGIN, COL_PASSWORD, COL_FIRSTNAME, 
				COL_LASTNAME, COL_TYPE, COL_CURRENT_STATION_ID}, 
				null,null,null,null,null);
		}
		return cursor;
	}
	
	public User getWithLogin(String login){
		User user = null;
		if(this.db != null){
			Cursor cursor = db.query(TABLE, 
				new String[] {COL_ID, COL_LOGIN, COL_PASSWORD, COL_FIRSTNAME, 
				COL_LASTNAME, COL_TYPE, COL_CURRENT_STATION_ID},
				COL_LOGIN + " = ? ",
				new String[]{login},
				null, null, null, null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				user = new User();
				
				user.setId(cursor.getInt(
										cursor.getColumnIndex(COL_ID)));
				user.setLogin(cursor.getString(
										cursor.getColumnIndex(COL_LOGIN)));
				user.setPassword(cursor.getString(
										cursor.getColumnIndex(COL_PASSWORD)));
				user.setFirstname(cursor.getString(
										cursor.getColumnIndex(COL_FIRSTNAME)));
				user.setLastname(cursor.getString(
										cursor.getColumnIndex(COL_LASTNAME)));
				user.setType(cursor.getString(
										cursor.getColumnIndex(COL_TYPE)));
				
				if(cursor.getString(
						cursor.getColumnIndex(COL_CURRENT_STATION_ID)) != null){
						StationAdapter stationAdapter =
								new StationAdapter(null);
						stationAdapter.setDatabase(db);
						
						user.setCurrentStation(stationAdapter.get(
							Integer.parseInt(cursor.getString(
							cursor.getColumnIndex(COL_CURRENT_STATION_ID)))));
					}
			}
			
			if(helper != null)
				db.close();
		}
		return user;		
	}

	public Cursor getWithKeyword(String keyword){
		Cursor cursor = null;
		if(db != null){
			
			
			String where = COL_LOGIN 		+ " LIKE '%" + keyword + "%' OR " +
							COL_FIRSTNAME 	+ " LIKE '%" + keyword + "%' OR " +
							COL_LASTNAME 	+ " LIKE '%" + keyword + "%'";
			
			cursor = db.query(TABLE,
				new String[] {COL_ID, COL_LOGIN, COL_PASSWORD, COL_FIRSTNAME, 
				COL_LASTNAME, COL_TYPE, COL_CURRENT_STATION_ID},
				where,
				null, null, null, null);
		}
		return cursor;
	}
	
	@Override
	public void setDatabase(SQLiteDatabase db) {
		this.db = db;
	}
}
