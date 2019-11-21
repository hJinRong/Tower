package com.example.tower;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tower.Character.User;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register_2 extends AppCompatActivity {

    EditText account, password, name, username;
    Button sendRegistrationRequest;
    String content;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2);
        Intent notify = new Intent(this, RequestForNotification.class);
        startService(notify);
        //        新增用于标题栏等的代码
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("用户注册");
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
//        原来用于数据交互的代码
        String call = getIntent().getStringExtra("username");
        username = (EditText) findViewById(R.id.username);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        sendRegistrationRequest = (Button) findViewById(R.id.sendRegistrationRequest);
        name = (EditText) findViewById(R.id.username);
        name.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                        content = name.getText().toString();
                    }
                }
        );
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

                    }
                }
        );

        sendRegistrationRequest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!(account.getText().toString().length() == 0) && !(password.getText().toString().length() == 0) && !(username.getText().toString().length() == 0)) {
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
                        } else {
                            Toast.makeText(Register_2.this, "请输入完整的信息以进行注册", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }

    //        新增用于标题栏等的代码
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar_menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigateToRegister:
                Toast.makeText(Register_2.this, "点击也不会留后门的", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

