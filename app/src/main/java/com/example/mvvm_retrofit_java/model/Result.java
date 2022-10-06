package com.example.mvvm_retrofit_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  Result {

    @SerializedName("urls")
    @Expose
    private Urls urls;

    public Result(Urls urls) {

        this.urls = urls;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

}