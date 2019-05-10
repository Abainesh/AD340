package com.example.hw3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//As shown in class demo code and https://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465
public class MainActivity extends AppCompatActivity {
    private Button button;
    final static String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.moivesBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this,RecyclerView.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        Log.i(TAG, view.getClass().getSimpleName());


        Button crntBut = (Button)findViewById(view.getId());
        String message = crntBut.getText().toString();

        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);

        toast.setGravity(90, 300, 1000);
        toast.show();

    }
}
