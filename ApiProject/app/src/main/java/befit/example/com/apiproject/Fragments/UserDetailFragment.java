package befit.example.com.apiproject.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import befit.example.com.apiproject.Activities.UserDetailActivity;
import befit.example.com.apiproject.Activities.UserListActivity;
import befit.example.com.apiproject.R;
import befit.example.com.apiproject.interfaces.Communicate;
import befit.example.com.apiproject.interfaces.DetailBackground;

/**
 * A fragment representing a single User detail screen.
 * This fragment is either contained in a {@link UserListActivity}
 * in two-pane mode (on tablets) or a {@link UserDetailActivity}
 * on handsets.
 */
public class UserDetailFragment extends Fragment implements DetailBackground {
    public static final String TAG = "UserDetail";
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */

    private String id, json, name, username, email, phone, website, max;
    private Communicate communicate;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private ArrayList<String> alImiona = new ArrayList<String>();
    private Set<String> set;
    private ImageView btnNastepny;
    private ImageView btnPoprzedni;
    private ImageView ivFav;
    private boolean mTwoPane;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserDetailFragment() {
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Activity activity;

            activity = (Activity) context;

        try{
            communicate = (Communicate) activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement  Communicate");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getArguments().getString("id");
        json = getArguments().getString("json");
        max = getArguments().getString("max");
        mTwoPane = getArguments().getBoolean("mTwoPane");


        try {
            JSONArray jArray = new JSONArray(json);

            for (int i = 0; i < jArray.length(); i++) {
                if (jArray.getJSONObject(i).getString("id").equals(id)) {

                    name = jArray.getJSONObject(i).getString("name");
                    username = jArray.getJSONObject(i).getString("username");
                    email = jArray.getJSONObject(i).getString("email");
                    phone = jArray.getJSONObject(i).getString("phone");
                    website = jArray.getJSONObject(i).getString("website");

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_detail, container, false);

        sp = getActivity().getSharedPreferences("App_names", Context.MODE_PRIVATE);
        editor = sp.edit();

        ((TextView) rootView.findViewById(R.id.user_name)).setText("Name: " + name);
        ((TextView) rootView.findViewById(R.id.user_nick)).setText("Username: " + username);
        ((TextView) rootView.findViewById(R.id.user_email)).setText("Email: " + email);
        ((TextView) rootView.findViewById(R.id.user_phone)).setText("Phone: " + phone);
        ((TextView) rootView.findViewById(R.id.user_website)).setText("Website: " + website);

        btnNastepny = (ImageView) rootView.findViewById(R.id.btnNastepny);
        btnPoprzedni = (ImageView) rootView.findViewById(R.id.btnPoprzedni);

        ivFav = (ImageView)rootView.findViewById(R.id.ivFav);

        setBackground();

        ifFav();

        btnNastepny();
        btnPoprzedni();


        return rootView;
    }

    private void ifFav() {

        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ivFav.getTag(R.id.ADD9_TAG).equals("usun")){

                    pobierzImiona();

                    sp.edit().remove("DATE_NAME").commit();

                    alImiona.remove(id);

                    zapiszImie();

                    Toast.makeText(getActivity(), "Usunięto z ulubionych", Toast.LENGTH_LONG).show();

                    ivFav.setImageResource(R.drawable.ic_add_circle_black_24dp);
                    ivFav.setTag(R.id.ADD9_TAG, "dodaj");

                    communicate.refreshAdapter();
                }
                else {

                    pobierzImiona();

                    alImiona.add(id);

                    zapiszImie();

                    Toast.makeText(getActivity(), "Dodano do ulubionych", Toast.LENGTH_LONG).show();

                    ivFav.setImageResource(R.drawable.ic_remove_circle_black_24dp);
                    ivFav.setTag(R.id.ADD9_TAG, "usun");

                    communicate.refreshAdapter();
                }
            }
        });

    }

    public void setBackground() {

        set = sp.getStringSet("DATE_NAME", null);

        if (set != null) {
            alImiona.clear();
            alImiona.addAll(set);
        } else
            alImiona.clear();

        if (alImiona.size() > 0) {
            for (int i = 0; i < alImiona.size(); i++) {
                if (id.equals(alImiona.get(i))) {
                    ivFav.setImageResource(R.drawable.ic_remove_circle_black_24dp);
                    ivFav.setTag(R.id.ADD9_TAG, "usun");
                    break;
                } else {
                    ivFav.setImageResource(R.drawable.ic_add_circle_black_24dp);
                    ivFav.setTag(R.id.ADD9_TAG, "dodaj");
                }
            }
        } else {
            ivFav.setImageResource(R.drawable.ic_add_circle_black_24dp);
            ivFav.setTag(R.id.ADD9_TAG, "dodaj");
        }
    }

    private void btnPoprzedni() {

        btnPoprzedni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int maxInt = Integer.parseInt(max);
                int idInt = Integer.parseInt(id);
                int next = maxInt;

                if(idInt != 1)
                    next = idInt - 1;

                String nextStr = String.valueOf(next);

                editor.remove("selected").commit();
                editor.putString("selected", nextStr);
                editor.commit();

                communicate.poprzedni(Integer.parseInt(id));

                if(mTwoPane)
                    communicate.refreshAdapter();
            }
        });
    }

    private void btnNastepny() {

        btnNastepny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int maxInt = Integer.parseInt(max);
                int idInt = Integer.parseInt(id);
                int next = 1;

                if(idInt != maxInt)
                    next = idInt + 1;

                String nextStr = String.valueOf(next);

                editor.remove("selected").commit();
                editor.putString("selected", nextStr);
                editor.commit();

                communicate.nastepny(Integer.parseInt(id));

                if(mTwoPane)
                    communicate.refreshAdapter();
            }
        });
    }

    private void pobierzImiona() {

        set = sp.getStringSet("DATE_NAME", null);

        if(set != null) {
            alImiona.clear();
            alImiona.addAll(set);
        }

    }

    private void zapiszImie() {

        Set<String> set = new HashSet<String>();
        set.addAll(alImiona);
        editor.putStringSet("DATE_NAME", set);
        editor.apply();

    }

    @Override
    public void change() {
        setBackground();
    }
}
