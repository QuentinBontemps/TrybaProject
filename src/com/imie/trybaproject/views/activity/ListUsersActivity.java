package com.imie.trybaproject.views.activity;


import com.imie.trybaproject.R;
import com.imie.trybaproject.views.fragment.ListUsersFragment;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ListUsersActivity extends FragmentActivity {
	Fragment fragUsersList;
	ListUsersFragment test;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_users);
		FragmentManager fm = getSupportFragmentManager();
		fragUsersList = fm.findFragmentById(R.id.fragment_list_users);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
	        case R.id.action_add_user:
	            ((ListUsersFragment) fragUsersList).clickActionAddUser();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}

	}
	
	
	/**
	 * Ouvre l'activity UserCreate
	 */
	
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		
		switch (arg1) {
		case Activity.RESULT_CANCELED:
			Toast.makeText(this, "L'utilisateur n'a pas été créé", Toast.LENGTH_LONG).show();
			break;
		case Activity.RESULT_OK:
			Toast.makeText(this, "L'utilisateur à été ajouté", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}
	
	
	
	
}
