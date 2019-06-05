package com.example.hw6;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONArray;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private RecyclerViewAdapter adapter;

    private final static String TAG = "MAIN ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_adapter);
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info !=null && info.isConnected()){

            Bundle bundle = new Bundle();
            getSupportLoaderManager().restartLoader(0, bundle, this);
        }else {
            Toast toast = Toast.makeText(this, "Unable to connect!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }
        return new  LOCAsyncTaskLoader(this, queryString);

    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

        //always have to "try"
        try{
            //JSON Object
            JSONObject rootObject= new JSONObject(s);
            JSONArray features = rootObject.getJSONArray("Features");
            //Object List
            TrafficCamera[] cameras = new TrafficCamera[features.length()];

            for ( int i = 0; i<features.length(); i++) {
                JSONObject currentImage = features.getJSONObject(i);
                JSONArray cams = currentImage.getJSONArray("Cameras");

                JSONObject cameraOne = cams.getJSONObject(0);
                JSONArray Coordinates = currentImage.getJSONArray("PointCoordinate");

                double longitude = Coordinates.getDouble(0);
                double latitude = Coordinates.getDouble(1);

                String id = cameraOne.getString("Id");
                String description = cameraOne.getString("Description");
                String url = cameraOne.getString("ImageUrl");
                String type = cameraOne.getString("Type");

                TrafficCamera trafficCamera = new TrafficCamera(id, description, url, type);
                cameras[i] = trafficCamera;
            }

            RecyclerView recyclerView = findViewById(R.id.recycler_view_adapter);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            adapter = new RecyclerViewAdapter(cameras);
            recyclerView.setAdapter(adapter);
        }catch(Exception e){
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}