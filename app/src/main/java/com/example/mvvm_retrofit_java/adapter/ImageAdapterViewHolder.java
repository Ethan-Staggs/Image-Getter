package com.example.mvvm_retrofit_java.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_retrofit_java.R;

public class ImageAdapterViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public ImageAdapterViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.img);
    }
}
