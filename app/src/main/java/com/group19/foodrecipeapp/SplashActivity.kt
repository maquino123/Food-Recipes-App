package com.group19.foodrecipeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.group19.foodrecipeapp.entities.Category
import com.group19.foodrecipeapp.entities.Meal
import com.group19.foodrecipeapp.entities.MealItems
import com.group19.foodrecipeapp.interfaces.GetDataService
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        job = Job()

        clearDataBase()
        getCategories()

        btnGetStarted.setOnClickListener {
            var intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun getCategories() {
        val service = MealDBRetrofit.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object : Callback<Category> {
            override fun onFailure(call: Call<Category>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {

                for (arr in response.body()!!.categorieitems!!) {
                    getMeal(arr.strcategory)
                }
                insertDataIntoRoomDb(response.body())
            }

        })
    }


    fun getMeal(categoryName: String) {
        val service = MealDBRetrofit.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getMealList(categoryName)
        call.enqueue(object : Callback<Meal> {
            override fun onFailure(call: Call<Meal>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Meal>,
                response: Response<Meal>
            ) {

                insertMealDataIntoRoomDb(categoryName, response.body())
            }

        })
    }

    fun insertDataIntoRoomDb(category: Category?) {

        launch {
            this.let {

                for (arr in category!!.categorieitems!!) {
                    RecipeDatabase.getDatabase(this@SplashActivity)
                        .recipeDao().insertCategory(arr)
                }
            }
        }


    }

    fun insertMealDataIntoRoomDb(categoryName: String, meal: Meal?) {

        launch {
            this.let {


                for (arr in meal!!.mealsItem!!) {
                    var mealItemModel = MealItems(
                        arr.id,
                        arr.idMeal,
                        categoryName,
                        arr.strMeal,
                        arr.strMealThumb
                    )
                    RecipeDatabase.getDatabase(this@SplashActivity)
                        .recipeDao().insertMeal(mealItemModel)
                    Log.d("mealData", arr.toString())
                }

                btnGetStarted.visibility = View.VISIBLE
            }
        }


    }

    fun clearDataBase() {
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearDb()
            }
        }
    }



}