package befit.example.com.befit.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import befit.example.com.befit.Adapter.AtlasAdapter;
import befit.example.com.befit.R;
import befit.example.com.befit.Serialized.Atlas;


public class AddProductFragment extends Fragment {

    private EditText etAddName, etAddCalories, etAddFat, etAddCarbs, etAddProtein;
    private Button btnAddSave;
    private HashMap postData;
    private PostResponseAsyncTask loginTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add product");
        return inflater.inflate(R.layout.fragment_add_product, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        etAddName = (EditText) view.findViewById(R.id.etAddName);
        etAddCalories = (EditText) view.findViewById(R.id.etAddCalories);
        etAddFat = (EditText) view.findViewById(R.id.etAddFat);
        etAddCarbs = (EditText) view.findViewById(R.id.etAddCarbs);
        etAddProtein = (EditText) view.findViewById(R.id.etAddProtein);
        btnAddSave = (Button) view.findViewById(R.id.btnAddSave);

        btnAddSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!etAddName.getText().toString().equals("")){
                    if(!etAddCalories.getText().toString().equals("")){
                        if(!etAddFat.getText().toString().equals("")){
                            if(!etAddCarbs.getText().toString().equals("")){
                                if(!etAddProtein.getText().toString().equals("")){

                                    postData = new HashMap();
                                    postData.put("mobile", "android");
                                    postData.put("name", etAddName.getText().toString());
                                    postData.put("calories", etAddCalories.getText().toString());
                                    postData.put("fat", etAddFat.getText().toString());
                                    postData.put("carbs", etAddCarbs.getText().toString());
                                    postData.put("protein", etAddProtein.getText().toString());

                                    loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                                        @Override
                                        public void processFinish(String result) {
                                            if(result.equals("success")) {
                                                Toast.makeText(getActivity(), "Product must be accepted by the admin", Toast.LENGTH_LONG).show();

                                                Thread thread = new Thread() {

                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(2000);

                                                            FragmentManager fm = getFragmentManager();
                                                            fm.popBackStack();

                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                };

                                                thread.start();
                                            }
                                            else
                                                Toast.makeText(getActivity(), "Name occupied", Toast.LENGTH_LONG).show();

                                        }
                                    });

                                    loginTask.execute("http://befit.apkawwsis.nstrefa.pl/add_product.php");
                                }
                                else
                                Toast.makeText(getActivity(), "Add protein of product", Toast.LENGTH_LONG).show();

                            }
                            else
                            Toast.makeText(getActivity(), "Add carbs of product", Toast.LENGTH_LONG).show();

                        }
                        else
                        Toast.makeText(getActivity(), "Add fat of product", Toast.LENGTH_LONG).show();

                    }
                    else
                    Toast.makeText(getActivity(), "Add calories of product", Toast.LENGTH_LONG).show();

                }
                else
                Toast.makeText(getActivity(), "Add name of product", Toast.LENGTH_LONG).show();
            }
        });

    }
}