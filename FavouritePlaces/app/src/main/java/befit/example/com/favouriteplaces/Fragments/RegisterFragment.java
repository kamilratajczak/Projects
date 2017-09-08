package befit.example.com.favouriteplaces.Fragments;


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

import befit.example.com.favouriteplaces.R;

public class RegisterFragment extends Fragment {

    private EditText etMail, etPw, etPw2;
    private Button btnZarejestruj;
    private FragmentManager fm;
    private PostResponseAsyncTask loginTask;
    private HashMap postData;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return inflater.inflate(R.layout.fragment_register, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        etMail = (EditText) view.findViewById(R.id.etLog);
        etPw = (EditText) view.findViewById(R.id.etPw);
        etPw2 = (EditText) view.findViewById(R.id.etPw2);
        btnZarejestruj = (Button) view.findViewById(R.id.btnZarejestruj);

        btnZarejestruj.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (!etMail.getText().toString().equals("")) {
                    if (etPw.getText().toString().length() > 3) {
                        if (etPw2.getText().toString().equals(etPw.getText().toString())) {

                            postData = new HashMap();
                            postData.put("mobile", "android");
                            postData.put("txtUsername", etMail.getText().toString());
                            postData.put("txtPassword", etPw.getText().toString());

                            loginTask = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {

                                @Override
                                public void processFinish(String result) {
                                    if (result.equals("success")) {

                                        sp = getActivity().getPreferences(Context.MODE_PRIVATE);
                                        editor = sp.edit();
                                        editor.putString("login", etMail.getText().toString());
                                        editor.commit();

                                        Toast.makeText(getActivity(), "Rejestracja zakończona powodzeniem!", Toast.LENGTH_LONG).show();

                                        fm = getFragmentManager();
                                        fm.popBackStack();

                                    } else
                                        Toast.makeText(getActivity(), "Login zajęty!", Toast.LENGTH_LONG).show();

                                }
                            });

                            loginTask.execute("http://befit.apkawwsis.nstrefa.pl/favouriteplaces/register.php");

                        } else
                            Toast.makeText(getActivity(), "Podane hasła są różne", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getActivity(), "Hasło musi być dłuższe niż 3 znaki", Toast.LENGTH_LONG).show();

                } else
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
