package com.imie.trybaproject.activity;



import com.imie.trybaproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment{

	private EditText ET_login;
	private EditText ET_pssword;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View frag = inflater.inflate(R.layout.fragment_login,container, false);

		
		// View objects
		Button btnValidate = (Button) frag.findViewById(R.id.log_BTN_validate);
		ET_login = (EditText) frag.findViewById(R.id.log_ET_Login);
		ET_pssword = (EditText) frag.findViewById(R.id.log_ET_pssword);
		
		
		btnValidate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				validateCommande();
			}
		});
		
		// TODO Auto-generated method stub
		return frag;
	}
	
	private void validateCommande()
	{
		Toast.makeText(getActivity(), "je suis la", 
				Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getActivity(), MainActivity.class);
		//intent.putExtra("userB", b);
		getActivity().startActivity(intent);
		/*
		// On cherche dans la base de données le client
		User user; 
		ApplicationSQLiteOpenHelper ASLOH = 
				new ApplicationSQLiteOpenHelper(getActivity(), 
						"tryba_database", null, 1);
		UserAdapter userAdapt = new UserAdapter(ASLOH.getDb());
		
		user = userAdapt.getWithLogin(ET_login.getText().toString());
		if (user != null)
		{
			
		}else
		{
			
		}*/
	}
	
}
