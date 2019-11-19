package com.example.tower;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.room.Room;

import com.example.tower.Character.UserInformation;
import com.example.tower.Db.AppDatabase;
import com.example.tower.Db.UserInfo;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ModifyUserInformation extends Activity {
    private Button btn;
    private EditText name, no, eduStartDay, faculty, major, dormitory;
    private String gender;
    private RadioButton female, male;
    AppDatabase db;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_user_information);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userinfo").build();
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new mClick());
        name = (EditText) findViewById(R.id.name);
        no = (EditText) findViewById(R.id.no);
        eduStartDay = (EditText) findViewById(R.id.eduStartDay);
        faculty = (EditText) findViewById(R.id.faculty);
        major = (EditText) findViewById(R.id.major);
        dormitory = (EditText) findViewById(R.id.dormitory);

        female = (RadioButton) findViewById(R.id.female);
        male = (RadioButton) findViewById(R.id.male);

        //TODO 等待补充从数据库拉取数据填充
        /*class FetchDataFromServer extends AsyncTask<String,Integer,String> {
            @Override
            protected void onPostExecute(String res) {
                if(!res.equals("FAILED")) {
                    UserInfo userInfo = new Gson().fromJson(res, UserInfo.class);

                }
            }

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
        }*/

        UserInfo userInfo = db.userInfoDao().getAll();
        name.setText(userInfo.name);
        no.setText(userInfo.no);
        if (userInfo.gender.equals("male")) {
            gender = "male";
            if (!male.isChecked()) {
                male.toggle();
            }
        } else {
            gender = "female";
            if (!female.isChecked()) {
                female.toggle();
            }
        }
        eduStartDay.setText(userInfo.eduStartDay);
        faculty.setText(userInfo.faculty);
        major.setText(userInfo.major);
        dormitory.setText(userInfo.dormitory);
    }

    class mClick implements View.OnClickListener {
        public void onClick(View v) {
            String iname = name.getText().toString(),
                    igender = gender,
                    ino = no.getText().toString(),
                    ieduStartDate = eduStartDay.getText().toString(),
                    ifaculty = faculty.getText().toString(),
                    imajor = major.getText().toString(),
                    idor = dormitory.getText().toString();

            UserInformation information = new UserInformation();
            information.setName(iname);
            information.setNo(ino);
            information.setGender(igender);
            information.setEduStartDate(ieduStartDate);
            information.setFaculty(ifaculty);
            information.setMajor(imajor);
            information.setDormitory(idor);
            String infoJson = new Gson().toJson(information);


            class ModifyUserInformationTask extends AsyncTask<String, Integer, String> {
                @Override
                protected void onPostExecute(String s) {
                    if (s.equals("SUCCESS")) {
                        Gson gson = new Gson();
                        UserInfo userInfo = gson.fromJson(infoJson, UserInfo.class);
                        db.userInfoDao().updateOne(userInfo);
                        Toast.makeText(ModifyUserInformation.this, "You have modify your information successfully.", Toast.LENGTH_LONG);
                    }
                }

                @Override
                protected String doInBackground(String... strs) {
                    final MediaType JSON = MediaType.get("application/json; charset=utf-8");
                    String resBackFromServer = null;
                    RequestBody body = RequestBody.create(JSON, strs[0]);
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://121.199.62.201:8080/modify/" + strs[0])
                            .post(body)
                            .build();
                    try (Response response = client.newCall(request).execute()) {
                        resBackFromServer = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return resBackFromServer;
                }
            }

            new ModifyUserInformationTask().execute(infoJson);
            Intent intent = new Intent(ModifyUserInformation.this, PersonalCenter.class);
            startActivity(intent);
        }
    }

    public void onRadioButtonClicked(View view) {
        if (view.getId() == R.id.male) {
            gender = "male";
        } else {
            gender = "female";
        }
    }
}
