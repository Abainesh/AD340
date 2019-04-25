package com.example.hw2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


//As shown in https://codinginflow.com/tutorials/android/open-a-new-activity-and-pass-variables
public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "";

    public static final String RESULT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView textView1 = (TextView) findViewById(R.id.textviewone);

        textView1.setText(text);

    }

    protected void onClick(View view) {
        EditText textBox = (EditText)findViewById(R.id.response);
        String message = textBox.getText().toString();

        Intent responseIntent = new Intent();
        responseIntent.putExtra(SecondActivity.RESULT, message);

        setResult(Activity.RESULT_OK, responseIntent);
        finish();
    }

}
