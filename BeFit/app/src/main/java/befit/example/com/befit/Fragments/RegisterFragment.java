package befit.example.com.befit.Fragments;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

import befit.example.com.befit.R;

public class RegisterFragment extends Fragment {

    private EditText etMail, etPw, etPw2;
    private Button btnZarejestruj;
    private FragmentManager fm;
    private PostResponseAsyncTask loginTask;
    private HashMap postData;
    private Thread thread;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String getMail(){
        return etMail.getText().toString();
    }

    private String getPw(){
        return etPw.getText().toString();
    }

    private String getPw2(){
        return etPw2.getText().toString();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return inflater.inflate(R.layout.fragment_register, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        etMail = (EditText)view.findViewById(R.id.etMail);
        etPw = (EditText)view.findViewById(R.id.etPw);
        etPw2 = (EditText)view.findViewById(R.id.etPw2);
        btnZarejestruj = (Button)view.findViewById(R.id.btnZarejestruj);

        btnZarejestruj.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(!getMail().equals("")){
                    if (getPw().length() > 3) {
                        if (getPw2().equals(getPw())) {

                            postData = new HashMap();
                            postData.put("mobile", "android");
                            postData.put("txtUsername", getMail());
                            postData.put("txtPassword", getPw());

                            loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                                @Override
                                public void processFinish(String result) {
                                    if(result.equals("success")){

                                        sp = getActivity().getPreferences(Context.MODE_PRIVATE);
                                        editor = sp.edit();
                                        editor.putString("mail", getMail());
                                        editor.commit();

                                        Toast.makeText(getActivity(), "Rejestracja zakończona powodzeniem!", Toast.LENGTH_LONG).show();

                                        thread = new Thread(){

                                            @Override
                                            public void run() {
                                                try {
                                                    Thread.sleep(2500);

                                                    fm = getFragmentManager();
                                                    fm.popBackStack();

                                                }
                                                catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };

                                        thread.start();

                                    }
                                    else
                                        Toast.makeText(getActivity(), "Login zajęty!", Toast.LENGTH_LONG).show();

                                }
                            });

                            loginTask.execute("http://befit.apkawwsis.nstrefa.pl/register.php");

                        }
                        else
                            Toast.makeText(getActivity(), "Podane hasła są różne", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Hasło musi być dłuższe niż 3 znaki", Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(getActivity(), "Uzupełnij wszystkie pola!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            fm = getFragmentManager();
            fm.popBackStack();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}