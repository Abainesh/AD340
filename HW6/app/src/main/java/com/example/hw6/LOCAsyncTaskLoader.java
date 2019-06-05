package com.example.hw6;

import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;



public class LOCAsyncTaskLoader extends AsyncTaskLoader<String> {

    private final static String TAG = "ASYNC TASK LOADER: ";

    //constructor
    private String queryString;

    public LOCAsyncTaskLoader(Context context, String queryString){
        super(context);
        setQueryString(queryString);
    }

    private void setQueryString(String queryString) {
        this.queryString ="https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";    }

    @Nullable
    @Override
    public String loadInBackground() {
        //network operation starts
        return NetworkConnection.getData(queryString);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}