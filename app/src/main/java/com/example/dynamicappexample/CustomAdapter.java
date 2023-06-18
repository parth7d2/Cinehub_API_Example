package com.example.dynamicappexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context context;

    ArrayList<MovieModel> modelArrayList;

    public CustomAdapter(Context context, ArrayList<MovieModel> modelArrayList){
        this.context = context;
        this.modelArrayList = modelArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.img_MoviePoster.setImageResource(modelArrayList.get(position).image);
//        holder.txt_TitleMovie.setText(modelArrayList.get(position).title);
//        holder.txt_SubTitleMovie.setText(modelArrayList.get(position).subTitle);

        holder.txt_TitleMovie.setText(modelArrayList.get(position).getOriginal_title());
//        holder.txt_SubTitleMovie.setText(modelArrayList.get(position).getOverview());
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + modelArrayList.get(1).getPoster_path()).into(holder.img_MoviePoster);
        holder.txt_date.setText(modelArrayList.get(position).getRelease_date());
        holder.txt_rate.setText(String.valueOf(modelArrayList.get(position).getVote_average()));
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_TitleMovie,  txt_date, txt_rate;
        ImageView img_MoviePoster;

        public ViewHolder(View itemView){
            super(itemView);
            img_MoviePoster = itemView.findViewById(R.id.img_poster);
            txt_TitleMovie = itemView.findViewById(R.id.txt_Titlemovie);
//            txt_SubTitleMovie = itemView.findViewById(R.id.txt_Subtitle_);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_rate = itemView.findViewById(R.id.txt_rate);
        }


    }
}
