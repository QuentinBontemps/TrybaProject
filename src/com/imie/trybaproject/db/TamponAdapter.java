package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.Product;
import com.imie.trybaproject.model.Tampon;

public class TamponAdapter implements Adapter<Tampon, Integer> {

	
	public static String TABLE = "tampon";
	public static String COL_ID = "id";
	public static String COL_NAME = "name";
	public static String COL_QUANTITY = "quantity";
	
	private SQLiteDatabase db;
	
	public TamponAdapter(SQLiteDatabase db){
		this.db = db;
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
		ContentValues values = new ContentValues();
		values.put(COL_NAME, item.getName());
		values.put(COL_QUANTITY, String.valueOf(item.getQuantity()));
		
		long i = db.insert(TABLE, null, values);
		db.close();
		return i;
	}


	@Override
	public long update(Tampon item) {
		ContentValues values = new ContentValues();
		values.put(COL_NAME, item.getName());
		values.put(COL_QUANTITY, String.valueOf(item.getQuantity()));
		
		int i = db.update(TABLE, values, COL_ID + " = ?",
				new String[] { String.valueOf(item.getId())});
		db.close();
		return i;
	}


	@Override
	public void delete(Tampon item) {
		db.delete(TABLE, COL_ID + " = ? ",
				new String[] {String.valueOf(item.getId())});
		db.close();
	}


	@Override
	public Tampon get(Integer id) {
		Cursor cursor = db.query(TABLE,
				new String[] {COL_ID, COL_NAME}, 
				COL_ID + " = ? ",
				new String[] {String.valueOf(id)},null,null,null,null);
		
		Tampon tampon = null;
		
		if(cursor != null)
		{
			cursor.moveToFirst();
			
			tampon = new Tampon();
			
			tampon.setId(Integer.parseInt(cursor.getString(0)));
			tampon.setName(cursor.getString(1));
			tampon.setQuantity(Integer.parseInt(cursor.getString(2)));

		}
		
		db.close();
	
		return tampon;
	}


	@Override
	public ArrayList<Tampon> getAll() {
		Cursor cursor = db.query(TABLE, 
				new String[] {COL_ID, COL_NAME}, 
				null,null,null,null,null);
		
		ArrayList<Tampon> tampons = new ArrayList<Tampon>();
		
		if(cursor != null){
			
			cursor.moveToFirst();				
			do {
				Tampon tampon = new Tampon();
				
				tampon.setId(Integer.parseInt(cursor.getString(0)));
				tampon.setName(cursor.getString(1));
				tampon.setQuantity(Integer.parseInt(cursor.getString(2)));

				tampons.add(tampon);
			} while (cursor.moveToNext());
		}
		
		db.close();
		return tampons;
	}

	

}
