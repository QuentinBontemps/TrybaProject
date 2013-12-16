package com.imie.trybaproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ApplicationSQLiteOpenHelper extends SQLiteOpenHelper {

	public ApplicationSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(new UserAdapter(db).createTable());
		db.execSQL(new ProductAdapter(db).createTable());
		db.execSQL(new OrderProductAdapter(db).createTable());
		db.execSQL(new ClientOrderAdapter(db).createTable());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	public SQLiteDatabase getDb(){
		return this.getWritableDatabase();
	}

}
