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
	public static final String COL_ID = "id";
	public static final String COL_CUSTOMER = "customer";
	public static final String COL_QUANTITY = "quantity";
	
	
	private SQLiteDatabase db;
	
	public ClientOrderAdapter(SQLiteDatabase db){
		this.db = db;
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
		ContentValues values = new ContentValues();
		values.put(COL_CUSTOMER, item.getCustomer());
		values.put(COL_QUANTITY, item.getQuantity());
		
		long id = db.insert(TABLE, null, values);
		
		for (Product product : item.getProducts()) {
			OrderProduct op = new OrderProduct(id, product.getId());
			OrderProductAdapter orderProductAdapter = 
												new OrderProductAdapter(db);
			orderProductAdapter.insert(op);
		}
		
		db.close();
		return id;
	}

	@Override
	public long update(ClientOrder item) {
		ContentValues values = new ContentValues();
		values.put(COL_CUSTOMER, item.getCustomer());
		values.put(COL_QUANTITY, item.getQuantity());
		
		int i = db.update(TABLE, values, COL_ID + " = ?",
				new String[] { String.valueOf(item.getId())});
				
		
		
		for (Product product : item.getProducts()) {
			OrderProduct op = new OrderProduct(item.getId(), product.getId());
			OrderProductAdapter orderProductAdapter = 
												new OrderProductAdapter(db);
			orderProductAdapter.update(op);
		}
		
		db.close();
		return i;
	}

	@Override
	public void delete(ClientOrder item) {
		db.delete(TABLE, COL_ID + " = ? ",
				new String[] {String.valueOf(item.getId())});
		OrderProductAdapter orderProductAdapter = new OrderProductAdapter(db);
		orderProductAdapter.deleteAllFromOrder(item.getId());
		db.close();
	}

	@Override
	public ClientOrder get(Integer id) {
		/*
		 * Récupération de la commande
		 * et les IDs Products de la commande
		 */
		
		Cursor cursor = db.query(TABLE,
				new String[] {COL_ID, COL_CUSTOMER, COL_QUANTITY}, 
				COL_ID + " = ? ",
				new String[] {String.valueOf(id)},null,null,null,null);
		
		ClientOrder order = null;
				
		if(cursor != null)
		{
			cursor.moveToFirst();
			
			order = new ClientOrder();
			
			order.setId(Integer.parseInt(cursor.getString(0)));
			order.setCustomer(cursor.getString(1));
			order.setQuantity(Integer.parseInt(cursor.getString(2)));
			OrderProductAdapter orderProductAdapter=new OrderProductAdapter(db);
			order.setProducts(orderProductAdapter.getProducts(id));
		}
		
		db.close();
	
		return order;
	}

	@Override
	public ArrayList<ClientOrder> getAll() {
		
		/*
		 * On récupére toutes les commandes
		 */
		
		Cursor cursor = db.query(TABLE, 
				new String[] {COL_ID, COL_CUSTOMER, COL_QUANTITY,}, 
				null,null,null,null,null);
		
		ArrayList<ClientOrder> orders = new ArrayList<ClientOrder>();
		
		if(cursor != null){
			cursor.moveToFirst();				
			/*
			 * Pour chaque commande, on recherche les ids des produits
			 */
			do {
				ClientOrder order = new ClientOrder();
				order.setId(Integer.parseInt(cursor.getString(0)));
				order.setCustomer(cursor.getString(1));
				order.setQuantity(Integer.getInteger(cursor.getString(2)));
				OrderProductAdapter orderProductAdapter = 
													new OrderProductAdapter(db);
				order.setProducts(orderProductAdapter.getProducts(
										Integer.parseInt(cursor.getString(0))));
				
				orders.add(order);
			} while (cursor.moveToNext());
		}
		
		db.close();
		return orders;
	}

}
