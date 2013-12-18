package com.imie.trybaproject.activity;

import java.util.ArrayList;
import java.util.Date;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.StationAdapter;
import com.imie.trybaproject.db.UserLogAdapter;
import com.imie.trybaproject.model.Station;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.model.UserLog;
import com.imie.trybaproject.model.UserType;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ChooseStationFragment extends Fragment {

	private Spinner selectStation;
	private ArrayAdapter<Station> stationsArrayAdapter;
	private Button btnValidate;
	private User currentUser;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View fragment = inflater.inflate(R.layout.choose_station_fragment,
															container, false);
		
		// View object
		Button buttonTest = (Button) fragment.findViewById(R.id.button1);
		Button buttonTest1 = (Button) fragment.findViewById(R.id.Button01);
		
		buttonTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gestionUserButton();
			}
		});		
		buttonTest1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gestionAddCommandeButton();
			}
		});
		
		SharedPreferences preferences = getActivity().
				getSharedPreferences("DEFAULT", Activity.MODE_PRIVATE);
		
		String userString = preferences.getString("CURRENT_USER", "");
					
		currentUser = new User();
		if(!userString.equals("")){
			try {
				currentUser.setUserWithSerializableString(userString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		selectStation = (Spinner) fragment.findViewById(R.id.selectStation);
		
		stationsArrayAdapter = new ArrayAdapter<Station>(
				getActivity(), android.R.layout.simple_list_item_1);
		stationsArrayAdapter.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		selectStation.setAdapter(stationsArrayAdapter);
						
		new LoadStation(getActivity()).execute();
		
		btnValidate = (Button)fragment.findViewById(R.id.btnValidate);
		btnValidate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApplicationSQLiteOpenHelper helper = 
						new ApplicationSQLiteOpenHelper(getActivity(),
						getString(R.string.database_name), null,
						Integer.parseInt(getString(R.string.database_version)));
				
				UserLogAdapter userLogAdapter = new UserLogAdapter(helper);
				
				if(userLogAdapter.isFreeStation(
						(Station)selectStation.getSelectedItem())){
					UserLogAdapter u_LogAdapter = new UserLogAdapter(helper);
					
				   UserLog userLog = new UserLog();
				   userLog.setUser(currentUser);
				   userLog.setStation((Station)selectStation.getSelectedItem());
				   userLog.setDateEntree(new Date());
				   u_LogAdapter.insert(userLog);

				   
				   Intent intent = new Intent(getActivity(), DashboardActivity.class);
				   startActivity(intent);
				}else{
					Toast.makeText(getActivity(), "Station déjà occupée", 
							Toast.LENGTH_SHORT).show();
				}
				
				
			}
		});
		
		return fragment;
	}
	
	private class LoadStation extends AsyncTask<Void, Void, ArrayList<Station>>
	{
		private Context context;
		private ProgressDialog progress;
		
		public LoadStation(Context ctx) {
			this.context = ctx;
		}
		
		@Override
		protected void onPreExecute() {
			progress = new ProgressDialog(context);
			progress.setTitle(getString(R.string.loading));
			progress.show();
			super.onPreExecute();
		}

		@Override
		protected ArrayList<Station> doInBackground(Void... params) {
		   ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
					getActivity(), getString(R.string.database_name), null, 
					Integer.parseInt(getString(R.string.database_version)));
			
			StationAdapter stationAdapter = new StationAdapter(helper);
			
			ArrayList<Station> stations = stationAdapter.getAll();	
			
			return stations;
		}

		@Override
		protected void onPostExecute(ArrayList<Station> result) {
			super.onPostExecute(result);
			stationsArrayAdapter.addAll(result);
			progress.dismiss();
		}
	    
	
	}
	
	private void gestionUserButton()
    {
    	Intent intent = new Intent(getActivity(), ListUsersActivity.class);
    	getActivity().startActivity(intent);
    }
    
    public void gestionAddCommandeButton()
    {
    	Intent intent = new Intent(getActivity(), AddOrderActivity.class);
    	getActivity().startActivity(intent);
    }
    
    
}
