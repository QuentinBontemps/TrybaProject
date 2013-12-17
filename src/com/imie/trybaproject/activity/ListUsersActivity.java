package com.imie.trybaproject.activity;


import com.imie.trybaproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ListUsersActivity extends FragmentActivity {
	final int CONST_REQUEST1 = 5001; // Code réponse pour la création
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_users);
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
	            clickActionAddUser();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}

	}
	
	
	/**
	 * Ouvre l'activity UserCreate
	 */
	private void clickActionAddUser()
	{
		Intent intent = new Intent(this, AddUserActivity.class);
		this.startActivityForResult(intent, CONST_REQUEST1);
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		
		switch (arg1) {
		case Activity.RESULT_CANCELED:
			Toast.makeText(this, "Retour faux", Toast.LENGTH_LONG).show();
			break;
		case Activity.RESULT_OK:
			Toast.makeText(this, "Retour OK", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}
	
	
}
