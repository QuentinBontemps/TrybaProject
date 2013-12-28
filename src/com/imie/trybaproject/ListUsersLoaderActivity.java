package com.imie.trybaproject;

import android.os.Bundle;
import android.provider.Contacts;
import android.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.database.Cursor;
import android.app.LoaderManager.LoaderCallbacks;

public class ListUsersLoaderActivity extends Activity 
							implements LoaderCallbacks<Cursor>{
	
	// This is the Adapter being used to display the list's data.
    SimpleCursorAdapter mAdapter;

    
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_users_loader);
		
		// Prepare the loader.  Either re-connect with an existing one,
		// or start a new one.
		getLoaderManager().initLoader(1, null, this);
		
		 

        
        
		
		
	}

	@Override
	public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(android.content.Loader<Cursor> arg0, Cursor data) {
		// TODO Auto-generated method stub
		mAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(android.content.Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}

	

	

}
