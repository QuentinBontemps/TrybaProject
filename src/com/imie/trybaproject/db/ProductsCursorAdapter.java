package com.imie.trybaproject.db;

import com.imie.trybaproject.R;
import com.imie.trybaproject.model.Product;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProductsCursorAdapter extends CursorAdapter {

	public ProductsCursorAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(View view, Context ctx, Cursor c) {
		TextView txtName = (TextView) view.findViewById(R.id.txtName);
		TextView txtStatus = (TextView) view.findViewById(R.id.txtStatus);
		
		ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
				ctx, ctx.getString(R.string.database_name), null,
				Integer.parseInt(ctx.getString(R.string.database_version)));
		ProductAdapter productAdapter = new ProductAdapter(helper);

		Product p = productAdapter.get(c.getInt(c
				.getColumnIndex(ProductAdapter.COL_ID)));

		txtName.setText(p.getName() + "   REF : " + p.getId());
		txtStatus.setText("Emplacement : " + p.getCurrentZone().getName());
	}

	@Override
	public View newView(Context context, Cursor c, ViewGroup vg) {
		LayoutInflater inflate = LayoutInflater.from(context);
		View view = inflate.inflate(R.layout.row_list_products, vg, false);
		return view;
	}

}
