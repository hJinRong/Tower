package com.example.tower;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class PersonalCenter extends Activity {
    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center);
        list = (ListView) findViewById(R.id.list1);
        String[] data = {
                "昵称",
                "个人信息",
                "充值",
        };
        //为listview设置数组适配器ArrayAdapter
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
        //为ListView设置列表选项监听器
        list.setOnItemClickListener(new mItemClick());

        BroadcastReceiver lor = new LastOpportunity();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        this.registerReceiver(lor, filter);
    }

    // 定义列表选项监听器事件
    class mItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            switch (((TextView) arg1).getText().toString()) {
                case "昵称":
                    Intent intent = new Intent(PersonalCenter.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case "个人信息":
                    Intent intent1 = new Intent(PersonalCenter.this, ModifyUserInformation.class);
                    startActivity(intent1);
                    break;
                case "充值":
                    Intent intent2 = new Intent(PersonalCenter.this, Recharge.class);
                    startActivity(intent2);
                    break;
            }

        }
    }
}
