package com.imie.trybaproject.activity;

import java.util.ArrayList;
import java.util.Currency;

import android.app.Activity;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.imie.trybaproject.R;
import com.imie.trybaproject.model.MenuItem;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.model.UserType;

public class HomeActivity extends FragmentActivity {

	DrawerLayout drawerLayout;
	ActionBarDrawerToggle drawerActionBarToggle;
	ListView drawerList;
	String[] titles;
	ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	User currentUser = new User();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		SharedPreferences preferences = 
				getSharedPreferences("DEFAULT", Activity.MODE_PRIVATE); 
		
		
		try {
			currentUser.setUserWithSerializableString(
					preferences.getString("CURRENT_USER", null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		MenuItem itemStationSelect = new MenuItem(new ChooseStationFragment(),getString(R.string.station_selection));
		MenuItem itemStationChange = new MenuItem(null,getString(R.string.station_change));
		MenuItem itemUsersManagement = new MenuItem(new ListUsersFragment(),getString(R.string.users_management));
		MenuItem itemOrdersManagement = new MenuItem(new ListOrdersFragment(),getString(R.string.orders_management));
		MenuItem itemProductScan = new MenuItem(null,getString(R.string.product_scan));
		MenuItem itemLogout = new MenuItem(null,getString(R.string.logout));
		
		items.add(itemStationSelect);
		items.add(itemStationChange);
		items.add(itemUsersManagement);
		items.add(itemOrdersManagement);
		items.add(itemProductScan);
		items.add(itemLogout);
		
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		drawerList = (ListView) findViewById(R.id.left_drawer);
		drawerList.setAdapter(new ArrayAdapter<MenuItem>(this, 
					R.layout.drawer_list_item,items));
			
		drawerList.setOnItemClickListener(new DrawerItemClickListener());

		
		
		//selectItem(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	
	
	



	private class DrawerItemClickListener implements ListView.OnItemClickListener{
		@Override
        public void onItemClick(AdapterView<?> parent, View view, int position, 
        	long id) {
		
			MenuItem item = items.get(position);
			
			Fragment fragment= item.getFragment();
						
			setTitle(item.getTitle());
			
			FragmentManager fragmentManager = getSupportFragmentManager();
		    fragmentManager.beginTransaction()
		                   .replace(R.id.choose_station_fragment, fragment)
		                   .commit();
		    
		   
		    drawerList.setItemChecked(position, true);
		    drawerLayout.closeDrawer(drawerList);
        }		
	}
	
	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(title);
		getActionBar().setTitle(title);
	}

	
	
	
}
