package com.example.mvvm_retrofit_java.network;

import com.example.mvvm_retrofit_java.model.PictureModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //search/photos?client_id=YZL4UwS7hTAZBL53-iPJaHhzUVwpnPY9tYVIQ186Z5U&query=phone&page=1&orientation=landscape&per_page=1
    @GET("search/photos?client_id=YZL4UwS7hTAZBL53-iPJaHhzUVwpnPY9tYVIQ186Z5U&page=1&orientation=landscape&per_page=10")
    Call<PictureModel> searchImage(@Query("query") String query,
                                   @Query("page") int page);
}
