package com.imie.trybaproject.activity;

import java.util.ArrayList;
import java.util.Date;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.StationAdapter;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.db.UserLogAdapter;
import com.imie.trybaproject.model.MenuItem;
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
	private User currentUser = new User();
	private ArrayAdapter<Station> stationsArrayAdapter;
	private Button btnValidate;
	   long userLogId;
	
	public ChooseStationFragment(){
		
	}
	
	public ChooseStationFragment(Context ctx,Boolean changeStation, int userLogId){
		if(changeStation){
				
			ApplicationSQLiteOpenHelper helper = 
					new ApplicationSQLiteOpenHelper(ctx,
					ctx.getString(R.string.database_name), null,
					Integer.parseInt(ctx.getString(R.string.database_version)));
			
			UserLogAdapter userLogAdapter = new UserLogAdapter(helper);
			
			UserLog userLog = userLogAdapter.get(userLogId);
			
			UserLogAdapter userLogAdapter2 = new UserLogAdapter(helper);
			userLogAdapter2.addEndDate(userLog, new Date());
				
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View fragment = inflater.inflate(R.layout.choose_station_fragment,
															container, false);
		
		SharedPreferences preferences = getActivity().
				getSharedPreferences("DEFAULT", Activity.MODE_PRIVATE);
		
		String userString = preferences.getString("CURRENT_USER", "");
					
		if(!userString.equals("")){
			try {
				currentUser.setWithSerializableString(userString);
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
				   userLogId = u_LogAdapter.insert(userLog);

				   SharedPreferences preferences = getActivity().
							getSharedPreferences("DEFAULT", Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = preferences.edit();
					
					
					String currentUserString = 
							preferences.getString("CURRENT_USER", "");
					
					if(!currentUserString.equals("")){
						try {
							Station currentStation = 
									(Station)selectStation.getSelectedItem();
							currentUser.setWithSerializableString(
									currentUserString);
							currentUser.setCurrentStation(currentStation);									
							editor.putString("CURRENT_USER", 
									currentUser.getSerializableString());
							Toast.makeText(getActivity(), "Station " + 
									currentStation.getName() + " sélectionnée",
									Toast.LENGTH_SHORT).show();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
										
					editor.putString("CURRENT_USER_LOG_ID", 
							String.valueOf(userLogId));
					editor.commit();
					
					updateMenuList();
				  
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
	
	private void updateMenuList(){
		HomeActivity activity = (HomeActivity) getActivity();
		
		
		
		if(!activity.getItems().contains(activity.getStationChange())){
			activity.getItems().remove(0);
			activity.getItems().add(0, activity.getProductScan());
			activity.getItems().add(1, activity.getStationChange());
			activity.getStationChange().setFragment(new ChooseStationFragment(activity, true, (int) userLogId));
			activity.setItemCheck(1);
			activity.getAdapter().notifyDataSetChanged();
		}
		
	}
 
    
}
