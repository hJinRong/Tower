package com.example.tower;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.tower.Character.UserRecharge;
import com.example.tower.Db.AppDatabase;
import com.example.tower.Db.UserInfo;

import java.io.IOException;
import java.math.BigDecimal;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Recharge extends AppCompatActivity {
    Button aHundredYuan, fiftyYuan, tenYuan, checked, rechargeNow;
    EditText customAmount;
    TextView howManyRecharge, showAmount;
    BigDecimal temFigure;
    String temAccount;
    AppDatabase db;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        customAmount = (EditText) findViewById(R.id.customAmount);
        aHundredYuan = (Button) findViewById(R.id.aHundredYuan);
        fiftyYuan = (Button) findViewById(R.id.fiftyYuan);
        tenYuan = (Button) findViewById(R.id.tenYuan);
        checked = (Button) findViewById(R.id.checked);
        howManyRecharge = (TextView) findViewById(R.id.howManyRecharge);
        showAmount = (TextView) findViewById(R.id.showAmount);
        rechargeNow = (Button) findViewById(R.id.rechargeNow);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userinfo").allowMainThreadQueries().build();
        UserInfo userInfo = db.userInfoDao().getAll();
        temAccount = userInfo.account;
        /*监听三个不同金额选项*/
        aHundredYuan.setOnClickListener(new handleClick());
        fiftyYuan.setOnClickListener(new handleClick());
        tenYuan.setOnClickListener(new handleClick());

        /*监听自定义金额选项*/
        checked.setOnClickListener(new checkCustomAmount());

        /*充值提交*/
        rechargeNow.setOnClickListener(new rechargeNow());

        new FetchFigure().execute(temAccount);
    }


    class handleClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tenYuan:
                    temFigure = new BigDecimal(10.00);
                    howManyRecharge.setText(temFigure.toString());
                    break;
                case R.id.fiftyYuan:
                    temFigure = new BigDecimal(50.00);
                    howManyRecharge.setText(temFigure.toString());
                    break;
                case R.id.aHundredYuan:
                    temFigure = new BigDecimal(100.00);
                    howManyRecharge.setText(temFigure.toString());
                    break;
            }
        }
    }

    class checkCustomAmount implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (!customAmount.getText().toString().equals("") || !customAmount.getText().toString().contains(" ")) {
                howManyRecharge.setText(customAmount.getText());
                temFigure = new BigDecimal(customAmount.getText().toString());
            }
        }
    }

    class rechargeNow implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (temFigure != null) {

                class RechargeTask extends AsyncTask<String, Integer, String> {

                    @Override
                    protected String doInBackground(String... strings) {
                        String resBackFromServer = null;
                        RequestBody body = RequestBody.create(JSON, strings[0]);
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://121.199.62.201:8080/recharge/" + strings[0])
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
                    protected void onPostExecute(String res) {
                        if (!res.equals("FAILED")) {
                            Toast.makeText(Recharge.this, "充值成功!", Toast.LENGTH_LONG);
                        }
                        Intent intent = new Intent(Recharge.this, RequestForNotification.class);
                        showAmount.setText("尊敬的用户，您的账户余额：" + res);
                        intent.putExtra("TITLE", "充值反馈");
                        intent.putExtra("CONTENT_TEXT", "目前账号余额为：" + res + "元");
                        startService(intent);

                        Intent calendar = new Intent(Intent.ACTION_INSERT).setData(CalendarContract.Events.CONTENT_URI)
                                .putExtra(CalendarContract.Events.TITLE, "Recharge")
                                .putExtra(CalendarContract.Events.DESCRIPTION, "充值后余额为：" + res);
                        startActivity(calendar);
                    }
                }
                UserRecharge userRecharge = new UserRecharge();
                userRecharge.setFigure(temFigure);
                userRecharge.setAccount(temAccount);

                new RechargeTask().execute(userRecharge.toString());
            }
        }
    }

    class FetchFigure extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String resBackFromServer = null;
            RequestBody body = RequestBody.create(JSON, strings[0]);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://121.199.62.201:8080/checkBalance/" + strings[0])
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
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.tem_figure), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("myfigure", s);
            editor.commit();
            showAmount.setText(showAmount.getText() + sharedPreferences.getString("myfigure", "0.0"));
        }
    }
}
