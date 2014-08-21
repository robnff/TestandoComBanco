package com.example.testandocombanco;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChartActivity extends Activity {
	MySQLiteHelper db = new MySQLiteHelper(this);
	double d1, d2, d3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		int total=1;
		String crit = null;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);

		//getting all the answers
		ArrayList<Pergunta> todas = db.getAllPerg();
		//tirando as perguntas da jogada e deixando so as respostas
		int CONTADOR_PERGUNTA = 0;
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			total = extras.getInt("contador");
			crit = extras.getString("criteria");
		}
		//lendo do banco os resultados da votacao
		int col1=0, col2=0, col3=0;
		System.out.println("so pra ver oq ele ta lendo");
		for (Iterator<Pergunta> iterator = todas.iterator(); iterator.hasNext();) {
			Pergunta pergunta = (Pergunta) iterator.next();
			if(CONTADOR_PERGUNTA<3){
				CONTADOR_PERGUNTA++;
			}else{
				System.out.println(pergunta.getId());
				System.out.println(pergunta.getQuestion());
				System.out.println(pergunta.getResposta());
				if(pergunta.getResposta()==1){
					col1++;
				}else if(pergunta.getResposta()==2){
					col2++;
				}else if(pergunta.getResposta()==3){
					col3++;
				}

			}
		}

		System.out.println(total);
		
		System.out.println("col1 = "+col1);
		System.out.println("col2 = "+col2);
		System.out.println("col3="+col3 );
		 
		//passando pra forma como eles serao representados
		DecimalFormat df = new DecimalFormat("#.##");
		d1=Double.parseDouble(df.format(col1*100/total));
		d2=Double.parseDouble(df.format(col2*100/total));
		d3=Double.parseDouble(df.format(col3*100/total));
		System.out.println("agora to vendo se ta formatando direitinho");
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
		GraphViewData[] data = new GraphViewData[]{
				new GraphViewData(0,d1),
				new GraphViewData(1,d2),
				new GraphViewData(2,d3),	
		};

		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

		if(crit.equalsIgnoreCase("Clarity")){
			GraphViewSeries input = new GraphViewSeries(data);
			//handling the graph
			GraphView exemplo = new BarGraphView(this,"answers to question 1 from clarity");		
			//exemplo.setVerticalLabels(new String[]{"100%", "66%", "33%","0%"});	
			
			exemplo.setHorizontalLabels(new String[]{"happy","+-", "sad"});
			exemplo.addSeries(input);

			layout.addView(exemplo);
		} else{
			final TextView errorMessage = new TextView(this);
			errorMessage.setText("No student evaluated this criteria");
			layout.addView(errorMessage);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chart, menu);
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
