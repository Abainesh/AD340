package com.example.hw6;

import android.net.Uri;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkConnection {


    final static String TAG = "NETWORK_CONNECTION";

    public static String getData(String url) {
        try {
            return getData(new URL(url));
        } catch (MalformedURLException ex) {
            Log.e(TAG, "Malformed URL");
        }
        return null;
    }
    public static String getData(String url, String...uriParams){
        Uri.Builder builder = Uri.parse(url).buildUpon();
        return getData(builder.build().toString());

        /*if ((uriParams.length % 2) !=0){
            Log.e(TAG, "odd number of params provided ");
            return null;
        }
        for(int i = 0; i<uriParams.length; i+=2){
            builder.appendQueryParameter(uriParams[i],uriParams[i +1]);
        }
        return getData(builder.build().toString());*/
    }

    public static String getData(URL url) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String results = null;

        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //InputStream
            InputStream inputStream = urlConnection.getInputStream();

            //InputStream buffer reader
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Store String input
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            results = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }
}


