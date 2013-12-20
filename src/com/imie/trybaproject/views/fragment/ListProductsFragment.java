package com.imie.trybaproject.views.fragment;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.ProductAdapter;
import com.imie.trybaproject.db.ProductsCursorAdapter;
import com.imie.trybaproject.model.ClientOrder;
import com.imie.trybaproject.model.Product;
import com.imie.trybaproject.views.activity.DetailProductActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListProductsFragment extends Fragment{
	Cursor products;
	ApplicationSQLiteOpenHelper helper; 
			
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View fragment = inflater.inflate(R.layout.fragment_products_list, 
															container, false);
		
		ListView lv = (ListView)fragment.findViewById(R.id.listView);
		
		Intent intent = getActivity().getIntent();
		ClientOrder order = (ClientOrder)intent.getSerializableExtra("order");
		
		
		helper = ApplicationSQLiteOpenHelper.getInstance(getActivity());
		ProductAdapter productAdapter = new ProductAdapter(helper);
		
		products = productAdapter.getByOrderIdWithCursor((int)order.getId());
		lv.setAdapter(new ProductsCursorAdapter(getActivity(), products));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, 
					int position, long id) {
				
				Product product = new Product();
				products.moveToPosition(position);
				
				ProductAdapter adapter = new ProductAdapter(helper);
				product = adapter.get(products.getInt(
						products.getColumnIndex(ProductAdapter.COL_ID)));
				
				Intent intent = new Intent(getActivity(), 
												DetailProductActivity.class);
				
				Bundle b = new Bundle();
				b.putSerializable("product", product);
				intent.putExtras(b);
				getActivity().startActivity(intent);
			}
		});		
		
		
		
		return fragment;
	}

}
