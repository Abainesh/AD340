package com.example.abbyapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            TextView label = new TextView(this);
            label.setText("Abby Abraha");
            setContentView(label);
            //setContentView(R.layout.activity_main);
            setContentView(label);


        }
    }



