package com.example.hw3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MovieInfo extends AppCompatActivity {

    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        Intent intent = getIntent();
        String[] movieInfo = intent.getStringArrayExtra(RecyclerView.RESULT);

        setContentView(R.layout.activity_movie_info);

        TextView title = (TextView)findViewById(R.id.title);
        TextView year = (TextView)findViewById(R.id.year);
        TextView director = (TextView)findViewById(R.id.director);
        TextView description = (TextView)findViewById(R.id.description);
        WebView image = (WebView)findViewById(R.id.image);

        //Call the string value from RecyclerView
        title.setText(movieInfo[0]);
        year.setText(movieInfo[1]);
        director.setText(movieInfo[2]);
        image.loadUrl(movieInfo[3]);
        description.setText(movieInfo[4]);



        image.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });

        image.getSettings().setLoadWithOverviewMode(true);
        image.getSettings().setUseWideViewPort(true);
    }
}
