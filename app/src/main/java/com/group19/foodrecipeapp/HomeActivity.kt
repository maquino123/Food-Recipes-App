package com.group19.foodrecipeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.group19.foodrecipeapp.adapter.MainCategoryAdapter
import com.group19.foodrecipeapp.adapter.SubCategoryAdapter
import com.group19.foodrecipeapp.entities.CategoryItems
import com.group19.foodrecipeapp.entities.MealItems
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeActivity : AppCompatActivity(), CoroutineScope {
    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        job = Job()

        getDataFromDb()

        mainCategoryAdapter.setClickListener(onCLicked)
        subCategoryAdapter.setClickListener(onCLickedSubItem)

    }

    private val onCLicked  = object : MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onCLickedSubItem  = object : SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity,DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllMealCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()

                getMealDataFromDb(arrMainCategory[0].strcategory)
                mainCategoryAdapter.setData(arrMainCategory)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_main_category.adapter = mainCategoryAdapter
            }


        }
    }

    private fun getMealDataFromDb(categoryName:String){
        tvCategory.text = "$categoryName Category"
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealItems>
                subCategoryAdapter.setData(arrSubCategory)
                rv_sub_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_sub_category.adapter = subCategoryAdapter
            }


        }
    }
}