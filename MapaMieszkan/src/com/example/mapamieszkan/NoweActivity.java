package com.example.mapamieszkan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class NoweActivity extends Activity{
	
	private Spinner iloscPokoi;
	private Button dodaj;
	private EditText miasta, ulice, numer, metraz, cena;
	private ImageView camera;
	private Geocoder geocoder;
	private Context context;
	private Bundle bundle;
	private HashMap postData;
	private String imageBase;
	private boolean makePhoto = false;
	private boolean daneKonta = false;
	private Date date;
	private DateFormat format;
	private String dateToday;
	private PostResponseAsyncTask loginTask;
	private String[] iloscPokoiLista = {"1 pokój","2 pokoje", "3 pokoje","4 i wiêcej"};
	
	private String miastaStr(){
		return miasta.getText().toString();
	}
	private String uliceStr(){
		return ulice.getText().toString();
	}
	private String numerStr(){
		return numer.getText().toString();
	}
	private String getLogin(){
		bundle = getIntent().getExtras();
		return bundle.getString("login");
	}
	private String getType(){
		return iloscPokoi.getSelectedItem().toString();
	}
	private String metrazStr(){
		return metraz.getText().toString();
	}
	private String cenaStr(){
		return cena.getText().toString();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nowe_main2);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		context = getApplicationContext();
		
		geocoder = new Geocoder(this, Locale.getDefault());
		
		etsp();
		
		check_data();
		
		przycisk_dodaj();
		
		camera();
		
}
	private void check_data() {

		postData = new HashMap();
		postData.put("mobile", "android");
		postData.put("txtLogin", getLogin());
			
		loginTask = new PostResponseAsyncTask(NoweActivity.this, postData, new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
          
				ArrayList<Dane> dane = new JsonConverter<Dane>().toArrayList(result, Dane.class);
								
				for(Dane d : dane){
					if(d.imie.equals("") || d.nazwisko.equals("") || d.numer_telefonu.equals("") || d.email.equals(""));
					
					else
					daneKonta = true;
			}
		}
		});
    
		loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/dane.php");
	}
	private void camera() {
		
		camera = (ImageView)findViewById(R.id.Camera);

		camera.setOnClickListener(new OnClickListener(){
	
	public void onClick(View v){
		
		if(makePhoto == false){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 1);
		}
		
		else
			Toast.makeText(context, "Zdjêcie zosta³o ju¿ zrobione!", Toast.LENGTH_LONG).show();
		}
	});
	}
	
	private void przycisk_dodaj() {

		dodaj = (Button)findViewById(R.id.Dodaj);
		dodaj.setOnClickListener(new OnClickListener(){
			public void onClick(View v){

				if(daneKonta==false)
					Toast.makeText(NoweActivity.this, "Uzupe³nij dane konta!", Toast.LENGTH_LONG).show();
				
					else if(miastaStr().equals(""))
						Toast.makeText(NoweActivity.this, "Uzupe³nij miasto!", Toast.LENGTH_LONG).show();
	
					else if(uliceStr().equals(""))
						Toast.makeText(NoweActivity.this, "Uzupe³nij ulicê!", Toast.LENGTH_LONG).show();
				
					else if(numerStr().equals(""))
						Toast.makeText(NoweActivity.this, "Uzupe³nij numer!", Toast.LENGTH_LONG).show();
				
					else if(metrazStr().equals(""))
						Toast.makeText(NoweActivity.this, "Uzupe³nij numer!", Toast.LENGTH_LONG).show();
	
					else if(cenaStr().equals(""))
						Toast.makeText(NoweActivity.this, "Uzupe³nij cenê!", Toast.LENGTH_LONG).show();
				
					else if(makePhoto==false)
						Toast.makeText(NoweActivity.this, "Zrób zdjêcie!", Toast.LENGTH_LONG).show();
			
							else 
								dane_do_bazy();
					
			}
			
			private void dane_do_bazy() {
				
				String adres = uliceStr() + " " + numerStr() + ", " + miastaStr();
				
				if(getDoubleFromX(adres) == 0 && getDoubleFromX(adres) == 0)
					Toast.makeText(NoweActivity.this, "B³êdny adres!", Toast.LENGTH_LONG).show();
				
				else{
					
				getDate();
						
				postData = new HashMap();
				postData.put("mobile", "android");
				postData.put("txtLogin", getLogin());
				postData.put("txtX", getAddressFromX(adres));
				postData.put("txtY", getAddressFromY(adres));
				postData.put("txtAddress", adres);
				postData.put("txtMiasto", miastaStr());
				postData.put("txtUlica", uliceStr());
				postData.put("txtNumer", numerStr());
				postData.put("txtType", getType());
				postData.put("txtSize", metrazStr());
				postData.put("txtPrice", cenaStr());
				postData.put("txtPhoto", imageBase);
				postData.put("txtDate", dateToday);
					
				loginTask = new PostResponseAsyncTask(NoweActivity.this, postData, new AsyncResponse() {
					
					@Override
					public void processFinish(String result) {
		          if(result.equals("success"))
		        	  Toast.makeText(NoweActivity.this, "Og³oszenie zosta³o dodane!", Toast.LENGTH_LONG).show();
		          else
		        	  Toast.makeText(NoweActivity.this, "Przekroczy³eœ liczbê og³oszeñ!", Toast.LENGTH_LONG).show();  
						}
				});
						
				loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/new.php");
				}
			}
			
			private void getDate() {
				
				date = Calendar.getInstance().getTime();
				
				format = new SimpleDateFormat("yyyy-MM-dd");
				
				dateToday = format.format(date);		
				
			}
			private Double getDoubleFromX(String location) {
				Double resultX = 0.0;
			    try {
			        List<Address> addresses = geocoder.getFromLocationName(location, 1);
			        for (Address address : addresses) {
			            resultX = address.getLatitude();
			            }
			    } catch (IOException e) {
			    }
			    return resultX;
			}
			
			private Double getDoubleFromY(String location) {
				Double resultX = 0.0;
			    try {
			        List<Address> addresses = geocoder.getFromLocationName(location, 1);
			        for (Address address : addresses) {
			            resultX = address.getLatitude();
			            }
			    } catch (IOException e) {
			    }
			    return resultX;
			}
			
			private String getAddressFromX(String location) {
				String resultX = "";
			    try {
			        List<Address> addresses = geocoder.getFromLocationName(location, 1);
			        for (Address address : addresses) {
			            resultX = Double.toString(address.getLatitude());
			            }
			    } catch (IOException e) {
			    }
			    return resultX;
			}
			private String getAddressFromY(String location) {
				String resultY = "";
			    try {
			        List<Address> addresses = geocoder.getFromLocationName(location, 1);
			        for (Address address : addresses) {
			            resultY = Double.toString(address.getLongitude());
			            }
			    } catch (IOException e) {
			    }
			    return resultY;
			}
		});
	}
	private void etsp() {
		
		iloscPokoi = (Spinner)findViewById(R.id.IloscPokoi);
		ArrayAdapter adapterIP = new ArrayAdapter(this, R.layout.spinner, iloscPokoiLista);
		adapterIP.setDropDownViewResource(R.layout.my_spinner);
		iloscPokoi.setAdapter(adapterIP);
		
		numer = (EditText)findViewById(R.id.Numer);
		
		cena = (EditText)findViewById(R.id.Cena);
		
		miasta = (EditText)findViewById(R.id.Miasta);
		
		ulice = (EditText)findViewById(R.id.Ulice);
		
		metraz = (EditText)findViewById(R.id.Metraz);
		
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == 1 && resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			Bitmap bitmap = (Bitmap) extras.get("data");
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			
			byte[] b = baos.toByteArray();
			
			imageBase = Base64.encodeToString(b, Base64.DEFAULT);
						
			makePhoto = true;
			
		}	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			
			Intent intent = new Intent();
			
			setResult(3, intent);
			
			finish();
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}