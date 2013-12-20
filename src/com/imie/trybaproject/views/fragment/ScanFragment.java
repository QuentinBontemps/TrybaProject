package com.imie.trybaproject.views.fragment;

import com.imie.trybaproject.R;
import com.imie.trybaproject.R.layout;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.LogAdapter;
import com.imie.trybaproject.db.ProductAdapter;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.db.UserLogAdapter;
import com.imie.trybaproject.model.Log;
import com.imie.trybaproject.model.Product;
import com.imie.trybaproject.model.Station;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.model.UserLog;
import com.imie.trybaproject.model.ZoneType;
import com.imie.trybaproject.views.activity.ScanActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ScanFragment extends Fragment {

	EditText et_idProduct;
	Button btn_validate;
	SharedPreferences preferences;
	User currentUser;
	final int CONST_REQUEST3 = 5003; // Code réponse du scan
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View fragment = inflater.inflate(R.layout.
				fragment_scan,container, false);
		
		preferences = getActivity().getSharedPreferences("DEFAULT", 
													Activity.MODE_PRIVATE);
		currentUser = new User();
		String userString = preferences.getString("CURRENT_USER","");
		if(userString != "")
		{
			 try {
				currentUser.setWithSerializableString(userString);
			 } catch (Exception e) {
				android.util.Log.e("monAppli", e.getMessage());
			}
		 }
		
		
		// View objects
		et_idProduct = (EditText) fragment.findViewById(R.id.scan_ET_idProduit);
		btn_validate = (Button) fragment.findViewById(R.id.scan_btn_validate);
		
		
		
		// Binding action
		btn_validate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				validateAction();
				
			}
		});
		
		
		// On regarde si un item est déjà en cours sur la station, 
		// Sinon on lance le scanView
		Boolean itemOnStation = false;
		Product productOnStation = null;
		
		productOnStation = getProductOnStation(currentUser.getCurrentStation());
		
		if (productOnStation == null)
		{
			openScanView();
		}else
		{
			et_idProduct.setText(String.valueOf(productOnStation.getId()));
		}
				
				
		return fragment;
	}
	
	/**
	 * 
	 * @return
	 */
	private int validateAction()
	{
		int result = 0;
		// On test si le produit existe
		// On récupère sa station courrante
		// On vérifie si sa station courrante permet de passer dans la station en cours
		// si oui on passe dans la bonne station
		
		ApplicationSQLiteOpenHelper ASLOH = 
				ApplicationSQLiteOpenHelper.getInstance(getActivity());
		
		ProductAdapter productAdapter = new ProductAdapter(null);
		productAdapter.setDatabase(ASLOH.getDb());
		
		Product product = new Product();
		int userId;
		
		// On récupère l'id
		int idProductScan = Integer.valueOf(et_idProduct.getText().toString());
		
		product = productAdapter.get(idProductScan);
		if (product != null) // On a bien récupèré le produit
		{
			// on test si le product est bien dans une station, 
			//et pas en attente dans un tampon
			if (product.getCurrentTypeZone() == ZoneType.STATION)
			{				
				preferences = getActivity().getSharedPreferences("DEFAULT", 
														Activity.MODE_PRIVATE);
				
				 userId = 
						Integer.parseInt(preferences.getString(
												"CURRENT_USER_LOG_ID", "0"));
				 User user = new User();
				 String userString = preferences.getString("CURRENT_USER","");
				 try {
						if(userString != "")
							user.setWithSerializableString(userString);			
					
					int resultatChangement = product.goToNextTampon(
												user.getCurrentStation(),
															ASLOH, user);
					
					switch (resultatChangement) {
					case 0: 
						Toast.makeText(getActivity(), 
						"Le produit n'est pas dans la station correspondante", 
													Toast.LENGTH_LONG).show();
						break;
					case 1:
						ProductAdapter productAdapt = new ProductAdapter(ASLOH);
						productAdapt.update(product);
						
						Toast.makeText(getActivity(), 
						"Le produit est passé dans le prochain tampon", 
												Toast.LENGTH_LONG).show();						
						break;
					case 2:
						Toast.makeText(getActivity(), 
								"Le produit n'est pas dans la bonne station", 
								Toast.LENGTH_LONG).show();
						break;
					default:
						break;
					}
				 } catch (Exception e) {
					android.util.Log.e("monAppli", e.getMessage());
				}
					
			}else{
				Toast.makeText(getActivity(), "Le produit est dans un tampon", 
						Toast.LENGTH_LONG).show();
			}
		
		}else{
			Toast.makeText(getActivity(), "Cet id n'existe pas ", 
					Toast.LENGTH_LONG).show();
		}
		ASLOH.getDb().close();
		
		return result;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
			case CONST_REQUEST3: 
				if (resultCode == Activity.RESULT_OK)
				{
					Product product = new Product();
					int userId = 0;
					
					ApplicationSQLiteOpenHelper ASLOH = 
							ApplicationSQLiteOpenHelper.getInstance(
																getActivity());
					
					
					SharedPreferences preferences = 
							getActivity().getSharedPreferences("DEFAULT", 
									Activity.MODE_PRIVATE);
					Integer idValueScan = preferences.getInt("ID_SCAN_VALUE", 0);
					if (idValueScan != 0)
					{
						
						et_idProduct.setText(String.valueOf(idValueScan));
									
						
						ProductAdapter productAdapter = new ProductAdapter(null);
						productAdapter.setDatabase(ASLOH.getDb());
						product = productAdapter.get(idValueScan);
						
						if (product != null)
						{
							// on test si le product est bien dans un tampon, 
							//et pas déjà dans une station
							if (product.getCurrentTypeZone() == ZoneType.TAMPON)
							{				
								preferences = getActivity().getSharedPreferences(
											"DEFAULT", Activity.MODE_PRIVATE);
								
								 userId = 
										Integer.parseInt(preferences.getString(
												"CURRENT_USER_LOG_ID", "0"));
								 User user = new User();
								 String userString = preferences.getString(
										 					"CURRENT_USER","");
								 try {
										if(userString != "")
											user.setWithSerializableString(
																	userString);			
									
										int resultatChangement = 
														product.goToNextStation(
										user.getCurrentStation(),ASLOH, user);
										
										switch (resultatChangement) {
											case 0: 
												Toast.makeText(getActivity(), 
												"Le produit n'est pas dans " +
												"la station correspondante", 
													Toast.LENGTH_LONG).show();
												break;
											case 1:
												
												Toast.makeText(getActivity(), 
												"Le produit est passé dans le " +
												"prochain tampon", 
													Toast.LENGTH_LONG).show();						
												break;
											case 2:
												Toast.makeText(getActivity(), 
														"Le produit n'est pas " +
														"dans le bon tampon", 
													Toast.LENGTH_LONG).show();
												break;
											default:
												break;
										}
								 }catch (Exception e) {
									android.util.Log.e("", e.getMessage());
								}
									
							}else{
								Toast.makeText(getActivity(), 
										"Le produit est dans une station", 
										Toast.LENGTH_LONG).show();
							}
						}else{
							Toast.makeText(getActivity(), "Aucun produit ne " +
									"correspond à cet ID", 
									Toast.LENGTH_LONG).show();
						}
					}else{
						Toast.makeText(getActivity(), "Aucun code n'a été scanné", 
								Toast.LENGTH_LONG).show();
					}
					
					ASLOH.getDb().close();
				}
				break;
				
				default :
					break;
		}

		
	}
	
	private void openScanView()
	{
		Intent intent = new Intent(getActivity(), ScanActivity.class);
		startActivityForResult(intent, CONST_REQUEST3);
	}
	
	private Product getProductOnStation(Station s)
	{
		Product productOnStation = null;
		ProductAdapter productAdapter;
		
		ApplicationSQLiteOpenHelper helper = 
				ApplicationSQLiteOpenHelper.getInstance(getActivity());
		
		productAdapter = new ProductAdapter(helper);
		productOnStation = productAdapter.getByZoneTypeAndZone(
													ZoneType.STATION, s);
		
		return productOnStation;
	}
}
