package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.Product;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.model.Zone;
import com.imie.trybaproject.model.ZoneType;

public class ProductAdapter implements Adapter<Product, Integer> {

	public static String TABLE = "product";
	public static String COL_ID = "_id";
	public static String COL_NAME = "name";
	public static String COL_ORDER_ID = "orderId";
	public static String COL_CURRENT_ZONE_ID = "currentZone";
	public static String COL_CURRENT_TYPE_ZONE = "currentTypeZone";
	
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
				+ COL_ORDER_ID	+ " INTEGER NOT NULL,"
				+ COL_CURRENT_ZONE_ID	+ " INTEGER NOT NULL,"
				+ COL_CURRENT_TYPE_ZONE	+ " TEXT NOT NULL)";
	}

	@Override
	public long insert(Product item) {
		long i = -1;
		if(this.db != null && db.isOpen()){
			ContentValues values = new ContentValues();
			values.put(COL_NAME, item.getName());
			values.put(COL_ORDER_ID,item.getOrder().getId());
			values.put(COL_CURRENT_ZONE_ID,item.getCurrentZone().getId());
			values.put(COL_CURRENT_TYPE_ZONE,item.getCurrentTypeZone().toString());
			
			i = db.insert(TABLE, null, values);
			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public long update(Product item) {
		long i = -1;
		if(this.db != null && db.isOpen()){
			ContentValues values = new ContentValues();
			values.put(COL_NAME, item.getName());
			values.put(COL_ORDER_ID,item.getOrder().getId());
			values.put(COL_CURRENT_ZONE_ID,item.getCurrentZone().getId());
			values.put(COL_CURRENT_TYPE_ZONE,item.getCurrentTypeZone().toString());
			
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
					new String[] {COL_ID, COL_NAME, COL_CURRENT_TYPE_ZONE, 
											COL_CURRENT_ZONE_ID,COL_ORDER_ID}, 
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
				product.setCurrentTypeZone(
						ZoneType.initProductTypeByString(cursor.getString(
						cursor.getColumnIndex(COL_CURRENT_TYPE_ZONE))));
				
				switch (product.getCurrentTypeZone()) {
					case STATION : // On recherche la station
						StationAdapter stationAdapter = 
													new StationAdapter(null);
						stationAdapter.setDatabase(db);
						product.setCurrentZone(stationAdapter.get(
								cursor.getInt(
								cursor.getColumnIndex(COL_CURRENT_ZONE_ID)))); // récupère 0
						break;
					case TAMPON : // On recherche le tampon
						TamponAdapter tamponAdapter = new TamponAdapter(null);
						tamponAdapter.setDatabase(db);
						product.setCurrentZone(tamponAdapter.get(
								cursor.getInt(
								cursor.getColumnIndex(COL_CURRENT_ZONE_ID))));
						break;
					default:
						break;
					
				}
					
						
				
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
					product.setCurrentTypeZone(
							ZoneType.initProductTypeByString(cursor.getString(
							cursor.getColumnIndex(COL_CURRENT_TYPE_ZONE))));
					
					switch (product.getCurrentTypeZone()) {
					case STATION : // On recherche la station
						StationAdapter stationAdapter = 
													new StationAdapter(null);
						stationAdapter.setDatabase(db);
						product.setCurrentZone(stationAdapter.get(
								cursor.getInt(
								cursor.getColumnIndex(COL_CURRENT_ZONE_ID))));
						break;
					case TAMPON : // On recherche le tampon
						TamponAdapter tamponAdapter = new TamponAdapter(null);
						tamponAdapter.setDatabase(db);
						product.setCurrentZone(tamponAdapter.get(
								cursor.getInt(
								cursor.getColumnIndex(COL_CURRENT_ZONE_ID))));
						break;
					default:
						break;
					
				}
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
					product.setCurrentTypeZone(
							ZoneType.initProductTypeByString(cursor.getString(
							cursor.getColumnIndex(COL_CURRENT_TYPE_ZONE))));
					
					switch (product.getCurrentTypeZone()) {
					case STATION : // On recherche la station
						StationAdapter stationAdapter = 
													new StationAdapter(null);
						stationAdapter.setDatabase(db);
						product.setCurrentZone(stationAdapter.get(
								cursor.getInt(
								cursor.getColumnIndex(COL_CURRENT_ZONE_ID))));
						break;
					case TAMPON : // On recherche le tampon
						TamponAdapter tamponAdapter = new TamponAdapter(null);
						tamponAdapter.setDatabase(db);
						product.setCurrentZone(tamponAdapter.get(
								cursor.getInt(
								cursor.getColumnIndex(COL_CURRENT_ZONE_ID))));
						break;
					default:
						break;
					
				}
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
	
	public int getAllocatedSpace(Product product)
	{
		Integer result = 0;
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
					new String[] {COL_ID, COL_NAME, COL_CURRENT_TYPE_ZONE, 
											COL_CURRENT_ZONE_ID,COL_ORDER_ID}, 
					COL_CURRENT_TYPE_ZONE + " = ? AND " + 
											COL_CURRENT_ZONE_ID + " = ? ",
					new String[] {product.getCurrentTypeZone().toString(), 
					String.valueOf(product.getCurrentZone().getId())},
													null,null,null,null);
					
			result = cursor.getCount();
			if(helper != null)
				db.close();
		}
		return result;
	}
	
	public Product getByZoneTypeAndZone(ZoneType zt, Zone z) {
		Product product = null;
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
					new String[] {COL_ID, COL_NAME, COL_CURRENT_TYPE_ZONE, 
											COL_CURRENT_ZONE_ID,COL_ORDER_ID}, 
					COL_CURRENT_TYPE_ZONE + " = ? AND " + 
													COL_CURRENT_ZONE_ID + " = ? ",
					new String[] {zt.toString(), 
					String.valueOf(z.getId())},
													null,null,null,null);
						
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
				product.setCurrentTypeZone(
						ZoneType.initProductTypeByString(cursor.getString(
						cursor.getColumnIndex(COL_CURRENT_TYPE_ZONE))));
				
				switch (product.getCurrentTypeZone()) {
					case STATION : // On recherche la station
						StationAdapter stationAdapter = 
													new StationAdapter(null);
						stationAdapter.setDatabase(db);
						product.setCurrentZone(stationAdapter.get(
								cursor.getInt(
								cursor.getColumnIndex(COL_CURRENT_ZONE_ID)))); // récupère 0
						break;
					case TAMPON : // On recherche le tampon
						TamponAdapter tamponAdapter = new TamponAdapter(null);
						tamponAdapter.setDatabase(db);
						product.setCurrentZone(tamponAdapter.get(
								cursor.getInt(
								cursor.getColumnIndex(COL_CURRENT_ZONE_ID))));
						break;
					default:
						break;
					
				}
					
						
				
			}
			if(helper != null)			
				db.close();
		}
		return product;
	}
	

}
