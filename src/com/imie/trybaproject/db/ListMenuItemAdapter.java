package com.imie.trybaproject.db;

import java.util.ArrayList;

import com.imie.trybaproject.R;
import com.imie.trybaproject.model.MenuItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListMenuItemAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<MenuItem> items;
	

	public ListMenuItemAdapter(Context context, ArrayList<MenuItem> items) {
		super();
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
        
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		txtTitle.setText(items.get(position).getTitle());

        ImageView imgView = (ImageView)convertView.findViewById(R.id.imgView);
        imgView.setImageResource(items.get(position).getIcon());
		
        return convertView;
	}

}
