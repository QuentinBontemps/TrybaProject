package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.Product;
import com.imie.trybaproject.model.Tampon;

public class TamponAdapter implements Adapter<Tampon, Integer> {

	
	public static String TABLE = "tampon";
	public static String COL_ID = "_id";
	public static String COL_NAME = "name";
	public static String COL_QUANTITY = "quantity";
	
	private SQLiteDatabase db;
	private ApplicationSQLiteOpenHelper helper;
	
	public TamponAdapter(){
		
	}
	
	public TamponAdapter(ApplicationSQLiteOpenHelper helper){
		if(helper != null){
			this.db = helper.getDb();
			this.helper = helper;
		}
	}
	
	@Override
	public String createTable() {
		return "CREATE TABLE " + TABLE + "( "
				+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COL_NAME + " TEXT NOT NULL, "
				+ COL_QUANTITY + " INTEGER)";
	}


	@Override
	public long insert(Tampon item) {
		long i = -1;
		if(this.db != null){		
			ContentValues values = new ContentValues();
			values.put(COL_NAME, item.getName());
			values.put(COL_QUANTITY, String.valueOf(item.getQuantity()));
			
			i = db.insert(TABLE, null, values);
			if(helper != null)			
				db.close();
		}
		return i;
	}


	@Override
	public long update(Tampon item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_NAME, item.getName());
			values.put(COL_QUANTITY, String.valueOf(item.getQuantity()));
			
			i = db.update(TABLE, values, COL_ID + " = ?",
					new String[] { String.valueOf(item.getId())});
			if(helper != null)			
				db.close();
		}
		return i;
	}


	@Override
	public void delete(Tampon item) {
		if(this.db != null){
			db.delete(TABLE, COL_ID + " = ? ",
					new String[] {String.valueOf(item.getId())});
			if(helper != null)
				db.close();
		}
	}


	@Override
	public Tampon get(Integer id) {
		Tampon tampon = null;
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
					new String[] {COL_ID, COL_NAME, COL_QUANTITY}, 
					COL_ID + " = ? ",
					new String[] {String.valueOf(id)},null,null,null,null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				tampon = new Tampon();
				
				tampon.setId(cursor.getInt(
										cursor.getColumnIndex(COL_ID)));
				tampon.setName(cursor.getString(
										cursor.getColumnIndex(COL_NAME)));
				tampon.setQuantity(cursor.getInt(
										cursor.getColumnIndex(COL_QUANTITY)));
			}
			if(helper != null)
				db.close();
		}
		return tampon;
	}
	


	@Override
	public ArrayList<Tampon> getAll() {
		ArrayList<Tampon> tampons = new ArrayList<Tampon>();
		if(this.db != null){
			Cursor cursor = db.query(TABLE, 
					new String[] {COL_ID, COL_NAME, COL_QUANTITY}, 
					null,null,null,null,null);
					
			if(cursor.getCount() > 0){
				cursor.moveToFirst();				
				do {
					Tampon tampon = new Tampon();
					
					tampon.setId(cursor.getInt(
										cursor.getColumnIndex(COL_ID)));
					tampon.setName(cursor.getString(
										cursor.getColumnIndex(COL_NAME)));
					tampon.setQuantity(cursor.getInt(
										cursor.getColumnIndex(COL_QUANTITY)));
	
					tampons.add(tampon);
				} while (cursor.moveToNext());
			}
			if(helper != null)
				db.close();
		}
		return tampons;
	}

	@Override
	public Cursor getAllWithCursor() {
		Cursor cursor = null;
		if(this.db != null){
			cursor = db.query(TABLE, 
					new String[] {COL_ID, COL_NAME, COL_QUANTITY}, 
					null,null,null,null,null);
		}
		return cursor;
	}

	@Override
	public void setDatabase(SQLiteDatabase db) {
		this.db = db;
	}

	

}
