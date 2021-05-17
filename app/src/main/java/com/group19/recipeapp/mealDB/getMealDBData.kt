package com.group19.recipeapp.mealDB

import com.group19.recipeapp.entities.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface getMealDBData {
    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>
}