package com.example.mvvm_retrofit_java.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvm_retrofit_java.R;
import com.example.mvvm_retrofit_java.model.PictureModel;
import com.example.mvvm_retrofit_java.model.Result;


import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Result> pictureModels = new ArrayList<>();

    public void setMovieList(List<Result> movieList) {
        this.pictureModels = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(pictureModels.get(position).getUrls().getFull()).into(((ImageAdapterViewHolder)holder).imageView);
    }

    @Override
    public int getItemCount() {
        return pictureModels.size();
    }
}





