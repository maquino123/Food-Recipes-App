package com.group19.foodrecipeapp.interfaces

import com.group19.foodrecipeapp.entities.Category
import com.group19.foodrecipeapp.entities.Meal
import com.group19.foodrecipeapp.entities.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>
}