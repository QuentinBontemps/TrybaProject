package com.imie.trybaproject.views.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.imie.trybaproject.R;

public class DetailOrderActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_order);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.detail_order, menu);
		return false;
	}

}
