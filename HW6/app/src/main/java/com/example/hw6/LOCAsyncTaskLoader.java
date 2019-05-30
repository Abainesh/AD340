package com.example.hw6;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;


public class LOCAsyncTaskLoader<String> extends AsyncTaskLoader<String> {

    private final static String TAG = "LOCAsyncTaskLoader: ";

    //constructor
    private String queryString;

    public LOCAsyncTaskLoader(Context context, String queryString){
        //required
        super(context);
        setQueryString(queryString);
    }

    private void setQueryString(String queryString) {
        this.queryString ="https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";

    }

    @Nullable
    @Override
    public String loadInBackground() {
        //start network operation

        return NetworkConnection.getData(queryString);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}