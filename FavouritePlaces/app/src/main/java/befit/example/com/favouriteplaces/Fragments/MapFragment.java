package befit.example.com.favouriteplaces.Fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Address;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import befit.example.com.favouriteplaces.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private FusedLocationProviderClient mFusedLocationClient;
    private double latitude, longitude;
    private Geocoder geocoder;
    private List<Address> addresses;
    private String newAdress, city;
    private Bundle bundle;
    private String login, userLogin;
    private FragmentTransaction ft;
    private MyPlacesFragment myPlacesFragment;
    private HashMap postData;
    private PostResponseAsyncTask loginTask;
    private List<Marker> markerList = new ArrayList<Marker>();
    private ImageButton ibLocation;
    private boolean save = false;
    private boolean myLocation = false;
    private boolean confirm = false;
    private TextView tvDialog;
    private EditText etNotatka;

    private String getLogin() {

        bundle = this.getArguments();

        login = bundle.getString("login");

        return login;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        return inflater.inflate(R.layout.fragment_map, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment supportMapFragment =  SupportMapFragment.newInstance();
        fm.beginTransaction().replace(R.id.mapPlaceholder, supportMapFragment).commit();

        supportMapFragment.getMapAsync(this);

        userLogin = getLogin();

        buttonLocation(view);

    }

    public void onResume(){
        super.onResume();

        loadPlaces();

    }

    private void buttonLocation(View view) {

        ibLocation = (ImageButton)  view.findViewById(R.id.ibLocation);

        ibLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLocation();

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {

            save = true;

            getLocation();

            final Dialog dialog = new Dialog(getActivity());

            dialog.setContentView(R.layout.dialog);
            dialog.setTitle("Dodaj nowe miejsce");

            tvDialog = (TextView) dialog.findViewById(R.id.tvDialog);

            etNotatka = (EditText) dialog.findViewById(R.id.etNotatka);

            buttonPositive(dialog);

            buttonNegative(dialog);

            dialog.show();

            return true;
        }
        if (id == R.id.my_places) {

            bundle.putString("login", userLogin);

            myPlacesFragment = new MyPlacesFragment();
            myPlacesFragment.setArguments(bundle);

            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder, myPlacesFragment);
            ft.addToBackStack(null);
            ft.commit();

            return true;
        }
        if (id == R.id.logout) {

            FragmentManager fm = getFragmentManager();
            fm.popBackStack();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void buttonNegative(final Dialog dialog) {

        Button bNegative = (Button) dialog.findViewById(R.id.bNegative);

        bNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

    }

    private void buttonPositive(final Dialog dialog) {

        Button bPositive = (Button) dialog.findViewById(R.id.bPositive);

        bPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!etNotatka.getText().toString().equals("")) {
                    save = true;
                    confirm = true;

                    getLocation();

                    dialog.dismiss();
                }
                else
                    Toast.makeText(getActivity(), "Dodaj notatkę", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "GPS jest wyłączony", Toast.LENGTH_SHORT).show();
            save = false;
            confirm = false;
        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                                getAddressFromLocation();

                            } else {
                                Toast.makeText(getActivity(), "Brak sygnału GPS", Toast.LENGTH_LONG).show();
                                save = false;
                                confirm = false;
                            }
                        }
                    });

        }
    }

    private void getAddressFromLocation() {

        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);

            String address = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            newAdress = "ul. " + address;

            if(save == true) {
                if(confirm == true) {
                    savePlace();
                    confirm = false;
                    save = false;
                }
                else {
                    tvDialog.setText(newAdress);
                    save = false;
                }
            }
            else {
                Toast.makeText(getActivity(), "Twoja lokalizacja:\n" + newAdress, Toast.LENGTH_LONG).show();

                myLocation = true;

                loadPlaces();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void savePlace() {

        postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("login", userLogin);
        postData.put("adres", newAdress);
        postData.put("city", city);
        postData.put("x", String.valueOf(latitude));
        postData.put("y", String.valueOf(longitude));
        postData.put("description", etNotatka.getText().toString());

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {
                if (result.equals("success")) {

                    Toast.makeText(getActivity(), "Miejsce zostało dodane", Toast.LENGTH_LONG).show();

                    loadPlaces();

                } else
                    Toast.makeText(getActivity(), "Miejsce już istnieje", Toast.LENGTH_LONG).show();

            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/favouriteplaces/new_place.php");


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;

        initializeMap();

    }

    private void initializeMap() {

        if (map == null) {
            Toast.makeText(getActivity(), "Nie można załadować mapy", Toast.LENGTH_SHORT).show();
        } else
                loadPlaces();
    }

    private void loadPlaces() {

        postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("login", userLogin);

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                map.clear();
                markerList.clear();

                try {
                    JSONArray jArray = new JSONArray(result);

                    for(int i = 0; i<jArray.length(); i++){

                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(jArray.getJSONObject(i).getString("x")), Double.parseDouble(jArray.getJSONObject(i).getString("y")))).title(jArray.getJSONObject(i).getString("description")));
                        markerList.add(marker);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(myLocation == true){

                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Tutaj jesteś").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        markerList.add(marker);

                        myLocation = false;
                    }

                    if(markerList.size() > 0)
                    moveCamera();

            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/favouriteplaces/my_places.php");

    }

    private void moveCamera() {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker m : markerList) {
            builder.include(m.getPosition());
        }

        LatLngBounds bounds = builder.build();

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

    }
}