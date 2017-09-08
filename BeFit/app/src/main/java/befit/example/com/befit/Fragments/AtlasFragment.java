package befit.example.com.befit.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ami.fundapter.BindDictionary;
import com.ami.fundapter.FunDapter;
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


public class AtlasFragment extends Fragment {

    private ListView lvAtlas;
    private HashMap postData;
    private SearchView svAtlas;
    private PostResponseAsyncTask loginTask;
    private ArrayList<Atlas> atlasList;
    private AtlasAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Atlas of exercise");
        return inflater.inflate(R.layout.fragment_atlas, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        lvAtlas = (ListView) view.findViewById(R.id.lvAtlas);

        svAtlas(view);

        loadData();

    }

    private void svAtlas(View view) {

        svAtlas = (SearchView) view.findViewById(R.id.svAtlas);

        svAtlas.setQueryHint("Search...");
        svAtlas.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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

    private void loadData() {

        postData = new HashMap();
        postData.put("mobile", "android");

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                atlasList = new JsonConverter<Atlas>().toArrayList(result, Atlas.class);

                BindDictionary<Atlas> dict = new BindDictionary<Atlas>();

                dict.addStringField(R.id.tvAtlasName, new StringExtractor<Atlas>() {

                    @Override
                    public String getStringValue(Atlas atlas, int position) {
                        return atlas.name;
                    }
                });

                dict.addStringField(R.id.tvAtlasDescription, new StringExtractor<Atlas>() {

                    @Override
                    public String getStringValue(Atlas atlas, int position) {
                        return atlas.description;
                    }
                });


                adapter = new AtlasAdapter(getActivity(), atlasList, R.layout.list_view_atlas, dict);

                lvAtlas.setAdapter(adapter);

                initFilter(adapter);

            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/atlas.php");

    }

    private void initFilter(AtlasAdapter<Atlas> adapter) {

        adapter.initFilter(new FunDapterFilter<Atlas>() {
            @Override
            public List<Atlas> filter(String s, List<Atlas> list) {

                ArrayList atlasProducts = new ArrayList<Atlas>();

                for(int i = 0; i < list.size(); i++) {
                    Atlas atlas = list.get(i);
                    if(atlas.name.toLowerCase().contains(s.toLowerCase()))
                        atlasProducts.add(atlas);

                }
                return atlasProducts;
            }
        });
    }

}
