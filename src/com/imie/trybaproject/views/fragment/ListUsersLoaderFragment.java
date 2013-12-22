package com.imie.trybaproject.views.fragment;

import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.db.UsersCursorAdapter;
import com.imie.trybaproject.db.UsersLoaderCursor;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.widget.CursorAdapter;

public class ListUsersLoaderFragment extends Fragment
	      implements LoaderCallbacks<Cursor>{


	UsersCursorAdapter listCursor;
	UserAdapter userAdapt;
	
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

	

}
