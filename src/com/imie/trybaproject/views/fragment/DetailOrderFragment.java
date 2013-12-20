package com.imie.trybaproject.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imie.trybaproject.R;
import com.imie.trybaproject.model.ClientOrder;

public class DetailOrderFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragment = inflater.inflate(R.layout.fragment_detail_order, 
															container, false);

		Intent intent = getActivity().getIntent();
		ClientOrder order = (ClientOrder)intent.getSerializableExtra("order");
		
		TextView txtCustomer = (TextView) fragment.findViewById(
															R.id.txtCustomer);
		TextView txtProductType = (TextView) fragment.findViewById(
														R.id.txtProductType);
		TextView txtMaterialType = (TextView) fragment.findViewById(
														R.id.txtMaterialType);
		TextView txtQuantity = (TextView) fragment.findViewById(
															R.id.txtQuantity);
		
		txtCustomer.setText(order.getCustomer());
		txtProductType.setText(order.getTypeProduct());
		txtMaterialType.setText(order.getTypeMaterial());
		txtQuantity.setText(String.valueOf(order.getQuantity()));
		
		return fragment;
	}

	
	
}
