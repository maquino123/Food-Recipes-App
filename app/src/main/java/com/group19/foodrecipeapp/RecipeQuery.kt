package com.group19.foodrecipeapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.group19.foodrecipeapp.entities.CategoryItems
import com.group19.foodrecipeapp.entities.MealItems

@Dao
interface RecipeQuery {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryItems: CategoryItems?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealsItems: MealItems?)

    @Query("DELETE FROM categoryitems")
    suspend fun clearDb()

    @Query("SELECT * FROM MealItems WHERE categoryName = :categoryName ORDER BY id DESC")
    suspend fun getMealList(categoryName:String) : List<MealItems>

    @Query("SELECT * FROM categoryitems ORDER BY id DESC")
    suspend fun getAllMealCategory() : List<CategoryItems>


}