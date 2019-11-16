package com.example.tower;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tower.Character.UserRecharge;

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
    TextView howManyRecharge;
    BigDecimal temFigure;
    String temAccount;
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
        rechargeNow = (Button) findViewById(R.id.rechargeNow);

        /*监听三个不同金额选项*/
        aHundredYuan.setOnClickListener(new handleClick());
        fiftyYuan.setOnClickListener(new handleClick());
        tenYuan.setOnClickListener(new handleClick());

        /*监听自定义金额选项*/
        checked.setOnClickListener(new checkCustomAmount());

        /*充值提交*/
        rechargeNow.setOnClickListener(new rechargeNow());

        /*下面将会被删除*/
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        temAccount = sharedPref.getString(getString(R.string.useraccount), "aaa");

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
                    protected void onPostExecute(String s) {
                        if (s.equals("SUCCESS")) {
                            Toast.makeText(Recharge.this, "Success!", Toast.LENGTH_LONG);
                        }
                    }
                }
                UserRecharge userRecharge = new UserRecharge();
                userRecharge.setFigure(temFigure);
                userRecharge.setAccount(temAccount);

                new RechargeTask().execute(userRecharge.toString());
            }
        }
    }
}
