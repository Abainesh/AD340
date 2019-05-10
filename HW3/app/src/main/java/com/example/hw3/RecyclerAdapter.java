package com.example.hw3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private Listener listener;
    private String[][] movies;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {void onClick(int position);}


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView layout;

        public ViewHolder(CardView v) {
            super(v);
            layout = v;
        }
    }

    public RecyclerAdapter(String[][] movies) {
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);
        return new ViewHolder(cardView);
    }

    //https://www.bing.com/videos/search?q=how-to-hide-show-a-view-in-the-activity-by-clicking-a-button-in-the-android-card&view=detail&mid=71F47A689CE29EAAA1E071F47A689CE29EAAA1E0&FORM=VIRE
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        CardView cardView = holder.layout;
        TextView title = (TextView)cardView.findViewById(R.id.title);
        TextView year = (TextView)cardView.findViewById(R.id.year);


        Context context = cardView.getContext();

        String[] movie = movies[position];
        title.setText(movie[0]);
        year.setText(movie[1]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }
}
