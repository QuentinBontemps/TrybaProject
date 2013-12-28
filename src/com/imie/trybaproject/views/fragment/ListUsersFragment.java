package com.imie.trybaproject.views.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
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
	ApplicationSQLiteOpenHelper helper;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View frag = inflater.inflate(R.layout.fragment_list_users, 
															container, false);

		setHasOptionsMenu(true);
		helper = ApplicationSQLiteOpenHelper.getInstance(getActivity());
		userAdapt = new UserAdapter(helper);
		
		
		this.lv = (ListView) frag.findViewById(R.id.list);

		// Associated context menu
		registerForContextMenu(lv);
				

		listCursor = new UsersCursorAdapter(getActivity(), 
				userAdapt.getAllWithCursor());
		lv.setAdapter(listCursor); 
		
		

		return frag;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		userAdapt = new UserAdapter(helper);
		listCursor = new UsersCursorAdapter(getActivity(), 
				userAdapt.getAllWithCursor());
		lv.setAdapter(listCursor); // Need user adapter
		this.lv.postInvalidate();

		
	}
	
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.user, menu);
		
		
		// SearchView
		MenuItem itemSearch = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView) itemSearch.getActionView();
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				
				userAdapt = new UserAdapter(helper);
				
				listCursor = new UsersCursorAdapter(getActivity(), 
						userAdapt.getWithKeyword(query));
				lv.setAdapter(listCursor);
				if(listCursor.getCount() == 0){
					Toast.makeText(getActivity(), "Aucun résultat", 
													Toast.LENGTH_SHORT).show();
				}
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				if(newText.equals("")){
					userAdapt = new UserAdapter(helper);
					
					listCursor = new UsersCursorAdapter(getActivity(), 
							userAdapt.getAllWithCursor());
					lv.setAdapter(listCursor);
				}
				return false;
			}
			
			
		});
		
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
