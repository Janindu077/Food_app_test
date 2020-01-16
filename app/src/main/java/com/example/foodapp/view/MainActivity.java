package com.example.foodapp.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.CategoryAdepter;
import com.example.foodapp.api.ApiClient;
import com.example.foodapp.api.foodApi;
import com.example.foodapp.model.Categories;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MAIN_ACTIVITY";
    List<Categories.Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_main);
        foodApi foodApi = ApiClient.getFoodClient().create(foodApi.class);
        foodApi.getCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {

                Categories body = response.body();

                categories = body.getCategories();
                loadData();
                Log.i(TAG, response.body().getCategories().toString());
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

            }
        });

    }

    void loadData() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        CategoryAdepter categoryAdepter = new CategoryAdepter(categories, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(categoryAdepter);
    }
}
