package com.bignerdranch.android.food_recipes_app.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bignerdranch.android.food_recipes_app.entities.Recipes

@Dao
interface RecipeDao {

    @get:Query("SELECT * FROM recipes ORDER BY id DESC")
    val allRecipes: List<Recipes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipes)
}