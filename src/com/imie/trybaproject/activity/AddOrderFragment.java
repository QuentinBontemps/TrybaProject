package com.imie.trybaproject.activity;
import java.util.ArrayList;
import java.util.List;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.ClientOrderAdapter;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.model.ClientOrder;
import com.imie.trybaproject.model.MaterialType;
import com.imie.trybaproject.model.ProductType;
import com.imie.trybaproject.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddOrderFragment extends Fragment {

	EditText customer;
	EditText quantity;
	Spinner typeProduct;
	Spinner typeMaterial;
	Button validate;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View frag = inflater.inflate(R.layout.
				framgment_add_order,container, false);
		
		
		// View object
		customer = (EditText) frag.findViewById(R.id.add_order_ET_customer);
		quantity = (EditText) frag.findViewById(R.id.add_order_ET_Quantity);
		typeProduct = (Spinner) frag.findViewById(R.id.add_order_SPIN_typeProduct);
		typeMaterial = (Spinner) frag.findViewById(R.id.add_order_SPIN_typeMaterial);
		validate = (Button) frag.findViewById(R.id.add_order_BTN_validate);
		
		// Action
		validate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				validateCommande();
				
			}
		}) ;
		
		// Init spinners
		List<String> listTypeProduct = new ArrayList<String>();
		List<String> listTypeMaterial = new ArrayList<String>();
		listTypeProduct.add(ProductType.PORTE.toString());
		listTypeProduct.add(ProductType.FENETRE.toString());
		
		listTypeMaterial.add(MaterialType.ACIER.toString());
		listTypeMaterial.add(MaterialType.ALUMINIUM.toString());
		listTypeMaterial.add(MaterialType.BOIS.toString());		
		
		ArrayAdapter<String> dataAdapterProduct = new ArrayAdapter<String>(getActivity(),
			android.R.layout.simple_spinner_item, listTypeProduct);
		dataAdapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typeProduct.setAdapter(dataAdapterProduct);
		
		ArrayAdapter<String> dataAdapterMaterial = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, listTypeMaterial);
			dataAdapterMaterial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			typeMaterial.setAdapter(dataAdapterMaterial);
			
		
		
		return frag;
	}
	
	private void validateCommande()
	{
		
		
		Log.v("monAppli","j'enregistre le user");
		// On ajoute l'utilisateur dans la base de données
		ClientOrder clientOrder = new ClientOrder();
		ProductType pt = ProductType.FENETRE;
		MaterialType mt = MaterialType.ALUMINIUM;
		
		// On remplit les champs de la commande
		clientOrder.setCustomer(customer.getText().toString());
		clientOrder.setQuantity(Integer.valueOf(quantity.getText().toString()));
		/*mt = MaterialType.initMaterialTypeByValue(typeMaterial.getSelectedItemPosition()+1);*/
		
		pt = ProductType.initProductTypeByString(typeProduct.getSelectedItem().toString());
		mt = MaterialType.initMaterialTypeByString(typeMaterial.getSelectedItem().toString());
		
		
		clientOrder.setTypeMaterial(mt.name());		
		clientOrder.setTypeProduct(pt.name());

		ApplicationSQLiteOpenHelper ASLOH = 
				new ApplicationSQLiteOpenHelper(getActivity(), 
						"tryba_database", null, 1);
		ClientOrderAdapter clientOrderAdapt = new ClientOrderAdapter(ASLOH);
		
		long idResultatInsertion = clientOrderAdapt.insert(clientOrder);
		
		Toast.makeText(getActivity(), "Création de la commande",
											Toast.LENGTH_LONG).show();
		
	}
	
}
