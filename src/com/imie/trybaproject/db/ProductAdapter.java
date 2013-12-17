package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.Product;
import com.imie.trybaproject.model.User;

public class ProductAdapter implements Adapter<Product, Integer> {

	public static String TABLE = "product";
	public static String COL_ID = "id";
	public static String COL_NAME = "name";
	
	private SQLiteDatabase db;
	
	public ProductAdapter(SQLiteDatabase db){
		this.db = db;
	}
	
	@Override
	public String createTable() {
		return "CREATE TABLE " + TABLE + "( "
				+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COL_NAME + " TEXT NOT NULL)";
	}

	@Override
	public long insert(Product item) {
		ContentValues values = new ContentValues();
		values.put(COL_NAME, item.getName());
		
		long i = db.insert(TABLE, null, values);
		db.close();
		return i;
	}

	@Override
	public long update(Product item) {
		ContentValues values = new ContentValues();
		values.put(COL_NAME, item.getName());
		
		int i = db.update(TABLE, values, COL_ID + " = ?",
				new String[] { String.valueOf(item.getId())});
		db.close();
		return i;
	}

	@Override
	public void delete(Product item) {
		db.delete(TABLE, COL_ID + " = ? ",
				new String[] {String.valueOf(item.getId())});
		db.close();
	}

	@Override
	public Product get(Integer id) {
		Cursor cursor = db.query(TABLE,
				new String[] {COL_ID, COL_NAME}, 
				COL_ID + " = ? ",
				new String[] {String.valueOf(id)},null,null,null,null);
		
		Product product = null;
		
		if(cursor.getCount() > 0)
		{
			cursor.moveToFirst();
			product = new Product();
			
			product.setId(Integer.parseInt(cursor.getString(
											 cursor.getColumnIndex(COL_ID))));
			product.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));

		}
		
		db.close();
	
		return product;
	}

	@Override
	public ArrayList<Product> getAll() {
		Cursor cursor = db.query(TABLE, 
				new String[] {COL_ID, COL_NAME}, 
				null,null,null,null,null);
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		if(cursor.getCount() > 0){
			
			cursor.moveToFirst();				
			do {
				Product product = new Product();
				product.setId(Integer.parseInt(cursor.getString(
											cursor.getColumnIndex(COL_ID))));
				product.setName(cursor.getString(
											cursor.getColumnIndex(COL_NAME)));

				products.add(product);
			} while (cursor.moveToNext());
		}
		
		db.close();
		return products;
	}

}
