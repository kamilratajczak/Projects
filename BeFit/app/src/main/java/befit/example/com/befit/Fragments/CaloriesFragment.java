package befit.example.com.befit.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.fundapter.BindDictionary;
import com.ami.fundapter.extractors.StringExtractor;
import com.ami.fundapter.interfaces.DynamicImageLoader;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import befit.example.com.befit.Adapter.CaloriesAdapter;
import befit.example.com.befit.Adapter.ListViewHeight;
import befit.example.com.befit.Adapter.WorkoutAdapter;
import befit.example.com.befit.R;
import befit.example.com.befit.Serialized.Products;
import befit.example.com.befit.Serialized.Workout;

import static befit.example.com.befit.R.id.parent;


public class CaloriesFragment extends Fragment {

    private Bundle bundle;
    private View mainView;
    private HashMap postData;
    private PostResponseAsyncTask loginTask;
    private ArrayList<Products> productsList;
    private ArrayList<Workout> workoutList;
    private FragmentTransaction ft;
    private String date, dateToday, mail, userMail, resultSupper, resultDinner, resultLunch, resultBreakfast, resultWorkout, idWorkout, idProducts;
    private ListViewHeight lvHeight;
    private SearchFragment searchFragment;
    private ListView lvBreakfast, lvLunch, lvDinner, lvSupper, lvWorkout;
    private RelativeLayout breakfastLayout, lunchLayout, dinnerLayout, supperLayout, bLayout, lLayout, dLayout, sLayout, workoutLayout, wLayout;
    private CaloriesAdapter adapter;
    private boolean delete = false;
    private TextView dFat, dCarbs, dProtein, tvDinner, bFat, bCarbs, bProtein, tvBreakfast, lFat, lCarbs, lProtein, tvLunch, sFat, sCarbs, sProtein, tvSupper, tvFat, tvCarbs, tvProtein, tvCalories, tvWorkout;

