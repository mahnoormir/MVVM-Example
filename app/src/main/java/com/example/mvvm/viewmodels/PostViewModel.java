package com.example.mvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.models.PostModel;
import com.example.mvvm.repositories.ApiRepo;
import com.example.mvvm.retrofit.ApiInterface;
import com.example.mvvm.retrofit.RetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {

    private MutableLiveData<List<PostModel>> getPosts;  //will observe live data and update the recycler view

    public PostViewModel(){

        getPosts = new MutableLiveData<>();

    }

    public MutableLiveData<List<PostModel>> getPostData(){
        return getPosts;
    }

    public void makeApiCall(){
        ApiInterface apiService = RetroClient.getinstance().create(ApiInterface.class);
        Call<List<PostModel>> call = apiService.getPosts();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {

                //API successful
                getPosts.postValue(response.body());

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {

                //API failed
                //send null
                getPosts.postValue(null);
            }
        });
    }
}
