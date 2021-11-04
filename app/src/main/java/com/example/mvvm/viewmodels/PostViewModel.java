package com.example.mvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.models.PostModel;
import com.example.mvvm.repositories.ApiRepo;

import java.util.List;

public class PostViewModel extends ViewModel {

    ApiRepo apiRepo;
    MutableLiveData<List<PostModel>> getPosts;

    public PostViewModel(){
        apiRepo = new ApiRepo();
    }
    public LiveData<List<PostModel>> getPostData(){
        if (getPosts == null){
            getPosts = apiRepo.getPostData();
        }
        return getPosts;
    }
}
