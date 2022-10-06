package com.example.mvvm_retrofit_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.mvvm_retrofit_java.adapter.ImageAdapter;
import com.example.mvvm_retrofit_java.model.PictureModel;
import com.example.mvvm_retrofit_java.model.Result;
import com.example.mvvm_retrofit_java.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private List<Result> listOfImages = new ArrayList<>();
    private TextView mNoImages;
    private int mPageNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoImages = findViewById(R.id.noImages);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        recyclerView = findViewById(R.id.recycler_view);
        populateRecyclerview();
        searchview();


    }

    //Setting "No images to display" to invisible and calling api with the given search term as query
    private void searchview() {
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mNoImages.setVisibility(View.INVISIBLE);
                mainViewModel.getImagesRepository(query, mPageNum);
                getResults(query);
                searchView.clearFocus();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public void getResults(String searchItem) {
        mainViewModel.getImagesRepository(searchItem, mPageNum).observe(this, new Observer<PictureModel>() {
            @Override
            public void onChanged(PictureModel pictureModel) {
                List<Result> images = pictureModel.getResults();
                listOfImages.addAll(images);
                imageAdapter.setMovieList(images);
            }
        });
    }

    private void populateRecyclerview() {
        imageAdapter = new ImageAdapter();
        GridLayoutManager linearLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(imageAdapter);
        //Increments page number by 1
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)) {
                    mainViewModel.searchNextPage();
                }
            }
        });
    }
}