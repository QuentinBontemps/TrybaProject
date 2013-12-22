package com.imie.trybaproject.views.activity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentIntegratorSupportV4;
import com.google.zxing.integration.android.IntentResult;
import com.imie.trybaproject.R;
import com.imie.trybaproject.R.layout;
import com.imie.trybaproject.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScanActivity extends Activity  implements View.OnClickListener{

	Button btnScan;
	TextView txtResult;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		btnScan = (Button) findViewById(R.id.btnScan);
		btnScan.setOnClickListener(this);
		txtResult = (TextView) findViewById(R.id.txtResult);
		this.setResult(RESULT_CANCELED);
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scan, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnScan){
			IntentIntegrator intent = new IntentIntegrator(this);
			intent.initiateScan();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if(result != null){
			txtResult.setText(result.getContents());
			SharedPreferences preferences = 
					this.getSharedPreferences("DEFAULT", 
							Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			if (result.getContents() != null)
			{
				Integer idScan = Integer.parseInt(result.getContents());
				editor.putInt("ID_SCAN_VALUE", idScan);
				editor.commit();
				this.setResult(RESULT_OK);
			}else
			{
				this.setResult(RESULT_CANCELED);
			}
			
			
			this.finish();
		}else{
			Toast.makeText(this, "Dommage ca marche pas", Toast.LENGTH_SHORT).show();
		}
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
	    case android.R.id.home:
	        this.finish();
	        return true;
	        
		}
	    return super.onOptionsItemSelected(item);
	}

}
