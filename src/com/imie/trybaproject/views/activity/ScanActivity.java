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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scan, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnScan){
			IntentIntegrator intent = new IntentIntegrator(this);
			intent.initiateScan();
			/*intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "ONE_D_MODE");
			startActivityForResult(intent, 0);*/
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
			Integer idScan = Integer.parseInt(result.getContents());
			editor.putInt("ID_SCAN_VALUE", idScan);
			editor.commit();
			
			this.setResult(RESULT_OK);
			this.finish();
		}else{
			Toast.makeText(this, "Dommage ca marche pas", Toast.LENGTH_SHORT).show();
		}
		
		
		/*if (requestCode == 0) {
		      if (resultCode == RESULT_OK) {
		         String contents = intent.getStringExtra("SCAN_RESULT");
		         String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
		         // Handle successful scan
		         Toast.makeText(this, "Good guy", Toast.LENGTH_SHORT).show();
		      } else if (resultCode == RESULT_CANCELED) {
		         // Handle cancel
		    	  Toast.makeText(this, "Dommage ca marche pas", Toast.LENGTH_SHORT).show();
		      }
		   }*/
	}
	
	

}
