package com.example.hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

//As shown in https://codinginflow.com/tutorials/android/open-a-new-activity-and-pass-variables

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = " ";

    public void openActivity2() {
        EditText editText1 = (EditText) findViewById(R.id.edittextone);
        String text = editText1.getText().toString();


        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.buttonone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }




}