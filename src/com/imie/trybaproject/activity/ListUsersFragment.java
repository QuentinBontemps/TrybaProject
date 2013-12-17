package com.imie.trybaproject.activity;


import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.UserAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListUsersFragment extends Fragment{
	ListView lv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ApplicationSQLiteOpenHelper ASLOH = 
				new ApplicationSQLiteOpenHelper(getActivity(), 
						"tryba_database", null, 1);
		UserAdapter userAdapt = new UserAdapter(ASLOH.getDb());
		
		
		View frag = inflater.inflate(R.layout.fragment_list_users,container, false);
		this.lv = (ListView) frag.findViewById(R.id.list);
		lv.setAdapter(new UsersCursorAdapter(getActivity(), userAdapt.getAllCursor())); // Need user adapter
		

		return frag;
	}
	
}
