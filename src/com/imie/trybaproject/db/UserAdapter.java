package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.User;

public class UserAdapter implements Adapter<User, Integer>{
	
	public static final String TABLE = "user";
	public static final String COL_ID = "id";
	public static final String COL_LOGIN = "login";
	public static final String COL_PASSWORD = "password";
	public static final String COL_FIRSTNAME = "firstname";
	public static final String COL_LASTNAME = "lastname";
	
	private SQLiteDatabase db;
	
	public UserAdapter(SQLiteDatabase db){
		this.db = db;
	}
	
	@Override
	public String createTable() {
		
		return "CREATE TABLE " + TABLE + "( "
				+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COL_LOGIN + " TEXT NOT NULL,"
				+ COL_PASSWORD + " TEXT NOT NULL,"
				+ COL_FIRSTNAME + " TEXT NOT NULL, " 
				+ COL_LASTNAME + " TEXT NOT NULL)";
	}

	@Override
	public long insert(User item) {
		
		ContentValues values = new ContentValues();
		values.put(COL_LOGIN, item.getLogin());
		values.put(COL_PASSWORD, item.getPassword());
		values.put(COL_FIRSTNAME, item.getFirstname());
		values.put(COL_LASTNAME, item.getLastname());
		
		long i = db.insert(TABLE, null, values);
		db.close();
		return i;
	}

	@Override
	public long update(User item) {
		ContentValues values = new ContentValues();
		values.put(COL_LOGIN, item.getLogin());
		values.put(COL_PASSWORD, item.getPassword());
		values.put(COL_FIRSTNAME, item.getFirstname());
		values.put(COL_LASTNAME, item.getLastname());
		
		int i = db.update(TABLE, values, COL_ID + " = ?",
				new String[] { String.valueOf(item.getId())});
		db.close();
		return i;		
	}

	@Override
	public void delete(User item) {
		db.delete(TABLE, COL_ID + " = ? ",
				new String[] {String.valueOf(item.getId())});
		db.close();
	}

	@Override
	public User get(Integer id) {
		Cursor cursor = db.query(TABLE,
				new String[] {COL_ID, COL_LOGIN, COL_PASSWORD, COL_FIRSTNAME, COL_LASTNAME}, 
				COL_ID + " = ? ",
				new String[] {String.valueOf(id)},null,null,null,null);
		
		User user = null;
		
		if(cursor != null)
		{
			cursor.moveToFirst();
			
			user = new User();
			
			user.setId(Integer.parseInt(cursor.getString(0)));
			user.setLogin(cursor.getString(1));
			user.setPassword(cursor.getString(2));
			user.setFirstname(cursor.getString(3));
			user.setLastname(cursor.getString(4));
		}
		
		db.close();
	
		return user;
	}

	@Override
	public ArrayList<User> getAll() {

		Cursor cursor = db.query(TABLE, 
				new String[] {COL_ID, COL_LOGIN, COL_PASSWORD, COL_FIRSTNAME, 
				COL_LASTNAME}, 
				null,null,null,null,null);
		
		ArrayList<User> users = new ArrayList<User>();
		
		if(cursor != null){
			
			cursor.moveToFirst();				
			do {
				User user = new User();
				user.setId(Integer.parseInt(cursor.getString(0)));
				user.setLogin(cursor.getString(1));
				user.setPassword(cursor.getString(2));
				user.setFirstname(cursor.getString(3));
				user.setLastname(cursor.getString(4));
				
				users.add(user);
			} while (cursor.moveToNext());
		}
		
		db.close();
		return users;	
	}
}
