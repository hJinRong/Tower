package com.example.tower;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Register_1 extends AppCompatActivity {

    String content;
    EditText name;
    Button toNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.call);
        toNextPage = (Button) findViewById(R.id.toNextRegisPage);

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
                        if (!content.equals("")) {
                            toNextPage.setVisibility(View.VISIBLE);
                        } else {
                            toNextPage.setVisibility(View.INVISIBLE);
                        }
                    }
                }
        );


        toNextPage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Register_1.this, Register_2.class);
                        intent.putExtra("username", content);
                        startActivity(intent);
                    }
                }
        );


    }
}
