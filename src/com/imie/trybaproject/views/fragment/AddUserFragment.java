package com.imie.trybaproject.views.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.model.MaterialType;
import com.imie.trybaproject.model.ProductType;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.model.UserType;

public class AddUserFragment extends Fragment {
	
	Activity monActi;
	private EditText ET_login;
	private EditText ET_pssword;
	private EditText ET_firstname;
	private EditText ET_lastname;
	private Spinner typeUser;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View frag = inflater.inflate(R.layout.
				fragment_add_user,container, false);
		monActi = this.getActivity();		
		monActi.setResult(Activity.RESULT_CANCELED);
		
		
		// View objects
		Button btnValidate = (Button) frag.findViewById(R.id.add_user_validate);
		ET_login = (EditText) frag.findViewById(R.id.add_user_ET_Login);
		ET_pssword = (EditText) frag.findViewById(R.id.add_user_ET_pssword);
		ET_firstname = (EditText) frag.findViewById(R.id.add_user_ET_firstname);
		ET_lastname = (EditText) frag.findViewById(R.id.add_user_ET_lastname);
		typeUser = (Spinner) frag.findViewById(R.id.add_user_SPIN_typeUser);
		
		// init spinner
		List<String> listTypeUser = new ArrayList<String>();
		listTypeUser.add(UserType.ADMINISTRATOR.toString());
		listTypeUser.add(UserType.OPERATOR.toString());
		
		
		ArrayAdapter<String> dataAdapterProduct = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, listTypeUser);
			dataAdapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			typeUser.setAdapter(dataAdapterProduct);
			
			
		// Click event on button
		btnValidate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				validateAddUser();
			}
		});
		
		return frag;
	}
	
	private void validateAddUser()
	{
		Log.v("monAppli","j'enregistre le user");
		// On ajoute l'utilisateur dans la base de données
		User user = new User();

		UserType ut = UserType.OPERATOR;
		// On remplit les champs de l'utilisateur
		user.setFirstname(ET_firstname.getText().toString());
		user.setLastname(ET_lastname.getText().toString());
		user.setLogin(ET_login.getText().toString());
		user.setPassword(ET_pssword.getText().toString());
		
		ut = UserType.initUserTypeByString(typeUser.getSelectedItem().toString());
		
		user.setType(ut.toString());
		
		ApplicationSQLiteOpenHelper ASLOH = 
				ApplicationSQLiteOpenHelper.getInstance(getActivity());
		UserAdapter userAdapt = new UserAdapter(ASLOH);
		
		long idResultatInsertion = userAdapt.insert(user);
		if (idResultatInsertion > 0)
		{
			Log.v("monAppli","id user : " + idResultatInsertion);
			monActi.setResult(Activity.RESULT_OK);
			this.getActivity().finish();
		}
		
	}
}
