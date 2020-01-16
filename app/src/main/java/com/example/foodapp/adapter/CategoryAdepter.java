package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Categories;
import com.example.foodapp.view.CategoryView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class CategoryAdepter extends RecyclerView.Adapter<CategoryAdepter.MyViewHolder> {
    private List<Categories.Category> categoryList;
    private Context context;

    public CategoryAdepter(List<Categories.Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.card_view_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.cate_title.setText(categoryList.get(position).getStrCategory());
//        URLRL newurl = null;

        Thread thread = new Thread(new Runnable() {
            Bitmap mIcon_val = null;
            int index = position;

            @Override
            public void run() {
                try {
                    //Your code goes here

                    try {
                        URL newurl = new URL(categoryList.get(position).getStrCategoryThumb());
                        mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                        holder.cate_img.setImageBitmap(mIcon_val);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//        Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CategoryView.class);
                intent.putExtra("cate_name", categoryList.get(position).getStrCategory());
                intent.putExtra("cate_img", categoryList.get(position).getStrCategoryThumb());
                intent.putExtra("cate_dec", categoryList.get(position).getStrCategoryDescription());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cate_img;
        TextView cate_title;
        CardView card_view;

        public MyViewHolder(View imageView) {
            super(imageView);
            cate_img = imageView.findViewById(R.id.title_image);
            cate_title = imageView.findViewById(R.id.title_name);
            card_view = imageView.findViewById(R.id.cate_card);
        }
    }
}
