package com.zeneo.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zeneo.newsapp.Activities.MainActivity;
import com.zeneo.newsapp.Activities.NewsActivity;
import com.zeneo.newsapp.Activities.R;
import com.zeneo.newsapp.Activities.DisplayActivity;
import com.zeneo.newsapp.Model.News;

import java.util.List;
import java.util.zip.CheckedOutputStream;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    List<News> list;
    Context context;


    public NewsListAdapter(List<News> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.ViewHolder holder, final int position) {

        holder.lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url",list.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.title.setText(list.get(position).getTitle());
        if (list.get(position).getImage()!=null){
            RequestOptions myOptions = new RequestOptions()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(context)
                    .load(list.get(position)
                            .getImage())
                    .apply(myOptions)
                    .into(holder.image);
        }else {
            holder.image.setImageResource(R.drawable.news_bg);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView image;
        LinearLayout lt;

        public ViewHolder(View itemView) {
            super(itemView);
            title =(TextView)itemView.findViewById(R.id.titletxt_item);
            image = (ImageView)itemView.findViewById(R.id.newsimg_item);
            lt = (LinearLayout)itemView.findViewById(R.id.newslinearlt);
        }


        @Override
        public void onClick(View view) {

        }
    }
}
