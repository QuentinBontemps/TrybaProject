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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.imie.trybaproject.R;
import com.imie.trybaproject.model.MenuItem;
import com.imie.trybaproject.model.Station;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.model.UserType;

public class HomeActivity extends FragmentActivity {

	DrawerLayout drawerLayout;
	ActionBarDrawerToggle drawerToggle;
	ActionBarDrawerToggle drawerActionBarToggle;
	ListView drawerList;
	ListMenuItemAdapter adapter;
	ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	User currentUser = new User();
	int userLogId;
	MenuItem itemStationChange = new MenuItem();
	MenuItem itemStationSelect = new MenuItem();
	MenuItem itemLogout = new MenuItem();
	MenuItem itemProductScan = new MenuItem();
	SharedPreferences preferences;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		preferences = getSharedPreferences("DEFAULT", Activity.MODE_PRIVATE);
		
		String userString = preferences.getString("CURRENT_USER", "");
		userLogId = Integer.parseInt(preferences.getString("CURRENT_USER_LOG_ID", "0"));
		
		itemProductScan = new MenuItem(new ScanFragment(),
				getString(R.string.product_scan));
		
		try {
			if(userString != "")
				currentUser.setWithSerializableString(userString);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		itemStationChange = new MenuItem(getString(R.string.station_change));
		itemStationSelect = new MenuItem(getString(R.string.station_selection));
		
		if(userLogId != 0){
			itemStationChange.setFragment(new ChooseStationFragment(this, true,userLogId));
			items.add(itemProductScan);
			items.add(itemStationChange);			
		}else{
			itemStationSelect.setFragment(new ChooseStationFragment());
			items.add(itemStationSelect);
		}
		MenuItem itemUsersManagement = new MenuItem(new ListUsersFragment(),
				getString(R.string.users_management));
		MenuItem itemOrdersManagement = new MenuItem(new ListOrdersFragment(),
				getString(R.string.orders_management));
		itemLogout = new MenuItem(new LogoutFragment(this),getString(R.string.logout));
			
		if(currentUser.getType().equals(UserType.ADMINISTRATOR.name())){
			items.add(itemUsersManagement);
			items.add(itemOrdersManagement);
		}
		items.add(itemLogout);
		
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		drawerList = (ListView) findViewById(R.id.left_drawer);
		
		adapter = new ListMenuItemAdapter(getApplicationContext(), items);
		drawerList.setAdapter(adapter);
		
		
		drawerList.setOnItemClickListener(new DrawerItemClickListener());
		
	
		 drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
	                R.drawable.ic_drawer,
	                R.string.app_name,
	                R.string.app_name
	        ) {
	            public void onDrawerClosed(View view) { 
	                invalidateOptionsMenu();
	            }
	 
	            public void onDrawerOpened(View drawerView) {
	                invalidateOptionsMenu();
	            }
	        };
	        drawerLayout.setDrawerListener(drawerToggle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.home, menu);
		return false;
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener{
		@Override
        public void onItemClick(AdapterView<?> parent, View view, int position, 
        	long id) {
		
			MenuItem item = items.get(position);
			SelectItem(item, position);			
        }		
	}
	
	private void SelectItem(MenuItem item, int position){
		Fragment fragment= item.getFragment();
		
		setTitle(item.getTitle());
		
		FragmentManager fragmentManager = getSupportFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.choose_station_fragment, fragment)
	                   .commit();
	    
	   
	    drawerList.setItemChecked(position, true);
	    drawerLayout.closeDrawer(drawerList);
	    
	    if(item.equals(itemStationSelect)){
	    	if(items.contains(itemStationSelect)){
		    	preferences = getSharedPreferences("DEFAULT", Activity.MODE_PRIVATE);
		    	userLogId = Integer.parseInt(preferences.getString("CURRENT_USER_LOG_ID", "0"));
		    	
		    	if(userLogId != 0){
		    		items.remove(itemStationSelect);
		    		itemStationChange.setFragment(new ChooseStationFragment(this, true,userLogId));
		    		
					items.add(0, itemProductScan);
					items.add(1, itemStationChange);
					drawerList.setItemChecked(1, true);
		    	}
	    	}
	    }else if(item.equals(itemStationChange)){
	    	if(items.contains(itemStationChange)){
		    	items.remove(itemProductScan);
	    		items.remove(itemStationChange);
		    	itemStationSelect.setFragment(new ChooseStationFragment());
				items.add(0, itemStationSelect);
				drawerList.setItemChecked(0, true);
	    	}
	    }else if(item.equals(itemLogout)){
			
	    	SharedPreferences preferences =
					getSharedPreferences("DEFAULT", Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
	    	if(!preferences.getString("CURRENT_USER", "").equals("")){
				editor.remove("CURRENT_USER");
			}
	    	
	    	if(!preferences.getString("CURRENT_USER_LOG_ID", "").equals("")){
	    		editor.remove("CURRENT_USER_LOG_ID");
	    	}
	    	
			editor.commit();
			this.finish();
	    }
	    	    
	    adapter.notifyDataSetChanged();
	}
	
	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(title);
		getActionBar().setTitle(title);
	}

	public MenuItem getStationChange()
	{
		return itemStationChange;
	}
	
	public MenuItem getStationSelect(){
		return itemStationSelect;
	}
	
	public ArrayList<MenuItem> getItems(){
		return items;
	}
	
	public ListMenuItemAdapter getAdapter(){
		return this.adapter;
	}
	
	public MenuItem getProductScan(){
		return this.itemProductScan;
	}
	
	public void setItemCheck(int position){
		this.drawerList.setItemChecked(position, true);
	}
	
}
