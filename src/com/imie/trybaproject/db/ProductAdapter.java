package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.Product;
import com.imie.trybaproject.model.User;

public class ProductAdapter implements Adapter<Product, Integer> {

	public static String TABLE = "product";
	public static String COL_ID = "_id";
	public static String COL_NAME = "name";
	public static String COL_ORDER_ID = "orderId";
	
	private ApplicationSQLiteOpenHelper helper;
	private SQLiteDatabase db;
	
	public ProductAdapter(){
	}
	
	public ProductAdapter(ApplicationSQLiteOpenHelper helper){
		if(helper != null){
			this.db = helper.getDb();
			this.helper = helper;
		}
	}
	
	@Override
	public String createTable() {
		return "CREATE TABLE " 	+ TABLE + "( "
				+ COL_ID 		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COL_NAME 		+ " TEXT NOT NULL,"
				+ COL_ORDER_ID	+ " INTEGER NOT NULL)";
	}

	@Override
	public long insert(Product item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_NAME, item.getName());
			values.put(COL_ORDER_ID,item.getOrder().getId());
			
			i = db.insert(TABLE, null, values);
			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public long update(Product item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_NAME, item.getName());
			values.put(COL_ORDER_ID,item.getOrder().getId());
			
			i = db.update(TABLE, values, COL_ID + " = ?",
					new String[] { String.valueOf(item.getId())});
			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public void delete(Product item) {
		if(this.db != null){
			db.delete(TABLE, COL_ID + " = ? ",
				new String[] {String.valueOf(item.getId())});
			if(helper != null)			
				db.close();
		}
	}

	@Override
	public Product get(Integer id) {
		Product product = null;
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
					new String[] {COL_ID, COL_NAME, COL_ORDER_ID}, 
					COL_ID + " = ? ",
					new String[] {String.valueOf(id)},null,null,null,null);
						
			if(cursor.getCount() > 0)
			{
				cursor.moveToFirst();
				product = new Product();
				
				product.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
				product.setName(cursor.getString(
											cursor.getColumnIndex(COL_NAME)));
				ClientOrderAdapter orderAdapter = new ClientOrderAdapter(null);
				orderAdapter.setDatabase(db);
				product.setOrder(orderAdapter.get(cursor.getInt(
										cursor.getColumnIndex(COL_ORDER_ID))));
			}
			if(helper != null)			
				db.close();
		}
		return product;
	}

	@Override
	public ArrayList<Product> getAll() {
		ArrayList<Product> products = new ArrayList<Product>();
		if(this.db != null){
				Cursor cursor = this.getAllWithCursor();
						
			if(cursor.getCount() > 0){
				cursor.moveToFirst();				
				do {
					Product product = new Product();
					product.setId(cursor.getInt(
											cursor.getColumnIndex(COL_ID)));
					product.setName(cursor.getString(
											cursor.getColumnIndex(COL_NAME)));
					ClientOrderAdapter orderAdapter = 
												new ClientOrderAdapter(null);
					orderAdapter.setDatabase(db);
					product.setOrder(orderAdapter.get(cursor.getInt(
										cursor.getColumnIndex(COL_ORDER_ID))));
					
					products.add(product);
				} while (cursor.moveToNext());
			}
			if(helper != null)			
				db.close();
		}
		return products;
	}
	
	@Override
	public Cursor getAllWithCursor() {
		Cursor cursor = null;
		if(this.db != null){
			cursor = db.query(TABLE, 
				new String[] {COL_ID, COL_NAME, COL_ORDER_ID}, 
				null,null,null,null,null);
		}
		return cursor;
	}
	
	public Cursor getByOrderIdWithCursor(int orderId) {
		Cursor cursor = null;
		if(this.db != null){
			cursor = db.query(TABLE, 
				new String[] {COL_ID, COL_NAME, COL_ORDER_ID},
				COL_ORDER_ID + " = ? ", new String[] {String.valueOf(orderId)},
				null,null,null,null);
		}
		return cursor;
	}
	
	public ArrayList<Product> getByOrderId(int orderId) {
		ArrayList<Product> products = new ArrayList<Product>();
		if(this.db != null){
				Cursor cursor = this.getByOrderIdWithCursor(orderId);
						
			if(cursor.getCount() > 0){
				cursor.moveToFirst();				
				do {
					Product product = new Product();
					product.setId(cursor.getInt(
											cursor.getColumnIndex(COL_ID)));
					product.setName(cursor.getString(
											cursor.getColumnIndex(COL_NAME)));
					ClientOrderAdapter orderAdapter = 
												new ClientOrderAdapter(null);
					orderAdapter.setDatabase(db);
					product.setOrder(orderAdapter.get(cursor.getInt(
										cursor.getColumnIndex(COL_ORDER_ID))));
					
					products.add(product);
				} while (cursor.moveToNext());
			}
			if(helper != null)			
				db.close();
		}
		return products;
	}

	@Override
	public void setDatabase(SQLiteDatabase db) {
		this.db = db;
	}
	
	public void closeDatabase()
	{
		this.db.close();
	}

}
