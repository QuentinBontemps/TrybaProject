package com.imie.trybaproject.views.fragment;

import com.imie.trybaproject.R;
import com.imie.trybaproject.model.MenuItem;
import com.imie.trybaproject.model.User;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment{

	public HomeFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View fragment =inflater.inflate(R.layout.fragment_home,container,false);
		
		TextView txtHome = (TextView)fragment.findViewById(R.id.txtHome);
		
		SharedPreferences preferences = getActivity().getSharedPreferences(
											"DEFAULT", Activity.MODE_PRIVATE);
		
		String userString = preferences.getString("CURRENT_USER", "");
			
		try {
			if(userString != ""){
				User currentUser = new User();
				currentUser.setWithSerializableString(userString);		
				txtHome.setText("Bienvenue "+ currentUser.getFirstname() + " " +
													currentUser.getLastname());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return fragment;
	}

}
