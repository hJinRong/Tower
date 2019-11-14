package com.example.tower;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class personal_data extends Activity {
    private Button btn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new mClick());
    }

    class mClick implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(personal_data.this, mine.class);
            startActivity(intent);
        }
    }

}
