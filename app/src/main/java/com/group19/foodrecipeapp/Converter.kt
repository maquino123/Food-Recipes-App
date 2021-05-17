package com.group19.foodrecipeapp

import androidx.room.TypeConverter
import com.group19.foodrecipeapp.entities.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.group19.foodrecipeapp.entities.MealItems

class Converter {
    @TypeConverter
    fun fromCategoryList(category: List<CategoryItems>):String?{
        if (category == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>(){

            }.type
            return gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toCategoryList ( categoryString: String):List<CategoryItems>?{
        if (categoryString == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object :TypeToken<CategoryItems>(){

            }.type
            return  gson.fromJson(categoryString,type)
        }
    }

    @TypeConverter
    fun fromMealList(category: List<MealItems>):String?{
        if (category == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<MealItems>(){

            }.type
            return gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toMealList ( categoryString: String):List<MealItems>?{
        if (categoryString == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object :TypeToken<MealItems>(){

            }.type
            return  gson.fromJson(categoryString,type)
        }
    }
}