package com.imie.trybaproject.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.imie.trybaproject.R;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.ClientOrderAdapter;
import com.imie.trybaproject.db.ProductAdapter;
import com.imie.trybaproject.model.ClientOrder;
import com.imie.trybaproject.model.Product;

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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
