package com.example.mapamieszkan;

import java.util.ArrayList;
import java.util.HashMap;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MojeKontoActivity extends Activity{

	private Context context;
	private TextView witamy;
	private Bundle bundle;
	private Button zapisz;
	private HashMap postData;
	private EditText imie, nazwisko, nrtel, email;
	private PostResponseAsyncTask loginTask;
	private Thread thread;
	
	public String getLogin(){
		bundle = getIntent().getExtras();
		return bundle.getString("login");
	}
	
	public String getImie(){
		return imie.getText().toString();
	}
	
	public String getNazwisko(){
		return nazwisko.getText().toString();
		
	}
	public String getNrTel(){
		return nrtel.getText().toString();
	}
	
	public String getEmail(){
		return email.getText().toString();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moje_konto_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		context = getApplicationContext();
				
		witamy = (TextView)findViewById(R.id.Witamy);
		
		witamy.setText("Witaj " +getLogin()+ " !");
		
		imie = (EditText)findViewById(R.id.Imie);
		
		nazwisko = (EditText)findViewById(R.id.Nazwisko);
		
		nrtel = (EditText)findViewById(R.id.NrTel);
		
		email = (EditText)findViewById(R.id.Email);
		
		zaladuj_dane();
		
		przycisk_zapisz();
		
	} 
			
	private void zaladuj_dane() {
		
		postData = new HashMap();
		postData.put("mobile", "android");
		postData.put("txtLogin", getLogin());
			
		loginTask = new PostResponseAsyncTask(MojeKontoActivity.this, postData, new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
          
				ArrayList<Dane> dane = new JsonConverter<Dane>().toArrayList(result, Dane.class);
								
				for(Dane d : dane){
					imie.setText(d.imie);
					nazwisko.setText(d.nazwisko);
					nrtel.setText(d.numer_telefonu);
					email.setText(d.email);
				}
		}
		});
    
		loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/dane.php");
}

	private void przycisk_zapisz() {

		zapisz = (Button)findViewById(R.id.Zapisz);

		zapisz.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){

					postData = new HashMap();
					postData.put("mobile", "android");
					postData.put("txtLogin", getLogin());
					postData.put("txtImie", getImie());
					postData.put("txtNazwisko", getNazwisko());
					postData.put("txtTel", getNrTel());
					postData.put("txtEmail", getEmail());
						
					loginTask = new PostResponseAsyncTask(MojeKontoActivity.this, postData, new AsyncResponse() {
						
						@Override
						public void processFinish(String result) {
				if(result.equals("success")){	            
					Toast.makeText(MojeKontoActivity.this, "Dane zosta³y zapisane!", Toast.LENGTH_LONG).show();
					
					}            
					}
					});
                
					loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/my_account.php");
		}
	});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			
			finish();
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}