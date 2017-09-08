package com.example.mapamieszkan;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

import com.ami.fundapter.BindDictionary;
import com.ami.fundapter.FunDapter;
import com.ami.fundapter.extractors.StringExtractor;
import com.ami.fundapter.interfaces.DynamicImageLoader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MojeOgloszeniaActivity extends Activity{
	
	private Context context;
	private Bundle bundle;
	private PostResponseAsyncTask loginTask;
	private HashMap postData;
	private TextView tvAdres, tvTyp, tvCena;
	private ImageView ivZdjecie;
	private String result1;
	private int idOgloszenia;
	private ListView lvOgloszenia;
	private ArrayList<Ogloszenia> ogloszeniaList;
	
	public String getLogin(){
		bundle = getIntent().getExtras();
		return bundle.getString("login");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moje_ogloszenia2);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		context = getApplicationContext();
				
		load_lvtv();
		
		load_data();
		
	}
	

	@Override
	protected void onRestart(){
		super.onRestart();

		load_data();
		
	}
	
	private void load_data() {
		
		postData = new HashMap();
		postData.put("mobile", "android");
		postData.put("txtLogin", getLogin());
			
		loginTask = new PostResponseAsyncTask(MojeOgloszeniaActivity.this, postData, new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
				
				if(result.equals("failed")){
					Toast.makeText(context, "Brak og³oszeñ!", Toast.LENGTH_LONG).show();
					lvOgloszenia.setAdapter(null);
				}
				
				else{
					
				result1 = result;
          
				ogloszeniaList = new JsonConverter<Ogloszenia>().toArrayList(result, Ogloszenia.class);
				
				BindDictionary<Ogloszenia> dict = new BindDictionary<Ogloszenia>();	
				
				dict.addStringField(R.id.tvAdres, new StringExtractor<Ogloszenia>() {
					
					@Override
					public String getStringValue(Ogloszenia ogloszenia, int position) {
						return ogloszenia.adres;
					}
				});
				
				
				dict.addStringField(R.id.tvTyp, new StringExtractor<Ogloszenia>() {
					
					@Override
					public String getStringValue(Ogloszenia ogloszenia, int position) {
						return ogloszenia.ilosc_pokoi;
					}
				});
				
				dict.addStringField(R.id.tvData, new StringExtractor<Ogloszenia>() {
					
					@Override
					public String getStringValue(Ogloszenia ogloszenia, int position) {
						return ogloszenia.data;
					}
				});
				
				dict.addStringField(R.id.tvCena, new StringExtractor<Ogloszenia>() {
					
					@Override
					public String getStringValue(Ogloszenia ogloszenia, int position) {
						return ogloszenia.cena;
					}
				});
				

				dict.addDynamicImageField(R.id.ivZdjecie, new StringExtractor<Ogloszenia>() {
					
					@Override
					public String getStringValue(Ogloszenia ogloszenia, int position) {
						return ogloszenia.url;
					}
				}, new DynamicImageLoader() {
					
					@Override
					public void loadImage(String url, ImageView imageView) {
						
						Picasso.with(context)
							   .load(url)
							   .placeholder(R.drawable.ic_action_camera)
							   .error(android.R.drawable.stat_sys_download)
							   .into(imageView);
					}
				});

				
				FunDapter<Ogloszenia> adapter = new FunDapter<Ogloszenia>(MojeOgloszeniaActivity.this, ogloszeniaList, R.layout.layout_list, dict);
				
				lvOgloszenia.setAdapter(adapter);
				}
			}
		});
    
		loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/advertisement.php");
		
	}

	private void load_lvtv() {

		tvAdres = (TextView)findViewById(R.id.tvAdres);
		tvTyp = (TextView)findViewById(R.id.tvTyp);
		tvCena = (TextView)findViewById(R.id.tvCena);
		ivZdjecie = (ImageView)findViewById(R.id.ivZdjecie);
		
		lvOgloszenia = (ListView)findViewById(R.id.lvOgloszenia);
		lvOgloszenia.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				for(int i = 0 ; i<lvOgloszenia.getAdapter().getCount(); i++){
					if(position == i){
						
					idOgloszenia = i;
						
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(MojeOgloszeniaActivity.this);

			        alertDialog.setTitle("Wybierz");

			        alertDialog.setMessage("Co chcesz zrobiæ?");

			        alertDialog.setIcon(R.drawable.ic_launcher);
			        
			        alertDialog.setPositiveButton("Edytuj",
			                new DialogInterface.OnClickListener() {
			                    public void onClick(DialogInterface dialog,    int which) {
			                    	
			                    	try {
				       					JSONArray jArray = new JSONArray(result1);
				       									       					 
				       					Intent intent = new Intent(context, Edytuj.class);
				       									                    	
				                    	intent.putExtra("id", jArray.getJSONObject(idOgloszenia).getString("id_ogloszenia"));
				                    	
				                    	startActivity(intent);
				       		 			       					 				       				
				                    	}

				       				      catch (JSONException e) {		
				       				    	 e.printStackTrace();
				       				    	 
				       					    }			                       
			                    }
			                });
			        
			        alertDialog.setNegativeButton("Cofnij", 
			        	new DialogInterface.OnClickListener() {
	                    	public void onClick(DialogInterface dialog, int which) {
	                    		dialog.cancel();
	                    }
	                });

			        alertDialog.setNeutralButton("Usuñ",
			                new DialogInterface.OnClickListener() {
			                    public void onClick(DialogInterface dialog,int which) {
			                    
			                    	try {
			       					 JSONArray jArray = new JSONArray(result1);
			       		 			       	
			       					postData = new HashMap();
			       					postData.put("mobile", "android");
			       					postData.put("txtId", jArray.getJSONObject(idOgloszenia).getString("id_ogloszenia"));
			       						
			       					loginTask = new PostResponseAsyncTask(MojeOgloszeniaActivity.this, postData, new AsyncResponse() {
			       						
			       						@Override
			       						public void processFinish(String result) {
			       			        	  Toast.makeText(MojeOgloszeniaActivity.this, "Og³oszenie zosta³o usuniête!", Toast.LENGTH_LONG).show();
			       						}
			       					});
			       							
			       					loginTask.execute("http://aplikacja.apkawwsis.nstrefa.pl/php/delete.php");
			        
			                    	}

			       				      catch (JSONException e) {		
			       				    	 e.printStackTrace();
			       					    }	
			                    	
			                    	load_data();
			                    	
			                    }
			                });

			        alertDialog.show();
					}
				
				}
			}
			});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			
			Intent intent = new Intent();
			
			setResult(4, intent);
			
			finish();
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}