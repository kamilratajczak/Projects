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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements AsyncResponse{
	
	private EditText login, haslo;
	private TextView zarejestruj;
	private Button zaloguj;
	private Context context;
	private HashMap postData;
	private PostResponseAsyncTask loginTask;
	private Thread thread;
	
	public String getLogin(){
		return login.getText().toString();
	}
		
	public String getPassword(){
		return haslo.getText().toString();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		context = getApplicationContext();
		
		login = (EditText)findViewById(R.id.Login);
		haslo = (EditText)findViewById(R.id.Haslo);
		zarejestruj = (TextView)findViewById(R.id.Zarejestruj);
		zaloguj = (Button)findViewById(R.id.Zaloguj);
		
		zaloguj.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				
				postData = new HashMap();
                postData.put("mobile", "android");
                postData.put("txtUsername", getLogin());
                postData.put("txtPassword", getPassword());
                       
                loginTask = new PostResponseAsyncTask(MainActivity.this, postData);
                
                loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/login.php");
                	
			}
		});
		
		zarejestruj.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				
				Intent intent = new Intent(context, RejestracjaActivity.class);
			 					
				startActivity(intent);
			}
		});
	}
		
	 public void processFinish(String result) {
		 if(result.equals("success")){	            

					Toast.makeText(this, "Logowanie zakoñczone powodzeniem!", Toast.LENGTH_LONG).show();             

					final Intent intentStart = new Intent(context, StartActivity.class);        

					thread = new Thread(){
						
					@Override
			public void run() {
                try {
                   Thread.sleep(2500);	
				   intentStart.putExtra("login", getLogin());
                   startActivity(intentStart);   
               } 
			   catch (Exception e) {
                   e.printStackTrace();
               }
           } 
        };

					thread.start();
    }      
				
				
		 else
			 Toast.makeText(this, "B³êdne dane!", Toast.LENGTH_LONG).show(); 
	 }
}