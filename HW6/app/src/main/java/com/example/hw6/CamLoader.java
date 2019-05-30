package com.example.hw6;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.annotation.Nullable;


public class CamLoader extends LOCAsyncTaskLoader<String> {

    private String mUrl;

    CamLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return null;

    }
}