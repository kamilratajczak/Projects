package com.example.mapamieszkan;

import java.util.HashMap;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RejestracjaActivity extends Activity implements AsyncResponse{
	
	
	private EditText rejLogin, rejHaslo, rejHaslo2;
	private Button wyczysc, rejestruj;
	private Context context;
	private ImageView cofnij;
	private Thread thread;
	private HashMap postData;
	private PostResponseAsyncTask loginTask;
	
	public String getLogin(){
		return rejLogin.getText().toString();
	}
	
	public String getPassword(){
		return rejHaslo.getText().toString();
	}
	
	public String getPassword2(){
		return rejHaslo2.getText().toString();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rejestracja_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		context = getApplicationContext();

		rejLogin = (EditText)findViewById(R.id.rejLogin);
		rejHaslo = (EditText)findViewById(R.id.rejHaslo);
		rejHaslo2 = (EditText)findViewById(R.id.rejHaslo2);
		
		wyczysc();
		
		rejestruj();
	
	}
			private void rejestruj() {
				rejestruj = (Button)findViewById(R.id.Rejestruj);
				
				rejestruj.setOnClickListener(new OnClickListener(){
									
					public void onClick(View v){
						
						if(rejHaslo.length()>3){
							if(getPassword().equals(getPassword2())){
							
							postData = new HashMap();
							postData.put("mobile", "android");
							postData.put("txtUsername", getLogin());
							postData.put("txtPassword", getPassword());
								
							loginTask = new PostResponseAsyncTask(RejestracjaActivity.this, postData);
		                
							loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/register.php");
							}
							else{
							Toast.makeText(context, "Podane has³a s¹ ró¿ne!", Toast.LENGTH_LONG).show();
							}
						}
							else{
							Toast.makeText(context, "Has³o musi zawieraæ wiêcej ni¿ 3 znaki!", Toast.LENGTH_LONG).show();
						}
					}
				});
	}

			private void wyczysc() {
				
				wyczysc = (Button)findViewById(R.id.Wyczysc);
				
				wyczysc.setOnClickListener(new OnClickListener(){
					
					public void onClick(View v){
						
						rejLogin.setText("");
						rejHaslo.setText("");
						rejHaslo2.setText("");
						
						Toast.makeText(context, "Formularz wyczyszczony", Toast.LENGTH_LONG).show();
					}
				});

	}

			public void processFinish(String result) {
				if(result.equals("success")){	            

					Toast.makeText(this, "Rejestracja zakoñczona sukcesem!", Toast.LENGTH_LONG).show();             

					final Intent intent = new Intent(context, MainActivity.class);        

					thread = new Thread(){
						
					@Override
			public void run() {
                try {
                   Thread.sleep(2500);
                   startActivity(intent);   
               } 
			   catch (Exception e) {
                   e.printStackTrace();
               }
           } 
        };

					thread.start();
    }               
			
				else
					Toast.makeText(this, "Podany Login jest zajêty!", Toast.LENGTH_LONG).show(); 
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