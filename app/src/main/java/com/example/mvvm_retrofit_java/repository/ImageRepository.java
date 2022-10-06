package com.example.mvvm_retrofit_java.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.mvvm_retrofit_java.model.PictureModel;
import com.example.mvvm_retrofit_java.network.ApiInterface;
import com.example.mvvm_retrofit_java.network.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {

    private static ApiInterface myInterface = null;
    private final MutableLiveData<PictureModel> listOfImages = new MutableLiveData<>();
    private int mPageNum = 1;
    private String mNextPageQuery;

    public static ImageRepository imageRepository;

    public static ImageRepository getInstance() {
        if (imageRepository == null) {
            imageRepository = new ImageRepository();
        }
        return imageRepository;
    }

    public void searchNextPage() {
        getListOfImages(mNextPageQuery, mPageNum = mPageNum + 1);
    }



    public ImageRepository() {
        myInterface = RetrofitService.getInterface();
    }

    public MutableLiveData<PictureModel> getListOfImages(String query, int pageNum) {
        mNextPageQuery = query;
        mPageNum = pageNum;
        Call<PictureModel> call = myInterface.searchImage(query, pageNum);
        call.enqueue(new Callback<PictureModel>() {
            @Override
            public void onResponse(Call<PictureModel> call, Response<PictureModel> response) {
                if (response.isSuccessful()) {
                        listOfImages.setValue(response.body());
                        Log.d("Success", response.body().toString());
                }
                else {
                        Log.d("Failed", response.message());

                    }
                }


            @Override
            public void onFailure(Call<PictureModel> call, Throwable t) {
                listOfImages.setValue(null);
                Log.d("Failed", "Failed");
            }
        });
        return listOfImages;
    }


}