    private String getDateToday(){

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


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Calories");
        return inflater.inflate(R.layout.fragment_calories, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        mainView = view;

        bundle = new Bundle();

        searchFragment = new SearchFragment();

        breakfast(view);

        lunch(view);

        dinner(view);

        supper(view);

        workout(view);

        textView(view);

        dateToday = getDateToday();

        userMail = getMail();

    }

    private void textView(View view) {

        dFat = (TextView) view.findViewById(R.id.dFat);
        dCarbs = (TextView) view.findViewById(R.id.dCarbs);
        dProtein = (TextView) view.findViewById(R.id.dProtein);
        bFat = (TextView) view.findViewById(R.id.bFat);
        bCarbs = (TextView) view.findViewById(R.id.bCarbs);
        bProtein = (TextView) view.findViewById(R.id.bProtein);
        lFat = (TextView) view.findViewById(R.id.lFat);
        lCarbs = (TextView) view.findViewById(R.id.lCarbs);
        lProtein = (TextView) view.findViewById(R.id.lProtein);
        sFat = (TextView) view.findViewById(R.id.sFat);
        sCarbs = (TextView) view.findViewById(R.id.sCarbs);
        sProtein = (TextView) view.findViewById(R.id.sProtein);
        tvFat = (TextView) view.findViewById(R.id.tvFat);
        tvCarbs = (TextView) view.findViewById(R.id.tvCarbs);
        tvProtein = (TextView) view.findViewById(R.id.tvProtein);
        tvCalories = (TextView) view.findViewById(R.id.tvCalories);
        tvWorkout = (TextView) view.findViewById(R.id.tvWorkout);

    }

    public View getViewByPosition(int position, ListView listView){

        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() -1;

        if(position < firstListItemPosition || position > lastListItemPosition) {
            return listView.getAdapter().getView(position, null, listView);
        }
        else{
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    private void workout(View view) {

        lvWorkout = (ListView) view.findViewById(R.id.lvWorkout);

        postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("mail", getMail());
        postData.put("date", getDateToday());

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                resultWorkout = result;

                if(!result.equals("failed")) {

                    workoutList = new JsonConverter<Workout>().toArrayList(result, Workout.class);

                    BindDictionary<Workout> dict = new BindDictionary<Workout>();

                    dict.addStringField(R.id.tvListWorkout, new StringExtractor<Workout>() {

                        @Override
                        public String getStringValue(Workout workout, int position) {
                            return workout.exercise;
                        }
                    });

                    dict.addDynamicImageField(R.id.ivWorkout, new StringExtractor<Workout>() {

                        @Override
                        public String getStringValue(Workout workout, int position) {
                            return workout.photo;
                        }
                    }, new DynamicImageLoader() {

                        @Override
                        public void loadImage(String url, ImageView imageView) {

                            Picasso.with(getActivity())
                                    .load(url)
                                    .placeholder(R.drawable.befit)
                                    .error(android.R.drawable.stat_sys_download)
                                    .into(imageView);
                        }
                    });

                    WorkoutAdapter adapter = new WorkoutAdapter<Workout>(getActivity(), workoutList, R.layout.list_view_workout, dict);

                    lvWorkout.setAdapter(adapter);

                    lvHeight.setListViewHeightBasedOnItems(lvWorkout);

                    tvWorkout.setText("Click to see exercises");
                }
                else {
                    tvWorkout.setText("No workout today");
                    lvWorkout.setAdapter(null);
                    workoutLayout.setBackgroundResource(R.drawable.rectangle_bottom);
                }

            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/workout.php");

        workoutLayout = (RelativeLayout) view.findViewById(R.id.workoutLayout);

        workoutView(workoutLayout, tvWorkout, lvWorkout);
        //lvWorkout.setVisibility(View.GONE);

        lvWorkout.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    JSONArray jArray = new JSONArray(resultWorkout);

                    idWorkout = jArray.getJSONObject(position).getString("id_cwiczenia");

                }

                catch (JSONException e) {
                    e.printStackTrace();

                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("Delete");

                alertDialog.setMessage("Do you want to delete?");

                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                postData = new HashMap();
                                postData.put("mobile", "android");
                                postData.put("id", idWorkout);

                                loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                                    @Override
                                    public void processFinish(String result) {

                                        delete = true;

                                        workout(mainView);

                                        Toast.makeText(getActivity(), "Exercise has been removed", Toast.LENGTH_LONG).show();

                                    }
                                });

                                loginTask.execute("http://befit.apkawwsis.nstrefa.pl/delete_workout.php");
                            }
                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();

                return false;
            }
        });

        workoutLayout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (lvWorkout.getVisibility() == View.VISIBLE) {
                    workoutLayout.setBackgroundResource(R.drawable.rectangle_bottom);
                    lvWorkout.setVisibility(View.GONE);
                    tvWorkout.setText("Click to see exercises");
                } else {
                    if (tvWorkout.getText().toString().equals("Click to see exercises")) {
                        workoutLayout.setBackgroundResource(R.drawable.un_rectangle_bottom);
                        lvWorkout.setVisibility(View.VISIBLE);
                        tvWorkout.setText("Click to hide exercises");
                    }
                }
            }
        });

        wLayout = (RelativeLayout) view.findViewById(R.id.wLayout);
        wLayout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final CharSequence[] exercise = {"Chest", "Back", "Legs", "Shoulders", "Biceps", "Triceps", "Stomach", "Cardio"};

                final ArrayList<Integer> selectedExercise = new ArrayList();

                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Select Exercise")
                        .setMultiChoiceItems(exercise, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                if (isChecked)
                                    selectedExercise.add(indexSelected);
                                else
                                    selectedExercise.remove(indexSelected);
                            }
                        }).setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                if(selectedExercise.size() > 0) {

                                    postData = new HashMap();
                                    postData.put("mobile", "android");
                                    postData.put("date", dateToday);
                                    postData.put("mail", userMail);

                                    if (selectedExercise.contains(0))
                                        postData.put("c1", "Chest");
                                    if (selectedExercise.contains(1))
                                        postData.put("c2", "Back");
                                    if (selectedExercise.contains(2))
                                        postData.put("c3", "Legs");
                                    if (selectedExercise.contains(3))
                                        postData.put("c4", "Shoulders");
                                    if (selectedExercise.contains(4))
                                        postData.put("c5", "Biceps");
                                    if (selectedExercise.contains(5))
                                        postData.put("c6", "Triceps");
                                    if (selectedExercise.contains(6))
                                        postData.put("c7", "Stomach");
                                    if (selectedExercise.contains(7))
                                        postData.put("c8", "Cardio");

                                    loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                                        @Override
                                        public void processFinish(String result) {

                                            Toast.makeText(getActivity(), "Exercise/s added!", Toast.LENGTH_LONG).show();
                                            workout(mainView);
                                        }
                                    });

                                    loginTask.execute("http://befit.apkawwsis.nstrefa.pl/newExercise.php");
                                }
                                else
                                    Toast.makeText(getActivity(), "Nothing to save", Toast.LENGTH_LONG).show();

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();

                            }
                        }).create();
                dialog.show();
            }
        });
    }

    private void supper(View view) {

        tvSupper = (TextView) view.findViewById(R.id.tvSupper);

        lvSupper = (ListView) view.findViewById(R.id.lvSupper);

        postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("mail", getMail());
        postData.put("type", "supper");
        postData.put("date", getDateToday());

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                resultSupper = result;

                if(!result.equals("failed")) {

                    productsList = new JsonConverter<Products>().toArrayList(result, Products.class);

                    setAdapter(lvSupper, productsList);

                    lvHeight.setListViewHeightBasedOnItems(lvSupper);

                    setValues(lvSupper, sFat, sCarbs, sProtein, tvSupper);

                    loadValues();

                    mealView(supperLayout, tvSupper, lvSupper);

                }

                else {

                    setAdapterAndValue(sFat, sCarbs, sProtein, tvSupper, lvSupper);

                    loadValues();

                    mealView(supperLayout, tvSupper, lvSupper);

                }
            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/calories.php");


        supperLayout = (RelativeLayout) view.findViewById(R.id.supperLayout);

        lvSupper.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                getIdProducts(resultSupper, position);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("Delete");

                alertDialog.setMessage("Do you want to delete?");

                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                postData = new HashMap();
                                postData.put("mobile", "android");
                                postData.put("id", idProducts);

                                loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                                    @Override
                                    public void processFinish(String result) {

                                        delete = true;

                                        supper(mainView);

                                        Toast.makeText(getActivity(), "Product has been removed", Toast.LENGTH_LONG).show();

                                    }
                                });

                                loginTask.execute("http://befit.apkawwsis.nstrefa.pl/delete_meal.php");
                            }
                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                alertDialog.show();

                return false;
            }
        });

        loadMealLayout(lvSupper, supperLayout, tvSupper);

        sLayout = (RelativeLayout) view.findViewById(R.id.sLayout);

        loadSearchLayout(sLayout, "Supper");
    }

    private void dinner(View view) {

        tvDinner = (TextView) view.findViewById(R.id.tvDinner);

        lvDinner = (ListView) view.findViewById(R.id.lvDinner);

        postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("mail", getMail());
        postData.put("type", "dinner");
        postData.put("date", getDateToday());

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                resultDinner = result;

                if(!result.equals("failed")) {

                    productsList = new JsonConverter<Products>().toArrayList(result, Products.class);

                    setAdapter(lvDinner, productsList);

                    lvHeight.setListViewHeightBasedOnItems(lvDinner);

                    setValues(lvDinner, dFat, dCarbs, dProtein, tvDinner);

                    loadValues();

                    mealView(dinnerLayout, tvDinner, lvDinner);

                }

                else {

                    setAdapterAndValue(dFat, dCarbs, dProtein, tvDinner, lvDinner);

                    loadValues();

                    mealView(dinnerLayout, tvDinner, lvDinner);

                }
            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/calories.php");


        dinnerLayout = (RelativeLayout) view.findViewById(R.id.dinnerLayout);

        lvDinner.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                getIdProducts(resultDinner, position);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("Delete");

                alertDialog.setMessage("Do you want to delete?");

                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                postData = new HashMap();
                                postData.put("mobile", "android");
                                postData.put("id", idProducts);

                                loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                                    @Override
                                    public void processFinish(String result) {

                                        delete = true;

                                        dinner(mainView);

                                        Toast.makeText(getActivity(), "Product has been removed", Toast.LENGTH_LONG).show();

                                    }
                                });

                                loginTask.execute("http://befit.apkawwsis.nstrefa.pl/delete_meal.php");
                            }
                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                alertDialog.show();

                return false;
            }
        });

        loadMealLayout(lvDinner, dinnerLayout, tvDinner);

        dLayout = (RelativeLayout) view.findViewById(R.id.dLayout);

        loadSearchLayout(dLayout, "Dinner");
    }

    private void lunch(View view) {

        tvLunch = (TextView) view.findViewById(R.id.tvLunch);

        lvLunch = (ListView) view.findViewById(R.id.lvLunch);

        postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("mail", getMail());
        postData.put("type", "lunch");
        postData.put("date", getDateToday());

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                resultLunch = result;

                if(!result.equals("failed")) {

                    productsList = new JsonConverter<Products>().toArrayList(result, Products.class);

                    setAdapter(lvLunch, productsList);

                    lvHeight.setListViewHeightBasedOnItems(lvLunch);

                    setValues(lvLunch, lFat, lCarbs, lProtein, tvLunch);

                    loadValues();

                    mealView(lunchLayout, tvLunch, lvLunch);

                }

                else {

                    setAdapterAndValue(lFat, lCarbs, lProtein, tvLunch, lvLunch);

                    loadValues();

                    mealView(lunchLayout, tvLunch, lvLunch);

                }
            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/calories.php");


        lunchLayout = (RelativeLayout) view.findViewById(R.id.lunchLayout);

        lvLunch.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                getIdProducts(resultLunch, position);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("Delete");

                alertDialog.setMessage("Do you want to delete?");

                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                postData = new HashMap();
                                postData.put("mobile", "android");
                                postData.put("id", idProducts);

                                loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                                    @Override
                                    public void processFinish(String result) {

                                        delete = true;

                                        lunch(mainView);

                                        Toast.makeText(getActivity(), "Product has been removed", Toast.LENGTH_LONG).show();

                                    }
                                });

                                loginTask.execute("http://befit.apkawwsis.nstrefa.pl/delete_meal.php");
                            }
                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                alertDialog.show();

                return false;
            }
        });

        loadMealLayout(lvLunch, lunchLayout, tvLunch);

        lLayout = (RelativeLayout) view.findViewById(R.id.lLayout);

        loadSearchLayout(lLayout, "Lunch");
    }

    private void breakfast(View view) {

        tvBreakfast = (TextView) view.findViewById(R.id.tvBreakfast);

        lvBreakfast = (ListView) view.findViewById(R.id.lvBreakfast);

        postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("mail", getMail());
        postData.put("type", "breakfast");
        postData.put("date", getDateToday());

        loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

            @Override
            public void processFinish(String result) {

                resultBreakfast = result;

                if(!result.equals("failed")) {

                    productsList = new JsonConverter<Products>().toArrayList(result, Products.class);

                    setAdapter(lvBreakfast, productsList);

                    lvHeight.setListViewHeightBasedOnItems(lvBreakfast);

                    setValues(lvBreakfast, bFat, bCarbs, bProtein, tvBreakfast);

                    loadValues();

                    mealView(breakfastLayout, tvBreakfast, lvBreakfast);

                }

                else {

                    setAdapterAndValue(bFat, bCarbs, bProtein, tvBreakfast,lvBreakfast);

                    loadValues();

                    mealView(breakfastLayout, tvBreakfast, lvBreakfast);

                }
            }
        });

        loginTask.execute("http://befit.apkawwsis.nstrefa.pl/calories.php");


        breakfastLayout = (RelativeLayout) view.findViewById(R.id.breakfastLayout);

        lvBreakfast.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                getIdProducts(resultBreakfast, position);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("Delete");

                alertDialog.setMessage("Do you want to delete?");

                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                postData = new HashMap();
                                postData.put("mobile", "android");
                                postData.put("id", idProducts);

                                loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                                    @Override
                                    public void processFinish(String result) {

                                        delete = true;

                                        breakfast(mainView);

                                        Toast.makeText(getActivity(), "Product has been removed", Toast.LENGTH_LONG).show();

                                    }
                                });

                                loginTask.execute("http://befit.apkawwsis.nstrefa.pl/delete_meal.php");
                            }
                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                alertDialog.show();

                return false;
            }
        });

        loadMealLayout(lvBreakfast, breakfastLayout, tvBreakfast);

        bLayout = (RelativeLayout) view.findViewById(R.id.bLayout);

        loadSearchLayout(bLayout, "Breakfast");
    }

    private void setAdapter(ListView lv, ArrayList<Products> al){

        BindDictionary<Products> dict = new BindDictionary<Products>();

        dict.addStringField(R.id.tvProdukt, new StringExtractor<Products>() {

            @Override
            public String getStringValue(Products products, int position) {
                return products.name;
            }
        });

        dict.addStringField(R.id.tvIlosc, new StringExtractor<Products>() {

            @Override
            public String getStringValue(Products products, int position) {
                return products.quantity + " gram";
            }
        });

        dict.addStringField(R.id.tvProdFat, new StringExtractor<Products>() {

            @Override
            public String getStringValue(Products products, int position) {
                return String.format("%.1f", products.fat);
            }
        });

        dict.addStringField(R.id.tvProdCarbs, new StringExtractor<Products>() {

            @Override
            public String getStringValue(Products products, int position) {
                return String.format("%.1f", products.carbs);
            }
        });

        dict.addStringField(R.id.tvProdProtein, new StringExtractor<Products>() {

            @Override
            public String getStringValue(Products products, int position) {
                return String.format("%.1f", products.protein);
            }
        });

        dict.addStringField(R.id.tvProdCalories, new StringExtractor<Products>() {

            @Override
            public String getStringValue(Products products, int position) {
                return String.format("%.1f", products.calories);
            }
        });

        adapter = new CaloriesAdapter<Products>(getActivity(), al, R.layout.list_view, dict);

        lv.setAdapter(adapter);

    }

    private void loadValues() {

        float allCalories = Float.parseFloat(tvBreakfast.getText().toString().replace(",", ".")) + Float.parseFloat(tvLunch.getText().toString().replace(",", ".")) + Float.parseFloat(tvDinner.getText().toString().replace(",", ".")) + Float.parseFloat(tvSupper.getText().toString().replace(",", "."));
        float allFat = Float.parseFloat(bFat.getText().toString().replace(",", ".")) + Float.parseFloat(lFat.getText().toString().replace(",", ".")) + Float.parseFloat(dFat.getText().toString().replace(",", ".")) + Float.parseFloat(sFat.getText().toString().replace(",", "."));
        float allCarbs = Float.parseFloat(bCarbs.getText().toString().replace(",", ".")) + Float.parseFloat(lCarbs.getText().toString().replace(",", ".")) + Float.parseFloat(dCarbs.getText().toString().replace(",", ".")) + Float.parseFloat(sCarbs.getText().toString().replace(",", "."));
        float allProtein = Float.parseFloat(bProtein.getText().toString().replace(",", ".")) + Float.parseFloat(lProtein.getText().toString().replace(",", ".")) + Float.parseFloat(dProtein.getText().toString().replace(",", ".")) + Float.parseFloat(sProtein.getText().toString().replace(",", "."));

        tvCalories.setText(String.format("%.1f", allCalories));
        tvFat.setText(String.format("%.1f", allFat));
        tvCarbs.setText(String.format("%.1f", allCarbs));
        tvProtein.setText(String.format("%.1f", allProtein));

    }

    private void setValues(ListView lv, TextView fat, TextView carbs, TextView protein, TextView meal) {

        float allCalories = 0;
        float allProtein = 0;
        float allCarbs = 0;
        float allFat = 0;

        for(int i = 0; i < adapter.getCount(); i++) {

            View dinnerView = getViewByPosition(i, lv);

            TextView dinnerCalories = (TextView) dinnerView.findViewById(R.id.tvProdCalories);
            TextView dinnerProtein = (TextView) dinnerView.findViewById(R.id.tvProdProtein);
            TextView dinnerCarbs = (TextView) dinnerView.findViewById(R.id.tvProdCarbs);
            TextView dinnerFat = (TextView) dinnerView.findViewById(R.id.tvProdFat);

            allCalories += Float.parseFloat(dinnerCalories.getText().toString().replace(",", "."));
            allProtein += Float.parseFloat(dinnerProtein.getText().toString().replace(",", "."));
            allCarbs += Float.parseFloat(dinnerCarbs.getText().toString().replace(",", "."));
            allFat += Float.parseFloat(dinnerFat.getText().toString().replace(",", "."));
        }

        fat.setText(String.format("%.1f", allFat));
        carbs.setText(String.format("%.1f", allCarbs));
        protein.setText(String.format("%.1f", allProtein));
        meal.setText(String.format("%.1f", allCalories));

    }

    private void setAdapterAndValue(TextView fat, TextView carbs, TextView protein, TextView meal, ListView lv) {

        fat.setText("0");
        carbs.setText("0");
        protein.setText("0");
        meal.setText("0");

        lv.setAdapter(null);

    }

    private void getIdProducts(String s, int i) {

        try {
            JSONArray jArray = new JSONArray(s);

            idProducts = jArray.getJSONObject(i).getString("id_produktu");

        }

        catch (JSONException e) {
            e.printStackTrace();

        }

    }

    private void loadMealLayout(final ListView lv, final RelativeLayout rl, final TextView tv){

        rl.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(lv.getVisibility() == View.VISIBLE) {
                    rl.setBackgroundResource(R.drawable.rectangle_bottom);
                    lv.setVisibility(View.GONE);
                }
                else {
                    if (Float.parseFloat(tv.getText().toString().replace(",", ".")) > 0) {
                        rl.setBackgroundResource(R.drawable.un_rectangle_bottom);
                        lv.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }

    private void loadSearchLayout(RelativeLayout rl, final String s){

        rl.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                bundle.putString("type", s);
                bundle.putString("date", dateToday);
                bundle.putString("mail", userMail);

                searchFragment.setArguments(bundle);

                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.layoutForFragments, searchFragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
    }

    private void mealView(RelativeLayout rl, TextView tv, ListView lv) {

        if(delete == true && !tv.getText().toString().equals("0")) {
            delete = false;
            lv.setVisibility(View.VISIBLE);

        }
        else {
            lv.setVisibility(View.GONE);
            rl.setBackgroundResource(R.drawable.rectangle_bottom);
        }

    }

    private void workoutView(RelativeLayout rl, TextView tv, ListView lv) {

        if(delete == true && !tv.getText().toString().equals("No workout today")) {
            delete = false;
            lv.setVisibility(View.VISIBLE);

        }
        else {
            lv.setVisibility(View.GONE);
            rl.setBackgroundResource(R.drawable.rectangle_bottom);
        }

    }

    public void onResume(){
        super.onResume();

        breakfast(mainView);

        lunch(mainView);

        dinner(mainView);

        supper(mainView);

    }
}
