package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.Station;
import com.imie.trybaproject.model.Tampon;

public class StationAdapter implements Adapter<Station, Integer>{

	public static final String TABLE = "station";
	public static final String COL_ID = "_id";
	public static final String COL_NAME = "name";
	public static final String COL_ORDER = "stationOrder";
	public static final String COL_VISIBLE = "visible";
	public static final String COL_TAMPON_ID = "tamponId";
	
	private SQLiteDatabase db;
	private ApplicationSQLiteOpenHelper helper;
	
	public StationAdapter(){
		
	}
	
	public StationAdapter(ApplicationSQLiteOpenHelper helper){
		if(helper != null){
			this.db = helper.getDb();
			this.helper = helper;
		}
	}
	
	@Override
	public String createTable() {
		return "CREATE TABLE " + TABLE + "( "
				+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COL_NAME + " TEXT NOT NULL,"
				+ COL_ORDER + " INTEGER NOT NULL,"
				+ COL_VISIBLE + " BOOLEAN NOT NULL,"
				+ COL_TAMPON_ID + " INTEGER)";
	}

	@Override
	public long insert(Station item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_NAME, item.getName());
			values.put(COL_ORDER, item.getOrder());
			values.put(COL_VISIBLE, item.isVisible());
			if(item.getTampon() != null){
				values.put(COL_TAMPON_ID, item.getTampon().getId());
			}
			i = db.insert(TABLE, null, values);
			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public long update(Station item) {
		long i = -1;
		if(this.db != null){
			ContentValues values = new ContentValues();
			values.put(COL_NAME, item.getName());
			values.put(COL_ORDER, item.getOrder());
			values.put(COL_VISIBLE, item.isVisible());
			if(item.getTampon() != null){
				values.put(COL_TAMPON_ID, item.getTampon().getId());
			}
			i = db.update(TABLE, values, COL_ID + " = ? ",
					new String[]{String.valueOf(item.getId())});
			if(helper != null)			
				db.close();
		}
		return i;
	}

	@Override
	public void delete(Station item) {
		if(this.db != null){
			db.delete(TABLE, COL_ID + " = ? ",
					new String[]{String.valueOf(item.getId())});
			if(item.getTampon() != null){
				TamponAdapter tamponAdapter = new TamponAdapter(null);
				tamponAdapter.setDatabase(db);
				tamponAdapter.delete(item.getTampon());
			}
			if(helper != null)			
				db.close();
		}
	}

