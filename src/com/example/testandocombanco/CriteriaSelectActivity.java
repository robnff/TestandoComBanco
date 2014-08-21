package com.example.testandocombanco;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CriteriaSelectActivity extends Activity {
	Button but1, but2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_criteria_select);
		final int cont;
		Bundle extras = getIntent().getExtras();
			cont = extras.getInt("contador");

		but1 = (Button) findViewById(R.id.buttonClarity);
		but2 = (Button) findViewById(R.id.buttonMethod);

		but1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				Intent intent;
				intent = new Intent(CriteriaSelectActivity.this, ChartActivity.class);
				intent.putExtra("criteria", "Clarity");
				intent.putExtra("contador", cont);
				startActivity(intent);
				finish();
			}});
		
		but2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				Intent intent;
				intent = new Intent(CriteriaSelectActivity.this, ChartActivity.class);
				intent.putExtra("criteria", "Method");
				intent.putExtra("contador", cont);
				startActivity(intent);
				finish();
			}});
		
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {

			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.criteria_select, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle action bar item clicks here. The action bar will
			// automatically handle clicks on the Home/Up button, so long
			// as you specify a parent activity in AndroidManifest.xml.
			int id = item.getItemId();
			if (id == R.id.action_settings) {
				return true;
			}
			return super.onOptionsItemSelected(item);
		}

	}
