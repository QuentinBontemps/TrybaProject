package com.imie.trybaproject.views.fragment;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.LogAdapter;
import com.imie.trybaproject.db.LogsCursorsAdapter;
import com.imie.trybaproject.model.Product;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class DetailProductFragment extends Fragment {

	Product product;
	LogsCursorsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragment = inflater.inflate(R.layout.fragment_detail_product, 
				container, false);
		
		Intent intent = getActivity().getIntent();
		product = (Product)intent.getSerializableExtra("product");
		
		TextView txtName = (TextView) fragment.findViewById(R.id.txtName);
		TextView txtEmplacement = (TextView) fragment.findViewById(R.id.txtEmplacement);
		ListView lv = (ListView) fragment.findViewById(R.id.listView);
			
		txtName.setText(product.getName());
		txtEmplacement.setText(product.getCurrentZone().getName());
		
		ApplicationSQLiteOpenHelper helper = 
				ApplicationSQLiteOpenHelper.getInstance(getActivity());
		
		LogAdapter logAdapter = new LogAdapter(helper);
		Cursor logs = logAdapter.getCursorWithProductId(product.getId());
		
		adapter = new LogsCursorsAdapter(getActivity(), logs);
		lv.setAdapter(adapter);
		
		return fragment;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
}
