package com.group19.foodrecipeapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.group19.foodrecipeapp.entities.*

@Database(entities = [Recipes::class,
                      CategoryItems::class,
                      Category::class,
                      Meal::class,
                      MealItems::class],version = 1,exportSchema = false)
@TypeConverters(Converter::class)
abstract class RecipeDatabase: RoomDatabase() {

    companion object{

        var recipesDatabase: RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase {
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

    abstract fun recipeDao(): RecipeQuery
}