package com.imie.trybaproject.activity;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.ClientOrderAdapter;
import com.imie.trybaproject.db.UserAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class ListOrdersFragment extends Fragment{
	final int CONST_REQUEST2 = 5002; // Code réponse pour la création

	ListView lv;
	OrdersCursorAdapter listCursor;
	ClientOrderAdapter clientOrderAdapt;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Toast.makeText(getActivity(), "Je suis là",Toast.LENGTH_LONG).show();
		View fragment = inflater.inflate(R.layout.fragment_list_orders, 
				container, false);
		
		ApplicationSQLiteOpenHelper ASLOH = 
				new ApplicationSQLiteOpenHelper(getActivity(), 
						getString(R.string.database_name), null, 
						Integer.parseInt(getString(R.string.database_version)));
		clientOrderAdapt = new ClientOrderAdapter(ASLOH);
		
		this.lv = (ListView) fragment.findViewById(R.id.list_orders);
		listCursor = new OrdersCursorAdapter(getActivity(), 
				clientOrderAdapt.getAllWithCursor());
		
		setHasOptionsMenu(true);
		lv.setAdapter(listCursor); 
		
		
		return fragment;
		
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.orders_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	public void clickActionAddOrder()
	{
		Intent intent = new Intent(this.getActivity(), AddOrderActivity.class);
		this.startActivityForResult(intent, CONST_REQUEST2);
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
        case R.id.action_add_order:
        	clickActionAddOrder();
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		listCursor = new OrdersCursorAdapter(getActivity(), 
				clientOrderAdapt.getAllWithCursor());
		lv.setAdapter(listCursor);
		this.lv.postInvalidate();

		
	}
	
	
}
