package com.imie.trybaproject.activity;

import com.imie.trybaproject.R;
import com.imie.trybaproject.R.layout;
import com.imie.trybaproject.R.menu;
import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.UserAdapter;
import com.imie.trybaproject.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*
         * ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(this, "tryba_database", null, 1);
         * UserAdapter u_adapter = new UserAdapter(helper.getDb());
        */
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
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
