package com.imie.trybaproject.activity;

import com.imie.trybaproject.R;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class DashboardFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View fragment = inflater.inflate(R.layout.dashborad_fragment, container, false);

		// View objects
		Button scanButton = (Button) fragment.findViewById(R.id.btnScan);
		
		// Actions
		scanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scanAction();
			}
		});
		
		
		return fragment;
	}
	
	private void scanAction()
	{
		Intent intent = new Intent(getActivity(), ScanActivity.class);
		getActivity().startActivity(intent);
	}
}
