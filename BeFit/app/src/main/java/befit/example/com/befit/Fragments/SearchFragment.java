package befit.example.com.befit.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ami.fundapter.BindDictionary;
import com.ami.fundapter.extractors.StringExtractor;
import com.ami.fundapter.interfaces.FunDapterFilter;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import befit.example.com.befit.Adapter.MyFunDapter;
import befit.example.com.befit.R;
import befit.example.com.befit.Serialized.Products;
import interfaces.LongExtractor;
import interfaces.MyAdapterClickInterface;

public class SearchFragment extends Fragment implements MyAdapterClickInterface{

    private ListView lvSearch;
    private SearchView searchView;
    private HashMap postData;
    private String type, date, mail;
    private PostResponseAsyncTask loginTask;
    private ArrayList<Products> productsList;
    private Bundle bundle;
    private MenuItem save;
    private RelativeLayout layoutInvisible;
    private MyFunDapter<Products> adapter;
    private ArrayList products, sizes;
    private String resultAll;
    private  int counter = 0;
    private befit.example.com.befit.Fragments.SearchFragment searchFragment;

    private String getType(){

        bundle = this.getArguments();

        type = bundle.getString("type");

        return type;
    }

    private String getDate(){

        bundle = this.getArguments();

        date = bundle.getString("date");

        return date;
    }

    private String getMail(){

        bundle = this.getArguments();

        mail = bundle.getString("mail");

        return mail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getType());

        return inflater.inflate(R.layout.fragment_search, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        lvSearch(view);

        search(view);

        searchFragment = this;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.fragment_search_menu, menu);

        save = menu.findItem(R.id.save);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void lvSearch(View view) {

        lvSearch = (ListView) view.findViewById(R.id.lvSearch);

        postData = new HashMap();
        postData.put("mobile", "android");

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                resultAll = result;

                productsList = new JsonConverter<Products>().toArrayList(result, Products.class);

                BindDictionary<Products> dict = new BindDictionary<Products>();

                dict.addStringField(R.id.tvProductsWeight, new StringExtractor<Products>() {

                    @Override
                    public String getStringValue(Products products, int position) {
                        return products.quantity + "";
                    }
                });

                dict.addStringField(R.id.tvProductsWeight2, new StringExtractor<Products>() {

                    @Override
                    public String getStringValue(Products products, int position) {
                        return products.calories + "";
                    }
                });

                dict.addStringField(R.id.tvProductsName, new StringExtractor<Products>() {

                    @Override
                    public String getStringValue(Products products, int position) {
                        return products.name;
                    }
                });

                dict.addStringField(R.id.tvSearchSize, new StringExtractor<Products>() {

                    @Override
                    public String getStringValue(Products products, int position) {
                        return products.quantity + "";
                    }
                });

                dict.addStringField(R.id.tvSearchCalories, new StringExtractor<Products>() {

                    @Override
                    public String getStringValue(Products products, int position) {
                        return products.calories + "";
                    }
                });

                dict.addStringField(R.id.tvSearchFat, new StringExtractor<Products>() {

                    @Override
                    public String getStringValue(Products products, int position) {
                        return products.fat + "";
                    }
                });

                dict.addStringField(R.id.tvSearchCarbs, new StringExtractor<Products>() {

                    @Override
                    public String getStringValue(Products products, int position) {
                        return products.carbs + "";
                    }
                });

                dict.addStringField(R.id.tvSearchProtein, new StringExtractor<Products>() {

                    @Override
                    public String getStringValue(Products products, int position) {
                        return products.protein + "";
                    }
                });

                adapter = new MyFunDapter<Products>(getActivity(), productsList, R.layout.list_view_search, dict, searchFragment);

                lvSearch.setAdapter(adapter);

                initFilter(adapter);
            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/products.php");

    }

    private void initFilter(MyFunDapter<Products> adapter) {

        adapter.initFilter(new FunDapterFilter<Products>() {
            @Override
            public List<Products> filter(String s, List<Products> list) {

                ArrayList listProducts = new ArrayList<Products>();

                for(int i = 0; i < list.size(); i++) {
                    Products product = list.get(i);
                    if(product.name.toLowerCase().contains(s.toLowerCase()))
                        listProducts.add(product);

                }
                return listProducts;
            }
        });
    }

    private void search(View view) {

        searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setQueryHint("Search...");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {

            int sizeOfProducts = adapter.getClickedProducts().size();

            if (sizeOfProducts > 0) {
                products = adapter.getClickedProducts();
                sizes = adapter.getClickedSize();

                postData = new HashMap();
                postData.put("mobile", "android");
                postData.put("date", getDate());
                postData.put("mail", getMail());
                postData.put("type", getType());
                postData.put("p1", products.get(0).toString());
                postData.put("r1", sizes.get(0).toString());

                if(sizeOfProducts > 1) {
                    postData.put("p2", products.get(1).toString());
                    postData.put("r2", sizes.get(1).toString());
                }

                if(sizeOfProducts > 2) {
                    postData.put("p3", products.get(2).toString());
                    postData.put("r3", sizes.get(2).toString());
                }

                if(sizeOfProducts > 3) {
                    postData.put("p4", products.get(3).toString());
                    postData.put("r4", sizes.get(3).toString());
                }

                if(sizeOfProducts > 4) {
                    postData.put("p5", products.get(4).toString());
                    postData.put("r5", sizes.get(4).toString());
                }

                if(sizeOfProducts > 5) {
                    postData.put("p6", products.get(5).toString());
                    postData.put("r6", sizes.get(5).toString());
                }

                if(sizeOfProducts > 6) {
                    postData.put("p7", products.get(6).toString());
                    postData.put("r7", sizes.get(6).toString());
                }

                loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                    @Override
                    public void processFinish(String result) {


                        Toast.makeText(getActivity(), "Product/s added!", Toast.LENGTH_LONG).show();

                        Thread thread = new Thread(){

                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);

                                    FragmentManager fm = getFragmentManager();
                                    fm.popBackStack();

                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        thread.start();

                    }
                });

                loginTask.execute("http://befit.apkawwsis.nstrefa.pl/newMeal.php");
            }

            else{

                FragmentManager fm = getFragmentManager();
                fm.popBackStack();

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void changeMenu(){

        products = adapter.getClickedProducts();

        if(products.size() > 0){

            counter = products.size();

            save.setTitle("Save (" + counter + ")");
        }
        else
            save.setTitle("Cancel");

    }

    /*@Override
    public void itemChecked(int position) {

        counter++;
        optionsMenu();
    }

    @Override
    public void itemUnChecked(int position) {

        counter--;
        optionsMenu();
    }

    private void optionsMenu() {

        if (counter != 0)
            save.setTitle("Save (" + counter + ")");
        else
            save.setTitle("Cancel");
    }*/
}