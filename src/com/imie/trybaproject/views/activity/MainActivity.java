package com.imie.trybaproject.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
										this,
										this.getString(R.string.database_name),
										null,1 );
		
		
		
		//UserAdapter u_adapter = new UserAdapter(helper.getDb());
        
        /*
         * User user = new User("toto", "toto", "toto", "toto");
         * u_adapter.insert(user);
         */
       
        /*
         * User user = new User("tata", "toto", "toto", "toto");
         * user.setId(1);
         * u_adapter.update(user);
         */
        
        /*
         * User user = u_adapter.get(1);
         * Log.i("user", user.getLastname() +  " "  + user.getFirstname() );
         */
        
        /*
         *  User user = new User("tata", "toto", "toto", "toto");
         *  user.setId(1);
         *  u_adapter.delete(user);
         */
		/*
		ArrayList<Product> products = new ArrayList<Product>();
		
		ProductAdapter p_adapter1 = new ProductAdapter(helper.getDb());
		Product p = new Product("product1");
        p_adapter1.insert(p);
        
        
        ProductAdapter p_adapter2 = new ProductAdapter(helper.getDb());
		Product p2 = new Product("product2");
        p_adapter2.insert(p2);
        
        ProductAdapter p_adapter3 = new ProductAdapter(helper.getDb());
		Product p3 = new Product("product3");
        p_adapter3.insert(p3);
        
        ProductAdapter p_adapter4 = new ProductAdapter(helper.getDb());
		Product p4 = new Product("product4");
        p_adapter4.insert(p4);
        
        ProductAdapter pAll = new ProductAdapter(helper.getDb());
        
        products = pAll.getAll();
        
        ClientOrderAdapter o_adapter = new ClientOrderAdapter(helper.getDb());
        ClientOrder order = new ClientOrder("Hello cock sucker","acier",12, products);
        o_adapter.insert(order);
        
        */
		
		
		/*TamponAdapter t_adapter = new TamponAdapter(helper.getDb());
		Tampon tampon = new Tampon("tampon2", 5);
		long id = t_adapter.insert(tampon);
		tampon.setId(id);
		
		
		StationAdapter s_adapter = new StationAdapter(helper.getDb());
		Station station = new Station("station2");
		station.setTampon(tampon);
		s_adapter.insert(station);	
		*/
		
		/*
		TamponAdapter t_adapter1 = new TamponAdapter(helper.getDb());
		Tampon t1 = t_adapter1.get(3);
		t1.setName("tamponUpd");
		
		TamponAdapter t_adapter2 = new TamponAdapter(helper.getDb());
		t_adapter2.update(t1);
		
		StationAdapter s_adapter1 = new StationAdapter(helper.getDb());
		Station s = s_adapter1.get(2);
		s.setName("stationUpd");
		
		StationAdapter s_adapter2 = new StationAdapter(helper.getDb());
		s_adapter2.update(s);
		*/
		
		/*
		StationAdapter s_adapter1 = new StationAdapter(helper.getDb());
		Station s = s_adapter1.get(2);
		
		StationAdapter s_adapter2 = new StationAdapter(helper.getDb());
		s_adapter2.delete(s);
		
		TamponAdapter t_adapter1 = new TamponAdapter(helper.getDb());
		Tampon t1 = t_adapter1.get(3);
		TamponAdapter t_adapter2 = new TamponAdapter(helper.getDb());
		t_adapter2.delete(t1);
		*/
		
		// On récupere un bouton pour le test
		Button buttonTest = (Button) this.findViewById(R.id.scan_btn_validate);
		Button buttonTest1 = (Button) this.findViewById(R.id.Button01);
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
		
    }
    private void gestionUserButton()
    {
    	Intent intent = new Intent(this, ListUsersActivity.class);
		this.startActivity(intent);
    }
    
    public void gestionAddCommandeButton()
    {
    	Intent intent = new Intent(this, AddOrderActivity.class);
		this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}