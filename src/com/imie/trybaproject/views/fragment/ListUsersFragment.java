package com.imie.trybaproject.views.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.db.UsersCursorAdapter;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.views.activity.AddUserActivity;

public class ListUsersFragment extends Fragment{
	final int CONST_REQUEST1 = 5001; // Code réponse pour la création
	
	ListView lv;
	UsersCursorAdapter listCursor;
	UserAdapter userAdapt;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		/*ApplicationSQLiteOpenHelper ASLOH = 
				new ApplicationSQLiteOpenHelper(getActivity(), 
						getString(R.string.database_name), null, 
						Integer.parseInt(getString(R.string.database_version)));*/
		
		
		
		View frag = inflater.inflate(R.layout.fragment_list_users, 
															container, false);
		
		setHasOptionsMenu(true);
		
		

		this.lv = (ListView) frag.findViewById(R.id.list);
		
		
		refreshListView();
		/*
		 * ApplicationSQLiteOpenHelper helper = 
				ApplicationSQLiteOpenHelper.getInstance(getActivity());
		userAdapt = new UserAdapter(helper);
		listCursor = new UsersCursorAdapter(getActivity(), 
				userAdapt.getAllWithCursor());
		lv.setAdapter(listCursor); */
		
		// UI Actions		
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                    int index, long arg3) {

            	Cursor c = (Cursor) listCursor.getItem(index);
            	int IdUser = c.getInt(0);
            	
                Toast.makeText(getActivity(),String.valueOf(IdUser), 
                		Toast.LENGTH_LONG).show();
                
                return false;
            	}
			}); 

		return frag;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		listCursor = new UsersCursorAdapter(getActivity(), 
				userAdapt.getAllWithCursor());
		lv.setAdapter(listCursor); // Need user adapter
		this.lv.postInvalidate();

		
	}
	
	
	public void deleteUser(User user){
		
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.user, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	public void clickActionAddUser()
	{
		Intent intent = new Intent(this.getActivity(), AddUserActivity.class);
		this.startActivityForResult(intent, CONST_REQUEST1);
	}
	@Override
	public void setHasOptionsMenu(boolean hasMenu) {
		// TODO Auto-generated method stub
		super.setHasOptionsMenu(hasMenu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
        case R.id.action_add_user:
            clickActionAddUser();
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}
	
	public void refreshListView()
	{
		ApplicationSQLiteOpenHelper helper = 
				ApplicationSQLiteOpenHelper.getInstance(getActivity());
		userAdapt = new UserAdapter(helper);

		listCursor = new UsersCursorAdapter(getActivity(), 
				userAdapt.getAllWithCursor());
		lv.setAdapter(listCursor);
		
	}
	
	
	
	
	
}
