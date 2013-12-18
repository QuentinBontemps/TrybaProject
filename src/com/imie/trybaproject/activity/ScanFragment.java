package com.imie.trybaproject.activity;

import com.imie.trybaproject.R;
import com.imie.trybaproject.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScanFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View fragment = inflater.inflate(R.layout.
				fragment_scan,container, false);
		// TODO Auto-generated method stub
		return fragment;
	}
	
}
