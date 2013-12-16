package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.OrderProduct;
import com.imie.trybaproject.model.Product;

public class OrderProductAdapter implements Adapter<OrderProduct, Integer>{

	public static final String TABLE_ORDER_PRODUCT = "orderProduct";
	public static final String COL_ID_ORDER = "orderId";
	public static final String COL_ID_PRODUCT = "productId";
	
	private SQLiteDatabase db;

	public OrderProductAdapter(SQLiteDatabase db){
		this.db = db;
	}
	
	@Override
	public String createTable() {
		return "CREATE TABLE " 	+ TABLE_ORDER_PRODUCT + "("
				+ COL_ID_ORDER 		+ " INTEGER NOT NULL, "
				+ COL_ID_PRODUCT 	+ " INTEGER NOT NULL)";
	}

	@Override
	public long insert(OrderProduct item) {
		ContentValues values = new ContentValues();
		values = new ContentValues();
		values.put(COL_ID_ORDER, item.getOrderId());
		values.put(COL_ID_PRODUCT, item.getProductId());
		
		long id = db.insert(TABLE_ORDER_PRODUCT, null, values);

		return id;
	}

	@Override
	public long update(OrderProduct item) {
		ContentValues values = new ContentValues();
		values.put(COL_ID_ORDER, item.getOrderId());
		values.put(COL_ID_PRODUCT, item.getProductId());
		long i = db.update(TABLE_ORDER_PRODUCT, values, 
				COL_ID_ORDER + " = ? AND " + COL_ID_PRODUCT + " = ? ",
				new String[] {String.valueOf(item.getOrderId()),
				String.valueOf(item.getProductId())
				});
		return i;
	}

	@Override
	public void delete(OrderProduct item) {
		db.delete(TABLE_ORDER_PRODUCT, 
				COL_ID_ORDER + " = ? AND " + COL_ID_PRODUCT + " = ? ",
				new String[] {String.valueOf(item.getOrderId()),
							String.valueOf(item.getProductId())
							});
		db.close();
	}

	public void deleteAllFromOrder(long orderId){
		db.delete(TABLE_ORDER_PRODUCT, COL_ID_ORDER + " = ? ",
				new String[]{String.valueOf(orderId)});
	}
	
	@Override
	public OrderProduct get(Integer id) {
		return null;
	}

	public ArrayList<Product> getProducts(Integer orderId){
		Cursor cursor = db.query(TABLE_ORDER_PRODUCT,
				new String[]{COL_ID_ORDER,COL_ID_PRODUCT},
				COL_ID_ORDER + " = ? ", 
				new String[]{String.valueOf(orderId)}, null, null, null);
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		if(cursor != null){
			cursor.moveToFirst();
			do {
				ProductAdapter productAdapter = new ProductAdapter(db);
				Product product = productAdapter.get(
										Integer.parseInt(cursor.getString(1)));
				products.add(product);
			} while (cursor.moveToNext());
		}

		return products;
	}
	
	@Override
	public ArrayList<OrderProduct> getAll() {
		return null;
	}
	
	
}
