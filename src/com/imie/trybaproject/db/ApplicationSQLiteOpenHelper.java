package com.imie.trybaproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ApplicationSQLiteOpenHelper extends SQLiteOpenHelper {

	private static ApplicationSQLiteOpenHelper _instance;
	
	public static ApplicationSQLiteOpenHelper getInstance(Context context){
		if(_instance == null){
			String name = "tryba_database";
			int version = 1;
			_instance = new ApplicationSQLiteOpenHelper(context, name, 
					null, version);
		}
		return _instance;
	}
	
	public ApplicationSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(new UserAdapter().createTable());
		db.execSQL(new ProductAdapter().createTable());;
		db.execSQL(new ClientOrderAdapter().createTable());
		db.execSQL(new TamponAdapter().createTable());
		db.execSQL(new StationAdapter().createTable());
		db.execSQL(new UserLogAdapter().createTable());
		db.execSQL(new LogAdapter().createTable());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public SQLiteDatabase getDb(){
		return this.getWritableDatabase();
	}

}
