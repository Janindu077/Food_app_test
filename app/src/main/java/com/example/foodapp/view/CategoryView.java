package com.example.foodapp.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;

import java.io.IOException;
import java.net.URL;

public class CategoryView extends AppCompatActivity {
    private ImageView cateImage;
    private TextView cateTitle;
    private TextView cateDec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);
        cateDec = findViewById(R.id.cate_description);
        cateImage = findViewById(R.id.cate_img_view);
        cateTitle = findViewById(R.id.cate_name);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        String cateName = (String) extras.get("cate_name");
        final String imageUri = (String) extras.get("cate_img");

        String desc = (String) extras.get("cate_dec");
        cateTitle.setText(cateName);
        cateDec.setText(desc);
        Thread thread = new Thread(new Runnable() {
            Bitmap mIcon_val = null;
            ;
            @Override
            public void run() {
                try {
                    //Your code goes here

                    try {
                        URL newurl = new URL(imageUri);
                        mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                        cateImage.setImageBitmap(mIcon_val);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}