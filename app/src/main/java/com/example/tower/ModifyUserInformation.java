package com.example.tower;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ModifyUserInformation extends Activity {
    private Button btn;
    private EditText name, no, eduStartDay, faculty, major, dormitory;
    private String gender;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_user_information);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new mClick());
        name = (EditText) findViewById(R.id.name);
        no = (EditText) findViewById(R.id.no);
        eduStartDay = (EditText) findViewById(R.id.eduStartDay);
        faculty = (EditText) findViewById(R.id.faculty);
        major = (EditText) findViewById(R.id.major);
        dormitory = (EditText) findViewById(R.id.dormitory);
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

            //TODO make a new Character<UserWithInformation>, post the class json string to server.
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
