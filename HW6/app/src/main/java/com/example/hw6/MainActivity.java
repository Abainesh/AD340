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
    private final static String TAG = "MAIN ACTIVITY";
    //private TextView location;
    private RecyclerViewAdapter adapter;

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

        /*String id ="";
        String description="";
        String url ="";
        String type="";
*/
        try{
            //JSON Object
            JSONObject rootObject= new JSONObject(s);
            JSONArray features = rootObject.getJSONArray("Features");
            //Objects List
            TrafficCamera[] cameras = new TrafficCamera[features.length()];

            for ( int i = 0; i<features.length(); i++) {
                JSONObject currentImage = features.getJSONObject(i);

                JSONArray cams = currentImage.getJSONArray("Cameras");
                JSONObject firstCamera = cams.getJSONObject(0);

                String id = firstCamera.getString("Id");
                String description = firstCamera.getString("Description");
                String url = firstCamera.getString("ImageUrl");
                String type = firstCamera.getString("Type");

                TrafficCamera trafficCamera = new TrafficCamera(id, description, url, type);
                cameras[i] = trafficCamera;
            }

            RecyclerView recyclerView = findViewById(R.id.recycler_view_adapter);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager r_manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(r_manager);
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