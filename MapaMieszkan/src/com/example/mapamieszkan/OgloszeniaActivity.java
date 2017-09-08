package com.example.mapamieszkan;

import java.util.ArrayList;
import java.util.HashMap;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OgloszeniaActivity extends Activity{

	private Context context;
	private TextView tvLogin, tvPhone, tvAdress, tvIloscPokoi, tvPrice, tvMetraz, tvDate;
	private Bundle bundle;
	private HashMap postData;
	private String telefon = "";
	private PostResponseAsyncTask loginTask;
	private ImageView ivPhoto;
	
	public String getID(){
		bundle = getIntent().getExtras();
		return bundle.getString("id");
	}
	
	public String getLogin(){
		bundle = getIntent().getExtras();
		return bundle.getString("login");
	}
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ogloszenia2);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		context = getApplicationContext();
		
		tviv();
		
		getData();			
		
	}	

	private void getData() {
		
		ogloszenia();
		dane();

	}

	private void dane() {

		postData = new HashMap();
		postData.put("mobile", "android");
		postData.put("txtLogin", getLogin());
		
		loginTask = new PostResponseAsyncTask(OgloszeniaActivity.this, postData, new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
         
				ArrayList<Dane> dane = new JsonConverter<Dane>().toArrayList(result, Dane.class);
								
				for(Dane d : dane){

					String dodajacy = d.nazwisko + " " +d.imie;
					
					for(int i=1; i<=d.numer_telefonu.length(); i++){
						telefon += d.numer_telefonu.charAt(i-1);
						if(i!=d.numer_telefonu.length()){
						if(i%3==0)
							telefon += "-";
						}
					}
					
					tvLogin.setText(dodajacy);
					tvPhone.setText(telefon);
				}
		}
		});
   
		loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/dane.php");

	}
		
	private void ogloszenia() {

		postData = new HashMap();
		postData.put("mobile", "android");
		postData.put("txtID", getID());
				
		loginTask = new PostResponseAsyncTask(OgloszeniaActivity.this, postData, new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
								
				ArrayList<Ogloszenia> ogloszenia = new JsonConverter<Ogloszenia>().toArrayList(result, Ogloszenia.class);
				
				for(Ogloszenia o : ogloszenia){
									
					tvAdress.setText(o.adres);
					tvIloscPokoi.setText(o.ilosc_pokoi);
					tvPrice.setText(o.cena + " z³");
					tvMetraz.setText(o.metraz + " m2");
					tvDate.setText(o.data);

							Picasso.with(context)
								   .load(o.url)
								   .placeholder(R.drawable.ic_action_camera)
								   .error(android.R.drawable.stat_sys_download)
								   .into(ivPhoto);
							
				}		
		}
		});
    
		loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/wybrane_ogloszenie.php");
		
	}
	private void tviv() {

		tvLogin = (TextView)findViewById(R.id.tvLogin);
		tvAdress = (TextView)findViewById(R.id.tvAdress);
		tvIloscPokoi = (TextView)findViewById(R.id.tvIloscPokoi);
		tvPrice = (TextView)findViewById(R.id.tvPrice);
		tvDate = (TextView)findViewById(R.id.tvDate);
		tvMetraz = (TextView)findViewById(R.id.tvMetraz);
		tvPhone = (TextView)findViewById(R.id.tvPhone);
		ivPhoto = (ImageView)findViewById(R.id.ivPhoto);
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