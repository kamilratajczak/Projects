package befit.example.com.apiproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.ami.fundapter.BindDictionary;
import com.ami.fundapter.extractors.StringExtractor;
import com.ami.fundapter.interfaces.FunDapterFilter;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import befit.example.com.apiproject.Adapter.MyFunDapter;
import befit.example.com.apiproject.R;
import befit.example.com.apiproject.Serialized.Users;
import befit.example.com.apiproject.Fragments.UserDetailFragment;
import befit.example.com.apiproject.interfaces.Communicate;
import befit.example.com.apiproject.interfaces.DetailBackground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An activity representing a list of Users. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link UserDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class UserListActivity extends AppCompatActivity implements Communicate, DetailBackground {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private ListView lvUser;
    private String json;
    private SearchView searchView;
    private TextView tv900;
    private MyFunDapter adapter;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private UserDetailFragment fragment;
    private DetailBackground detailBackground;
    private Fragment myFragment;
    private befit.example.com.apiproject.Activities.UserListActivity userListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.user_detail_container) != null) {

            tv900 = (TextView) findViewById(R.id.tv900);

            mTwoPane = true;

        }

        sp = getApplicationContext().getSharedPreferences("App_names", Context.MODE_PRIVATE);
        editor = sp.edit();

        userListActivity = this;

        setListView();

    }

    private void checkLastChoice() {

        if (mTwoPane) {
        String selectedId = sp.getString("selected", null);

        if(selectedId != null) {
                tv900.setVisibility(View.GONE);
                Bundle arguments = new Bundle();
                arguments.putString("json", json);
                arguments.putString("id", selectedId);
                arguments.putString("max", String.valueOf(adapter.getCount()));
                arguments.putBoolean("mTwoPane", mTwoPane);
                UserDetailFragment fragment = new UserDetailFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.user_detail_container, fragment, UserDetailFragment.TAG)
                        .commit();

            }
        }
    }

    private void setListView() {

        lvUser = (ListView) findViewById(R.id.lvUser);

        HashMap postData = new HashMap();
        postData.put("mobile", "android");

        PostResponseAsyncTask loginTask = new PostResponseAsyncTask(UserListActivity.this, postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                json = result;

                ArrayList<Users> userList = new JsonConverter<Users>().toArrayList(result, Users.class);

                BindDictionary<Users> dict = new BindDictionary<Users>();

                dict.addStringField(R.id.tvId, new StringExtractor<Users>() {

                    @Override
                    public String getStringValue(Users users, int position) {
                        return users.id + "";
                    }
                });

                dict.addStringField(R.id.tvImie, new StringExtractor<Users>() {

                    @Override
                    public String getStringValue(Users users, int position) {
                        return users.name;
                    }
                });


                adapter = new MyFunDapter(getApplicationContext(), userList, R.layout.user_list_content, dict, mTwoPane, userListActivity);

                lvUser.setAdapter(adapter);

                initFilter(adapter);

                searchView(adapter);

                checkLastChoice();
                
            }
        });

        loginTask.execute("https://befit.apkawwsis.nstrefa.pl/test.php");

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mTwoPane) {

                    putSelected(String.valueOf(view.getTag(R.id.ID_TAG)));

                    tv900.setVisibility(View.GONE);
                    Bundle arguments = new Bundle();
                    arguments.putString("json", json);
                    arguments.putString("id", String.valueOf(view.getTag(R.id.ID_TAG)));
                    arguments.putBoolean("mTwoPane", mTwoPane);
                    arguments.putString("max", String.valueOf(adapter.getCount()));
                    arguments.putBoolean("mTwoPane", mTwoPane);
                    fragment = new UserDetailFragment();
                    fragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.user_detail_container, fragment, UserDetailFragment.TAG)
                            .commit();

                    adapter.notifyDataSetChanged();


                } else {

                    putSelected(String.valueOf(view.getTag(R.id.ID_TAG)));

                    Intent intent = new Intent(UserListActivity.this, UserDetailActivity.class);
                    intent.putExtra("json", json);
                    intent.putExtra("id", String.valueOf(view.getTag(R.id.ID_TAG)));
                    intent.putExtra("max", String.valueOf(adapter.getCount()));
                    intent.putExtra("mTwoPane", mTwoPane);

                    startActivity(intent);
                }
            }
        });
    }

    private void putSelected(String s) {

        editor.remove("selected").commit();
        editor.putString("selected", s);
        editor.commit();

    }


    private void searchView(final MyFunDapter adapter) {

        searchView = (SearchView) findViewById(R.id.searchView);
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

    private void initFilter(MyFunDapter<Users> adapter) {

        adapter.initFilter(new FunDapterFilter<Users>() {
            @Override
            public List<Users> filter(String s, List<Users> list) {

                ArrayList listProducts = new ArrayList<Users>();

                for(int i = 0; i < list.size(); i++) {
                    Users users = list.get(i);
                    if(users.name.toLowerCase().contains(s.toLowerCase()))
                        listProducts.add(users);

                }
                return listProducts;
            }
        });

    }

    @Override
    public void nastepny(int id) {

        int next = 1;

        if(id != adapter.getCount())
        next = id + 1;

        String nextStr = String.valueOf(next);

        Bundle arguments = new Bundle();
        arguments.putString("json", json);
        arguments.putString("id", nextStr);
        arguments.putString("max", String.valueOf(adapter.getCount()));
        arguments.putBoolean("mTwoPane", mTwoPane);
        UserDetailFragment fragment = new UserDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.user_detail_container, fragment, UserDetailFragment.TAG)
                .commit();
    }

    @Override
    public void poprzedni(int id) {

        int next = adapter.getCount();

        if(id != 1)
            next = id - 1;

        String nextStr = String.valueOf(next);

        Bundle arguments = new Bundle();
        arguments.putString("json", json);
        arguments.putString("id", nextStr);
        arguments.putString("max", String.valueOf(adapter.getCount()));
        arguments.putBoolean("mTwoPane", mTwoPane);
        UserDetailFragment fragment = new UserDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.user_detail_container, fragment, UserDetailFragment.TAG)
                .commit();

    }

    @Override
    public void refreshAdapter() {

        adapter.notifyDataSetChanged();

    }

    @Override
    public void change() {
        myFragment = getSupportFragmentManager().findFragmentByTag(UserDetailFragment.TAG);
        if(myFragment != null) {
            detailBackground = (DetailBackground) myFragment;

            detailBackground.change();
        }
    }
}

