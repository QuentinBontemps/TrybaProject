package com.imie.trybaproject.views.fragment;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.ClientOrderAdapter;
import com.imie.trybaproject.db.OrdersCursorAdapter;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.model.ClientOrder;
import com.imie.trybaproject.views.activity.AddOrderActivity;
import com.imie.trybaproject.views.activity.DetailOrderActivity;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListOrdersFragment extends Fragment{
	final int CONST_REQUEST2 = 5002; // Code réponse pour la création

	ListView lv;
	OrdersCursorAdapter listCursor;
	ClientOrderAdapter clientOrderAdapt;
	Cursor orders;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View fragment = inflater.inflate(R.layout.fragment_list_orders, 
				container, false);
		
		ApplicationSQLiteOpenHelper ASLOH = 
				ApplicationSQLiteOpenHelper.getInstance(getActivity());
		clientOrderAdapt = new ClientOrderAdapter(ASLOH);
		
		lv = (ListView) fragment.findViewById(R.id.list_orders);
	
		orders = clientOrderAdapt.getAllWithCursor();
		
		listCursor = new OrdersCursorAdapter(getActivity(), orders);
		
		setHasOptionsMenu(true);
		lv.setAdapter(listCursor); 
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, 
					int position, long id) {
				orders.moveToFirst();
				orders.moveToPosition(position);
				
				ClientOrder order = new ClientOrder();
				
				order.setId(orders.getInt(
				  orders.getColumnIndex(ClientOrderAdapter.COL_ID)));
				order.setCustomer(orders.getString(
				  orders.getColumnIndex(ClientOrderAdapter.COL_CUSTOMER)));
				order.setTypeProduct(orders.getString(
				  orders.getColumnIndex(ClientOrderAdapter.COL_TYPE_PRODUCT)));
				order.setTypeMaterial(orders.getString(
				  orders.getColumnIndex(ClientOrderAdapter.COL_TYPE_MATERIAL)));
				order.setQuantity(orders.getInt(
				  orders.getColumnIndex(ClientOrderAdapter.COL_QUANTITY)));
				
				Intent intent = new Intent(getActivity(), 
						DetailOrderActivity.class);
				
				Bundle b = new Bundle();
				b.putSerializable("order", order);
				intent.putExtras(b);
				
				getActivity().startActivity(intent);
			}
		});
		
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
		orders = clientOrderAdapt.getAllWithCursor();
		listCursor = new OrdersCursorAdapter(getActivity(), orders);
		lv.setAdapter(listCursor);
		this.lv.postInvalidate();

		
	}
	
	
}
