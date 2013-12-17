package com.imie.trybaproject.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.ClientOrderAdapter;
import com.imie.trybaproject.db.ProductAdapter;
<<<<<<< HEAD
import com.imie.trybaproject.model.ClientOrder;
import com.imie.trybaproject.model.Product;
=======
import com.imie.trybaproject.db.StationAdapter;
import com.imie.trybaproject.db.TamponAdapter;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.model.ClientOrder;
import com.imie.trybaproject.model.Product;
import com.imie.trybaproject.model.Station;
import com.imie.trybaproject.model.Tampon;
import com.imie.trybaproject.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
>>>>>>> 7465eca94913ee7a2e34471a17240302e843d611

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
											this, "tryba_database", null, 1
											);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
