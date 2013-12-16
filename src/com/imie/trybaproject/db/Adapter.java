package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;

public interface Adapter<T, U> {
		
	String createTable();
	
	long insert(T item);
	
	long update(T item);
	
	void delete(T item);
	
	T get(U id);
	
	ArrayList<T> getAll();
	
}
