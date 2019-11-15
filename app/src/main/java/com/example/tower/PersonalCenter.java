package com.example.tower;

import android.app.Activity;
import android.content.Intent;
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
        setContentView(R.layout.activity_mine);
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
                    /*Intent intent2 = new Intent(PersonalCenter.this, Login.class);
                    startActivity(intent2);*/
                    break;
            }

        }
    }
}
