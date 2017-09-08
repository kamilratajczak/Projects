package befit.example.com.befit.Fragments;

import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import befit.example.com.befit.R;

public class CalendarFragment extends Fragment {

    private Bundle bundle;
    private String mail, userMail;
    private CalendarView calendarView;
    private CaloriesFragment caloriesFragment;

    private String getMail(){

        bundle = this.getArguments();

        mail = bundle.getString("mail");

        return mail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Calendar");
        return inflater.inflate(R.layout.fragment_calendar, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        userMail = getMail();

        calendar(view);

    }

    private void calendar(View view) {

        calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                month += 1;
                String monthStr = String.valueOf(month);
                String dayStr = String.valueOf(dayOfMonth);

                if(monthStr.length() == 1)
                    monthStr = "0" + monthStr;

                if(dayStr.length() == 1)
                    dayStr = "0" + dayStr;

                String choosenDate = year + "-" + monthStr + "-" + dayStr;

                Toast.makeText(getActivity(), choosenDate, Toast.LENGTH_LONG).show();

                Bundle bundle = new Bundle();

                caloriesFragment = new CaloriesFragment();
                bundle.putString("date", choosenDate);
                bundle.putString("mail", userMail);
                caloriesFragment.setArguments(bundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.layoutForFragments, caloriesFragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
    }
}
