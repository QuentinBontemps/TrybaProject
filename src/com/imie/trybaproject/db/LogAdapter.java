package com.imie.trybaproject.db;

import java.util.ArrayList;

import com.imie.trybaproject.model.Log;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LogAdapter implements Adapter<Log, Integer> {

	@Override
	public String createTable() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public long insert(Log item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long update(Log item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Log item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Log get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Log> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor getAllWithCursor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDatabase(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

}
