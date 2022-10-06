package com.example.mvvm_retrofit_java.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.mvvm_retrofit_java.model.PictureModel;
import com.example.mvvm_retrofit_java.repository.ImageRepository;

public class MainViewModel extends AndroidViewModel {
    private final ImageRepository imageRepository;
    private MutableLiveData<PictureModel> listOfImages = new MutableLiveData<>();

    public MainViewModel(Application application) {
        super(application);
       imageRepository = new ImageRepository();
    }

    public MutableLiveData<PictureModel> getImagesRepository(String query, int pageNum) {
        listOfImages = loadImages(query, pageNum);
        return listOfImages;
    }

    private MutableLiveData<PictureModel> loadImages(String query, int pageNum) {
        return imageRepository.getListOfImages(query, pageNum);
    }

    public void searchNextPage() {
        imageRepository.searchNextPage();
    }

}
