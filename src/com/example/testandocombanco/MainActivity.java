package com.example.testandocombanco;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	int cont;
	Button button1, button2, button3;
	//adicionando o banco
	MySQLiteHelper db = new MySQLiteHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();

		if (extras != null){
			cont = extras.getInt("contador");
		}else{
			//eh a 1a vez q eu to rodando o programa
			cont = 0;
			//limpando o banco pra testar
			SQLiteDatabase cleanNeed = db.getWritableDatabase();
			cleanNeed.execSQL("DROP TABLE IF EXISTS pergunta");
			db.onCreate(cleanNeed);

			//adicionando coisas pra testar
			db.addPerg(new Pergunta("Clarity - Question 1", "Clarity", 0));
			db.addPerg(new Pergunta("Clarity - Question 2", "Clarity", 0));
			db.addPerg(new Pergunta("Clarity - Question 3", "Clarity", 0));

		}

		
		final ArrayList<Pergunta> todas = db.getAllPerg();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//setando coisas com base na pergunta atual
		final String pergunta = todas.get(cont).getQuestion();
		TextView shownQ = (TextView)findViewById(R.id.pergunta);
		shownQ.setText(pergunta);

		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);

		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				cont++;
				Intent intent;
				//System.out.println(cont);
				//System.out.println("cliquei no butao 1");
				if(cont==3){
					intent = new Intent(MainActivity.this, CriteriaSelectActivity.class);
					db.addPerg(new Pergunta(pergunta, "Clarity", 1));
				}
				else{
					intent = new Intent(MainActivity.this, MainActivity.class);
					db.addPerg(new Pergunta(pergunta, "Clarity", 1));
					System.out.println("adicionasse a pergunta "+cont);
				}
				intent.putExtra("contador", cont);
				Toast toast = Toast.makeText(getApplicationContext(),"you voted :)", Toast.LENGTH_LONG);
				toast.show();
				startActivity(intent);
				finish();
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				cont++;
				Intent intent;
				//System.out.println(cont);
				//System.out.println("cliquei no butao 2");
				if(cont==3){
					intent = new Intent(MainActivity.this, CriteriaSelectActivity.class);
					db.addPerg(new Pergunta(pergunta, "Clarity", 2));
				}
				else{
					intent = new Intent(MainActivity.this, MainActivity.class);
					db.addPerg(new Pergunta(pergunta, "Clarity", 2));
				}
				intent.putExtra("contador", cont);
				Toast toast = Toast.makeText(getApplicationContext(),"you voted :|", Toast.LENGTH_LONG);
				toast.show();
				startActivity(intent);
				finish();
			}
		});

		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				cont++;
				Intent intent;
				//System.out.println(cont);
				//System.out.println("cliquei no butao 3");
				if(cont==3){
					intent = new Intent(MainActivity.this, CriteriaSelectActivity.class);
					db.addPerg(new Pergunta(pergunta, "Clarity", 3));
				}
				else{
					intent = new Intent(MainActivity.this, MainActivity.class);
					db.addPerg(new Pergunta(pergunta, "Clarity", 3));
				}
				intent.putExtra("contador", cont);
				Toast toast = Toast.makeText(getApplicationContext(), "you voted :(", Toast.LENGTH_LONG);
				toast.show();
				startActivity(intent);
				finish();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
