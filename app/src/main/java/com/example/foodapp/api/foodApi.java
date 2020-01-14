package com.example.foodapp.api;

import com.example.foodapp.model.Categories;

import retrofit2.Call;
import retrofit2.http.GET;

public interface foodApi {
    @GET("categories.php")
    Call<Categories> getCategories();

}
