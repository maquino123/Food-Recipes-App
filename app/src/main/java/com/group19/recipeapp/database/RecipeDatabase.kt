package com.group19.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.group19.recipeapp.query.RecipeQuery
import com.group19.recipeapp.entities.*
import com.group19.recipeapp.entities.converter.CategoryListConverter
import com.group19.recipeapp.entities.converter.MealListConverter

@Database(entities = [Recipes::class,CategoryItems::class,Category::class,Meal::class,MealsItems::class],version = 1,exportSchema = false)
@TypeConverters(CategoryListConverter::class,MealListConverter::class)
abstract class RecipeDatabase: RoomDatabase() {

    companion object{

        var recipesDatabase:RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase{
            if (recipesDatabase == null){
                recipesDatabase = Room.databaseBuilder(
                        context,
                        RecipeDatabase::class.java,
                        "recipe.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao():RecipeQuery
}