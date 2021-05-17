package com.group19.recipeapp.entities.converter

import androidx.room.TypeConverter
import com.group19.recipeapp.entities.MealsItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealListConverter {
    @TypeConverter
    fun fromCategoryList(category: List<MealsItems>):String?{
            val gson = Gson()
            val type = object : TypeToken<MealsItems>(){

            }.type
            return gson.toJson(category,type)
    }

    @TypeConverter
    fun toCategoryList ( categoryString: String):List<MealsItems>?{
            val gson = Gson()
            val type = object :TypeToken<MealsItems>(){

            }.type
            return  gson.fromJson(categoryString,type)
        }
}