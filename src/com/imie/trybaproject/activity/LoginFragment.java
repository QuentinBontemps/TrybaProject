package com.imie.trybaproject.activity;



import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.model.User;

import android.app.Activity;
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

		// Add user in DB pour test
		ApplicationSQLiteOpenHelper ASLOH = 
				new ApplicationSQLiteOpenHelper(getActivity(), 
						"tryba_database", null, 1);
		UserAdapter userAdapt = new UserAdapter(ASLOH);
		
		userAdapt.insert(new User("n1n1","tata","Antonin","Auffray",1));
		
		
		// View objects
		Button btnValidate = (Button) frag.findViewById(R.id.log_BTN_validate);
		ET_login = (EditText) frag.findViewById(R.id.log_ET_Login);
		ET_pssword = (EditText) frag.findViewById(R.id.log_ET_pssword);
		
		
		// Click event on button
		btnValidate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				validateConnexion();
			}
		});
		
		return frag;
	}
	
	/**
	 * Validation de la connexion. Vérification du login//mdp
	 */
	private void validateConnexion()
	{

		Intent intent = new Intent(getActivity(), MainActivity.class);
				
		
		// On cherche dans la base de données le client
		User user; 
		ApplicationSQLiteOpenHelper ASLOH = 
				new ApplicationSQLiteOpenHelper(getActivity(), 
						"tryba_database", null, 1);
		UserAdapter userAdapt = new UserAdapter(ASLOH);
		
		user = userAdapt.getWithLogin(ET_login.getText().toString());
		if (user != null)
		{
			if(user.getPassword().equals(ET_pssword.getText().toString()))
				getActivity().startActivity(intent);
			else
				Toast.makeText(getActivity(), R.string.log_wrongpassword, 
						Toast.LENGTH_LONG).show();
		}else
		{
			Toast.makeText(getActivity(), R.string.log_wronglogin, 
					Toast.LENGTH_LONG).show();
		}
	}
	
}
