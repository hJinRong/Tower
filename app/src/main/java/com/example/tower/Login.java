package com.example.tower;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tower.Character.User;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    EditText account, password;
    Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        class LoginTask extends AsyncTask<String, Integer, String> {
                            @Override
                            protected String doInBackground(String... strs) {
                                final MediaType JSON = MediaType.get("application/json; charset=utf-8");
                                String resBackFromServer = null;
                                RequestBody body = RequestBody.create(JSON, strs[0]);
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url("http://121.199.62.201:8080/login/" + strs[0])
                                        .post(body)
                                        .build();
                                try (Response response = client.newCall(request).execute()) {
                                    resBackFromServer = response.body().string();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return resBackFromServer;
                            }

                            @Override
                            protected void onPostExecute(String res) {
                                if (res.equals("CHECKED")) {
                                    /*把帐号通过SharedPreferences 存起来，将会被删除，不同Activity不能获取同一个sp*/
                                    SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString(getString(R.string.useraccount), account.getText().toString());
                                    editor.commit();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                        User user = new User();
                        user.setAccount(account.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.setName("");   //ADVOI NULLPOINTEREXCEPTION
                        new LoginTask().execute(user.toString());
                    }
                }
        );

//        注册页面跳转，直接跳转至注册页面
        registerBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Login.this, Register_1.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
