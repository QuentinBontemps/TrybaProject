package com.imie.trybaproject.db;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;

public interface Adapter<T, U> {
	
	
	String createTable();
	
	
	/**
	 * Insert item in table
	 * @param item
	 * @return l'identifiant de l'item inseré
	 */
	long insert(T item);
	
	/**
	 * Update item in table
	 * @param item
	 * @return Number of row affected
	 */
	long update(T item);
	
	/**
	 * Delete item in table
	 * @param item
	 */
	void delete(T item);
	
	/**
	 * Get item in table
	 * @param id
	 * @return l'object 
	 */
	T get(U id);
	
	/**
	 * Get all items in table
	 * @return l'ensembre des objects de la table
	 */
	ArrayList<T> getAll();
	
}
