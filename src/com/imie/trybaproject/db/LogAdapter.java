package com.imie.trybaproject.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.imie.trybaproject.model.Log;
import com.imie.trybaproject.model.Product;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.model.UserLog;
import com.imie.trybaproject.model.Zone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LogAdapter implements Adapter<Log, Integer> {
	
	
	public static final String TABLE = "log";
	public static final String COL_ID = "_id";
	public static final String COL_USER_ID = "userId";
	public static final String COL_ZONE_NAME = "zoneName";
	public static final String COL_PRODUCT_ID = "productId";
	public static final String COL_DATE = "date";
	
	private String dateFormat = "yyyy-MM-dd'T'HH:mm";
	private SimpleDateFormat sdf = 
								new SimpleDateFormat(dateFormat, Locale.FRANCE);
	
	private ApplicationSQLiteOpenHelper helper;
	private SQLiteDatabase db;
	
	public LogAdapter(){
	}
	
	public LogAdapter(ApplicationSQLiteOpenHelper helper){
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
				+ COL_ZONE_NAME + " VARCHAR(50) NOT NULL, " 
				+ COL_PRODUCT_ID+ " INTEGER NOT NULL, " 
				+ COL_DATE	+ " DATETIME); ";
	}

	@Override
	public long insert(Log item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_USER_ID, item.getUser().getId());
			values.put(COL_ZONE_NAME, item.getZoneName());
			values.put(COL_PRODUCT_ID, item.getProduct().getId());
			if(item.getDate() != null)
				values.put(COL_DATE, sdf.format(item.getDate()));
			
			i = db.insert(TABLE, null, values);

			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public long update(Log item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_USER_ID, item.getUser().getId());
			values.put(COL_ZONE_NAME, item.getZoneName());
			values.put(COL_PRODUCT_ID, item.getProduct().getId());
			if(item.getDate() != null)
				values.put(COL_DATE, sdf.format(item.getDate()));
			
			i = db.update(TABLE, values, COL_ID + " = ?",
						new String[] { String.valueOf(item.getId())});
			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public void delete(Log item) {
		try {
			throw new Exception("Methode non implémentée");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Log get(Integer id) {
		Log log = null;
		if(db != null){
			Cursor cursor = db.query(TABLE,
					new String[]{COL_ID, COL_USER_ID, COL_PRODUCT_ID, 
					COL_ZONE_NAME, COL_DATE},
					COL_ID + " = ? ",
					new String[]{String.valueOf(id)}, null, null, null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				log = new Log();
				log.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
				log.setZoneName(cursor.getString(
						cursor.getColumnIndex(COL_ZONE_NAME)));

				UserAdapter userAdapter = new UserAdapter(null);
				userAdapter.setDatabase(db);
				log.setUser(userAdapter.get(cursor.getInt(
									cursor.getColumnIndex(COL_USER_ID))));
				ProductAdapter productAdapter = new ProductAdapter(null);
				productAdapter.setDatabase(db);
				log.setProduct(productAdapter.get(cursor.getInt(
									cursor.getColumnIndex(COL_PRODUCT_ID))));
				try {
					Date date = null;
					if(!cursor.getString(
							cursor.getColumnIndex(COL_DATE)).equals("")){
								date = new SimpleDateFormat(
								dateFormat, Locale.FRANCE).
								parse(cursor.getString(
										cursor.getColumnIndex(COL_DATE)));
					}
					
					log.setDate(date);
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
	public ArrayList<Log> getAll() {
		ArrayList<Log> logs = new ArrayList<Log>();
		if(db != null){
			Cursor cursor = this.getAllWithCursor();
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				do {
					Log log = new Log();
					log.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
					log.setZoneName(cursor.getString(
							cursor.getColumnIndex(COL_ZONE_NAME)));
					
					UserAdapter userAdapter = new UserAdapter(null);
					userAdapter.setDatabase(db);
					log.setUser(userAdapter.get(cursor.getInt(
										cursor.getColumnIndex(COL_USER_ID))));
					ProductAdapter productAdapter = new ProductAdapter(null);
					productAdapter.setDatabase(db);
					log.setProduct(productAdapter.get(cursor.getInt(
										cursor.getColumnIndex(COL_PRODUCT_ID))));
					try {
						Date date = null;
						if(!cursor.getString(
								cursor.getColumnIndex(COL_DATE)).equals("")){
									date = new SimpleDateFormat(
									dateFormat, Locale.FRANCE).
									parse(cursor.getString(
											cursor.getColumnIndex(COL_DATE)));
						}
						
						log.setDate(date);
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
					new String[]{COL_ID, COL_USER_ID, COL_PRODUCT_ID, 
												COL_ZONE_NAME, COL_DATE},
					null,null, null, null, null);
		}
		return cursor;
	}

	public Cursor getCursorWithProductId(long productId){
		Cursor cursor = null;
		if(db != null){
			cursor = db.query(TABLE,
					new String[]{COL_ID, COL_USER_ID, COL_PRODUCT_ID, 
					COL_ZONE_NAME, COL_DATE},
					COL_PRODUCT_ID + " = ?",
					new String[]{String.valueOf(productId)}, null, null, null);
		}
		return cursor;
	}
	
	@Override
	public void setDatabase(SQLiteDatabase db) {
		this.db = db;
		
	}

}
