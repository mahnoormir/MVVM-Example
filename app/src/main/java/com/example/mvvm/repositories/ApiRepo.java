package com.example.mvvm.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.models.PostModel;
import com.example.mvvm.retrofit.ApiInterface;
import com.example.mvvm.retrofit.RetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiRepo {
    public MutableLiveData<List<PostModel>> getPostData(){
        final MutableLiveData<List<PostModel>> postModel = new MutableLiveData<>();
        ApiInterface apiInterface = RetroClient.getinstance().create(ApiInterface.class);
        apiInterface.getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                    postModel.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.d("Failure",""+ t );
            }
        });
        return postModel;
    }

}
