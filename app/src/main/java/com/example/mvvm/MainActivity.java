package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mvvm.models.PostModel;
import com.example.mvvm.retrofit.ApiInterface;
import com.example.mvvm.retrofit.RetroClient;
import com.example.mvvm.viewmodels.PostViewModel;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    //TextView mytext;
    PostViewModel postViewModel = new PostViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mytext = findViewById(R.id.mytext);


        postViewModel.getPostData().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {

                    for (int i=0; i<postModels.size(); i++){
                        //title.setText("Title: "+ postModels.get(i).getTitle());
                        //body.setText("Body: "+ postModels.get(i).getBody());
                        Log.d("PostData", "Title: "+ postModels.get(i).getTitle());
                        Log.d("PostData", "Body:" + postModels.get(i).getBody());
                    }



            }
        });

    }

}