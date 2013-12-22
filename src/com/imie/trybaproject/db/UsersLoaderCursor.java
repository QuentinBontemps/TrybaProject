package com.imie.trybaproject.db;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

public class UsersLoaderCursor extends CursorLoader{

	UserAdapter userAdapt;
	Context context;
	
	public UsersLoaderCursor(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cursor loadInBackground() {
		ApplicationSQLiteOpenHelper helper = 
				ApplicationSQLiteOpenHelper.getInstance(this.context);
		userAdapt = new UserAdapter(helper);
		return userAdapt.getAllWithCursor();
	}

	
}
