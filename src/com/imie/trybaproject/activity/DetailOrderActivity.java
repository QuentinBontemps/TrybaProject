package com.imie.trybaproject.activity;

import com.imie.trybaproject.R;
import com.imie.trybaproject.R.layout;
import com.imie.trybaproject.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class DetailOrderActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_order);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_order, menu);
		return true;
	}

}
