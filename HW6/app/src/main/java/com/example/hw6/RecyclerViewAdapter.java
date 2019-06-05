package com.example.hw6;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.content.Loader;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.squareup.picasso.Picasso;


//RecyclerViewAdapter
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
        implements LoaderManager.LoaderCallbacks<String> {
    private final static String TAG = "RECYCLER VIEW ADAPTER";
    private TrafficCamera[] cams;
    private String SDOTBaseUrl  = " http://www.seattle.gov/trafficcams/images/";
    private String WSDOTBaseUrl = " http://images.wsdot.wa.gov/nw/";
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onClick(int position);

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView layout;

        public ViewHolder(CardView v) {

            super(v);
            layout = v;

        }
    }

    public RecyclerViewAdapter(TrafficCamera[] cams) {
        this.cams = cams;
    }
    // new views

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Create CardView
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_items, parent, false);

        return new ViewHolder(v);
    }

    //https://www.bing.com/videos/search?q=how-to-hide-show-a-view-in-the-activity-by-clicking-a-button-in-the-android-card&view=detail&mid=71F47A689CE29EAAA1E071F47A689CE29EAAA1E0&FORM=VIRE
    @Override
    public void onBindViewHolder (ViewHolder myHolder,final int position){

        CardView v = myHolder.layout;

        TextView locationCamera = myHolder.layout.findViewById(R.id.location_camera);
        ImageView cameraImage = myHolder.layout.findViewById(R.id.camera_image);

        Context context = v.getContext();

        TrafficCamera cam = cams[position];
        locationCamera.setText((cam.getLocation()));
        Picasso.get().load(cam.getUrl()).into(cameraImage);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(position);
                    }
                }
            });

        }
        @Override
        public int getItemCount () {

            return cams.length;
        }
    }