	@Override
	public Station get(Integer id) {
		Station station = null;
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
						new String[]{COL_ID, COL_NAME, 
						COL_ORDER, COL_VISIBLE, COL_TAMPON_ID},
						COL_ID + " = ? ", 
						new String[]{String.valueOf(id)}, null, null, null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				station = new Station();
				station.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
				station.setName(cursor.getString(
											cursor.getColumnIndex(COL_NAME)));
				station.setOrder(cursor.getInt(
											cursor.getColumnIndex(COL_ORDER)));
				station.setVisible(cursor.getInt(
						cursor.getColumnIndex(COL_VISIBLE)));
				TamponAdapter tamponAdapter = new TamponAdapter(null);
				tamponAdapter.setDatabase(db);
				station.setTampon(tamponAdapter.get(cursor.getInt(
									   	cursor.getColumnIndex(COL_TAMPON_ID))));	
				
			}
			if(helper != null)			
				db.close();
		}
		return station;
	}
	
	public Station getByOrder(Integer id) {
		Station station = null;
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
						new String[]{COL_ID, COL_NAME, 
						COL_ORDER, COL_VISIBLE, COL_TAMPON_ID},
						COL_ORDER + " = ? ", 
						new String[]{String.valueOf(id)}, 
						null, null, null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				station = new Station();
				station.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
				station.setName(cursor.getString(
											cursor.getColumnIndex(COL_NAME)));
				station.setOrder(cursor.getInt(
											cursor.getColumnIndex(COL_ORDER)));
				station.setVisible(cursor.getInt(
						cursor.getColumnIndex(COL_VISIBLE)));
				TamponAdapter tamponAdapter = new TamponAdapter(null);
				tamponAdapter.setDatabase(db);
				station.setTampon(tamponAdapter.get(cursor.getInt(
									   	cursor.getColumnIndex(COL_TAMPON_ID))));	
				
			}
			if(helper != null)			
				db.close();
		}
		return station;
	}
	

	@Override
	public ArrayList<Station> getAll() {
		ArrayList<Station> stations = new ArrayList<Station>();
		if(this.db != null){
			Cursor cursor = this.getAllWithCursor();
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				do {
					Station station = new Station();
					station.setId(cursor.getInt(
										cursor.getColumnIndex(COL_ID)));
					station.setName(cursor.getString(
										cursor.getColumnIndex(COL_NAME)));
					station.setOrder(cursor.getInt(
										cursor.getColumnIndex(COL_ORDER)));
					TamponAdapter tamponAdapter = new TamponAdapter(null);
					tamponAdapter.setDatabase(db);
					station.setTampon(tamponAdapter.get(cursor.getInt(
										cursor.getColumnIndex(COL_TAMPON_ID))));
					stations.add(station);
				} while (cursor.moveToNext());
			}
			if(helper != null)			
				db.close();
		}
		
		return stations;
	}
	
	
	@Override
	public Cursor getAllWithCursor() {
		Cursor cursor = null;
		if(db != null){
			cursor = db.query(TABLE,
					new String[]{COL_ID, COL_NAME, COL_ORDER, 
					COL_VISIBLE, COL_TAMPON_ID},
					null, null, null, null, null);
		}
		return cursor;
	}
	
	public ArrayList<Station> getStationVisible(){
		ArrayList<Station> stations = new ArrayList<Station>();
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
					new String[]{COL_ID, COL_NAME, 
					COL_ORDER, COL_VISIBLE, COL_TAMPON_ID},
					COL_VISIBLE + " = 1 ", null, null, null, null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				do {
					Station station = new Station();
					station.setId(cursor.getInt(
										cursor.getColumnIndex(COL_ID)));
					station.setName(cursor.getString(
										cursor.getColumnIndex(COL_NAME)));
					station.setOrder(cursor.getInt(
										cursor.getColumnIndex(COL_ORDER)));
					TamponAdapter tamponAdapter = new TamponAdapter(null);
					tamponAdapter.setDatabase(db);
					station.setTampon(tamponAdapter.get(cursor.getInt(
										cursor.getColumnIndex(COL_TAMPON_ID))));
					stations.add(station);
				} while (cursor.moveToNext());
			}
			if(helper != null)			
				db.close();
		}
		
		return stations;
	}

	@Override
	public void setDatabase(SQLiteDatabase db) {
		this.db = db;
	}
	
	public Station getByTampon(Tampon tampon) {
		Station station = null;
		if(this.db != null){
			Cursor cursor = db.query(TABLE,
						new String[]{COL_ID, COL_NAME, 
						COL_ORDER, COL_VISIBLE, COL_TAMPON_ID},
						COL_TAMPON_ID + " = ? ", 
						new String[]{String.valueOf(tampon.getId())}, 
						null, null, null);
			
			if(cursor.getCount() > 0){
				cursor.moveToFirst();
				station = new Station();
				station.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
				station.setName(cursor.getString(
											cursor.getColumnIndex(COL_NAME)));
				station.setOrder(cursor.getInt(
											cursor.getColumnIndex(COL_ORDER)));
				station.setVisible(cursor.getInt(
						cursor.getColumnIndex(COL_VISIBLE)));
				TamponAdapter tamponAdapter = new TamponAdapter(null);
				tamponAdapter.setDatabase(db);
				station.setTampon(tamponAdapter.get(cursor.getInt(
									   	cursor.getColumnIndex(COL_TAMPON_ID))));	
				
			}
			if(helper != null)			
				db.close();
		}
		return station;
	}

}
