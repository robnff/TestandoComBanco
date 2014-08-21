package com.example.testandocombanco;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{
	private static int DATABASE_VERSION=1;
	private static String DATABASE_NAME="PerguntaDB";
	private static String TABLE_PERGUNTA="pergunta";
	private static String KEY_ID="id";
	private static String KEY_CRITERIA="criteria";
	private static String KEY_QUESTION="question";
	private static String KEY_RESPOSTA = "resposta";
	private static String[] COLUMNS = {KEY_ID,KEY_QUESTION,KEY_CRITERIA, KEY_RESPOSTA};

	public MySQLiteHelper(Context context){
		super(context, DATABASE_NAME,null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_QUESTION_TABLE = "CREATE TABLE pergunta ( "+ 
				"id INTEGER PRIMARY KEY AUTOINCREMENT, "+
				"question TEXT, "+
				"criteria TEXT, "+
				"resposta INTEGER)";
		db.execSQL(CREATE_QUESTION_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
		db.execSQL("DROP TABLE IF EXISTS pergunta");
		this.onCreate(db);
	}

	public void addPerg(Pergunta pergunta){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_QUESTION, pergunta.getQuestion());
		values.put(KEY_CRITERIA, pergunta.getCriteria());
		values.put(KEY_RESPOSTA, pergunta.getResposta());

		db.insert(TABLE_PERGUNTA, null, values);
		db.close();
	}

	public Pergunta getPerg(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT searched FROM "+ TABLE_PERGUNTA+" WHERE id = ?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
		if (cursor != null){
			cursor.moveToFirst();
		}
		Pergunta perg = new Pergunta(cursor.getString(1),//question
				cursor.getString(2),//criteria
				Integer.parseInt(cursor.getString(3)));//resposta
		perg.setId(Integer.parseInt(cursor.getString(0)));
		return perg;
	}
	
	public ArrayList<Pergunta> getAllPerg(){
		ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
		String query = "SELECT * FROM "+ TABLE_PERGUNTA;
		SQLiteDatabase db = this.getReadableDatabase();
		//fazendo a query
		Cursor cursor = db.rawQuery(query, null);
		//brincando com o cursor
		if(cursor.moveToFirst()){
			do{
				Pergunta novo = new Pergunta(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
				novo.setId(Integer.parseInt(cursor.getString(0)));
				perguntas.add(novo);
			}while(cursor.moveToNext());
		}return perguntas;
		
	}

}
