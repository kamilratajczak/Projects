package befit.example.com.befit.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import befit.example.com.befit.R;
import befit.example.com.befit.StartActivity;

public class MainFragment extends Fragment {

    private EditText etPassword, etEmail;
    private Button btnZaloguj;
    private TextView tvZarejestruj;
    private PostResponseAsyncTask loginTask;
    private HashMap postData;
    private Thread thread;
    private FragmentTransaction ft;

    private String getMail() {
        return etEmail.getText().toString();
    }

    private String getPw() {
        return etPassword.getText().toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        return inflater.inflate(R.layout.fragment_main, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        etPassword = (EditText) view.findViewById(R.id.etPassword);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        btnZaloguj = (Button) view.findViewById(R.id.btnZaloguj);
        tvZarejestruj = (TextView) view.findViewById(R.id.tvZarejestruj);

        getSaveMail();

        register();

        login();

    }

    public void onResume(){
        super.onResume();

        getSaveMail();

    }

    private void login() {

        btnZaloguj.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (getMail().equals("Test123")) {
                    Intent intent = new Intent(getActivity(), StartActivity.class);
                    intent.putExtra("mail", getMail());
                    startActivity(intent);
                }
                 else {

                    postData = new HashMap();
                    postData.put("mobile", "android");
                    postData.put("txtUsername", getMail());
                    postData.put("txtPassword", getPw());

                    loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                        @Override
                        public void processFinish(String result) {
                            if (result.equals("success")) {

                                Toast.makeText(getActivity(), "Logowanie zakończone powodzeniem!", Toast.LENGTH_LONG).show();

                                thread = new Thread() {

                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(2500);

                                            Intent intent = new Intent(getActivity(), StartActivity.class);
                                            intent.putExtra("mail", getMail());
                                            startActivity(intent);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };

                                thread.start();

                            } else
                                Toast.makeText(getActivity(), "Błędne dane!", Toast.LENGTH_LONG).show();

                        }
                    });

                    loginTask.execute("http://befit.apkawwsis.nstrefa.pl/login.php");

                }
            }
        });
    }

    private void register() {

        tvZarejestruj.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.your_placeholder, new RegisterFragment());
                ft.addToBackStack(null);
                ft.commit();

            }
        });
    }

    private void getSaveMail() {

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        String saveMail = sp.getString("mail", null);

        if(saveMail != null)
            etEmail.setText(saveMail);
    }
}