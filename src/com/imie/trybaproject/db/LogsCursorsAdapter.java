package com.imie.trybaproject.db;

import com.imie.trybaproject.R;
import com.imie.trybaproject.model.Log;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LogsCursorsAdapter extends CursorAdapter{

	public LogsCursorsAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(View view, Context ctx, Cursor c) {

		TextView operator = (TextView)view.findViewById(R.id.txtOperator);
		TextView zone = (TextView)view.findViewById(R.id.txtZone);
		TextView date = (TextView)view.findViewById(R.id.txtDate);

		ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
				ctx, ctx.getString(R.string.database_name), null,
				Integer.parseInt(ctx.getString(R.string.database_version)));
		LogAdapter logAdapter = new LogAdapter(helper);
		Log log = logAdapter.get(c.getInt(c.getColumnIndex(LogAdapter.COL_ID)));
		
		operator.setText(log.getUser().getFirstname() + " " + log.getUser().getLastname());
		zone.setText(log.getZone().getName());
		date.setText(LogAdapter.sdf.format(log.getDate()));
		
	}

	@Override
	public View newView(Context context, Cursor c, ViewGroup vg) {
		LayoutInflater inflate = LayoutInflater.from(context);
		View view = inflate.inflate(R.layout.row_list_history, vg, false);
		return view;
	}
}
