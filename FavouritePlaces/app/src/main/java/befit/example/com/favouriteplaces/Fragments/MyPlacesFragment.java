package befit.example.com.favouriteplaces.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.fundapter.BindDictionary;
import com.ami.fundapter.extractors.StringExtractor;
import com.ami.fundapter.interfaces.FunDapterFilter;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import befit.example.com.favouriteplaces.Adapter.MyFunDapter;
import befit.example.com.favouriteplaces.R;
import befit.example.com.favouriteplaces.Serialized.Miejsca;
import befit.example.com.favouriteplaces.interfaces.LoadPlacesInterface;

public class MyPlacesFragment extends Fragment implements LoadPlacesInterface{

    private ListView lvMiejsca;
    private TextView tvAddress;
    private SearchView searchView;
    private Bundle bundle;
    private String login, userLogin;
    private HashMap postData;
    private PostResponseAsyncTask loginTask;
    private ArrayList<Miejsca> miejscaLista;
    private MyFunDapter<Miejsca> adapter;
    private FragmentManager fm;
    private View v;
    private befit.example.com.favouriteplaces.Fragments.MyPlacesFragment myPlacesFragment;

    private String getLogin() {

        bundle = this.getArguments();

        login = bundle.getString("login");

        return login;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return inflater.inflate(R.layout.fragment_my_places, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        this.v = view;

        userLogin = getLogin();

        lvMiejsca(view);

        myPlacesFragment = this;

        searchView = (SearchView) view.findViewById(R.id.searchView);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.myplaces_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {

            fm = getFragmentManager();
            fm.popBackStack("LoginFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            return true;
        }
        if (id == android.R.id.home){

            fm = getFragmentManager();
            fm.popBackStack();

            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchView() {

        searchView.setQueryHint("Szukaj...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });
    }

    private void lvMiejsca(View view) {

        lvMiejsca = (ListView) view.findViewById(R.id.lvMiejsca);

        postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("login", userLogin);

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                if(!result.equals("failed")) {
                    miejscaLista = new JsonConverter<Miejsca>().toArrayList(result, Miejsca.class);

                    BindDictionary<Miejsca> dict = new BindDictionary<Miejsca>();

                    dict.addStringField(R.id.tvAddress, new StringExtractor<Miejsca>() {

                        @Override
                        public String getStringValue(Miejsca miejsca, int position) {
                            return miejsca.adres;
                        }
                    });

                    dict.addStringField(R.id.tvOpis, new StringExtractor<Miejsca>() {

                        @Override
                        public String getStringValue(Miejsca miejsca, int position) {
                            return miejsca.description;
                        }
                    });


                    adapter = new MyFunDapter<Miejsca>(getActivity(), miejscaLista, R.layout.list_item, dict, myPlacesFragment);

                    lvMiejsca.setAdapter(adapter);

                    initFilter(adapter);

                    searchView();

                }
                else{

                    Toast.makeText(getActivity(), "Brak ulubionych miejsc", Toast.LENGTH_LONG).show();
                    lvMiejsca.setAdapter(null);

                }
            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/favouriteplaces/places.php");

    }

    private void initFilter(MyFunDapter<Miejsca> adapter) {

        adapter.initFilter(new FunDapterFilter<Miejsca>() {
            @Override
            public List<Miejsca> filter(String s, List<Miejsca> list) {

                ArrayList listaMiejsc = new ArrayList<Miejsca>();

                for(int i = 0; i < list.size(); i++) {
                    Miejsca miejsca = list.get(i);
                    if(miejsca.description.toLowerCase().contains(s.toLowerCase()))
                        listaMiejsc.add(miejsca);

                }
                return listaMiejsc;
            }
        });

    }

    @Override
    public void loadPlaces(String s) {

        postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("login", userLogin);
        postData.put("adres", s);

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                Toast.makeText(getActivity(), "Miejsce zostało usunięte", Toast.LENGTH_LONG).show();

                lvMiejsca(v);

            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/favouriteplaces/delete.php");

    }
}