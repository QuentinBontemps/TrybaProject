package com.imie.trybaproject.views.activity;

import java.util.ArrayList;
import java.util.Currency;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ListMenuItemAdapter;
import com.imie.trybaproject.model.MenuItem;
import com.imie.trybaproject.model.Station;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.model.UserType;
import com.imie.trybaproject.views.fragment.ChooseStationFragment;
import com.imie.trybaproject.views.fragment.ListOrdersFragment;
import com.imie.trybaproject.views.fragment.ListUsersFragment;
import com.imie.trybaproject.views.fragment.LogoutFragment;
import com.imie.trybaproject.views.fragment.ScanFragment;

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
		userLogId = Integer.parseInt(preferences.getString("CURRENT_USER_LOG_ID"
																		, "0"));
		
		itemProductScan = new MenuItem(new ScanFragment(),
				getString(R.string.product_scan),R.drawable.ic_menu_archive);
		
		try {
			if(userString != "")
				currentUser.setWithSerializableString(userString);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		itemStationChange = new MenuItem(getString(R.string.station_change),
				R.drawable.ic_menu_mylocation);
		itemStationSelect = new MenuItem(getString(R.string.station_selection),
				R.drawable.ic_menu_mylocation);
		
		if(userLogId != 0){
			itemStationChange.setFragment(new ChooseStationFragment(this, true, 
					userLogId));
			items.add(itemProductScan);
			items.add(itemStationChange);			
		}else{
			itemStationSelect.setFragment(new ChooseStationFragment());
			items.add(itemStationSelect);
		}
		MenuItem itemUsersManagement = new MenuItem(new ListUsersFragment(),
				getString(R.string.users_management),
				R.drawable.ic_menu_cc_am);
		MenuItem itemOrdersManagement = new MenuItem(new ListOrdersFragment(),
				getString(R.string.orders_management),
				R.drawable.ic_menu_archive);
		itemLogout = new MenuItem(new LogoutFragment(this),
				getString(R.string.logout),
				R.drawable.ic_menu_set_as);
			
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
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_drawer);
	
		 drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
	                R.drawable.ic_navigation_drawer,
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

	
	
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
	    case android.R.id.home:
	        if (this.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
	            this.drawerLayout.closeDrawer(Gravity.LEFT);
	        } else {
	            this.drawerLayout.openDrawer(Gravity.LEFT);
	        }
	        return true;
	        
	}
    return super.onOptionsItemSelected(item);
	}



	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		this.drawerToggle.syncState();
	}

	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
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
	    
	   
	    //drawerList.setItemChecked(position, true);
	    drawerLayout.closeDrawer(drawerList);
	    
	    if(item.equals(itemStationSelect)){
	    	if(items.contains(itemStationSelect)){
		    	preferences = getSharedPreferences("DEFAULT", 
		    											Activity.MODE_PRIVATE);
		    	userLogId = Integer.parseInt(preferences.getString(
		    										"CURRENT_USER_LOG_ID", "0"));
		    	
		    	if(userLogId != 0){
		    		items.remove(itemStationSelect);
		    		itemStationChange.setFragment(new ChooseStationFragment(this, 
		    													true,userLogId));
		    		
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
