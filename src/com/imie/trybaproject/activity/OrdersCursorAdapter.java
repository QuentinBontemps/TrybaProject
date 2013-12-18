package com.imie.trybaproject.activity;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ClientOrderAdapter;
import com.imie.trybaproject.db.OrderProductAdapter;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.model.OrderProduct;
import com.imie.trybaproject.model.User;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class OrdersCursorAdapter extends CursorAdapter {
	
	Context context;
	User u;
	
	
	public OrdersCursorAdapter(Context c, Cursor cursor) {
		super(c, cursor);
		context = c;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context ctx, Cursor c) {
		// TODO Auto-generated method stub
		TextView order_customer = 
				(TextView) view.findViewById(R.id.row_orders_customer);
		TextView order_quantity = 
				(TextView) view.findViewById(R.id.row_orders_quantity);
		TextView order_typematerial = 
				(TextView) view.findViewById(R.id.row_orders_type_material);
		TextView order_typeproduct = 
				(TextView) view.findViewById(R.id.row_orders_type_product);
		
		
		
		order_customer.setText(ctx.getString(R.string.order_customer)
				+ " " + c.getString(
				c.getColumnIndex(ClientOrderAdapter.COL_CUSTOMER)));
		order_quantity.setText(ctx.getString(R.string.order_quantity)
				+ " " + c.getString(
				c.getColumnIndex(ClientOrderAdapter.COL_QUANTITY)));
		order_typematerial.setText(ctx.getString(R.string.order_type_material) 
				+ " " + c.getString(
			c.getColumnIndex(ClientOrderAdapter.COL_TYPE_MATERIAL)));
		order_typeproduct.setText(ctx.getString(R.string.order_type_product)
				+ " " +	c.getString(
			c.getColumnIndex(ClientOrderAdapter.COL_TYPE_PRODUCT)));
	}

	@Override
	public View newView(Context context, Cursor c, ViewGroup vg) {

		LayoutInflater inflate = LayoutInflater.from(context);
		View view = inflate.inflate(R.layout.row_list_orders, vg, false);
		return view;
	}

}	
