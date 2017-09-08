package com.example.mapamieszkan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends FragmentActivity implements OnMarkerClickListener{
	
	private TextView witamy;
	private Context context;
	private ImageView znajdz;
	private Bundle bundle;
	private GoogleMap Map;
	private String resultAll;
	private PostResponseAsyncTask loginTask;
	private HashMap postData;
	private List<Marker> markerList = new ArrayList<Marker>();	
	private List<Marker> markerList1 = new ArrayList<Marker>();
	
	public String getLogin(){
		bundle = getIntent().getExtras();
		return bundle.getString("login");
	}
	
	public String getResult(){
		bundle = getIntent().getExtras();     
		return bundle.getString("result");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_main);
		getActionBar().setDisplayShowHomeEnabled(false);

		context = getApplicationContext();
		
		znajdz();
	
		map();
		
		all_advertisement();
	}

	private void all_advertisement() {
		
		postData = new HashMap();
		postData.put("mobile", "android");
		
		loginTask = new PostResponseAsyncTask(StartActivity.this, postData, new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
				
				resultAll = result;
				
				Map.clear();
				markerList.clear();
				
				try {
					 JSONArray jArray = new JSONArray(result);
		 
					 for(int i = 0; i<jArray.length(); i++){

					 Marker marker = Map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(jArray.getJSONObject(i).getString("x")), Double.parseDouble(jArray.getJSONObject(i).getString("y")))).title(jArray.getJSONObject(i).getString("id_ogloszenia")));
					 markerList.add(marker);
					 }
					 
					 LatLngBounds.Builder builder = new LatLngBounds.Builder();
		             for (Marker m : markerList) {
		                 builder.include(m.getPosition());
		             }
		             
		             LatLngBounds bounds = builder.build();
		             
		             Map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
					 
				     } catch (JSONException e) {		
				    	 e.printStackTrace();
					    }	
				
		        
		}
		});
    
		loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/all.php");
	}
	private void map() {
		
		Map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.Map)).getMap();
		
		Map.setMyLocationEnabled(false);
	    Map.getUiSettings().setScrollGesturesEnabled(true);
	    Map.getUiSettings().setTiltGesturesEnabled(true);
	    Map.getUiSettings().setRotateGesturesEnabled(true);
	    Map.setOnMarkerClickListener(this);
	    
		if (Map == null) {
			Toast.makeText(context, "Nie mo¿na za³adowaæ mapy", Toast.LENGTH_SHORT).show();
		}
	}

	private void znajdz() {
		
		znajdz = (ImageView)findViewById(R.id.Znajdz);
		
		znajdz.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				
				Intent intent = new Intent(context, SzukajActivity.class);
				
				startActivityForResult(intent, 5);				
				}
			});
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 5){
				if(resultCode == RESULT_OK){
			Map.clear();
			markerList1.clear();
			
			try {
						 JSONArray jArray = new JSONArray(data.getStringExtra("result"));
						 
						 for(int i = 0; i<jArray.length(); i++){

						 Marker marker = Map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(jArray.getJSONObject(i).getString("x")), Double.parseDouble(jArray.getJSONObject(i).getString("y")))).title(jArray.getJSONObject(i).getString("id_ogloszenia")));
						 markerList1.add(marker);
						 
						 resultAll = data.getStringExtra("result");
						 }
						 
						 LatLngBounds.Builder builder = new LatLngBounds.Builder();
			             for (Marker m : markerList1) {
			                 builder.include(m.getPosition());
			             }
			             
			             LatLngBounds bounds = builder.build();
			             
			             Map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
						 
					     } catch (JSONException e) {		
					    	 e.printStackTrace();
						    }	
				}
				
				else if (resultCode == RESULT_CANCELED)
					
					all_advertisement();
				
			
		}
		
		if(requestCode == 3)
		
			all_advertisement();

	}
	
	public boolean onMarkerClick(final Marker marker){
		
		try {
			 JSONArray jArray = new JSONArray(resultAll);

			 for(int i = 0; i<jArray.length(); i++){
				 
				 if(marker.getTitle().equals(jArray.getJSONObject(i).getString("id_ogloszenia"))){

					 	Intent intent = new Intent(context, OgloszeniaActivity.class);
						
						intent.putExtra("id", jArray.getJSONObject(i).getString("id_ogloszenia"));
						intent.putExtra("login", jArray.getJSONObject(i).getString("login"));
							
						startActivity(intent);
					 
				 }
			 }
	 
		     } catch (JSONException e) {		
		    	 e.printStackTrace();
			    }	
		
		return true;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.moje_konto) {
			
			Intent intent = new Intent(context, MojeKontoActivity.class);
			
			intent.putExtra("login", getLogin());
				
			startActivity(intent);
			
			return true;
		}
		if (id == R.id.dodaj_ogloszenie){

			Intent intent = new Intent(context, NoweActivity.class);
			
			intent.putExtra("login", getLogin());
				
			startActivityForResult(intent, 3);
			
			return true;
		}
		if (id == R.id.moje_ogloszenia) {

			Intent intent = new Intent(context, MojeOgloszeniaActivity.class);
			
			intent.putExtra("login", getLogin());
				
			startActivityForResult(intent, 3);
			
			return true;
		}
		if (id == R.id.wyloguj) {
			
			finish();
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	}