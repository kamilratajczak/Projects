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

public class Edytuj extends Activity{
	
	private Spinner iloscPokoi;
	private Button zmien;
	private EditText miasto, ulica, numer, metraz, cena;
	private ImageView camera;
	private Geocoder geocoder;
	private Context context;
	private Bundle bundle;
	private HashMap postData;
	private String imageBase;
	private ArrayAdapter adapterIP;
	private boolean makePhoto = false;
	private Date date;
	private DateFormat format;
	private String dateToday;
	private PostResponseAsyncTask loginTask;
	private String[] iloscPokoiLista = {"1 pokój","2 pokoje", "3 pokoje","4 i wiêcej"};
	
	private String miastoStr(){
		return miasto.getText().toString();
	}
	private String ulicaStr(){
		return ulica.getText().toString();
	}
	private String numerStr(){
		return numer.getText().toString();
	}
	private String getID(){
		bundle = getIntent().getExtras();
		return bundle.getString("id");
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
		setContentView(R.layout.edytuj);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		context = getApplicationContext();
		
		geocoder = new Geocoder(this, Locale.getDefault());
		
		etsp();
		
		get_data();
		
		przycisk_zmien();
		
		camera();
		
}
	
	private void get_data() {

		postData = new HashMap();
		postData.put("mobile", "android");
		postData.put("txtID", getID());
			
		loginTask = new PostResponseAsyncTask(Edytuj.this, postData, new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
          
				ArrayList<Ogloszenia> ogloszenia = new JsonConverter<Ogloszenia>().toArrayList(result, Ogloszenia.class);
								
				for(Ogloszenia ogl : ogloszenia){
				
				miasto.setText(ogl.miasto);
				ulica.setText(ogl.ulica);
				iloscPokoi.setSelection(adapterIP.getPosition(ogl.ilosc_pokoi));
				numer.setText(ogl.numer);
				metraz.setText(ogl.metraz);
				cena.setText(ogl.cena);

				}
		}
		});
    
		loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/wybrane_ogloszenie.php");
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
	
	private void przycisk_zmien() {

		zmien = (Button)findViewById(R.id.Zmien);
		zmien.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				String adres = ulicaStr() + " " + numerStr() + ", " + miastoStr();
								
				if(getDoubleFromX(adres) == 0 && getDoubleFromX(adres) == 0)
					Toast.makeText(Edytuj.this, "B³êdny adres!", Toast.LENGTH_LONG).show();
				
				else{
					
				getDate();
						
				postData = new HashMap();
				postData.put("mobile", "android");
				postData.put("txtID", getID());
				postData.put("txtX", getAddressFromX(adres));
				postData.put("txtY", getAddressFromY(adres));
				postData.put("txtAddress", adres);
				postData.put("txtMiasto", miastoStr());
				postData.put("txtUlica", ulicaStr());
				postData.put("txtNumer", numerStr());
				postData.put("txtType", getType());
				postData.put("txtSize", metrazStr());
				postData.put("txtPrice", cenaStr());
				if(makePhoto == true)
				postData.put("txtPhoto", imageBase);
				postData.put("txtDate", dateToday);
					
				loginTask = new PostResponseAsyncTask(Edytuj.this, postData, new AsyncResponse() {
					
					@Override
					public void processFinish(String result) {
		          if(result.equals("success"))
		        	  Toast.makeText(Edytuj.this, "Og³oszenie zosta³o zmienione!", Toast.LENGTH_LONG).show(); 

						}
				});
						
				loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/edit.php");
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
		adapterIP = new ArrayAdapter(this, R.layout.spinner, iloscPokoiLista);
		adapterIP.setDropDownViewResource(R.layout.my_spinner);
		iloscPokoi.setAdapter(adapterIP);
		
		numer = (EditText)findViewById(R.id.Numer);
		
		cena = (EditText)findViewById(R.id.Cena);
		
		miasto = (EditText)findViewById(R.id.Miasta);
		
		ulica = (EditText)findViewById(R.id.Ulice);
		
		metraz = (EditText)findViewById(R.id.Metraz);
		
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == 1 && resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			Bitmap bitmap = (Bitmap) extras.get("data");
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			
			byte[] b = baos.toByteArray();
			
			imageBase = Base64.encodeToString(b, Base64.DEFAULT);
						
			makePhoto = true;
			
		}	
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