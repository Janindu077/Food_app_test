package com.example.foodapp.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.api.ApiClient;
import com.example.foodapp.api.foodApi;
import com.example.foodapp.model.Categories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_main);
        foodApi foodApi = ApiClient.getFoodClient().create(foodApi.class);
        foodApi.getCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Toast.makeText(MainActivity.this, "test" + response.body().getCategories().get(1), Toast.LENGTH_SHORT).show();
                Log.i(TAG,response.body().getCategories().toString());
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

            }
        });
    }
}
