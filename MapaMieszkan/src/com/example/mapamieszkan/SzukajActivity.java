package com.example.mapamieszkan;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SzukajActivity extends Activity {
	
	private Spinner zasieg, iloscPokoi, metraz, cena;
	private EditText miasta, ulice;
	private Button szukaj;
	private Context context;
	private HashMap postData;
	private Geocoder geocoder;
	private PostResponseAsyncTask loginTask;
	private String[] zasiegWyszukiwania = {"do 5km","do 10km","do 20km"};
	private String[] iloscPokoiLista = {"1 pokój","2 pokoje", "3 pokoje","4 i wiêcej"};
	private String[] metrazLista = {"poni¿ej 20m","20-30m","30-50m","50-80m","80m i wiêcej"};
	private String[] cenaLista = {"poni¿ej 500z³","500-800z³","800-1200z³","1200-1600z³","1600-2000z³","2000z³ i wiêcej"};
	
	private String miastaStr(){
		return miasta.getText().toString();
	}
	private String uliceStr(){
		return ulice.getText().toString();
	}
	private String getZasieg(){
		return zasieg.getSelectedItem().toString();
	}
	private String getType(){
		return iloscPokoi.getSelectedItem().toString();
	}
	private String getSize(){
		return metraz.getSelectedItem().toString();
	}
	private String getPrice(){
		return cena.getSelectedItem().toString();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.szukaj_main);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		context = getApplicationContext();
		
		geocoder = new Geocoder(this, Locale.getDefault());
		
		miasta = (EditText)findViewById(R.id.Miasta);
		
		ulice = (EditText)findViewById(R.id.Ulice);
		
		spinnery();
		
		szukaj();
		
	}
	
	private void szukaj() {

		szukaj = (Button)findViewById(R.id.Szukaj);
		
		szukaj.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
		
		String adres = uliceStr() + miastaStr();
		
		postData = new HashMap();
		postData.put("mobile", "android");
		postData.put("txtX", getAddressFromX(adres));
		postData.put("txtY", getAddressFromY(adres));
		postData.put("txtZasieg", getZasieg());
		postData.put("txtIloscPokoi", getType());
		postData.put("txtMetraz", getSize());
		postData.put("txtCena", getPrice());
			
		loginTask = new PostResponseAsyncTask(SzukajActivity.this, postData, new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
				if(result.equals("failed"))
		        	  Toast.makeText(SzukajActivity.this, "Brak og³oszeñ dla podanych parametrów!", Toast.LENGTH_LONG).show();
				else{

				Intent intent = new Intent();
								
				intent.putExtra("result", result);
				
				setResult(Activity.RESULT_OK, intent);
				
				finish();
				
				}
				
				}
		});
		loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/select.php");
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

	private void spinnery() {

		zasieg = (Spinner)findViewById(R.id.Zasieg);
		ArrayAdapter adapterZW = new ArrayAdapter(this, R.layout.spinner, zasiegWyszukiwania);
		adapterZW.setDropDownViewResource(R.layout.my_spinner);
		zasieg.setAdapter(adapterZW);
		
		iloscPokoi = (Spinner)findViewById(R.id.IloscPokoi);
		ArrayAdapter adapterIP = new ArrayAdapter(this, R.layout.spinner, iloscPokoiLista);
		adapterIP.setDropDownViewResource(R.layout.my_spinner);
		iloscPokoi.setAdapter(adapterIP);
		
		metraz = (Spinner)findViewById(R.id.Metraz);
		ArrayAdapter adapterMe = new ArrayAdapter(this, R.layout.spinner, metrazLista);
		adapterMe.setDropDownViewResource(R.layout.my_spinner);
		metraz.setAdapter(adapterMe);
		
		cena = (Spinner)findViewById(R.id.Cena);
		ArrayAdapter adapterCe = new ArrayAdapter(this, R.layout.spinner, cenaLista);
		adapterCe.setDropDownViewResource(R.layout.my_spinner);
		cena.setAdapter(adapterCe);
		
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