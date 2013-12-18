package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.ClientOrder;
import com.imie.trybaproject.model.OrderProduct;
import com.imie.trybaproject.model.Product;

public class ClientOrderAdapter implements Adapter<ClientOrder, Integer> {

	public static final String TABLE = "clientOrder";
	public static final String COL_ID = "_id";
	public static final String COL_CUSTOMER = "customer";
	public static final String COL_QUANTITY = "quantity";
	
	
	private SQLiteDatabase db;
	private ApplicationSQLiteOpenHelper helper;
	
	public ClientOrderAdapter(){
		
	}
	
	public ClientOrderAdapter(ApplicationSQLiteOpenHelper helper){
		if(helper != null){
			this.db = helper.getDb();
			this.helper = helper;
		}
	}
	
	@Override
	public String createTable() {
		return "CREATE TABLE " 	+ TABLE + "("
				+ COL_ID 		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COL_CUSTOMER 	+ " TEXT NOT NULL, "
				+ COL_QUANTITY 	+ " INTEGER NOT NULL); ";
	}

	@Override
	public long insert(ClientOrder item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_CUSTOMER, item.getCustomer());
			values.put(COL_QUANTITY, item.getQuantity());
			
			i = db.insert(TABLE, null, values);

			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public long update(ClientOrder item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_CUSTOMER, item.getCustomer());
			values.put(COL_QUANTITY, item.getQuantity());
		
			i = db.update(TABLE, values, COL_ID + " = ?",
						new String[] { String.valueOf(item.getId())});
			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public void delete(ClientOrder item) {
		if(this.db != null){
			db.delete(TABLE, COL_ID + " = ? ",
					new String[] {String.valueOf(item.getId())});
			OrderProductAdapter orderProductAdapter = new OrderProductAdapter(db);
			orderProductAdapter.deleteAllFromOrder(item.getId());
			if(helper != null)			
				db.close();
		}
	}

	@Override
	public ClientOrder get(Integer id) {
		ClientOrder order = null;
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
					new String[] {COL_ID, COL_CUSTOMER, COL_QUANTITY}, 
					COL_ID + " = ? ",
					new String[] {String.valueOf(id)},null,null,null,null);
				
			if(cursor.getCount() > 0)
			{
				cursor.moveToFirst();		
				order = new ClientOrder();
				
				order.setId(Integer.parseInt(cursor.getString(
												cursor.getColumnIndex(COL_ID))));
				order.setCustomer(cursor.getString(
											cursor.getColumnIndex(COL_CUSTOMER)));
				order.setQuantity(Integer.parseInt(cursor.getString(
											cursor.getColumnIndex(COL_QUANTITY))));
			}
			if(helper != null)			
				db.close();
		}
		return order;
	}

	@Override
	public ArrayList<ClientOrder> getAll() {
		ArrayList<ClientOrder> orders = new ArrayList<ClientOrder>();
		if(this.db != null){
			Cursor cursor = db.query(TABLE, 
					new String[] {COL_ID, COL_CUSTOMER, COL_QUANTITY,}, 
					null,null,null,null,null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();				
				do {
					ClientOrder order = new ClientOrder();
					order.setId(Integer.parseInt(cursor.getString(
												cursor.getColumnIndex(COL_ID))));
					order.setCustomer(cursor.getString(
											cursor.getColumnIndex(COL_CUSTOMER)));
					order.setQuantity(Integer.getInteger(cursor.getString(
											cursor.getColumnIndex(COL_QUANTITY))));
					
					orders.add(order);
				} while (cursor.moveToNext());
			}
			if(helper != null)			
				db.close();
		}
		return orders;
	}
	

	@Override
	public void setDatabase(SQLiteDatabase db) {
		this.db = db;
	}

}
