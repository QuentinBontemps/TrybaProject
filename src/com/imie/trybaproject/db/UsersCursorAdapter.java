package com.imie.trybaproject.db;

import com.imie.trybaproject.R;
import com.imie.trybaproject.model.User;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UsersCursorAdapter extends CursorAdapter{
	Context context;
	User u;
	
	public UsersCursorAdapter(Context c, Cursor cursor) {

		super(c, cursor);
		context = c;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context ctx, Cursor c) {
		// TODO Auto-generated method stub
		TextView user_lastname = 
				(TextView) view.findViewById(R.id.row_user_lastname);
		TextView user_firstname = 
				(TextView) view.findViewById(R.id.row_user_firstname);
		TextView user_login = 
				(TextView) view.findViewById(R.id.row_user_login);
		TextView user_password = 
				(TextView) view.findViewById(R.id.row_user_password);
		ImageButton btn_suppression = 
				(ImageButton) view.findViewById(R.id.btn_suppression_user);
		
		u = new User(c.getString(
				c.getColumnIndex(UserAdapter.COL_LOGIN)),
				c.getString(
						c.getColumnIndex(UserAdapter.COL_PASSWORD)),
				c.getString(
						c.getColumnIndex(UserAdapter.COL_FIRSTNAME)),
				c.getString(
						c.getColumnIndex(UserAdapter.COL_LASTNAME)),"");
		
		u.setId(c.getInt(c.getColumnIndex(UserAdapter.COL_ID)));
		
		user_login.setText(c.getString(
				c.getColumnIndex(UserAdapter.COL_LOGIN)));
		user_password.setText(c.getString(
				c.getColumnIndex(UserAdapter.COL_PASSWORD)));
		user_firstname.setText(c.getString(
				c.getColumnIndex(UserAdapter.COL_FIRSTNAME)));
		user_lastname.setText(c.getString(
				c.getColumnIndex(UserAdapter.COL_LASTNAME)));
		
		btn_suppression.setTag(u.getId());
		btn_suppression.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ApplicationSQLiteOpenHelper ASLOH = 
						new ApplicationSQLiteOpenHelper(context, 
								context.getString(R.string.database_name), null, 
								Integer.parseInt(
										context.getString(
												R.string.database_version)));
				UserAdapter userAdapter = new UserAdapter();
				userAdapter.setDatabase(ASLOH.getDb());
				
				long id = (Long)v.getTag();
				
				User user = new User();
				user.setId(id);
				
				userAdapter.delete(user);
				Toast.makeText(context, "L'utilisateur à été supprimé", 
						Toast.LENGTH_LONG).show();;
				
			}
		});
	}

	@Override
	public View newView(Context context, Cursor c, ViewGroup vg) {

		LayoutInflater inflate = LayoutInflater.from(context);
		View view = inflate.inflate(R.layout.row_list_users, vg, false);
		return view;
	}

	
	
	
	
}
