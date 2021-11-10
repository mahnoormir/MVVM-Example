package com.example.mvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvm.adapters.PostAdapter;
import com.example.mvvm.models.PostModel;
import com.example.mvvm.retrofit.ApiInterface;
import com.example.mvvm.retrofit.RetroClient;
import com.example.mvvm.viewmodels.PostViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {


    private List<PostModel> postModelList;
    private PostAdapter adapter;
    private PostViewModel viewModel;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar myToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // implementing functionalities for side menu
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        myToolBar = findViewById(R.id.myToolBar);


        //toolbar
        setSupportActionBar(myToolBar);

        //drawer layout
        toggle = new ActionBarDrawerToggle(this, drawerLayout,myToolBar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //Nav Controller implementation
        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView,navController);


        //Recycler view Implementation
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //set linear layout manager to recycler view
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostAdapter(this,postModelList);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PostViewModel.class);
        viewModel.getPostData().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                //Whenever change is observed in data
                if (postModels != null){
                    postModelList = postModels;
                    adapter.setPostModel(postModels);
                }

            }
        });

        viewModel.makeApiCall();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


}