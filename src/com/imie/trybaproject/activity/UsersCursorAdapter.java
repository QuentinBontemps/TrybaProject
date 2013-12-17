package com.imie.trybaproject.activity;

import com.imie.trybaproject.R;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UsersCursorAdapter extends CursorAdapter{

	public UsersCursorAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context ctx, Cursor c) {
		// TODO Auto-generated method stub
		TextView user_lastname = (TextView) view.findViewById(R.id.row_user_lastname);
		TextView user_firstname = (TextView) view.findViewById(R.id.row_user_firstname);
		TextView user_login = (TextView) view.findViewById(R.id.row_user_login);
		TextView user_password = (TextView) view.findViewById(R.id.row_user_password);
		
		user_login.setText(c.getString(1));
		user_password.setText(c.getString(2));
		user_firstname.setText(c.getString(3));
		user_lastname.setText(c.getString(4));
	}

	@Override
	public View newView(Context context, Cursor c, ViewGroup vg) {

		LayoutInflater inflate = LayoutInflater.from(context);
		View view = inflate.inflate(R.layout.row_list_users, vg, false);
		return view;
	}
	
	
}
