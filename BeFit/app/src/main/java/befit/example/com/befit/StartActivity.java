package befit.example.com.befit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import befit.example.com.befit.Fragments.AddProductFragment;
import befit.example.com.befit.Fragments.AtlasFragment;
import befit.example.com.befit.Fragments.CalendarFragment;
import befit.example.com.befit.Fragments.CaloriesFragment;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Date date;
    private DateFormat format;
    private String dateToday, mail;
    private Bundle bundle;
    private CaloriesFragment caloriesFragment;
    private CalendarFragment calendarFragment;
    private AtlasFragment atlasFragment;
    private AddProductFragment addProductFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = getIntent().getExtras();

        mail = bundle.getString("mail");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getDateToday();

        loadFragment();

    }

    private void loadFragment() {

        bundle = new Bundle();
        caloriesFragment = new CaloriesFragment();
        bundle.putString("date", dateToday);
        bundle.putString("mail", mail);
        caloriesFragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layoutForFragments, caloriesFragment);
        ft.commit();

    }

    private void getDateToday() {

        date = Calendar.getInstance().getTime();

        format = new SimpleDateFormat("yyyy-MM-dd");

        dateToday = format.format(date);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calories) {

            loadFragment();

        }  else if (id == R.id.nav_calendar) {

            bundle = new Bundle();
            calendarFragment = new CalendarFragment();
            bundle.putString("mail", mail);
            calendarFragment.setArguments(bundle);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.layoutForFragments, calendarFragment);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_add_product) {

            bundle = new Bundle();
            addProductFragment = new AddProductFragment();
            bundle.putString("mail", mail);
            addProductFragment.setArguments(bundle);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.layoutForFragments, addProductFragment);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_atlas) {

            atlasFragment = new AtlasFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.layoutForFragments, atlasFragment);
            ft.addToBackStack(null);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
