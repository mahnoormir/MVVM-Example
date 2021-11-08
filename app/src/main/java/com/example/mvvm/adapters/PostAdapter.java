package com.example.mvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.R;
import com.example.mvvm.models.PostModel;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private Context context;
    private List<PostModel> postModel;

    public PostAdapter(Context context,List<PostModel> postModel){
        this.context = context;
        this.postModel= postModel;
    }

    //function for updated data
    public void setPostModel(List<PostModel> postModel){
        this.postModel= postModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating our view
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {

        holder.title.setText(this.postModel.get(position).getTitle().toString());
        holder.body.setText(this.postModel.get(position).getBody().toString());
    }

    @Override
    public int getItemCount() {
        if(this.postModel != null){
            return this.postModel.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView body;

        public MyViewHolder(View itemView){

            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            body = (TextView) itemView.findViewById(R.id.body);
        }
    }
}
