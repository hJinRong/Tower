package com.example.tower;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tower.Character.User;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register_2 extends AppCompatActivity {

    TextView username;
    EditText account, password;
    Button sendRegistrationRequest;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2);
        String call = getIntent().getStringExtra("username");
        username = (TextView) findViewById(R.id.username);
        username.setText(call);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        sendRegistrationRequest = (Button) findViewById(R.id.sendRegistrationRequest);
        account.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (!account.getText().toString().equals("")) {
                            password.setVisibility(View.VISIBLE);
                        } else {
                            password.setVisibility(View.INVISIBLE);
                        }
                    }
                }
        );

        password.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (!password.getText().toString().equals("")) {
                            sendRegistrationRequest.setVisibility(View.VISIBLE);
                        } else {
                            sendRegistrationRequest.setVisibility(View.INVISIBLE);
                        }
                    }
                }
        );

        sendRegistrationRequest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!account.getText().equals("") && !password.getText().equals("")) {
                            String acc = account.getText().toString(),
                                    pwd = password.getText().toString();
                            User user = new User();
                            user.setName(username.getText().toString());
                            user.setAccount(acc);
                            user.setPassword(pwd);

                            class RegisterTask extends AsyncTask<String, Integer, String> {
                                @Override
                                protected String doInBackground(String... strings) {
                                    String resBackFromServer = null;
                                    RequestBody body = RequestBody.create(JSON, strings[0]);
                                    OkHttpClient client = new OkHttpClient();
                                    Request request = new Request.Builder()
                                            .url("http://121.199.62.201:8080/register/" + strings[0])
                                            .post(body)
                                            .build();
                                    try (Response response = client.newCall(request).execute()) {
                                        resBackFromServer = response.body().string();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                    }
                                    return resBackFromServer;
                                }

                                @Override
                                protected void onPostExecute(String s) {
                                    if (s.equals("SUCCESS")) {
                                        Intent intent = new Intent(getApplicationContext(),Login.class);
                                        Toast.makeText(getApplicationContext(),R.string.congratulation,Toast.LENGTH_LONG).show();
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(),"Sorry, something go wrong.",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            new RegisterTask().execute(user.toString());
                        }
                    }
                }
        );

    }
}

